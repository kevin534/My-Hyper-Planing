package com.hyperplanning;
        import com.hyperplanning.daos.*;
        import com.hyperplanning.dataSource.DBCPDataSource;
        import com.hyperplanning.entities.*;
        import com.hyperplanning.entities.Etudiant;
        import com.jfoenix.controls.JFXDatePicker;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.control.cell.TextFieldTableCell;
        import java.net.URL;
        import java.sql.*;
        import java.time.LocalDate;
        import java.util.Calendar;
        import java.util.Objects;
        import java.util.ResourceBundle;

public class EmpreintMaterielController implements Initializable {
    public Button id_button_afficher;
    @FXML
    private DatePicker datePicker_empreint;
    @FXML
    private DatePicker datePicker_retour;
    @FXML
    private ComboBox<String> liste_materiel;
    private ObservableList<String> materiel = FXCollections.observableArrayList();
    private Calendar cal = Calendar.getInstance();
    Connection connection;
    {
        try {
            connection = DBCPDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<String> material = FXCollections.observableArrayList();
        material.add("Projecteur");
        material.add("Moniteur");
        material.add("Camera");
        material.add("Clavier");
        material.add("Mouse");
        material.add("Livre");



        liste_materiel.setItems(material);
    }

    public void SendMaterial() {
       String item = liste_materiel.getValue();
        LocalDate localDate_from = datePicker_empreint.getValue();
        LocalDate localDate_to = datePicker_retour.getValue();
        System.out.println(item);
        String tmp = localDate_from.getDayOfMonth() +"-"+localDate_from.getMonthValue()+"-"+localDate_from.getYear();
        System.out.println(tmp);
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO RESERVATION(MATERIEL,DATEDEBUT,DATEFIN) VALUES (?,?,?)";
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(query);
            preparedStatement.setString(1,item);
            preparedStatement.setString(2, String.valueOf(localDate_from));
            preparedStatement.setString(3, String.valueOf(localDate_to));
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
        }

    }
}