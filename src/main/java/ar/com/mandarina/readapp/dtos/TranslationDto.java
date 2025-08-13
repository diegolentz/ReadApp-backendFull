package ar.com.mandarina.readapp.dtos;

public class TranslationDto {

    private Long id;
    private String language;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public TranslationDto(Long id, String language) {
        this.id = id;
        this.language = language;
    }

}
