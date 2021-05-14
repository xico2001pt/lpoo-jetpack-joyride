package org.jetpack.controller.game;

import org.jetpack.model.Matrix;
import org.jetpack.model.elements.Element;

import static java.lang.Math.max;
import static java.lang.Math.min;

public final class CollisionController {

    public static boolean checkElementCollision(Element a, Element b) {
        int x1 = a.getPosition().getX(), y1 = a.getPosition().getY();
        int width1 = a.getImage().getNumberCol(), height1 = a.getImage().getNumberRows();

        int x2 = b.getPosition().getX(), y2 = b.getPosition().getY();
        int width2 = b.getImage().getNumberCol(), height2 = b.getImage().getNumberRows();

        return checkBoxCollision(x1, y1, width1, height1, x2, y2, width2, height2) && checkImageCollision(a, b);
    }

    static boolean checkBoxCollision(int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2) {
        return  x1 < x2 + width2  &&
                x1 + width1 > x2  &&
                y1 < y2 + height2 &&
                y1 + height1 > y2;
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
