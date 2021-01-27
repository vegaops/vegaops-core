package org.prophetech.hyperone;

import com.alibaba.fastjson.JSON;
import lombok.Setter;
import org.apache.commons.cli.CommandLine;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Opt {
    String opt();

    String logOpt() default "";

    String description();

    boolean hasArg() default true;


    public static class LoadOpts {
        private static void doWithFields(Class clazz, Function<Field,Void> function){
            Field[] declaredFields = clazz.getDeclaredFields();
            for(Field field:declaredFields){
                field.setAccessible(true);
                function.apply(field);
            }
        }
        public static List<OptImpl> loadOpts(Object object) {
            List<OptImpl> optList = new ArrayList<>();
            if (object != null) {
                doWithFields(object.getClass(), f -> {
                    f.setAccessible(true);
                    if (f.isAnnotationPresent(Opt.class)) {
                        OptImpl opt = new OptImpl(f.getAnnotation(Opt.class));
                        opt.setField(f);
                        if (opt.logOpt==null||opt.logOpt.trim().length()==0) {
                            opt.setLogOpt(f.getName());
                        }
                        optList.add(opt);
                    }
                    return null;
                });
            }
            return optList;
        }

        public static void loadObject(Object object, CommandLine commandLine) {
            List<OptImpl> optList = loadOpts(object);
            optList.forEach(o -> {
                if (commandLine.hasOption(o.opt())) {
                    Field field = o.getField();
                    field.setAccessible(true);
                    try {
                        if (!o.hasArg()) {
                        } else if (String.class.isAssignableFrom(field.getType())) {
                            field.set(object, commandLine.getOptionValue(o.opt));
                        } else if (Boolean.class.isAssignableFrom(field.getType())) {
                            field.set(object, Boolean.valueOf(commandLine.getOptionValue(o.opt)));
                        } else {
                            field.set(object, JSON.parseObject(commandLine.getOptionValue(o.opt), field.getType()));
                        }
                    }catch (Throwable e){
                        throw new RuntimeException(e);
                    }
                }
            });
            if(commandLine.hasOption("h")){
                optList.forEach(o -> {
                    System.out.println("-"+o.opt()+"   "+o.description());
                });
                System.exit(0);
            }
        }
    }

    @Setter
    public static class OptImpl implements Opt {
        private String opt;
        private String logOpt;
        private String description;
        private boolean hasArg;
        private Field field;

        public OptImpl() {
        }

        public OptImpl(Opt opt) {
            this.opt = opt.opt();
            this.logOpt = opt.logOpt();
            this.description = opt.description();
            this.hasArg = opt.hasArg();
        }

        @Override
        public String opt() {
            return opt;
        }

        @Override
        public String logOpt() {
            return logOpt;
        }

        @Override
        public String description() {
            return description;
        }

        @Override
        public boolean hasArg() {
            return hasArg;
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return Opt.class;
        }

        public Field getField() {
            return field;
        }
    }


}
