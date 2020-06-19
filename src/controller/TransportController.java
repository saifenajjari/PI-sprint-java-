/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import com.sun.prism.impl.Disposer;
import entites.Bus;
import entites.Chauffeur;
import entites.Ligne;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.joda.time.DateTime;
import services.BusService;
import services.ChauffeurService;
import services.LigneService;

/**
 * FXML Controller class
 *
 * @author asus_pc
 */
public class TransportController implements Initializable {
    @FXML
    private JFXTextField search;

   

    @FXML
    private TableColumn<Bus, String> matriculeBusLigne;
    @FXML
    private TableColumn<Bus, String> marqueBusLigne;
    @FXML
    private TableView<Bus> tableBusLigne;

    @FXML
    private TableView<Ligne> tableLigne;

    @FXML
    private TableColumn<Ligne, String> nomLigne;
    @FXML
    private TableColumn<Ligne, String> pointDepart;
    @FXML
    private TableColumn<Ligne, String> pointArriver;
    @FXML
    private TableColumn<Ligne,LocalTime> dateDepart;

    @FXML
    private TableColumn<Ligne, Integer> placesDispo;
    @FXML
    TableColumn actionLigne;
    @FXML
    private JFXTextField pointDepartTxt;
    @FXML
    private JFXTextField poitArriveTxt;
    @FXML
    private JFXTextField NomLigneTxt;
    @FXML
    private JFXTextField placeDispoTxt;

    @FXML
    private JFXTextField matriculeTxt;
    @FXML
    private JFXTextField marqueTxt;
    @FXML
    private TableView<Bus> tableBus;
    @FXML
    private TableView<Chauffeur> tableChauffeur;
    @FXML
    private TableView<Chauffeur> tableChauffeurBus;
    @FXML
    TableColumn<Bus, String> matricule;
    @FXML
    TableColumn<Bus, String> marque;
    @FXML
    TableColumn actionBus;
    @FXML
    TableColumn<Chauffeur, String> nom;
    @FXML
    TableColumn<Chauffeur, String> nomC;
    @FXML
    TableColumn<Chauffeur, String> prenom;
    @FXML
    TableColumn<Chauffeur, String> prenomC;
    @FXML
    TableColumn<Chauffeur, Integer> cin;
    @FXML
    TableColumn<Chauffeur, Integer> cinC;

    @FXML
    private TableColumn col_action;

    @FXML
    private JFXTextField nomTxt;

    @FXML
    private JFXTextField cinTxt;
    
    @FXML
    private TextField minute;
    
    @FXML
    private TextField heure;

    @FXML
    private JFXTextField prenomTxt;
    private int current_id;
    private int idChauffeur = -1;
    private int current_idBus;
    private int current_idLigne;
    private int current_idChauffeur;
    private int idBus = -1;
    /**
     * Initializes the controller class.
     */
    ChauffeurService ps = new ChauffeurService();
    public ObservableList<Chauffeur> list = FXCollections.observableArrayList(
            ps.afficher()
    );
    BusService bs = new BusService();
    public ObservableList<Bus> listBus = FXCollections.observableArrayList(
            bs.afficher()
    );
    LigneService ls = new LigneService();
    public ObservableList<Ligne> listLigne = FXCollections.observableArrayList(
            ls.afficher()
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   /* LocalDateTime myDateObj = LocalDateTime.now(); 
    
    System.out.println("Before formatting: " + myDateObj);  
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
    
    String formattedDate = myDateObj.format(myFormatObj);  
    System.out.println("After formatting: " + formattedDate); */
      FilteredList<Chauffeur> filteredData = new FilteredList<>(list, e -> true);

        search.setOnKeyReleased(e -> {
            search.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Chauffeur>) chauffeur -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lower = newValue.toLowerCase();
                    if (chauffeur.getNom().toLowerCase().contains(lower)) {
                        return true;
                    }

                    return false;
                });
            });
            SortedList<Chauffeur> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tableChauffeur.comparatorProperty());
            tableChauffeur.setItems(sortedData);
        });
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        nomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomC.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cinC.setCellValueFactory(new PropertyValueFactory<>("cin"));
        nomLigne.setCellValueFactory(new PropertyValueFactory<>("nom"));
        pointDepart.setCellValueFactory(new PropertyValueFactory<>("pointDepart"));
        pointArriver.setCellValueFactory(new PropertyValueFactory<>("pointArrive"));
        dateDepart.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
        placesDispo.setCellValueFactory(new PropertyValueFactory<>("placesDisponibles"));
        matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        matriculeBusLigne.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        marqueBusLigne.setCellValueFactory(new PropertyValueFactory<>("marque"));
        col_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        col_action.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell();
            }

        });

        actionLigne.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        actionLigne.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell2();
            }

        });

        actionBus.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        actionBus.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell1();
            }

        });

        tableChauffeur.setItems(list);
        tableChauffeurBus.setItems(list);
        tableBus.setItems(listBus);
        tableLigne.setItems(listLigne);
        tableBusLigne.setItems(listBus);
        tableChauffeur.setRowFactory(tv -> {
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
        tableChauffeurBus.setRowFactory(tv -> {
            TableRow<Chauffeur> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Chauffeur rowData = row.getItem();
                    idChauffeur = rowData.getId();
                }
            });
            return row;
        });
        tableBusLigne.setRowFactory(tv -> {
            TableRow<Bus> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Bus rowData = row.getItem();
                    idBus = rowData.getId();
                }
            });
            return row;
        });
        tableBus.setRowFactory(tv -> {
            TableRow<Bus> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Bus rowData = row.getItem();
                    matriculeTxt.setText(rowData.getMatricule());
                    marqueTxt.setText(rowData.getMarque());
                    current_idBus = rowData.getId();
                    current_idChauffeur = rowData.getIdChauffeur();
                }
            });
            return row;
        });
        tableLigne.setRowFactory(tv -> {
            TableRow<Ligne> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Ligne rowData = row.getItem();
                    pointDepartTxt.setText(rowData.getPointDepart());
                    poitArriveTxt.setText(rowData.getPointArrive());
                    NomLigneTxt.setText(rowData.getNom());
                    placeDispoTxt.setText(Integer.toString(rowData.getPlacesDisponibles()));
                    current_idBus = rowData.getBus_id();
                    current_idLigne = rowData.getId();
                    heure.setText(Integer.toString(rowData.getDateDepart().getHour()));
                    minute.setText(Integer.toString(rowData.getDateDepart().getMinute()));
                }
            });
            return row;
        });

    }

    private class ButtonCell extends TableCell<Disposer.Record, Boolean> {

        final Button cellButton = new Button("Delete");

        ButtonCell() {

            //Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    //confirmation alert 
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("delete Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("are you sure ?");

                    Optional<ButtonType> action = alert.showAndWait();
                    if (action.get() == ButtonType.OK) {
                        Chauffeur currentChauffeur = (Chauffeur) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());

                        ChauffeurService ps = new ChauffeurService();
                        int bus_id = ps.getBusID(currentChauffeur.getId());
                        System.out.println("bus_id = " + bus_id);
                        ps.setChauffeurNull(bus_id);
                        ps.deleteLigne(bus_id);
                        ps.supprimer(currentChauffeur);
                        list.remove(currentChauffeur);
                    }
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                cellButton.getStyleClass().add("btn");
                setGraphic(cellButton);
            } else {
                setGraphic(null);
            }
        }
    }

    private class ButtonCell1 extends TableCell<Disposer.Record, Boolean> {

        final Button cellButton1 = new Button("Delete");

        ButtonCell1() {

            //Action when the button is pressed
            cellButton1.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    //confirmation alert 
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("delete Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("are you sure ?");

                    Optional<ButtonType> action = alert.showAndWait();
                    if (action.get() == ButtonType.OK) {
                        Bus currentBus = (Bus) ButtonCell1.this.getTableView().getItems().get(ButtonCell1.this.getIndex());

                        BusService ps = new BusService();
                        ps.supprimer(currentBus);
                        listBus.remove(currentBus);
                    }
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                cellButton1.getStyleClass().add("btn");
                setGraphic(cellButton1);
            } else {
                setGraphic(null);
            }
        }
    }

    private class ButtonCell2 extends TableCell<Disposer.Record, Boolean> {

        final Button cellButton2 = new Button("Delete");

        ButtonCell2() {

            //Action when the button is pressed
            cellButton2.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    //confirmation alert 
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("delete Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("are you sure ?");

                    Optional<ButtonType> action = alert.showAndWait();
                    if (action.get() == ButtonType.OK) {
                        Ligne currentLigne = (Ligne) ButtonCell2.this.getTableView().getItems().get(ButtonCell2.this.getIndex());
                        LigneService ps = new LigneService();
                        ps.supprimer(currentLigne);
                        listLigne.remove(currentLigne);
                    }
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                cellButton2.getStyleClass().add("btn");
                setGraphic(cellButton2);
            } else {
                setGraphic(null);
            }
        }
    }
    public void showNotif(String message){
         Image img = new Image("/uploads/error.png");
                ImageView imgV = new ImageView(img);
                imgV.setFitHeight(50);
                imgV.setFitWidth(50);
                
                  Notifications notif = Notifications.create()
                .title("  erreur")
                .text(message)
                .graphic(imgV)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_CENTER)
                
                          .onAction(s->{
                         // System.out.println("notif clicked");
                          });
                notif.show();
    
    
    }
    boolean validateFieldsChauffeur(){
        if(cinTxt.getText().isEmpty()||nomTxt.getText().isEmpty()||prenomTxt.getText().isEmpty()){
        showNotif(" s'il vous plait de remplir tous les champs !");
        return false;}
        
    return true;
    }
    
    @FXML
    void onAjouterAction(ActionEvent event) {
        if(validateFieldsChauffeur()){
        int cin = Integer.parseInt(cinTxt.getText());
        Chauffeur c = new Chauffeur(nomTxt.getText(), prenomTxt.getText(), cin);
        ChauffeurService sc = new ChauffeurService();
        sc.ajouter(c);
        /**
         * refreshing the table view *
         */
        list.clear();
        list = FXCollections.observableArrayList(
                sc.afficher()
        );
        tableChauffeur.setItems(list);
        tableChauffeurBus.setItems(list);
        }
    }

    @FXML
    void onModifierChauffeurAction(ActionEvent event) {
        ChauffeurService ps = new ChauffeurService();

        int cin = Integer.parseInt(cinTxt.getText());
        Chauffeur p = new Chauffeur(current_id, nomTxt.getText(), prenomTxt.getText(), cin);
        System.out.println("chauffeur = " + p);
        ps.modifier(p);
        /**
         * refreshing the table view *
         */
        list.clear();
        list = FXCollections.observableArrayList(
                ps.afficher()
        );
        tableChauffeur.setItems(list);
        tableChauffeurBus.setItems(list);
    }

    @FXML
    void onModifierBusAction(ActionEvent event) {
        Bus b;
        if (idChauffeur != -1) {
            b = new Bus(current_idBus, matriculeTxt.getText(), marqueTxt.getText(), idChauffeur);
        } else {
            b = new Bus(current_idBus, matriculeTxt.getText(), marqueTxt.getText(), current_idChauffeur);
        }
        BusService bs = new BusService();
        bs.modifier(b);
        /**
         * refreshing the table view *
         */
        listBus.clear();
        listBus = FXCollections.observableArrayList(
                bs.afficher()
        );
        tableBus.setItems(listBus);
        tableBusLigne.setItems(listBus);

    }
    boolean validateFieldsBus(){
    if(matriculeTxt.getText().isEmpty()||marqueTxt.getText().isEmpty()){
        showNotif(" s'il vous plait de remplir tous les champs !");
        return false;
    }
    return true;
    }
    @FXML
    void onAjouterBusAction(ActionEvent event) {
        if(validateFieldsBus()){
        if (idChauffeur != -1) {
            Bus b = new Bus(matriculeTxt.getText(), marqueTxt.getText(), idChauffeur);
            BusService bs = new BusService();
            bs.ajouter(b);
            /**
             * refreshing the table view *
             */
            listBus.clear();
            listBus = FXCollections.observableArrayList(
                    bs.afficher()
            );
            tableBus.setItems(listBus);
            tableBusLigne.setItems(listBus);

        }
        }
    }
    boolean validatefieldsLigne(){
    if(pointDepartTxt.getText().isEmpty()||poitArriveTxt.getText().isEmpty()||NomLigneTxt.getText().isEmpty())
    {
    showNotif(" s'il vous plait de remplir tous les champs !");
    return false;
    }
    return true;
    }
    @FXML
    void onAjouterLigneAction(ActionEvent event) {
        if(validatefieldsLigne()&&validatefieldsTime()){
        if (idBus != -1) {
            int min = Integer.parseInt(minute.getText());
            int heur = Integer.parseInt(heure.getText());
            
            //ZoneId defaultZoneId = ZoneId.systemDefault();

            //local date + atStartOfDay() + default time zone + toInstant() = Date
           // Date date = Date.from(dateDepartPicker.getValue().atStartOfDay(defaultZoneId).toInstant());
 
             
            LocalTime myObj = LocalTime.now();
            myObj = myObj.withHour(heur);
            myObj = myObj.withMinute(min);
            myObj = myObj.withSecond(0);
            myObj = myObj.withNano(0);
            System.out.println("myObj = "+myObj);
            int nbPlaces = Integer.parseInt(placeDispoTxt.getText());
            Ligne l = new Ligne(idBus, pointDepartTxt.getText(), poitArriveTxt.getText(), myObj, NomLigneTxt.getText(), nbPlaces);
            LigneService bs = new LigneService();
            bs.ajouter(l);
            /**
             * refreshing the table view *
             */
          listLigne.clear();
            listLigne = FXCollections.observableArrayList(
                    bs.afficher()
            );
            tableLigne.setItems(listLigne);
}
        }

    }
    public boolean validatefieldsTime(){
        try {
        int min = Integer.parseInt(minute.getText());
        if(min<0||min>59){
            showNotif("minutes entre 0 et 59");
            return false;
        }
        } catch (NumberFormatException nfe) {
            showNotif("minutes numerique !");
        return false;
        }
        try {
        int heur = Integer.parseInt(heure.getText());
         if(heur<0||heur>23){
            showNotif("heures entre 0 et 23");
            return false;
        }
        } catch (NumberFormatException nfe) {
         showNotif("heures numerique !");
        return false;
        }
        
    return true;}
    @FXML
    void onModifierLigneAction(ActionEvent event) {
        if(validatefieldsTime()){
         Ligne l;
          LocalTime myObj = LocalTime.now();
                myObj = myObj.withHour(Integer.parseInt(heure.getText()));
                myObj = myObj.withMinute(Integer.parseInt(minute.getText()));
                myObj = myObj.withSecond(0);
                myObj = myObj.withNano(0);
        if (idBus != -1) {
            l = new Ligne(current_idLigne,idBus, pointDepartTxt.getText(),poitArriveTxt.getText(),myObj,NomLigneTxt.getText(),Integer.parseInt(placeDispoTxt.getText()));
        } else {
            l = new Ligne(current_idLigne,current_idBus, pointDepartTxt.getText(),poitArriveTxt.getText(),myObj,NomLigneTxt.getText(),Integer.parseInt(placeDispoTxt.getText()));
        }
        LigneService bs = new LigneService();
        bs.modifier(l);
        /**
         * refreshing the table view *
         */
        listLigne.clear();
        listLigne = FXCollections.observableArrayList(
                bs.afficher()
        );
        tableLigne.setItems(listLigne);

    }}

}
