package com.letscode.transacaobanco811.service;

import com.letscode.transacaobanco811.dtos.TransacaoRequest;
import com.letscode.transacaobanco811.dtos.jms.TransferenciaMsg;
import com.letscode.transacaobanco811.jms.serviceProducer.ProducerTransferenciaMsgService;
import com.letscode.transacaobanco811.models.Transacao;
import com.letscode.transacaobanco811.models.TransacaoValidada;
import com.letscode.transacaobanco811.models.ValidacaoTransacao;
import com.letscode.transacaobanco811.repository.TransacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ProducerTransferenciaMsgService producerTransferenciaMsgService;

    public ResponseEntity<?> save(TransacaoRequest transacaoRequest) {
        Transacao transacao = new Transacao();
        transacao.setValorTransacao(Float.parseFloat(transacaoRequest.getValorTransacao()));
        transacao.setNumContaMandante(Integer.parseInt(transacaoRequest.getNumContaMandante()));
        transacao.setNumContaDestinatario(Integer.parseInt(transacaoRequest.getNumContaDestinatario()));

        if(validaTransacao(transacaoRequest)){
            TransferenciaMsg transferenciaMsg = new TransferenciaMsg();
            transferenciaMsg.setNumContaMandante(Integer.parseInt(transacaoRequest.getNumContaMandante()));
            transferenciaMsg.setNumContaDestinatario(Integer.parseInt(transacaoRequest.getNumContaDestinatario()));
            transferenciaMsg.setValorTransacao(Float.parseFloat(transacaoRequest.getValorTransacao()));

            //TODO Criar msg kafka
            producerTransferenciaMsgService.send(transferenciaMsg);
            transacao.setValidacaoTransacao(ValidacaoTransacao.AUTORIZADA);
            return ResponseEntity.status(HttpStatus.CREATED).body(transacaoRepository.save(transacao));
        }else {
            transacao.setValidacaoTransacao(ValidacaoTransacao.NAOAUTORIZADA);
            transacaoRepository.save(transacao);
            return ResponseEntity.status(HttpStatus.OK).body("Transação não Autorizada: Saldo Insuficiente - " + transacao);
        }

    }

    private boolean validaTransacao(TransacaoRequest transacaoRequest){

        TransacaoValidada transacaoValidada = new TransacaoValidada();

        String numConta = transacaoRequest.getNumContaMandante();
        String valorTransferencia = transacaoRequest.getValorTransacao();



        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> validaTransacao =  restTemplate.exchange("http://localhost:8080/conta/validasaldo?numConta={numConta}&valorTransferencia={valorTransferencia}", HttpMethod.GET, entity, String.class, numConta, valorTransferencia);
        if(Objects.equals(validaTransacao.getBody(), "true")){
            return true;
        }
        return false;
    }

    public List<Transacao> getAllTransacoes() {
        return transacaoRepository.findAll();
    }
}
