package com.SGC.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Util {
	// // public static int ConverterDocumentos(String s) {
	// return Integer.parseInt(s.replaceAll("\\D", ""));
	// }
	public static <K, V> Map<K, V> invert(Map<K, V> map) {

		Map<K, V> inv = new HashMap<K, V>();

		for (Entry<K, V> entry : map.entrySet())
			inv.put(entry.getKey(), entry.getValue());

		return inv;
	}
}
