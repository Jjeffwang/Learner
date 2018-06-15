package cache;

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
        cache = CacheBuilder.newBuilder().maximumSize(10000)
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .initialCapacity(10)
                .removalListener(new RemovalListener<String, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, Object> rn) {
                        if (log.isInfoEnabled()) {
                            log.info("被移除缓存{}:{}", rn.getKey(), rn.getValue());
                        }
                    }
                }).build();
    }


    public static Object get(String key) {
        return StringUtils.isNotEmpty(key) ? cache.getIfPresent(key) : null;
    }


    public static void put(String key, Object value) {
        if (StringUtils.isNotEmpty(key) && value != null) {
            cache.put(key, value);
        }
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
        CacheMap.put("key1", "value1");
        System.out.println(CacheMap.get("key1"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println("- - - - - - - - - - - -  - - -  -");
        System.out.println(CacheMap.get("key1"));
    }
}


