package dev.janssenbatista.literalura.models;

import dev.janssenbatista.literalura.controllers.dtos.AuthorDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_authors")
public class Author {
    @Id
    String name;
    @Column(name = "birth_year")
    int birthYear;
    @Column(name = "death_year")
    int deathYear;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "authors_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    List<Book> books;

    public Author() {
    }

    public Author(String name, int birthYear, int deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public static AuthorDTO toAuthorDTO(Author author) {
        return new AuthorDTO(author.getName(), author.getBirthYear(), author.getDeathYear());
    }

}
