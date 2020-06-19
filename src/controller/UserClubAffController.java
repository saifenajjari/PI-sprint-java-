/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import entites.Club;
import entites.Inscription;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import services.ClubService;
import util.DataSource;


/**
 * FXML Controller class
 *
 * @author USER
 */
public class UserClubAffController implements Initializable {


    @FXML
    private FlowPane flow;
     private int id;
        private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    @FXML
    private AnchorPane versact;
    @FXML
    private Button activite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       flow.getChildren().clear();
        afficherClub();
        
    }  
    void afficherClub() {
        ArrayList<Club> club = new ArrayList<>();
        club = (ArrayList<Club>) getAll();
        ObservableList<Club> obsl = FXCollections.observableArrayList(club);
        Node[] nodes = new Node[obsl.size()];
        for (int i = 0; i < nodes.length; i++) {
           
            try {    
                
                FXMLLoader loader = new FXMLLoader();
               
                Pane root = loader.load(getClass().getResource("/views/UserClub.fxml").openStream());
                 UserClubController single = (UserClubController)loader.getController();
                 single.getInfo(club.get(i));
                 //Button button = single.getButton();
                  int id1 = club.get(i).getId();
                    
                 
//                     SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
//                     selectionModel.select(1); //select by index starting with 0
                     this.id = id1;
            
                 
            
               
                 
                nodes[i]=root;
                flow.getChildren().add(nodes[i]);
                
                
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }
    
       
       public List<Club> getAll()
       {
            List<Club> myList = new ArrayList();
        String sql="select * from club";
        Connection con = DataSource.connect();

        try {

                        ResultSet rs = con.createStatement().executeQuery(sql);

            while(rs.next())
            {Club p = new Club();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setDescp(rs.getString(3));
                p.setImage(rs.getString(4));
                p.setJardin(rs.getString(5));
                p.setContact(rs.getString(6));
                p.setNbAct(rs.getInt(7));
                myList.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
       return myList;
      }

    @FXML
    private void activite(ActionEvent event) throws IOException {
         versact.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/views/UserAct.fxml"));
        versact.getChildren().add(parent);
        versact.toFront();
        
    }
    
       
   
   
   }     

    

