package org.jetpack.model.menu;

import java.util.Arrays;

public class MainMenu extends Menu {
    public MainMenu() {
        super(Arrays.asList("Start", "Exit"));
    }

    public boolean isSelectedStart() {
        return isSelected(0);
    }

    public boolean isSelectedExit() {
        return isSelected(1);
    }
}
