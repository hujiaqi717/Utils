import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @auther HuJiaqi
 * @createTime 2016年04月08日 13时08分
 * @discription 时间工具类
 */
@SuppressWarnings("unused")
public class DateUtils {

    // 标准程序时间格式
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    // 带毫秒的标准程序时间格式
    public final static String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    // 标准显示时间格式
    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 13时44分
     * @discription 获取标准程序时间格式的当前时间字符串
     */
    public static String getStringDate() {
        Date date = new Date();
        String patten = YYYYMMDDHHMMSS;
        return getStringDate(date, patten);
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 13时44分
     * @discription 获取标准程序时间格式的date时间字符串
     */
    public static String getStringDate(Date date) {
        String patten = YYYYMMDDHHMMSS;
        return getStringDate(date, patten);
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 13时44分
     * @discription 获取patten时间格式的当前时间字符串
     */
    public static String getStringDate(String patten) {
        Date date = new Date();
        return getStringDate(date, patten);
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 13时44分
     * @discription 获取patten时间格式的date时间字符串
     */
    public static String getStringDate(Date date, String patten) {
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        return sdf.format(date);
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时10分
     * @discription 获取当前时间加i天的date格式，负数为减
     */
    public static Date getAddDayDate(int i) {
        Date date = new Date();
        return getAddDayDate(date, i);
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时10分
     * @discription 获取date时间加i天的date格式，负数为减
     */
    public static Date getAddDayDate(Date date, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, i);
        return c.getTime();
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时10分
     * @discription 获取当前时间加i周的date格式，负数为减
     */
    public static Date getAddWeekDate(int i) {
        Date date = new Date();
        return getAddWeekDate(date, i);
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时10分
     * @discription 获取date时间加i周的date格式，负数为减
     */
    public static Date getAddWeekDate(Date date, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, i * 7);
        return c.getTime();
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时10分
     * @discription 获取当前时间加i月的date格式，负数为减
     */
    public static Date getAddMonthDate(int i) {
        Date date = new Date();
        return getAddMonthDate(date, i);
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时10分
     * @discription 获取date时间加i月的date格式，负数为减
     */
    public static Date getAddMonthDate(Date date, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, i);
        return c.getTime();
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时10分
     * @discription 获取当前时间加i年的date格式，负数为减
     */
    public static Date getAddYearDate(int i) {
        Date date = new Date();
        return getAddYearDate(date, i);
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时10分
     * @discription 获取date时间加i年的date格式，负数为减
     */
    public static Date getAddYearDate(Date date, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, i);
        return c.getTime();
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时30分
     * @discription 获取当前时间本周第一天的date格式，考虑中国人习惯问题，这里取的是周一
     */
    public static Date getFirstDayOfWeek() {
        Date date = new Date();
        return getFirstDayOfWeek(date);
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时30分
     * @discription 获取date时间本周第一天的date格式，考虑中国人习惯问题，这里取的是周一
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, 2);
        return c.getTime();
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时30分
     * @discription 获取当前时间本周最后一天的date格式
     */
    public static Date getLastDayOfWeek() {
        Date date = new Date();
        return getLastDayOfWeek(date);
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时30分
     * @discription 获取date时间本周最后一天的date格式
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, -1);
        return c.getTime();
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时30分
     * @discription 获取当前时间本月第一天的date格式
     */
    public static Date getFirstDayOfMonth() {
        Date date = new Date();
        return getFirstDayOfMonth(date);
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时30分
     * @discription 获取date时间本月第一天的date格式
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时30分
     * @discription 获取当前时间本月最后一天的date格式
     */
    public static Date getLastDayOfMonth() {
        Date date = new Date();
        return getLastDayOfMonth(date);
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时30分
     * @discription 获取date时间本月最后一天的date格式
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时30分
     * @discription 获取当前时间本年第一天的date格式
     */
    public static Date getFirstDayOfYear() {
        Date date = new Date();
        return getFirstDayOfYear(date);
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时30分
     * @discription 获取date时间本年第一天的date格式
     */
    public static Date getFirstDayOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_YEAR, 1);
        return c.getTime();
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时30分
     * @discription 获取当前时间本年最后一天的date格式
     */
    public static Date getLastDayOfYear() {
        Date date = new Date();
        return getLastDayOfYear(date);
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 14时30分
     * @discription 获取date时间本年最后一天的date格式
     */
    public static Date getLastDayOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_YEAR, -1);
        return c.getTime();
    }

}
