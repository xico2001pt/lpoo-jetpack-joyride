package org.jetpack.model.elements.player;

import org.jetpack.model.Position;
import org.jetpack.model.elements.player.playerStrategies.PlayerStrategy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {


    @Test
    void buyPowerUpNoMoney() {
        Position position = Mockito.mock(Position.class);
        Player player = new Player(position);

        assertFalse(player.buyPowerUp());
    }

    @Test
    void buyPowerUp() {
        Position position = Mockito.mock(Position.class);
        Player player = new Player(position);

        PlayerStrategy strategy = Mockito.mock(PlayerStrategy.class);
        Mockito.when(strategy.coinTaken()).thenReturn(20);
        player.setStrategy(strategy);

        player.addCoin();
        assertTrue(player.buyPowerUp());
        // Power-Up costs 10 (20-10=10 coins left)
        assertEquals(player.getCoins(), 10);
    }
}