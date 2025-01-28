package com.otabek.library.service;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.TransactionDto;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    ApiResponse<TransactionDto> createTransaction(Integer memberId, Integer bookId);
    ApiResponse<TransactionDto> completeTransaction(Integer memberId, Integer bookId);
}
