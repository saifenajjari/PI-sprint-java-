/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class GuideAdminController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button club;
    @FXML
    private Button activite;
    @FXML
    private Button demandeIns;
    @FXML
    private Button demande;
    @FXML
    private AnchorPane mainpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void club(ActionEvent event) throws IOException {
         mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/views/AfficherClub.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    @FXML
    private void activite(ActionEvent event) throws IOException {
         mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/views/AfficherActivite.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    @FXML
    private void demandeIns(ActionEvent event) throws IOException {
         mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/views/AfficherInscription.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    @FXML
    private void demande(ActionEvent event) throws IOException {
         mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/views/AfficherDemandes.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }
    
}
