package com.otabek.library.service.mapper;

import com.otabek.library.dto.TransactionDto;
import com.otabek.library.model.Transaction;
import com.otabek.library.repository.BookRepository;
import com.otabek.library.repository.MemberRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TransactionMapper {

    @Autowired
    public BookRepository bookRepository;
    @Autowired
    public MemberRepository memberRepository;

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "member", expression = "java(memberRepository.findById(dto.getMemberId()).orElse(null))")
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "book", expression = "java(bookRepository.findById(dto.getBookId()).orElse(null))")
    public abstract Transaction toEntity(TransactionDto dto);

    @Mapping(target = "memberId", expression = "java(transaction.getMember().getId())")
    @Mapping(target = "bookId", expression = "java(transaction.getBook().getId())")
    public abstract TransactionDto toDto(Transaction transaction);

}
