package dev.janssenbatista.literalura.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.janssenbatista.literalura.controllers.dtos.ApiResponseDTO;
import dev.janssenbatista.literalura.controllers.dtos.BookDTO;
import dev.janssenbatista.literalura.controllers.dtos.ErrorMessageDTO;
import dev.janssenbatista.literalura.models.Author;
import dev.janssenbatista.literalura.models.Book;
import dev.janssenbatista.literalura.models.Language;
import dev.janssenbatista.literalura.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksService {

    private final HttpClient httpClient;
    private final ObjectMapper mapper = new ObjectMapper();
    private final BookRepository bookRepository;

    public BooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        int FIVE_SECONDS_IN_MILLI = 5_000;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(FIVE_SECONDS_IN_MILLI))
                .build();
    }

    public Page<BookDTO> getBookByTitle(String title, Pageable pageable) {
        Page<Book> foundBooks = bookRepository
                .findBookByTitleContainingIgnoreCase(title.trim().replaceAll("\\s+", " "), pageable);
        if (!foundBooks.isEmpty()) {
            return foundBooks.map(Book::toBookDTO);
        }
        final String BASE_URL = "https://gutendex.com";
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(BASE_URL + "/books/?search=%s".formatted(title.trim().replaceAll("\\s+", "+"))))
                .build();
        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(res -> {
                    try {
                        if (res.statusCode() == 200) {
                            var apiResponse = mapper.readValue(res.body(), ApiResponseDTO.class);
                            if (apiResponse.results().isEmpty()) {
                                throw new RuntimeException("book not found");
                            }
                            return apiResponse;
                        } else {
                            ErrorMessageDTO errorMessageDTO = mapper.readValue(res.body(), ErrorMessageDTO.class);
                            throw new RuntimeException(errorMessageDTO.detail());
                        }
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                })
                .thenApply(ApiResponseDTO::results)
                .thenAccept(bookDTOS -> saveBooks(bookDTOS, title))
                .join();
        return bookRepository.findBookByTitleContainingIgnoreCase(title, pageable).map(Book::toBookDTO);
    }

    public Page<BookDTO> getAllBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(Book::toBookDTO);
    }

    public Page<BookDTO> getAllBooksByLanguage(Language language, Pageable pageable) {
        return bookRepository.findAllByLanguage(language.getLang(), pageable).map(Book::toBookDTO);
    }

    public List<BookDTO> getTop10Books() {
        return bookRepository.getTop10Books().stream().map(Book::toBookDTO).toList();
    }

    @Transactional
    private void saveBooks(List<BookDTO> books, String title) {
        List<Book> bookEntities = books.stream()
                .filter(bookDTO -> bookDTO.title().trim().equalsIgnoreCase(title))
                .map(BookDTO::toBook).collect(Collectors.toList());
        for (Book book : bookEntities) {
            for (Author author : book.getAuthors()) {
                if (author.getBooks() == null) {
                    author.setBooks(new ArrayList<>());
                }
                author.getBooks().add(book);
            }
        }
        bookRepository.saveAll(bookEntities);
    }

}
