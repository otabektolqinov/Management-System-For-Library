package com.otabek.library.mvc;

import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.MemberDto;
import com.otabek.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("members")
@RequiredArgsConstructor
public class MvcMemberController {

    private final MemberService memberService;


    @GetMapping("/create")
    public String createMember(){
        return "create-member";
    }

    @PostMapping("/save")
    public String saveMember(@ModelAttribute MemberDto dto){
        memberService.createMember(dto);
        return "redirect:/members/get-all";
    }

    @GetMapping("/member/update/{id}")
    public String viewMember(@PathVariable("id") Integer id, Model model){
        ApiResponse<MemberDto> memberById = memberService.getMemberById(id);
        MemberDto content = memberById.getContent();
        model.addAttribute("member", content);
        return "update-member";
    }

    @GetMapping("/get-all")
    public String getAllMembers(Model model){
        ApiResponse<List<MemberDto>> allMembers = memberService.getAllMembers();
        model.addAttribute("members", allMembers.getContent());
        return "get-all-members";
    }

    @PostMapping("/member/update/{id}")
    public String updateMember(@PathVariable("id") Integer id, @ModelAttribute MemberDto dto){
        memberService.updateMemberById(id, dto);
        return "redirect:/members/get-all";
    }

    @GetMapping("/member/delete/{id}")
    public String deleteMember(@PathVariable("id") Integer id){
        memberService.deleteMemberById(id);
        return "redirect:/members/get-all";
    }
}
