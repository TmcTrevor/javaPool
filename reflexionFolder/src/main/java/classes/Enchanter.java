package classes;

import java.util.StringJoiner;

public class Enchanter {

    private final String name;
    private final int healPoints;
    private int health;



    public Enchanter() {
        this.name = "first Enchanter";
        this.healPoints = 10;
        this.health = 100;
    }

    public Enchanter(String name, int hp) {

        this.name = name;
        this.healPoints = hp;
        this.health = 100;
    }
    public Enchanter(String name, int hp, int health) {

        this.name = name;
        this.healPoints = hp;
        this.health = health;
    }

    public void regenerate(int extraHealth) {
        if (extraHealth > 0) {
            if (this.health + extraHealth <= 100) {
                this.health += extraHealth;
            }
            else
                this.health = 100;
        }
    }

    public void heal(Fighter fighter) {
        fighter.regenerate(healPoints);
    }

    public void heal(Enchanter enchanter) {
        enchanter.regenerate(healPoints);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Enchanter.class.getSimpleName() + "[", "]")
                .add("Name='" + name + "'")
                .add("Heal points ='" + healPoints + "'")
                .add("Health =" + health)
                .toString();
    }


}
