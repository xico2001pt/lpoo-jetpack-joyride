package org.jetpack.states;

import org.jetpack.controller.menu.GameOverMenuController;
import org.jetpack.controller.menu.MainMenuController;
import org.jetpack.model.menu.GameOverMenu;
import org.jetpack.model.menu.MainMenu;
import org.jetpack.viewer.menu.GameOverMenuViewer;
import org.jetpack.viewer.menu.MainMenuViewer;

public class GameOverMenuState extends State<GameOverMenu> {
    public GameOverMenuState(GameOverMenu menu) {
        super(menu,new GameOverMenuController(menu), new GameOverMenuViewer(menu));
    }
}
