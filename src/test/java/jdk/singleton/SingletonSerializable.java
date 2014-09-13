package jdk.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.security.AccessControlException;

public class SingletonSerializable implements Serializable {

	private static final long serialVersionUID = 228766961501442111L;

	private static final SingletonSerializable INSTANSE = new SingletonSerializable();

	private SingletonSerializable() {
		throw new AccessControlException("不容许重复创建实例");
	}

	public static SingletonSerializable getInstanse() {
		return INSTANSE;
	}

	/**
	 * JVM反序列化时候会调用readResolve方法 维护单例模式
	 */
	private Object readResolve() throws ObjectStreamException {
		return INSTANSE;
	}

	public static void main(String[] args) throws Exception {
		/**
		 * 构造器无法创建实例
		 */
		Constructor<SingletonSerializable> constructor = SingletonSerializable.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		// Caused by: java.security.AccessControlException: 不容许重复创建实例
		System.out.println(constructor.newInstance());
	}

}
