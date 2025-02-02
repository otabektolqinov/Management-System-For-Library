package com.otabek.library.mvc;

import com.otabek.library.repository.BookRepository;
import com.otabek.library.repository.CategoryRepository;
import com.otabek.library.repository.LibrarianRepository;
import com.otabek.library.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("admin")
public class AdminController {

    @GetMapping
    public String allModels(Model model){
        HashMap<String, String> models = new HashMap<>();
        models.put("book", "books/get-all");
        models.put("category", "category/get-all");
        models.put("librarian", "librarian/get-all");
        models.put("member", "members/get-all");
        model.addAttribute("models", models);
        return "admin-panel";
    }

    @GetMapping("/hello")
    public String hello(HttpSession session, Model model, HttpServletRequest httpServletRequest){
        model.addAttribute("sessionID", session.getId());
        System.out.println(session.getAttribute("_csrf"));
        System.out.println(httpServletRequest.getSession().getAttribute("_csrf"));
        return "test";
    }

}
