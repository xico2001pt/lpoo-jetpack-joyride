package org.jetpack.viewer.game;

import org.jetpack.gui.ColorDatabase;
import org.jetpack.gui.GUI;
import org.jetpack.model.Position;
import org.jetpack.model.elements.obstacles.Obstacle;

public class ObstacleViewer implements ElementViewer<Obstacle> {
    @Override
    public void drawElement(GUI gui, Obstacle element, Position offset) {
        gui.drawImage(element.getPosition().getIncrementedPosition(offset), element.getImage(), ColorDatabase.OSBTACLES.getName());
    }
}
