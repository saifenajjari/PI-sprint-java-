package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.JardinService;
import entites.Jardin;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Acceuil_BackController {
    //For MultiThreading
    @FXML
    private AnchorPane bp1;

    @FXML
    private Pane content;
    @FXML
    private HBox id_ajouter_enfant;
    @FXML
    private HBox id_afficherReservation;
    @FXML
    private HBox id_afficherJardin;
    @FXML
    private HBox id_retour;
    @FXML
    private Label user;

    private Executor exec;
    @FXML
    private HBox id_ajouter_reservation;
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
        bp1.setPrefWidth(1500);
        content.setPrefWidth(900.0);
        user.setText("admin");
        afficherlistJardin();
    }
    @FXML
    void btn_afficher(MouseEvent event) {
        if (event.getTarget() == id_ajouter_enfant) {
            try {
                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/views/EnfantView.fxml"));
                content.getChildren().clear();
                content.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (event.getTarget() == id_afficherReservation) {
            try {
                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/views/ListeReservationView.fxml"));
                content.getChildren().clear();
                content.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        if ((event.getTarget() == id_afficherJardin) || (event.getTarget() == id_retour)) {
           afficherlistJardin();
        }

    }

   public  void afficherlistJardin()
   {
       try {
          AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/views/GestionJardinView.fxml"));
           content.getChildren().clear();
           content.getChildren().add(newLoadedPane);
           content.setPrefWidth(900.0);
       } catch (IOException ex) {
           Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
       }
   }

}
