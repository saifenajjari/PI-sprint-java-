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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView img;
    @FXML
    private Button deconnexion;
    @FXML
    private Button evenement;
    @FXML
    private Button restau;
    @FXML
    private Button recl;
    @FXML
    private Button transport;
    @FXML
    private Button club;
    @FXML
    private Button jardin;
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
    private void deconnexion(ActionEvent event) throws IOException {
        mainPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/views/Login1.fxml"));
        mainPane.getChildren().add(parent);
        mainPane.toFront(); 
    }

    @FXML
    private void evenement(ActionEvent event) throws IOException {
         mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/views/afficherfront.fxml"));
        mainpane.getChildren().add(parent);
      mainpane.getStylesheets().add(getClass().getResource("/views/style.css").toExternalForm());
        mainpane.toFront();
    }

    @FXML
    private void restau(ActionEvent event) {
    }

    @FXML
    private void recl(ActionEvent event) {
    }

    @FXML
    private void transport(ActionEvent event) throws IOException {
          mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/views/TransportFront.fxml"));
          mainpane.getStylesheets().add(getClass().getResource("/Style/bootstrap3.css").toExternalForm());
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    @FXML
    private void club(ActionEvent event) throws IOException {
         mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/views/UserClubAff.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    @FXML
    private void jardin(ActionEvent event) {
    }
    
}
