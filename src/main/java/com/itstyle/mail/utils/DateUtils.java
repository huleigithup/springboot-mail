package com.itstyle.mail.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    private static final String YYYY_MM_DD ="yyyy-MM-dd HH:mm:ss";

    public static String dateFormatString(Date date) {
        SimpleDateFormat format =  new SimpleDateFormat(YYYY_MM_DD);
        return format.format(date);
    }
    // LocalDateTime获取毫秒数
    /**
     * 获取秒数
      */
    Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    /**
     *  获取毫秒数
     */
    Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();

    // LocalDateTime与String互转

    //时间转字符串格式化
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    String dateTimeOfString = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);

    //字符串转时间
    String dateTimeStr = "2018-07-28 14:11:15";
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime dateTimeOfLocal = LocalDateTime.parse(dateTimeStr, df);

    // Date与LocalDateTime互转
    //将java.util.Date 转换为java8 的java.time.LocalDateTime,默认时区为东8区
    public static LocalDateTime dateConvertToLocalDateTime(Date date) {
        return date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
    }

    //将java8 的 java.time.LocalDateTime 转换为 java.util.Date，默认时区为东8区
    public static Date localDateTimeConvertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
    }

//    /** 测试转换是否正确 */
//    @Test
//    public void testDateConvertToLocalDateTime() {
//        Date date = DateUtils.parseDate("2018-08-01 21:22:22", DateUtils.DATE_YMDHMS);
//        LocalDateTime localDateTime = DateUtils.dateConvertToLocalDateTime(date);
//        Long localDateTimeSecond = localDateTime.toEpochSecond(ZoneOffset.of("+8"));
//        Long dateSecond = date.toInstant().atOffset(ZoneOffset.of("+8")).toEpochSecond();
//        Assert.assertTrue(dateSecond.equals(localDateTimeSecond));
//    }
}
