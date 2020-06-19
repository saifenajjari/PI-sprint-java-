/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Chauffeur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DataSource1;

/**
 *
 * @author yosra
 */
public class ChauffeurService implements IService<Chauffeur> {

    Connection cnx = DataSource1.getInstance().getCnx();

    @Override
    public void ajouter(Chauffeur c) {

        try {
            String requete = "INSERT INTO chauffeur (id,nom,prenom,cin) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setInt(1, c.getId());
            pst.setString(2, c.getNom());
            pst.setString(3, c.getPrenom());
            pst.setInt(4, c.getCin());
            pst.executeUpdate();
            System.out.println("Chauffeur ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(Chauffeur c) {
        try {
            String requete = "DELETE FROM chauffeur WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
            System.out.println("Chauffeur supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Chauffeur c) {
        try {
            String requete;
            requete = "UPDATE chauffeur SET nom=?,prenom=? ,cin=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
            pst.setInt(3, c.getCin());
            pst.setInt(4, c.getId());
            pst.executeUpdate();
            System.out.println("Chauffeur modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public List<Chauffeur> afficher() {
        List<Chauffeur> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM chauffeur";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                list.add(new Chauffeur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;

    }

    public int getBusID(int idChauffeur) {
        int nb = 0;
        try {
            Statement st = cnx.createStatement();
            String requete = "SELECT id FROM bus where idChauffeur='" + idChauffeur + "'";
            //executeQuery seulement pour select 
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                nb = rs.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
    }
    public void deleteLigne(int bus_id) {
        try {
            String requete = "DELETE FROM ligne WHERE bus_id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, bus_id);
            pst.executeUpdate();
            System.out.println("ligne supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
     public void setChauffeurNull(int Bus_id) {
        try {
            String requete;
            requete = "UPDATE bus SET idChauffeur=NULL WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, Bus_id);
            pst.executeUpdate();
            System.out.println("Chauffeur null affecté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
}
