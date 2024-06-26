package dev.janssenbatista.literalura.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.janssenbatista.literalura.models.Book;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        int id,
        String title,
        @JsonAlias("authors")
        List<AuthorDTO> authors,
        List<String> languages,
        @JsonAlias("download_count")
        int downloadCount
) {
    public static Book toBook(BookDTO dto) {
        return new Book(dto.id(),
                dto.title,
                dto.authors.stream().map(AuthorDTO::toAuthor).toList(),
                dto.languages().get(0),
                dto.downloadCount);
    }
}
