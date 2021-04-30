package controller;

import gui.GUI;
import model.Position;
import model.arena.Arena;
import model.elements.Player;
import model.elements.Coin;
import model.elements.Element;
import model.elements.obstacles.Obstacle;

public class PlayerController {
    private final Arena arena;

    public PlayerController(Arena arena) {
        this.arena = arena;
    }

    private void movePlayerDown() {
        movePlayer(arena.getPlayer().getPosition().getDown());
    }

    private void movePlayerUp() {
        movePlayer(arena.getPlayer().getPosition().getUp());
    }

    private void movePlayer(Position position) {
        // TODO: See position in arena
        // setPosition for player
        // - If coins, nCoins++
        // - If obstacle, lives--
        // - ...

        Player player = arena.getPlayer();

        player.setPosition(position);

        // Coins
        for (Coin c: arena.getCoins())
            if (checkCollision(player, c))
                player.setCoins(player.getCoins() + 1);

        // Obstacles
        for (Obstacle o: arena.getObstacles())
            if (checkCollision(player, o))
                player.setLives(player.getLives() - 1);
    }

    public void doAction(GUI.ACTION action) {
        if (action == GUI.ACTION.UP) movePlayerUp();
        else if (action == GUI.ACTION.DOWN) movePlayerDown();
    }

    private boolean checkCollision(Element a, Element b) {
        return checkBoxCollision(a, b) && checkImageCollision(a, b);
    }

    private boolean checkBoxCollision(Element a, Element b) {
        return !(a.getPosition().getX() + a.getImage().getNumberCol() < b.getPosition().getX() ||
                a.getPosition().getX() > b.getPosition().getX() + b.getImage().getNumberCol() ||
                a.getPosition().getY() < b.getPosition().getY() + b.getImage().getNumberRows() ||
                a.getPosition().getY() + a.getImage().getNumberRows() > b.getPosition().getY());
    }

    private boolean checkImageCollision(Element a, Element b) {
        // TODO
        return true;
    }
}
