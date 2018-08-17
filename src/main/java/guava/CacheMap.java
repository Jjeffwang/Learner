package guava;

import com.google.common.cache.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CacheMap {
    private static final Logger log = LoggerFactory.getLogger(CacheMap.class);

    /**
     * @desction: 使用google guava缓存处理
     * maximumSize定义了缓存的容量大小，当缓存数量即将到达容量上线时，则会进行缓存回收，回收最近没有使用或总体上很少使用的缓存项
     */
    private static Cache<String, Object> cache;

    static {
        cache = CacheBuilder.newBuilder().maximumSize(3)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .initialCapacity(10)
                .removalListener((RemovalListener<String, Object>) rn -> {
                    if (log.isInfoEnabled()) {
                        log.info("被移除缓存{}:{}", rn.getKey(), rn.getValue());
                    }
                }).build();
    }


    public static Object get(String key) {
        return StringUtils.isNotEmpty(key) ? cache.getIfPresent(key) : null;
    }


    /**
     * guava默认是对重复的key值做value的跟新
     * 这里在put前增加校验,以实现唯一锁的效果
     * @param key
     * @param value
     * @return
     */
    public static boolean put(String key, Object value) {
        if (StringUtils.isNotEmpty(key) && value != null) {
            while (cache.getIfPresent(key)==null){
                cache.put(key, value);
                return true;
            }
            return false;
        }
        return false;
    }

    public static void remove(String key) {
        if (StringUtils.isNotEmpty(key)) {
            cache.invalidate(key);
        }
    }


    public static void remove(List<String> keys) {
        if (keys != null && keys.size() > 0) {
            cache.invalidateAll(keys);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println(Integer.MAX_VALUE);
        Thread thread1 = new Thread(() -> CacheMap.put("key1", "value1"));
        Thread thread2 = new Thread(() -> CacheMap.put("key2", "value2"));
        Thread thread3 = new Thread(() -> CacheMap.put("key3", "value3"));
        Thread thread4 = new Thread(() -> CacheMap.put("key4", "value4"));
        thread1.run();
        thread2.run();
        thread3.run();
        thread4.run();
        System.out.println(cache.size());
    }
}


