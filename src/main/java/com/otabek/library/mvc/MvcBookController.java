package com.otabek.library.mvc;

import com.otabek.library.config.UserPrincipal;
import com.otabek.library.dto.ApiResponse;
import com.otabek.library.dto.BookDto;
import com.otabek.library.model.Book;
import com.otabek.library.model.Role;
import com.otabek.library.service.BookService;
import com.otabek.library.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("books")
@RequiredArgsConstructor
public class MvcBookController {

    private final BookService bookService;
    private final TransactionService transactionService;

    @GetMapping("/create")
    public String getBookDto() {
        return "create-book";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute @Valid BookDto book, BindingResult bindingResult) {
        bookService.createBook(book);
        return "redirect:/books/get-all";
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/get-all")
    public String getAllBooks(Model model) {
        ApiResponse<List<BookDto>> allBooks = bookService.getAllBooks();
        List<BookDto> books = allBooks.getContent();
        model.addAttribute("books", books);
        return "get-all-books";
    }

    @GetMapping("/update/{id}")
    public String editBook(@PathVariable("id") Integer bookId, Model model) {
        ApiResponse<BookDto> book = bookService.getBookById(bookId);
        BookDto content = book.getContent();
        model.addAttribute("book", content);
        return "update-book";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Integer bookId, @ModelAttribute BookDto dto) {
        bookService.updateBookById(bookId, dto);
        return "redirect:/books/get-all";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer bookId){
        bookService.deleteBookById(bookId);
        return "redirect:/books/get-all";
    }

    @GetMapping("/book/{id}")
    public String viewBook(@PathVariable("id") Integer bookId, Model model){
        ApiResponse<BookDto> book = bookService.getBookById(bookId);
        BookDto content = book.getContent();
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean isBorrowed = transactionService.isBookBorrowedByMember(bookId, principal.getMember().getId());

        List<Role> whiteList = new ArrayList<>();
        whiteList.add(Role.ADMIN);
        whiteList.add(Role.STAFF);
        System.out.println(whiteList.contains(principal.getMember().getRole()));
        model.addAttribute("whiteList", whiteList);
        model.addAttribute("member", principal.getMember());
        model.addAttribute("isBorrowed", isBorrowed);
        model.addAttribute("book", content);
        return "view-book";
    }

    @GetMapping("/borrow/{bookId}/{memberId}")
    public String borrowBook(@PathVariable("bookId") Integer bookId, @PathVariable("memberId") Integer memberId){
        transactionService.createTransaction(memberId, bookId);
        return "redirect:/books/get-all";
    }

    @GetMapping("/return/{bookId}/{memberId}")
    public String returnBook(@PathVariable("bookId") Integer bookId, @PathVariable("memberId") Integer memberId){
        transactionService.completeTransaction(memberId, bookId);
        return "redirect:/books/get-all";
    }

}