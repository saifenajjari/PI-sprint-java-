/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entites.Club;
import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ClubService;
import util.DataSource;
//import views.AfficherClub;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
public class ClubController implements Initializable {

    @FXML
    private TextField jardin;
    @FXML
    private TextField contact;
    @FXML
    private TextField photo;
    @FXML
    private TextField nom;
    
    @FXML
    private Button saveUser;
    @FXML
    private TableView<Club> clubTable;

    ObservableList<Club> oblist = FXCollections.observableArrayList();
    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    @FXML
    private MenuItem deleteUsers;
    
    @FXML
    private TableColumn<Club, String> colJardin;
    @FXML
    private TableColumn<Club, String> colContact;
    @FXML
    private TableColumn<Club, String> colPhoto;
    @FXML
    private TableColumn<Club, String> colNom;
    @FXML
    private TableColumn<Club, String> colDesc;
    @FXML
    private TableColumn<Club, Integer> colNbAct;
    @FXML
    private TextField desc;
    @FXML
    private TextField nbact;
    @FXML
    private Button modifier;
    @FXML
    private TextField id;
    FileChooser filechooser;
    String filename = null;
    byte[] person_image = null;
    private File file;
    @FXML
    private Button importerImage;
    @FXML
    private Button delete;
    @FXML
    private Button reset;
    @FXML
    private ImageView imgview;

       @FXML
    private TextField recherche;
       
    public String imgurl;
    private URI imguriUri;
    public String imgp;
       
 /**
     * Initializes the controller class.
     */
//private int id_club() {
//        Connection con = Database2.connect();
//
//        int k = 0;
//
//        try {
//            PreparedStatement pt = con.prepareStatement("select idClub from club where nomClub=?");
//            pt.setString(1, (String) idcl.getValue());
//
//            ResultSet rs = pt.executeQuery();
//
//            while (rs.next()) {
//                k = rs.getInt(1);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return k;
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setVisible(false);
        nbact.setVisible(false);
        
        
        getAll();

        
        
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colJardin.setCellValueFactory(new PropertyValueFactory<>("jardin"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("descp"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colNbAct.setCellValueFactory(new PropertyValueFactory<>("nbAct"));
        colPhoto.setCellValueFactory(new PropertyValueFactory<>("image"));      
        
        clubTable.setOnMousePressed(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent event) {
                try{
                    
                   nbact.setVisible(true); 
                    saveUser.setVisible(false); 
                    
                id.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getId()));

                jardin.setText(clubTable.getSelectionModel().getSelectedItem().getJardin());
                nom.setText(clubTable.getSelectionModel().getSelectedItem().getNom());
                contact.setText(clubTable.getSelectionModel().getSelectedItem().getContact());
                desc.setText(clubTable.getSelectionModel().getSelectedItem().getDescp());
                nbact.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getNbAct()));
                photo.setText(clubTable.getSelectionModel().getSelectedItem().getImage());
               
               Image image = new Image("file:\\C:\\xampp\\htdocs\\JardinEnfant\\web\\uploads\\photos\\"+(photo.getText()));
                 imgview.setImage(image);
                }
                catch(Exception e){
                    System.out.println(e);
                    
                }

            }

        });
        
        FilteredList<Club> filteredData = new FilteredList<>(oblist, e -> true);
        recherche.setOnKeyReleased(e -> {
            recherche.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Club>) club -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lower = newValue.toLowerCase();
                    if (club.getJardin().toLowerCase().contains(lower)) {
                        return true;
                    }

                    return false;
                });
            });
            SortedList<Club> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(clubTable.comparatorProperty());
            clubTable.setItems(sortedData);
        });
        

    }
     
    
     public ClubController() 
     {
        connexion=DataSource.getInstance().getCnx();
     }
    
     public void getAll()
       {
        String sql="select * from club";
        Connection con = DataSource.connect();
        oblist.clear();

        try {

                        ResultSet rs = con.createStatement().executeQuery(sql);

            while(rs.next())
            {
                oblist.add(new Club(rs.getInt("id"), rs.getString("nom"), rs.getString("jardin"), rs.getString("descp"), rs.getString("contact") ,rs.getInt("nbAct"),rs.getString("image")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
       clubTable.setItems(oblist);
      }
    
  

    @FXML
    private void reset(ActionEvent event) {
        clearFields();
    }


    private void clearFields() {
        id.clear();
        jardin.clear();
        contact.clear();
        photo.clear();
        nom.clear();
        desc.clear();
        nbact.clear();
        imgview.setImage(null);
        nbact.setVisible(false);
        saveUser.setVisible(true); 


    }
    
     public Boolean ValidateFields() {
        if (jardin.getText().isEmpty() | contact.getText().isEmpty() | nom.getText().isEmpty()| photo.getText().isEmpty() | desc.getText().isEmpty()    ) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Champs vides ou incorectes");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait vérifier les champs.");
            alert.showAndWait();
            return false;
        }

        return true;

    }
     
     
     
        

    private void saveAlert() {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Club Ajouté ");
        alert.showAndWait();
    }

   
    @FXML
    private void saveUser(ActionEvent event) throws SQLException, AWTException, IOException {
    
        
     if (ValidateFields()) {
      

     ClubService.insererClub(nom.getText(),jardin.getText(),contact.getText(),desc.getText(),photo.getText());
                Notification n = new Notification();
                n.displayTray("Club", "bien inseré");
     
     
     clearFields();
     saveAlert();
     getAll();
       Files.copy(Paths.get(imguriUri), Paths.get("C:\\xampp\\htdocs\\JardinEnfant\\web\\uploads\\photos\\" + imgp));

     }
 }
  


   

    @FXML
    private void modifier(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes vous sur !! ");
            Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK ) try {
                ClubService.modifierClub(Integer.parseInt(id.getText()), nom.getText(),jardin.getText(),contact.getText(),desc.getText(),photo.getText());
             getAll();
             clearFields();
             


     
            }     catch (Exception e){    JOptionPane.showMessageDialog(null,"Veuillez sélectionner une ligne du table");}
 
    }
    //java.sql.Date.valueOf(dateCreation.getValue()).toString()

      @FXML
    private void importerImage(ActionEvent event) throws FileNotFoundException {

        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
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
            ClubService.supprimerClub(Integer.parseInt(id.getText()));
    }
        getAll();
        clearFields();
    }

    @FXML
    private void deleteUsers(ActionEvent event) {
    }

    
}
