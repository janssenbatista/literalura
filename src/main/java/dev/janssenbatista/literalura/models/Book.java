package dev.janssenbatista.literalura.models;

import dev.janssenbatista.literalura.controllers.dtos.BookDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_books")
public class Book {
    @Id
    int id;
    String title;
    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Author> authors;
    String language;
    @Column(name = "number_of_downloads")
    int numberOfDownloads;

    public Book() {
    }

    public Book(int id, String title, List<Author> authors, String language, int numberOfDownloads) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.language = language;
        this.numberOfDownloads = numberOfDownloads;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public String getLanguage() {
        return language;
    }

    public int getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public BookDTO toBookDTO() {
        return new BookDTO(
                this.getId(),
                this.getTitle(),
                this.getAuthors().stream().map(Author::toAuthorDTO).toList(),
                List.of(this.getLanguage()),
                this.getNumberOfDownloads());
    }
}
