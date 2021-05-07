package org.jetpack.model.elements;

import org.jetpack.model.Matrix;
import org.jetpack.model.Position;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return position.equals(element.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
