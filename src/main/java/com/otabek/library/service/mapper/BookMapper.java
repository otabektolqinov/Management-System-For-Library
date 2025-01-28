package com.otabek.library.service.mapper;

import com.otabek.library.dto.BookDto;
import com.otabek.library.model.Book;
import com.otabek.library.repository.CategoryRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BookMapper {

    @Autowired
    public CategoryRepository categoryRepository;

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "category", expression = "java(categoryRepository.findByName(dto.getCategory()))")
    public abstract Book toEntity(BookDto dto);

    @Named("toDto")
    @Mapping(target = "category", expression = "java(book.getCategory().getName())")
    public abstract BookDto toDto(Book book);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "category", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Book updateAllFields(@MappingTarget Book book, BookDto dto);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<BookDto> toDtoList(List<Book> bookList);
}
