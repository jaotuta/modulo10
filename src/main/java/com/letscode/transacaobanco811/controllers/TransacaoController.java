package com.letscode.transacaobanco811.controllers;

import com.letscode.transacaobanco811.dtos.TransacaoRequest;
import com.letscode.transacaobanco811.models.Transacao;
import com.letscode.transacaobanco811.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<?> transacao (@RequestBody TransacaoRequest transacaoRequest) {

        return transacaoService.save(transacaoRequest);
    }

    @GetMapping("/all")
    public List<Transacao> getAllTransacoes() {
        return transacaoService.getAllTransacoes();
    }
}
