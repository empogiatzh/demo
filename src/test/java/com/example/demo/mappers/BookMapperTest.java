package com.example.demo.mappers;

import com.example.demo.dto.BookDto;
import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Genre;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookMapperTest {

    private final BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    /**
     * Test the mapping of a fully populated Book entity to a BookDto object.
     */
    @Test
    void testToDto_withAllFieldsPopulated() {
        // Arrange
        Genre genre = Genre.builder()
                .id(1)
                .genre("Test Genre")
                .build();

        Book book = Book.builder()
                .id(1)
                .title("Test Title")
                .author("Test Author")
                .description("Test Description")
                .pages(200)
                .price(19.99)
                .availability(5)
                .isbn("123-456-789")
                .genres(Collections.singletonList(genre))
                .build();

        // Act
        BookDto bookDto = bookMapper.toDto(book);

        // Assert
        assertNotNull(bookDto);
        assertEquals(book.getTitle(), bookDto.getTitle());
        assertEquals(book.getAuthor(), bookDto.getAuthor());
        assertEquals(book.getDescription(), bookDto.getDescription());
        assertEquals(book.getPages(), bookDto.getPages());
        assertEquals(book.getPrice(), bookDto.getPrice());
        assertEquals(book.getAvailability(), bookDto.getAvailability());
        assertEquals(book.getIsbn(), bookDto.getIsbn());
        assertNotNull(bookDto.getGenres());
        assertEquals(1, bookDto.getGenres().size());
    }

    /**
     * Test the mapping when the Book entity has an empty list of genres.
     */
    @Test
    void testToDto_withEmptyGenres() {
        // Arrange
        Book book = Book.builder()
                .id(2)
                .title("Another Title")
                .author("Another Author")
                .description("Test Description")
                .pages(300)
                .price(9.99)
                .availability(10)
                .isbn("987-654-321")
                .genres(Collections.emptyList())
                .build();

        // Act
        BookDto bookDto = bookMapper.toDto(book);

        // Assert
        assertNotNull(bookDto);
        assertEquals(book.getTitle(), bookDto.getTitle());
        assertEquals(book.getAuthor(), bookDto.getAuthor());
        assertEquals(book.getDescription(), bookDto.getDescription());
        assertEquals(book.getPages(), bookDto.getPages());
        assertEquals(book.getPrice(), bookDto.getPrice());
        assertEquals(book.getAvailability(), bookDto.getAvailability());
        assertEquals(book.getIsbn(), bookDto.getIsbn());
        assertNotNull(bookDto.getGenres());
        assertEquals(0, bookDto.getGenres().size());
    }

    /**
     * Test the mapping of a Book entity to a BookDto object when optional fields are null.
     */
    @Test
    void testToDto_withNullOptionalFields() {
        // Arrange
        Book book = Book.builder()
                .id(3)
                .title("Null Fields Book")
                .author("Author")
                .pages(100)
                .price(4.99)
                .availability(2)
                .isbn("123-000-789")
                .build();

        // Act
        BookDto bookDto = bookMapper.toDto(book);

        // Assert
        assertNotNull(bookDto);
        assertEquals(book.getTitle(), bookDto.getTitle());
        assertEquals(book.getAuthor(), bookDto.getAuthor());
        assertEquals(null, bookDto.getDescription());
        assertEquals(book.getPages(), bookDto.getPages());
        assertEquals(book.getPrice(), bookDto.getPrice());
        assertEquals(book.getAvailability(), bookDto.getAvailability());
        assertEquals(book.getIsbn(), bookDto.getIsbn());
        assertNotNull(bookDto.getGenres());
        assertEquals(0, bookDto.getGenres().size());
    }
}