package me.myblog.framework.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCache<V> {
    @Autowired
    private RedisTemplate<String, V> redisTemplate;

    public void setCacheObject(final String key, final V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setCacheObject(final String key, final V value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public boolean expire(final String key, final long timeout, final TimeUnit timeUnit) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, timeUnit));
    }

    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    public V getCacheObject(final String key) {
        ValueOperations<String, V> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    public boolean deleteCacheObject(final String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    public Long deleteCacheObjects(final Collection<String> collection) {
        return redisTemplate.delete(collection);
    }

    public long setCacheList(final String key, final List<V> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    public List<V> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    public BoundSetOperations<String, V> setCacheSet(final String key, final Set<V> dataSet) {
        BoundSetOperations<String, V> setOperation = redisTemplate.boundSetOps(key);
        dataSet.forEach(setOperation::add);
        return setOperation;
    }

    public Set<V> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public void setCacheMap(final String key, final Map<String, ?> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    public Map<Object, Object> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public void setCacheMapValue(final String key, final String hKey, final V value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    public Object getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    public void delCacheMapValue(final String key, final String hKey) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, hKey);
    }

    public List<Object> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }

    public void incrementCacheMapValue(String key, String hKey, int step) {
        redisTemplate.opsForHash().increment(key, hKey, step);
    }
}
