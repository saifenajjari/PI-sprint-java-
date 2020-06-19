/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Club;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.DataSource;

/**
 *
 * @author USER
 */
public class ClubService {
    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
    
     public ClubService() {
        connexion=DataSource.getInstance().getCnx();
    }
     
    
     public static void insererClub( String nom, String jardin, String contact , String descp,String image) throws SQLException
    {
        String sql="insert into club (nom,jardin,contact,descp,image) values('"+nom+"','"+jardin+"','"+contact+"','"+descp+"','"+image+"' )";
      
//        try {
            DataSource.dbExecuteQuery(sql);
//        } catch (SQLException e) {
//            System.out.println("Erreur dinsertion" + e);
//            throw e;
//        }
    }
    
   
    public  static void modifierClub(int id, String nom, String jardin, String contact , String descp,String image) throws SQLException {
    
       String sql = "UPDATE club SET nom='" + nom + "' ,jardin='" + jardin + "',contact='" +contact + "' ,descp='" + descp + "',image='" + image + "'  WHERE id=" + id;
           try {
            DataSource.dbExecuteQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "User inexisistant");

            throw e;
        }
    }
    
//     public void supprimer(Club c) {
//        try {
//            String requete = "DELETE FROM club WHERE id=" + c.getId();
//            ste = connexion.createStatement();
//            ste.executeUpdate(requete);
//            System.out.println("Club supprimée !");
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
     public static void supprimerClub(Integer id) throws SQLException {
           String sql = "DELETE FROM `club` WHERE `club`.`id` =" + Integer.toString(id);
        try {
            DataSource.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de suppression" + e);
            throw e;
        }

    }
//      public void modifierClub(int id, String nom) {
//        try {
//            PreparedStatement pt = connexion.prepareStatement("update club set nom=?where id=?");
//            pt.setString(1, nom);
//            pt.setInt(2, id);
//            pt.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
     
      public List<Club> getAll()
    {
        String req="select * from club";
        List<Club> lp =new ArrayList<>();
        try {
            ste=connexion.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next())
            {
                lp.add(new Club(rs.getInt("id"), rs.getString("nom"), rs.getString("jardin"), rs.getString("descp"), rs.getString("contact") ,rs.getInt("nbAct"),rs.getString("image")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return lp;
    }
        public List<Club> afficherClub() {
         

        List<Club> myList = new ArrayList();
        try {
        String requete= "SELECT * FROM club ";
        ste=connexion.createStatement();
            rs=ste.executeQuery(requete);
            //executeQuery seulement pour select 
            while (rs.next()) {
                Club p = new Club();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setJardin(rs.getString(3));
                p.setDescp(rs.getString(4));
                p.setContact(rs.getString(5));
                p.setNbAct(rs.getInt(6));
                p.setImage(rs.getString(7));
                myList.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }
 

}
