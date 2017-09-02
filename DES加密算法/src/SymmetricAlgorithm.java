import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class SymmetricAlgorithm {// 对称算法

	private static String strKey = "@#$%^&*&";
	private String info;

	public SymmetricAlgorithm(String info) {
		this.info = info;
	}

	public SymmetricAlgorithm(String info, String strKey) {
		this.info = info;
		this.strKey = strKey;
	}

	private Key getKey() {
		byte[] keyByte = this.strKey.getBytes();
		byte[] _keyByte = new byte[strKey.length()];
		for (int i = 0; (i < keyByte.length) && (i < _keyByte.length); i++) {
			_keyByte = keyByte;

		}
		return new SecretKeySpec(_keyByte, "DES");
	}

	public String desEncrypt() {
		return desEncrypt(this.info, "UTF-8");

	}

	public String desEncrypt(String origin, String encoding) {// 译成密码
		if (origin == null || encoding == null) {
			return null;
		}

		return encrypt(origin.getBytes(), "DES");

	}

	public String desDecrypt() {// 解码

		return desDecrypt(this.info, "UTF-8");

	}

	public String desDecrypt(String ciperData, String encoding) {
		if (ciperData == null || encoding == null)
			return encoding;
		byte[] bytes = decrypt(EncryptHelper.byte2hex(ciperData), "DES");
		try {
			return new String(bytes, encoding);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private byte[] decrypt(byte[] data, String algorithm) {// 解码
		Key key = getKey();
		try {
			Cipher c1 = Cipher.getInstance(algorithm);
			try {
				c1.init(2, key);
				try {
					return c1.doFinal(data);
				} catch (IllegalBlockSizeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private String encrypt(byte[] data, String algorithm) {
		Key key = getKey();
		try {
			Cipher c1 = Cipher.getInstance(algorithm);
			c1.init(1, key);

			byte[] cipherByte = c1.doFinal(data);
			return EncryptHelper.byte2hex(cipherByte);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		SymmetricAlgorithm s = new SymmetricAlgorithm("woshinibabba", strKey);
		String sa_pwd = s.desEncrypt("woshinibabba", "UTF-8");
		System.out.println("加密后为：" + sa_pwd);

		SymmetricAlgorithm s2 = new SymmetricAlgorithm(sa_pwd);
		String password = s2.desDecrypt(sa_pwd, "UTF-8");
		System.out.println("解密后为：" + password);
	}

	static class EncryptHelper {
		public static final String DEFAULT_ENCODING = "UTF-8";

		public static String byte2hex(byte[] bytes) {
			StringBuffer retString = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				retString.append(Integer.toHexString(256 + (bytes[i] & 0xFF))
						.substring(1));

			}
			return retString.toString().toUpperCase();

		}

		public static byte[] byte2hex(String hex) {
			byte[] bts = new byte[hex.length() / 2];
			for (int i = 0; i < bts.length; i++) {
				bts[i] = (byte) Integer.parseInt(
						hex.substring(2 * i, 2 * i + 2), 16);

			}
			return bts;

		}
	}
}
