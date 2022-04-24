package com.letscode.transacaobanco811.jms.serviceProducer;

import com.letscode.transacaobanco811.dtos.jms.TransferenciaMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerTransferenciaMsgService {

    @Value("${kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, TransferenciaMsg> kafkaTemplate;

    public void send(TransferenciaMsg transferenciaMsg) {
        kafkaTemplate.send(topicName, transferenciaMsg);
    }
}
