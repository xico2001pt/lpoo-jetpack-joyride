package org.jetpack.model.elements;

import org.jetpack.model.CharColor;
import org.jetpack.model.Matrix;
import org.jetpack.model.Position;
import org.jetpack.model.elements.movements.LinearMovement;
import org.jetpack.model.elements.movements.MovementStrategy;

import java.util.Objects;

public abstract class Element {
    private Position position;
    private Matrix<CharColor> image;
    private MovementStrategy movement;

    public Element(Position position, Matrix<CharColor> image) {
        this.position = position;
        this.image = image;
        this.movement = new LinearMovement();
    }

    public Matrix<CharColor> getImage() {
        return image;
    }

    public void setImage(Matrix<CharColor> image) {
        this.image = image;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public MovementStrategy getMovement() {
        return movement;
    }

    public void setMovement(MovementStrategy movement) {
        this.movement = movement;
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
