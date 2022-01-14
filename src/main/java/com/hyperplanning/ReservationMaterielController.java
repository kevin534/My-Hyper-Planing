package com.hyperplanning;
import com.hyperplanning.daos.*;
import com.hyperplanning.dataSource.DBCPDataSource;
import com.hyperplanning.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ReservationMaterielController implements Initializable {
    int id;
    @FXML
    // an TableView of  Reservation
    private TableView<Reservation> list_table;
      @FXML
    // an TableColumn of  id_materiel_col
    private TableColumn<Enseignant, Integer> id_materiel_col;
    @FXML
    // an TableColumn of  nom_materiel_col
    private TableColumn<Enseignant, String> nom_materiel_col;
    @FXML
    // an TableColumn of  date_debut_col
    private TableColumn<Enseignant, String> date_debut_col;
    @FXML
    // an TableColumn of  date_fin_col
    private TableColumn<Enseignant, String> date_fin_col;
    // an observable list of reservation
    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();
    // an observable list of enseignant
    private ObservableList<Enseignant> list_enseignant_ = FXCollections.observableArrayList();
    //get connection
    Connection connection;


    public ReservationMaterielController(int id){this.id = id;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            connection = DBCPDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // load table on class start
        // define what each column is going to hold (based on reservation class)
        id_materiel_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_materiel_col.setCellValueFactory(new PropertyValueFactory<>("materiel"));
        date_debut_col.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        date_fin_col.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        list_table.setItems(reservations); // set table items as the reservation observable list

        //liste_Matiere_enseignant.setItems(list_enseignant_);
        LoadData();
    }
    public  void LoadData() {

            list_table.getItems().clear(); // clear table content before adding them again
            nextAction();

    }
    private void nextAction() {
        System.out.println(id);
        try( Statement statement = connection.createStatement()) {

           // Enseignant enseignantSelected = liste_Matiere_enseignant.getValue();
            // get all items of reservation to each enseignant
            ResultSet rs = statement.executeQuery("SELECT * FROM reservation  INNER JOIN enseignants  ON reservation.idEnseignant = enseignants.id "
                    +"where reservation.idEnseignant = "
                    +id);

            while (rs.next()) {
                Reservation reservation = Reservation.builder()
                        .id(rs.getInt("reservation.id"))
                        .materiel( rs.getString("materiel"))
                        .dateDebut(rs.getDate("dateDebut"))
                        .dateFin(rs.getDate("dateFin"))
                        .enseignant(Enseignant.builder().id(rs.getInt("idEnseignant")).build())
                        .build();
                // add a new object Reservation in list reservations
                reservations.add(reservation);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
