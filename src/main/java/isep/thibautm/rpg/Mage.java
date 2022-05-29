package isep.thibautm.rpg;

public class Mage extends SpellCaster {
    public Mage(String name){
        this.name=name;
        this.typeHero = "Mage";
        this.lifePoints = 15;
        this.maxLifePoints=lifePoints;
        this.armor=2;
        this.weaponDamage=10;
        this.manaPoints=10;
    }

    @Override
    public boolean attack(Hero hero) {
        return false;
    }

    @Override
    public boolean attack(Enemy enemy) {
        this.setManaPoints(-1);
        return enemy.receiveDamage(this.weaponDamage);
    }

}