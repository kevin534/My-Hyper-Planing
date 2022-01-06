package com.hyperplanning;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.hyperplanning.daos.*;
import com.hyperplanning.dataSource.DBCPDataSource;
import com.hyperplanning.entities.*;
import com.hyperplanning.exceptions.DataAccessException;
import com.hyperplanning.exceptions.NotFoundException;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author way4ward
 */
public class HomeController implements Initializable {

    Connection conn;
    public String role;
    public String pre;
    PreparedStatement pstmt;
    ResultSet rs;
    Statement statement;
    ObservableList<String> A = FXCollections.observableArrayList("7:00AM","8:00AM","9:00AM","10:00AM","11:00AM","12:00PM","1:00PM","2:00PM","3:00PM");
    ObservableList<String> B = FXCollections.observableArrayList("Monday","Tuesday","Wednesday","Thursday","Friday");

    @FXML
    private ComboBox<String> time;
    @FXML
    private ComboBox<String> cday;
    @FXML
    private ComboBox<Groupe> groupeList;
    @FXML
    private ComboBox<Salle> cvenue;
    @FXML
    private ComboBox<Groupe> ccourse;
    @FXML
    private JFXTabPane AddModule;
    final ObservableList a =  FXCollections.observableArrayList();
    final ObservableList b =  FXCollections.observableArrayList();
    final ObservableList c =  FXCollections.observableArrayList();
    final ObservableList d =  FXCollections.observableArrayList();
    @FXML
    private JFXTextField Addlevel;
    @FXML
    private JFXTextField Addcourse;
    @FXML
    private JFXTextField Addvenue;
    @FXML
    private JFXTextField VenueCapacity;
    @FXML
    private JFXButton submitLevel;
    @FXML
    private JFXButton submitVenue;
    @FXML
    private JFXButton submitCourse;
    @FXML
    private JFXComboBox<?> LeveInCourse;

    @FXML
    private JFXTextField lecturer;
    @FXML
    private JFXTextField Creneau;
    @FXML
    private JFXComboBox<?> lecturercourse;
    @FXML
    private JFXButton submitVenue1;
    @FXML
    private JFXButton groupeSubmit;
    @FXML
    private StackPane stick;
    @FXML
    private ComboBox<Matiere> levelTable;
    @FXML
    private ComboBox<Enseignant> leturerTable;
    @FXML
    private TextArea mon7;
    @FXML
    private TextArea tue7;
    @FXML
    private TextArea wed7;
    @FXML
    private TextArea thur7;
    @FXML
    private TextArea fr7i;
    @FXML
    private TextArea mon8;
    @FXML
    private TextArea tue8;
    @FXML
    private TextArea wed8;
    @FXML
    private TextArea thur8;
    @FXML
    private TextArea fri8;
    @FXML
    private TextArea mon9;
    @FXML
    private TextArea tue9;
    @FXML
    private TextArea wed9;
    @FXML
    private TextArea thur9;
    @FXML
    private TextArea fri9;
    @FXML
    private TextArea mon10;
    @FXML
    private TextArea tue10;
    @FXML
    private TextArea wed10;
    @FXML
    private TextArea thur10;
    @FXML
    private TextArea fri10;
    @FXML
    private TextArea mon11;
    @FXML
    private TextArea tue11;
    @FXML
    private TextArea wed11;
    @FXML
    private TextArea thur11;
    @FXML
    private TextArea fri11;
    @FXML
    private TextArea mon12;
    @FXML
    private TextArea tue12;
    @FXML
    private TextArea wed12;
    @FXML
    private TextArea thur12;
    @FXML
    private TextArea fri12;
    @FXML
    private TextArea mon1;
    @FXML
    private TextArea tue1;
    @FXML
    private TextArea wed1;
    @FXML
    private TextArea thur1;
    @FXML
    private TextArea fri1;
    @FXML
    private TextArea mon2;
    @FXML
    private TextArea tue2;
    @FXML
    private TextArea wed2;
    @FXML
    private TextArea thur2;
    @FXML
    private TextArea fri2;
    @FXML
    private TextArea fri3;
    @FXML
    private TextArea thur3;
    @FXML
    private TextArea wed3;
    @FXML
    private TextArea tue3;
    @FXML
    private TextArea mon3;
    private String tim ;
    private Salle venue ;
    private    Groupe cos ;
    private    String day ;
    private    Enseignant lec ;
    private   Matiere lev ;
    private String date;
    private String timee;
    @FXML
    private Text datey;
    @FXML
    private Text empty;
    protected static final Properties properties = new Properties();
    Groupe groupe = Groupe.builder().build();
    Salle salle = Salle.builder().build();
    Matiere matiere= Matiere.builder().build();
    Enseignant enseignant = Enseignant.builder().build();
    String heureDebut;


    static void loadProperties(String propFileName) throws IOException {
        InputStream inputstream = App.class.getClassLoader().getResourceAsStream(propFileName);
        if (inputstream == null) throw new FileNotFoundException();
        properties.load(inputstream);

    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        try {

            loadProperties("app.properties");
            conn = DBCPDataSource.getConnection();
            statement = conn.createStatement();
            empty.setVisible(false);
            stick.setVisible(false);

            fetch();
            fetch1();
            fetch2();
            fetch3();
            timeAndDate();
            cvenue.setItems(b);
            LeveInCourse.setItems(c);
            leturerTable.setItems(d);
            lecturercourse.setItems(a);
            ccourse.setItems(a);
            groupeList.setItems(a);
            levelTable.setItems(c);
            time.setItems(A);
            cday.setItems(B);


        } catch (Exception e) {
            e.printStackTrace();
        }

        groupeSubmit.setOnAction(actionEvent ->  {

            //... do something in here.
            Groupe groupe = groupeList.getSelectionModel().getSelectedItem();
            try {
                loadData(groupe);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

    }



    public void fetch(){
        //groupes
        a.clear();
        //String sql = "select LIBELLECLASSE from GROUPES";
        try{
            ResultSet resultSet = statement.executeQuery("SELECT * from GROUPES");
            //pstmt = conn.prepareStatement(sql);
            //rs = pstmt.executeQuery();
            GroupeDao groupeDao = new GroupeDao();
            while(resultSet.next()){
                a.add(groupeDao.fromResultSet(resultSet));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }
    }

    public void fetch1(){
        //salles
        b.clear();
        //String sql = "select * from SALLES";
         try{
             ResultSet resultSet = statement.executeQuery("SELECT * from SALLES");
             //pstmt = conn.prepareStatement(sql);
             SalleDao salleDao = new SalleDao();
            //rs = pstmt.executeQuery();
            while(resultSet.next()){

                b.add(salleDao.fromResultSet(resultSet));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }
    }
    public void fetch2(){
        //matiere
        c.clear();
        //String sql = "Select LIBELLEMATIERE from MATIERES";
        try{
            ResultSet resultSet = statement.executeQuery("SELECT * from MATIERES");
            // pstmt = conn.prepareStatement(sql);
            // rs = pstmt.executeQuery();
            MatiereDao matiereDao = new MatiereDao();
            while(resultSet.next()){
                c.add(matiereDao.fromResultSet(resultSet));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }
    }
    public void fetch3(){
        d.clear();
        //String sql = "Select UTILISATEURS.NOM FROM UTILISATEURS INNER JOIN ENSEIGNANTS " +
               //  "ON UTILISATEURS.ID=ENSEIGNANTS.ID";
        try{
            ResultSet resultSet = statement.executeQuery("SELECT * from ENSEIGNANTS");
            //pstmt = conn.prepareStatement(sql);
            //rs = pstmt.executeQuery();
            EnseignantDao enseignantDao = new EnseignantDao();
            while(resultSet.next()){
                d.add(enseignantDao.fromResultSet(resultSet));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }
    }

    @FXML
    private void Addcourse(ActionEvent event) {
        if(Addcourse.getText().isEmpty()){
            emptyMessage();
        }else{
            String sql = "INSERT INTO COURSE(NAME,LEVEL) VALUES (?,?)";
            try{
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1 , Addcourse.getText());
                pstmt.setString(2 , (String) LeveInCourse.getSelectionModel().getSelectedItem());
                pstmt.execute();
                fetch();
                Addcourse.setText("");
                fetch1();
                msg();
                fetch2();
                fetch3();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null , e);
            }
        }
    }

    @FXML
    private void submitLevel(ActionEvent event) {
        if(Addlevel.getText().isEmpty()){
            emptyMessage();
        }else{
            String sql = "INSERT INTO LEVEL(NAME) VALUES (?)";
            try{
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1 , Addlevel.getText());
                pstmt.execute();
                Addlevel.setText("");
                fetch();
                fetch1();
                fetch2();
                fetch3();
                msg();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null , e);
            }
        }
    }

    @FXML
    private void submitVenue(ActionEvent event) {
        if(Addvenue.getText().isEmpty() || VenueCapacity.getText().isEmpty()){
            emptyMessage();
        }else{
            String sql = "INSERT INTO VENUE(NAME,CAPACITY) VALUES (?,?)";
            try{
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1 , Addvenue.getText());
                pstmt.setString(2 , VenueCapacity.getText());

                pstmt.execute();
                Addvenue.setText("");
                VenueCapacity.setText("");
                msg();
                fetch();
                fetch1();
                fetch2();
                fetch3();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null , e);
            }
        }
    }

    private void venuecos(String Venue, String Course){
        stick.setVisible(true);
        JFXDialogLayout content =  new JFXDialogLayout();
        content.setHeading(new Text("Error")); // Header of the Dialog
        content.setBody(new Text("Room "+Venue+" Has been allocated to \n"+Course)); // Text in the dialog
        JFXDialog dialog = new JFXDialog(stick, content,JFXDialog.DialogTransition.CENTER);
        JFXButton btn = new JFXButton("Close"); // Button to close Dialog

        btn.setOnAction(new EventHandler <ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
                stick.setVisible(false);
            }

        });
        content.setActions(btn);

        dialog.show();
    }

    private void cos(String Course){
        stick.setVisible(true);
        JFXDialogLayout content =  new JFXDialogLayout();
        content.setHeading(new Text("Error")); // Header of the Dialog
        content.setBody(new Text(Course+" has been allocated")); // Text in the dialog
        JFXDialog dialog = new JFXDialog(stick, content,JFXDialog.DialogTransition.CENTER);
        JFXButton btn = new JFXButton("Close"); // Button to close Dialog

        btn.setOnAction(new EventHandler <ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
                stick.setVisible(false);
            }

        });
        content.setActions(btn);

        dialog.show();
    }


    private void Level(String Level){
        stick.setVisible(true);
        JFXDialogLayout content =  new JFXDialogLayout();
        content.setHeading(new Text("Error")); // Header of the Dialog
        content.setBody(new Text(Level+" will be Busy")); // Text in the dialog
        JFXDialog dialog = new JFXDialog(stick, content,JFXDialog.DialogTransition.CENTER);
        JFXButton btn = new JFXButton("Close"); // Button to close Dialog

        btn.setOnAction(new EventHandler <ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
                stick.setVisible(false);
            }

        });
        content.setActions(btn);

        dialog.show();
    }


    private void lec(String Lecturer){
        stick.setVisible(true);
        JFXDialogLayout content =  new JFXDialogLayout();
        content.setHeading(new Text("Error")); // Header of the Dialog
        content.setBody(new Text(Lecturer+" will be Busy")); // Text in the dialog
        JFXDialog dialog = new JFXDialog(stick, content,JFXDialog.DialogTransition.CENTER);
        JFXButton btn = new JFXButton("Close"); // Button to close Dialog

        btn.setOnAction(new EventHandler <ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
                stick.setVisible(false);
            }

        });
        content.setActions(btn);

        dialog.show();
    }

    public void check(TextArea baba) {

        String test = venue.toString();
        String test2 = lec.toString();
        String test3 = lev.toString();
        if(baba.getText().contains(test)){
            venuecos(String.valueOf(venue), String.valueOf(cos));
        }else if(baba.getText().contains(test2)){
            lec(String.valueOf(lec));
        }else if(baba.getText().contains(test3)){
            Level(String.valueOf(lev));
        }else{

            if(baba.getText().isEmpty()){


                //baba.setText(lev+"\n"+venue+"\n"+lec+"\n"+cos);
                enseignant = leturerTable.getSelectionModel().getSelectedItem();
                salle = cvenue.getSelectionModel().getSelectedItem();
                groupe = ccourse.getSelectionModel().getSelectedItem();
                matiere = levelTable.getSelectionModel().getSelectedItem();
                heureDebut = time.getSelectionModel().getSelectedItem();
                //Date datedebut = datey;
                String texte = Creneau.getText();
                Date dateDebut = Date.valueOf(texte);

                try {
                    String sql = "INSERT INTO COURS(IDENSEIGNANTS,CODESALLE,GROUPECLASSE,CODEMATIERE, DATEDEBUT, DATEFIN,HEUREDEBUT) VALUES (?,?,?,?,?,?,?)";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, enseignant.getId());
                    pstmt.setInt(2, salle.getCodeSalle());
                    pstmt.setInt(3, groupe.getGroupeClasse());
                    pstmt.setInt(4, matiere.getCodeMatiere());
                    pstmt.setDate(5, dateDebut);
                    pstmt.setDate(6, dateDebut);
                    pstmt.setString(7, heureDebut);
                    pstmt.execute();


                } catch(Exception e){
                JOptionPane.showMessageDialog(null , e);
                }
            }else{
                baba.setText(baba.getText()+"\n\n______________________\n"+String.valueOf(lev)+"\n"+String.valueOf(venue)+"\n"+String.valueOf(lec)+"\n"+String.valueOf(cos));
            }
        }
    }
    @FXML
    private void initiate(ActionEvent event) {

        tim = time.getSelectionModel().getSelectedItem();//heure
        venue = cvenue.getSelectionModel().getSelectedItem(); //salle
        cos = ccourse.getSelectionModel().getSelectedItem();//groupe
        day = cday.getSelectionModel().getSelectedItem();//jour
        lec = leturerTable.getSelectionModel().getSelectedItem();//enseignant
        lev = levelTable.getSelectionModel().getSelectedItem();//matiere


        //MONDAY
        if(day == "Monday" && tim == "7:00AM"){
            check(mon7);
        }
        if(day == "Monday" && tim == "8:00AM"){
            check(mon8);

        }
        if(day == "Monday" && tim == "9:00AM"){
            check(mon9);
        }
        if(day == "Monday" && tim == "10:00AM"){
            check(mon10);
        }
        if(day == "Monday" && tim == "11:00AM"){
            check(mon11);
        }
        if(day == "Monday" && tim == "12:00PM"){
            check(mon12);
        }
        if(day == "Monday" && tim == "1:00PM"){
            check(mon1);
        }
        if(day == "Monday" && tim == "2:00PM"){
            check(mon2);
        }
        if(day == "Monday" && tim == "3:00PM"){
            check(mon3);
        }
        //TUESDAY
        if(day == "Tuesday" && tim == "7:00AM"){
            check(tue7);
        }
        if(day == "Tuesday" && tim == "8:00AM"){
            check(tue8);
        }
        if(day == "Tuesday" && tim == "9:00AM"){
            check(tue9);
        }
        if(day == "Tuesday" && tim == "10:00AM"){
            check(tue10);
        }
        if(day == "Tuesday" && tim == "11:00AM"){
            check(tue11);
        }
        if(day == "Tuesday" && tim == "12:00PM"){
            check(tue12);
        }
        if(day == "Tuesday" && tim == "1:00PM"){
            check(tue1);
        }
        if(day == "Tuesday" && tim == "2:00PM"){
            check(tue2);
        }
        if(day == "Tuesday" && tim == "3:00PM"){
            check(tue3);
        }
        //  WEDNESDAY
        if(day == "Wednesday" && tim == "7:00AM"){
            check(wed7);
        }
        if(day == "Wednesday" && tim == "8:00AM"){
            check(wed8);
        }
        if(day == "Wednesday" && tim == "9:00AM"){
            check(wed9);
        }
        if(day == "Wednesday" && tim == "10:00AM"){
            check(wed10);
        }
        if(day == "Wednesday" && tim == "11:00AM"){
            check(wed11);
        }
        if(day == "Wednesday" && tim == "12:00PM"){
            check(wed12);
        }
        if(day == "Wednesday" && tim == "1:00PM"){
            check(wed1);
        }
        if(day == "Wednesday" && tim == "2:00PM"){
            check(wed2);
        }
        if(day == "Wednesday" && tim == "3:00PM"){
            check(wed3);
        }

        //THURSDAY
        if(day == "Thursday" && tim == "7:00AM"){
            check(thur7);
        }
        if(day == "Thursday" && tim == "8:00AM"){
            check(thur8);
        }
        if(day == "Thursday" && tim == "9:00AM"){
            check(thur9);
        }
        if(day == "Thursday" && tim == "10:00AM"){
            check(thur10);
        }
        if(day == "Thursday" && tim == "11:00AM"){
            check(thur11);
        }
        if(day == "Thursday" && tim == "12:00PM"){
            check(thur12);
        }
        if(day == "Thursday" && tim == "1:00PM"){
            check(thur1);
        }
        if(day == "Thursday" && tim == "2:00PM"){
            check(thur2);
        }
        if(day == "Thursday" && tim == "3:00PM"){
            check(thur3);
        }

        //FRIDAY
        if(day == "Friday" && tim == "7:00AM"){
            check(fr7i);
        }
        if(day == "Friday" && tim == "8:00AM"){
            check(fri8);
        }
        if(day == "Friday" && tim == "9:00AM"){
            check(fri9);
        }
        if(day == "Friday" && tim == "10:00AM"){
            check(fri10);
        }
        if(day == "Friday" && tim == "11:00AM"){
            check(fri11);
        }
        if(day == "Friday" && tim == "12:00PM"){
            check(fri12);
        }
        if(day == "Friday" && tim == "1:00PM"){
            check(fri1);
        }
        if(day == "Friday" && tim == "2:00PM"){
            check(fri2);
        }
        if(day == "Friday" && tim == "3:00PM"){
            check(fri3);
        }

    }


    public void timeAndDate(){
        Calendar  cal = new GregorianCalendar();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        date = (day +":" +(month+1)+ ":"+ year);

        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);

        if(hour == 0){
            hour = hour + 12;
        }
        timee = (hour +":" +minute);

        datey.setText(date);


    }
    @FXML
    private void submitLecturer(ActionEvent event) {
        if(lecturer.getText().isEmpty()){
            emptyMessage();
        }else{
            String sql = "INSERT INTO LECTURER(NAME,LEVEL) VALUES (?,?)";
            try{
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1 , lecturer.getText());
                pstmt.setString(2 , (String) lecturercourse.getSelectionModel().getSelectedItem());
                pstmt.execute();
                msg();
                lecturer.setText("");
                fetch();
                fetch1();
                fetch2();
                fetch3();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null , e);
            }
        }
    }
    public void loadData(Groupe groupe)throws SQLException{
        String sql = "SELECT * FROM COURS WHERE GROUPECLASSE = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, groupe.getGroupeClasse());
        ResultSet resultSet = pstmt.executeQuery();
        String matiere = null;
        String enseignant = null;
        String salle = null;
        String days = null;

        while(resultSet.next()){
            String heureDebut = resultSet.getString("heureDebut");

            Date dat = resultSet.getDate("dateDebut");
            DayOfWeek dayOfWeek = dat.toLocalDate().getDayOfWeek();
            days = String.valueOf(dayOfWeek);


            sql = "SELECT * FROM MATIERES WHERE ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, resultSet.getInt("codeMatiere"));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                matiere = rs.getString("libelleMatiere");
            }
            sql = "SELECT * FROM SALLES WHERE ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, resultSet.getInt("codeSalle"));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                salle = rs.getString("libelleSalle");
            }

            sql = "Select * FROM UTILISATEURS WHERE ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, resultSet.getInt("idEnseignants"));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                enseignant = rs.getString("nom") +" " +rs.getString("prenoms");
            }

            load(matiere,salle,enseignant,days,heureDebut);

        }
    }

    public void load(String matiere, String salle, String enseignant, String day, String tim ) throws SQLException {

        //MONDAY
        if(day == "MONDAY" && tim.equals("7:00AM")){

            mon7.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "MONDAY" && tim.equals("8:00AM")){
            mon8.setText(matiere+"\n"+salle+"\n"+enseignant);

        }
        else if(day == "MONDAY" && tim.equals("9:00AM")){
            mon9.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "MONDAY" && tim.equals("10:00AM")){
            mon10.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "MONDAY" && tim.equals("11:00AM")){
            mon11   .setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "MONDAY" && tim.equals("12:00PM")){
            mon12.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "MONDAY" && tim.equals("1:00PM")){
            mon1.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "MONDAY" && tim.equals("2:00PM")){
            mon2.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "MONDAY" && tim.equals("3:00PM")){
            check(mon3);
        }
        //TUESDAY
        else if(day == "TUESDAY" &&  tim.equals("7:00AM")){
            tue7.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "TUESDAY" && tim.equals("8:00AM")){
            tue8.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "TUESDAY" && tim.equals("9:00AM")){
            tue9.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "TUESDAY" && tim.equals("10:00AM")){
            tue10.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "TUESDAY" && tim.equals("11:00AM")){
            tue11.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "TUESDAY" && tim.equals("12:00PM")){
            tue12.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "TUESDAY" && tim.equals("1:00PM")){
            tue1.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "TUESDAY" && tim.equals("2:00PM")){
            tue2.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "TUESDAY" && tim.equals("3:00PM")){
            tue3.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        //  WEDNESDAY
        else if(day == "WEDNESDAY" && tim.equals("7:00AM")){
            wed7.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "WEDNESDAY" && tim.equals("8:00AM")){
            wed8.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "WEDNESDAY" && tim.equals("9:00AM")){
            wed9.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "WEDNESDAY" && tim.equals("10:00AM")){
            wed10.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "WEDNESDAY" && tim.equals("11:00AM")){
            wed11.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "WEDNESDAY" && tim.equals("12:00PM")){
            wed12.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "WEDNESDAY" && tim.equals("1:00PM")){
            wed1.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "WEDNESDAY" && tim.equals("2:00PM")){
            wed2.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "WEDNESDAY" && tim.equals("3:00PM")){
            wed3.setText(matiere+"\n"+salle+"\n"+enseignant);
        }

        //THURSDAY
        else if(day == "THURSDAY" && tim.equals("7:00AM")){
            thur7.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "THURSDAY" && tim.equals("8:00AM")){
            thur8.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "THURSDAY" && tim.equals("9:00AM")){
            thur9.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "THURSDAY" && tim.equals("10:00AM")){
            thur10.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "THURSDAY" && tim.equals("11:00AM")){
            thur11.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "THURSDAY" && tim.equals("12:00PM")){
            thur12.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "THURSDAY" && tim.equals("1:00PM")){
            thur1.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "THURSDAY" && tim.equals("2:00PM")){
            thur2.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "THURSDAY" && tim.equals("3:00PM")){
            thur3.setText(matiere+"\n"+salle+"\n"+enseignant);
        }

        //FRIDAY
        else if(day == "FRIDAY" && tim.equals("7:00AM")){
            fr7i.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "FRIDAY" && tim.equals("8:00AM")){
            fri8.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "FRIDAY" && tim.equals("9:00AM")){
            fri9.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "FRIDAY" && tim.equals("10:00AM")){
            fri10.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "FRIDAY" && tim.equals("11:00AM")){
            fri11.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "FRIDAY" && tim.equals("12:00PM")){
            fri12.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "FRIDAY" && tim.equals("1:00PM")){
            fri1.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "FRIDAY" && tim.equals("2:00PM")){
            fri2.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
        else if(day == "FRIDAY" && tim.equals("3:00PM")){
            fri3.setText(matiere+"\n"+salle+"\n"+enseignant);
        }
    }

    @FXML
    private void emptyAll(ActionEvent event) {
        String sql = "DELETE FROM COURSE";
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }

        String sql2 = "DELETE FROM LECTURER";
        try{
            pstmt = conn.prepareStatement(sql2);
            pstmt.execute();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }

        String sql3 = "DELETE FROM LEVEL";
        try{
            pstmt = conn.prepareStatement(sql3);
            pstmt.execute();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }

        String sql4 = "DELETE FROM VENUE";
        try{
            pstmt = conn.prepareStatement(sql4);
            pstmt.execute();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }
        msg();
    }

    private void msg(){
        Image img = new Image("/images/owk.png");
              /*Notifications notificationBuilder = Notifications.create()
               .title("Success")
               .text("Operation Succesful")
               .graphic(new ImageView(img))
               .hideAfter(Duration.seconds(3))
               .position(Pos.BOTTOM_RIGHT);
               notificationBuilder.show();*/
    }
    private void emptyMessage(){
        Image img = new Image("/images/owk.png");
         /*Notifications notificationBuilder = Notifications.create()
               .title("Error")
               .text("You cant Submit and EmptyField")
               .graphic(new ImageView(img))
               .hideAfter(Duration.seconds(7))
               .position(Pos.BOTTOM_RIGHT);
               notificationBuilder.show();*/
    }
    @FXML
    private void hide(MouseEvent event) {
        empty.setVisible(false);
    }

    @FXML
    private void show(MouseEvent event) {
        empty.setVisible(true);
    }

}
