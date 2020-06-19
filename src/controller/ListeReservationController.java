package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.JardinService;
import services.ReservationService1;
import entites.Enfant;
import entites.Jardin;
import entites.ReservationJardin;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListeReservationController {


@FXML
    private TableView tablereservation;
@FXML
    private TableColumn<ReservationJardin,Image> image;
@FXML
    private TableColumn<ReservationJardin,String>nom;
@FXML
    private TableColumn<ReservationJardin,String>prenom;
@FXML
    private TableColumn<ReservationJardin,String>jardin;
@FXML
    private TextField tfrecherche;
@FXML
    private TextField resultArea;
ReservationService1 reservationService = new ReservationService1();
    private Executor exec;
@FXML
    private void initialize() {


        //For multithreading: Create executor that uses daemon threads:
        exec = Executors.newCachedThreadPool((runnable) -> {
            Thread t = new Thread (runnable);
            t.setDaemon(true);
            return t;
        });

    image.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(50);
            imageview.setFitWidth(50);

            //Set up the Table
            TableCell<ReservationJardin, Image> cell = new TableCell<ReservationJardin, Image>() {
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


    //    idColumn.setCellValueFactory(new PropertyValueFactory<Jardin, Integer>("id"));
        image.setCellValueFactory(new PropertyValueFactory<ReservationJardin, Image>("imageload"));
        nom.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getNom_enfant()));
        prenom.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getPrenom_enfant()));
        jardin.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getNom_jardin()));





        InitialiserReservation();

    }

    private void InitialiserReservation() {

        try {

            reservationService = new ReservationService1();
            //Get all Employees information
            ObservableList<ReservationJardin> Data = reservationService.getAll();
            //Populate Employees on TableView
            populateReservation(Data);

//            resultArea.setText("liste des réservations");
 JOptionPane.showMessageDialog(null,"liste des réservations!  \n");
        } catch (SQLException e){
            //resultArea.setText("Aucune reservation trouvé \n");
             JOptionPane.showMessageDialog(null,"Aucune reservation trouvé   \n");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            try {
                throw e;
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        tablereservation.getSelectionModel().select(1);
    }

    //Populate Jardins for TableView
    @FXML
    private void populateReservation (ObservableList<ReservationJardin> Data) throws ClassNotFoundException {
        //Set items to the employeeTable

        tablereservation.setItems(Data);

    }
    @FXML
    private void rechercherNom(javafx.scene.input.KeyEvent event) {

        reservationService= new ReservationService1();
        ArrayList<ReservationJardin> reservations = new ArrayList<>();
        try {
            reservations = (ArrayList<ReservationJardin>) reservationService.rechercherNom(
                    tfrecherche.getText());
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<ReservationJardin> obsl = FXCollections.observableArrayList(reservations);

        tablereservation.setItems(obsl);
        image.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(50);
            imageview.setFitWidth(50);

            //Set up the Table
            TableCell<ReservationJardin, Image> cell = new TableCell<ReservationJardin, Image>() {
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


        //    idColumn.setCellValueFactory(new PropertyValueFactory<Jardin, Integer>("id"));
        image.setCellValueFactory(new PropertyValueFactory<ReservationJardin, Image>("imageload"));
        nom.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getNom_enfant()));
        prenom.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getPrenom_enfant()));
        jardin.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getNom_jardin()));


        // prixtotale.setCellValueFactory(new PropertyValueFactory<>("5"));
    }

}
