package org.jetpack.model.elements;

import org.jetpack.model.Matrix;

public final class ImageLibrary {

    private static Matrix<Character> playerImage;
    private static Matrix<Character> coinImage;
    private static Matrix<Character> laser1Image;
    private static Matrix<Character> laser2Image;
    private static Matrix<Character> energyWall1Image;
    private static Matrix<Character> energyWall2Image;

    public static Matrix<Character> getPlayerImage() {
        if (playerImage == null) playerImage = new Matrix<>(1, 1, 'P');
        return playerImage;
    }

    public static Matrix<Character> getCoinImage() {
        if (coinImage == null) coinImage = new Matrix<>(1, 1, 'C');
        return coinImage;
    }

    public static Matrix<Character> getLaser1Image() {
        if (laser1Image == null) {
            laser1Image = new Matrix<>(7, 1, '-');
            laser1Image.setValue(0, 0, '|');
            laser1Image.setValue(6, 0, '|');
        }
        return laser1Image;
    }

    public static Matrix<Character> getLaser2Image() {
        if (laser2Image == null) {
            laser2Image = new Matrix<>(1, 5, '|');
            laser2Image.setValue(0, 0, '-');
            laser2Image.setValue(0, 4, '-');
        }
        return laser2Image;
    }

    public static Matrix<Character> getEnergyWall1Image() {
        if (energyWall1Image == null) energyWall1Image = new Matrix<>(2, 5, 'O');
        return energyWall1Image;
    }

    public static Matrix<Character> getEnergyWall2Image() {
        if (energyWall2Image == null) energyWall2Image = new Matrix<>(5, 2, 'O');
        return energyWall2Image;
    }
}
