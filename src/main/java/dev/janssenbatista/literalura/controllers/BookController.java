package dev.janssenbatista.literalura.controllers;

import dev.janssenbatista.literalura.controllers.dtos.BookDTO;
import dev.janssenbatista.literalura.models.Language;
import dev.janssenbatista.literalura.services.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
@Tag(name = "Book Controller")
public class BookController {

    private final BooksService service;

    public BookController(BooksService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "get all books saved in local database")
    ResponseEntity<Page<BookDTO>> getAllBooks(@RequestParam(defaultValue = "0") int pageNumber,
                                              @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
        return ResponseEntity.ok(service.getAllBooks(pageable));
    }

    @GetMapping("/{title}")
    @Operation(summary = "search all books in gutendex API and store in local database")
    ResponseEntity<Page<BookDTO>> getAllBooksByTitle(@PathVariable String title,
                                                     @RequestParam(defaultValue = "0") int pageNumber,
                                                     @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
        return ResponseEntity.ok(service.getBookByTitle(title, pageable));
    }

    @GetMapping("/languages/{language}")
    @Operation(summary = "get all books saved in local database by language")
    ResponseEntity<Page<BookDTO>> getAllBooksByLanguage(@PathVariable Language language,
                                                        @RequestParam(defaultValue = "0") int pageNumber,
                                                        @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("title"));
        return ResponseEntity.ok(service.getAllBooksByLanguage(language, pageable));
    }

    @GetMapping("top10")
    @Operation(summary = "get top 10 books by your number of download that are stored in local database")
    ResponseEntity<List<BookDTO>> getTop10Books() {
        return ResponseEntity.ok(service.getTop10Books());
    }


}
