/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Activites;
import entites.Inscription;
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
import java.sql.Date;
/**
 *
 * @author USER
 */
public class InscriptionService {
     private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    
    public InscriptionService() {
        connexion=DataSource.getInstance().getCnx();
    }
    
     public static void insererInscription(String nomParent, String email, String nomEnfant,
           int age,int numTel, 
            int nbmois, String adresse, String dated,
            String commentaire,  String photo , float montant,int activite_id ) throws SQLException
    {   
      
        String sql="insert into inscription (nomParent,email,nomEnfant,age,numTel,nbmois,adresse,dated,commentaire,photo,montant,activite_id) values('"+nomParent+"','"+email+"','"+nomEnfant+"','"+age+"','"+numTel+"','"+nbmois+"','"+adresse+"','"+dated+"','"+commentaire+"','"+photo+"','"+montant+"','"+activite_id+"' )";
        
        try {
            DataSource.dbExecuteQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // update club set nbPlace=nbPlace-1 whre id = ?
     
     public int getPrix(int activite_id){     
       
        int x=0;
         
        try {
          Connection con = DataSource.connect();
            PreparedStatement pt = con.prepareStatement ( "SELECT montantp FROM activite where id ='"+activite_id+"'");
            
           ResultSet rs = pt.executeQuery();

            
            
            while (rs.next()) {
                
                
                x = rs.getInt(1);
                
                
                
            } 
            
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
         return x;
    }
     
 
    public int getNb(String Nom){     
     
        int x=0;
        try {
            Connection con = DataSource.connect();
            PreparedStatement pt = con.prepareStatement ("SELECT nbmois FROM inscription where NomParent ='"+Nom+"'");
            
            
           
            
           ResultSet rs = pt.executeQuery();
            
            
            while (rs.next()) {
                
                
                x = rs.getInt(1);
                
                
                
            } 
            
             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
         return x;
    }
   
        
      
     
     
     
      public static void supprimerInscription(Integer id) throws SQLException {
           String sql = "DELETE FROM `inscription` WHERE `inscription`.`id` =" + Integer.toString(id);
        try {
            DataSource.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de suppression" + e);
            throw e;
        }

    }
      
        public List<Inscription> getAll2()
    {
        String req="select * from inscription";
        List<Inscription> lp =new ArrayList<>();
        try {
            ste=connexion.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next())
            {
              
                lp.add(new Inscription(rs.getInt("id"), rs.getString("nomParent"), rs.getString("email"), rs.getString("nomEnfant"), rs.getInt("age"),rs.getInt("numTel"), rs.getFloat("montant"), rs.getInt("nbmois"), rs.getString("adresse"), rs.getDate("dated"), rs.getString("commentaire"),rs.getString("photo") ,rs.getInt("activite_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return lp;
    }
    
 public static void UpdateConfirmer(int id) throws SQLException {
        try {
            String sql = "UPDATE inscription SET confirmed = true WHERE `inscription`.`id` =" + Integer.toString(id);
               
           DataSource.dbExecuteQuery(sql);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }
 
  public static void UpdateNb(int id) throws SQLException {
        try {
            String sql = "UPDATE activite SET nbDispo = nbDispo-1  WHERE `activite`.`id` =" + Integer.toString(id);
               
           DataSource.dbExecuteQuery(sql);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }
   public static void UpdateNbn(int id) throws SQLException {
        try {
            String sql = "UPDATE activite SET nbDispo = nbDispo+1  WHERE `activite`.`id` =" + Integer.toString(id);
               
           DataSource.dbExecuteQuery(sql);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }
    
    
}
