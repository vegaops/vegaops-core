package org.prophetech.hyperone;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.LogFactory;
import org.prophetech.hyperone.vegaops.engine.core.CloudTemplateFactory;
import org.prophetech.hyperone.vegaops.engine.model.CloudContainer;
import org.prophetech.hyperone.vegaops.engine.model.Credentials;
import org.prophetech.hyperone.vegaops.engine.parser.ContainerParser;
import org.prophetech.hyperone.vegaops.engine.utils.ValidationUtil;
import org.springframework.util.FileCopyUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class Application {

    public static void main(String[] args) throws Exception {
        try {
            if (args == null || args.length == 0) {
                args = new String[]{"-h"};
            }
            CommandLineParser parser = new BasicParser();
            Options options = new Options();
            InputConfig inputConfig = new InputConfig();
            List<Opt.OptImpl> optList = Opt.LoadOpts.loadOpts(inputConfig);
            for (Opt opt : optList) {
                options.addOption(opt.opt(), opt.logOpt(), opt.hasArg(), opt.description());
            }
            CommandLine commandLine = parser.parse(options, args);
            Opt.LoadOpts.loadObject(inputConfig, commandLine);
            addClassPath(inputConfig.getConfigDir(), false);
            addClassPath(inputConfig.getLibDir(), true);
            ValidationUtil.validateThrow(inputConfig);
            File inputFile = new File(inputConfig.getInputFile());
            File outputFile = new File(inputConfig.getOutputFile());
            CloudTemplateFactory.getVegaopsConfig().setTemplateFormat(inputConfig.getTemplateFormat());
            CloudContainer container = Objects.equals("json", inputConfig.getFormat()) ? loadCloudContainerFromJson(inputFile) : loadCloudContainerFromYaml(inputFile);
            ValidationUtil.validateThrow(container);
            addClassPath4Vendor(inputConfig.getProviderDir(), container.getVendor(), container.getVersion());
            Credentials credentials = container.getCredentials();
            container.getVariables().put("accessKey", credentials.getKey());
            container.getVariables().put("secret", credentials.getSecret());
            container.getVariables().put("regionId", credentials.getRegionId());
            container.getVariables().put("zoneId", credentials.getZoneId());
            ContainerParser.install(container);
            Map<String, Map<String, Object>> containerOutput = container.getContainerOutput();
            if (Objects.equals("json", inputConfig.getFormat())) {
                FileCopyUtils.copy(JSON.toJSONBytes(containerOutput, SerializerFeature.PrettyFormat), outputFile);
            } else {
                Yaml yaml = new Yaml();
                String dump = yaml.dumpAsMap(containerOutput);
                FileCopyUtils.copy(dump.getBytes(), outputFile);
            }
        } catch (Throwable e) {
            LogFactory.getLog(Application.class).error(ExceptionUtils.getRootCause(e).getLocalizedMessage());
            System.exit(-1);
        }
    }

    private static void addClassPath4Vendor(String providerDir, String vendor, String version) throws Exception {
        File file = new File(providerDir);
        if (!file.exists()) {
            throw new RuntimeException("providerDir not exists");
        }
        addClassPath(String.join(File.separator, providerDir, vendor, version, "lib"), true);
        addClassPath(file.getParent(), false);
    }


    private static void addClassPath(String root, boolean onlyJar) throws Exception {
        List<URL> urls = new ArrayList<>();
        addClassPath(root, urls, onlyJar);
        addClassPath(urls);
    }

    private static void addClassPath(List<URL> urls) throws Exception {
        Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        add.setAccessible(true);
        for (URL url : urls) {
            add.invoke(classLoader, new Object[]{url});
        }
    }

    private static void addClassPath(String root, List<URL> urls, boolean onlyJar) throws MalformedURLException {
        File file = new File(root);
        if (file.exists()) {
            if (file.isFile() || !onlyJar) {
                urls.add(file.toURL());
            } else if (file.isDirectory() && onlyJar) {
                File[] list = file.listFiles(f -> f.getName().endsWith("jar") || f.isDirectory());
                for (File sub : list) {
                    addClassPath(sub.getPath(), urls, onlyJar);
                }
            }
        }
    }

    private static CloudContainer loadCloudContainerFromJson(File inputFile) throws IOException {
        CloudContainer container = new CloudContainer();
        container.readFormMap(JSON.parseObject(FileCopyUtils.copyToByteArray(inputFile), HashMap.class));
        return container;
    }

    private static CloudContainer loadCloudContainerFromYaml(File inputFile) throws IOException {
        CloudContainer container = new CloudContainer();
        Yaml yaml = new Yaml();
        try (InputStream is = new FileInputStream(inputFile)) {
            Map map = yaml.load(is);
            container.readFormMap(map);
        }

        return container;
    }
}
