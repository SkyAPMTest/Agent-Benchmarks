package org.skywalking.apm.benchmark.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Configuration
@Component
public class JedisConfiguration {

    @Bean
    public Jedis getJedis() {
        return new Jedis("127.0.0.1", 6379);
    }
}
