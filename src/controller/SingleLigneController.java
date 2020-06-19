/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import entites.Ligne;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import org.joda.time.DateTime;

/**
 * FXML Controller class
 *
 * @author asus_pc
 */
public class SingleLigneController implements Initializable {
    @FXML
    private Label labelHeure;
    @FXML
    private Label labelMinute;

    @FXML
    private Label PointDepartLabel;
    @FXML
    private Label pointArriveLabel;
  
    @FXML
    private Label NomLabel;
    @FXML
    private Label NombrePlaceLabel;
    @FXML
    private JFXButton reserverBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void getInfo(Ligne l) {
        PointDepartLabel.setText(l.getPointDepart());
        pointArriveLabel.setText(l.getPointArrive());
        labelHeure.setText(Integer.toString(l.getDateDepart().getHour()));
        labelMinute.setText(Integer.toString(l.getDateDepart().getMinute()));
        NomLabel.setText(l.getNom());
        NombrePlaceLabel.setText(Integer.toString(l.getPlacesDisponibles()));
    }

    JFXButton getButton() {
        return reserverBtn;
    }

}
