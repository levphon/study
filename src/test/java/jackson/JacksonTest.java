package jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonTest {

	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	}

	public static void main(String[] args) throws Exception {

	}

}
