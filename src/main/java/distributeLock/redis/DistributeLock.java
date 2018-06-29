package distributeLock.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;

import java.util.List;
import java.util.UUID;

/**
 * Created by ${WangChengYong} on 2018/2/26.
 */
public class DistributeLock {

    private final JedisPool jedisPool;

    public DistributeLock(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 加锁
     *
     * @param key            锁的key
     * @param acquireTimeout 获取超时时间
     * @param timeout        锁的超时时间
     * @return 锁标识
     */
    public String lockin(String key, long acquireTimeout, long timeout) {
        Jedis conn = null;
        String retIdentifier = null;
        try {
            conn = jedisPool.getResource();
            // 随机生成一个value
            String identifier = UUID.randomUUID().toString();
            // 锁名，即key值
            String lockKey = "lock:" + key;
            // 超时时间，上锁后超过此时间则自动释放锁
            int lockExpire = (int) (timeout / 1000);
            // 获取锁的超时时间，超过这个时间则放弃获取锁
            long end = System.currentTimeMillis() + acquireTimeout;
            while (System.currentTimeMillis() < end) {
                //setnx set if not exist ==1表示设置成功   0表示失败
                if (conn.setnx(lockKey, identifier) == 1) {
                    conn.expire(lockKey, lockExpire);
                    // 返回value值，用于释放锁时间确认
                    retIdentifier = identifier;
                    return retIdentifier;
                }
                //ttl time to live 返回给定 key 的剩余生存时间
                //当 key 不存在时，返回 -2 。 当 key 存在但没有设置剩余生存时间时，返回 -1 。
                // 否则，以毫秒为单位，返回 key 的剩余生存时间。 redis2.8之前都返回-1
                if (conn.ttl(lockKey) == -1) {
                    conn.expire(lockKey, lockExpire);
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (JedisException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return retIdentifier;
    }


    /**
     * 释放锁
     *
     * @param key        锁的key
     * @param identifier 释放锁的标识
     * @return
     */
    public boolean releaseLock(String key, String identifier) {
        Jedis conn = null;
        String lockKey = "lock:" + key;
        boolean retFlag = false;
        try {
            conn = jedisPool.getResource();
            while (true) {
                // 监视lock，准备开始事务
                conn.watch(lockKey);
                // 通过前面返回的value值判断是不是该锁，若是该锁，则删除，释放锁
                if (identifier.equals(conn.get(lockKey))) {
                    Transaction transaction = conn.multi();
                    transaction.del(lockKey);
                    List<Object> results = transaction.exec();
                    System.out.println(results.get(0));
                    if (results == null) {
                        continue;
                    }
                    retFlag = true;
                }
                conn.unwatch();
                break;
            }
        } catch (JedisException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return retFlag;
    }

}


