package isep.thibautm.rpg;

public class Potion implements Consumable {
    private final int minHeal = 8;
    private final int maxHeal = 12;

    @Override
    public int healedLifePoint() {
        return (int)Math.floor(Math.random()*(this.maxHeal-this.minHeal+1)+this.minHeal);
    }
}