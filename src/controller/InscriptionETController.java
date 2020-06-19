/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entites.User;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class InscriptionETController implements Initializable {

    @FXML
    private Pane paneIE;
    @FXML
    private ComboBox<String> Comboboxsexe;
    @FXML
    private TextField email;
    @FXML
    private TextField pasword;
    @FXML
    private TextField username;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private Button retour1;
    @FXML
    private Button ok1;
     ObservableList<String> list=FXCollections.observableArrayList("Admin","User");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Comboboxsexe.setItems(list);

    }    

    @FXML
    private void retour1(ActionEvent event) throws IOException {
          Stage stage = (Stage) paneIE.getScene().getWindow();
                System.out.println("redirection to login");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("views/Login1.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    

    @FXML
    private void signinEt1(ActionEvent event) throws SQLException, AWTException, IOException {
         System.err.println("test inscription");
        
        User p = new User();
        
        
//        p.setUsername(username.getText());
//        p.setPassword(pasword.getText());
//       
//        p.setNom(nom.getText());
//        p.setPrenom(prenom.getText());
//        p.setEmail(email.getText());
//        
//        
//        p.setRoles((String) Comboboxsexe.getValue());
     String x =Comboboxsexe.getValue();
//        
       
        UserService.creerUser(username.getText(),pasword.getText(),x ,email.getText(),nom.getText(),prenom.getText());
        
               
//          System.err.println("insertion effectue");
       
        Stage stage = (Stage) ok1.getScene().getWindow();
                System.err.println("bbb2");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("views/Login1.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

}
