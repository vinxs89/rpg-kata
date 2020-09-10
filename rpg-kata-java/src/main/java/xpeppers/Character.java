package xpeppers;

public class Character {

    private int health;
    private int level;

    public Character() {
        this(1000);
    }

    public Character(int health) {
        this.health = health;
        this.level = 1;
    }

    public Character(int health, int level) {
        this.health = health;
        this.level = level;
    }

    public void dealDamage(final Character c2, final int damage) {
        if (this != c2) {
            int realDamage = damage;
            if (this.getLevel() - c2.getLevel() >= 5) {
                realDamage += damage / 2;
            } else if (c2.getLevel() - this.getLevel() >= 5) {
                realDamage -= damage / 2;
            }
            c2.receiveDamage(realDamage);
        }
    }

    public void heal(Character c2, int healthPower) {
        if (this == c2) {
            c2.increaseHealth(healthPower);
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    int getHealth() {
        return health;
    }

    int getLevel() {
        return level;
    }

    private void receiveDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    private void increaseHealth(int healthPower) {
        if (isAlive()) {
            health += healthPower;
            if (health > 1000) {
                health = 1000;
            }
        }
    }
}
