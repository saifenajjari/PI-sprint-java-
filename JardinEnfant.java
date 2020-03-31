/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.io.IOException;
import java.sql.Connection;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DataSource;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;


/**
 *
 * @author yosra
 */
public class JardinEnfant extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Core.JardinEnfant.main()");
        // TODO code application logic here
       DataSource cbd = DataSource.getInstance();
       Connection cnx = cbd.getCnx();
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/views/FXML.fxml")); 

        primaryStage.setTitle("aaaaaaaaaa");
        
        Scene scene = new Scene(root);   
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);          
        primaryStage.show();
    }

}
