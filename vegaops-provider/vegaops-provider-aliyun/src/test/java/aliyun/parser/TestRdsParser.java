package aliyun.parser;

import lombok.SneakyThrows;
import org.junit.Test;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudAction;
import org.prophetech.hyperone.vegaops.engine.model.CloudTemplate;
import org.prophetech.hyperone.vegaops.engine.parser.ActionParser;

import java.util.HashMap;
import java.util.Map;

public class TestRdsParser {
    @SneakyThrows
    private CloudTemplate getCloudTemplate(){
        CloudTemplate cloudTemplate = CloudTemplateFactory.getTemplate("aliyun","1.0",  "rdsInstance");
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
    public void parseCreateRds(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("engine","MySQL");
        cloudTemplate.getVariables().put("engineVersion","5.5");
        cloudTemplate.getVariables().put("dBInstanceClass","mysql.n2.small.2c");
        cloudTemplate.getVariables().put("dBInstanceStorage","20");
        cloudTemplate.getVariables().put("dBInstanceNetType","Internet");
        cloudTemplate.getVariables().put("securityIPList","10.23.12.24");
        cloudTemplate.getVariables().put("vswitchId","vsw-m5evtfq1gll5n6oa0c381");
        cloudTemplate.getVariables().put("vpcId","vpc-m5e9qoxj7oc327igjv1ip");
        cloudTemplate.getVariables().put("instanceNetworkType","VPC");
        cloudTemplate.getVariables().put("category","HighAvailability");
        cloudTemplate.getVariables().put("payType","Postpaid");
        cloudTemplate.getVariables().put("description","Vegaopstest");
        cloudTemplate.getVariables().put("dBInstanceStorageType","cloud_ssd");
        cloudTemplate.getVariables().put("zoneId","cn-qingdao-b");
        CloudAction install = cloudTemplate.getCloudAction("install");
        ActionParser.parse(install);
    }

    @Test
    @SneakyThrows
    public void parseGetRdsList(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        CloudAction list = cloudTemplate.getCloudAction("list");
        ActionParser.parse(list);
    }

    @Test
    @SneakyThrows
    public void parseGetRds(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("dBInstanceId","rm-bp1hqcq25n4o84unp");
        CloudAction query = cloudTemplate.getCloudAction("query");
        ActionParser.parse(query);
    }

    @Test
    @SneakyThrows
    public void parseDeleteRds(){
        CloudTemplate cloudTemplate=getCloudTemplate();
        cloudTemplate.getVariables().put("dBInstanceId","rm-bp1hqcq25n4o84unp");
        CloudAction uninstall = cloudTemplate.getCloudAction("uninstall");
        ActionParser.parse(uninstall);
    }

}
