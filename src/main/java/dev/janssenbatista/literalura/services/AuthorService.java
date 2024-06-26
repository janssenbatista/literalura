package dev.janssenbatista.literalura.services;

import dev.janssenbatista.literalura.controllers.dtos.AuthorDTO;
import dev.janssenbatista.literalura.models.Author;
import dev.janssenbatista.literalura.repositories.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Page<AuthorDTO> getAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable).map(Author::toAuthorDTO);
    }

    public Page<AuthorDTO> getAllLivingAuthorsInTheYear(int year, Pageable pageable) {
        return authorRepository.getAllLivingInYear(year, pageable).map(Author::toAuthorDTO);
    }

}
