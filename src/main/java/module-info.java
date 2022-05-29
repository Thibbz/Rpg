module isep.thibautm.rpg {
    requires javafx.controls;
    requires javafx.fxml;


    opens isep.thibautm.rpg to javafx.fxml;
    exports isep.thibautm.rpg;
}