/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;



import entites.ReservationTransport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import utils.DataSource;

/**
 *
 * @author yosra
 */
public class ReservationTransportService implements IService<ReservationTransport> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(ReservationTransport r) {
        try {
            String requete = "INSERT INTO reservationtransport (id,idLigne,idUser) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
           pst.setInt(1, r.getId());
            pst.setInt(2, r.getLigne().getId());
            pst.setInt(3, r.getUser().getId());
            pst.executeUpdate();
            System.out.println("Chauffeur ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       
    }

    @Override
    public void supprimer(ReservationTransport r) {
          try {
            String requete = "DELETE FROM reservationtransport WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("ReservationTransport supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(ReservationTransport r) {
        try {
            String requete = "UPDATE reservationtransport SET idLigne=?,idUser=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, r.getId());
            pst.setInt(2, r.getLigne().getId());
            pst.setInt(3, r.getUser().getId());
            
         
            
            pst.executeUpdate();
            System.out.println("Bus modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   // @Override
    //public List<ReservationTransport> afficher() {
    // try {
           // String requete = "SELECT * FROM chauffeur";
           // PreparedStatement pst = cnx.prepareStatement(requete);
          //  ResultSet rs = pst.executeQuery();
           // while (rs.next()) {
          //      list.add(new ReservationTransport(rs.getInt(1),new Ligne(rs.getInt(2),new User(rs.getInt(3)));
          //  }

        //} catch (SQLException ex) {
          //  System.err.println(ex.getMessage());
       // }

        //return list;

    @Override
    public List<ReservationTransport> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
         
    
    }   
        
    

    
    

