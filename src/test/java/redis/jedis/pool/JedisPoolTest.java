package redis.jedis.pool;

import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolTest {

	private final static JedisPool jedisPool;

	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();

			Properties properties = new Properties();
			properties.load(JedisPoolTest.class.getClassLoader().getResourceAsStream("redis/jedis/redis.properties"));

			String host = properties.getProperty("redis.ip");
			int port = Integer.parseInt(properties.getProperty("redis.port", "6379"));

			int maxTotal = Integer.parseInt(properties.getProperty("redis.pool.maxTotal", String.valueOf(config.getMaxTotal())));
			int maxIdle = Integer.parseInt(properties.getProperty("redis.pool.maxIdle", String.valueOf(config.getMaxIdle())));
			int minIdle = Integer.parseInt(properties.getProperty("redis.pool.minIdle", String.valueOf(config.getMinIdle())));
			long maxWaitMillis = Long.parseLong(properties.getProperty("redis.pool.minIdle"));

			config.setMaxTotal(maxTotal);
			config.setMaxIdle(maxIdle);
			config.setMinIdle(minIdle);
			config.setMaxWaitMillis(maxWaitMillis);

			jedisPool = new JedisPool(config, host, port);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		Jedis jedis = jedisPool.getResource();
		System.out.println(jedis.get("key"));
	}

}
