package org.prophetech.hyperone.vegaops.engine.model;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.web.bean.FastBeanCopier;
import org.springframework.util.Assert;

import java.util.*;

@Setter
@Getter
public class CloudTemplate {
    private String path;
    private String vendor;
    private String version;
    private String nodeType;
    private String componentId;
    private String[] actions;
    private Map<String,String> actionMap=new HashMap();
    private LinkedHashMap<String, Map> bizMap = new LinkedHashMap<>();
    private LinkedHashMap<String, Object> variables = new LinkedHashMap<>();

    public CloudAction getCloudAction(String action) {
        Map map = bizMap.get(action);
        if (map == null) {
            throw new RuntimeException(path + "中未定义action：" + action);
        }
        CloudAction cloudAction = new CloudAction();
        cloudAction.setCloudTemplate(this);
        cloudAction.readFormMap(map);
        return cloudAction;
    }

    public void readFormMap(Map source) {
        FastBeanCopier.copy(source, this);
        Assert.notNull(path, "path must not be null");
        Assert.notNull(version, "version must not be null");
        Assert.notNull(vendor, "vendor must not be null");
        Assert.notNull(nodeType, "nodeType must not be null");
        Assert.notNull(actions, "actions must not be null");
        Map map = new LinkedHashMap(source);
        String[] keys = "path,vendor,nodeType,componentId,actions,bizMap".split(",");
        for (String key : keys) {
            map.remove(key);
        }
        List<String> actionList=new ArrayList<>(actions.length);
        for (String action : actions) {
            String[] split = action.split("-");
            Assert.isTrue(split.length==1||split.length==2,"action:"+action+" format error!");
            actionList.addAll(Arrays.asList(split));
            if(split.length==2){
                actionMap.put(split[0],split[1]);
            }
        }
        for (String action : actionList) {
            Map bizConfig = (Map) map.remove(action);
            Assert.notNull(bizConfig, "action:" + action + " must not be null");
            bizConfig.put("action", action);
            bizMap.put(action, bizConfig);
        }
        variables.putAll(map);
    }

    public void inputVars(Map vars){
        variables.putAll(vars);
    }

    /**
     * 获取一个业务的卸载action名称
     * @param action
     * @return 如果传入action本身就是卸载操作那么返回本身，其他返回卸载action名称
     */
    public String getUninstallAction(String action) {
        String uninstallAction = actionMap.get(action);
        return uninstallAction == null ? action : uninstallAction;
    }
}
