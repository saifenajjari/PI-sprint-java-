/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import entites.Activites;
import entites.Club;
import entites.Inscription;
import java.text.DateFormat;
import services.InscriptionService;
import services.ActivitesService;
import services.ClubService;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.time.Instant;
import java.time.LocalTime;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author USER
 */
public class PI extends Application {

@Override
    public void start(Stage stage) throws Exception {
  /*      Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();*/

            Parent root = FXMLLoader.load(getClass().getResource("/views/Login1.fxml"));        
            Scene scene = new Scene(root);
           // scene.setFill(Color.TRANSPARENT);
            //stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    
}
