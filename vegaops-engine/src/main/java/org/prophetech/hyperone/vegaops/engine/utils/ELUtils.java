package org.prophetech.hyperone.vegaops.engine.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.expression.BeanFactoryAccessor;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.expression.EnvironmentAccessor;
import org.springframework.context.expression.MapAccessor;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionException;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.DataBindingPropertyAccessor;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hyberbin on 2018/7/29.
 */
public class ELUtils {
    private static final Logger log = LoggerFactory.getLogger(ELUtils.class);
    private static final ParameterNameDiscoverer paraNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    private static PropertyAccessor[] propertyAccessors = new PropertyAccessor[]{new MapAccessor(), new EnvironmentAccessor(),
            new BeanFactoryAccessor(), DataBindingPropertyAccessor.forReadWriteAccess()};
    /**
     * 是否单独运行，如果不是在spring web下运行standalone=true，通常是在Test模式下
     */
    private static boolean standalone=false;

    public static Map<String, Object> getParamMap(Method method, Object[] args) {
        String[] paraNames = paraNameDiscoverer.getParameterNames(method);
        Map map = new HashMap();
        if (!ArrayUtils.isEmpty(args) && !ArrayUtils.isEmpty(paraNames)) {
            if (args.length != paraNames.length) {
                throw new IllegalArgumentException("args length must be equal to paraNames length");
            }
            for (int i = 0; i < paraNames.length; i++) {
                map.put(paraNames[i], args[i]);
            }
        }
        return map;
    }

    /**
     * 获取EL表达式的值
     *
     * @param expression EL表达式
     * @param vars       变量
     * @return
     */
    public static <T> T getSpelValue(String expression, Object vars) throws ExpressionException {
        return getSpelValue(expression, vars,true);
    }
    public static <T> T getSpelValue(String expression, Object vars, boolean logError){
        return getSpelValue(expression, vars, logError,null);
    }
    public static <T> T getSpelValue(String expression, Object vars, boolean logError,Map... varMap) throws ExpressionException {
        if (StringUtils.isBlank(expression)) {
            return null;
        }
        ExpressionParser ep = new SpelExpressionParser();
        StandardEvaluationContext context = new HyperoneStandardEvaluationContext(vars,varMap);
        BeanFactory beanFactory =  new DefaultListableBeanFactory();
        if(standalone&&expression.startsWith("@")){
            log.warn("standalone模式下@bean.xxx直接返回null");
            return null;
        }
        context.setBeanResolver(new BeanFactoryResolver(beanFactory));
        try {
            return (T) ep.parseExpression(expression).getValue(context);
        } catch (Throwable e) {
            if (logError) {
                log.warn("运算SPEL表达式：{}出错！", expression, e);
            }
            if(e instanceof ExpressionException){
                throw (ExpressionException)e;
            }
            throw new IllegalArgumentException(e);
        }
    }

    private static class HyperoneStandardEvaluationContext extends StandardEvaluationContext{
        private Object variableObject;
        private Map variableMap=new HashMap();

        public HyperoneStandardEvaluationContext(Object variableObject,Map... variableMap) {
            this.variableObject = variableObject;
            if(variableMap!=null){
                for(Map map:variableMap){
                    this.variableMap.putAll(map);
                }
            }
            if(variableObject instanceof Map){
                this.variableMap.putAll((Map<String, Object>) variableObject);
                this.setVariables(this.variableMap);
            }
        }

        @Override
        public Object lookupVariable(String name) {
            if("me".equals(name)){
                return variableObject instanceof Map?variableMap:variableObject;
            }else if(variableObject instanceof Map){
                return super.lookupVariable(name);
            }else  if(variableMap.containsKey(name)){
                return variableMap.get(name);
            }else {
                Map map=new HashMap();
                map.put("var",variableObject);
                try {
                    return ELUtils.getSpelValue("#var?."+name,map,false);
                }catch (Throwable e){
                    return null;
                }
            }
        }
    }

    public static <T> T getSpelValue(Map vars, String template) throws ExpressionException {
        if (StringUtils.isBlank(template)) {
            return null;
        }
        SpelExpressionParser ep = new SpelExpressionParser();
        TemplateParserContext context = new TemplateParserContext();
        try {
            Expression expression = ep.parseExpression(template, context);
            SimpleEvaluationContext evaluationContext = SimpleEvaluationContext
                    .forPropertyAccessors(propertyAccessors).build();
            return (T) expression.getValue(evaluationContext, vars);
        } catch (ExpressionException e) {
            log.warn("运算SPEL表达式模板：{}出错！", template, e);
            throw e;
        }
    }

    public static boolean isSpelExpression(String expression){
        return expression!=null&&(expression.startsWith("#")||expression.startsWith("@")||expression.startsWith("new ")||expression.startsWith("T("));
    }
}
