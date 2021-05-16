package org.jetpack.gui;

public enum ColorDatabase {
    WHITE("#FFFFFF"),
    BLACK("#000000"),
    RED("#FF0000"),
    GOLD("#FFD700"),
    DARK_GREY("#303030"),

    SILVER("#CDCDCD"),

    DARK_GOLD("#D4AF37"),
    GHOST_WHITE("#FBFBFF"),
    SHINY_RED("#FF331F"),
    PALE_BLUE("#657ED4"),
    SATURATED_BLUE("#3626A7"),
    VERY_DARK_GREY("#0D0106");

    private final String name;

    ColorDatabase(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
