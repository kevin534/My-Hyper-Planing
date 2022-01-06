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

import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Objects;
import java.util.ResourceBundle;

public class PresenceController implements Initializable {
    @FXML
    private TableView<Etudiant> list_table;
    @FXML
    private ComboBox<Cours> liste_cours;

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
        present_col.setCellValueFactory(new PropertyValueFactory<>("present"));
        excuse_col.setCellValueFactory(new PropertyValueFactory<>("excuse"));
        list_table.setItems(students); // set table items as the Students observable list
        list_table.setEditable(true); // enable table editing
        excuse_col.setCellFactory(TextFieldTableCell.forTableColumn()); // enable column editing
        list_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // resize column based on whole table(window) size

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cours ");
            CoursDao coursDao = new CoursDao();
            while(resultSet.next()) {
                cours.add(coursDao.fromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        liste_cours.setItems(cours);
    }
    public  void LoadData() {
        //list_table.getItems().clear(); // clear table content before adding them again
        if (liste_cours.getValue() != null){
            list_table.getItems().clear(); // clear table content before adding them again
           nextAction();
        }
    }
    private void nextAction() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            Cours courSelected = liste_cours.getValue();
            ResultSet rs = statement.executeQuery("SELECT * FROM utilisateurs  INNER JOIN etudiants  ON utilisateurs.id = etudiants.id "
                    +"where idGroupeClasse = "
                    +courSelected.getGroupe().getGroupeClasse());

            while (rs.next()) {
                // store each row in a student object
                students.add(new Etudiant(rs.getInt("id"), rs.getString("nom"),
                        rs.getString("prenoms"), rs.getString("email"),rs.getString("password"),
                        rs.getBoolean("present")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void fairePresence() {

        ObservableList<Matiere> matieresList = FXCollections.observableArrayList();
        Matiere matiere = Matiere.builder().libelleMatiere("Math").codeMatiere(1).build();
        matieresList.add(matiere);
        Enseignant enseignant = Enseignant.builder().id(1).nom("DJESSOU").prenoms("MAHUGNON").email("regisdjessou2@gmail.com").password("abcd")
                .matiere(matieresList).build();
        Salle salle = Salle.builder().codeSalle(1).libelleSalle("U001").batiment("Bat U").build();
        Groupe groupe = Groupe.builder().groupeClasse(9).libelleClasse("M2 infos").build();
        Date dateFin = new Date(2020, 05, 25);
        Date dateDebut = new Date(2020, 05, 25);
        Cours cours = Cours.builder().id(1).enseignant(enseignant).groupe(groupe).salle(salle).matiere(matiere).dateDebut(dateDebut).dateFin(dateFin).build();
        for (Etudiant stud : students) {
            PreparedStatement preparedStatement = null;
            try {
                String query = "INSERT INTO PRESENCE(ID,IDCOURS,PRESENT) VALUES (?,?,?)";
                preparedStatement = Objects.requireNonNull(connection).prepareStatement(query);
                preparedStatement.setInt(1,stud.getId());
                preparedStatement.setInt(2,cours.getId());
                preparedStatement.setBoolean(3,stud.getPresent().isSelected());
                preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error");
            }
        }
             /*
            PresenceDao presenceDao = new PresenceDao();
          System.out.println("my stud: 2" + presenceDao);
            ObservableList<Matiere> matieresList = FXCollections.observableArrayList();
            System.out.println("my stud: 3");
            Matiere matiere = Matiere.builder().libelleMatiere("Math").codeMatiere(1).build();
            matieresList.add(matiere);
            System.out.println("my stud: 4");
            Enseignant enseignant = Enseignant.builder().id(1).nom("DJESSOU").prenoms("MAHUGNON").email("regisdjessou2@gmail.com").password("abcd")
                    .matiere(matieresList).build();
            Salle salle = Salle.builder().codeSalle(1).libelleSalle("U001").batiment("Bat U").build();
            Groupe groupe = Groupe.builder().groupeClasse(9).libelleClasse("M2 infos").build();
            Date dateFin = new Date(2020, 05, 25);
            Date dateDebut = new Date(2020, 05, 25);
            Cours cours = Cours.builder().id(1).enseignant(enseignant).groupe(groupe).salle(salle).matiere(matiere).dateDebut(dateDebut).dateFin(dateFin).build();
            System.out.println("my stud: n");
            for (Etudiant stud : students) {

            System.out.println("my stud: 1");
            try {
                presenceDao.persist(stud,  cours,stud.getPresent().isSelected());
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
        */

    }

}
































/*

 public void fairePresence() {

            PresenceDao presenceDao = new PresenceDao();
          System.out.println("my stud: 2" + presenceDao);
            ObservableList<Matiere> matieresList = FXCollections.observableArrayList();
            System.out.println("my stud: 3");
            Matiere matiere = Matiere.builder().libelleMatiere("Math").codeMatiere(1).build();
            matieresList.add(matiere);
            System.out.println("my stud: 4");
            Enseignant enseignant = Enseignant.builder().id(1).nom("DJESSOU").prenoms("MAHUGNON").email("regisdjessou2@gmail.com").password("abcd")
                    .matiere(matieresList).build();
            Salle salle = Salle.builder().codeSalle(1).libelleSalle("U001").batiment("Bat U").build();
            Groupe groupe = Groupe.builder().groupeClasse(9).libelleClasse("M2 infos").build();
            Date dateFin = new Date(2020, 05, 25);
            Date dateDebut = new Date(2020, 05, 25);
            Cours cours = Cours.builder().id(1).enseignant(enseignant).groupe(groupe).salle(salle).matiere(matiere).dateDebut(dateDebut).dateFin(dateFin).build();
            System.out.println("my stud: n");
            for (Etudiant stud : students) {

            System.out.println("my stud: 1");
            try {
                presenceDao.persist(stud,  cours,stud.getPresent().isSelected());
                System.out.println("my stud: nn"+((Cours) cours).getId());
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }

ObservableList<Matiere> matieresList = FXCollections.observableArrayList();
        System.out.println("my stud: 3");
                Matiere matiere = Matiere.builder().libelleMatiere("Math").codeMatiere(1).build();
                matieresList.add(matiere);
                System.out.println("my stud: 4");
                Enseignant enseignant = Enseignant.builder().id(1).nom("DJESSOU").prenoms("MAHUGNON").email("regisdjessou2@gmail.com").password("abcd")
                .matiere(matieresList).build();
                Salle salle = Salle.builder().codeSalle(1).libelleSalle("U001").batiment("Bat U").build();
                Groupe groupe = Groupe.builder().groupeClasse(9).libelleClasse("M2 infos").build();
                Date dateFin = new Date(2020, 05, 25);
                Date dateDebut = new Date(2020, 05, 25);
                Cours cours = Cours.builder().id(1).enseignant(enseignant).groupe(groupe).salle(salle).matiere(matiere).dateDebut(dateDebut).dateFin(dateFin).build();
                System.out.println("my stud: n");
                for (Etudiant stud : students) {
                PreparedStatement preparedStatement = null;
                try {
                String query = "INSERT INTO PRESENCE(ID,IDCOURS,PRESENT) VALUES (?,?,?)";
                preparedStatement = Objects.requireNonNull(connection).prepareStatement(query);
                preparedStatement.setInt(1,stud.getId());
                preparedStatement.setInt(2,cours.getId());
                preparedStatement.setBoolean(3,stud.getPresent().isSelected());
                preparedStatement.executeQuery();
                } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error");
                }
                }

        for (Etudiant stud : students) {

            PresenceDao presenceDao = new PresenceDao();
            System.out.println("my stud: 1");
            try {
                presenceDao.persist(stud, (Cours) cours,stud.getPresent().isSelected());
                System.out.println("my stud: nn"+((Cours) cours).getId());
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
                }

 */

/*

 public void LoadData() {
        //list_table.getItems().clear(); // clear table content before adding them again
        if (liste_cours.getValue() != null){
            System.out.println("liste  "+liste_cours.getValue());
           // Cours courSelected = liste_cours.getValue();
            Statement statement = null;
            try {
                //Connection connection = DBCPDataSource.getConnection();
               statement = DBCPDataSource.getConnection().createStatement();
                System.out.println("Hi ------"+statement);
                String query = "SELECT * FROM utilisateurs  INNER JOIN etudiants  ON utilisateurs.id = etudiants.id ";
               // PreparedStatement preparedStatement = connection.prepareStatement(query);

               // statement.setInt(1,courSelected.getId());
                ResultSet rs = statement.executeQuery(query);
                // EtudiantDao etudiantDao = new EtudiantDao();

                while (rs.next()) {
                    // store each row in a student object
                    //students.add(etudiantDao.fromResultSet(rs));
                    students.add(new Etudiant(rs.getInt("id"), rs.getString("nom"),
                            rs.getString("prenoms"), rs.getString("email"),rs.getString("password"),
                            rs.getBoolean("present")));
                }
                System.out.println(students);
               // rs.close(); // close statement
                //DBCPDataSource.getConnection().close(); // close connection for now
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
 */