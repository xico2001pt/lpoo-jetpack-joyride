package org.jetpack.model.menu;

import java.util.Arrays;

public class GameOverMenu extends Menu {
    public GameOverMenu() {
        super(Arrays.asList("Play Again", "Exit"));
    }

    public boolean isSelectedStart() {
        return isSelected(0);
    }

    public boolean isSelectedExit() {
        return isSelected(1);
    }
}
