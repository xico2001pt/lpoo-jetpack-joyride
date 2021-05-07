package org.jetpack.viewer;

import org.jetpack.gui.GUI;
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
        gui.drawText(new Position(0, 0), "Lives: " + player.getLives());
        gui.drawText(new Position(gui.getTerminalWidth()/2, 0), "Coins: " + player.getCoins());
    }

    public void close() throws IOException {
        gui.close();
    }
}
