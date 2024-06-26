package dev.janssenbatista.literalura.repositories;

import dev.janssenbatista.literalura.models.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, String> {

    @Query("from Author WHERE :year < deathYear")
    Page<Author> getAllLivingInYear(int year, Pageable pageable);

}
