package com.letscode.transacaobanco811.dtos;

import lombok.Data;

@Data
public class TransacaoRequest {
    private String numContaMandante;
    private String numContaDestinatario;
    private String valorTransacao;
}
