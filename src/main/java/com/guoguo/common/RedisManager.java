package com.guoguo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisManager {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 按天存储
     *
     * @param key
     * @param value
     * @param days
     * @return
     */
    public boolean setDays(final String key, Object value, long days) {
        try {
            redisTemplate.opsForValue().set(key, StaticObject.gson.toJson(value), days, TimeUnit.DAYS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 按小时存储
     *
     * @param key
     * @param value
     * @param hours
     * @return
     */
    public boolean setHours(final String key, Object value, long hours) {
        try {
            redisTemplate.opsForValue().set(key, StaticObject.gson.toJson(value), hours, TimeUnit.HOURS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 按分钟存储
     *
     * @param key
     * @param value
     * @param minutes
     * @return
     */
    public boolean setMinutes(final String key, Object value, long minutes) {
        try {
            redisTemplate.opsForValue().set(key, StaticObject.gson.toJson(value), minutes, TimeUnit.MINUTES);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 按秒存储
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public boolean setSeconds(final String key, Object value, long seconds) {
        try {
            redisTemplate.opsForValue().set(key, StaticObject.gson.toJson(value), seconds, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更新缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean getAndSet(final String key, String value) {
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
    public boolean delete(final String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
