package com.hyperplanning;



import com.hyperplanning.dataSource.DBCPDataSource;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author way4ward
 */
public class LoginController implements Initializable {
    String role;
    int id;
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private JFXButton lgbtn;
    @FXML
    private AnchorPane splashAnchorPane;
    /**
     * Initializes the controller class.
     */
    protected static final Properties properties = new Properties();

    static void loadProperties(String propFileName) throws IOException {
        InputStream inputstream = LoginController.class.getClassLoader().getResourceAsStream(propFileName);
        if (inputstream == null) throw new FileNotFoundException();
        properties.load(inputstream);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //mettre la connection de la BD
        try {
            loadProperties("app.properties");
            conn = DBCPDataSource.getConnection();
            lgbtn.setOnAction(e-> login());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //test unitaires

    }



    private void login() {

        if(username.getText().isEmpty() || password.getText().isEmpty()){
            emptyMessage();
        }else{
              String sqll = "SELECT * FROM UTILISATEURS WHERE EMAIL=? AND PASSWORD=?";
            try{
                pstmt = conn.prepareStatement(sqll);
                pstmt.setString(1 , username.getText());
                pstmt.setString(2 , password.getText());
                rs = pstmt.executeQuery();
                if(rs.next()){
                    role = rs.getString("role");
                    id = rs.getInt("id");

                    Stage stage = new Stage();
                    HomeController homeController = new HomeController(role,id);
                    FXMLLoader loader;

                        loader = new FXMLLoader(getClass().getResource("views/home.fxml"));


                    loader.setController(homeController);

                    Parent root = loader.load();

                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.centerOnScreen();
                    stage.setTitle("HOME");
                    stage.show();
                    splashAnchorPane.getScene().getWindow().hide();



                }else{
                    Alert alert  = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("WARNING");
                    alert.setHeaderText(null);
                    alert.setContentText("INVALID DETAILS");
                    alert.showAndWait();
                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(null , e);
            }

        }
    }


    private void emptyMessage(){
        Image img = new Image("@images/owk.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Error")
                .text("You cant Submit and EmptyField")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(7))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.show();
    }
    @FXML
    private void closeme(MouseEvent event) {
        System.exit(0);
    }
}
