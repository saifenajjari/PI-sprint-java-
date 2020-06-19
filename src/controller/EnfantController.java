package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import services.EnfantService;
import entites.Enfant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import views.NumFieldFX;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JOptionPane;

/**
 * Created by ONUR BASKIRT on 23.02.2016.
 */
public class EnfantController {
@FXML
    private AnchorPane anchor;
    @FXML
    private TextField nomText;
    @FXML
    private TextField prenomText;
    @FXML
    private  TextField ageText;
    @FXML
    private Spinner categorie;
    @FXML
    private Spinner<String> sexe;
    @FXML
    private TextField resultArea;
    @FXML
    private Button image;
    @FXML
    private javafx.scene.image.ImageView imageload;
    //For MultiThreading
    private Executor exec;
    final FileChooser fileChooser = new FileChooser();
    private Desktop desktop = Desktop.getDesktop();
    Image img;
    byte[] bytes;
    File file;
    Boolean filepath=false;
     private static final String TITLE = "Votre Inscription";
    public static final String PDF_EXTENSION = ".pdf";
    private List<Enfant> enfants;
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
        ObservableList<String> listcat = FXCollections.observableArrayList("Junior", "Senior");
        SpinnerValueFactory<String> valuecategorie = new SpinnerValueFactory.ListSpinnerValueFactory<String>(listcat);
        categorie.setValueFactory(valuecategorie);
     //  valuecategorie.setValue("hello");

        ObservableList<String> listsexe = FXCollections.observableArrayList("Masculin", "Feminin");
        SpinnerValueFactory<String> valuesexe= new SpinnerValueFactory.ListSpinnerValueFactory<String>(listsexe);
        sexe.setValueFactory(valuesexe);
      //  valuesexe.setValue("hello");
        image = new Button("Open a Picture...");
        
    }


    //Insert an employee to the DB
    @FXML
    private void ajouterenfant(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {

            String nameImage=null;

        if ( (isEmptynom(nomText.getText()))&&(isEmptyprenom(prenomText.getText()))&&(isParsable(ageText.getText())==true)&&( isEmpty()== false)){
            try {
                try {
                    FileInputStream f = new FileInputStream(file);
                    String chemin = file.getPath().toString();
                    nameImage = chemin.substring(chemin.lastIndexOf('\\'));
                    nameImage = nameImage.substring(1,nameImage.length());
                    Image image = new Image( "file:///"+file.getPath());
                    saveToFile(image,nameImage);
                } catch (IOException e) {
                     BoxBlur blur = new BoxBlur(3, 3, 3);
        anchor.setEffect(blur);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Repetez svp");
        alert.setHeaderText("choisir une image");
        //alert.setContentText("Verifiez vos champs svp!!");
        alert.showAndWait();
        anchor.setEffect(null);
                  //  resultArea.setText("choisir une image" );
                    System.out.println("Exception file:: "+ e.getMessage()+"here" );
                   
                }
                
                EnfantService Es = new EnfantService();
                Enfant e = new Enfant(nomText.getText(), prenomText.getText(), Integer.parseInt(ageText.getText()),categorie.getValue().toString(), sexe.getValue().toString(),nameImage);
                Es.insererEnfant(e);
                System.out.println("here" );
                JOptionPane.showMessageDialog(null,"votre enfant a été ajouté avec succée!  \n");
                
            } catch (SQLException e) {
                 JOptionPane.showMessageDialog(null,"Erreur de serveur  \n");
               // resultArea.setText("Probléme d'insertion " );
                System.out.println("here" );
                throw e;
                
            }}
             List<Enfant> dataObjList = getDataObjectList();
        Document document = null;
        try {
        //Document is not auto-closable hence need to close it separately
            document = new Document(PageSize.A4);            
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
                    new File(TITLE + PDF_EXTENSION)));
            HeaderFooter event = new HeaderFooter();
            event.setHeader("Test Report");
            writer.setPageEvent(event);
            document.open();
            PDFCreator.addMetaData(document, TITLE);
            PDFCreator.addTitlePage(document, TITLE);
            PDFCreator.addContent(document, dataObjList);
        }catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FileNotFoundException occurs.." + e.getMessage());
        }finally{
            if(null != document){
                document.close();
            }
        }
    }
    private void saveToFile(Image image ,String nameImage) {
        File outputFile = new File("C:/wamp64/www/JardinEnfant/web/uploads/"+nameImage);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Ajouter image to the DB
    @FXML
    private void ajouterimage(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        configureFileChooser(fileChooser);

        file = fileChooser.showOpenDialog(((Node)actionEvent.getTarget()).getScene().getWindow());
        if (file != null) {
            // openFile(file);

            img = new Image(file.toURI().toString());
            imageload.setImage(img);
            filepath=true;
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
    
    //control de saisie sur l'age de l'enfant
    public  boolean isParsable(String input){
    try{
      
        Integer.parseInt(input);
        return true;
    }catch(Exception e){
         BoxBlur blur = new BoxBlur(3, 3, 3);
			anchor.setEffect(blur);
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Repetez svp");
			alert.setHeaderText("l'age doit être numéric");
			//alert.setContentText("Verifiez vos champs svp!!");
			alert.showAndWait();
			anchor.setEffect(null);
                       // resultArea.setText("l'age doit être numéric" );
           
        
    return false;
}
    
}

//control de saisie sur l'image de l'enfant
public  boolean isEmpty() {
    boolean result=true;
     if(filepath==false){
         BoxBlur blur = new BoxBlur(3, 3, 3);
        anchor.setEffect(blur);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Repetez svp");
        alert.setHeaderText("insérer l'image de votre enfant");
        //alert.setContentText("Verifiez vos champs svp!!");
        alert.showAndWait();
        anchor.setEffect(null);
        
     //   resultArea.setText("insérer l'image de votre enfant" );
         result=true;
}else
         result=false;
     return result;
}
 public  List<Enfant> getDataObjectList(){
        List<Enfant> dataObjList = new ArrayList<Enfant>();
         EnfantService enfantService = new EnfantService();
        enfants = enfantService.getAll();
        for(Enfant enfant : enfants) { 
          Enfant d1 = new Enfant();
        d1.setNom(enfant.getNom());
        d1.setPrenom(enfant.getPrenom());
        d1.setAge(enfant.getAge());
        
          dataObjList.add(d1);
 }
         return dataObjList;    
     
      
      
    }

    private boolean isEmptynom(String nom) {
      if (nom.isEmpty()){
      
       BoxBlur blur = new BoxBlur(3, 3, 3);
			anchor.setEffect(blur);
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Repetez svp");
			alert.setHeaderText("nom ne pas être vide");
			//alert.setContentText("Verifiez vos champs svp!!");
			alert.showAndWait();
			anchor.setEffect(null);
                       // resultArea.setText("l'age doit être numéric" );
           
        
    return false;
       
    } else{
          return true;
    }
    }
        private boolean isEmptyprenom(String prenom) {
      if (prenom.isEmpty()){
      System.out.println(prenom+"tessssssssssssssssssst");
           BoxBlur blur = new BoxBlur(3, 3, 3);
			anchor.setEffect(blur);
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Repetez svp");
			alert.setHeaderText("prenom ne pas être vide");
			//alert.setContentText("Verifiez vos champs svp!!");
			alert.showAndWait();
			anchor.setEffect(null);
                       // resultArea.setText("l'age doit être numéric" );
           
        
    return false;
        
    } else{
          return true;
     
    }
    }
}
