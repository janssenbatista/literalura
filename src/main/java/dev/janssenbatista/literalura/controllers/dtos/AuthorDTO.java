package dev.janssenbatista.literalura.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.janssenbatista.literalura.models.Author;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorDTO(
        String name,
        @JsonAlias("birth_year")
        int birthYear,
        @JsonAlias("death_year")
        int deathYear){

        public static Author toAuthor(AuthorDTO dto) {
                return new Author(dto.name(), dto.birthYear(), dto.deathYear());
        }
}
