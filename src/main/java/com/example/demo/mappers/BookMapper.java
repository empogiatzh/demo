package com.example.demo.mappers;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.MyBookDto;
import com.example.demo.model.entity.Book;
import org.mapstruct.Mapper;

/** Mapper class for converting Book entities and DTOs. */
@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(Book book);
    MyBookDto tomyDto(Book book);
    Book toDoc(BookDto dto);

}
