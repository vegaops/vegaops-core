package org.prophetech.hyperone.vegaops.engine.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public class DateUtils {

    public static Date parseUTC(String dateValue, String... fmtOpt) {
        String fmt = null;
        try {
            if (dateValue.matches(
                    "^[1-9]\\d{9}$")) {
                return new Date(Long.valueOf(dateValue + "000"));
            } else if (dateValue.matches(
                    "^[1-9]\\d{12}$")) {
                return new Date(Long.valueOf(dateValue));
            } else if (StringUtils.isBlank(dateValue)) {
                return null;
            }
            if (fmtOpt.length == 0) {
                if (dateValue.matches(
                        "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s+(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$")) {
                    fmt = "yyyy-MM-dd HH:mm:ss";
                } else if (dateValue.matches(
                        "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s+(20|21|22|23|[0-1]\\d):[0-5]$")) {
                    fmt = "yyyy-MM-dd HH:mm";
                } else if (dateValue
                        .matches("^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$")) {
                    fmt = "yyyy-MM-dd";
                } else if (dateValue.matches(
                        "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T+(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\dZ$")) {
                    fmt = "yyyy-MM-dd'T'HH:mm:ss'Z'";
                } else if (dateValue.matches(
                        "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T+(20|21|22|23|[0-1]\\d):[0-5]\\dZ$")) {
                    fmt = "yyyy-MM-dd'T'HH:mm'Z'";
                }
            } else {
                fmt = fmtOpt[0];
            }
            SimpleDateFormat dataFmt = new SimpleDateFormat(fmt);
            dataFmt.setTimeZone(TimeZone.getTimeZone("UTC"));
            return dataFmt.parse(dateValue);
        } catch (Throwable e) {
            if (e instanceof ParseException && fmtOpt.length != 0) {
                return parseUTC(dateValue);
            }
            log.error("时间格式:{}不正确", dateValue, e);
        }
        return null;
    }

    public static Date parse(String dateValue) {
        try {
            if (StringUtils.isBlank(dateValue)) {
                return null;
            } else if (dateValue.matches(
                    "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s+(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$")) {
                return org.apache.commons.lang3.time.DateUtils
                        .parseDate(dateValue, "yyyy-MM-dd HH:mm:ss");
            } else if (dateValue.matches(
                    "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s+(20|21|22|23|[0-1]\\d):[0-5]$")) {
                return org.apache.commons.lang3.time.DateUtils
                        .parseDate(dateValue, "yyyy-MM-dd HH:mm");
            } else if (dateValue
                    .matches("^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$")) {
                return org.apache.commons.lang3.time.DateUtils.parseDate(dateValue, "yyyy-MM-dd");
            } else if (dateValue.matches(
                    "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T+(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\dZ$")) {
                return parseUTC(dateValue);
            } else if (dateValue.matches(
                    "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T+(20|21|22|23|[0-1]\\d):[0-5]\\dZ$")) {
                return parseUTC(dateValue);
            } else if (dateValue.matches(
                    "^[1-9]\\d{9}$")) {
                return new Date(Long.valueOf(dateValue + "000"));
            } else if (dateValue.matches(
                    "^[1-9]\\d{12}$")) {
                return new Date(Long.valueOf(dateValue));
            }
        } catch (Throwable e) {
            log.error("时间格式:{}不正确", dateValue, e);
        }
        return null;
    }

}
