package guava.base;

import com.google.common.base.Optional;

public class OptionalTest {

	public static void main(String[] args) {
		Optional<?> possible = Optional.of(null);
		if (possible.isPresent()) {
			Object value = possible.get();
			System.out.println("value:" + value);
		}
	}

}
