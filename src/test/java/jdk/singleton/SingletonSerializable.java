package jdk.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Constructor;

import jdk.unsafe.UnsafeTest;

public class SingletonSerializable implements Serializable {

	private static final long serialVersionUID = 228766961501442111L;

	private static final SingletonSerializable INSTANSE = new SingletonSerializable();

	private String name;
	private SingletonSerializable() {
		name = "mumu";
//		throw new AssertionError();
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
		System.out.println(constructor.newInstance().name);	// mumu

		/**
		 * Unsafe.allocateInstance 也是通过无参构造器创建对象，但是不会执行里面的代码
		 */
		SingletonSerializable object = (SingletonSerializable) UnsafeTest.getUnsafe().allocateInstance(SingletonSerializable.class);
		System.out.println(object.name);	// null

		SingletonEnum instanse = SingletonEnum.INSTANSE;
		System.out.println(instanse);
	}

}
