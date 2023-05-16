package org.evolboot.security.api.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.security.api.repository.EvolSessionRepository;
import org.evolboot.security.api.repository.redis.EvolSessionRedisTemplate;
import org.evolboot.security.api.repository.redis.RedisEvolSessionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author evol
 */

@Configuration
@Slf4j
public class SecurityApiAutoConfiguration {


    @Bean
    public EvolSessionRepository redisFsSessionRepository(RedisConnectionFactory connectionFactory, StringRedisTemplate stringRedisTemplate) {
        EvolSessionRedisTemplate evolSessionRedisTemplate = new EvolSessionRedisTemplate(connectionFactory);
        return new RedisEvolSessionRepository(evolSessionRedisTemplate, stringRedisTemplate, 604800000L);
    }

}
