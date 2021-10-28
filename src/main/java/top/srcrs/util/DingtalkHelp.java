package top.srcrs.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URLEncoder;

public class DingtalkHelp {

	/** 获取日志记录器对象 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DingtalkHelp.class);

	public static String getSign(Long timestamp, String secret) {
		try {
			String stringToSign = timestamp + "\n" + secret;
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
			byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
			String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
			return sign;
		} catch (Exception e) {
			LOGGER.error("获取签名错误 -- " + e);
			return "";
		}

	}

}
