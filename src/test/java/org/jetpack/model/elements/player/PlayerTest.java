package org.jetpack.model.elements.player;

import net.jqwik.api.*;
import net.jqwik.api.constraints.Positive;
import org.jetpack.model.Position;
import org.jetpack.model.arena.Arena;
import org.jetpack.model.arena.ArenaBuilder;
import org.jetpack.model.arena.RandomArenaBuilder;
import org.jetpack.model.elements.movements.*;
import org.jetpack.model.elements.player.playerStrategies.*;
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
        Player player = new Player(new Position(0, 0));
        PlayerStrategy strategy = Mockito.mock(PlayerStrategy.class);
        Mockito.when(strategy.coinTaken()).thenReturn(20);
        player.setStrategy(strategy);

        player.addCoin();
        assertTrue(player.buyPowerUp());
        // Power-Up costs 10 (20-10=10 coins left)
        assertEquals(player.getCoins(), 10);
    }

    @Property
    void takeDamage(@ForAll("getStrategy") PlayerStrategy strategy) {
        Player player = new Player(new Position(0, 0));
        int initialLives = player.getLives();
        player.setStrategy(strategy);

        player.takeDamage();
        assertEquals(player.getLives() + strategy.damageTaken(), initialLives);
    }

    @Property
    void addCoin(@ForAll("getStrategy") PlayerStrategy strategy) {
        Player player = new Player(new Position(0, 0));
        int initialCoins = player.getCoins();
        player.setStrategy(strategy);

        player.addCoin();
        assertEquals(player.getCoins() - strategy.coinTaken(), initialCoins);
    }

    @Property
    void movement(@ForAll("getMovement") MovementStrategy strategy) {
        ArenaBuilder builder = new RandomArenaBuilder(10,10);
        Arena arena = builder.createArena();

        Player player = arena.getPlayer();
        Position initial = player.getPosition();
        player.setMovement(strategy);
        player.setPosition(player.getMovement().move(player.getPosition(), arena));

        assertNotEquals(player.getPosition(), initial);
    }

    @Provide
    Arbitrary<PlayerStrategy> getStrategy() {
        return Arbitraries.of(new DoubleCoinsStrategy(), new ImmortalStrategy(), new NormalStrategy(),
                new SlowDownStrategy());
    }

    @Provide
    Arbitrary<MovementStrategy> getMovement() {
        return Arbitraries.of(new DownMovement(), new LinearMovement(), new StaticMovement(), new UpMovement(),
                new MissileMovement(), new ZigZagMovement());
    }
}