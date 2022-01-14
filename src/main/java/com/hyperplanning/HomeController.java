package com.hyperplanning;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.hyperplanning.daos.*;
import com.hyperplanning.dataSource.DBCPDataSource;
import com.hyperplanning.entities.*;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.java.Log;

import javax.swing.*;
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

/**
 * FXML Controller class
 *
 * @author Registe
 */

@Log
public class HomeController implements Initializable {
    Map<Button,Integer> map = new HashMap<>();
    Connection conn;
    int id;
    int idGroupeEtudiant;
    public String role;
    PreparedStatement pstmt;
    Statement statement;
    ObservableList<String> time_of_calendar = FXCollections.observableArrayList("7:00AM","8:00AM","9:00AM","10:00AM","11:00AM","12:00PM","1:00PM","2:00PM","3:00PM");
    ObservableList<String> day_of_calendar = FXCollections.observableArrayList("Monday","Tuesday","Wednesday","Thursday","Friday");

    @FXML
    private Menu planning;
    @FXML
    private DatePicker Creneau;
    @FXML
    private Menu presence;
    @FXML
    private Menu reservation;
    @FXML
    private MenuItem makeReservation;
    @FXML
    private MenuItem makePresence;
    @FXML
    private MenuItem reservationList;
    @FXML
    private MenuItem presenceList;
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
    private ComboBox<String> timeAction;
    @FXML
    private ComboBox<String> cdayAction;
    @FXML
    private ComboBox<Salle> cvenueAction;
    @FXML
    private ComboBox<Groupe> ccourseAction;
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
    private JFXComboBox<?> LeveInCourse;
    @FXML
    private JFXTabPane AddModule;

    @FXML
    private JFXTextField lecturer;
    @FXML
    private JFXComboBox<?> lecturercourse;
    @FXML
    private JFXButton groupeSubmit;
    @FXML
    private StackPane stick;
    @FXML
    private ComboBox<Matiere> levelTable;
    @FXML
    private ComboBox<Enseignant> leturerTable;
    @FXML
    private Button mon7;
    @FXML
    private Button tue7;
    @FXML
    private Button wed7;
    @FXML
    private Button thur7;
    @FXML
    private Button fr7i;
    @FXML
    private Button mon8;
    @FXML
    private Button tue8;
    @FXML
    private Button wed8;
    @FXML
    private Button thur8;
    @FXML
    private Button fri8;
    @FXML
    private Button mon9;
    @FXML
    private Button tue9;
    @FXML
    private Button wed9;
    @FXML
    private Button thur9;
    @FXML
    private Button fri9;
    @FXML
    private Button mon10;
    @FXML
    private Button tue10;
    @FXML
    private Button wed10;
    @FXML
    private Button thur10;
    @FXML
    private Button fri10;
    @FXML
    private Button mon11;
    @FXML
    private Button tue11;
    @FXML
    private Button wed11;
    @FXML
    private Button thur11;
    @FXML
    private Button fri11;
    @FXML
    private Button mon12;
    @FXML
    private Button tue12;
    @FXML
    private Button wed12;
    @FXML
    private Button thur12;
    @FXML
    private Button fri12;
    @FXML
    private Button mon1;
    @FXML
    private Button tue1;
    @FXML
    private Button wed1;
    @FXML
    private Button thur1;
    @FXML
    private Button fri1;
    @FXML
    private Button mon2;
    @FXML
    private Button tue2;
    @FXML
    private Button wed2;
    @FXML
    private Button thur2;
    @FXML
    private Button fri2;
    @FXML
    private Button fri3;
    @FXML
    private Button thur3;
    @FXML
    private Button wed3;
    @FXML
    private Button tue3;
    @FXML
    private Button mon3;
    private TextField idCours;
    @FXML
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
    private JFXButton submitCours;
    @FXML
    private Text empty;
    public int idCoursInite;
    protected static final Properties properties = new Properties();
    Groupe groupe = Groupe.builder().build();
    Salle salle = Salle.builder().build();
    Matiere matiere= Matiere.builder().build();
    Enseignant enseignant = Enseignant.builder().build();
    Stage stage ;


    /**
     * Une fonction pour charger les propriétes de connexion depuis les proprietes
     * @param propFileName
     * @throws IOException
     */
    static void loadProperties(String propFileName) throws IOException {
        InputStream inputstream = HomeController.class.getClassLoader().getResourceAsStream(propFileName);
        if (inputstream == null) throw new FileNotFoundException();
        properties.load(inputstream);

    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        try {

            if(role.equals("RESPONSABLE")){
                presence.setVisible(false);
                reservation.setVisible(false);
            }

            if(role.equals("ETUDIANT")){
                makePresence.setVisible(false);
                reservation.setVisible(false);
            }

            if(role.equals("ENSEIGNANT")){
                presenceList.setVisible(false);
            }


            if(!role.equals("RESPONSABLE")){
                groupeSubmit.setVisible(false);
                groupeList.setVisible(false);
                AddModule.setVisible(false);
                DisableButton(mon1, mon2, mon3, mon7, mon8, mon9, mon10, mon11, mon12);
                DisableButton(tue1, tue2, tue3, tue7, tue8, tue9, tue10, tue11, tue12);
                DisableButton(wed1, wed2, wed3, wed7, wed8, wed9, wed10, wed11, wed12);
                DisableButton(thur1, thur2, thur3, thur7, thur8, thur9, thur10, thur11, thur12);
                DisableButton(fri1, fri2, fri3, fr7i, fri8, fri9, fri10, fri11, fri12);
            }

            loadProperties("app.properties");
            conn = DBCPDataSource.getConnection();
            statement = conn.createStatement();

            empty.setVisible(false);
            stick.setVisible(false);


            fetch();
            fetch1();
            fetch2();
            fetch3();
            fetch5();

            timeAndDate();
            cvenue.setItems(b);
            LeveInCourse.setItems(c);
            leturerTable.setItems(d);
            lecturercourse.setItems(a);
            ccourse.setItems(a);
            groupeList.setItems(a);
            levelTable.setItems(c);
            time.setItems(time_of_calendar);
            //cday.setItems(day_of_calendar);

            if(!role.equals("RESPONSABLE"))
                loadData(groupe);




        } catch (Exception e) {
            e.printStackTrace();
        }

        groupeSubmit.setOnAction(actionEvent ->  {
            //... do something in here.
            Groupe groupe2 = groupeList.getSelectionModel().getSelectedItem();
            loadData(groupe2);

        });
        submitCours.setOnAction(actionEvent ->  {
            //... do something in here.
           insertCourse();
           Groupe groupe2 = groupeList.getSelectionModel().getSelectedItem();
           loadData(groupe2);

        });

        makePresence.setOnAction(actionEvent -> {
            presence();
        });


        presenceList.setOnAction(actionEvent -> {
            listPresence();
        });
        makeReservation.setOnAction(actionEvent -> {
            faireReservation();
        });

        reservationList.setOnAction(actionEvent -> {
            listReservation();
        });

        MakeAction(mon1, mon2, mon3, mon7, mon8, mon9, mon10, mon11, mon12);
        MakeAction(tue8, tue9, tue10, tue11, tue12, tue1, tue2, tue3, tue7);
        MakeAction(wed8, wed9, wed10, wed11, wed12, wed1, wed2, wed3, wed7);
        MakeAction(thur1, thur2, thur3, thur7, thur8, thur9, thur10, thur11, thur12);
        MakeAction(fri1, fri2, fri3, fr7i,fri8, fri9, fri10,fri11,fri12);


    }

    private void DisableButton(Button mon1, Button mon2, Button mon3, Button mon7, Button mon8, Button mon9, Button mon10, Button mon11, Button mon12) {
        mon1.setDisable(true);
        mon2.setDisable(true);
        mon3.setDisable(true);
        mon7.setDisable(true);
        mon8.setDisable(true);
        mon9.setDisable(true);
        mon10.setDisable(true);
        mon11.setDisable(true);
        mon12.setDisable(true);
    }

    public HomeController(String role, int id){ this.role = role;this.id = id;}


    private void MakeAction(Button mon1, Button mon2, Button mon3, Button mon7, Button mon8, Button mon9, Button mon10, Button mon11, Button mon12) {
        actionButton(mon1);
        actionButton(mon2);
        actionButton(mon3);
        actionButton(mon7);
        actionButton(mon8);
        actionButton(mon9);
        actionButton(mon10);
        actionButton(mon11);
        actionButton(mon12);

    }

    void actionButton(Button button){
        button.setOnAction(actionEvent ->  {
            //... do something in here.


            clickShow(actionEvent,button);



        });
    }




    /**
     * Une fonction qui recupere les differents groupes depuis la BD
     */
    public void fetch(){
        //groupes
        a.clear();
        //String sql = "select LIBELLECLASSE from GROUPES";
        try(GroupeDao groupeDao = new GroupeDao();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from GROUPES");){

            while(resultSet.next()){
                a.add(groupeDao.fromResultSet(resultSet));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }

    }

    /**
     * Une fonction qui recupere les differents salles depuis la BD
     */
    public void fetch1(){
        //salles
        b.clear();
        //String sql = "select * from SALLES";
         try( SalleDao salleDao = new SalleDao();
             Statement statement = conn.createStatement();
              ResultSet resultSet = statement.executeQuery("SELECT * from SALLES");){


             //pstmt = conn.prepareStatement(sql);

            while(resultSet.next()){

                b.add(salleDao.fromResultSet(resultSet));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }
    }

    /**
     * Une fonction qui recupere les differents matieres depuis la BD
     */
    public void fetch2(){
        //matiere
        c.clear();
        //String sql = "Select LIBELLEMATIERE from MATIERES";
        try(
               MatiereDao matiereDao = new MatiereDao();
                Statement statement = conn.createStatement();
               ResultSet resultSet = statement.executeQuery("SELECT * from MATIERES");){

            while(resultSet.next()){
                c.add(matiereDao.fromResultSet(resultSet));
            }
        }catch(Exception e){
            log.log(Level.SEVERE, "Erreur de création de la DAO: "+e.getMessage());

            JOptionPane.showMessageDialog(null , e);
        }
    }

    public void fetch5(){
        //matiere
        //String sql = "Select LIBELLEMATIERE from MATIERES";
        try(
                EtudiantDao etudiantDao = new EtudiantDao();
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * from ETUDIANTS WHERE ID = "+id);){

            while (resultSet.next())
                idGroupeEtudiant = etudiantDao.fromResultSet(resultSet).getGroupeClasse().getGroupeClasse();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }
    }

    /**
     * Une fonction qui recupere les differents enseignants depuis la BD
     */
    public void fetch3(){
        d.clear();
        //String sql = "Select UTILISATEURS.NOM FROM UTILISATEURS INNER JOIN ENSEIGNANTS " +
               //  "ON UTILISATEURS.ID=ENSEIGNANTS.ID";
        try(EnseignantDao enseignantDao = new EnseignantDao();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from ENSEIGNANTS");){

            //pstmt = conn.prepareStatement(sql);
            //rs = pstmt.executeQuery();

            while(resultSet.next()){
                d.add(enseignantDao.fromResultSet(resultSet));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }
    }

    /**
     * Une fonction qui permet d'insérer les differents salles depuis la BD
     * @param event
     */
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
    private void clickShow(ActionEvent event,Button button) {

        try {
            if(map.get(button) != null) {
                idCoursInite = map.get(button);
                Stage stage = new Stage();
                ActionController actionController = new ActionController(idCoursInite);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("views/action.fxml"));

                loader.setController(actionController);

                Parent root = loader.load();

                stage.setScene(new Scene(root));
                stage.setTitle("My modal window");

                stage.initModality(Modality.WINDOW_MODAL);

                stage.setResizable(false);
                stage.initStyle(StageStyle.DECORATED);
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void insertCourse(){
        //matiere
        String sql;
        LocalDate localDate_from = Creneau.getValue();
        Date dateDebut = Date.valueOf(localDate_from);
        //idSalle = cvenueAction.getSelectionModel().getSelectedItem().getCodeSalle();
        String heure = time.getSelectionModel().getSelectedItem();

        enseignant = leturerTable.getSelectionModel().getSelectedItem();
        salle = cvenue.getSelectionModel().getSelectedItem();
        groupe = ccourse.getSelectionModel().getSelectedItem();
        matiere = levelTable.getSelectionModel().getSelectedItem();


        try(Statement statement = conn.createStatement();){


            sql = "SELECT COUNT(*) AS rowCount FROM COURS WHERE DateDebut = ? AND HeureDebut = ? AND groupeClasse = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setDate(1,dateDebut);
            pstmt.setString(2, heure);
            pstmt.setInt(3,groupeList.getSelectionModel().getSelectedItem().getGroupeClasse());
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int rowCount = rs.getInt("rowCount");

            if( rowCount >= 1){
                JOptionPane.showMessageDialog(null ,"Sorry!!This group has an course at this time.");
            }else{


                sql = "SELECT COUNT(*) AS rowCount FROM COURS WHERE DateDebut = ? AND HeureDebut = ? AND codeSalle = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setDate(1,dateDebut);
                pstmt.setString(2, heure);
                pstmt.setInt(3,cvenue.getSelectionModel().getSelectedItem().getCodeSalle());
                rs = pstmt.executeQuery();
                rs.next();
                System.out.println(rowCount);
                rowCount = rs.getInt("rowCount");
                if( rowCount >= 1){
                    JOptionPane.showMessageDialog(null ,"Sorry!!A course was plan in this room");
                }else {


                    sql = "SELECT COUNT(*) AS rowCount FROM COURS WHERE DateDebut = ? AND HeureDebut = ? AND idEnseignants = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setDate(1,dateDebut);
                    pstmt.setString(2, heure);
                    pstmt.setInt(3,leturerTable.getSelectionModel().getSelectedItem().getId());
                    rs = pstmt.executeQuery();
                    rs.next();
                    System.out.println(rowCount);
                    rowCount = rs.getInt("rowCount");
                    if( rowCount >= 1){
                        JOptionPane.showMessageDialog(null ,"Sorry!!This teacher has an course at this time");
                    }else {



                        sql = "INSERT INTO COURS(IDENSEIGNANTS,CODESALLE,GROUPECLASSE,CODEMATIERE, DATEDEBUT, DATEFIN,HEUREDEBUT) VALUES (?,?,?,?,?,?,?)";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setInt(1, enseignant.getId());
                        pstmt.setInt(2, salle.getCodeSalle());
                        pstmt.setInt(3, groupe.getGroupeClasse());
                        pstmt.setInt(4, matiere.getCodeMatiere());
                        pstmt.setDate(5, dateDebut);
                        pstmt.setDate(6, dateDebut);
                        pstmt.setString(7, heure);
                        pstmt.execute();
                        JOptionPane.showMessageDialog(null, "Your course were added");
                    }
                }
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null ,"Your course can't be added!");
        }finally {
            if(pstmt!=null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
    public void loadData(Groupe groupe){

        initeTextArea(mon1, mon2, mon3, mon7, mon8, mon9, mon10, mon11, mon12);
        initeTextArea(tue1, tue2, tue3, tue7, tue8, tue9, tue10, tue11, tue12);
        initeTextArea(wed1, wed2, wed3, wed7, wed8, wed9, wed10, wed11, wed12);
        initeTextArea(thur1, thur2, thur3, thur7, thur8, thur9, thur10, thur11, thur12);
        initeTextArea(fri1, fri2, fri3, fr7i, fri8, fri9, fri10, fri11, fri12);
            try {
                String sql;
                int parametre;
                if (role.equals("ENSEIGNANT")) {
                    sql = "SELECT * FROM COURS WHERE IDENSEIGNANTS = ?";
                    parametre = id;
                }
                else if  (role.equals("ETUDIANT")) {
                    sql = "SELECT * FROM COURS WHERE GROUPECLASSE = ?";
                    parametre = idGroupeEtudiant;
                }else{
                    sql = "SELECT * FROM COURS WHERE GROUPECLASSE = ?";
                    parametre = groupe.getGroupeClasse();
                }



                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, parametre);
                ResultSet resultSet = pstmt.executeQuery();
                String matiere = null;
                String enseignant = null;
                String salle = null;
                String days = null;
                while (resultSet.next()) {
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

                    int idCours = resultSet.getInt("id");
                    load(matiere,salle,enseignant,days,heureDebut,idCours);

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if(pstmt!=null) {
                    try {
                        pstmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }


    }

    public void load(String matiere, String salle, String enseignant, String day, String tim, int idCours) throws SQLException {


        //MONDAY
        if(day.equals("MONDAY") && tim.equals("7:00AM")){
            mon7.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(mon7.getText()!=null)
                 map.put(mon7,idCours);
        }
        else if(day.equals("MONDAY") && tim.equals("8:00AM")){
            mon8.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(mon8.getText()!=null)
                map.put(mon8,idCours);
        }
        else if(day.equals("MONDAY") && tim.equals("9:00AM")){
            mon9.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(mon9.getText()!=null)
                map.put(mon9,idCours);
        }
        else if(day.equals("MONDAY") && tim.equals("10:00AM")){
            mon10.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(mon10.getText()!=null)
                map.put(mon10,idCours);
        }
        else if(day.equals("MONDAY") && tim.equals("11:00AM")){
            mon11   .setText(matiere+"\n"+salle+"\n"+enseignant);
            if(mon11.getText()!=null)
                map.put(mon11,idCours);
        }
        else if(day.equals("MONDAY") && tim.equals("12:00PM")){
            mon12.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(mon12.getText()!=null)
                map.put(mon12,idCours);
        }
        else if(day.equals("MONDAY")&& tim.equals("1:00PM")){
            mon1.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(mon1.getText()!=null)
                map.put(mon1,idCours);
        }
        else if(day.equals("MONDAY") && tim.equals("2:00PM")){
            mon2.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(mon2.getText()!=null)
                map.put(mon2,idCours);
        }
        else if(day.equals("MONDAY") && tim.equals("3:00PM")){
            mon3.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(mon3.getText()!=null)
                map.put(mon3,idCours);
        }
        //TUESDAY
        else if(day.equals("TUESDAY") &&  tim.equals("7:00AM")){
            tue7.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(tue7.getText()!=null)
                map.put(tue7,idCours);
        }
        else if(day.equals("TUESDAY") && tim.equals("8:00AM")){
            tue8.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(tue8.getText()!=null)
                map.put(tue8,idCours);
        }
        else if(day.equals("TUESDAY") && tim.equals("9:00AM")){
            tue9.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(tue9.getText()!=null)
                map.put(tue9,idCours);
        }
        else if(day.equals("TUESDAY") && tim.equals("10:00AM")){
            tue10.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(tue10.getText()!=null)
                map.put(tue10,idCours);
        }
        else if(day.equals("TUESDAY") && tim.equals("11:00AM")){
            tue11.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(tue11.getText()!=null)
                map.put(tue11,idCours);
        }
        else if(day.equals("TUESDAY") && tim.equals("12:00PM")){
            tue12.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(tue12.getText()!=null)
                map.put(tue12,idCours);
        }
        else if(day.equals("TUESDAY") && tim.equals("1:00PM")){
            tue1.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(tue1.getText()!=null)
                map.put(tue1,idCours);
        }
        else if(day.equals("TUESDAY") && tim.equals("2:00PM")){
            tue2.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(tue2.getText()!=null)
                map.put(tue2,idCours);
        }
        else if(day.equals("TUESDAY") && tim.equals("3:00PM")){
            tue3.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(tue3.getText()!=null)
                map.put(tue3,idCours);
        }
        //  WEDNESDAY
        else if(day.equals("WEDNESDAY") && tim.equals("7:00AM")){
            wed7.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(wed7.getText()!=null)
                map.put(wed7,idCours);
        }
        else if(day.equals("WEDNESDAY") && tim.equals("8:00AM")){
            wed8.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(wed8.getText()!=null)
                map.put(wed8,idCours);
        }
        else if(day.equals("WEDNESDAY") && tim.equals("9:00AM")){
            wed9.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(wed9.getText()!=null)
                map.put(wed9,idCours);
        }
        else if(day.equals("WEDNESDAY") && tim.equals("10:00AM")){
            wed10.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(wed10.getText()!=null)
                map.put(wed10,idCours);
        }
        else if(day.equals("WEDNESDAY") && tim.equals("11:00AM")){
            wed11.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(wed11.getText()!=null)
                map.put(wed11,idCours);
        }
        else if(day.equals("WEDNESDAY") && tim.equals("12:00PM")){
            wed12.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(wed12.getText()!=null)
                map.put(wed12,idCours);
        }
        else if(day.equals("WEDNESDAY") && tim.equals("1:00PM")){
            wed1.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(wed1.getText()!=null)
                map.put(wed1,idCours);
        }
        else if(day.equals("WEDNESDAY") && tim.equals("2:00PM")){
            wed2.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(wed2.getText()!=null)
                map.put(wed2,idCours);
        }
        else if(day.equals("WEDNESDAY") && tim.equals("3:00PM")){
            wed3.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(wed3.getText()!=null)
                map.put(wed3,idCours);
        }

        //THURSDAY
        else if(day.equals("THURSDAY") && tim.equals("7:00AM")){
            thur7.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(thur7.getText()!=null)
                map.put(thur7,idCours);
        }
        else if(day.equals("THURSDAY") && tim.equals("8:00AM")){
            thur8.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(thur8.getText()!=null)
                map.put(thur8,idCours);
        }
        else if(day.equals("THURSDAY") && tim.equals("9:00AM")){
            thur9.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(thur9.getText()!=null)
                map.put(thur9,idCours);
        }
        else if(day.equals("THURSDAY") && tim.equals("10:00AM")){
            thur10.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(thur10.getText()!=null)
                map.put(thur10,idCours);
        }
        else if(day.equals("THURSDAY") && tim.equals("11:00AM")){
            thur11.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(thur11.getText()!=null)
                map.put(thur11,idCours);
        }
        else if(day.equals("THURSDAY") && tim.equals("12:00PM")){
            thur12.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(thur12.getText()!=null)
                map.put(thur12,idCours);
        }
        else if(day.equals("THURSDAY") && tim.equals("1:00PM")){
            thur1.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(thur1.getText()!=null)
                map.put(thur1,idCours);
        }
        else if(day.equals("THURSDAY") && tim.equals("2:00PM")){
            thur2.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(thur2.getText()!=null)
                map.put(thur2,idCours);
        }
        else if(day.equals("THURSDAY") && tim.equals("3:00PM")){
            thur3.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(thur3.getText()!=null)
                map.put(thur3,idCours);
        }

        //FRIDAY
        else if(day.equals("FRIDAY") && tim.equals("7:00AM")){
            fr7i.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(fr7i.getText()!=null)
                map.put(fr7i,idCours);
        }
        else if(day.equals("FRIDAY") && tim.equals("8:00AM")){
            fri8.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(fri8.getText()!=null)
                map.put(fri8,idCours);
        }
        else if(day.equals("FRIDAY") && tim.equals("9:00AM")){
            fri9.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(fri9.getText()!=null)
                map.put(fri9,idCours);
        }
        else if(day.equals("FRIDAY") && tim.equals("10:00AM")){
            fri10.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(fri10.getText()!=null)
                map.put(fri10,idCours);
        }
        else if(day.equals("FRIDAY") && tim.equals("11:00AM")){
            fri11.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(fri11.getText()!=null)
                map.put(fri11,idCours);
        }
        else if(day.equals("FRIDAY") && tim.equals("12:00PM")){
            fri12.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(fri12.getText()!=null)
                map.put(fri12,idCours);
        }
        else if(day.equals("FRIDAY") && tim.equals("1:00PM")){
            fri1.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(fri1.getText()!=null)
                map.put(fri1,idCours);
        }
        else if(day.equals("FRIDAY") && tim.equals("2:00PM")){
            fri2.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(fri2.getText()!=null)
                map.put(fri2,idCours);
        }
        else if(day.equals("FRIDAY") && tim.equals("3:00PM")){
            fri3.setText(matiere+"\n"+salle+"\n"+enseignant);
            if(fri3.getText()!=null)
                map.put(fri3,idCours);
        }
    }

    private void initeTextArea(Button mon1, Button mon2, Button mon3, Button mon7, Button mon8, Button mon9, Button mon10, Button mon11, Button mon12) {
        mon1.setText(null);
        mon2.setText(null);
        mon3.setText(null);
        mon7.setText(null);
        mon8.setText(null);
        mon9.setText(null);
        mon10.setText(null);
        mon11.setText(null);
        mon12.setText(null);

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

    public void presence() {
        try {
            FXMLLoader Loader;

            Loader = new FXMLLoader();
            PresenceController presenceController = new PresenceController(id);

            Loader.setLocation(getClass().getResource("views/presence.fxml"));
            Loader.setController(presenceController);
            Parent root = Loader.load();

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.setTitle("Make Presence");
            stage.show();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }

    }

    public void emploiDeTemps() {
        try {
            FXMLLoader Loader;
            Loader = new FXMLLoader();
            ///HomeController homeController = new HomeController(role);
            Loader.setLocation(getClass().getResource("views/home.fxml"));
           // Loader.setController(homeController);
            Parent root = Loader.load();

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.setTitle("HOME");
            stage.show();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
        public void faireReservation() {
            try {
                FXMLLoader Loader;

                EmpreintMaterielController empreintMaterielController = new EmpreintMaterielController(id);

                Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("views/empreint_materiel.fxml"));

                Loader.setController(empreintMaterielController);
                Parent root = Loader.load();

                Scene scene = new Scene(root);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.centerOnScreen();
                stage.setTitle("Make Reservation");
                stage.show();

            }catch(Exception e){
                JOptionPane.showMessageDialog(null , e);
            }


        }

        public void listReservation() {
            try {
                FXMLLoader Loader;
                Loader = new FXMLLoader();
                //a changer
                ReservationMaterielController reservationMaterielController = new ReservationMaterielController(id);

                Loader.setLocation(getClass().getResource("views/liste_reservation_enseignant.fxml"));


                Loader.setController(reservationMaterielController);

                Parent root = Loader.load();

                Scene scene = new Scene(root);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.centerOnScreen();
                stage.setTitle("YOURS RESERVATIONS");
                stage.show();

            }catch(Exception e){
                JOptionPane.showMessageDialog(null , e);
            }


        }

    public void listPresence() {
        try {
            FXMLLoader Loader;
            EtudiantAbsentController etudiantAbsentController = new EtudiantAbsentController(id);
            Loader = new FXMLLoader();

            Loader.setLocation(getClass().getResource("views/liste_etudiant_absent.fxml"));

            Loader.setController(etudiantAbsentController);

            Parent root = Loader.load();

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.setTitle("Presence");
            stage.show();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
        }


    }




}
