package org.jetpack.controller.game;

import org.jetpack.model.CharColor;
import org.jetpack.model.Matrix;
import org.jetpack.model.Position;
import org.jetpack.model.elements.Element;
import org.jetpack.model.elements.obstacles.Laser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CollisionControllerTest {

    private Matrix<CharColor> image;

    @BeforeEach
    void setUp() {
        image = new Matrix<>(3, 5, null);

        image.setValue(0, 0, new CharColor('-', ""));
        image.setValue(1, 0, new CharColor('-', ""));
        image.setValue(2, 0, new CharColor('-', ""));
        image.setValue(0, 4, new CharColor('-', ""));
        image.setValue(1, 4, new CharColor('-', ""));
        image.setValue(2, 4, new CharColor('-', ""));

        image.setValue(1, 0, new CharColor('|', ""));
        image.setValue(1, 1, new CharColor('|', ""));
        image.setValue(1, 2, new CharColor('|', ""));
        image.setValue(1, 3, new CharColor('|', ""));
    }

    @Test
    void checkElementCollision() {
        Element element1 = new Laser(new Position(5, 5));
        Element element2 = new Laser(new Position(5, 5));

        assertTrue(CollisionController.checkElementCollision(element1, element2));
    }

    @Test
    void checkBoxCollision() {
        Element element1 = new Laser(new Position(5, 5)); element1.setImage(image);
        Element element2 = new Laser(new Position(3, 6)); element2.setImage(image);

        assertTrue(CollisionController.checkBoxCollision(element1.getPosition(),
                new Dimension(element1.getImage().getNumberCol(),
                element2.getImage().getNumberRows()), element2.getPosition(),
                new Dimension(element2.getImage().getNumberCol(),
                element2.getImage().getNumberRows())));
    }

    @Test
    void checkImageCollision() {
        Element element1 = new Laser(new Position(5, 5)); element1.setImage(image);
        Element element2 = new Laser(new Position(3, 6)); element2.setImage(image);

        assertFalse(CollisionController.checkImageCollision(element1, element2));
    }
}