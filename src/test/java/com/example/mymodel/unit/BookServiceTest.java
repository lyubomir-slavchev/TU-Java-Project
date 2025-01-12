package com.example.mymodel.unit;

import com.example.mymodel.model.Book;
import com.example.mymodel.repository.BookRepository;
import com.example.mymodel.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookServiceTest {
    @Test
    void testFindBooksByTitle() {
        BookRepository mockRepository = Mockito.mock(BookRepository.class);
        BookService bookService = new BookService(mockRepository);

        Mockito.when(mockRepository.findByTitleContains("Java")).thenReturn(List.of(new Book()));

        List<Book> books = bookService.findBooksByTitle("Java");
        assertEquals(1, books.size());
    }
}
