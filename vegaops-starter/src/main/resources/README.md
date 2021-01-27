## hyperone-web 部署说明

最终打包生成的文件目录结构如下

-------
| config

| lib

| hyperone-web.jar

--------

* config 所有的可改动配置文件都在这个目录
* lib 项目依赖的库都在这个目录
* hyperone-web.jar 启动的主文件

## 启动方式

java -server  -jar hyperone-web.jar

java其他options可以直接加在命令行