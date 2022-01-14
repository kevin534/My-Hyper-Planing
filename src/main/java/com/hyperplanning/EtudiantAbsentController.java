package com.hyperplanning;
import com.hyperplanning.daos.*;
import com.hyperplanning.dataSource.DBCPDataSource;
import com.hyperplanning.entities.*;
import com.hyperplanning.entities.Etudiant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.java.Log;

import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;

@Log
public class EtudiantAbsentController implements Initializable {

    int id;
    @FXML
    // an TableView of object
    private TableView<Object> liste_personne_absent;

    @FXML
    private TableColumn<Matiere, String> name_col;
    @FXML
    private TableColumn<Cours, String> date_debut_col;
    @FXML
    private TableColumn<Cours, String> heure_debut_col;
    // an observable list of Object
    private ObservableList<Object> cours = FXCollections.observableArrayList();
    // an observable list of Etudiant
    private ObservableList<Etudiant> stud_list = FXCollections.observableArrayList();

    // get connection
    Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connection = DBCPDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // load table on class start
        // define what each column is going to hold (based on cours class)
        name_col.setCellValueFactory(new PropertyValueFactory<Matiere,String>("matiere"));
        date_debut_col.setCellValueFactory(new PropertyValueFactory<Cours,String>("dateDebut"));
        heure_debut_col.setCellValueFactory(new PropertyValueFactory<Cours,String>("heureDebut"));
        liste_personne_absent.setItems(cours); // set table items as the cours observable list
        liste_personne_absent.setEditable(true); // enable table editing
        liste_personne_absent.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // resize column based on whole table(window) size


        /*try(  Statement statement = connection.createStatement();
              EtudiantDao etudiantDao = new EtudiantDao()) {

            //selects students to do presence
            ResultSet resultSet = statement.executeQuery("SELECT * FROM utilisateurs  INNER JOIN etudiants  ON utilisateurs.id = etudiants.id " +
                    "INNER JOIN presence ON etudiants.id = presence.id ");

            while(resultSet.next()) {
                // add new student to list of students
                stud_list.add(new Etudiant(resultSet.getInt("id"), resultSet.getString("nom"),
                        resultSet.getString("prenoms"), resultSet.getString("email"),resultSet.getString("password"),
                        resultSet.getBoolean("present"),resultSet.getString("excuse")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        liste_etudiant.setItems(stud_list);*/
        LoadAbsent();
    }

    public  EtudiantAbsentController(int id){this.id = id;}
    public void LoadAbsent() {

            liste_personne_absent.getItems().clear(); // clear table content before adding them again
            //Etudiant courSelected = liste_etudiant.getValue();

            try(Statement statement = connection.createStatement()) {

                // selects the courses for which the student is absent
                ResultSet rs = statement.executeQuery("SELECT * FROM presence INNER JOIN utilisateurs ON presence.id = utilisateurs.id INNER JOIN cours ON presence.idCours = cours.id "+
                        "INNER JOIN matieres ON cours.codeMatiere = matieres.id WHERE presence.present = 0  AND utilisateurs.id ="+id);

                while (rs.next()) {
                    // load object Matiere
                    Matiere matiere = Matiere.builder().
                            libelleMatiere(rs.getString("libelleMatiere")).build();
                    // date of cours where student is absent
                    Date dateCours = rs.getDate("dateDebut");
                    // hour that corse is begin
                    String heure = String.valueOf(rs.getString("heureDebut"));
                    // load object Cours
                    Cours cours_ = Cours.builder().matiere(matiere).dateDebut(dateCours).heureDebut(heure.toString()).build();
                    // add new Cours to list of cours
                    cours.add(cours_);
                }
            } catch (SQLException e) {
                log.log(Level.SEVERE, "Erreur: "+e.getMessage());            }


    }
}