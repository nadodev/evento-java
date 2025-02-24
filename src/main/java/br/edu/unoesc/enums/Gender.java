package br.edu.unoesc.enums;

public enum Gender {
    MALE("Male"),
    FEMALE("Feminine"),
    OTHER("Others");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}