/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import entites.Inscription;
import services.ActivitesService;
import entites.Activites;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import services.InscriptionService;
import util.DataSource;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class DemandeInscController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private Label labid;
    @FXML
    private TextField activité;
    @FXML
    private TextField nomparent;
    @FXML
    private TextField numtel;
    @FXML
    private TextField email;
    @FXML
    private TextField adresse;
    @FXML
    private TextField photo;
    @FXML
    private ImageView imgview;
    @FXML
    private TextField age;
    @FXML
    private TextField nombredesmois;
    @FXML
    private TextField montant;
    @FXML
    private TextField commentaire;
    @FXML
    private Button refuser;
    @FXML
    private Button accepter;
    @FXML
    private Button reset;
    @FXML
    private TableView<Inscription> clubTable;
    @FXML
    private MenuItem deleteUsers;
    @FXML
    private TableColumn<Inscription, Integer> colAct;
    @FXML
    private TableColumn<Inscription, String> colNom;
    @FXML
    private TableColumn<Inscription,Integer> colNumtel;
    @FXML
    private TableColumn<Inscription,String> colEmail;
    @FXML
    private TableColumn<Inscription,String> colAdresse;
    @FXML
    private TableColumn<Inscription,String> colImage;
    @FXML
    private TableColumn<Inscription,Integer> colAge;
    @FXML
    private TableColumn<Inscription,Integer> colNbmois;
    @FXML
    private TableColumn<Inscription,String> colDated;
    @FXML
    private TableColumn<Inscription,Float> colMontantp;
    @FXML
    private TableColumn<Inscription,String> colComm;
    @FXML
    private DatePicker date;
    
    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    ObservableList<Inscription> oblist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Inscription,String> colNomenfant;
     String staticUserMail = "fekihmeyssen@gmail.com";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       id.setVisible(false);
        
        getAll();
         
        colAct.setCellValueFactory(new PropertyValueFactory<>("activite_id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nomParent"));
        colNumtel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("photo"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colNbmois.setCellValueFactory(new PropertyValueFactory<>("nbmois"));
        colDated.setCellValueFactory(new PropertyValueFactory<>("dated"));
        colMontantp.setCellValueFactory(new PropertyValueFactory<>("montant"));
        colComm.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        colNomenfant.setCellValueFactory(new PropertyValueFactory<>("nomEnfant"));
        
        
        
        clubTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try{
             
              
               id.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getId()));
               activité.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getActivite_id()));

                nomparent.setText(clubTable.getSelectionModel().getSelectedItem().getNomParent());
                adresse.setText(clubTable.getSelectionModel().getSelectedItem().getAdresse());
                numtel.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getNumTel()));
                age.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getAge()));
                email.setText(clubTable.getSelectionModel().getSelectedItem().getEmail());
                date.setValue(clubTable.getSelectionModel().getSelectedItem().getDated().toLocalDate());               
                commentaire.setText(clubTable.getSelectionModel().getSelectedItem().getCommentaire());
                nombredesmois.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getNbmois()));
                montant.setText(Float.toString(clubTable.getSelectionModel().getSelectedItem().getMontant()));
                photo.setText(clubTable.getSelectionModel().getSelectedItem().getPhoto());
                Image image = new Image(photo.getText());
                imgview.setImage(image);
                }
                catch(Exception e){
                    
                      System.out.println(e);
                }

            }

        });

    }

    
     public DemandeInscController() {
        connexion=DataSource.getInstance().getCnx();
               
        
        
        
        
        
    }
      public void getAll()
    {
        String sql="SELECT * FROM inscription where confirmed = false";
        Connection con = DataSource.connect();
        oblist.clear();

        try {

                        ResultSet rs = con.createStatement().executeQuery(sql);

            while(rs.next())
            {
        oblist.add(new Inscription(rs.getInt("id"), rs.getString("nomParent"), rs.getString("email"), rs.getString("nomEnfant"), rs.getInt("age"),rs.getInt("numTel"), rs.getFloat("montant"), rs.getInt("nbmois"), rs.getString("adresse"), rs.getDate("dated"), rs.getString("commentaire"),rs.getString("photo") ,rs.getInt("activite_id")));                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
       clubTable.setItems(oblist);
    }

    @FXML
    private void refuser(ActionEvent event) throws SQLException, javax.mail.MessagingException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de refuser cette inscription ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
           InscriptionService.supprimerInscription(Integer.parseInt(id.getText()));
           sendEmail1(staticUserMail, "Votre demande est réfusé ");
    }
        getAll();
        clearFields();
        saveAlert1();
    }
    

    @FXML
    private void accepter(ActionEvent event) throws SQLException, javax.mail.MessagingException {
     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr d'accepter?");
      Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
           InscriptionService.UpdateConfirmer(Integer.parseInt(id.getText()));
           InscriptionService.UpdateNb(Integer.parseInt(activité.getText()));
           sendEmail(staticUserMail, "Votre demande est accepté");
      
        }
//        getAll();
//        clearFields();
        saveAlert();
        getAll();
        clearFields();
    }


   

    @FXML
    private void reset(ActionEvent event) {
      clearFields();
    }


    private void clearFields() {
        id.clear();
        activité.clear();
        nomparent.clear();
        numtel.clear();
        email.clear();
        adresse.clear();
        photo.clear();
        age.clear();
        nombredesmois.clear();
        montant.clear();
        imgview.setImage(null);
        commentaire.clear();
        date.setValue(null);



}

    @FXML
    private void deleteUsers(ActionEvent event) {
    }
    
    
    
    public static void sendEmail(String emailAddress,String sub) throws AddressException, javax.mail.MessagingException{
     String to = emailAddress;//change accordingly  
      //String subject = emailSubject;
        //String msg = emailMessage;
      
      String host = "smtp.gmail.com";//or IP address  
      final String username = "fekihmeyssen@gmail.com";
    final String password = "meyssen12345678";
     //Get the session object  
      Properties props = System.getProperties();  
      props.setProperty("mail.smtp.host", host);  
       props.put("mail.smtp.starttls.enable","true");
        /* mail.smtp.ssl.trust is needed in script to avoid error "Could not convert socket to TLS"  */
        props.setProperty("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.auth", "true");      
            // props.put("mail.smtp.user", username);
      //  props.put("mail.smtp.password", password);
       
        props.put("mail.smtp.port", "587");
     Session session = Session.getDefaultInstance(props, 
      new javax.mail.Authenticator() {
           
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
     
     
     MimeMessage message = new MimeMessage(session);
     message.setFrom(new InternetAddress(username));
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
     message.setSubject("Demande");
     message.setText("Votre demande est accepté et Merci .");
     // Send message
     Transport.send(message);
     System.out.println("message sent successfully....");  
     
}
    
    private void saveAlert() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Demande accepté et un Email de confirmation est envoyé. ");
        alert.showAndWait();
    }
    
    private void saveAlert1() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Demande réfusé et un Email de refus est envoyé. ");
        alert.showAndWait();
    }
     public static void sendEmail1(String emailAddress,String sub) throws AddressException, javax.mail.MessagingException{
     String to = emailAddress;//change accordingly  
      //String subject = emailSubject;
        //String msg = emailMessage;
      
      String host = "smtp.gmail.com";//or IP address  
      final String username = "fekihmeyssen@gmail.com";
    final String password = "meyssen12345678";
     //Get the session object  
      Properties props = System.getProperties();  
      props.setProperty("mail.smtp.host", host);  
       props.put("mail.smtp.starttls.enable","true");
        /* mail.smtp.ssl.trust is needed in script to avoid error "Could not convert socket to TLS"  */
        props.setProperty("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.auth", "true");      
            // props.put("mail.smtp.user", username);
      //  props.put("mail.smtp.password", password);
       
        props.put("mail.smtp.port", "587");
     Session session = Session.getDefaultInstance(props, 
      new javax.mail.Authenticator() {
           
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
     
     
     MimeMessage message = new MimeMessage(session);
     message.setFrom(new InternetAddress(username));
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
     message.setSubject("Demande");
     message.setText("Votre demande est Réfusé et Merci.");
     // Send message
     Transport.send(message);
     System.out.println("message sent successfully....");  
     
}
}
   


      
   

    
    

