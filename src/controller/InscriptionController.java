/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;
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
import javax.swing.JOptionPane;

import services.InscriptionService;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class InscriptionController implements Initializable {

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
    private Button delete;
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
    private TableColumn<?, ?> colNomEnfant;
    @FXML
    private Button pdf;

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
        colNomEnfant.setCellValueFactory(new PropertyValueFactory<>("nomEnfant"));
        
        
        
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

    
     public InscriptionController() {
        connexion=DataSource.getInstance().getCnx();
               
        
        
        
        
        
    }
      public void getAll()
    {
        String sql="SELECT * FROM inscription where confirmed = true";
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
    private void delete(ActionEvent event) throws SQLException {
        if (ValidateFields()){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de suuprimer cette inscription ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
           InscriptionService.supprimerInscription(Integer.parseInt(id.getText()));
           InscriptionService.UpdateNbn(Integer.parseInt(activité.getText()));
    }
        getAll();
        clearFields();
    } 
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
   
    public Boolean ValidateFields() {
        if (nomparent.getText().isEmpty() | numtel.getText().isEmpty() | email.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides  ");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait séléctionner une inscription .");
            alert.showAndWait();
            return false;
        }

        return true;

    }

    @FXML
    private void deleteUsers(ActionEvent event) {
    }

    @FXML
    private void CratePDF(ActionEvent event) {
        try {
         Document document = new Document();
       PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\USER\\Desktop\\utilisateur.pdf"));
       document.open();
       PdfPTable table=new PdfPTable(7);
       table.setWidthPercentage(100);
		table.getDefaultCell().setBorder(2);
       table.addCell("Nom parent");
        table.addCell("Adresse");
        table.addCell("Contact");
       table.addCell("Nom enfant");
       table.addCell("Nombre de mois de participation");
             table.addCell("Date de debut");
                     table.addCell("Montant à payer");
    
      
        com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph();
            p.add("Listes des Inscriptions");
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(p);
             document.add(com.itextpdf.text.Image.getInstance("C:\\Users\\USER\\Desktop\\logo.png"));

       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jardinenfant", "root", "");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("SELECT NomParent,adresse,email,NomEnfant,nbmois ,dated,montant FROM `inscription`  where confirmed = true");
       while(rs.next()){
       table.addCell(rs.getString("NomParent"));
       table.addCell(rs.getString("adresse"));

       table.addCell(rs.getString("email"));
       table.addCell(rs.getString("NomEnfant"));
       table.addCell(rs.getString("nbmois"));
       table.addCell(rs.getString("dated"));
       table.addCell(rs.getString("montant"));
       }
       document.add(table);
        JOptionPane.showMessageDialog(
                null, " données exportées en pdf avec succés ");
               document.close();
           
            

        } catch (Exception e) {

            System.out.println("Error PDF");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
   
    }

    
}
