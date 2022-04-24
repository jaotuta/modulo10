package com.letscode.transacaobanco811.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "transacao")
@Data
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "numContaMandante")
    private Integer numContaMandante;
    @Column(name = "numContaDestinatario")
    private Integer numContaDestinatario;
    @Column(name = "valorTransacao")
    private Float valorTransacao;
    @Enumerated(EnumType.STRING)
    @Column(name = "validacaoTransacao")
    private ValidacaoTransacao validacaoTransacao;

}
