package aliyun.parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestScGroupRuleParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("aliyun","1.0",  "securityGroupRule");
        cloudTemplate.setComponentId("1234567890");
        Map input=new HashMap();
        input.put("accessKey","xxxxx");
        input.put("secret","xxxxx");
        input.put("regionId", "cn-beijing");
        cloudTemplate.inputVars(input);
        return cloudTemplate;
    }

    @Test
    @SneakyThrows
    public void parseCreateScGroupRule(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("direction","egress");
        cloudTemplate.getVariables().put("portRange","22/22");
        cloudTemplate.getVariables().put("securityGroupId","sg-m5eg427bft0ybhyqr6wq");
        cloudTemplate.getVariables().put("description","xjhtet");
        cloudTemplate.getVariables().put("policy","accept");
        cloudTemplate.getVariables().put("ipProtocol","tcp");
        cloudTemplate.getVariables().put("destCidrIp","10.0.0.0/8");
        CloudAction install = cloudTemplate.getCloudAction("install");
        ActionParser.parse(install);
    }


    @Test
    @SneakyThrows
    public void parseGetGetScGroupRule(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("securityGroupId","sg-2zehjgqf52p7pjuyegg7");
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseDeleteScGroupRule(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("direction","egress");
        cloudTemplate.getVariables().put("portRange","22/22");
        cloudTemplate.getVariables().put("securityGroupId","sg-bp1hjeeponkkq55zwwa1");
        cloudTemplate.getVariables().put("providerId","0B6475FF4BD08BCABA4ECB1BDA7C00B9");
        cloudTemplate.getVariables().put("policy","accept");
        cloudTemplate.getVariables().put("ipProtocol","tcp");
        cloudTemplate.getVariables().put("destCidrIp","10.0.0.0/8");
        CloudAction uninstall = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(uninstall);
    }

}
