package com.otabek.library.repository;

import com.otabek.library.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Optional<Transaction> findTransactionByBook_IdAndMember_IdAndReturnDateIsNull(Integer bookId, Integer memberId);
}
