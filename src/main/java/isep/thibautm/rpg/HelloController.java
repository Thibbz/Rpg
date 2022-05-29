package isep.thibautm.rpg;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

public class HelloController {
    private int playerTurn;
    private List<Hero> heroes = new ArrayList<Hero>();
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private List<Object> fighters = new ArrayList<Object>();

    public static void main() {
        int x = 6;
        if(x>5){
            System.out.println("Threshold breach");
        }
    }

    @FXML
    private AnchorPane mainWindow;
    @FXML
    private Pane menu;
    @FXML
    private Pane playerSelector;
    @FXML
    private Pane fightScene;
    @FXML
    private Pane gameOverScreen;
    @FXML
    private Pane innBackground;

    @FXML
    private Button attackButton;
    @FXML
    private Button potionButton;
    @FXML
    private Button regenButton;
    @FXML
    private Button foodButton;


    @FXML
    private Label warriorNumber;
    @FXML
    private Slider warriorSlider;

    @FXML
    private Label hunterNumber;
    @FXML
    private Slider hunterSlider;

    @FXML
    private Label mageNumber;
    @FXML
    private Slider mageSlider;

    @FXML
    private Label healerNumber;
    @FXML
    private Slider healerSlider;

    @FXML
    private Label heroName;
    @FXML
    private Label hpCount;
    @FXML
    private Label atkCount;
    @FXML
    private Label arrowCount;
    @FXML
    private Label manaCount;

    @FXML
    private Button upArmor;
    @FXML
    private Button upAttack;
    @FXML
    private Button moreConsumable;
    @FXML
    private Button upConsumable;
    @FXML
    private Button moreAmmo;


    @FXML
    protected void onStartButtonClick(ActionEvent actionEvent) {
        changeScene(menu,playerSelector);
    }

    private void changeScene(Pane oldPane,Pane newpane){
        oldPane.setVisible(false);
        oldPane.setDisable(true);
        newpane.setDisable(false);
        newpane.setVisible(true);
    }

    public void initialize() {
        warriorSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            warriorNumber.setText(String.valueOf(Math.round(newValue.intValue())));
        });
        hunterSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            hunterNumber.setText(String.valueOf(Math.round(newValue.intValue())));
        });
        mageSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            mageNumber.setText(String.valueOf(Math.round(newValue.intValue())));
        });
        healerSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            healerNumber.setText(String.valueOf(Math.round(newValue.intValue())));
        });

    }

    public void startGame(ActionEvent actionEvent) {
        int nbWarrior = (int) Math.round(warriorSlider.getValue());
        int nbHunter = (int) Math.round(hunterSlider.getValue());
        int nbMage = (int) Math.round(mageSlider.getValue());
        int nbHealer = (int) Math.round(healerSlider.getValue());
        changeScene(playerSelector,fightScene);
        createHero(nbWarrior, nbHunter, nbMage, nbHealer);
    }

    private void createHero(int nbWarrior, int nbHunter, int nbMage, int nbHealer){
        for (int i=1; i<=nbWarrior;i++){
            Hero guerrier = new Warrior("Warrior "+ i);
            guerrier.setPotions(3);
            heroes.add(guerrier);
        }
        for (int i=1; i<=nbHunter;i++){
            Hero hunter = new Hunter("Hunter "+ i);
            heroes.add(hunter);
            hunter.setLambas(1);
        }
        for (int i=1; i<=nbMage;i++){
            Hero mage = new Mage("Mage "+ i);
            heroes.add(mage);
        }
        for (int i=1; i<=nbHealer;i++){
            Hero healer = new Healer("Healer "+ i);
            heroes.add(healer);
        }
        fighters.addAll(heroes);
        createEnemies();
    }

    private void createEnemies(){
        for (int i=0; i<heroes.size();i++){
            BasicEnemy enemy = new BasicEnemy();
            enemies.add(enemy);
        }
        fighters.addAll(enemies);
        Collections.shuffle(fighters);

        fightTurn(0);
    }

    private void nextTurn(int i){
        if (heroes.size()==0){
            System.out.println("Game over");
            changeScene(fightScene,gameOverScreen);
        } else if (enemies.size()==0){
            System.out.println("Victoire");
            changeScene(fightScene,innBackground);
            postBattleTurn(0);
        } else {
            if (i < fighters.size() - 1) {
                fightTurn(i + 1);
            } else {
                fightTurn(0);
            }
        }
    }

    private void fightTurn(int i){
        String fighterClass = fighters.get(i).getClass().getName();
        if (fighterClass.equals( "isep.thibautm.rpg.BasicEnemy")){
            BasicEnemy fighter = (BasicEnemy) fighters.get(i);
            boolean isDead = fighter.attack(heroes.get(0));
            if (isDead == true){
                Hero heroRem = heroes.get(0);
                heroes.remove(0);
                for (int j = 0;j<fighters.size();j++){
                    if (fighters.get(j)==heroRem){
                        fighters.remove(j);
                    }
                }
            }
            nextTurn(i);
        } else if (fighterClass.equals("isep.thibautm.rpg.Warrior")){
            Warrior fighter = (Warrior) fighters.get(i);

            attackButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    boolean isDead = fighter.attack(enemies.get(0));
                    if (isDead==true){
                        enemyDead();
                    }
                    nextTurn(i);
                }
            }));

            potionButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    fighter.setPotions(-1);
                    nextTurn(i);
                }
            }));

            foodButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    fighter.setLambas(-1);
                    nextTurn(i);
                }
            }));

            activateButton(1,fighter.getName(),fighter.getMaxLifePoints(),fighter.getLifePoints(),fighter.getWeaponDamage(),-1);
            hasConsumable(fighter.getPotions(),fighter.getLambas());

        } else if (fighterClass.equals("isep.thibautm.rpg.Hunter")){
            Hunter fighter = (Hunter) fighters.get(i);

            attackButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    boolean isDead = fighter.attack(enemies.get(0));
                    if (isDead==true){
                        enemyDead();
                    }
                    nextTurn(i);
                }
            }));

            regenButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    fighter.setArrows(2);
                    nextTurn(i);
                }
            }));

            potionButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    fighter.setPotions(-1);
                    nextTurn(i);
                }
            }));

            foodButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    fighter.setLambas(-1);
                    nextTurn(i);
                }
            }));

            activateButton(2,fighter.getName(),fighter.getMaxLifePoints(),fighter.getLifePoints(),fighter.getWeaponDamage(), fighter.getArrows());
            hasConsumable(fighter.getPotions(),fighter.getLambas());

        } else if (fighterClass.equals("isep.thibautm.rpg.Mage")){
            Mage fighter = (Mage) fighters.get(i);

            attackButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    boolean isDead = fighter.attack(enemies.get(0));
                    if (isDead==true){
                        enemyDead();
                    }
                    nextTurn(i);
                }
            }));

            potionButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    fighter.setPotions(-1);
                    nextTurn(i);
                }
            }));

            regenButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    fighter.setManaPoints(2);
                    nextTurn(i);
                }
            }));

            foodButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    fighter.setLambas(-1);
                    nextTurn(i);
                }
            }));

            activateButton(3,fighter.getName(),fighter.getMaxLifePoints(),fighter.getLifePoints(),fighter.getWeaponDamage(), fighter.getManaPoints());
            hasConsumable(fighter.getPotions(),fighter.getLambas());

        } else if (fighterClass.equals("isep.thibautm.rpg.Healer")){
            Healer fighter = (Healer) fighters.get(i);

            attackButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    fighter.attack(heroes.get(0));
                    nextTurn(i);
                }
            }));

            regenButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    fighter.setManaPoints(2);
                    nextTurn(i);
                }
            }));

            potionButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    fighter.setPotions(-1);
                    nextTurn(i);
                }
            }));

            foodButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    fighter.setLambas(-1);
                    nextTurn(i);
                }
            }));

            activateButton(3,fighter.getName(),fighter.getMaxLifePoints(),fighter.getLifePoints(),fighter.getWeaponDamage(), fighter.getManaPoints());
            hasConsumable(fighter.getPotions(),fighter.getLambas());
        }
    }

    private void hasConsumable(int potion, int food){
        potionButton.setDisable(true);
        foodButton.setDisable(true);
        if (potion>0){
            potionButton.setDisable(false);
        }
        if (food>0){
            foodButton.setDisable(false);
        }
    }

    private void activateButton(int heroType,String name, int maxHp, int hp, int damage, int count){
        heroName.setText(name);
        hpCount.setText("PV : " + hp+'/'+maxHp);
        atkCount.setText("Attaque : "+damage);
        attackButton.setDisable(false);
        if (heroType==2 || heroType ==3){
            if (count<=0){
                attackButton.setDisable(true);
            }
            regenButton.setDisable(false);
            if (heroType==2){
                arrowCount.setText("Flèches : "+count);
                manaCount.setText("");
            } else {
                manaCount.setText("Mana : "+count);
                arrowCount.setText("");
            }
        } else {
            arrowCount.setText("");
            manaCount.setText("");
            regenButton.setDisable(true);
        }
    }

    private void enemyDead(){
        Enemy enemyRem = enemies.get(0);
        enemies.remove(0);
        for (int j = 0;j<fighters.size();j++){
            if (fighters.get(j)==enemyRem){
                    fighters.remove(j);
            }
        }
    }

    private void postBattleTurn(int i){
        moreAmmo.setDisable(true);
        String fighterClass= heroes.get(i).getClass().getName();
        if (fighterClass.equals( "isep.thibautm.rpg.Hunter") || fighterClass.equals("isep.thibautm.rpg.Mage") || fighterClass.equals("isep.thibautm.rpg.Healer")) {
            moreAmmo.setDisable(false);
        }

        upArmor.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                heroes.get(i).setArmor(1);
                nextInnTurn(i);
            }
        }));

        upAttack.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                heroes.get(i).setWeaponDamage(1);
                nextInnTurn(i);
            }
        }));

        upConsumable.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                heroes.get(i).setConsumableEfficency(0.5);
                nextInnTurn(i);
            }
        }));

        moreAmmo.setOnMouseClicked((new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (fighterClass.equals( "isep.thibautm.rpg.Hunter")){
                    ((Hunter) heroes.get(i)).setArrows(5);
                } else if (fighterClass.equals( "isep.thibautm.rpg.Mage")) {
                    ((Mage) heroes.get(i)).setManaPoints(5);
                } else {
                    ((Healer) heroes.get(i)).setManaPoints(5);
                }
                nextInnTurn(i);
            }
        }));

    }

    private void nextInnTurn(int i){
        if (i < heroes.size() - 1) {
            postBattleTurn(i + 1);
        } else {
            changeScene(innBackground,fightScene);
            createEnemies();
        }
    }

}