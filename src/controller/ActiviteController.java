/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTimePicker;
import static com.jfoenix.svg.SVGGlyphLoader.clear;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import entites.Activites;
import entites.Club;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ClubService;
import services.ActivitesService;
import util.DataSource;
//import views.AfficherClub;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class ActiviteController implements Initializable {

    @FXML
    private TextField id;
    FileChooser filechooser;
    String filename = null;
    byte[] person_image = null;
    private File file;
    @FXML
    private ComboBox Clubbox;
    @FXML
    private Button importerImage;
    @FXML
    private TextField photo;
    @FXML
    private TextField nom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField agemin;
    @FXML
    private TextField agemax;
    @FXML
    private TextField jours;
//    @FXML
//    private JFXTimePicker heured;
//    @FXML
//    private JFXTimePicker heuref;
    @FXML
    private TextField nombredesplaces;
    @FXML
    private TextField montant;
    @FXML
    private Button delete;
    @FXML
    private Button saveUser;
    @FXML
    private Button reset;
    @FXML
    private Button modifier;
    @FXML
    private TableView<Activites> clubTable;
    @FXML
    private MenuItem deleteUsers;
    @FXML
    private TableColumn<Activites, Integer> colClub;
    @FXML
    private TableColumn<Activites,String> colNom;
    @FXML
    private TableColumn<Activites, String> colPhoto;
    @FXML
    private TableColumn<Activites,String> colAdresse;
    @FXML
    private TableColumn<Activites, Integer> cloAgemin;
    @FXML
    private TableColumn<Activites, Integer> colAgemax;
    @FXML
    private TableColumn<Activites,String> colJours;
    @FXML
    private TableColumn<Activites,Integer> colNbdispo;
    @FXML
    private TableColumn<Activites,Integer> colMontant;
    @FXML
    private ImageView imgview;
     
    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    
     public String imgurl;
    private URI imguriUri;
    public String imgp;
    
     ObservableList<Activites> oblist = FXCollections.observableArrayList();
    @FXML
    private TextField idclub;
    @FXML
    private Label labid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setVisible(false);
        idclub.setVisible(false); 
        labid.setVisible(false); 
        
        String sql = "select id,nom from club";
        Connection con = DataSource.connect();
        oblist.clear();
        try {
            ResultSet rse = con.createStatement().executeQuery(sql);
            while (rse.next()) {

                Clubbox.getItems().add(rse.getString("nom"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivitesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
  getAll();
        
        colClub.setCellValueFactory(new PropertyValueFactory<>("club_id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPhoto.setCellValueFactory(new PropertyValueFactory<>("image"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        cloAgemin.setCellValueFactory(new PropertyValueFactory<>("agemin"));
        colAgemax.setCellValueFactory(new PropertyValueFactory<>("agemax"));
        colJours.setCellValueFactory(new PropertyValueFactory<>("jours"));
//        colHeured.setCellValueFactory(new PropertyValueFactory<>("heured"));
//        colHeuref.setCellValueFactory(new PropertyValueFactory<>("heuref"));
        colNbdispo.setCellValueFactory(new PropertyValueFactory<>("nbDispo"));
        colMontant.setCellValueFactory(new PropertyValueFactory<>("montantp"));
        
        clubTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try{
               Clubbox.setVisible(false);  
               labid.setVisible(true); 
               idclub.setVisible(true);
               saveUser.setVisible(false); 

              
               id.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getId()));
               idclub.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getClub_id()));
//                Clubbox.setValue(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getClub_id()));
                nom.setText(clubTable.getSelectionModel().getSelectedItem().getNom());
                adresse.setText(clubTable.getSelectionModel().getSelectedItem().getAdresse());
                agemin.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getAgemin()));
                agemax.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getAgemax()));
                jours.setText(clubTable.getSelectionModel().getSelectedItem().getJours());
//                heured.setValue(clubTable.getSelectionModel().getSelectedItem().getHeured().toLocalTime());
//                heuref.setValue(clubTable.getSelectionModel().getSelectedItem().getHeuref().toLocalTime());
                nombredesplaces.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getNbDispo()));
                montant.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getMontantp()));
                photo.setText(clubTable.getSelectionModel().getSelectedItem().getImage());
                Image image = new Image("file:\\C:\\xampp\\htdocs\\JardinEnfant\\web\\uploads\\photos\\"+(photo.getText()));
                imgview.setImage(image);
                }
                catch(Exception e){
                    
                      System.out.println(e);
                }

            }

        });

    }

    
     public ActiviteController() {
        connexion=DataSource.getInstance().getCnx();
               
        
        
        
        
        
    }
      public void getAll()
    {
        String sql="select * from activite";
        Connection con = DataSource.connect();
        oblist.clear();

        try {

                        ResultSet rs = con.createStatement().executeQuery(sql);

            while(rs.next())
            {
        oblist.add(new Activites(rs.getInt("id"), rs.getString("nom"), rs.getString("adresse"), rs.getInt("agemin"), rs.getInt("agemax"),rs.getInt("montantp"), rs.getInt("nbDispo"), rs.getTime("heured"), rs.getTime("heuref"), rs.getString("image"), rs.getString("jours"), rs.getInt("club_id")));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
       clubTable.setItems(oblist);
    }
    

    @FXML
    private void Clubbox (ActionEvent event) {
      
    }

    @FXML
    private void importerImage(ActionEvent event) throws FileNotFoundException {

        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
//           photo.setText(selectedfile.toURI().toString());
//           Image image = new Image(photo.getText());
//            
//            imgview.setImage(image);
//            imguriUri = selectedfile.toURI();
//            imgp = selectedfile.getName();


            imgurl = selectedfile.toURI().toString();
            String fileName = imgurl.substring(imgurl.lastIndexOf('/') + 1, imgurl.length());
            photo.setText(fileName);
            Image image = new Image(imgurl);
            imgview.setImage(image);
            imguriUri = selectedfile.toURI();
            imgp = selectedfile.getName();
            
        }
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
           ActivitesService.supprimerActivite(Integer.parseInt(id.getText()));
           ActivitesService.UpdateNbr(Integer.parseInt(idclub.getText()));
    }
        getAll();
        clearFields();
    }
    

    @FXML
    private void saveUser(ActionEvent event) throws SQLException, IOException {
                    Time time = new Time(0, 0, 0);
if (ValidateFields() && ValidateAge() ) {
     ActivitesService.insererActivites(nom.getText(),adresse.getText(),photo.getText(),jours.getText(), Integer.parseInt(agemin.getText()),Integer.parseInt(agemax.getText()),Integer.parseInt(montant.getText()),Integer.parseInt(nombredesplaces.getText()),time,time,club_id());
     ActivitesService.UpdateNb(club_id());
     
     saveAlert();
     getAll();
     clearFields();
     Files.copy(Paths.get(imguriUri), Paths.get("C:\\xampp\\htdocs\\JardinEnfant\\web\\uploads\\photos\\" + imgp));
    }
    }

    @FXML
    private void reset(ActionEvent event) {
        clearFields();
    }

    @FXML
     private void modifier(ActionEvent event) {
         Time time = new Time(0, 0, 0);
         
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes vous sur !! ");
            Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK ) try {
               ActivitesService.modifierActivites(Integer.parseInt(id.getText()), nom.getText(),adresse.getText(),photo.getText(),jours.getText(), Integer.parseInt(agemin.getText()),Integer.parseInt(agemax.getText()),Integer.parseInt(montant.getText()),Integer.parseInt(nombredesplaces.getText()),time,time,Integer.parseInt(idclub.getText()));
             getAll();
             clearFields();


     
            }     catch (Exception e){    System.out.println(e);;}
 
    }


    @FXML
    private void deleteUsers(ActionEvent event) {
    }
    
    
    
    
    private int club_id() {
        Connection con = DataSource.connect();

        int k = 0;

        try {
            PreparedStatement pt = con.prepareStatement("select id from club where nom=?");
            pt.setString(1, (String) Clubbox.getValue());

            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                k = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return k;
    }
      
      
       private void clearFields() {
        id.clear();
        montant.clear();
        nombredesplaces.clear();
        nom.clear();
        photo.clear();
        adresse.clear();
        agemin.clear();
        agemax.clear();
//        heured.setValue(LocalTime.now());
//        heuref.setValue(LocalTime.now());
        imgview.setImage(null);
        jours.clear();
        Clubbox.setValue(null); 
        Clubbox.setVisible(true);  
        idclub.setVisible(false);
        labid.setVisible(false);
        saveUser.setVisible(true); 
         idclub.clear();
        



    }
       private void clearFields1() {
        id.clear();
        montant.clear();
        nombredesplaces.clear();
        nom.clear();
        photo.clear();
        adresse.clear();
        agemin.clear();
        agemax.clear();
//        heured.setValue(LocalTime.now());
//        heuref.setValue(LocalTime.now());
        imgview.setImage(null);
        jours.clear();
        Clubbox.setValue(null); 
      


    }
       
       
        public Boolean ValidateFields() {
        if (montant.getText().isEmpty() | nombredesplaces.getText().isEmpty() | nom.getText().isEmpty() | photo.getText().isEmpty() | adresse.getText().isEmpty() | agemin.getText().isEmpty()| agemax.getText().isEmpty() | jours.getText().isEmpty() |Clubbox.getValue()==null  ) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Champs vides ou incorectes");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait vérifier les champs.");
            alert.showAndWait();
            return false;
        }

        return true;

    }
        public Boolean ValidateAge() {
        if ((Integer.parseInt(agemin.getText())) >= (Integer.parseInt(agemax.getText())) ) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Vérifiez Les Ages Min et Max .");
            alert.showAndWait();
            return false;
        }
        return true;
    }
        
        
        
       
       private void saveAlert() {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmé");
        alert.setHeaderText(null);
        alert.setContentText("Activité Ajouté . ");
        alert.showAndWait();
    }
      
//      java.sql.Date.valueOf(datevavavnanavhrh.getValue()).toString()
    
}
