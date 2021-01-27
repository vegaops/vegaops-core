package aliyun.parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestScGroupParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("aliyun","1.0", "securityGroup");
        cloudTemplate.setComponentId("1234567890");
        Map input=new HashMap();
        //onePro云账号
        input.put("accessKey","xxxxx");
        input.put("secret","xxxxx");
        input.put("regionId", "cn-qingdao");
        cloudTemplate.inputVars(input);
        return cloudTemplate;
    }

    @Test
    @SneakyThrows
    public void parseCreateScGroup(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("vpcId","vpc-bp13pmhrqee6v6nt90ero");
        cloudTemplate.getVariables().put("securityGroupName","vegaopsTest6");
//        cloudTemplate.getVariables().put("securityGroupType","cn-hangzhou-b");
        cloudTemplate.getVariables().put("description","xjhtet");

        CloudAction install = cloudTemplate.getCloudAction("install");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseGetScGroupList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseGetGetScGroup(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("securityGroupId","sg-bp13a472vharpyi6j4l");
        CloudAction query = cloudTemplate.getCloudAction("query");
        ActionParser.parse(query);
    }

    @Test
    @SneakyThrows
    public void parseDeleteScGroup(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("securityGroupId","sg-bp14yziiuvu2z4tfnf79");
        CloudAction uninstall = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(uninstall);
    }

}
