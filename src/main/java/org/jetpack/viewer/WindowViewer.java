package org.jetpack.viewer;

import org.jetpack.gui.GUI;
import org.jetpack.gui.LanternaGUI;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.elements.Player;

import java.io.IOException;

public class WindowViewer {
    private final GUI gui;
    private final ArenaViewer arenaViewer;

    public WindowViewer(GUI gui) {
        this.gui = gui;
        this.arenaViewer = new ArenaViewer(gui);
    }

    public void draw(Arena arena) throws IOException {
        gui.clear();

        arenaViewer.draw(arena);
        drawInfo(arena.getPlayer());

        gui.refresh();
    }

    public ArenaViewer getArenaViewer() {
        return arenaViewer;
    }

    private void drawInfo(Player player) {

        int infoWidth = (gui.getTerminalWidth() - gui.getArenaWidth()) / 2;
        int infoHeight = (gui.getTerminalHeight() - gui.getArenaHeight()) / 2;

        for (int y = 0; y < gui.getTerminalWidth(); y++) {
            for (int x = 0; x < gui.getTerminalHeight(); x++) {

                if (y != 0 && (x < infoWidth || x >= gui.getTerminalWidth() - infoWidth))
                    gui.color(x, y, "#303030");
                if (x != 0 && (y < infoHeight || y >= gui.getTerminalHeight() - infoHeight))
                    gui.color(x, y, "#303030");
            }
        }

        gui.drawText(new Position(0, 0), "Lives: " + player.getLives(), "#303030");
        gui.drawText(new Position(gui.getTerminalWidth()/2, 0), "Coins: " + player.getCoins(), "#303030");
    }

    public void close() throws IOException {
        gui.close();
    }
}
