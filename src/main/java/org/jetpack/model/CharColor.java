package org.jetpack.model;

import org.jetpack.gui.ColorDatabase;

public class CharColor {
    private Character character;
    private String color;

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
