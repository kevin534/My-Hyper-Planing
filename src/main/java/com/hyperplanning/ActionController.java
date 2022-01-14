package com.hyperplanning;

import com.hyperplanning.daos.CoursDao;
import com.hyperplanning.daos.GroupeDao;
import com.hyperplanning.daos.MatiereDao;
import com.hyperplanning.daos.SalleDao;
import com.hyperplanning.dataSource.DBCPDataSource;
import com.hyperplanning.entities.*;
import com.hyperplanning.exceptions.DataAccessException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import lombok.extern.java.Log;

import javax.swing.*;
import java.awt.image.ComponentColorModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;

@Log
public class ActionController implements Initializable {
    @FXML
    TextField leturerTableAction ;
    @FXML
    TextField levelTableAction;
    @FXML
    TextField ccourseAction;
    @FXML
    ComboBox<String>  timeAction;
    @FXML
    ComboBox<Salle>  cvenueAction;
    @FXML
    JFXButton supprimer;
    @FXML
    JFXButton modifier;
    @FXML
    JFXButton cancel;
    @FXML
    DatePicker CreneauAction;


    final ObservableList<Salle> b =  FXCollections.observableArrayList(); //Salle

    private static final String ACTION_1 = "rowCount"; // Compliant code for rowCount
    Connection conn;
    public int idCours;
    public int idGroupe;
    public int idEnseignant;
    public int idSalle;
    PreparedStatement pstmt;
    Statement statement;
    ObservableList<String> time_of_calendar = FXCollections.observableArrayList("7:00AM","8:00AM","9:00AM","10:00AM","11:00AM","12:00PM","1:00PM","2:00PM","3:00PM");

    protected static final Properties properties = new Properties();
    Salle salle = Salle.builder().build();

    static void loadProperties(String propFileName) throws IOException {
        InputStream inputstream = ActionController.class.getClassLoader().getResourceAsStream(propFileName);
        if (inputstream == null) throw new FileNotFoundException();
        properties.load(inputstream);

    }
    public ActionController(int idCours){
        this.idCours = idCours;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            loadProperties("app.properties");
            conn = DBCPDataSource.getConnection();
           // statement = conn.createStatement();


            fetch1();
            fetch2();
            fetch3();
            fetch4();
            fetch();
            cvenueAction.setItems(b);
            timeAction.setItems(time_of_calendar);


        } catch (Exception e) {
            e.printStackTrace();
        }

        supprimer.setOnAction(actionEvent ->  {
            //... do something in here.
            deleteCours(idCours, actionEvent);

        });
        modifier.setOnAction(actionEvent ->  {
            //... do something in here.
            updateCourse();

        });

        cancel.setOnAction(actionEvent ->  {
            //... do something in here.
            onMouseClickedCancelBtn(actionEvent);

        });
    }

    public void fetch(){
        //matiere
        String sql;
        Date date = null;
        String heure = null;
        try( Connection conn = DBCPDataSource.getConnection();){


            sql = "SELECT * FROM COURS WHERE COURS.id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idCours);
            try( ResultSet rs = pstmt.executeQuery();){

                while (rs.next()) {
                    date = rs.getDate("dateDebut");
                    heure = rs.getString("heureDebut");
                    idGroupe = rs.getInt("groupeClasse");


                }
            }
            LocalDate localDate_from = date.toLocalDate();
            CreneauAction.setValue(localDate_from);
            timeAction.getSelectionModel().select(heure);



        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        } finally {

                try {
                    conn.close();
                } catch (SQLException e) { /* Ignored */}

        }

    }
    public void fetch4(){
        //matiere
        String sql;
        String libelleSalle = null;
        int id;
        try{

            sql = "SELECT * FROM SALLES INNER JOIN COURS on COURS.codeSalle = Salles.id WHERE COURS.id = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, idCours);
            try (ResultSet rs = pstmt.executeQuery();){

            while(rs.next()) {
                id = rs.getInt("id");
                libelleSalle = rs.getString("libelleSalle");
                salle = Salle.builder()
                        .codeSalle(id)
                        .libelleSalle(libelleSalle)
                        .build();
            }

            }
            cvenueAction.getSelectionModel().select(salle);


        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        } finally {
                try {
                    conn.close();
                } catch (SQLException e) { /* Ignored */}

        }

    }


    public void fetch1(){
        //salles
        b.clear();
        try( SalleDao salleDao = new SalleDao()){

            List<Salle> salle1 ;
            salle1 = salleDao.getSalle();
            for(Salle s: salle1){
                b.add(s);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }


    }

    public void fetch2(){
        //matiere
        String sql;
        String matiere = null;
        try{


            sql = "SELECT * FROM MATIERES INNER JOIN COURS ON MATIERES.id = COURS.codeMatiere WHERE COURS.id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idCours);
            try(ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    matiere = rs.getString("libelleMatiere");


                }
            }
            levelTableAction.setText(matiere);
            levelTableAction.setEditable(false);


        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }

    }
    public void fetch3(){
        //matiere
        String sql;
        String enseignant = null;
        try{


            sql = "SELECT * FROM UTILISATEURS INNER JOIN COURS ON UTILISATEURS.id = COURS.idEnseignants WHERE COURS.id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idCours);
            try(ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    idEnseignant = rs.getInt("id");
                    enseignant = rs.getString("nom") + " " + rs.getString("prenoms");


                }
            }
            leturerTableAction.setText(enseignant);
            leturerTableAction.setEditable(false);


        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }

    }
    public void deleteCours(int id, Event event){

        try ( CoursDao coursDao = new CoursDao()){

            coursDao.remove(id);
            JOptionPane.showMessageDialog(null ,"Your course was deleted!!");
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(null ,"Your course could not be deleted");

        }

    }

    public void updateCourse(){
        //matiere
        String sql;
        String enseignant = null;

        Date dateDebut = Date.valueOf(CreneauAction.getValue());
        idSalle = cvenueAction.getSelectionModel().getSelectedItem().getCodeSalle();
        String heure = timeAction.getSelectionModel().getSelectedItem();

        try( Connection conn = DBCPDataSource.getConnection();){


            sql = "SELECT COUNT(*) AS rowCount FROM COURS WHERE DateDebut = ? AND HeureDebut = ? AND groupeClasse = ? AND NOT id = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setDate(1,dateDebut);
            pstmt.setString(2, heure);
            pstmt.setInt(3,idGroupe);
            pstmt.setInt(4,idCours);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int rowCount = rs.getInt(ACTION_1);

            if( rowCount >= 1){
                JOptionPane.showMessageDialog(null ,"Sorry!!This group has a course at this time");
            }else{
                /*if(conn == null){
                    conn = DBCPDataSource.getConnection();
                    statement = conn.createStatement();
                }else if(conn.isClosed()){
                    conn = DBCPDataSource.getConnection();
                    statement = conn.createStatement();
                }*/

                sql = "SELECT COUNT(*) AS rowCount FROM COURS WHERE DateDebut = ? AND HeureDebut = ? AND codeSalle = ? AND NOT id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setDate(1,dateDebut);
                pstmt.setString(2, heure);
                pstmt.setInt(3,idSalle);
                pstmt.setInt(4,idCours);
                rs = pstmt.executeQuery();
                rs.next();
                rowCount = rs.getInt(ACTION_1);
                if( rowCount >= 1){
                    JOptionPane.showMessageDialog(null ,"Sorry!!A coursse was plan in this room");
                }else {
                    /*if(conn == null){
                        conn = DBCPDataSource.getConnection();
                        statement = conn.createStatement();
                    }else if(conn.isClosed()){
                        conn = DBCPDataSource.getConnection();
                        statement = conn.createStatement();
                    }*/

                    sql = "SELECT COUNT(*) AS rowCount FROM COURS WHERE DateDebut = ? AND HeureDebut = ? AND idEnseignants = ? AND NOT id = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setDate(1,dateDebut);
                    pstmt.setString(2, heure);
                    pstmt.setInt(3,idEnseignant);
                    pstmt.setInt(4,idCours);
                    rs = pstmt.executeQuery();
                    rs.next();
                    rowCount = rs.getInt(ACTION_1);
                    if( rowCount >= 1){
                        JOptionPane.showMessageDialog(null ,"Sorry!!This teacher has an course at this time");
                    }else {


                        /*if (conn == null) {
                            conn = DBCPDataSource.getConnection();
                            statement = conn.createStatement();
                        } else if (conn.isClosed()) {
                            conn = DBCPDataSource.getConnection();
                            statement = conn.createStatement();
                        }*/


                        sql = "UPDATE COURS SET CODESALLE = ?, DATEDEBUT = ? , HEUREDEBUT = ? WHERE COURS.id = ?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setInt(1, idSalle);
                        pstmt.setDate(2, dateDebut);
                        pstmt.setString(3, heure);
                        pstmt.setInt(4, idCours);
                        pstmt.executeQuery();
                        JOptionPane.showMessageDialog(null, "Your course was updated!!");
                    }
                }
            }

        }catch(Exception e){
            log.log(Level.SEVERE, "Erreur de mise à jour: "+e.getMessage());

            JOptionPane.showMessageDialog(null ,"Votre cours n'a pas pu etre modifié");

        } finally {

                try {
                    conn.close();
                } catch (SQLException e) { /* Ignored */}

        }
    }



    @FXML public void onMouseClickedCancelBtn(Event e) {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
