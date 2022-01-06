package com.hyperplanning;

import com.hyperplanning.daos.*;
import com.hyperplanning.dataSource.DBCPDataSource;
import com.hyperplanning.entities.*;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Generated;
import lombok.extern.java.Log;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Log
public class App {
    protected static final Properties properties = new Properties();

    static void loadProperties(String propFileName) throws IOException {
        InputStream inputstream = App.class.getClassLoader().getResourceAsStream(propFileName);
        if (inputstream == null) throw new FileNotFoundException();
        properties.load(inputstream);
        String query = "INSERT INTO COURS(IDENSEIGNANTS,CODESALLE,GROUPECLASSE,CODEMATIERE, DATEDEBUT, DATEFIN) VALUES (?,?,?,?,?,?)";

    }


    public static void main(String[] args) throws IOException, NotFoundException, DataAccessException, SQLException {
        loadProperties("app.properties");
        //test unitaires
        Statement statement = DBCPDataSource.getConnection().createStatement();
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        utilisateurDao.persist("DJESSOU","MAHUGNON","regisdjessou2@gmail.com","abcd","ENSEIGNANT");
        //log.info(" utilisateurDao persisted " + utilisateurDao);

        Enseignant enseignant = Enseignant.builder().id(3).build();
        Salle salle = Salle.builder().codeSalle(1).build();
        Matiere matiere = Matiere.builder().codeMatiere(1).build();
        Groupe groupe = Groupe.builder().groupeClasse(1).build();
        CoursDao coursDao = new CoursDao();
        Date dateDebut = new Date(2020,05,25);
        Date dateFin = new Date(2020,05,25);
        coursDao.persist(enseignant,salle,groupe,matiere,dateDebut,dateFin,"");
        log.info(" coursDAO persisted " + utilisateurDao);

        /*try {

            Statement statement = DBCPDataSource.getConnection().createStatement();

            //problème de récuperation des elements
            ObservableList<Enseignant> ensei = FXCollections.observableArrayList();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM enseignants WHERE ID = 2");
            EnseignantDao enseignantDao = new EnseignantDao();

            while(resultSet.next()) {
                ensei.add(enseignantDao.fromResultSet(resultSet));

            }
            ObservableList<Salle> sall = FXCollections.observableArrayList();
            resultSet = statement.executeQuery("SELECT * FROM SALLES");
            SalleDao salleDao = new SalleDao();

            while( resultSet.next()) {
                sall.add(salleDao.fromResultSet(resultSet));
            }

            ObservableList<Matiere> matiere = FXCollections.observableArrayList();
            resultSet = statement.executeQuery("SELECT * FROM matieres");
            MatiereDao matiereDao = new MatiereDao();

            while( resultSet.next()) {
                matiere.add(matiereDao.fromResultSet(resultSet));
            }

            ObservableList<Groupe> groupe = FXCollections.observableArrayList();
            resultSet = statement.executeQuery("SELECT * FROM groupes");
            GroupeDao groupeDao = new GroupeDao();

            while( resultSet.next()) {
                groupe.add(groupeDao.fromResultSet(resultSet));
            }

            Date Datedebut = Date.valueOf("2015-03-31");
            ObservableList<Date> dateDebut = FXCollections.observableArrayList(Datedebut);

            ObservableList<Date> dateFin = FXCollections.observableArrayList(Datedebut);
            // passage de l'objet a chaque id label
            timeAndDate();
            liste_enseignant.setItems(ensei);
            liste_salle.setItems(sall);
            liste_dateDebut.setItems(dateDebut);
            liste_matiere.setItems(matiere);
            liste_dateFin.setItems(dateFin);
            liste_groupe.setItems(groupe);


            time.setItems(A);
            cday.setItems(B);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        bsave.setOnAction(event);*/


    }

    public static String getProperty(String s) {
        return properties.getProperty(s);
    }
}