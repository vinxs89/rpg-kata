package xpeppers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {

    Character attacker;
    Character player6Level;
    Character player1Level;
    Character healer;
    Character healthyPlayer;
    Character deadPlayer;
    Character health500Player;

    @Before
    public void setUp() {
        attacker = new Character();
        player6Level = new Character(1000, 6);
        player1Level = new Character();
        healer = new Character();
        healthyPlayer = new Character();
        deadPlayer = new Character(0);
        health500Player = new Character(500);
    }

    @Test
    public void healthShouldBe1000Initially() {
        assertEquals(1000, attacker.getHealth());
    }

    @Test
    public void shouldDealDamage() {
        assertEquals(1000, healthyPlayer.getHealth());
        attacker.dealDamage(healthyPlayer, 50);
        assertEquals(950, healthyPlayer.getHealth());
    }

    @Test
    public void shouldBeAliveInitially() {
        assertTrue(attacker.isAlive());
    }

    @Test
    public void shouldBeDeadWhenHealthReachZero() {
        assertEquals(1000, healthyPlayer.getHealth());
        attacker.dealDamage(healthyPlayer, 1000);
        assertFalse(healthyPlayer.isAlive());
    }

    @Test
    public void healthShouldBeZeroWhenReceiveMoreThanHealth() {
        assertEquals(1000, healthyPlayer.getHealth());
        attacker.dealDamage(healthyPlayer, 1500);
        assertEquals(0, healthyPlayer.getHealth());
    }

    @Test
    public void shouldHealItself() {
        assertEquals(500, health500Player.getHealth());
        health500Player.heal(health500Player, 100);
        assertEquals(600, health500Player.getHealth());
    }


    @Test
    public void shouldNotHealHealthyItself() {
        assertEquals(1000, healer.getHealth());
        healer.heal(healer, 100);
        assertEquals(1000, healer.getHealth());
    }

    @Test
    public void healingShouldNotIncreaseHealthMoreThan1000() {
        Character sickHealer = new Character(950);
        sickHealer.heal(sickHealer, 100);
        assertEquals(1000, sickHealer.getHealth());
    }

    @Test
    public void shouldNotHealDeadItself() {
        assertFalse(deadPlayer.isAlive());
        deadPlayer.heal(deadPlayer, 100);
        assertFalse(deadPlayer.isAlive());
        assertEquals(0, deadPlayer.getHealth());
    }

    @Test
    public void shouldNotHealOtherCharacter() {
        assertEquals(500, health500Player.getHealth());
        healer.heal(health500Player, 100);
        assertEquals(500, health500Player.getHealth());
    }

    @Test
    public void shouldNotDealDamageToItself() {
        assertEquals(1000, attacker.getHealth());
        attacker.dealDamage(attacker, 100);
        assertEquals(1000, attacker.getHealth());
    }

    @Test
    public void damageShouldBeIncreasedBy50IfAttackerHas5LevelOrMoreMoreThanVictim() {
        assertEquals(1000, player1Level.getHealth());
        player6Level.dealDamage(player1Level, 200);
        assertEquals(700, player1Level.getHealth());
    }

    @Test
    public void damageShouldBeDecreasedBy50IfVictimHas5LevelOrMoreThanAttacked() {
        assertEquals(1000, player6Level.getHealth());
        player1Level.dealDamage(player6Level, 200);
        assertEquals(900, player6Level.getHealth());
    }
}
