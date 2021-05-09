package org.jetpack.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import org.jetpack.model.Matrix;
import org.jetpack.model.Position;
import org.jetpack.model.elements.Element;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI implements GUI {
    private final TerminalScreen screen;
    private final int width, height; // TODO: SEE IF IS ANOTHER WAY TO GET THIS VALUES

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(width, height, fontConfig);
        screen = createScreen(terminal);

        // TODO: SEE IF IS ANOTHER WAY TO GET THIS VALUES
        // Arena attributes
        this.width = width;
        this.height = height;
    }

    private TerminalScreen createScreen(Terminal terminal) throws IOException {
        final TerminalScreen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width + 2, height + 2);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        return terminal;
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
    }

    @Override
    public int getTerminalWidth() {
        return screen.getTerminalSize().getColumns();
    }

    @Override
    public int getTerminalHeight() {
        return screen.getTerminalSize().getRows();
    }

    @Override
    public int getArenaWidth() {
        return width;
    }

    @Override
    public int getArenaHeight() {
        return height;
    }

    @Override
    public ACTION getNextAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();

        if (keyStroke == null) return ACTION.NONE;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') return ACTION.QUIT;
        if (keyStroke.getKeyType() == KeyType.Escape) return ACTION.PAUSE;      // TODO
        if (keyStroke.getKeyType() == KeyType.Enter) return ACTION.ENTER;

        if (keyStroke.getKeyType() == KeyType.ArrowUp) return ACTION.UP;
        if (keyStroke.getKeyType() == KeyType.ArrowDown) return ACTION.DOWN;

        return ACTION.NONE;
    }

    @Override
    public void drawElement(Element element) {
        Matrix<Character> image = element.getImage();
        Position position = element.getPosition();

        int infoWidth = (getTerminalWidth() - width) / 2;
        int infoHeight = (getTerminalHeight() - height) / 2;

        int minRow = Math.max(0, position.getY()) + infoHeight;
        int maxRow = Math.min(height, position.getY() + image.getNumberRows()) + infoHeight;
        int minCol = Math.max(0, position.getX()) + infoWidth;
        int maxCol = Math.min(width, position.getX() + image.getNumberCol()) + infoWidth;

        for (int row = minRow; row < maxRow; ++row) {
            for (int col = minCol; col < maxCol; ++col) {
                if (minCol == infoWidth) {
                    drawCharacter(new Position(col, row), image.getValue(col - minCol - position.getX(), row - minRow));
                } else {
                    drawCharacter(new Position(col, row), image.getValue(col - minCol, row - minRow));
                }
            }
        }
    }

    @Override
    public void drawCharacter(Position position, Character c) {
        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter(c)[0]);
    }

    @Override
    public void drawText(Position position, String text, String color) {
        TextGraphics tg = screen.newTextGraphics();
        if (!color.equals(""))
            tg.setBackgroundColor(TextColor.Factory.fromString(color));
        tg.putString(position.getX(), position.getY(), text);
    }

    @Override
    public void color(int x, int y, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.Factory.fromString(color));
        tg.putString(x, y, " ");
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}