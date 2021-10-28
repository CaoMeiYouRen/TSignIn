package top.srcrs.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 * 对字符串进行加密
 * @author srcrs
 * @Time 2020-10-31
 */
public class Encryption {
	
	public static void main(String[] args) {
		System.out.println(Encryption.hmacSha256("123456", "abc"));
	}
	
    /** 获取日志记录器对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(Encryption.class);

    /**
     * 对字符串进行 MD5加密
     * @param str 传入一个字符串
     * @return String 加密后的字符串
     * @author srcrs
     * @Time 2020-10-31
     */
    public static String enCodeMd5(String str){
        try{
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes("UTF-8"));
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）

            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e){
            LOGGER.error("字符串进行MD5加密错误 -- " + e);
            return "";
        }
    }

    public static String hmacSha256(String data, String key) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception e){
            LOGGER.error("字符串进行HMACSHA256加密错误 -- " + e);
            return "";
        }
   }
}
