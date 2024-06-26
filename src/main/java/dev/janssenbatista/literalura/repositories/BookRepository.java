package dev.janssenbatista.literalura.repositories;

import dev.janssenbatista.literalura.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    Page<Book> findBookByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Book> findAllByLanguage(String language, Pageable pageable);

    @Query("from Book b ORDER BY b.numberOfDownloads DESC LIMIT 10")
    List<Book> getTop10Books();
}
