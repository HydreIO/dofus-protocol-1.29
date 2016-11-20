package fr.aresrpg.dofus.protocol.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public final class Crypt {
	private static final char[] hash = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C',
			'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_' };

	private Crypt() {
	}

	public static String hash(String password, String key) {
		String result = "#1";
		for (int i = 0; i < password.length(); i++) {
			int aPass = password.charAt(i) / 16;
			int bPass = password.charAt(i) % 16;
			result = result + hash[(aPass + (int) key.charAt(i)) % hash.length] + hash[(bPass + (int) key.charAt(i)) % hash.length];
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
		for(int i = 0; i < splitted.length ; i++){
			int v = Integer.parseInt(splitted[i]);
			sb.append((char)((v >> 4) + 48)).append((char)((v & 15) + 48));
		}
		return sb.toString();
	}

	public static String cryptPort(int port) {
		int cur = port;
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i > 0; i--) {
			sb.append(hash[cur >> (6 * i)]);
			cur &= (int)(Math.pow(64 , i) -1);
		}
		sb.append(hash[cur]);
		return sb.toString();
	}

	public static int decryptPort(char[] chars) {
		if(chars.length != 3)
			throw new IllegalArgumentException("Port must be encrypted in 3 chars");
		int port = 0;
		for (int i = 0; i < 2; i++)
			port += (int)(Math.pow(64 , 2 - i) * indexOfHash(chars[i]));
		port += indexOfHash(chars[2]);
		return port;
	}

	public static int indexOfHash(char ch){
		for(int i = 0 ; i < hash.length ; i++)
			if(hash[i] == ch)
				return i;
		return -1;
	}

	public static String prepareKey(String key) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		for(int i = 0  ; i < key.length() ; i+=2) {
			sb.append((char)Long.parseLong(key.substring(i , i +2) , 16));
		}
		return decode(sb.toString());
	}

	public static String decypherData(String data , String preparedKey , int checksum) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		for(int i = 0  ; i < data.length() ; i+=2) {
			long a = Long.parseLong(data.substring(i , i+2) , 16);
			long b = (long) preparedKey.charAt(((i>>1) + checksum) % preparedKey.length());
			sb.append((char) (a ^ b));
		}
		return decode(sb.toString());
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
						sb.append((char)Integer.parseInt(s.substring(i + 2 , i + 4) , 16));
						i += 2;
					} catch (NumberFormatException e){
						sb.append('%');
					}
				} else {
					try {
						sb.append((char)Integer.parseInt(s.substring(i + 1 , i + 3) , 16));
						i += 2;
					} catch (NumberFormatException e){
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


	public static class Cell {
		private int movement;
		private boolean layerObject2Interactive;
		private int layerObject2Num;

		public Cell(int movement, boolean layerObject2Interactive, int layerObject2Num) {
			this.movement = movement;
			this.layerObject2Interactive = layerObject2Interactive;
			this.layerObject2Num = layerObject2Num;
		}

		public int getMovement() {
			return movement;
		}

		public boolean isLayerObject2Interactive() {
			return layerObject2Interactive;
		}

		public int getLayerObject2Num() {
			return layerObject2Num;
		}
	}

	public static List<Cell> uncompressData(String data) {
		List<Cell> cells = new ArrayList<>(data.length()/10);
		for(int i = 0 ; i < data.length()/10 ; i++){
			int index = i * 10;
			String cell = data.substring(index , index + 10);

			int movement = (indexOfHash(cell.charAt(2)) & 56) >> 3;
			System.out.println(movement);
			boolean layerObject2Num = ((indexOfHash(cell.charAt(0)) & 2) << 12) + ((indexOfHash(cell.charAt(7)) & 1) << 12) + (indexOfHash(cell.charAt(8)) << 6) + indexOfHash(cell.charAt(9)) == 1;
			int layerObject2Interactive = (indexOfHash(cell.charAt(7)) & 2) >> 1;
			cells.add(new Cell(movement , layerObject2Num , layerObject2Interactive));
		}
		return cells;
	}

}

