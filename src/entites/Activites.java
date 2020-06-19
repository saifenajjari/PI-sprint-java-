/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.time.Instant;
import java.time.LocalTime;
import entites.Club;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ClubService;
import util.DataSource;


//import java.time.LocalTime;

public class Activites {
     private int id;
    private String nom;
    private String adresse;
    private int agemin ;
    private int agemax;
    private int montantp;
    private int nbDispo;
    private Time heured ;
    private Time heuref ;
    private String image;
    private String jours ;
    private int club_id;
  
//recuperation
    public Activites(int id, String nom, String adresse, int agemin,int agemax,int montantp, int nbDispo,Time heured, Time heuref , String image ,String jours ,int club_id ) {
        this.id = id;
        this.nom = nom;
        this. adresse =  adresse;
        this.agemin = agemin;
        this.agemax = agemax;
        this.montantp = montantp;
        this.nbDispo = nbDispo;
        this.heured = heured;
        this.heuref = heuref;
        this.image = image;
        this.jours = jours;
        this.club_id = club_id;
        
        
    }
//ajout
    public Activites( String nom, String adresse,String jours, int agemin,int agemax,int montantp, int nbDispo,Time heured, Time heuref , String image ,int club_id  )  {
        this.nom = nom;
        this. adresse =  adresse;
        this.jours = jours ;
        this.agemin = agemin;
        this.agemax = agemax;
        this.montantp = montantp;
        this.nbDispo = nbDispo;
        this.heured = heured;
        this.heuref = heuref;
        this.image = image;
        this.club_id = club_id;

    }

    public Activites() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getAgemin() {
        return agemin;
    }

    public void setAgemin(int agemin) {
        this.agemin = agemin;
    }

    public int getAgemax() {
        return agemax;
    }

    public void setAgemax(int agemax) {
        this.agemax = agemax;
    }

    public int getMontantp() {
        return montantp;
    }

    public void setMontantp(int montantp) {
        this.montantp = montantp;
    }

    public int getNbDispo() {
        return nbDispo;
    }

    public void setNbDispo(int nbDispo) {
        this.nbDispo = nbDispo;
    }

    public Time getHeured() {
        return heured;
    }

    public void setHeured(Time heured) {
        this.heured = heured;
    }

    public Time getHeuref() {
        return heuref;
    }

    public void setHeuref(Time heuref) {
        this.heuref = heuref;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getJours() {
        return jours;
    }

    public void setJours(String jours) {
        this.jours = jours;
    }

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }
    
    
    
    
     @Override
     public String toString() {
      return "Activites {" + "Id=" + id + ", Nom=" + nom + 
              ", Adresse=" + adresse +
              ", Age minimum =" + agemin  + 
              ", Age maximum=" + agemax + 
              ", Montant a payer par mois=" +  montantp +
              ", Nombre des places disponibles=" + nbDispo +
              ", Heure de debut de l'activite=" + heured +
              ", Heure de fin de l'activite =" + heuref +
              ", Image=" + image +
              ", club_id=" + club_id +
             '}';
    }
     public String convert(int clubid) {
        Connection con = DataSource.connect();

       String k ="";
       
        try {
            PreparedStatement pt = con.prepareStatement("select nom from club where `club`.`id` =" + Integer.toString(clubid));
          
            
            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                k = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return k;
       }
        
    
}
