package com.system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis configuration class
 */
@Configuration
public class RedisConfig {
    // acquire the configuration value from the configuration file(application.yaml)
    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${redis.maxIdle}")
    private int maxIdle;

    @Value("${redis.maxTotal}")
    private int maxTotal;

    @Value("${redis.maxWait}")
    private long maxWaitMillis;

    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;

    /**
     * Connection pool configuration
     * @return configuration jedis connection pool
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        poolConfig.setTestOnBorrow(testOnBorrow);
        return poolConfig;
    }

    /**
     * Connection factory configuration
     * @return configuration jedis connection factory
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig poolConfig) {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisHost);
        factory.setPort(redisPort);
        factory.setPoolConfig(poolConfig);
        return factory;
    }

    /**
     * Redis template configuration
     * @return configuration redis template
     */
    @Bean
    public RedisTemplate<String, Integer> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Integer> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisSerializer<Integer>() {
            @Override
            public byte[] serialize(Integer value) throws SerializationException {
                return value == null ? null : value.toString().getBytes();
            }

            @Override
            public Integer deserialize(byte[] bytes) throws SerializationException {
                return bytes == null ? null : Integer.parseInt(new String(bytes));
            }
        });
        return template;
    }
}
