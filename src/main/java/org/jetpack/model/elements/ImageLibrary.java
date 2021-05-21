package org.jetpack.model.elements;

import org.jetpack.gui.ColorDatabase;
import org.jetpack.model.CharColor;
import org.jetpack.model.Matrix;

import java.awt.*;

public final class ImageLibrary {

    private static Matrix<CharColor> playerImage;
    private static Matrix<CharColor> coinImage;
    private static Matrix<CharColor> laser1Image;
    private static Matrix<CharColor> laser2Image;
    private static Matrix<CharColor> energyWall1Image;
    private static Matrix<CharColor> energyWall2Image;
    private static Matrix<CharColor> missileImage;
    private static Matrix<CharColor> zigZagImage;
    private static Matrix<CharColor> staticLaserImage;

    public static Matrix<CharColor> getPlayerImage() {
        if (playerImage == null) playerImage = new Matrix<>(1, 1, new CharColor('P', ColorDatabase.PLAYER.getName()));
        return playerImage;
    }

    public static Matrix<CharColor> getCoinImage() {
        if (coinImage == null) coinImage = new Matrix<>(1, 1, new CharColor('C', ColorDatabase.COINS.getName()));
        return coinImage;
    }

    public static Matrix<CharColor> getLaser1Image() {
        if (laser1Image == null) {
            laser1Image = new Matrix<>(7, 1, new CharColor('-', ColorDatabase.OSBTACLES.getName()));
            laser1Image.setValue(0, 0, new CharColor('|', ColorDatabase.OSBTACLES.getName()));
            laser1Image.setValue(6, 0, new CharColor('|', ColorDatabase.OSBTACLES.getName()));
        }
        return laser1Image;
    }

    public static Matrix<CharColor> getLaser2Image() {
        if (laser2Image == null) {
            laser2Image = new Matrix<>(1, 5, new CharColor('|', ColorDatabase.VIBRANT_GREEN.getName()));
            laser2Image.setValue(0, 0, new CharColor('-', ColorDatabase.OSBTACLES.getName()));
            laser2Image.setValue(0, 4, new CharColor('-', ColorDatabase.OSBTACLES.getName()));
        }
        return laser2Image;
    }

    public static Matrix<CharColor> getEnergyWall1Image() {
        if (energyWall1Image == null) energyWall1Image = new Matrix<>(2, 5, new CharColor('O', ColorDatabase.OSBTACLES.getName()));
        return energyWall1Image;
    }

    public static Matrix<CharColor> getEnergyWall2Image() {
        if (energyWall2Image == null) energyWall2Image = new Matrix<>(5, 2, new CharColor('O', ColorDatabase.OSBTACLES.getName()));
        return energyWall2Image;
    }

    public static Matrix<CharColor> getMissileImage() {
        if (missileImage == null) {
            missileImage = new Matrix<>(2,1, new CharColor('=', ColorDatabase.OSBTACLES.getName()));
            missileImage.setValue(0,0, new CharColor('<', ColorDatabase.OSBTACLES.getName()));
        }
        return missileImage;
    }

    public static Matrix<CharColor> getZigZagImage() {
        if (zigZagImage == null) zigZagImage = new Matrix<>(1,1, new CharColor('#', ColorDatabase.OSBTACLES.getName()));
        return zigZagImage;
    }

    public static Matrix<CharColor> getStaticLaserImage(Dimension dimension) {
        if (staticLaserImage == null) {
            staticLaserImage = new Matrix<>(dimension.width, dimension.height, new CharColor('X', ColorDatabase.OSBTACLES.getName()));
            staticLaserImage.setValue(0, 0, new CharColor('o', ColorDatabase.OSBTACLES.getName()));
        }
        return staticLaserImage;
    }
}
