package gui;

import model.Position;
import model.elements.Element;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawElement(Element object);

    void drawCharacter(Position position, Character c);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, DOWN, NONE, PAUSE, QUIT}
}
