package org.prophetech.hyperone.vegaops.engine.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.prophetech.hyperone.vegaops.engine.bean.FastBeanCopier;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.*;
@Slf4j
public class SecretMaskJsonSerializer extends MapSerializer {
    private static final SecretMaskJsonSerializer instance = new SecretMaskJsonSerializer();
    private static final SerializeConfig serializeConfig = new SerializeConfig() {
        @Override
        public ObjectSerializer getObjectWriter(Class<?> clazz) {
            return instance;
        }
    };
    private  static Set<String> MASK_KEYS;

    private SecretMaskJsonSerializer() {
        MASK_KEYS=new HashSet<>();
        MASK_KEYS.add("secret");
        MASK_KEYS.add("secretKey");
        MASK_KEYS.add("username");
        MASK_KEYS.add("password");
    }

    public static void addSecretMaskKeys(Collection<String> keys) {
        if (keys != null) {
            MASK_KEYS.addAll(keys);
        }
    }

    public static String toJSONString(Object object, Collection<String> maskKeys) {
        addSecretMaskKeys(maskKeys);
        return toJSONString(object);
    }

    public static String toJSONString(Object object, String... maskKeys) {
        addSecretMaskKeys(maskKeys);
        return toJSONString(object);
    }

    public static String toJSONString(Object object) {
        try {
            return JSON.toJSONString(object, serializeConfig);
        }catch (Throwable e){
            log.error("SecretMaskJsonSerializer.toJSONString error",e);
        }
        return JSON.toJSONString(object);
    }


    public static void addSecretMaskKeys(String... keys) {
        if(keys!=null){
            addSecretMaskKeys(Arrays.asList(keys));
        }
    }



    public static Set<String> getMaskKeys() {
        return MASK_KEYS;
    }

    private ObjectSerializer getDefaultSerializer(Class type) {
        if (Map.class.isAssignableFrom(type)) {
            return this;
        }
        return SerializeConfig.getGlobalInstance().getObjectWriter(type);
    }

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if (object != null) {
            if (object instanceof Map) {
                super.write(serializer, object, fieldName, fieldType, features);
                return;
            }
            object = convertIfMap(object);
            getDefaultSerializer(object.getClass()).write(serializer, object, fieldName, fieldType, features);
            return;
        }
        super.write(serializer, object, fieldName, fieldType, features);
    }

    private Object convertIfMap(Object object) {
        if(object==null){
            return null;
        }
        if (object.getClass().isArray()) {
            Object[] array = (Object[]) object;
            List list = new ArrayList(array.length);
            for (Object o : array) {
                list.add(convertIfMap(o));
            }
            return list;
        } else if (object instanceof Collection) {
            Collection collection = (Collection) object;
            List list = new ArrayList(collection.size());
            for (Object o : collection) {
                list.add(convertIfMap(o));
            }
            return list;
        } else if (!(object instanceof String)
                && !(object instanceof Date)
                && !(ClassUtils.isPrimitiveWrapper(object.getClass()))
                && !(object instanceof Number)
                && Modifier.isPublic(object.getClass().getModifiers())
        ) {
            return FastBeanCopier.copy(object, HashMap.class);
        }
        return object;
    }

    @Override
    protected Object processValue(JSONSerializer jsonBeanDeser, BeanContext beanContext, Object object, String key, Object propertyValue) {
        if (getMaskKeys().contains(key) && propertyValue instanceof String) {
            return processMaskValue((String) propertyValue);
        } else {
            return super.processValue(jsonBeanDeser, beanContext, object, key, propertyValue);
        }
    }

    private String processMaskValue(String plan) {
        if (StringUtils.isNotBlank(plan)) {
            if (plan.length() > 8) {
                char[] mask = new char[plan.length() - 8];
                Arrays.fill(mask, '*');
                return plan.substring(0, 4) + new String(mask) + plan.substring(plan.length() - 4);
            } else {
                char[] mask = new char[plan.length()];
                Arrays.fill(mask, '*');
                return new String(mask);
            }
        }
        return plan;
    }
}
