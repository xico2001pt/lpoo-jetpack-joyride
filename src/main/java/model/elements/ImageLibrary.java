package model.elements;

import model.Matrix;

public final class ImageLibrary {
    /*private static ImageLibrary instance;

    private ImageLibrary() {}

    public static ImageLibrary getInstance() {
        if (instance == null) instance = new ImageLibrary();
        return instance;
    }*/

    //public static Matrix<Character> getLaser() {}

    public static Matrix<Character> getPlayerImage() {
        return new Matrix<Character> (1, 1, 'P');
    }
}
