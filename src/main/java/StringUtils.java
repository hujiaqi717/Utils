/**
 * @auther HuJiaqi
 * @createTime 2016年04月13日 11时57分
 * @discription String工具类
 */
@SuppressWarnings({"unchecked", "unused"})
public class StringUtils {

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月14日 10时29分
     * @discription 对象是否为null或者""，仅用于可包装成String的对象
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        String str = String.valueOf(obj);
        return (("".equals(str)) || (str == null));
    }

}
