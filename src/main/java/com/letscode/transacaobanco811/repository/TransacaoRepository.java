package com.letscode.transacaobanco811.repository;

import com.letscode.transacaobanco811.models.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
}
