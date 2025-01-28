package com.otabek.library.service.impl;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.TransactionDto;
import com.otabek.library.exceptions.ContentNotFoundException;
import com.otabek.library.exceptions.DatabaseException;
import com.otabek.library.model.Book;
import com.otabek.library.model.Transaction;
import com.otabek.library.repository.BookRepository;
import com.otabek.library.repository.TransactionRepository;
import com.otabek.library.service.TransactionService;
import com.otabek.library.service.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final BookRepository bookRepository;

    @Override
    public ApiResponse<TransactionDto> createTransaction(Integer memberId, Integer bookId) {
        try {
            Transaction entity = new Transaction();
            changeBookCount(bookId, '-');
            entity.setIssueDate(LocalDate.now());
            entity.setIssueDate(entity.getIssueDate().plusDays(10));
            Transaction saved = transactionRepository.save(entity);

            return ApiResponse.<TransactionDto>builder()
                    .message("ok")
                    .content(transactionMapper.toDto(saved))
                    .success(true)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public ApiResponse<TransactionDto> completeTransaction(Integer memberId, Integer bookId) {
        Optional<Transaction> optional = transactionRepository.findTransactionByBook_IdAndMember_IdAndReturnDateIsNull(bookId, memberId);
        if (optional.isEmpty()){
            throw new ContentNotFoundException(String.format("Transaction with %d member id is not found", memberId));
        }
        Transaction transaction = optional.get();
        transaction.setReturnDate(LocalDate.now());

        if (transaction.getReturnDate().isAfter(transaction.getDueDate())){
            transaction.setFine(12000d);
        }
        changeBookCount(bookId, '+');

        return ApiResponse.<TransactionDto>builder()
                .success(true)
                .content(transactionMapper.toDto(transaction))
                .message("ok")
                .build();
    }

    private void changeBookCount(Integer bookId, char addOrrSubtract){
        Optional<Book> optionalBook = bookRepository.findByIdAndDeletedAtIsNull(bookId);
        if (optionalBook.isEmpty()){
            throw new ContentNotFoundException(String.format("Book with %d id is not found", bookId));
        }
        Book book = optionalBook.get();
        if (addOrrSubtract == '+'){
            book.setCount(book.getCount() + 1);
        } else {
            book.setCount(book.getCount() - 1);
        }

        bookRepository.save(book);
    }
}
