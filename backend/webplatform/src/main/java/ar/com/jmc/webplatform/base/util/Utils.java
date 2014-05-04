package ar.com.jmc.webplatform.base.util;

public final class Utils {

	private Utils() {}
	
	public static boolean equal(Object a, Object b) {
		return (a != null && a.equals(b)) || (a == null && b == null);
	}
}
