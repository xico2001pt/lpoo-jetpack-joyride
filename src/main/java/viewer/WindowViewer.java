package viewer;

import gui.GUI;
import model.Position;
import model.arena.Arena;
import model.elements.Player;

import java.io.IOException;

public class WindowViewer {
    private final GUI gui;


    public WindowViewer(GUI gui) {
        this.gui = gui;
    }

    public void draw(Arena arena) throws IOException {
        gui.clear();

        drawArena(arena);
        drawInfo(arena.getPlayer());

        gui.refresh();
    }

    private void drawArena(Arena arena) throws IOException {
        ArenaViewer arenaViewer = new ArenaViewer(gui);
        arenaViewer.draw(arena);
    }

    private void drawInfo(Player player) {
        gui.drawText(new Position(0, 0), "Lives: " + player.getLives());
        gui.drawText(new Position(gui.getTerminalWidth()/2, 0), "Coins: " + player.getCoins());
    }
}
