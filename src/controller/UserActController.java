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
import entites.Inscription;
import java.io.File;
import java.io.FileNotFoundException;
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
import services.InscriptionService;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import entites.Activites;
import entites.Inscription;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;
import java.sql.Date;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.AnchorPane;
/**

/**
 * FXML Controller class
 *
 * @author USER
 */
public class UserActController implements Initializable {

    @FXML
    private TableColumn<Activites,String> colAct;
    @FXML
    private TableColumn<Activites,Integer> colNb;
    @FXML
    private Label details;
    @FXML
    private VBox vbox;
    @FXML
    private TextField nom;
    @FXML
    private Label labid;
    @FXML
    private ImageView imgview;
    @FXML
    private TextField adresse;
    @FXML
    private TextField agemin;
    @FXML
    private TextField agemax;
    @FXML
    private TextField jours;
    @FXML
    private TextField nombredesplaces;
    @FXML
    private TextField montant;
    @FXML
    private Label formulaire;
    private TableColumn<Activites,String> colClub;
    @FXML
    private TextField club;
    @FXML
    private VBox vbox1;
    @FXML
    private TextField activité_id;
    @FXML
    private TextField nompar;
    @FXML
    private TextField numtel;
    @FXML
    private TextField email;
    @FXML
    private TextField adresse1;
    @FXML
    private TextField nomenf;
    @FXML
    private ImageView imgview1;
    @FXML
    private TextField age;
    @FXML
    private TextField nbmois;
    @FXML
    private DatePicker date;
    @FXML
    private TextField comm;
    @FXML
    private Button sinscrir1;
    @FXML
    private Button annuler;
   
    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    ObservableList<Activites> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<Activites> clubTable;
    @FXML
    private TextField recherche;
    @FXML
    private Button form;
    @FXML
    private TextField photo;
    @FXML
    private TextField idclub;
    @FXML
    private Button importerImage;
    @FXML
    private TextField photo1;
    @FXML
    private Button retour;
    @FXML
    private Button reset;
    @FXML
    private AnchorPane mainpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         vbox.setVisible(false);
         vbox1.setVisible(false);
         formulaire.setVisible(false);
         details.setVisible(false);
           
           getAll();
           
           colAct.setCellValueFactory(new PropertyValueFactory<>("nom"));
           colNb.setCellValueFactory(new PropertyValueFactory<>("nbDispo"));
          
          clubTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try{
               details.setVisible(true);
               vbox.setVisible(true);
                photo.setVisible(false);
                idclub.setVisible(false);
                photo.setVisible(false);
               
               
                
                
                activité_id.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getId()));
                idclub.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getClub_id()));
                club.setText((clubTable.getSelectionModel().getSelectedItem(). convert(Integer.parseInt(idclub.getText()))));

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
          
          
         FilteredList<Activites> filteredData = new FilteredList<>(oblist, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        recherche.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (person.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
             
            
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Activites> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(clubTable.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
       //  mainpane.getChildren().add(table);
        clubTable.setItems(sortedData);
    
    }  
          
          
          
          
          
         
   
    
    
    
    
     public void getAll()
    {
        String sql="select * from activite where nbDispo > 0 ";
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
    private void sinscrir(ActionEvent event) throws SQLException {
     
        
         if (ValidateFields() && ValidateDate()) {
          InscriptionService is = new InscriptionService();
          int prix =  is.getPrix(Integer.parseInt(activité_id.getText())); 

          InscriptionService.insererInscription(nompar.getText(),email.getText(),nomenf.getText(), Integer.parseInt(age.getText()),Integer.parseInt(numtel.getText()),Integer.parseInt(nbmois.getText()),adresse.getText(),java.sql.Date.valueOf(date.getValue()).toString(),comm.getText(),photo1.getText(), prix*Integer.parseInt(nbmois.getText()) ,Integer.parseInt(activité_id.getText()));
         saveAlert();
        clearFields();
         vbox.setVisible(false);
         vbox1.setVisible(false);
         formulaire.setVisible(false);
         details.setVisible(false);
         
    }
        
    }

    @FXML
    private void reset(ActionEvent event) {
         vbox.setVisible(false);
         vbox1.setVisible(false);
         formulaire.setVisible(false);
         details.setVisible(false);
    }

    @FXML
    private void recherche(KeyEvent event) throws SQLException {
    oblist= ActivitesService.rechercherNomEt(recherche.getText());
    clubTable.setItems(oblist);
//if (recherche.getText() != "") {
//            clubTable.setItems(ActivitesService.rechercherNomEt(recherche.getText()));
//        }
//     try {
//               if (recherche.getText() != "") {
//          clubTable.setItems(ActivitesService.rechercherNomEt(recherche.getText()));
//            }} catch (SQLException ex) {
//                Logger.getLogger(ActivitesService.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    
    
    
    
    }

    @FXML
    private void form(ActionEvent event) {
       
      
       
        
        formulaire.setVisible(true);  
        vbox1.setVisible(true);
        activité_id.setVisible(false);

        
    }

    @FXML
    private void importerImage(ActionEvent event) throws FileNotFoundException {

        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            photo1.setText(selectedfile.toURI().toString());
    Image image = new Image(photo1.getText());
            imgview1.setImage(image);

            
        }
    }
    
     private void saveAlert() {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Votre demande a été envoyé Merci.  ");
        alert.showAndWait();
    }
     
     
     private void saveAlert1() {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Impossible");
        alert.setHeaderText(null);
        alert.setContentText("Il n'y a pas des places disponibles .  ");
        alert.showAndWait();
    }
     
      private void clearFields() {
      
        
        nompar.clear();
        nomenf.clear();
        email.clear();
        adresse1.clear();
        nbmois.clear();
        age.clear();
        numtel.clear();
        photo1.clear();
        comm.clear();
        date.setValue(null);
        imgview1.setImage(null);


}
      
      public Boolean ValidateFields() {
        if (nompar.getText().isEmpty() | nomenf.getText().isEmpty() | email.getText().isEmpty() | adresse.getText().isEmpty() | nbmois.getText().isEmpty() | age.getText().isEmpty()| numtel.getText().isEmpty() | photo.getText().isEmpty() |comm.getText().isEmpty() ) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Champs vides ou incorectes ");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait vérifier les champs.");
            alert.showAndWait();
            return false;
        }

        return true;

    }
    public Boolean ValidateDate() {
        if ( (date.getValue().isBefore(java.time.LocalDate.now()))) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Date Non Valide ");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait vérifier la date .");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    @FXML
    private void retour(ActionEvent event )  throws Exception {
        mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/views/UserClubAff.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

  

    @FXML
    private void annuler(ActionEvent event) {
                recherche.clear();

    }

    
      
      
    
}
