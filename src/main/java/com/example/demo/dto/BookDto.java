package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Integer id;
    private String title;
    private String author;
    private String description;
    private Integer pages;
    private Double price;
    private Integer availability;
    private String isbn;

    @Builder.Default  //need this for lombok. use field initializers
    //initialize genres to new ArrayList<>() instead of null
    private List<GenreDto> genres = new ArrayList<>();
}
