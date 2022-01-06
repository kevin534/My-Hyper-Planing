module com.hyperplanning {

    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires com.jfoenix;
    requires javafx.graphics;
    requires javafx.controls;
    requires org.controlsfx.controls;
    requires lombok;
    requires commons.dbcp2;

    requires java.management;


    opens com.hyperplanning to javafx.fxml;
    exports com.hyperplanning;
    exports com.hyperplanning.entities;
    opens com.hyperplanning.entities to javafx.fxml;
}