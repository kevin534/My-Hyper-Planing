package com.hyperplanning;
import com.hyperplanning.daos.*;
import com.hyperplanning.dataSource.DBCPDataSource;
import com.hyperplanning.entities.*;
import com.hyperplanning.entities.Etudiant;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Objects;
import java.util.ResourceBundle;

public class PresenceController implements Initializable {
    int idEnseignant;
    @FXML
    private TableView<Etudiant> list_table;
    @FXML
    private ComboBox<Cours> liste_cours;
    @FXML
    private Button makePresence;
    @FXML
    private Button showCours;

  //  private ComboBox<Integer> liste_C;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private TableColumn<Etudiant, Integer> id_col;
    @FXML
    private TableColumn<Etudiant, String> name_col;
    @FXML
    private TableColumn<Etudiant, Boolean> present_col;
    @FXML
    private TableColumn<Etudiant, String> excuse_col;
    // an observable list of students
    private ObservableList<Etudiant> students = FXCollections.observableArrayList();
    // an observable list of presence
    private ObservableList<Presence> presences = FXCollections.observableArrayList();
    // an observable list of cours
    private ObservableList<Cours> cours = FXCollections.observableArrayList();
    //private ObservableList<Integer> cours_C = FXCollections.observableArrayList();
    private Calendar cal = Calendar.getInstance();
    Connection  connection;
    Cours courSelected;
    {
        try {
            connection = DBCPDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PresenceController(int id){this.idEnseignant = id;}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(idEnseignant);
       // load table on class start
        // define what each column is going to hold (based on student class)
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
        present_col.setCellValueFactory(new PropertyValueFactory<>("present"));
        excuse_col.setCellValueFactory(new PropertyValueFactory<>("excuse"));
        list_table.setItems(students); // set table items as the Students observable list
        list_table.setEditable(true); // enable table editing
        excuse_col.setCellFactory(TextFieldTableCell.forTableColumn()); // enable column editing
        list_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // resize column based on whole table(window) size

        showCours.setOnAction(actionEvent ->  {
            //... do something in here.
            LoadData();

        });

        try(Statement statement = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM cours where idEnseignants="+idEnseignant);
            CoursDao coursDao = new CoursDao();
            while(resultSet.next()) {
                cours.add(coursDao.fromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        liste_cours.setItems(cours);



        makePresence.setOnAction(actionEvent ->  {
            //... do something in here.
           fairePresence();
        });
    }
    public  void LoadData() {
        //list_table.getItems().clear(); // clear table content before adding them again
        if (liste_cours.getValue() != null){
            list_table.getItems().clear(); // clear table content before adding them again
           nextAction();
        }
    }
    private void nextAction() {

        try (Statement statement = connection.createStatement();){

            courSelected = liste_cours.getSelectionModel().getSelectedItem();
            ResultSet rs = statement.executeQuery("SELECT * FROM utilisateurs  INNER JOIN etudiants  ON utilisateurs.id = etudiants.id "
                    +"where idGroupeClasse = "
                    +courSelected.getGroupe().getGroupeClasse());

            while (rs.next()) {
                // store each row in a student object
                students.add(new Etudiant(rs.getInt("id"), rs.getString("nom"),
                        rs.getString("prenoms"), rs.getString("email"),rs.getString("password"),
                        rs.getBoolean("present"),rs.getString("excuse")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void fairePresence() {
        System.out.println(students);

        for (Etudiant stud : students) {
            try (Statement statement = connection.createStatement();)
            {

                // selects the courses for which the student is absent



                    String query = "INSERT INTO PRESENCE(ID,IDCOURS,PRESENT,EXCUSE) VALUES (?,?,?,?)";
                    try ( PreparedStatement preparedStatement = connection.prepareStatement(query);) {
                        preparedStatement.setInt(1, stud.getId());
                        preparedStatement.setInt(2, courSelected.getId());
                        preparedStatement.setBoolean(3, stud.getPresent().isSelected());
                        preparedStatement.setString(4, stud.getExcuse());
                        preparedStatement.executeQuery();

                        JOptionPane.showMessageDialog(null ,"Presence done!");


                    }
            } catch (SQLException e) {
                e.printStackTrace();

                JOptionPane.showMessageDialog(null ,"Presence of this course was done!");

            }


    }

}

}

