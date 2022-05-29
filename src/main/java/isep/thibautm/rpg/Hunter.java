package isep.thibautm.rpg;

public class Hunter extends Hero {
    private int arrows;
    public Hunter(String name){
        this.name=name;
        this.typeHero = "Hunter";
        this.lifePoints = 15;
        this.maxLifePoints=lifePoints;
        this.armor=3;
        this.weaponDamage=8;
        this.arrows=10;
    }

    @Override
    public boolean attack(Enemy enemy) {
        this.setArrows(-1);
        return enemy.receiveDamage(this.weaponDamage);
    }


    public int getArrows(){
        return arrows;
    }
    public void setArrows(int change){
        this.arrows+=change;
    }
}