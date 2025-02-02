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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public ApiResponse<MemberDto> createMember(MemberDto dto) {
        try {
            dto.setPassword(encoder.encode(dto.getPassword()));
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
        try {
            Optional<Member> optional = memberRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Member with %d id is not found", id));
            }
            MemberDto dto = memberMapper.toDto(optional.get());
            return ApiResponse.<MemberDto>builder()
                    .message("ok")
                    .success(true)
                    .content(dto)
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public ApiResponse<MemberDto> updateMemberById(Integer id, MemberDto dto) {
        try {
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
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public ApiResponse<MemberDto> deleteMemberById(Integer id) {
        try {
            Optional<Member> optional = memberRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Member with %d id is not found", id));
            }
            memberRepository.deleteById(id);
            return ApiResponse.<MemberDto>builder()
                    .success(true)
                    .message("Successfully deleted")
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public ApiResponse<List<MemberDto>> getAllMembers() {
        try {
            List<Member> all = memberRepository.findAll();
            List<MemberDto> memberDtoList = memberMapper.toDtoList(all);
            return ApiResponse.<List<MemberDto>>builder()
                    .success(true)
                    .content(memberDtoList)
                    .message("ok")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
