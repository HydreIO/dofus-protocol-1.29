/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Crypt {
	private static final char[] HASH = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C',
			'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_' };

	private static final char[] HEX_CHAR = "0123456789ABCDEF".toCharArray();

	private Crypt() {
	}

	public static String hash(String password, String key) {
		String result = "#1";
		for (int i = 0; i < password.length(); i++) {
			int aPass = password.charAt(i) / 16;
			int bPass = password.charAt(i) % 16;
			result = result + HASH[(aPass + (int) key.charAt(i)) % HASH.length] + HASH[(bPass + (int) key.charAt(i)) % HASH.length];
		}
		return result;
	}

	public static String md5hash(String password, String key) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return "#2" + toHexString(md.digest((toHexString(md.digest(password.getBytes()), false) + key).getBytes()), false);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String toHexString(byte[] bytes, boolean space) {
		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xff & bytes[i]);
			if (hex.length() == 1) hexString.append('0');
			hexString.append(hex);
			if (space) hexString.append(' ');
		}
		return hexString.toString();
	}

	public static String decryptIp(String ipCrypt) {
		StringBuilder ip = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			int d1 = ipCrypt.charAt(i) - 48;
			i++;
			int d2 = ipCrypt.charAt(i) - 48;

			ip.append((d1 & 15) << 4 | d2 & 15);
			if (i < 7) ip.append('.');
		}
		return ip.toString();
	}

	public static String cryptIp(String ip) {
		String[] splitted = ip.split("\\.");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < splitted.length; i++) {
			int v = Integer.parseInt(splitted[i]);
			sb.append((char) ((v >> 4) + 48)).append((char) ((v & 15) + 48));
		}
		return sb.toString();
	}

	public static String cryptPort(int port) {
		int cur = port;
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i > 0; i--) {
			sb.append(HASH[cur >> (6 * i)]);
			cur &= (int) (Math.pow(64, i) - 1);
		}
		sb.append(HASH[cur]);
		return sb.toString();
	}

	public static int decryptPort(char[] chars) {
		if (chars.length != 3)
			throw new IllegalArgumentException("Port must be encrypted in 3 chars");
		int port = 0;
		for (int i = 0; i < 2; i++)
			port += (int) (Math.pow(64, 2 - i) * indexOfHash(chars[i]));
		port += indexOfHash(chars[2]);
		return port;
	}

	public static int decryptPort(String ePort) {
		return decryptPort(ePort.toCharArray());
	}

	public static char encode64(int var) {
		return HASH[var];
	}

	public static int indexOfHash(char ch) {
		for (int i = 0; i < HASH.length; i++)
			if (HASH[i] == ch)
				return i;
		throw new ArrayIndexOutOfBoundsException(ch + " is not in hash array");
	}

	public static char hashToIndex(int i) {
		return HASH[i];
	}

	public static String prepareKey(String key) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < key.length(); i += 2) {
			sb.append((char) Integer.parseInt(key.substring(i, i + 2), 16));
		}
		return decode(sb.toString());
	}

	public static String decipherData(String data, String preparedKey, int checksum) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length(); i += 2) {
			int a = Integer.parseInt(data.substring(i, i + 2), 16);
			int b = preparedKey.charAt((i / 2 + checksum) % preparedKey.length());
			sb.append((char) (a ^ b));
		}
		return decode(sb.toString());
	}

	public static char checksum(String s) {
		int v = 0;
		for (char c : s.toCharArray())
			v += c & 15;
		return HEX_CHAR[v & 15];
	}

	public static String getRandomNetworkKey() { // From ankama
		int size = (int) (Math.round(Math.random() * 20) + 10);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++)
			sb.append(HASH[(int) (Math.random() * HASH.length)]);
		String check = checksum(sb.toString()) + sb.toString();
		return check + checksum(check);
	}

	private static String decode(String s) {
		if (s.indexOf('%') == -1 && s.indexOf('+') == -1)
			return s;

		long len = s.length();
		StringBuilder sb = new StringBuilder();
		char ch;

		for (int i = 0; i < len; i++) {
			ch = s.charAt(i);
			if (ch == '%' && i + 2 < len && s.charAt(i + 1) != '%') {
				if (s.charAt(i + 1) == 'u' && i + 5 < len) {
					// unicode hex sequence
					try {
						sb.append((char) Integer.parseInt(s.substring(i + 2, i + 4), 16));
						i += 2;
					} catch (NumberFormatException e) {
						sb.append('%');
					}
				} else {
					try {
						sb.append((char) Integer.parseInt(s.substring(i + 1, i + 3), 16));
						i += 2;
					} catch (NumberFormatException e) {
						sb.append('%');
					}
				}
				continue;
			}

			if (ch == '+')
				sb.append(' ');
			else
				sb.append(ch);
		}
		return sb.toString();
	}

}
