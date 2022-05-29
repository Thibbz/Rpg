package isep.thibautm.rpg;

public class Healer extends SpellCaster {
    public Healer(String name){
        this.name = name;
        this.typeHero = "Healer";
        this.lifePoints = 15;
        this.maxLifePoints=lifePoints;
        this.armor=2;
        this.weaponDamage=3;
        this.manaPoints=10;
    }

    @Override
    public boolean attack(Hero hero) {
        this.setManaPoints(-1);
        return hero.receiveDamage(-this.weaponDamage);
    }
}