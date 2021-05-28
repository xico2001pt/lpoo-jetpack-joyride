package org.jetpack.states;

import org.jetpack.controller.menu.InstructionsMenuController;
import org.jetpack.model.menu.InstructionsMenu;
import org.jetpack.viewer.menu.InstructionsMenuViewer;

public class InstructionsMenuState extends State<InstructionsMenu> {
    public InstructionsMenuState(InstructionsMenu menu) {
        super(menu, new InstructionsMenuController(menu), new InstructionsMenuViewer(menu));
    }
}
