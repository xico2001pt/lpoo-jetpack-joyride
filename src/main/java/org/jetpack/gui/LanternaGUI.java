package org.jetpack.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import org.jetpack.model.CharColor;
import org.jetpack.model.Matrix;
import org.jetpack.model.Position;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI implements GUI {
    private final TerminalScreen screen;
    private boolean mousePressed;

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.mousePressed = false;
        screen = createScreen(terminal);
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
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        ((AWTTerminalFrame)terminal).getComponent(0).addMouseListener(new LanternaMouseAdapter(this));
        return terminal;
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 20);
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
    public ACTION getNextAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();

        if (keyStroke == null) return (this.mousePressed ? ACTION.MOUSE_PRESSED : ACTION.NONE);

        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == 'q') return ACTION.QUIT;
        if (keyStroke.getKeyType() == KeyType.Escape) return ACTION.PAUSE;
        if (keyStroke.getKeyType() == KeyType.Enter) return ACTION.ENTER;

        if (keyStroke.getKeyType() == KeyType.ArrowUp) return ACTION.UP;
        if (keyStroke.getKeyType() == KeyType.ArrowDown) return ACTION.DOWN;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == ' ') return ACTION.SPACE;

        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == '1') return ACTION.POWER_UP1;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == '2') return ACTION.POWER_UP2;
        if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == '3') return ACTION.POWER_UP3;

        return (this.mousePressed ? ACTION.MOUSE_PRESSED : ACTION.NONE);
    }

    @Override
    public void drawImage(Position position, Matrix<CharColor> image) {
        TextGraphics tg = screen.newTextGraphics();
        for (int y = position.getY(); y < position.getY() + image.getNumberRows(); ++y) {
            for (int x = position.getX(); x < position.getX() + image.getNumberCol(); ++x) {
                if (isOnScreen(new Position(x, y))) {
                    // Every time it overrides the background color
                    tg.setBackgroundColor(screen.getBackCharacter(x, y).getBackgroundColor());
                    tg.setForegroundColor(TextColor.Factory.fromString(image.getValue(x - position.getX(),
                            y - position.getY()).getColor()));
                    tg.setCharacter(x, y, image.getValue(x - position.getX(), y - position.getY()).getCharacter());
                }
            }
        }
    }

    @Override
    public void drawText(Position position, String text, String color) {
        TextGraphics tg = screen.newTextGraphics();
        if (color != null) tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.setBackgroundColor(screen.getBackCharacter(position.getX(),position.getY()).getBackgroundColor());
        tg.putString(position.getX(), position.getY(), text);
    }

    @Override
    public void drawRectangle(Position position, Dimension dimension, int thickness, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.Factory.fromString(color));
        for (int t = 0; t < thickness; ++t)
            tg.drawRectangle(new TerminalPosition(position.getX() + t, position.getY() + t), new TerminalSize(dimension.width - 2*t, dimension.height - 2*t), ' ');
    }

    @Override
    public void drawFillRectangle(Position position, Dimension dimension, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.Factory.fromString(color));
        tg.fillRectangle(new TerminalPosition(position.getX(), position.getY()), new TerminalSize(dimension.width, dimension.height), ' ');
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

    public void changeMouseState(boolean isPressed) {
        this.mousePressed = isPressed;
    }
}