package org.jetpack.model;

public class CharColor {
    private final Character character;
    private final String color;

    public CharColor(Character character, String color) {
        this.character = character;
        this.color = color;
    }

    public Character getCharacter() {
        return character;
    }

    public String getColor() {
        return color;
    }
}
