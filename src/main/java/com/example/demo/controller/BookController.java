package com.example.demo.controller;


import com.example.demo.dto.BookDto;
import com.example.demo.mappers.BookMapper;
import com.example.demo.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** REST controller for endpoints related to books. */
@RestController
@RequestMapping(path = "/books")
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


}
