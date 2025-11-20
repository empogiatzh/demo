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
public class BookReadDTO {
    private Integer id;
    private String title;
    private String author;
    private String description;
    private Integer pages;
    private Double price;
    private Integer availability;
    private String isbn;
    @Builder.Default
    private List<GenreDTO> genres = new ArrayList<>();
}
