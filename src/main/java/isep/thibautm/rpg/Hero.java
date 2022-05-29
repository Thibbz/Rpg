package isep.thibautm.rpg;

import java.util.ArrayList;
import java.util.List;

abstract public class Hero {


    protected String name;
    protected int maxLifePoints;
    protected int lifePoints;
    protected int armor;
    protected int weaponDamage;
    protected String typeHero;
    protected ArrayList<Potion> Potions = new ArrayList<Potion>();
    protected ArrayList<Food> Lambas = new ArrayList<Food>();
    protected double consumableEfficency = 1;

    public int getMaxLifePoints() {
        return maxLifePoints;
    }


    protected Hero() {
    }

    public int getWeaponDamage(){
        return weaponDamage;
    }
    public void setWeaponDamage(int damageUp){
        this.weaponDamage+=damageUp;
    }

    public void setArmor(int armorUp){
        this.armor+=armorUp;
    }

    public void setConsumableEfficency(double EfficiencyUp){
        this.consumableEfficency+=EfficiencyUp;
    }

    public int getLifePoints(){
        return lifePoints;
    }
    public void setLifePoints(int change){
        if (this.lifePoints+change>=this.maxLifePoints) {
            this.lifePoints=this.maxLifePoints;
        } else {
            this.lifePoints+=change;
        }
    }

    public boolean attack(Enemy enemy){
        return enemy.receiveDamage(this.weaponDamage);
    }

    public String getName() {
        return name;
    }
    public Boolean receiveDamage(int damage){
        if (damage-this.armor<0){
            damage = 0;
        } else {
            damage=damage-this.armor;
        }
        this.lifePoints=lifePoints-damage;
        return this.lifePoints<=0;
    }


    public int getLambas() {
        return Lambas.size();
    }

    public void setLambas(int count) {
        if (count==-1){
            if (Lambas.size()>0){
                int healedLifePoint = Lambas.get(0).healedLifePoint();
                this.setLifePoints(healedLifePoint);
                Lambas.remove(0);
            }
        } else {
            for (int i=0; i<count;i++){
                Food lamba = new Food();
                Lambas.add(lamba);
            }
        }
    }

    public void setPotions(int count){
        System.out.println("called");
        if (count==-1){
            if (Potions.size()>0){
                int healedLifePoint = Potions.get(0).healedLifePoint();
                this.setLifePoints(healedLifePoint);
                Potions.remove(0);
            }
        } else {
            System.out.println("yes");
            for (int i=0; i<count;i++){
                Potion potion = new Potion();
                System.out.println("presque");
                Potions.add(potion);
            }
        }
    }

    public int getPotions() {
        return Potions.size();
    }

}