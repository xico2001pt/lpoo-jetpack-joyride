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
    VERY_DARK_GREY("#0D0106"),

    DAVYS_GREY("#4C4C4C"),
    LIGHT_GREY("#D1D1D1"),
    DIM_GREY("#636363"),
    SKY_BLUE("#85C7F2"),

    OSBTACLES("#DBE6FA"),
    PLAYER("#FF2B0A"),
    BACK("#4C5B5C"),
    INFO("#404F4F"),
    COINS("#F8C425"),
    INFOTEXT("#FFFFFF"),

    VIBRANT_GREEN("#7DB800");

    private final String name;

    ColorDatabase(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
