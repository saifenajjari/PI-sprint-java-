package services;


import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import entites.Enfant;
import entites.Jardin;
import entites.ReservationJardin;
import util.DataSource1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


    /**
     *
     * @author CASA INFO M
     */
    public class ReservationService1 {
        private Connection connexion;
        private Statement ste;
        private ResultSet rs;


        public ReservationService1() {
            connexion= DataSource1.getInstance().getCnx();
        }
        public void insererReservation_jardin (ReservationJardin R)throws SQLException, ClassNotFoundException
        {
            String req="insert into reservation_jardin (jardin,enfant) values('"+R.getIdjardin()+"','"+R.getIdenfant()+"' )";
            System.out.println(req);
            try {
                ste=connexion.createStatement();
                ste.executeUpdate(req);
            } catch (SQLException ex) {
                Logger.getLogger(JardinService.class.getName()).log(Level.SEVERE, null, ex);
                throw  ex;
            }
        }
        public Integer rechercheE (int  idEnfant) throws SQLException, ClassNotFoundException {

            String req = "SELECT * FROM reservation_jardin WHERE enfant="+"'"+idEnfant+"'";
            System.out.println(req);
            int result = 0;
            try {

                ste=connexion.createStatement();
                rs=ste.executeQuery(req);
                ReservationJardin reservation = null;
                while (rs.next()) {
                    reservation = new ReservationJardin();
                    result= reservation.setIdenfant(rs.getInt("enfant"));
                    System.out.println(result);
                }
                return result;

            } catch (SQLException e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
                //Return exception
                throw e;
            }
        }
        public ObservableList<ReservationJardin> getAll() throws SQLException, ClassNotFoundException {

            String req = " SELECT e.image as img,e.nom as nome ,e.prenom as prenome, j.nom as nomj FROM `reservation_jardin` r LEFT JOIN jardin j on r.jardin = j.id LEFT JOIN enfant e on r.enfant= e.id ";
            System.out.println(req + "hi");

            try {

                ste = connexion.createStatement();
                rs = ste.executeQuery(req);
                List<String> lp = new ArrayList<String>();
                ObservableList<ReservationJardin> reservationlist = FXCollections.observableArrayList();
                ReservationJardin reservationJardin = null;

                while (rs.next()) {
                    reservationJardin = new ReservationJardin();
                //   reservationJardin.setId(rs.getInt("id"));
                    reservationJardin.setImage(rs.getString("img"));
                    reservationJardin.setNom_enfant(rs.getString("nome"));
                    reservationJardin.setPrenom_enfant(rs.getString("prenome"));
                    reservationJardin.setNom_jardin(rs.getString("nomj"));

                    reservationlist.add(reservationJardin);

                }
                return reservationlist;
            } catch (SQLException e) {
                Logger.getLogger(JardinService.class.getName()).log(Level.SEVERE, null, e);
                //Return exception
                throw e;
            }
        }
        public ArrayList<ReservationJardin> rechercherNom(String rech) throws SQLException {

            ArrayList<ReservationJardin> off = new ArrayList<>();
            ReservationJardin e = null;
            String req = "SELECT e.image as img,e.nom as nome ,e.prenom as prenome, j.nom as nomj FROM `reservation_jardin` r LEFT JOIN jardin j on r.jardin = j.id LEFT JOIN enfant e on r.enfant= e.id  where e.nom  Like '"+rech+"%'  ";
            ste = connexion.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                e = new ReservationJardin();

                e.setImage(rs.getString("img"));
                e.setNom_enfant(rs.getString("nome"));
                e.setPrenom_enfant(rs.getString("prenome"));
                e.setNom_jardin(rs.getString("nomj"));

                off.add(e);
            }
            return off;
        }


    }

