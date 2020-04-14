package beans;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/*
 * 双重检查单例模式创建Jedis连接池
 * 
 * */
/**
 * @author xiongys
 *
 */
public class JedisPoolUtil {

	private static volatile JedisPool jedisPool=null;
	private JedisPoolUtil() {}
	/**
	 * @功能 返回JedisPool
	 *
	 */
	public static JedisPool getInstance() {
		if(null== jedisPool) {
			synchronized(JedisPoolUtil.class){
				if(null== jedisPool) {
					JedisPoolConfig poolConfig= new JedisPoolConfig();			//jedispool初始值配置
					poolConfig.setMaxTotal(900);							//池中最大连接数
					poolConfig.setMaxIdle(30);								//最大空闲数
					poolConfig.setMaxWaitMillis(100*1000);					//最大等待时间，等待100秒超时会抛出异常
										
					
					jedisPool=new JedisPool(poolConfig, "127.0.0.1", 6379, 10000, "xc98923iverson");
					
				}
			}
			
		}		
		return jedisPool;		
	}
	
	
}
