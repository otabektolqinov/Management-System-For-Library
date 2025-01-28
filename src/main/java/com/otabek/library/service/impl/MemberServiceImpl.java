package com.otabek.library.service.impl;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.MemberDto;
import com.otabek.library.exceptions.ContentNotFoundException;
import com.otabek.library.exceptions.DatabaseException;
import com.otabek.library.model.Member;
import com.otabek.library.repository.MemberRepository;
import com.otabek.library.service.MemberService;
import com.otabek.library.service.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public ApiResponse<MemberDto> createMember(MemberDto dto) {
        try {
            Member member = memberMapper.toEntity(dto);
            Member saved = memberRepository.save(member);
            return ApiResponse.<MemberDto>builder()
                    .content(memberMapper.toDto(saved))
                    .success(true)
                    .message("ok")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public ApiResponse<MemberDto> getMemberById(Integer id) {
        Optional<Member> optional = memberRepository.findById(id);
        if (optional.isEmpty()){
            throw new ContentNotFoundException(String.format("Member with %d id is not found", id));
        }
        return ApiResponse.<MemberDto>builder()
                .message("ok")
                .success(true)
                .content(memberMapper.toDto(optional.get()))
                .build();
    }

    @Override
    public ApiResponse<MemberDto> updateMemberById(Integer id, MemberDto dto) {
        Optional<Member> optional = memberRepository.findById(id);
        if (optional.isEmpty()){
            throw new ContentNotFoundException(String.format("Member with %d id is not found", id));
        }
        Member member = memberMapper.updateAllFields(optional.get(), dto);
        Member saved = memberRepository.save(member);

        return ApiResponse.<MemberDto>builder()
                .message("Successfully updated member")
                .success(true)
                .content(memberMapper.toDto(saved))
                .build();
    }

    @Override
    public ApiResponse<MemberDto> deleteMemberById(Integer id) {
        Optional<Member> optional = memberRepository.findById(id);
        if (optional.isEmpty()){
            throw new ContentNotFoundException(String.format("Member with %d id is not found", id));
        }
        memberRepository.deleteById(id);
        return ApiResponse.<MemberDto>builder()
                .success(true)
                .message("Successfully deleted")
                .build();
    }
}
