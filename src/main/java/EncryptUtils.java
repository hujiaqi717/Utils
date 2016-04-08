import java.security.MessageDigest;

/**
 * @auther HuJiaqi
 * @createTime 2016年04月08日 16时52分
 * @discription
 */
@SuppressWarnings("unused")
public class EncryptUtils {

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 16时57分
     * @discription 获取标准32位大写MD5
     */
    public static String getMD5(String str) {
        return getMD5(str, "unicode");
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 16时55分
     * @discription 获取设置编码格式的32位大写MD5
     */
    public static String getMD5(String str, String charset) {
        try {
            byte[] b = str.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(b);
            b = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i : b) {
                int j = i & 0xff;
                if (j < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(j));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("MD5加密失败" + e);
        }
    }

}
