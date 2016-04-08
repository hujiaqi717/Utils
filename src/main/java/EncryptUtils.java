import java.security.MessageDigest;

/**
 * @auther HuJiaqi
 * @createTime 2016年04月08日 16时52分
 * @discription 加密工具类
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

    /**
     * @author HuJiaqi
     * @createTime 2015年11月13日 下午5:19:26
     * @description 加密姓名，格式有三种：1、郑和：郑*，2、司马迁：司**，3、东方不败：东方**，4、你跺你也麻：你跺***
     */
    public static String getEncryptName(String name) {
        if (name.length() == 2) {
            name = name.substring(0, 1) + "*";
        } else if (name.length() == 3) {
            name = name.substring(0, 1) + "**";
        } else if (name.length() == 4) {
            name = name.substring(0, 2) + "**";
        } else {
            int len = name.length();
            name = name.substring(0, 2);
            for (int i = 0; i < len - 2; i++) {
                name += "*";
            }
        }
        return name;
    }

    /**
     * @author HuJiaqi
     * @createTime 2015年11月13日 下午5:19:03
     * @description 加密手机号：格式150****0717
     */
    public static String getEncryptPhone(String phone) {
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }

}
