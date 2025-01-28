package com.otabek.library.service.mapper;

import com.otabek.library.dto.CategoryDto;
import com.otabek.library.model.Category;
import com.otabek.library.repository.BookRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
    @Autowired
    public BookRepository bookRepository;
    @Autowired
    public BookMapper bookMapper;

    @Mapping(target = "books", ignore = true)
    public abstract Category toEntity(CategoryDto categoryDto);

    @Mapping(target = "books", ignore = true)
    public abstract CategoryDto toDto(Category category);

    @Mapping(target = "books", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Category updateAllFields(@MappingTarget Category category, CategoryDto dto);

    @Mapping(target = "books", expression = "java(bookMapper.toDtoList(bookRepository.findAllByCategory_Name(category.getName())))")
    public abstract CategoryDto toDtoWithAllEntity(Category category);

}
