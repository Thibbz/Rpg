package isep.thibautm.rpg;

public class Food implements Consumable {
    private int minHeal = 4;
    private int maxHeal = 8;

    @Override
    public int healedLifePoint() {
        return (int)Math.floor(Math.random()*(this.maxHeal-this.minHeal+1)+this.minHeal);
    }
}