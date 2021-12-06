package com.hyperplanning;

import com.hyperplanning.entities.Enseignant;
import com.hyperplanning.entities.Etudiant;
import com.hyperplanning.entities.Utilisateur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        //Utilisation builder
        //Enseignant enseignant = Enseignant.builder().build();
    }

    public static void main(String[] args) {
        launch();
    }
}