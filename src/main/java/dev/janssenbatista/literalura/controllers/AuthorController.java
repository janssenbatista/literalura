package dev.janssenbatista.literalura.controllers;

import dev.janssenbatista.literalura.controllers.dtos.AuthorDTO;
import dev.janssenbatista.literalura.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authors")
@Tag(name = "Author Controller")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @Operation(summary = "get all authors saved in local database")
    public ResponseEntity<Page<AuthorDTO>> getAllAuthors(@RequestParam(defaultValue = "0") int pageNumber,
                                                         @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        return ResponseEntity.ok(authorService.getAllAuthors(pageable));
    }

    @GetMapping("{aliveInYear}")
    @Operation(summary = "get all authors alive in a specific year and saved in local database")
    public ResponseEntity<Page<AuthorDTO>> getAllLivingAuthorsInTheYear(@PathVariable int aliveInYear,
                                                                        @RequestParam(defaultValue = "0") int pageNumber,
                                                                        @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        return ResponseEntity.ok(authorService.getAllLivingAuthorsInTheYear(aliveInYear, pageable));
    }
}
