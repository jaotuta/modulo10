package com.letscode.transacaobanco811.dtos.jms;

import lombok.Data;

@Data
public class TransferenciaMsg {
    private Integer numContaMandante;
    private Integer numContaDestinatario;
    private Float valorTransacao;
}
