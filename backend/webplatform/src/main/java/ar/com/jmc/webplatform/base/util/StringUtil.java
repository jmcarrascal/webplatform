package ar.com.jmc.webplatform.base.util;

import java.util.Collection;
import java.util.Iterator;

public final class StringUtil {
	
	private StringUtil() { }
	
	public static String join(Collection<String> col, String separator) {
		if (col == null) return null;
		if (col.isEmpty()) return "";
		
		StringBuffer join = new StringBuffer();
		for (Iterator<String> it = col.iterator(); it.hasNext(); ) {
			join.append(it.next());
			if (it.hasNext()) {
				join.append(", ");
			}
		}
		return join.toString();
	}
	
	public static boolean emptyOrNull(String s) {
		return (s == null || s.isEmpty());
	}
	
	public static boolean isAlphabetic(String s) {
		// Que no contenga digitos ni signos de puntuacion (para permitir caracteres acentuados)
		return s.matches("\\D*") && s.matches("[^\\p{Punct}]*");
	}
	
}
