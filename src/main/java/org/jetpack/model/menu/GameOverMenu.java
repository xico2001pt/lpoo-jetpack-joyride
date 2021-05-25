package org.jetpack.model.menu;

import java.util.Arrays;

public class GameOverMenu extends Menu {
    long score;

    public GameOverMenu(long score) {
        super(Arrays.asList("PLAY AGAIN", "EXIT"));
        this.score = score;
    }

    public boolean isSelectedStart() {
        return isSelected(0);
    }

    public boolean isSelectedExit() {
        return isSelected(1);
    }

    public long getScore() {
        return score;
    }
}
