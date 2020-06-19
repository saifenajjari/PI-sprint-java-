/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import entites.Chauffeur;
import entites.Resto;

import java.net.URL;

import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import static java.util.Collections.list;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.RestoService;

import com.jfoenix.controls.JFXTextField;
import com.sun.prism.impl.Disposer;
import entites.Plat;

import java.net.URL;

import static java.nio.file.Files.list;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.util.Collections.list;

import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.PlatService;

/**
 * FXML Controller class
 *
 * @author Haifa
 */
public class RestoController implements Initializable {

    private JFXTextField nomTxt;
    @FXML
    private JFXTextField descriptionTxt;
    @FXML
    private JFXTextField AdresseTxt;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXTextField NombredeplaceTxt;
    @FXML
    private TableView<Resto> tableRestaurant;
    @FXML
    private TableColumn<Resto, String> nomR;
    @FXML
    private TableColumn<Resto, String> AdresseR;
    @FXML
    private TableView<Plat> tablePlat;
    @FXML
    private TableColumn<Plat, String> nomP;
    @FXML
    private TableColumn<Plat, String> descriptionP;
    @FXML
    private TableColumn<Plat, String> catégorieP;
    @FXML
    private TableColumn<Plat, String> prixP;
    @FXML
    private TableColumn<Resto, Integer> idResto;
    @FXML
    private TableColumn<Resto, String> nomResto;
    @FXML
    private TableColumn<Resto, String> descriptionResto;
    @FXML
    private TableColumn<Resto, String> adresseResto;
    @FXML
    private TableColumn<Resto, Integer> nbplace;
    @FXML
    private JFXTextField nomPTxt;
    @FXML
    private JFXTextField descriptionPTxt;
    @FXML
    private JFXTextField prixPTxt;
    @FXML
    private JFXTextField categoriePTxt;
    private JFXTextField idTxt;

    private int idrestomodif;
    private int idrestoplat;
    private int idplatmodif;

    @FXML
    private TableView<Resto> tableResto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idResto.setCellValueFactory(new PropertyValueFactory<Resto, Integer>("id"));
        nomResto.setCellValueFactory(new PropertyValueFactory<Resto, String>("nom"));
        descriptionResto.setCellValueFactory(new PropertyValueFactory<Resto, String>("description"));
        adresseResto.setCellValueFactory(new PropertyValueFactory<Resto, String>("adresse"));
        nbplace.setCellValueFactory(new PropertyValueFactory<Resto, Integer>("nbrdeplace"));
        nomR.setCellValueFactory(new PropertyValueFactory<Resto, String>("nom"));
        AdresseR.setCellValueFactory(new PropertyValueFactory<Resto, String>("adresse"));

        nomP.setCellValueFactory(new PropertyValueFactory<Plat, String>("nom"));
        descriptionP.setCellValueFactory(new PropertyValueFactory<Plat, String>("descp"));
        catégorieP.setCellValueFactory(new PropertyValueFactory<Plat, String>("categorie"));
        prixP.setCellValueFactory(new PropertyValueFactory<Plat, String>("prix"));

        tableRestaurant.setItems(list);
        tableResto.setItems(list);
        tableRestaurant.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            Resto r = tableRestaurant.getItems().get((int) newValue);
            idrestomodif = r.getId();
            nomTxt.setText(r.getNom());
            AdresseTxt.setText(r.getAdresse());
            descriptionTxt.setText(r.getDescription());
            NombredeplaceTxt.setText(r.getNbrdeplace() + "");

        });
        tableResto.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            Resto r = tableRestaurant.getItems().get((int) newValue);
            idrestoplat = r.getId();
            listP.clear();
            listP = FXCollections.observableArrayList(
                    pss.afficher(idrestoplat)
            );
            tablePlat.setItems(listP);

        });

        tablePlat.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            Plat p = tablePlat.getItems().get((int) newValue);
            idplatmodif = p.getId();
            nomPTxt.setText(p.getNom());
            descriptionPTxt.setText(p.getDescp());
            categoriePTxt.setText(p.getCategorie());
            prixPTxt.setText(p.getPrix() + "");

            listP.clear();
            listP = FXCollections.observableArrayList(
                    pss.afficher(idrestoplat)
            );
            tablePlat.setItems(listP);

        });
        
        
        FilteredList<Resto> filteredData = new FilteredList<>(list, e -> true);

        search.setOnKeyReleased(e -> {
            search.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Resto>) resto -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lower = newValue.toLowerCase();
                    if (resto.getNom().toLowerCase().contains(lower)) {
                        return true;
                    }

                    return false;
                });
            });
            SortedList<Resto> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tableRestaurant.comparatorProperty());
            tableRestaurant.setItems(sortedData);
        });
    }

    RestoService ps = new RestoService();
    public ObservableList<Resto> list = FXCollections.observableArrayList(
            ps.afficher()
    );
    PlatService pss = new PlatService();

    public ObservableList<Plat> listP = FXCollections.observableArrayList();

    /* public ObservableList<Resto> list = FXCollections.observableArrayList(
      tableRestaurant.setItems(list));
      tableRestaurant.setRowFactory(tv -> {

             TableRow<Chauffeur> row = new TableRow<>();
             row.setOnMouseClicked(event -> {
                 if (!row.isEmpty()) {
                     Chauffeur rowData = row.getItem();
                     nomTxt.setText(rowData.getNom());
                     prenomTxt.setText(rowData.getPrenom());
                     cinTxt.setText(Integer.toString(rowData.getCin()));
                     current_id = rowData.getId();
                 }
             });
             return row;
         });
     */
    public void showNotif() {
        Image img = new Image("/uploads/error.png");
        ImageView imgV = new ImageView(img);
        imgV.setFitHeight(50);
        imgV.setFitWidth(50);

        Notifications notif = Notifications.create()
                .title("  erreur")
                .text(" s'il vous plait de remplir tous les champs !")
                .graphic(imgV)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_CENTER)
                .onAction(s -> {
                    // System.out.println("notif clicked");
                });
        notif.show();

    }

    boolean validateFieldsResto() {
        if (nomTxt.getText().isEmpty() || descriptionTxt.getText().isEmpty() || AdresseTxt.getText().isEmpty() || NombredeplaceTxt.getText().isEmpty()) {
            showNotif();
            return false;
        }

        return true;
    }

    @FXML
    private void onAjouterRestoAction(ActionEvent event) {

        if (validateFieldsResto()) {
            int nbr = Integer.parseInt(NombredeplaceTxt.getText());
            Resto c = new Resto(nomTxt.getText(), descriptionTxt.getText(), AdresseTxt.getText(), nbr);
            RestoService sc = new RestoService();

            sc.ajouter(c);

            /**
             * refreshing the table view *
             */
            list.clear();
            list = FXCollections.observableArrayList(
                    sc.afficher()
            );

            tableRestaurant.setItems(list);
            tableResto.setItems(list);
        }

    }

    @FXML
    private void onSupprimerRestoAction(ActionEvent event) {
        if (idrestomodif != 0) {
            RestoService sc = new RestoService();
            sc.supprimer(idrestomodif);
            list.clear();
            list = FXCollections.observableArrayList(
                    sc.afficher()
            );
            tableRestaurant.setItems(list);
            tableResto.setItems(list);
        }
        idrestomodif = 0;
    }

    @FXML
    private void onModifierAction(ActionEvent event) {

        RestoService sc = new RestoService();
        int asd = Integer.parseInt(NombredeplaceTxt.getText());
        sc.modifier(new Resto(idrestomodif, nomTxt.getText(), descriptionTxt.getText(), AdresseTxt.getText(), asd));
        list.clear();
        list = FXCollections.observableArrayList(
                sc.afficher()
        );

        tableRestaurant.setItems(list);        
        tableResto.setItems(list);

    }

    private void onModifierPlatAction(ActionEvent event) {
        PlatService pss = new PlatService();
        int nbr = Integer.parseInt(prixPTxt.getText());
        Plat p = new Plat();
        p.setId(idplatmodif);
        p.setNom(nomPTxt.getText());
        p.setCategorie(categoriePTxt.getText());
        p.setDescp(descriptionPTxt.getText());
        p.setPrix(nbr);
        pss.modifier(p);

        listP.clear();
        listP = FXCollections.observableArrayList(
                pss.afficher(idrestoplat)
        );

        tablePlat.setItems(listP);
    }

    private void onSupprimerPlatAction(ActionEvent event) {
        if (idplatmodif != 0) {
            PlatService pss = new PlatService();
            pss.supprimer(idplatmodif);
            listP.clear();
            listP = FXCollections.observableArrayList(
                    pss.afficher(idrestoplat)
            );

            tablePlat.setItems(listP);
        }
        idrestomodif = 0;
    }

    @FXML
    private void onAjouterPlatAction(ActionEvent event) {

        int nbr = Integer.parseInt(prixPTxt.getText());
        Plat p = new Plat();
        p.setNom(nomPTxt.getText());
        p.setCategorie(categoriePTxt.getText());
        p.setDescp(descriptionPTxt.getText());
        p.setPrix(nbr);
        p.setResto_id(idrestoplat);

        PlatService pss = new PlatService();

        pss.ajouter(p);

        /**
         * refreshing the table view *
         */
        listP.clear();
        listP = FXCollections.observableArrayList(
                pss.afficher(idrestoplat)
        );

        tablePlat.setItems(listP);
    }

}
