/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author LENOVO
 */
import java.sql.*;
import utils.DataSource;
import Entite.Reservation;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;

/**
 *
 * @author LENOVO
 */
public class ReservationService {

    private Connection con = DataSource.getInstance().getConnection();
    private Statement stm, stm0, stm1, stm2, stm3, stm4, stm5, stm6, stm7, stm8, stma, stm10, stmb;

    public ReservationService() {
        try {
            stm = con.createStatement();
            stm0 = con.createStatement();
            stm1 = con.createStatement();
            stm2 = con.createStatement();
            stm3 = con.createStatement();
            stm4 = con.createStatement();
            stm5 = con.createStatement();
            stm6 = con.createStatement();
            stm7 = con.createStatement();
            stm8 = con.createStatement();
            stma = con.createStatement();
            stmb = con.createStatement();
            stm10 = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    //public void ajouterReservation(Reservation r, User u, evenement e ) throws SQLException {
    //try {
    // stm = con.createStatement();
    //String req = "INSERT INTO `Reservation` (`Id_Res`,`Id_Event`,`Id_User`, `Date_Event`,`Etat_Res`,`E_mailU`,`NumTel`,`Descr_Event`) "
    //+ "VALUES (NULL, '" + e.getId_Event() + "', '" + u.getId_User()+ "', '" + e.getDate_Event() + "', '" + r.getEtat() + "', '" + u.getE_mailU()+ "', '" + u.getNumTel() + "', '" + e.getDescr_Event() + "');";
    //stm.executeUpdate(req);
    //System.out.println("reservation ajoutée");
    //} catch (SQLException ex) {
    //Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
    //}
    //}
    //public void supprimerReserv(String login,String titre) {

        //try {
             //int idU=rechercherIdU(login);
             //int idE=rechercherIdE(titre);
            //String req = "DELETE FROM `Reservation` WHERE (`reservation`.`Id_User` = " + idU + ") and  (`reservation`.`Id_Event`=" + idE + ");";
            //PreparedStatement ps = con.prepareStatement(req);
            //ps.setInt(1, id);
            //ps.executeUpdate();
            //System.out.println("element supprimé");

        //} catch (SQLException ex) {
            //Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        //}

    //}
public void supprimerReserv(int id) {

        try {
           
            String req = "DELETE FROM `Reservation` WHERE `reservation`.`Id_Res` ="+ id;
            PreparedStatement ps = con.prepareStatement(req);
            //ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("element supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    public void modifierReser(String titre,String login,String mail,int num ) throws SQLException {
        int idE=rechercherIdE(titre);
        int idU=rechercherIdU(login);
        String sql = "UPDATE Reservation SET `E_mailU`=?,`NumTel`=?  WHERE (`reservation`.`Id_User`=" + idU+") and (`reservation`.`Id_Event` ="+idE+")";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, mail);
            ps.setInt(2,num);

            ps.executeUpdate();
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de votre réservation a été éffectué avec succée ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public List<Reservation> readAll(String login) throws SQLException{
    
       // String sql="";
       // List<Reservation> list=new ArrayList<>();
        
       
        List<Reservation> ReservationL = new ArrayList<>();
        Reservation R = null;
        
             int idU= rechercherIdU(login);
          
            
   
             try {
              String req="select Id_Event from reservation WHERE `reservation`.`Id_User` ="+idU;
          ResultSet rs=stm.executeQuery(req);
          
            while (rs.next()){
                  int ide=rs.getInt("Id_Event");
                      //
                      
                  //String  sql="select E.Descr_Event,U.E_mailU,U.NumTel from reservation R JOIN user U ON R.Id_User=U.Id_User JOIN evenement E ON R.Id_Event=E.Id_EVent  where (R.Id_Event="+ide+")and( R.Id_User="+idU+")";
              //String  sql = "select * FROM reservation WHERE (`reservation`.`Id_Event` =" + ide+") and (`reservation`.`Id_User`="+idU+")";
                 String sql="select E.Titre_Event,R.* from reservation R JOIN evenement E ON E.Id_Event=R.Id_Event  WHERE (R.Id_Event =" + ide+") and (R.Id_User="+idU+")";
                  ResultSet res=stm0.executeQuery(sql);
                      while(res.next())
                      {   R = new Reservation();
                      R.setId_Res(res.getInt("Id_Res"));
                       R.setId_Event(res.getInt("Id_Event"));
                       R.setId_User(res.getInt("Id_User"));
                         R.setEtat("en attente");
                          R.setE_mailU(res.getString("E_mailU"));
                           R.setNumTel(res.getInt("NumTel"));
                            
                          R.setDescr_Event(res.getString("Descr_Event"));
                          R.setTitre_Event(res.getString("Titre_Event"));
                            java.util.Date d1 =res.getDate("DATED_EVENT"); 
                      java.util.Date d2 =res.getDate("DATEF_EVENT"); 
                      Instant instant = Instant.ofEpochMilli(d1.getTime());
                      Instant instant1 = Instant.ofEpochMilli(d2.getTime());
                      LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                      LocalDate date1 = instant1.atZone(ZoneId.systemDefault()).toLocalDate();
              System.out.println(date);
                                    R.setDATED_EVENT(date);
                      R.setDATEF_EVENT(date1);
                      ReservationL.add(R);                              
                       
                          
                      }
                
                 //e = new Reservation();
                //int ide=rs.getInt("Id_Event");
              //e.setId_Event(rs.getInt("Id_Event"));
              //e.setDescr_Event(res.getString("Descr_Event"));
              //e.setE_mailU(res.getString("E_mailU"));
              //e.setNumTel(res.getInt("NumTel"));
              
                
                
             
              // while(res.next()){
                    //System.out.println(res.getString("Descr_Event"));
                     //System.out.println(res.getString("E_mailU"));
              
                  //list.add(new Reservation(res.getString("Descr_Event"),res.getString("E_mailU"),res.getInt("NumTel")));
              // }
       
              
                  
                   
                 //list.add(new Reservation(titre,Des,mail,tel));
                // System.out.println(rs.getInt("Id_Event"));
               }
                   //list.add(R);
                
            
             
       
            //System.out.println("ok");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return ReservationL;
     
    }

    /*public void ajouterReservation(Reservation R) throws SQLException {
        String sql3 = "", sql1 = "", sql2 = "", sql4 = "", sql5 = "";
        int EV = 0, U = 0, exist = 0;

        String req0 = "Select nbr_place_E FROM evenement WHERE `evenement`.`Id_Event` =" + R.getId_Event();
        String Test = "Select count(Id_Res)FROM reservation WHERE `reservation`.`Id_Event` =" + R.getId_Event();
        String Test1 = "Select Id_Res FROM reservation where (`reservation`.`Id_User` = " + R.getId_User() + ") and  (`reservation`.`Id_Event`=" + R.getId_Event() + ");";
        ResultSet res0 = stm0.executeQuery(req0);
        ResultSet res8 = stm7.executeQuery(Test);
        String req1 = "select .  from evenement";
        String req2 = "select Login from user";

        ResultSet res1 = stm.executeQuery(req1);
        ResultSet res2 = stm1.executeQuery(req2);

        while (res1.next()) {
            if (res1.getInt("Id_Event") == R.getId_Event()) {
                EV = 1;
                sql1 = "Select DATED_Event FROM evenement WHERE `evenement`.`Id_Event` =" + R.getId_Event();
                sql2 = "Select DATEF_Event FROM evenement WHERE `evenement`.`Id_Event` =" + R.getId_Event();
                sql3 = "Select Descr_Event FROM evenement WHERE `evenement`.`Id_Event` =" + R.getId_Event();

            }
        }
        while (res2.next()) {
            if (res2.getInt("Id_User") == R.getId_User()) {
                U = 1;
                sql4 = "Select E_mailU FROM user WHERE `user`.`Id_User` =" + R.getId_User();
                sql5 = "Select NumTel FROM user WHERE `user`.`Id_User` =" + R.getId_User();
            }
        }
        if ((EV == 1) && (U == 1)) {

            ResultSet res9 = stm8.executeQuery(Test1);

            while (res9.next()) {

                exist = exist + (res9.getInt(1));
            }

            if (exist == 0) {

                while ((res0.next()) && res8.next()) {

                    if ((res0.getInt("nbr_place_E")) > (res8.getInt(1))) {

                        ResultSet res3 = stm2.executeQuery(sql1);
                        ResultSet res4 = stm3.executeQuery(sql2);
                        ResultSet res5 = stm4.executeQuery(sql3);
                        ResultSet res6 = stm5.executeQuery(sql4);
                        ResultSet res7 = stm6.executeQuery(sql5);

                        while ((res3.next()) && (res4.next()) && (res5.next()) && (res6.next()) && (res7.next())) {

                            String req = "INSERT INTO `reservation` (`Id_Res`,`Id_Event`,`Id_User`, `Etat_Res`,`E_mailU`,`NumTel`,`Descr_Event`,`DATED_EVENT`,`DATEF_EVENT`) "
                                    + "VALUES (NULL, '" + R.getId_Event() + "', '" + R.getId_User() + "', '" + R.getEtat() + "', '" + res6.getString("E_mailU") + "', '" + res7.getInt("NumTel") + "', '" + res5.getString("Descr_Event") + "', '" + res3.getDate("DATED_EVENT") + "','" + res4.getDate("DATEF_EVENT") + "');";
                            stm.executeUpdate(req);
                            System.out.println("Reservation ajoutée");
                        }
                    } else {
                        System.out.println("evennement Complet");
                    }
                }
            }

            if (exist != 0) {
                System.out.println("Vous avez déja une reservation à cet evennement");
            }

        }

        if (EV == 0) {
            System.out.println("évennement inexistant");
        }
        if (U == 0) {
            System.out.println("vérifier votre Id User");
        }

    }*/
    public void ajouterReservation(Reservation R) throws SQLException {
        
        String sql3 = "", sql1 = "", sql2 = "", sql4 = "", sql5 = "";
        int exist=0;
        int idE=rechercherIdE(R.getTitre_Event());
          int idU=rechercherIdU(R.getLogin());
        
        
        
        String req0 = "Select nbr_place_E FROM evenement WHERE `evenement`.`Id_Event` =" + idE;
        String Test = "Select count(Id_Res)FROM reservation WHERE `reservation`.`Id_Event` =" + idE;
        String Test1 = "Select Id_Res FROM reservation where (`reservation`.`Id_User` = " + idU + ") and  (`reservation`.`Id_Event`=" + idE + ");";
        ResultSet res0 = stm0.executeQuery(req0);
        ResultSet res8 = stm7.executeQuery(Test);
        String req1 = "select Id_Event  from evenement";
        String req2 = "select Id_User from user";

        ResultSet res1 = stm.executeQuery(req1);
        ResultSet res2 = stm1.executeQuery(req2);

        while (res1.next()) {
            if (res1.getInt("Id_Event") == idE) {
                
                sql1 = "Select DATED_Event FROM evenement WHERE `evenement`.`Id_Event` =" + idE;
                sql2 = "Select DATEF_Event FROM evenement WHERE `evenement`.`Id_Event` =" + idE;
                sql3 = "Select Descr_Event FROM evenement WHERE `evenement`.`Id_Event` =" + idE;

            }
        }
        while (res2.next()) {
            if (res2.getInt("Id_User") == idU) {
               
                sql4 = "Select E_mailU FROM user WHERE `user`.`Id_User` =" + idU;
                sql5 = "Select NumTel FROM user WHERE `user`.`Id_User` =" + idU;
            }
        }
        if ((idE>0) && (idU>0)) {

            ResultSet res9 = stm8.executeQuery(Test1);

            while (res9.next()) {

                exist = exist + (res9.getInt(1));
            }

            if (exist == 0) {

                while ((res0.next()) && res8.next()) {

                    if ((res0.getInt("nbr_place_E")) > (res8.getInt(1))) {

                        ResultSet res3 = stm2.executeQuery(sql1);
                        ResultSet res4 = stm3.executeQuery(sql2);
                        ResultSet res5 = stm4.executeQuery(sql3);
                        ResultSet res6 = stm5.executeQuery(sql4);
                        ResultSet res7 = stm6.executeQuery(sql5);

                        while ((res3.next()) && (res4.next()) && (res5.next()) && (res6.next()) && (res7.next())) {

                            String req = "INSERT INTO `reservation` (`Id_Res`,`Id_Event`,`Id_User`, `Etat_Res`,`E_mailU`,`NumTel`,`Descr_Event`,`DATED_EVENT`,`DATEF_EVENT`) "
                                    + "VALUES (NULL, '" + idE + "', '" + idU + "', '" + R.getEtat() + "', '" + res6.getString("E_mailU") + "', '" + res7.getInt("NumTel") + "', '" + res5.getString("Descr_Event") + "', '" + res3.getDate("DATED_EVENT") + "','" + res4.getDate("DATEF_EVENT") + "');";
                            stm.executeUpdate(req);
                            System.out.println("Reservation ajoutée");
                           
                            
                        }
                    } else {
                        System.out.println("evennement Complet");
                       
                    }
                }
            }

            if (exist != 0) {
                System.out.println("Vous avez déja une reservation à cet evennement");
               
            }

        }

        if (idE==0) {
            System.out.println("évennement inexistant");
            
        }
        if (idU==0) {
            System.out.println("vérifier votre Id User");
           
        }
        
       

    }

    /*public int rechercherId(String titre) throws SQLException
    {   int id=0;
        String sql1="";
       String sql="select Titre_Event from evenement";
       ResultSet res=stm.executeQuery(sql);
       while(res.next()){
           if(res.getString("Titre_Event").equals(titre)){
              sql1 = "Select Id_Event FROM evenement WHERE `evenement`.`Titre_Event`='"+ titre+"'";
             
              
    
               
           }
           
               
       }
       
        ResultSet res1=stm10.executeQuery(sql1);
              while(res1.next())
              {
                  id=res1.getInt("Id_Event");
                 
              }
        System.out.println(id);
        return id;
    }*/
    public int rechercherIdE(String titre) throws SQLException {
        int id = 0;
        String sql1 = "";
        String sql = "select Titre_Event from evenement";
        ResultSet res = stm.executeQuery(sql);
        while (res.next()) {
            if (res.getString("Titre_Event").equals(titre)) {
                sql1 = "Select Id_Event FROM evenement WHERE `evenement`.`Titre_Event`='" + titre + "'";
                ResultSet res1 = stm10.executeQuery(sql1);
                while (res1.next()) {
                    id = res1.getInt("Id_Event");
                    System.out.println(id);
                   

                }

            }

        }

        
        return id;
    }
   
    
     public int rechercherIdU(String nom) throws SQLException {
        int id = 0;
        String sql1 = "";
        String sql = "select Login from user";
        ResultSet res = stm.executeQuery(sql);
        while (res.next()) {
            if (res.getString("Login").equals(nom)) {
                sql1 = "Select Id_User FROM user WHERE `user`.`Login`='" + nom + "'";
                ResultSet res1 = stm10.executeQuery(sql1);
                while (res1.next()) {
                    id = res1.getInt("Id_User");
                    System.out.println(id);

                }

            }

        }

        
        return id;
    }
    
     
          public String Fidéle(String nom) throws SQLException {
              String statut="simple";
       
            int idU=rechercherIdU(nom);
                   String Test = "Select count(Id_Res)FROM reservation WHERE `reservation`.`Id_User` =" + idU;
                       ResultSet res = stm10.executeQuery(Test);
                       while(res.next())
                       {    int nbr=res.getInt(1);
                            if (nbr>30)
                            {
                                statut="Fidéle";
                            }
                           
                       }
                       return statut;
            }

        
          
    
    
}
