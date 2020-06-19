/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import entites.Club;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import services.ClubService;



/**
 * FXML Controller class
 *
 * @author USER
 */
public class UserClubController implements Initializable {

    @FXML
    private ImageView img_piece;
    @FXML
    private Label jardin;
    @FXML
    private Label nbAct;
    @FXML
    private Label contact;
    @FXML
    private Label nom;
    private  Club club;
    private Button activité;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
        
        void getInfo(Club p){
        jardin.setText(p.getJardin());
        contact.setText(p.getContact());
        nom.setText(p.getNom());
        nbAct.setText(Integer.toString(p.getNbAct()));
        club = p;
//        try {
//            Image image = new Image(new FileInputStream(p.getImage()));
//            img_piece.setImage(image);
//        } catch (FileNotFoundException ex) {
//            System.out.println(ex);
//        }
            try {
           Image image = new Image("file:\\C:\\xampp\\htdocs\\JardinEnfant\\web\\uploads\\photos\\"+(p.getImage()));
                 img_piece.setImage(image);
                }
                catch(Exception e){
                    System.out.println(e);
                    
                }

        
    }
       
      

    
    Button getButton(){
        return activité;
    }
    
}
