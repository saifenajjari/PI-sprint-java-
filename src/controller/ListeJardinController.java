package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.JardinService;
import entites.CustomImage;
import entites.Jardin;
import entites.ReservationJardin;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;
import services.RatingService;

public class ListeJardinController {

    //@FXML
   // private ComboBox<String>    test = new ComboBox<String>();

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
    private Rating rating;
    @FXML
    private TextField tfrecherche;
    @FXML 
    private Label label_rating;
    @FXML
    private Button save;
    

    @FXML
    private Button btAjout;
    @FXML
    private AnchorPane ancrelist;
    //For MultiThreading
    private Executor exec;
    Image img;
    TableView<CustomImage> tableview;
    //Initializing the controller class.
    //This method is automatically called after the fxml file has been loaded.
    JardinService j = new JardinService();
    Jardin jardin =new Jardin();
    private void initialize  ()  {
      

        //For multithreading: Create executor that uses daemon threads:
        exec = Executors.newCachedThreadPool((runnable) -> {
            Thread t = new Thread (runnable);
            t.setDaemon(true);
            return t;
        });
        //changement des etoiles 
          rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
              label_rating.setText(t1.intValue()+"");
              System.out.println(t1.intValue()+" rating");
            }
            
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


        idColumn.setCellValueFactory(new PropertyValueFactory<Jardin, Integer>("id"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<Jardin, Image>("imageload"));
        nomColumn.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getNom()));
        adresseColumn.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getAddress()));
        placeColumn.setCellValueFactory(cellData ->new SimpleIntegerProperty(cellData.getValue().getPlacesDisponibles()).asString());
        nombreColumn.setCellValueFactory(cellData ->new SimpleIntegerProperty(cellData.getValue().getNbenfant()).asString());
        telColumn.setCellValueFactory(cellData ->new SimpleIntegerProperty(cellData.getValue().getNumTel()).asString());



//usually list of combobox items here
     

                        



        ObservableList<String> list = j.getAllAdress();

        InitialiserJardin();

      //  Jardin jardin = (Jardin) jardinTable.getSelectionModel().getSelectedItem();
        jardinTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if(jardinTable.getSelectionModel().getSelectedItem() != null)
                {

                    Jardin jardin = (Jardin) jardinTable.getSelectionModel().getSelectedItem();
                    if(jardin.getPlacesDisponibles() >0)
                    btAjout.setVisible(true);
                    else
                    btAjout.setVisible(false);
                }
            }
        });
  

    }

   

    private void InitialiserJardin() {
        try {

            j = new JardinService();
            //Get all Employees information
            ObservableList<Jardin> Data = j.getAll();
            //Populate Employees on TableView
            populateJardins(Data);
             jardinTable.getSelectionModel().clearSelection();
           // jardinTable.getSelectionModel().select(0);
           // resultArea.setText("liste des jardin");
            // JOptionPane.showMessageDialog(null,"liste des jardin "+label_rating.getText());
        } catch (SQLException e){
           // resultArea.setText("Aucune jardin trouvé \n");
             JOptionPane.showMessageDialog(null,"Aucune jardin trouvé \n "+label_rating.getText());

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


  



    //Populate Jardins for TableView
    private void populateJardins (ObservableList<Jardin> Data) throws ClassNotFoundException {
        //Set items to the employeeTable
        jardinTable.setItems(Data);
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

    }

    //Populate Jardin
    private void populateJardin (Jardin jardin) throws ClassNotFoundException {

        ObservableList<Jardin> Data = FXCollections.observableArrayList();
        System.out.println(Data);
        Data.add(jardin);
       
        jardinTable.setItems(Data);
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
    }


    private void setJardinInfoToTextArea ( Jardin jardin) {
       // resultArea.setText("Nom: " + jardin.getNom() + "\n" + "Adresse: " + jardin.getAddress());
         JOptionPane.showMessageDialog(null,"Nom: " + jardin.getNom() + "\n" + "Adresse: " + jardin.getAddress());
        
    }


    private void populateAndShowJardin(Jardin jardin) throws ClassNotFoundException {
        if (jardin != null) {

            populateJardin(jardin);
            setJardinInfoToTextArea(jardin);
        } else {
             JOptionPane.showMessageDialog(null,"jardin non trouvé!\n");
        //    resultArea.setText("jardin non trouvé!\n");
        }
    }





    @FXML
    private void ajouterRes(ActionEvent event) {
        if (event.getTarget() == btAjout) {
            try {
                //AnchorPane newLoadedPane = FXMLLoader.load(ListeJardinController.class.getResource("/sample/view/EnfantView.fxml"));
                FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/ReservationView.fxml"));
                AnchorPane newLoadedPane =loader.load();
                ancrelist.getChildren().clear();
                ancrelist.getChildren().add(newLoadedPane);
                ReservationController enf = loader.getController();
                Jardin  j = (Jardin) jardinTable.getSelectionModel().getSelectedItem();
                enf.LOG2(j.getId()+"",j.getNom());


            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
//ajouter rating
@FXML
    private void save_rating (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
     RatingService rs=new RatingService();
      
      Jardin  jardinrating = (Jardin) jardinTable.getSelectionModel().getSelectedItem();
     
              
              entites.Rating Data =  rs.rechercheparIdjardin(jardinrating.getId());
              System.out.println( rs.rechercheparIdjardin(jardinrating.getId()) +"tesssttttt");
              if (Data==null){
              entites.Rating r;
              r = new  entites.Rating(jardinrating.getId(),Float.parseFloat(label_rating.getText()));
              rs.insererRating(r);
              JOptionPane.showMessageDialog(null,"rating modifié "+label_rating.getText());
              }else{
              Float result =(Data.getRating()+ Float.parseFloat(label_rating.getText()))/2;
               entites.Rating r;
              r = new  entites.Rating(Data.getId_jardin(),result);
              rs.modifierJardin(r);
              JOptionPane.showMessageDialog(null,"rating modifié "+label_rating.getText());
              
              
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

