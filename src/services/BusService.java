/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entites.Bus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DataSource1;

/**
 *
 * @author yosra
 */
public class BusService  implements IService<Bus>{
    
Connection cnx = DataSource1.getInstance().getCnx();


@Override
    public void ajouter(Bus t) {
        try {
            String requete = "INSERT INTO bus (matricule,marque,idChauffeur) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, t.getMatricule());
            pst.setString(2, t.getMarque());
            pst.setInt(3, t.getIdChauffeur());
           
            pst.executeUpdate();
            System.out.println("Bus ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
@Override
    public void supprimer(Bus t) {
        try {
            String requete = "DELETE FROM bus WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Bus supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
@Override
    public void modifier(Bus t) {
        try {
            String requete = "UPDATE bus SET matricule=?,marque=?, idChauffeur=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getMatricule());
            pst.setString(2, t.getMarque());
            pst.setInt(3, t.getIdChauffeur());
            pst.setInt(4, t.getId());
         
            
            pst.executeUpdate();
            System.out.println("Bus modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
@Override
     public List<Bus> afficher() {
        List<Bus> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM bus ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Bus( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
}
