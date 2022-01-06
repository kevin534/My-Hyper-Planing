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
        import java.util.Calendar;
        import java.util.Objects;
        import java.util.ResourceBundle;

public class EtudiantAbsentController implements Initializable {
    public Button id_button_afficher;
    @FXML
    private TableView<Etudiant> liste_personne_absent;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private ComboBox<Etudiant> liste_etudiant;
    private ComboBox<String> liste_et;
    @FXML
    private TableColumn<Etudiant, Integer> id_col;
    @FXML
    private TableColumn<Etudiant, String> name_col;
    // an observable list of students
    private ObservableList<Etudiant> students = FXCollections.observableArrayList();
    private ObservableList<Etudiant> stud_list = FXCollections.observableArrayList();
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
        // load table on class start
        // define what each column is going to hold (based on student class)
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
        liste_personne_absent.setItems(students); // set table items as the Students observable list
        liste_personne_absent.setEditable(true); // enable table editing
        liste_personne_absent.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // resize column based on whole table(window) size

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM utilisateurs  INNER JOIN etudiants  ON utilisateurs.id = etudiants.id " +
                    "INNER JOIN presence ON etudiants.id = presence.id ");
            EtudiantDao etudiantDao = new EtudiantDao();
            while(resultSet.next()) {
                stud_list.add(new Etudiant(resultSet.getInt("id"), resultSet.getString("nom"),
                        resultSet.getString("prenoms"), resultSet.getString("email"),resultSet.getString("password"),
                        resultSet.getBoolean("present")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        liste_etudiant.setItems(stud_list);
    }
    public void LoadAbsent() {
        if (liste_etudiant.getValue() != null) {
            liste_personne_absent.getItems().clear(); // clear table content before adding them again
            Etudiant courSelected = liste_etudiant.getValue();
            System.out.println("ma liste selon l'id:  " + courSelected.getId());
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM utilisateurs  INNER JOIN etudiants  ON utilisateurs.id = etudiants.id  " +
                        "INNER JOIN presence ON presence.id = etudiants.id  WHERE presence.present = 0 AND presence.id = "+courSelected.getId());
                //WHERE presence.present = 0
                while (rs.next()) {
                    // store each row in a student object
                    students.add(new Etudiant(rs.getInt("id"), rs.getString("nom"),
                            rs.getString("prenoms"), rs.getString("email"), rs.getString("password"),
                            rs.getBoolean("present")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error");
            }
            //id_button_afficher.setDisable(true);
        }

    }
}