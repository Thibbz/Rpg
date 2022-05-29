package isep.thibautm.rpg;

abstract public class Enemy {
    protected int lifePoints;
    protected int damage;
    protected int armor;

    public Boolean receiveDamage(int damage){
        if (damage-this.armor<0){
            damage = 0;
        } else {
            damage=damage-this.armor;
        }
        this.lifePoints=lifePoints-damage;
        return this.lifePoints<0;
    }

    public boolean attack(Hero hero){
        return hero.receiveDamage(this.damage);
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

}