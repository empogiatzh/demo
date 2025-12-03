package com.example.demo.controller;


import com.example.demo.dto.BookDto;
import com.example.demo.dto.MyBookDto;
import com.example.demo.mappers.BookMapper;
import com.example.demo.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** REST controller for endpoints related to books. */
@RestController
@RequestMapping(path = "/books")
@Slf4j
public class BookController {
    private final BookService bookService;

    public BookController(final BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
    }


//    @GetMapping
//    public ResponseEntity<Page<BookDto>> getBooks(
//            @RequestParam(required = false) String title,
//            @RequestParam(required = false) String genre,
//            @RequestParam(required = false) String author,
//            @RequestParam(required = false) String description,
//            @RequestParam(required = false) Double minPrice,
//            @RequestParam(required = false) Double maxPrice,
//            @PageableDefault(size = 20, sort = "title") Pageable pageable) {
//
//        Page<BookDto> books = bookService.getBooks(title, genre, author, description, minPrice, maxPrice, pageable);
//        return ResponseEntity.ok(books);
//    }

    @Operation(summary = "Get all books")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "This is the whole list of books",
                    content = @Content(schema = @Schema(implementation = BookDto.class))
            )
    })

    @GetMapping("/list")
    public ResponseEntity<List<BookDto>> getBooksAsList(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        var booksPage = bookService.getAllBooks();
        return ResponseEntity.ok(booksPage);
    }

    @GetMapping("/me/list")
    public ResponseEntity<List<MyBookDto>> getMyBooksAsList(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        log.info("Username: {}", username);
        var booksPage = bookService.getAllMyBooks();
        return ResponseEntity.ok(booksPage);
    }


}
