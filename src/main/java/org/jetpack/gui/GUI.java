package org.jetpack.gui;

import org.jetpack.model.Matrix;
import org.jetpack.model.Position;

import java.io.IOException;

public interface GUI {
    enum ACTION { UP, DOWN, NONE, PAUSE, ENTER, QUIT }

    int getTerminalWidth();

    int getTerminalHeight();

    ACTION getNextAction() throws IOException;

    void drawImage(Position position, Matrix<Character> image);

    void drawText(Position position, String text, String color);

    void drawRectangle(Position position, int width, int height, String color);

    default boolean isOnScreen(Position position) {
        boolean xFits = position.getX() >= 0 && position.getX() < getTerminalWidth();
        boolean yFits = position.getY() >= 0 && position.getY() < getTerminalHeight();
        return xFits && yFits;
    }

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;
}
