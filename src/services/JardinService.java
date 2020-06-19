package services;



import com.sun.rowset.CachedRowSetImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entites.Jardin;

import util.DataSource1;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CASA INFO M
 */
public class JardinService {
    private Connection connexion;
    private Statement ste;
    private ResultSet rs = null;

    CachedRowSetImpl crs = null;

    public JardinService() {

        connexion = DataSource1.getInstance().getCnx();
    }


    public void insererJardin(Jardin j) throws SQLException, ClassNotFoundException {
        String req = "insert into jardin (nom,address,nbenfant,description,numTel,organisationDesClasses,image,placesDisponibles) values('" + j.getNom() + "','" + j.getAddress() + "','" + j.getNbenfant() + "','" + j.getDescription() + "','" + j.getNumTel() + "','" + j.getOrganisationDesClasses() + "','" + j.getImage() + "','" + j.getPlacesDisponibles() + "' )";
        System.out.println(req);
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(JardinService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public Jardin rechercheparId(int id) throws SQLException, ClassNotFoundException {

        String req = "SELECT * FROM jardin WHERE id=" + "'" + id + "'";
        System.out.println(req+"tesssssssst");

        try {

            ste = connexion.createStatement();
            rs = ste.executeQuery(req);

            Jardin jardin = null;
   while (rs.next()) {
       
                jardin = new Jardin();
                jardin.setId(rs.getInt("id"));
                jardin.setImage(rs.getString("image"));
                jardin.setNom(rs.getString("nom"));
                jardin.setAddress(rs.getString("address"));
                jardin.setPlacesDisponibles(rs.getInt("placesDisponibles"));
                jardin.setNbenfant(rs.getInt("nbenfant"));
                jardin.setNumTel(rs.getInt("numTel"));
                jardin.setDescription(rs.getString("description"));
                jardin.setOrganisationDesClasses(rs.getString("organisationDesClasses"));

                System.out.println(jardin+"tesst");
            
   }
            return jardin;
        } catch (SQLException e) {
            Logger.getLogger(JardinService.class.getName()).log(Level.SEVERE, null, e);
            //Return exception
            throw e;
        }
    }

    public Jardin rechercheJ(String nom) throws SQLException, ClassNotFoundException {

        String req = "SELECT * FROM jardin WHERE nom=" + "'" + nom + "'";
        System.out.println(req);

        try {

            ste = connexion.createStatement();
            rs = ste.executeQuery(req);

            Jardin jardin = null;

            while (rs.next()) {
                jardin = new Jardin();
                jardin.setImage(rs.getString("image"));
                jardin.setNom(rs.getString("nom"));
                jardin.setAddress(rs.getString("address"));
                jardin.setPlacesDisponibles(rs.getInt("placesDisponibles"));
                jardin.setNbenfant(rs.getInt("nbenfant"));
                jardin.setNumTel(rs.getInt("numTel"));


            }
            return jardin;
        } catch (SQLException e) {
            Logger.getLogger(JardinService.class.getName()).log(Level.SEVERE, null, e);
            //Return exception
            throw e;
        }
    }

    public ObservableList<Jardin> rechercheJs(String adresse) throws SQLException, ClassNotFoundException {

        String req = "SELECT * FROM jardin WHERE address=" + "'" + adresse + "'";
        System.out.println(req + "hi");

        try {

            ste = connexion.createStatement();
            rs = ste.executeQuery(req);
            List<String> lp = new ArrayList<String>();
            ObservableList<Jardin> jardinlist = FXCollections.observableArrayList();
            Jardin jardin = null;

            while (rs.next()) {
                jardin = new Jardin();
                jardin.setImage(rs.getString("image"));
                jardin.setNom(rs.getString("nom"));
                jardin.setAddress(rs.getString("address"));
                jardin.setPlacesDisponibles(rs.getInt("placesDisponibles"));
                jardin.setNbenfant(rs.getInt("nbenfant"));
                jardin.setNumTel(rs.getInt("numTel"));
                jardinlist.add(jardin);

            }
            return jardinlist;
        } catch (SQLException e) {
            Logger.getLogger(JardinService.class.getName()).log(Level.SEVERE, null, e);
            //Return exception
            throw e;
        }
    }

    public ObservableList<Jardin> getAll() throws SQLException, ClassNotFoundException {

        String req = "SELECT * FROM jardin ";
        System.out.println(req + "hi");

        try {

            ste = connexion.createStatement();
            rs = ste.executeQuery(req);
            List<String> lp = new ArrayList<String>();
            ObservableList<Jardin> jardinlist = FXCollections.observableArrayList();
            Jardin jardin = null;

            while (rs.next()) {
                jardin = new Jardin();
                jardin.setId(rs.getInt("id"));
                jardin.setImage(rs.getString("image"));
                jardin.setNom(rs.getString("nom"));
                jardin.setAddress(rs.getString("address"));
                jardin.setPlacesDisponibles(rs.getInt("placesDisponibles"));
                jardin.setNbenfant(rs.getInt("nbenfant"));
                jardin.setNumTel(rs.getInt("numTel"));
                jardinlist.add(jardin);

            }
            return jardinlist;
        } catch (SQLException e) {
            Logger.getLogger(JardinService.class.getName()).log(Level.SEVERE, null, e);
            //Return exception
            throw e;
        }
    }

    public void modifierJardin(Jardin j) throws SQLException, ClassNotFoundException { 

        //  String requete = "UPDATE jardin SET nom='" + j.getNom() + "' ,address='" + j.getAddress()+ "',nbenfant='" + j.getNbenfant()+ "' ,description='" + j.getDescription()+"',numTel='"+j.getNumTel()+ "',image='" + j.getImage() +  "' ,placesDisponibles='" +j.getPlacesDisponibles()+"',organisationDesClasses='"+j.getOrganisationDesClasses()+ "'  WHERE id=" + j.getId();
        String requete = "UPDATE jardin SET nom='" + j.getNom() + "' ,address='" + j.getAddress() + "',nbenfant='" + j.getNbenfant() + "' ,description='" + j.getDescription() + "',numTel='" + j.getNumTel() + "' ,placesDisponibles='" + j.getPlacesDisponibles() + "',organisationDesClasses='" + j.getOrganisationDesClasses() + "',image='" + j.getImage()+"'  WHERE id=" + j.getId();
       System.out.println(requete);
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(requete);
            System.out.println("Jardin modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
             throw ex;
        }
    }

    //     public void supprimer(jardin j) {
//        try {
//            String requete = "DELETE FROM club WHERE id=" + j.getId();
//            ste = connexion.createStatement();
//            ste.executeUpdate(requete);
//            System.out.println("jardin supprimée !");
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
    public void supprimerJardin(Jardin j) throws SQLException, ClassNotFoundException {
   String requete ="delete from jardin  WHERE id=" + j.getId();
        try {
            System.out.println(requete+" Jardin modifiée !");
             ste = connexion.createStatement();
            ste.executeUpdate(requete);
            System.out.println("Jardin modifiée !");

        } catch (SQLException ex) {
            Logger.getLogger(JardinService.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

    }

    public ObservableList<String> getAllAdress() {

        String req = "select * from jardin";
        List<String> lp = new ArrayList<String>();
        ObservableList<String> list = null;
        Jardin nom = null;
        Jardin jardin;
        try {
            ste = connexion.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {

                lp.add(rs.getString("address"));
            }

            list = (FXCollections.observableArrayList(lp));
            System.out.println(list);
        } catch (SQLException ex) {
            Logger.getLogger(JardinService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public ObservableList<String> getAllNom() {

        String req = "select * from jardin";
        List<String> lp = new ArrayList<String>();
        ObservableList<String> list = null;
        Jardin nom = null;
        Jardin jardin;
        try {
            ste = connexion.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                //    jardin = new Jardin((rs.getString("nom")));
                //      lp.add(new Jardin(rs.getInt("id"), rs.getString("nom"), rs.getString("address"), rs.getInt("nbenfant"), rs.getString("description") ,rs.getInt("numTel"),rs.getString("image"),rs.getInt("placesDisponibles"), rs.getString("organisationDesClasses")));

                //   System.out.println(rs.getString("nom"));
                lp.add(rs.getString("nom"));
            }

            list = (FXCollections.observableArrayList(lp));
            System.out.println(list);
        } catch (SQLException ex) {
            Logger.getLogger(JardinService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }


    public void reservationJardin(int id) throws SQLException, ClassNotFoundException {
        Jardin jardin = new Jardin();
        jardin = (Jardin) rechercheparId(id);
        int nbr_enfant = jardin.getNbenfant()+1;
        int places = jardin.getPlacesDisponibles()-1;

        String requete = "UPDATE jardin SET nbenfant='" + nbr_enfant + "' ,placesDisponibles='" + places + "'  WHERE id=" + id;
           try {
                ste = connexion.createStatement();
               ste.executeUpdate(requete);
           } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                throw ex;
           }


        }
    
    public ArrayList<Jardin> rechercherNom(String rech) throws SQLException {

            ArrayList<Jardin> off = new ArrayList<>();
            Jardin jardin = null;
            String req = "SELECT * from jardin  where nom  Like '%"+rech+"%'  ";
            ste = connexion.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                jardin= new Jardin();

               jardin = new Jardin();
                jardin.setId(rs.getInt("id"));
                jardin.setImage(rs.getString("image"));
                jardin.setNom(rs.getString("nom"));
                jardin.setAddress(rs.getString("address"));
                jardin.setPlacesDisponibles(rs.getInt("placesDisponibles"));
                jardin.setNbenfant(rs.getInt("nbenfant"));
                jardin.setNumTel(rs.getInt("numTel"));

                off.add(jardin);
            }
            return off;
        }

}


