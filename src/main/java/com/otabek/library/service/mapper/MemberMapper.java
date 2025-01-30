package com.otabek.library.service.mapper;

import com.otabek.library.dto.MemberDto;
import com.otabek.library.model.Member;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MemberMapper {

    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract Member toEntity(MemberDto dto);

    @Named("toDto")
    public abstract MemberDto toDto(Member member);

    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Member updateAllFields(@MappingTarget Member member, MemberDto dto);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<MemberDto> toDtoList(List<Member> all);
}
