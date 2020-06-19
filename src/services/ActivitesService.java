/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entites.Club;
import entites.Activites;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;
//import java.util.Date;
//import java.time.LocalTime;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.time.Instant;
import java.time.LocalTime;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;



public class ActivitesService {
    
    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    
     public ActivitesService() {
        connexion=DataSource.getInstance().getCnx();
    }
     
     
      public static void insererActivites(String nom, String adresse, String image,String jours, Integer agemin,Integer agemax,Integer montantp, Integer nbDispo,Time heured, Time heuref  ,Integer club_id) throws SQLException
    {
        String sql="insert into activite (nom,adresse,image,jours,agemin,agemax,montantp,nbDispo,heured,heuref,club_id) values('"+nom+"','"+adresse+"','"+image+"','"+jours+"','"+agemin+"','"+agemax+"','"+montantp+"','"+nbDispo+"','"+heured+"','"+heuref+"','"+club_id+"' )";
         try {
           
            DataSource.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur dinsertion" + e);
            throw e;
        }
    }
    
      
    
    public static void modifierActivites(int id, String nom, String adresse, String image ,String jours , int agemin,int agemax,int montantp, int nbDispo,Time heured, Time heuref ,int club_id) throws SQLException {
       
            String sql = "UPDATE activite SET nom='" + nom + "' ,adresse='" + adresse + "',image='" + image + "',jours='" + jours + "',agemin='" + agemin + "' ,agemax='" + agemax + "',montantp='" + montantp + "',nbDispo='" + nbDispo + "',heured='" + heured + "',heuref='" + heuref + "',club_id='" + club_id + "'  WHERE id=" + id;
           try {
            DataSource.dbExecuteQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Activite inexisistant");

            throw e;
        }
    }
    
//     public void supprimerActivites(Activites a) {
//        try {
//            String requete = "DELETE FROM activite WHERE id=" + a.getId();
//            ste = connexion.createStatement();
//            ste.executeUpdate(requete);
//            System.out.println("Activite supprimée !");
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
//    
    
//     public void supprimerActivite(Activites a) {
//        PreparedStatement pt;
//        try {
//            pt = connexion.prepareStatement("delete from activite where nom=? ");
//            pt.setString(1, a.getNom());
//            pt.executeUpdate();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//    
     public static void supprimerActivite(Integer id) throws SQLException {
           String sql = "DELETE FROM `activite` WHERE `activite`.`id` =" + Integer.toString(id);
        try {
            DataSource.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de suppression" + e);
            throw e;
        }
     }
    
    /**
     *
     * @return
     */
    public static  List<Activites> getAll() throws SQLException
    {
         Connection con = DataSource.connect();
         PreparedStatement pt = con.prepareStatement ("select * from activite");
                ResultSet rs = pt.executeQuery();

//        String req="select * from activite";
        List<Activites> lp =new ArrayList<>();
        try {
           
            while(rs.next())
            {
              
                lp.add(new Activites(rs.getInt("id"), rs.getString("nom"), rs.getString("adresse"), rs.getInt("agemin"), rs.getInt("agemax"),rs.getInt("montantp"), rs.getInt("nbDispo"), rs.getTime("heured"), rs.getTime("heuref"), rs.getString("image"), rs.getString("jours"), rs.getInt("club_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return lp;
    }

    


public static void UpdateNb(int id) throws SQLException {
        try {
            String sql = "UPDATE club SET nbAct = nbAct+1  WHERE `club`.`id` =" + Integer.toString(id);
               
           DataSource.dbExecuteQuery(sql);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }
public static void UpdateNbr(int id) throws SQLException {
        try {
            String sql = "UPDATE club SET nbAct = nbAct-1  WHERE `club`.`id` =" + Integer.toString(id);
               
           DataSource.dbExecuteQuery(sql);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

public static ObservableList<Activites> rechercherNomEt(String rech) throws SQLException {

        ArrayList<Activites> off = new ArrayList<>();
           Activites e = null;
       
           Connection con = DataSource.connect();
            PreparedStatement pt = con.prepareStatement (  "SELECT `id`,`nom`,`adresse`,`agemin`,`agemax`,`montantp`,`nbDispo`,`heured`,`heuref`,`image`,`jours`,`club_id` FROM activite where nom Like '%"+rech+"%' ");
            
           ResultSet rs = pt.executeQuery();
           
           
           
//           String req = "SELECT `nom`,`nbDispo` FROM activite where nom Like '%"+rech+"%' ";  
//         
//        Statement stm = cn.createStatement();
//        ResultSet rst = stm.executeQuery(req);
        

        while (rs.next()) {
                              e = new Activites();
                
              
                e.setId(rs.getInt("id"));
               
                
                e.setNom(rs.getString("nom"));
                e.setAdresse(rs.getString("adresse"));
                e.setAgemin(rs.getInt("agemin"));
                e.setAgemax(rs.getInt("agemax"));
                e.setMontantp(rs.getInt("montantp"));
                e.setNbDispo(rs.getInt("nbDispo"));
                e.setHeured(rs.getTime("heured"));
                e.setHeuref(rs.getTime("heuref"));
                
                e.setImage(rs.getString("image"));
                e.setJours(rs.getString("jours"));
                e.setClub_id(rs.getInt("club_id"));
                
                  
                
                
                      
//   Activites per = new Activites(rst.getInt(1),rst.getString(2),rst.getInt(3),rst.getFloat(4),rst.getString(5));
  

            off.add(e);
        }
        return (ObservableList<Activites>) off;
    }



}



