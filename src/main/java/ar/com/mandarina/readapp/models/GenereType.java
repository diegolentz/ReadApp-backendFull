package ar.com.mandarina.readapp.models;

public enum GenereType {
    FICCION("FICCIÓN"),
    NO_FICCION("NO FICCIÓN"),
    MISTERIO("MISTERIO"),
    FANTASIA("FANTASIA"),
    CIENCIA_FICCION("CIENCIA FICCIÓN"),
    BIOGRAFIA("BIOGRAFÍA"),
    HISTORIA("HISTORIA"),
    ROMANCE("ROMANCE"),
    TERROR("TERROR"),
    AVENTURA("AVENTURA"),
    AUTOAYUDA("AUTOAYUDA"),
    SALUD("SALUD"),
    VIAJES("VIAJES"),
    RELIGION("RELIGIÓN"),
    COCINA("COCINA");

    private final String displayName;

    GenereType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
