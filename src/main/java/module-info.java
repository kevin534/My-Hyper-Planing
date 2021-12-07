module com.hyperplanning {

    requires java.sql;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires commons.dbcp2;
    requires java.management;
    requires lombok;


    opens com.hyperplanning to javafx.fxml;
    exports com.hyperplanning;
    exports com.hyperplanning.entities;
    opens com.hyperplanning.entities to javafx.fxml;
}