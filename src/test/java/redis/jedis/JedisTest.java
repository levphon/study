package redis.jedis;

import redis.clients.jedis.Jedis;

public class JedisTest {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.0.254");
		jedis.set("key", "value");	// OK
		System.out.println(jedis.get("key"));
		jedis.close();
	}

}
