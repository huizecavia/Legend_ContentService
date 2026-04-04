package io.matthijs.legend.ContentService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;


@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration();

        sentinelConfig.setMaster("mymaster");
        sentinelConfig.sentinel("127.0.0.1", 26379);
        return new JedisConnectionFactory(sentinelConfig);
    }
}

// podman run --name redis-standalone -p 6379:6379 docker.io/library/redis:7
// podman run --name redis-sentinel -p 26379:26379 -e REDIS_MASTER_HOST=192.168.1.107 docker.io/bitnami/redis-sentinel:latest


// podman pull bitnami/redis
// podman pull bitnami/redis-sentinel
// podman network create redis-network --driver bridge
// podman run --name redis-server -e ALLOW_EMPTY_PASSWORD=yes -p 6379:6379 --network redis-network bitnami/redis
// podman run --name redis-sentinel -it --rm -e REDIS_MASTER_HOST=127.0.0.1 -p 26379:26379 --network redis-network bitnami/redis-sentinel

// you may connect to sentinel at 127.0.0.1:26379