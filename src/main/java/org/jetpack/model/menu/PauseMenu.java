package org.jetpack.model.menu;

import java.util.Arrays;

public class PauseMenu extends Menu {

    public PauseMenu() {
        super(Arrays.asList("Resume", "Exit"));
    }

    public boolean isSelectedResume() {
        return isSelected(0);
    }

    public boolean isSelectedExit() {
        return isSelected(1);
    }
}
