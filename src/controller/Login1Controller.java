/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entites.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Login1Controller implements Initializable {

    @FXML
    private TextField passeword;
    @FXML
    private TextField username;
    @FXML
    private Button Compte;
    @FXML
    private Button Login;
    @FXML
    private AnchorPane ap;
public static String test ;
 UserService us = new UserService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void choix(ActionEvent event) throws IOException {
         Stage stage = (Stage) ap.getScene().getWindow();
                System.out.println("redirection to login");
//            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("views/InscriptionET.fxml"));
                 Parent root = FXMLLoader.load(getClass().getResource("/views/InscriptionET.fxml"));        

            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }
    


    @FXML
    private void authentification(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        
        
//         System.err.println("Welcome");
//        
//        User p = new User();
//        p.setUsername(username.getText());
//        p.setPassword(passeword.getText());
//        
////        UserService sp = new UserService();
//       UserService.authentification(passeword.getText());
//     
//          
//        
//        Stage stage = (Stage) ap.getScene().getWindow();
//                System.err.println("connection etablie");
//         if(p.getRoles()=="Admin"){
//           FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("views/Admin.fxml"));
//           Parent root =(Parent) loader.load();
//            //System.err.println(info);
////            FXMLDocumentController  acceuil=loader.getController();
//          // acceuil.MyLogin(username.getText());
//           
//            
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
//         else {
//             FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("views/Home.fxml"));
//           Parent root =(Parent) loader.load();
//            //System.err.println(info);
////            FXMLDocumentController  acceuil=loader.getController();
//          // acceuil.MyLogin(username.getText());
//           
//            
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        }
//    
User p = new User();
       p.setUsername(username.getText());
      p.setPassword(passeword.getText());


 if (us.login(username.getText(), passeword.getText())!= null ){
    
         ap.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/views/Home.fxml"));
        ap.getChildren().add(parent);
        ap.toFront(); 
     
     
//        if(HomeController.user!=null)
//        ap.getScene().setRoot(FXMLLoader.load(getClass().getResource("/views/Home.fxml")));
 }
 else{   Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("VERIFICATION");
        alert.setHeaderText(null);
        alert.setContentText("User n'existe pas . ");
        alert.showAndWait(); }
    }} 

    
    

