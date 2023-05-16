package org.evolboot.mq.rocketmq.producer.autoconfigure;

import cn.hutool.core.util.TypeUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.exception.ExtendRuntimeException;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.mq.rocketmq.producer.RocketMQLocalTransactionCheck;
import org.evolboot.shared.event.mq.RocketMQMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author evol
 */
@Configuration
@Slf4j
@DependsOn
public class RocketMqProducerConfiguration {


    @Bean
    public Map<Class<RocketMQMessage>, RocketMQLocalTransactionCheck> classRocketMQLocalTransactionCheckMap(List<RocketMQLocalTransactionCheck> checks) {
        Map<Class<RocketMQMessage>, RocketMQLocalTransactionCheck> map = Maps.newHashMap();
        if (ExtendObjects.nonNull(checks)) {
            checks.forEach(listener -> {
                Map<Type, Type> typeMap = TypeUtil.getTypeMap(listener.getClass());
                Class<RocketMQMessage> aClass = (Class<RocketMQMessage>) TypeUtil.getClass(typeMap.values().stream().findFirst().get());
                if (map.containsKey(aClass)) {
                    log.error("存在重复的事务消息检查者:{}", listener.getClass());
                    throw new ExtendRuntimeException("存在重复的事务消息检查者:" + listener.getClass());
                }
                map.put(aClass, listener);
            });
        }
        return map;
    }

}
