package model.elements;

import model.Position;

import java.util.List;

public abstract class Element {
    Position position;
    List<List<Character>> image;

    public Element(Position position, List<List<Character>> image) {
        this.position = position;
        this.image = image;
    }

    public List<List<Character>> getImage() {
        return image;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
