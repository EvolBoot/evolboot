package org.evolboot.mq.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.TopicPartition;
import org.evolboot.core.exception.ExtendRuntimeException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.util.backoff.FixedBackOff;

import javax.transaction.TransactionManager;

/**
 * @author evol
 */
@Configuration
@Slf4j
public class KafkaMQMessageConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            ConsumerFactory<String, String> kafkaConsumerFactory,
            KafkaTemplate<String, String> template
    ) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerFactory);

        DeadLetterPublishingRecoverer deadLetterPublishingRecoverer =
                new DeadLetterPublishingRecoverer(template, (cr, e) -> new TopicPartition("your-dlq-topic", cr.partition()));

        SeekToCurrentErrorHandler errorHandler = new SeekToCurrentErrorHandler(deadLetterPublishingRecoverer, new FixedBackOff(3000L, 2)); // Retry twice, then go to DLT

        factory.setErrorHandler(errorHandler);

        return factory;
    }
}
