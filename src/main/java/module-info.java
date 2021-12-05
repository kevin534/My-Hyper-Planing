module com.hyperplanning {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;

    opens com.hyperplanning to javafx.fxml;
    exports com.hyperplanning;
    exports com.hyperplanning.entities;
    opens com.hyperplanning.entities to javafx.fxml;
}