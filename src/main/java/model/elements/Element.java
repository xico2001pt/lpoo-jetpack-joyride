package model.elements;

import model.Matrix;
import model.Position;

public abstract class Element {
    Position position;
    Matrix<Character> image;

    public Element(Position position, Matrix<Character> image) {
        this.position = position;
        this.image = image;
    }

    public Matrix<Character> getImage() {
        return image;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
