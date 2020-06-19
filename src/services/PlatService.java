/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Plat;
import entites.Resto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DataSource1;

/**
 *
 * @author Haifa
 */
public class PlatService implements IService<Plat> {

    Connection cnx = DataSource1.getInstance().getCnx();

    @Override
    public void ajouter(Plat t) {
        try {
            String requete = "INSERT INTO plat(nom,descp,categorie,prix,resto_id) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, t.getNom());
            pst.setString(2, t.getDescp());
            pst.setString(3, t.getCategorie());
            pst.setInt(4, t.getPrix());
            pst.setInt(5, t.getResto_id());
            pst.executeUpdate();
            //System.out.println("Restaurant ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
    public void supprimer(int id) {
        try {
            String requete = "DELETE from plat WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Restaurant ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }}

    @Override
    public void modifier(Plat t) {
         try {
            String requete = "UPDATE plat SET nom=? ,descp=? ,categorie=? ,prix=? where id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, t.getNom());
            pst.setString(2, t.getDescp());
            pst.setString(3, t.getCategorie());
            pst.setInt(4, t.getPrix());
            pst.setInt(5, t.getId());
            pst.executeUpdate();
            System.out.println("Restaurant ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }}

    public List<Plat> afficher(int idresto) {
        List<Plat> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM plat where resto_id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, idresto);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String nom = rs.getString("nom");
                String descp = rs.getString("descp");
                String categorie = rs.getString("categorie");
                Plat p = new Plat();
                p.setCategorie(categorie);
                p.setDescp(descp);
                p.setNom(nom);
                list.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public void supprimer(Plat t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Plat> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
