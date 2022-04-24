package com.letscode.transacaobanco811.jms.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letscode.transacaobanco811.dtos.jms.TransferenciaMsg;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Objects;

public class TransferenciaSerializer implements Serializer<TransferenciaMsg> {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public byte[] serialize(String s, TransferenciaMsg transferenciaMsg) {
        try {
            if(Objects.nonNull(transferenciaMsg)){

                    return mapper.writeValueAsBytes(transferenciaMsg);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
