package dev.janssenbatista.literalura.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponseDTO(List<BookDTO> results) {
}
