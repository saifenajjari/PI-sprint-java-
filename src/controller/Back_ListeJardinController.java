/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entites.CustomImage;
import entites.Jardin;
import entites.ReservationJardin;
import com.jfoenix.controls.JFXTextField;
import entites.Evennement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import services.JardinService;


/**
 *
 * @author CASA INFO M
 */
public class Back_ListeJardinController {
  //@FXML
   // private ComboBox<String>    test = new ComboBox<String>();
    @FXML
    private AnchorPane ancrelist;
    @FXML
    private TableView jardinTable;
    @FXML
    private TableColumn<Jardin, Integer> idColumn ;
    @FXML
    private TableColumn<Jardin, Image> imageColumn ;
    @FXML
    private TableColumn<Jardin, String>  nomColumn;
    @FXML
    private TableColumn<Jardin, String> adresseColumn;
    @FXML
    private TableColumn<Jardin, String> placeColumn;
    @FXML
    private TableColumn<Jardin, String> nombreColumn;
    @FXML
    private TableColumn<Jardin, String> telColumn;
   
    @FXML
    private TextField tfrecherche;
    @FXML
    private Button btAjout;
    @FXML
    private Button bmodif;
    @FXML
    private Button suppmodif;
   
    //For MultiThreading
    private Executor exec;
    Image img;
 
   
   
    JardinService j = new JardinService();
    Jardin jardin =new Jardin();
    private void initialize  (URL url, ResourceBundle rb)  {
        ArrayList<Jardin> arrayList = null;
        try {
            arrayList = (ArrayList<Jardin>) j.getAll();
        
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Back_ListeJardinController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Back_ListeJardinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList obs = FXCollections.observableArrayList(arrayList);
        jardinTable.setItems(obs);
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
        exec = Executors.newCachedThreadPool((runnable) -> {
            Thread t = new Thread (runnable);
            t.setDaemon(true);
            return t;
        });

        imageColumn.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(50);
            imageview.setFitWidth(50);

            //Set up the Table
            TableCell<Jardin, Image> cell = new TableCell<Jardin, Image>() {
                public void updateItem(Image item, boolean empty) {
                    if (item != null) {
                        imageview.setImage(item);
                    }
                }
            };
            // Attach the imageview to the cell
            cell.setGraphic(imageview);
            return cell;
        });


//        idColumn.setCellValueFactory(new PropertyValueFactory<Jardin, Integer>("id"));
//        imageColumn.setCellValueFactory(new PropertyValueFactory<Jardin, Image>("imageload"));
//        nomColumn.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getNom()));
//        adresseColumn.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getAddress()));
//        placeColumn.setCellValueFactory(cellData ->new SimpleIntegerProperty(cellData.getValue().getPlacesDisponibles()).asString());
//        nombreColumn.setCellValueFactory(cellData ->new SimpleIntegerProperty(cellData.getValue().getNbenfant()).asString());
//        telColumn.setCellValueFactory(cellData ->new SimpleIntegerProperty(cellData.getValue().getNumTel()).asString());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("imageload"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("placesDisponibles"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nbenfant")); 
        telColumn.setCellValueFactory(new PropertyValueFactory<>("numTel")); 


//        InitialiserJardin();


    }

    


    private void InitialiserJardin() {
        try {

            j = new JardinService();
            //Get all jardin information 
            ObservableList<Jardin> Data = j.getAll();
            //Populate Employees on TableView
            populateJardins(Data);

       
         //   JOptionPane.showMessageDialog(null,"liste des jardin");
        } catch (SQLException e){
          //  resultArea.setText("Aucune jardin trouvé \n");
         JOptionPane.showMessageDialog(null,"Aucune jardin trouvé \n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            try {
                throw e;
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        jardinTable.getSelectionModel().select(1);
    }


    //Search an employee
   


    //Populate Jardins for TableView
    private void populateJardins (ObservableList<Jardin> Data) throws ClassNotFoundException {
        //Set items to the employeeTable
        jardinTable.setItems(Data);

    }

    //Populate Jardin
   

    private void setJardinInfoToTextArea ( Jardin jardin) {
        
           JOptionPane.showMessageDialog(null,"Nom: " + jardin.getNom() + "\n" +"\n" +"Adresse: " + jardin.getAddress());
    }


 




    @FXML
    private void ajouterJardin(ActionEvent event) {
        if (event.getTarget() == btAjout) {
            try {
                //AnchorPane newLoadedPane = FXMLLoader.load(ListeJardinController.class.getResource("/sample/view/EnfantView.fxml"));
                FXMLLoader loader= new FXMLLoader(getClass().getResource("/views/JardinView.fxml"));
                AnchorPane newLoadedPane =loader.load();
                ancrelist.getChildren().clear();
                ancrelist.getChildren().add(newLoadedPane);
               JardinController jar = loader.getController();
                
               
              


            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     @FXML
    private void modifjardin(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        if (event.getTarget() == bmodif) {
            try {
                //AnchorPane newLoadedPane = FXMLLoader.load(ListeJardinController.class.getResource("/sample/view/EnfantView.fxml"));
                FXMLLoader loader= new FXMLLoader(getClass().getResource("/views/ModifierJardinView.fxml"));
                AnchorPane newLoadedPane =loader.load();
                ancrelist.getChildren().clear();
                ancrelist.getChildren().add(newLoadedPane);
                ModifierJardinController jar = loader.getController();
                Jardin  jentity = (Jardin) jardinTable.getSelectionModel().getSelectedItem();
                // j=new JardinService();
               // Jardin  jardinrech = j.rechercheparId(jentity.getId());
               //   System.out.println(jardinrech);
                jar.LOG2(jentity.getId()+"",jentity.getNom(),jentity.getAddress(),jentity.getNumTel()+"",jentity.getDescription(),jentity.getNbenfant()+"",jentity.getImage(),jentity.getOrganisationDesClasses()+"",jentity.getPlacesDisponibles()+"");
              

            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                throw ex;
            }
        }
    }
      @FXML
    private void supprimerjardin(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        if (event.getTarget() == suppmodif) {
            Jardin  jentity = (Jardin) jardinTable.getSelectionModel().getSelectedItem();
            if (jentity.getNbenfant()==0){
            j=new JardinService();
            j.supprimerJardin(jentity);
            jardinTable.refresh();
            InitialiserJardin();
            }else{
             BoxBlur blur = new BoxBlur(3, 3, 3);
        ancrelist.setEffect(blur);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("suppression impossible");
        alert.setHeaderText("");
        alert.setContentText("On peut pas supprimer cet jardin car elle contient des reservations!!");
        alert.showAndWait();
        ancrelist.setEffect(null);
        
       
    
            }
        }
    }
    

  @FXML
    private void rechercherNom(javafx.scene.input.KeyEvent event) {

        JardinService js= new JardinService();
        ArrayList<Jardin> j = new ArrayList<>();
        try {
           j = (ArrayList<Jardin>) js.rechercherNom(
                    tfrecherche.getText());
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Jardin> obsl = FXCollections.observableArrayList(j);

        jardinTable.setItems(obsl);
        imageColumn.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(50);
            imageview.setFitWidth(50);

            //Set up the Table
            TableCell<Jardin, Image> cell = new TableCell<Jardin, Image>() {
                public void updateItem(Image item, boolean empty) {
                    if (item != null) {
                        imageview.setImage(item);
                    }
                }
            };
            // Attach the imageview to the cell
            cell.setGraphic(imageview);
            return cell;
        });


    idColumn.setCellValueFactory(new PropertyValueFactory<Jardin, Integer>("id"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<Jardin, Image>("imageload"));
        nomColumn.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getNom()));
        adresseColumn.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getAddress()));
        placeColumn.setCellValueFactory(cellData ->new SimpleIntegerProperty(cellData.getValue().getPlacesDisponibles()).asString());
        nombreColumn.setCellValueFactory(cellData ->new SimpleIntegerProperty(cellData.getValue().getNbenfant()).asString());
        telColumn.setCellValueFactory(cellData ->new SimpleIntegerProperty(cellData.getValue().getNumTel()).asString());



        // prixtotale.setCellValueFactory(new PropertyValueFactory<>("5"));
    }
    
}


