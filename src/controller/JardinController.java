package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import services.JardinService;
import entites.Jardin;


import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 * Created by ONUR BASKIRT on 23.02.2016.
 */
public class JardinController {

    @FXML
    private TextField nomText;
     @FXML
    private TextField idText;
    @FXML
    private TextField adresseText;
    @FXML
    private TextField capaciteText;
    @FXML
    private TextField descriptionText;
    @FXML
    private TextField numTelText;
    @FXML
    private TextField classeText;
    @FXML
    private TextField resultArea;
    @FXML
    private Button image;
    @FXML
    private javafx.scene.image.ImageView imageload;
    @FXML
    private Button addjardinBtn;
    //For MultiThreading
    private Executor exec;
    final FileChooser fileChooser = new FileChooser();
    private Desktop desktop = Desktop.getDesktop();
    Image img;
    byte[] bytes;
    File file;
     String nameImage=null;
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

        image = new Button("Open a Picture...");
       

    }


    //Ajouter jardin to the DB
    @FXML
    private void ajouterjardin(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
     
       
       if(file!=null){
            try {

     
                FileInputStream f = new FileInputStream(file);
                String chemin = file.getPath().toString();
                nameImage = chemin.substring(chemin.lastIndexOf('\\'));
                nameImage = nameImage.substring(1,nameImage.length());
                Image image = new Image( "file:///"+file.getPath());
               
//                saveToFile(image,nameImage);
                 try {
               JardinService sp1 = new JardinService();
               
            Jardin a1 = new Jardin(nomText.getText(), adresseText.getText(), Integer.parseInt(capaciteText.getText()), descriptionText.getText(), Integer.parseInt(numTelText.getText()), classeText.getText(),nameImage);
            sp1.insererJardin(a1);
          //  resultArea.setText("votre jardin a été ajouté avec succée! \n");
            JOptionPane.showMessageDialog(null,"votre jardin a été ajouté avec succée! \n");
               } catch (SQLException e) {
                   JOptionPane.showMessageDialog(null,"probléme d'insertion dans le serveur! \n");
         //   resultArea.setText("Problem occurred while inserting employee " + e);
            throw e;

               }
               
                 
            } catch (IOException e) {
                System.out.println("Exception :: "+ e.getMessage() );
            } }else{
   JOptionPane.showMessageDialog(null,"insérer une image \n");

       }
    }
   
//    private void saveToFile(Image image ,String nameImage) {
//        File outputFile = new File("C:/wamp64/www/JardinEnfant/web/uploads/"+nameImage);
//        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
//        try {
//            ImageIO.write(bImage, "png", outputFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    //Ajouter image to the DB
    @FXML
    private void ajouterimage(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        configureFileChooser(fileChooser);

         file = fileChooser.showOpenDialog(((Node)actionEvent.getTarget()).getScene().getWindow());
        if (file != null) {
           // openFile(file);

            img = new Image(file.toURI().toString());
            imageload.setImage(img);
        }


    }
    
    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }
    
  
}