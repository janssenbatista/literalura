package dev.janssenbatista.literalura.models;

public enum Language {
    SPANISH("es"),
    ENGLISH("en"),
    FRENCH("ft"),
    PORTUGUESE("pt");

    final String lang;

    Language(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }
}
