package com.otabek.library.service;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.MemberDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    ApiResponse<MemberDto> createMember(MemberDto dto);
    ApiResponse<MemberDto> getMemberById(Integer id);
    ApiResponse<MemberDto> updateMemberById(Integer id, MemberDto dto);
    ApiResponse<MemberDto> deleteMemberById(Integer id);
}
