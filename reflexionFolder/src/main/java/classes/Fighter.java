package classes;

import java.util.StringJoiner;

public class Fighter {

    private String name;
    private int attackDmg;
    private int health;



    public Fighter() {
        this.name = "first Fighter";
        this.attackDmg = 10;
        this.health = 100;
    }

    public Fighter(String name, int ad) {

        this.name = name;
        this.attackDmg = ad;
        this.health = 100;
    }
    public Fighter(String name, int ad, int hp) {

        this.name = name;
        this.attackDmg = ad;
        this.health = hp;
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

    public void attack(Fighter fighter) {
        fighter.health -= this.attackDmg;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Fighter.class.getSimpleName() + "[", "]")
                .add("Name='" + name + "'")
                .add("Attack Damage='" + attackDmg + "'")
                .add("Health =" + health)
                .toString();
    }


}
