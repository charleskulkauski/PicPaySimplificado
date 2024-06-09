package com.picpaysimplificado.repository;

import com.picpaysimplificado.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepoitory extends JpaRepository<Transaction, Long> {
    
}
