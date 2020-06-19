
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Jardin;
import entites.Rating;
import entites.ReservationJardin;
import com.sun.rowset.CachedRowSetImpl;
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
import util.DataSource1;

/**
 *
 * @author CASA INFO M
 */
public class RatingService {
      private Connection connexion;
    private Statement ste;
    private ResultSet rs = null;

    CachedRowSetImpl crs = null;

    public RatingService() {

        connexion = DataSource1.getInstance().getCnx();
    }
     public  Rating rechercheparIdjardin(int id) throws SQLException, ClassNotFoundException {
       
        String req = "SELECT * FROM rating WHERE jardin=" + "'" + id + "'";
     

        try {

            ste = connexion.createStatement();
            rs = ste.executeQuery(req);
               System.out.println(rs+"ratte");
            List<String> lp = new ArrayList<String>();
            ObservableList<Rating> ratinglist = FXCollections.observableArrayList();
           
           
                 Rating rating = null;
            while (rs.next()) {
                rating = new Rating();
                rating.setId(rs.getInt("id"));
                rating.setId_jardin(rs.getInt("jardin"));
                rating.setRating(rs.getFloat("rating"));
              


            }
            ratinglist.add(rating);
           
         if(ratinglist.isEmpty() ){
               return null;
              
            }else{
                return rating;
            }
        } catch (SQLException e) {
            Logger.getLogger(JardinService.class.getName()).log(Level.SEVERE, null, e);
            //Return exception
            throw e;
        } 
      
       
    }
      public void insererRating(Rating r) throws SQLException, ClassNotFoundException {
        String req = "insert into rating (jardin,rating) values('" + r.getId_jardin()+ "','" + r.getRating()+  "' )";
        System.out.println(req);
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(JardinService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
      
     public void modifierJardin(Rating r) {

        //  String requete = "UPDATE jardin SET nom='" + j.getNom() + "' ,address='" + j.getAddress()+ "',nbenfant='" + j.getNbenfant()+ "' ,description='" + j.getDescription()+"',numTel='"+j.getNumTel()+ "',image='" + j.getImage() +  "' ,placesDisponibles='" +j.getPlacesDisponibles()+"',organisationDesClasses='"+j.getOrganisationDesClasses()+ "'  WHERE id=" + j.getId();
        String requete = "UPDATE rating SET jardin='" + r.getId_jardin() + "' ,rating='" + r.getRating() +  "'  WHERE jardin=" + r.getId_jardin();
       System.out.println(requete);
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
            System.out.println("Rating modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     
      public ObservableList<Rating> getAll() throws SQLException, ClassNotFoundException {

            String req = " SELECT r.rating,j.nom FROM `rating` r INNER JOIN jardin j on r.jardin = j.id  ";
            System.out.println(req + "hi");

            try {

                ste = connexion.createStatement();
                rs = ste.executeQuery(req);
                List<String> lp = new ArrayList<String>();
                ObservableList<Rating> ratinglist = FXCollections.observableArrayList();
                Rating rating = null;

                while (rs.next()) {
                    rating = new Rating();
                //   reservationJardin.setId(rs.getInt("id"));
                    rating.setRating(rs.getFloat("rating"));
                    rating.setNom_jardin(rs.getString("nom"));
                   

                    ratinglist.add(rating);

                }
                return ratinglist;
            } catch (SQLException e) {
                Logger.getLogger(JardinService.class.getName()).log(Level.SEVERE, null, e);
                //Return exception
                throw e;
            }
        }

}
