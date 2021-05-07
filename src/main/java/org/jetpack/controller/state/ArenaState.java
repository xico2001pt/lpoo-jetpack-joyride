package org.jetpack.controller.state;

import org.jetpack.controller.ArenaController;
import org.jetpack.controller.GameLoop;
import org.jetpack.gui.GUI;
import org.jetpack.model.arena.ArenaBuilder;
import org.jetpack.viewer.WindowViewer;

import java.io.IOException;

public class ArenaState extends GameState {
    private final ArenaController controller;
    private final WindowViewer viewer;

    public ArenaState(GameLoop gameLoop, GUI gui, ArenaBuilder arenaBuilder) {
        super(gameLoop, gui);
        this.controller = new ArenaController(arenaBuilder);
        this.viewer = new WindowViewer(gui);
    }

    @Override
    public void update(GUI.ACTION action, long elapsed) {
        controller.getArenaBuilder().incrementInstant(elapsed);
        controller.getArena().addCoins(controller.getArenaBuilder().getCoins());
        controller.getArena().addObstacles(controller.getArenaBuilder().getObstacles());
        controller.updateArena(action, elapsed);

        if (controller.getArena().getPlayer().getLives() <= 0) getGameLoop().stop();
    }

    @Override
    public void render() throws IOException {
        viewer.draw(controller.getArena());
    }
}
