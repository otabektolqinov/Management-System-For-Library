package com.otabek.library.apicontroller;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.TransactionDto;
import com.otabek.library.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/create/{memberId}/{bookId}")
    public ApiResponse<TransactionDto> createTransaction(@PathVariable("memberId") Integer memberId,
                                                         @PathVariable("bookId") Integer bookId){
        return transactionService.createTransaction(memberId, bookId);
    }

    @PostMapping("/complete/{memberId}/{bookId}")
    public ApiResponse<TransactionDto> completeTransactionById(@PathVariable("memberId") Integer memberId,
                                                               @PathVariable("bookId") Integer bookId){
        return transactionService.completeTransaction(memberId, bookId);
    }
}
