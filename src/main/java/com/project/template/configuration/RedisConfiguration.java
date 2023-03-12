package com.project.template.configuration;

import com.project.template.properties.RedisProperties;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.resource.DefaultClientResources;
import lombok.RequiredArgsConstructor;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RedisConfiguration {

    private final RedisProperties redisProperties;

    @Bean
    public RedisConnectionFactory connectionFactory(RedisSentinelConfiguration redisSentinelConfiguration, LettucePoolingClientConfiguration lettucePoolingClientConfiguration){
        return new LettuceConnectionFactory(redisSentinelConfiguration,lettucePoolingClientConfiguration);
    }

    @Bean
    public RedisSentinelConfiguration redisSentinelConfiguration(){
        var redisSentinelConfiguration = new RedisSentinelConfiguration().master(redisProperties.getMaster());
        addSentinels(redisSentinelConfiguration);
        return redisSentinelConfiguration;
    }

    @Bean
    public LettucePoolingClientConfiguration lettucePoolingClientConfiguration(ClientOptions clientOptions){
        return LettucePoolingClientConfiguration.builder()
                .poolConfig(genericObjectPoolConfig())
                .clientOptions(clientOptions)
                .clientResources(DefaultClientResources.create())
                .build();
    }

    @Bean
    public ClientOptions clientOptions(){
        return ClientOptions.builder()
                .disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
                .autoReconnect(true)
                .build();
    }

    @Bean
    public GenericObjectPoolConfig genericObjectPoolConfig(){
        var genericObjectPoolConfig = new GenericObjectPoolConfig();
        final var pool = redisProperties.getPool();
        genericObjectPoolConfig.setMaxIdle(pool.getMaxIdleConnection());
        genericObjectPoolConfig.setMinIdle(pool.getMinIdleConnection());
        genericObjectPoolConfig.setMaxTotal(pool.getMaxTotalConnection());
        genericObjectPoolConfig.setTestOnBorrow(pool.isTestOnBorrow());
        genericObjectPoolConfig.setLifo(pool.isLifo());
        genericObjectPoolConfig.setBlockWhenExhausted(pool.isBlockWhenExhausted());
        genericObjectPoolConfig.setMaxWait(Duration.ofMillis(pool.getMaxWaitMillis()));
        return genericObjectPoolConfig;
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration(){
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(redisProperties.getCacheTtlSecond()))
                .disableCachingNullValues();
    }

    @Bean
    public RedisCacheManager defaultCacheManager(){
        return RedisCacheManager.builder(connectionFactory(redisSentinelConfiguration(),lettucePoolingClientConfiguration(clientOptions())))
                .cacheDefaults(cacheConfiguration())
                .transactionAware()
                .build();
    }

    private void addSentinels(RedisSentinelConfiguration redisSentinelConfiguration){
        redisProperties.getNodes().forEach(node->{
            final var split = node.split(":");
            final var host = split[0];
            final int port = Integer.parseInt(split[1]);
            redisSentinelConfiguration.addSentinel(RedisNode.newRedisNode()
                    .listeningAt(host,port)
                    .build());
        });
    }


}
