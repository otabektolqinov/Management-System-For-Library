package com.otabek.library.controller;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.MemberDto;
import com.otabek.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ApiResponse<MemberDto> createMember(@RequestBody MemberDto dto){
        return memberService.createMember(dto);
    }

    @GetMapping
    public ApiResponse<MemberDto> getMemberById(@RequestParam("id") Integer id){
        return memberService.getMemberById(id);
    }

    @PutMapping
    public ApiResponse<MemberDto> updateMemberById(@RequestParam("id") Integer id,
                                                   @RequestBody MemberDto dto){
        return memberService.updateMemberById(id, dto);
    }

    @DeleteMapping
    public ApiResponse<MemberDto> deleteMemberById(@RequestParam("id") Integer id){
        return memberService.deleteMemberById(id);
    }
}
