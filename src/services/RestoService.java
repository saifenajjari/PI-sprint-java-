package services;

import entites.Chauffeur;
import entites.Resto;
import util.DataSource1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestoService implements IService<Resto> {

    Connection cnx = DataSource1.getInstance().getCnx();

    @Override
    public void ajouter(Resto resto) {

        try {
            String requete = "INSERT INTO resto(nom,description,adress,img,nbplace) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, resto.getNom());
            pst.setString(2, resto.getDescription());
            pst.setString(3, resto.getAdresse());
            pst.setString(4, "TODO: GESTION import IMAGE COTE JAVAFX");
            pst.setInt(5, resto.getNbrdeplace());
            pst.executeUpdate();
            System.out.println("Restaurant ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Resto resto) {

    }

    public void supprimer(int id) {
        try {
            String requete = "DELETE from resto WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Restaurant ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Resto resto) {
        try {
            String requete = "UPDATE resto SET nom=? ,description=? ,adress=? ,nbplace=? where id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setString(1, resto.getNom());
            pst.setString(2, resto.getDescription());
            pst.setString(3, resto.getAdresse());
            pst.setInt(4, resto.getNbrdeplace());
            pst.setInt(5, resto.getId());
            pst.executeUpdate();
            System.out.println("Restaurant ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Resto> afficher() {
        List<Resto> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM resto";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                list.add(new Resto(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
}
