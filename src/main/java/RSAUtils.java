import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @auther HuJiaqi
 * @createTime 2016年04月08日 17时26分
 * @discription RSA公钥加密私钥解密，用于签名
 */
public class RSAUtils {

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 17时41分
     * @discription 加密算法：RSA
     */
    private static final String ENCRYPTION_ALGORITHM = "RSA";

    private static final String CIPHER_TYPE = "RSA/ECB/PKCS1Padding";

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 17时42分
     * @discription RSA初始化的长度，512-2048
     */
    private static final Integer INIT_LENGTH = 512;

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 17时42分
     * @discription 最大加密字节数组长度，114以上，否则无法加密
     */
    private static final Integer ENCRYPT_LENGTH = 114;

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 17时43分
     * @discription 最大解密字节数组长度，128以上，否则无法解密
     */
    private static final Integer DECRYPT_LENGTH = 128;

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 17时43分
     * @discription 测试明文，必须32位以上，建议用32位标准md5，这里并没有处理中文乱码，因为是毫无意义的
     */
    private static final String TEST_ORIGINAL = EncryptUtils.getMD5("test");

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 17时44分
     * @discription base64编码器
     */
    private static final BASE64Encoder base64Encoder = new BASE64Encoder();

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 17时43分
     * @discription base64解码器
     */
    private static final BASE64Decoder base64Decoder = new BASE64Decoder();

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 18时01分
     * @discription 公钥加密
     */
    public static String encryptByPublicKey(String publicKey, String originalText) throws Exception {
        byte[] keyByteArray = base64Decoder.decodeBuffer(publicKey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyByteArray);
        KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPTION_ALGORITHM);
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(CIPHER_TYPE);
        cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
        byte[] originalByteArray = base64Decoder.decodeBuffer(originalText);
        int length = originalByteArray.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int start = 0;
        byte[] temp;
        int i = 0;
        while (length - start > 0) {
            if (length - start > ENCRYPT_LENGTH) {
                temp = cipher.doFinal(originalByteArray, start, ENCRYPT_LENGTH);
            } else {
                temp = cipher.doFinal(originalByteArray, start, length - start);
            }
            out.write(temp, 0, temp.length);
            i++;
            start = i * ENCRYPT_LENGTH;
        }
        byte[] finalByteArray = out.toByteArray();
        out.close();
        return base64Encoder.encode(finalByteArray);
    }

    /**
     * @auther HuJiaqi
     * @createTime 2016年04月08日 18时01分
     * @discription 私钥解密
     */
    public static String decryptByPrivateKey(String privateKey, String cipherText) throws Exception {
        byte[] keyByteArray = base64Decoder.decodeBuffer(privateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyByteArray);
        KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPTION_ALGORITHM);
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(CIPHER_TYPE);
        cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
        byte[] originalByteArray = base64Decoder.decodeBuffer(cipherText);
        int length = originalByteArray.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int start = 0;
        byte[] temp;
        int i = 0;
        while (length - start > 0) {
            if (length - start > DECRYPT_LENGTH) {
                temp = cipher.doFinal(originalByteArray, start, DECRYPT_LENGTH);
            } else {
                temp = cipher.doFinal(originalByteArray, start, length - start);
            }
            out.write(temp, 0, temp.length);
            i++;
            start = i * DECRYPT_LENGTH;
        }
        byte[] finalByteArray = out.toByteArray();
        out.close();
        return base64Encoder.encode(finalByteArray);
    }

    public static void main(String[] args) throws Exception {
        // 开始计算非对称密钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ENCRYPTION_ALGORITHM);
        keyPairGenerator.initialize(INIT_LENGTH);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        String publicKey = base64Encoder.encode(rsaPublicKey.getEncoded());
        String privateKey = base64Encoder.encode(rsaPrivateKey.getEncoded());
        // 开始测试加解密
        String testOriginal = TEST_ORIGINAL;
        System.out.println("加密前的明文：" + testOriginal);
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);
        String cipherText = encryptByPublicKey(publicKey, testOriginal);
        System.out.println("加密后的密文：" + cipherText);
        String originalText = decryptByPrivateKey(privateKey, cipherText);
        System.out.println("解密后的明文：" + originalText);
    }

}
