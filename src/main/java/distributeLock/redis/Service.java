package distributeLock.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by ${WangChengYong} on 2018/2/26.
 */
public class Service {

    private static JedisPool jedisPool = null;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 设置最大连接数
        jedisPoolConfig.setMaxTotal(200);
        // 设置最大空闲数
        jedisPoolConfig.setMaxIdle(8);
        // 设置最大等待时间
        jedisPoolConfig.setMaxWaitMillis(1000 * 100);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 3000);
    }

    DistributeLock lock = new DistributeLock(jedisPool);

    int n = 500;

    public void secondKill() {
        // 返回锁的value值，供释放锁时候进行判断
        String indentifier = lock.lockin("resource", 5000, 1000);
        System.out.println(MyThread.currentThread().getName() + "获得了锁");
        System.out.println(--n);
        lock.releaseLock("resource", indentifier);
    }
}
