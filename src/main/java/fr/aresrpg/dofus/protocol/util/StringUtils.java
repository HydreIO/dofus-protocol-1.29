package fr.aresrpg.dofus.protocol.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

	public static String[] split(String value , String separator) {
		String v = value;
		List<String> buf = new ArrayList<>();
		int index;
		while ((index = v.indexOf(separator)) != -1){
			buf.add(v.substring(0 , index));
			v = v.substring(index + 1);
		}
		buf.add(v);
		return buf.toArray(new String[buf.size()]);
	}
}
