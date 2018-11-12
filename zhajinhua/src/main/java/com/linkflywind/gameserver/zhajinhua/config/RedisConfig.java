package com.linkflywind.gameserver.zhajinhua.config;

import com.linkflywind.gameserver.zhajinhua.redisModel.ConnectorData;
import com.linkflywind.gameserver.zhajinhua.redisModel.UserSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {



    @Bean
    ReactiveRedisOperations<String, UserSession> redisOperationsByUserSession(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<UserSession> serializer = new Jackson2JsonRedisSerializer<>(UserSession.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, UserSession> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, UserSession> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }


    @Bean
    ReactiveRedisOperations<String, ConnectorData> redisOperationsByConnectorData(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<ConnectorData> serializer = new Jackson2JsonRedisSerializer<>(ConnectorData.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, ConnectorData> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
        RedisSerializationContext<String, ConnectorData> context = builder.value(serializer).build();
        return new ReactiveRedisTemplate<>(factory, context);
    }
}