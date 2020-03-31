/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Bus;
import entites.Ligne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;

/**
 *
 * @author yosra
 */
public class LigneService implements IService<Ligne>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Ligne l) {
        try {
            String requete = "INSERT INTO ligne (id,pointDepart,pointArrive,dateDepart,nom,placesDisponibles) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
           pst.setInt(1, l.getId());
            pst.setString(2, l.getPointDepart());
            pst.setString(3, l.getPointArrive());
            pst.setDate(4, new java.sql.Date(l.getDateDepart().getTime()));
            pst.setString(5, l.getNom());
            pst.setInt(6, l.getPlacesDisponibles());
            pst.executeUpdate();
            System.out.println("Ligne ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Ligne l) {
       try {
            String requete = "DELETE FROM bus WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, l.getId());
            pst.executeUpdate();
            System.out.println("Ligne supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Ligne l) {
        try {
            String requete;
            requete = "UPDATE ligne SET pointDepart=?,pointArrive=?, dateDepart=?, nom=? placesDisponibles=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, l.getId());
            pst.setString(2, l.getPointDepart());
            pst.setString(3, l.getPointArrive());
            pst.setDate(4,new java.sql.Date(l.getDateDepart().getTime()));
            pst.setString(5, l.getNom());
            pst.setInt(6, l.getPlacesDisponibles());
         
            
            pst.executeUpdate();
            System.out.println("Ligne modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Ligne> afficher() {
      List<Ligne> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM chauffeur";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Ligne(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getInt(7), new Bus(rs.getInt(2))));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
         
    
    }
  
    }

