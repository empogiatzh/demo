package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.validations.NullOrNotBlank;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookWriteDTO {
    @NotBlank(message = "title must not be blank")
    @Size(max = 61, message = "title must be less than 64 characters")
    private String title;

    @NotBlank(message = "author must not be blank")
    @Size(max = 31, message = "author must be less than 32 characters")
    private String author;

    private String description;

    @NotNull
    @Positive(message = "pages must be greater than 0")
    private Integer pages;

    @PositiveOrZero(message = "price must be greater than or equal to 0")
    private Double price;

    @PositiveOrZero(message = "availability must be greater than or equal to 0")
    private Integer availability;

    @NullOrNotBlank
    @Size(max = 31, message = "isbn must be less than 32 characters")
    private String isbn;

    @Builder.Default
    @Valid
    private List<GenreDto> genres = new ArrayList<>();
}
