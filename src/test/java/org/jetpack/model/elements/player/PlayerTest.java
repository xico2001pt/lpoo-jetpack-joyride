package org.jetpack.model.elements.player;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Positive;
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

    @Property
    void takeDamage(@ForAll @Positive int damage) {
        Position position = Mockito.mock(Position.class);
        Player player = new Player(position);
        int initialLives = player.getLives();

        PlayerStrategy strategy = Mockito.mock(PlayerStrategy.class);
        Mockito.when(strategy.damageTaken()).thenReturn(damage);
        player.setStrategy(strategy);

        player.takeDamage();
        assertEquals(player.getLives() + damage, initialLives);
    }

    @Property
    void addCoin(@ForAll @Positive int coin) {
        Position position = Mockito.mock(Position.class);
        Player player = new Player(position);
        int initialCoins = player.getCoins();

        PlayerStrategy strategy = Mockito.mock(PlayerStrategy.class);
        Mockito.when(strategy.coinTaken()).thenReturn(coin);
        player.setStrategy(strategy);

        player.addCoin();
        assertEquals(player.getCoins() - coin, initialCoins);
    }
}