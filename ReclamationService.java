
package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;
import Entite.Reclamation;
import Entite.User;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;


public class ReclamationService {

     Connection c = DataSource.getInstance().getConnection();
       
    Statement ste;

    public ReclamationService() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    
     private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
    
    
    
     public void creerreclamation(Reclamation r) throws SQLException {
     

          
          String req2="select Id_User from user";
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) {
            if (res.getInt("Id_User")==r.getId_User()){
      

      String req1="INSERT INTO reclamation (Id_User,Titre_Reclamation,"
              + " Contenu_Reclamation, Etat_Reclamation, date_R,rateR) VALUES ('"+r.getId_User()+"','"+r.getTitre_Reclamation()+"', "
              + "'"+r.getContenu_Reclamation()+"', '"+"not yet"+"','"+r.getDate_R()+"','"+"0.0"+"');";
            ste.executeUpdate(req1);
            System.out.println("elment inste"); 
         break;}
         else {
    System.out.println("nnnn");
    }
          }
  
    }
     
     
      public void creerreclamationRate(Reclamation r) throws SQLException {
     

          
          String req2="select Id_User from user";
          ResultSet res=  ste.executeQuery(req2);
          while (res.next()) {
            if (res.getInt("Id_User")==r.getId_User()){
      

      String req1="INSERT INTO reclamation (Id_User,Titre_Reclamation,"
              + " Contenu_Reclamation, Etat_Reclamation, date_R,rateR) VALUES ('"+r.getId_User()+"','"+r.getTitre_Reclamation()+"', "
              + "'"+r.getContenu_Reclamation()+"', '"+r.getEtat_Reclamation()+"','"+r.getDate_R()+"',"+r.getRateR()+"');";
            ste.executeUpdate(req1);
            System.out.println("elment inste"); 
         break;}
         else {
    System.out.println("nnnn");
    }
          }
  
    }
    
    public void modifierReclamation(Reclamation rec) {
        String sql = "UPDATE reclamation  SET  Etat_Reclamation=?  id="+rec.getId_User(); 
        PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
            ps.setString(0, rec.getEtat_Reclamation());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {

                System.out.println("La reclamation a été modifier avec succès");
            }
        } catch (SQLException ex) {
        }
        
    }
    
    
    public void supprimerRecalamtion(Reclamation rec) {
        try {
            Statement ps=c.createStatement();
            ps.executeUpdate("delete from reclamation where id = '"+rec.getId_Reclamation()+"'");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erreur de suppression\n"+ex.getMessage());
        }

    }
    
    
    
    public ArrayList<Reclamation> readReclamtion() {
        ArrayList<Reclamation> lstRecl = new ArrayList<Reclamation>();
        UserService userService = new UserService();
        String test="not yet";
        try{
            Statement ps=c.createStatement();
            ResultSet res; 
            res=ps.executeQuery("select `Titre_Reclamation` ,`Contenu_Reclamation`, `Etat_Reclamation` ,`date_R` from 	reclamation where Etat_Reclamation = \"not yet\" order by (date_R) DESC");
            while(res.next()){

                String titre = res.getString("Titre_Reclamation");
                String contenu=res.getString("Contenu_Reclamation");
                String etat=res.getString("Etat_Reclamation");
                
                 

                
                Date date1=res.getDate("date_R");
                Instant instant = Instant.ofEpochMilli(date1.getTime());
                LocalDate date123 = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                     
                System.out.println(date123);
                
                Reclamation rec = new Reclamation( titre, contenu, etat,date123);
                lstRecl.add(rec);                  
            }      
        }catch(Exception e)
        {
           System.out.println(""+e.getMessage());
        }   
          return lstRecl;
    }
    
    
    
     public ArrayList<Reclamation> readReclamtionUser(int id) {
        
        ArrayList<Reclamation> lstRecl = new ArrayList<Reclamation>();
        try{
            Statement ps=c.createStatement();
            ResultSet res; 
            res=ps.executeQuery("select `Titre_Reclamation` ,`Contenu_Reclamation`, `Etat_Reclamation` ,`date_R` from reclamation where Id_User="+id+" ");
            while(res.next()){
                
                
                String titre=res.getString("Titre_Reclamation");
                String contenu=res.getString("Contenu_Reclamation");
                String etat = res.getString("Etat_Reclamation");
                
                
              Date dateReclamation=res.getDate("date_R");
              Instant instant = Instant.ofEpochMilli(dateReclamation.getTime());
                      
              LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                     
              System.out.println(date);
                
                
                Reclamation rec = new Reclamation(titre, contenu, etat, date);
                
                lstRecl.add(rec);                  
            }      
        }catch(Exception e)
        {
           System.out.println(""+e.getMessage());
        }   
          return lstRecl;       
     }

     
     public void modifierRate(int id,String titre,String rate) {
  
        
        
         String sql="UPDATE `reclamation` SET `rateR` = ? WHERE ((`reclamation`.`Id_user` = '" + id + "')&&(`reclamation`.`Titre_Reclamation` = '" + titre + "')); " ;
   PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
           
            ps.setString(1, rate);
            
            ps.executeUpdate();
            int rowsUpdated=ps.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification de "+id+" a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
     
     public ArrayList<Reclamation> rechercheEvennement(String rech) throws SQLException {

        ArrayList<Reclamation> off = new ArrayList<>();
String req = "SELECT `Titre_Reclamation` ,`Contenu_Reclamation`, `Etat_Reclamation` ,`date_R` FROM reclamation where `Titre_Reclamation` Like '%"+rech+"%'  ";     
        Statement stm = c.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
                String titre=rst.getString("Titre_Reclamation");
                String contenu=rst.getString("Contenu_Reclamation");
                String etat = rst.getString("Etat_Reclamation");
                
                
              Date dateReclamation=rst.getDate("date_R");
              Instant instant = Instant.ofEpochMilli(dateReclamation.getTime());
                      
              LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                     
              System.out.println(date);
                
                
                Reclamation rec = new Reclamation(titre, contenu, etat, date);
        off.add(rec);
        }
        return off;
    }
   
     
     
    public String findMail(){
     String test=null;
     
     
     
     return test;
     }
     
     
     
    }
    













