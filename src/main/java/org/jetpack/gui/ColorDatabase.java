package org.jetpack.gui;

public enum ColorDatabase {
    WHITE("#ffffff"),
    BLACK("#000000"),
    RED("#ff0000"),
    GOLD("#ffd700"),
    DARK_GREY("#303030");

    private final String name;

    ColorDatabase(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
