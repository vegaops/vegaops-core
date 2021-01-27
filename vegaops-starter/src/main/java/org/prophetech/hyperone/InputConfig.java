package org.prophetech.hyperone;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class InputConfig {
    @Opt(opt = "i", description = "File to input")
    @NotNull(message = "输入文件不能为空")
    private String inputFile;

    @Opt(opt = "p", description = "Provider files dir. default is ./provider")
    private String providerDir = "./provider";

    @Opt(opt = "l", description = "Common Lib jars dir. default is ./lib")
    private String libDir = "./lib";

    @Opt(opt = "c", description = "Config dir. default is ./config")
    private String configDir = "./config";

    @Opt(opt = "f", description = "File format. json / yaml. default is yaml")
    private String format = "yaml";

    @Opt(opt = "tf", description = "Template file format. json / yaml. default is json")
    private String templateFormat = "json";

    @Opt(opt = "o", description = "File to save program output to")
    private String outputFile = "out";

    @Opt(opt = "h", description = "Print this usage information", hasArg = false)
    private String help;

    public String getOutputFile() {
        return outputFile.endsWith("." + format) ? outputFile : (outputFile + "." + format);
    }
}
