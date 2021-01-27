import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Tools {
    public static void main(String[] args) throws Exception {
        try {
            if (args == null || args.length == 0) {
                throw new RuntimeException("用法: Tools -action [args...]");
            }
            String action = args[0].toLowerCase().replace("-", "");
            List<Method> declaredMethods = Arrays.stream(Tools.class.getDeclaredMethods()).filter(m -> !m.getName().contains("$")).collect(Collectors.toList());
            Optional<Method> methodOptional = declaredMethods.stream().filter(method -> Objects.equals(method.getName(), action)).findAny();
            Method method = methodOptional.orElseThrow(() -> new RuntimeException("action只能取值于[" + String.join(",", declaredMethods.stream().map(m -> m.getName()).collect(Collectors.toList())) + "]"));
            method.setAccessible(true);
            if (method.getParameterTypes().length != args.length - 1) {
                throw new RuntimeException("action:" + action + "的参数个数需要是" + (method.getParameterTypes().length) + "个," + JSON.toJSONString(Arrays.stream(method.getParameters()).map(p -> p.getName()).collect(Collectors.toList())));
            }
            String[] objects = new String[args.length - 1];
            System.arraycopy(args, 1, objects, 0, method.getParameterTypes().length);
            System.out.println(method.invoke(Tools.class, objects));
        } catch (Throwable e) {
            System.out.println(ExceptionUtils.getRootCause(e).getLocalizedMessage());
            System.exit(-1);
        }
    }

    private static String parse(String fileName, String key) throws Throwable {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new RuntimeException(fileName + "文件不存在");
        }
        try (InputStream is = new FileInputStream(file)) {
            HashMap map;
            if (fileName.toLowerCase().endsWith(".json")) {
                map = JSON.parseObject(is, HashMap.class);
            } else {
                map = new Yaml().load(is);
            }
            return String.valueOf(map.get(key));
        }
    }

    private static String download(String url, String fileDir) throws IOException {
        Connection connect = Jsoup.connect(url);
        connect.method(Connection.Method.GET);
        connect.ignoreContentType(true);
        connect.ignoreHttpErrors(true);
        connect.validateTLSCertificates(true);
        connect.maxBodySize(0);
        Connection.Response execute = connect.execute();
        if (execute.statusCode() == 200) {
            File tmp = File.createTempFile("vegaops", ".zip");
            try (FileOutputStream fileOutputStream = new FileOutputStream(tmp)) {
                fileOutputStream.write(execute.bodyAsBytes());
            }
            if (tmp.exists()) {
                ZipFile zip = new ZipFile(tmp);
                File destDir = new File(fileDir);
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }
                for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements(); ) {
                    ZipEntry entry = entries.nextElement();
                    String zipEntryName = entry.getName();
                    String outPath = (fileDir + "/" + zipEntryName).replaceAll("\\*", "/");

                    // 判断路径是否存在,不存在则创建文件路径
                    File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                    if (new File(outPath).isDirectory()) {
                        continue;
                    }
                    // 输出文件路径信息
                    try (FileOutputStream out = new FileOutputStream(outPath);
                         InputStream in = zip.getInputStream(entry)) {
                        byte[] buf1 = new byte[1024];
                        int len;
                        while ((len = in.read(buf1)) > 0) {
                            out.write(buf1, 0, len);
                        }
                    }
                }
            } else {
                return "error";
            }
        }
        return execute.statusCode() + "";
    }
}
