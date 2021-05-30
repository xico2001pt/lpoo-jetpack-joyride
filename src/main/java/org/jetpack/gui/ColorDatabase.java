package org.jetpack.gui;

public enum ColorDatabase {
    WHITE("#FFFFFF"),
    GOLD("#F8C425"),
    GHOST_WHITE("#DBE6FA"),
    RED("#FF2B0A"),
    DARK_GRAY("#4C5B5C"),
    GRAY("#404F4F"),
    LIGHT_GRAY("#828c8d"),
    VIBRANT_GREEN("#7DB800");

    private final String name;

    ColorDatabase(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
