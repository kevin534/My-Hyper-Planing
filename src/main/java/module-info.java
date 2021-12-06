module com.hyperplanning {
    requires lombok;
    requires commons.dbcp2;
    requires java.sql;

    opens com.hyperplanning to javafx.fxml;
    exports com.hyperplanning;
    exports com.hyperplanning.entities;
    opens com.hyperplanning.entities to javafx.fxml;
}