package org.jetpack.model.elements;

import org.jetpack.model.Matrix;

public final class ImageLibrary {
    public static Matrix<Character> getPlayerImage() {
        return new Matrix<Character> (1, 1, 'P');
    }

    public static Matrix<Character> getCoinImage() {
        return new Matrix<Character>(1, 1, 'C');
    }

    public static Matrix<Character> getLaser1Image() {
        Matrix<Character> matrix = new Matrix<>(7, 1, '-');
        matrix.setValue(0, 0, '|');
        matrix.setValue(6, 0, '|');

        return matrix;
    }

    public static Matrix<Character> getLaser2Image() {
        Matrix<Character> matrix = new Matrix<>(1, 5, '|');
        matrix.setValue(0, 0, '-');
        matrix.setValue(0, 4, '-');

        return matrix;
    }

    public static Matrix<Character> getEnergyWall1Image() {
        return new Matrix<>(2, 5, 'O');
    }

    public static Matrix<Character> getEnergyWall2Image() {
        return new Matrix<>(5, 2, 'O');
    }
}
