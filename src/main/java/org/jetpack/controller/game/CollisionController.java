package org.jetpack.controller.game;

import org.jetpack.model.Dimensions;
import org.jetpack.model.Matrix;
import org.jetpack.model.Position;
import org.jetpack.model.elements.Element;

import static java.lang.Math.max;
import static java.lang.Math.min;

public final class CollisionController {

    public static boolean checkElementCollision(Element a, Element b) {
        Dimensions dimensionsA = new Dimensions(a.getImage().getNumberCol(), a.getImage().getNumberRows());
        Dimensions dimensionsB = new Dimensions(b.getImage().getNumberCol(), b.getImage().getNumberRows());

        return checkBoxCollision(a.getPosition(), dimensionsA, b.getPosition(), dimensionsB) && checkImageCollision(a, b);
    }

    static boolean checkBoxCollision(Position pos1, Dimensions d1, Position pos2, Dimensions d2) {
        return  pos1.getX() < pos2.getX() + d2.getWidth()  &&
                pos1.getX() + d1.getWidth() > pos2.getX()  &&
                pos1.getY() < pos2.getY() + d2.getHeight() &&
                pos1.getY() + d1.getHeight() > pos2.getY();
    }

    static boolean checkImageCollision(Element a, Element b) {
        Matrix<Character> imageA = a.getImage();
        Matrix<Character> imageB = b.getImage();

        int xMin = max(a.getPosition().getX(), b.getPosition().getX());
        int xMax = min(a.getPosition().getX() + imageA.getNumberCol(), b.getPosition().getX() + imageB.getNumberCol());
        int yMin = max(a.getPosition().getY(), b.getPosition().getY());
        int yMax = min(a.getPosition().getY() + imageA.getNumberRows(), b.getPosition().getY() + imageB.getNumberRows());

        for (int x = xMin; x < xMax; x++) {
            for (int y = yMin; y < yMax; y++) {

                if (imageA.getValue(x - a.getPosition().getX(), y - a.getPosition().getY()) != null
                        && imageB.getValue(x - b.getPosition().getX(), y - b.getPosition().getY()) != null) {
                    return true;
                }
            }
        }

        return false;
    }
}