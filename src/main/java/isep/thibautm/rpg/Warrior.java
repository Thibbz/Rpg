package isep.thibautm.rpg;

public class Warrior extends Hero {
    public Warrior(String name){
        this.name=name;
        this.typeHero = "Warrior";
        this.lifePoints = 20;
        this.maxLifePoints=lifePoints;
        this.armor=4;
        this.weaponDamage=6;
    }

}