package com.otabek.library.apicontroller;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.AuthUserDto;
import com.otabek.library.dto.MemberDto;
import com.otabek.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ApiResponse<MemberDto> createMember(@RequestBody MemberDto dto){
        return memberService.createMember(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthUserDto dto){
        return memberService.verify(dto);
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

    @GetMapping("/get-all")
    public ApiResponse<List<MemberDto>> getAllMembers(){
        return memberService.getAllMembers();
    }
}
