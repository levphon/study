package jdk.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UnsafeTest {

	private static Unsafe unsafe;

	static {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			unsafe = (Unsafe) field.get(null);
		} catch (Exception e) {
			throw new RuntimeException("can not get instance : sun.misc.Unsafe", e);
		}
	}

	public static Unsafe getUnsafe() {
		return unsafe;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(unsafe);
	}
}
