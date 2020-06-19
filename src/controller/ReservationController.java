package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import services.EnfantService;
import services.JardinService;
import services.ReservationService1;
import entites.Enfant;
import entites.ReservationJardin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ReservationController {

    @FXML
    private ComboBox<Enfant> enfantCombo = new ComboBox<Enfant>();
    @FXML
    private Button reserverBtn;
    private Executor exec;
    @FXML
    private TextField resultArea;
    @FXML
    private TextField idEnfant;
    @FXML
    private TextField idJardin;
    @FXML
    private AnchorPane ancreservation;
    @FXML
    private Label nomjardin;

    //For MultiThreading
    List<Enfant> enfants;

    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.

    @FXML
    private void initialize() {
        /*
        The setCellValueFactory(...) that we set on the table columns are used to determine
        which field inside the Employee objects should be used for the particular column.
        The arrow -> indicates that we're using a Java 8 feature called Lambdas.
        (Another option would be to use a PropertyValueFactory, but this is not type-safe

        We're only using StringProperty values for our table columns in this example.
        When you want to use IntegerProperty or DoubleProperty, the setCellValueFactory(...)
        must have an additional asObject():
        */

        //For multithreading: Create executor that uses daemon threads:
//For multithreading: Create executor that uses daemon threads:
        exec = Executors.newCachedThreadPool((runnable) -> {
            Thread t = new Thread (runnable);
            t.setDaemon(true);
            return t;
        });
        EnfantService enfantService = new EnfantService();
        enfants = enfantService.getAll();
        enfantCombo.setItems(FXCollections.observableList(enfants));
      //  enfantCombo.getSelectionModel().selectFirst();
        //
        enfantCombo.setConverter(new StringConverter<Enfant>() {

            @Override
            public String toString(Enfant object) {
             
                return object.getPrenom();
            }

            @Override
            public Enfant fromString(String string) {
               
                return enfantCombo.getItems().stream().filter(ap ->
                        ap.getPrenom().equals(string)).findFirst().orElse(null);
            }
        });
        enfantCombo.valueProperty().addListener((obs, oldval, newval) -> {
            if(newval != null)
                idEnfant.setText(newval.getId()+"");
        });

    }


    //Ajouter jardin to the DB
    @FXML
    private void reserver(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


            String idj=idJardin.getText();
            String ide=idEnfant.getText();

            int idjardin = 0;
            int idenfant=0;
            try {
            //   resultArea.setText("Votre enfant est déja inscrit");
             
                idjardin=Integer.parseInt(idj);
                idenfant=Integer.parseInt(ide);
                ReservationService1 Rs = new ReservationService1();
                JardinService Js = new JardinService();
                System.out.println(Rs.rechercheE(idenfant)+"enfant!!");
                if ((Rs.rechercheE(idenfant)).equals(0)  ){
                    System.out.println(Rs.rechercheE(idenfant)+"enfant");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation Réservation");
alert.setHeaderText("Vous êtes sur pour la réservation de votre enfant");
//alert.setContentText("Are you ok with this?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                    try{
                        ReservationJardin r   = new ReservationJardin(idjardin,idenfant);
                        Rs.insererReservation_jardin(r);
                      //  resultArea.setText("Mise à jour encours ... \n");
                         JOptionPane.showMessageDialog(null,"Mise à jour encours ... \n");
                        Js.reservationJardin(idjardin);
                     //   resultArea.setText("votre inscription a été affectué avec succée! \n");
                         JOptionPane.showMessageDialog(null,"votre inscription a été affectué avec succée! \n");
                   
                    } catch (SQLException e) {
                    //    resultArea.setText("Probléme de réservation" + e);
                         JOptionPane.showMessageDialog(null,"Probléme de réservation" + e);
                        throw e;
                     
                    }}}
                    else{

                      JOptionPane.showMessageDialog(null,"Votre enfant est déja inscrit");
                    System.out.println("Votre enfant est déja inscrit");
                }
            }catch (NumberFormatException e){
             
                   JOptionPane.showMessageDialog(null,"Sélectionner votre enfant" + e);
            }



    }


    public void LOG2(String  id,String nom) {
          idJardin.setText(id);
          nomjardin.setText(nom);

    }
}
