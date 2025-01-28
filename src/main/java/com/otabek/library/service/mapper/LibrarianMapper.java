package com.otabek.library.service.mapper;

import com.otabek.library.dto.LibrarianDto;
import com.otabek.library.model.Librarian;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class LibrarianMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Librarian toEntity(LibrarianDto dto);

    public abstract LibrarianDto toDto(Librarian librarian);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Librarian updateAllFields(@MappingTarget Librarian librarian, LibrarianDto dto);

}
