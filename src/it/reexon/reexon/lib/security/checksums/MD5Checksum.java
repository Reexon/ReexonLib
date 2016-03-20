package it.reexon.reexon.lib.security.checksums;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.MessageDigestAlgorithms;

public class MD5Checksum {

	/**
	 * Create a checksum in MD5 from file name
	 * 
	 * @param filename
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static byte[] createChecksum(String filename) throws NoSuchAlgorithmException, IOException {
		InputStream fis = new FileInputStream(filename);

		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
		int numRead;

		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);

		fis.close();
		return complete.digest();
	}

	/**
	 * How-to for a faster way to convert a byte array to a HEX string
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public static String getMD5Checksum(String filename) throws NoSuchAlgorithmException, IOException {
		byte[] b = createChecksum(filename);
		String result = "";

		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}
}