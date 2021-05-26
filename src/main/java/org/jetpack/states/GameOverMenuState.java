package org.jetpack.states;

import org.jetpack.controller.menu.GameOverMenuController;
import org.jetpack.model.menu.GameOverMenu;
import org.jetpack.viewer.menu.GameOverMenuViewer;

public class GameOverMenuState extends State<GameOverMenu> {
    public GameOverMenuState(GameOverMenu menu) {
        super(menu,new GameOverMenuController(menu), new GameOverMenuViewer(menu));
    }
}
