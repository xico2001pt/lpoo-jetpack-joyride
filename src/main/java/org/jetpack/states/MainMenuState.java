package org.jetpack.states;

import org.jetpack.controller.menu.MainMenuController;
import org.jetpack.model.menu.MainMenu;
import org.jetpack.viewer.menu.MainMenuViewer;

public class MainMenuState extends State<MainMenu> {
    public MainMenuState(MainMenu menu) {
        super(menu,new MainMenuController(menu), new MainMenuViewer(menu));
    }
}
