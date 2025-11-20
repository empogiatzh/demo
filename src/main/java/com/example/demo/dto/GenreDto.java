package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {
    private Integer id;

    private String genre;

    public GenreDto(final String genre) {
        this.genre = genre;
    }
}
