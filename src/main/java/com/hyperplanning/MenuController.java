package com.hyperplanning;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private JFXButton absence;
    @FXML
    private JFXButton emploiTemps;
    @FXML
    private JFXButton reservation;
    String role;
    Stage stage = new Stage();

    @FXML
    private AnchorPane splashAnchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    public void presence() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("views/liste_etudiant_absent.fxml"));
            //AnchorPane frame = loader.load();

            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.setTitle("HOME");
            stage.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }

    }

    public void emploiDeTemps() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("views/home.fxml"));
            //AnchorPane frame = loader.load();

            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);


            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.setTitle("EMPLOI DU TEMPS");
            stage.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }


    }

    public void reservation() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("views/empreint_materiel.fxml"));
            //AnchorPane frame = loader.load();

            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.setTitle("RESERVATION");
            stage.show();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }



    }
}
