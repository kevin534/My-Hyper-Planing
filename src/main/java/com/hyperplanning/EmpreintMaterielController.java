package com.hyperplanning;
import com.hyperplanning.dataSource.DBCPDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.extern.java.Log;

import java.net.URL;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;


@Log
public class EmpreintMaterielController implements Initializable {
    int id;
    @FXML
    private DatePicker datePicker_empreint;
    @FXML
    private DatePicker datePicker_retour;
    @FXML
    private ComboBox<String> liste_materiel;Connection connection;
    @FXML
    private Button id_button_materiel;

    public EmpreintMaterielController(int id){this.id = id;}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connection = DBCPDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        id_button_materiel.setOnAction(actionEvent ->  {
            //... do something in here.
            try {
                SendMaterial();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

        // an observable list of material
        ObservableList<String> material = FXCollections.observableArrayList();
        material.add("Projecteur");
        material.add("Moniteur");
        material.add("Camera");
        material.add("Clavier");
        material.add("Mouse");
        material.add("Livre");
        liste_materiel.setItems(material);
    }
    public void SendMaterial() throws SQLException {
       String item = liste_materiel.getValue();
        LocalDate localDate_from = datePicker_empreint.getValue();
        LocalDate localDate_to = datePicker_retour.getValue();
        String tmp = localDate_from.getDayOfMonth() +"-"+localDate_from.getMonthValue()+"-"+localDate_from.getYear();

        long nbreHeure = Date.valueOf(localDate_to).getTime() - Date.valueOf(localDate_from).getTime();
        float res = (nbreHeure / (1000*60*60*24));

        if(Date.valueOf(localDate_from).getTime() < Date.valueOf(localDate_to).getTime()) {

            // control if time of reservation of an materail is not superior of 30 days
            if (res > 30) {
                JOptionPane.showMessageDialog(null, "Your reservation can't exceed 30 days" + "\n" + "Yours exceed for: " + res + " days");
            } else {
                String query;
                query = "SELECT COUNT(*) AS rowCount FROM RESERVATION WHERE materiel = ? AND dateFin >= ? AND DateDebut <= ?";

                try( PreparedStatement preparedStatement1 = connection.prepareStatement(query);) {


                    // select all reservation that the dateFin >= localDate_from AND dateDebut <= localdate_to

                    preparedStatement1.setString(1, item);
                    preparedStatement1.setDate(2, Date.valueOf(localDate_from));
                    preparedStatement1.setDate(3, Date.valueOf(localDate_to));
                    ResultSet rs = preparedStatement1.executeQuery();
                    rs.next();
                    // get count row
                    int rowCount = rs.getInt("rowCount");
                    // to check if the reservation exist
                    if (rowCount >= 1) {
                        JOptionPane.showMessageDialog(null, "Sorry! A reservation was plan at this date");
                    } else {
                        query = "INSERT INTO RESERVATION(MATERIEL,DATEDEBUT,DATEFIN,IDENSEIGNANT) VALUES (?,?,?,?)";

                        try ( PreparedStatement preparedStatement2 = connection.prepareStatement(query);){

                            preparedStatement2.setString(1, item);
                            preparedStatement2.setString(2, String.valueOf(localDate_from));
                            preparedStatement2.setString(3, String.valueOf(localDate_to));
                            preparedStatement2.setInt(4, id);
                            preparedStatement2.executeQuery();
                            JOptionPane.showMessageDialog(null, "Your reservation is done!");

                        }catch (SQLException e){
                            log.log(Level.SEVERE, "Erreur: "+e.getMessage());
                        }
                    }
                } catch (SQLException e) {
                    log.log(Level.SEVERE, "Erreur: "+e.getMessage());
                }
            }
        }

        else {
            JOptionPane.showMessageDialog(null, "Check, your return date must not be superior than your borrow date"+"\n"+"Thanks !!!");

        }

    }
}