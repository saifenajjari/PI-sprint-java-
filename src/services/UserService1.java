
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource2;
import entites.User1;


public class UserService1 {
    
    Connection c = DataSource2.getInstance().getConnection();
       
    Statement ste;

    public UserService1() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
        public void creerUser(User1 u) throws SQLException{
      
            String req1="INSERT INTO `user` (`Login`, `Mdp`, `E_mailU`, `NomU`, `PrenomU`,`NumTel`, `PhotoProfil`, `RoleU`,`status`,`nbr_c`) VALUES ('"+u.getLogin()+"', '"+u.getMdp()+"', '"+u.getE_mailU()+"', '"+u.getNomU()+"', '"+u.getPrenomU()+"', '"+u.getNumTel()+"', '"+u.getPhotoProfil()+"', 'Membre','nonactive',"+0+");";
            
            ste.executeUpdate(req1);
            System.out.println("elment insert");     
    }
    
        public List<User1> readAll() throws SQLException
    {List<User1> list=new ArrayList<>();
    ResultSet res=ste.executeQuery("select * from user");
    User1 per=null;
    while (res.next()) {            
      per=new User1(res.getInt(1), res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getInt(9),res.getString(10),res.getString(11));
      list.add(per);
        }
    return list;
    } 
        
        public void modifierUser(User1 u) {
        
         
        
   String sql="UPDATE user SET `Login`=?,`Mdp`=?,`E_mailU`=? ,`NomU`=? ,`PrenomU`=?,`NumTel`=?,`PhotoProfil`=?,`RoleU`=? WHERE Id_User="+u.getId_User();
   PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getMdp());
            ps.setString(3, u.getE_mailU());
            ps.setString(4, u.getNomU());
            ps.setString(5, u.getPrenomU());
            ps.setInt(8, u.getNumTel());
            ps.setString(9, u.getPhotoProfil());
            ps.setString(10, u.getRoleU());
            
            ps.executeUpdate();
            int rowsUpdated=ps.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification de user"+u.getId_User()+" a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(UserService1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
         public void supprimerUser(User1 p) {
            
        
        try { 
            String req="DELETE FROM `user` WHERE `user`.`Id_User` = ?";
            PreparedStatement ps = c.prepareStatement(req);
            ps.setInt(1, p.getId_User());
            ps.executeUpdate();
            System.out.println("element supprimer");
         
        } catch (SQLException ex) {
            Logger.getLogger(UserService1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        public String rechercherparrole(int id) {
        String test = null;
        String req = "SELECT RoleU from user where Id_User='"+id+"'";
             
        try {
           
            ResultSet stp=ste.executeQuery(req);
            stp.last();
            if(stp.getRow()!=0)
            {
               test= stp.getString(1);
                System.out.println(test);
            }
            else
            {
                test="not found";
                System.out.println(test);
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(UserService1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
        
    } 
    
        
        public int authentification(User1 u){
        int test = 0;
          try {
          String req2="select `Id_User`,`Login`, `Mdp` from user";
          ResultSet res=  ste.executeQuery(req2);
          while (res.next() && (test==0)) {
            if (u.getLogin().equals((res.getString("Login"))) && (res.getString("Mdp").equals(u.getMdp()))){
                
                 test=res.getInt(1);
                 System.err.println(test);
            }
            
            else{
           System.err.println("erreur");

            test=0;
            }
         
    
         
          }
    }   catch (SQLException ex) {
            Logger.getLogger(UserService1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
        }
        
         public String readRole(int id) throws SQLException
         {
    ResultSet res=ste.executeQuery("select `RoleU` from user ");
String test=null;
 while (res.next() && (test.equals(test))) {
     test =res.getString(1);
 }
    return test;
    } 
         
         
         
         
         
          
          
          
       public User1 readmembre(int id) throws SQLException
    {List<User1> list=new ArrayList<>();
    ResultSet res=ste.executeQuery("select * from user WHERE `user`.`Id_User`="+id+ "' ");
    User1 per=null;
    while (res.next()) {            
      per=new User1(res.getInt(1), res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getInt(7),res.getString(8),res.getString(9));
      list.add(per);
        }
    return per;
    } 
          
          public User1 readUser(int id) throws SQLException
         {
    ResultSet res=ste.executeQuery("select  Login , Mdp , mailU , NomU , PrenomU ,NumTel ,PhotoProfil , \n" +
"RoleU  from user WHERE `user`.`Id_User`="+id+ "' ");
    User1 per=null;
    while (res.next()) {            
      per=new User1(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getInt(6),res.getString(7),res.getString(9));
    
    
    }
        return per;
         } 
          
          
            public User1 getUserByid(int id) throws SQLException {
        User1 emp = null;
        Statement stm = c.createStatement();
        String req = "SELECT * FROM `user` WHERE  Id_User='"+id+"'";
        ResultSet resultat = stm.executeQuery(req);
        if (resultat.next()) {
            int id_user = resultat.getInt("Id_User");
            String login = resultat.getString("Login");
            String mdp = resultat.getString("Mdp");
            String email = resultat.getString("E_mailU");
            String nom = resultat.getString("NomU");
            String prenom = resultat.getString("PrenomU");
            int numtel = resultat.getInt("NumTel");
            String photo = resultat.getString("PhotoProfil");
            String role = resultat.getString("RoleU");
            
            emp = new User1(id_user, login, mdp,email, nom, prenom, numtel, photo, role);
            
        }

        return emp;
    }
          
       public void deleteUser(String login) {


        try { 
            String req="DELETE FROM `user` WHERE `user`.`Login`='" + login + "' ";
            PreparedStatement ps = c.prepareStatement(req);
            ps.executeUpdate();
            System.out.println("element supprimer");
         
        } catch (SQLException ex) {
            Logger.getLogger(UserService1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
       
     
       public void modifierRole(String login,String role) {
        
         
        
   String sql="UPDATE user SET `RoleU`=? WHERE `user`.`Login`='" + login + "' ";
   PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
           
            ps.setString(1, role);
            
            ps.executeUpdate();
            int rowsUpdated=ps.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification de "+login+" a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(UserService1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
       
       
       public void modifieruser(int id,User1 u) {
        
         
        
   String sql="UPDATE user SET `E_mailU`=?,`NomU`=?,`PrenomU`=?,`NumTel`=? WHERE `user`.`Id_User`='" + id + "' ";
   PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
           
            ps.setString(1, u.getE_mailU());
            ps.setString(2, u.getNomU());
            ps.setString(3, u.getPrenomU());
            ps.setInt(4, u.getNumTel());
            
            ps.executeUpdate();
            int rowsUpdated=ps.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification de "+id+" a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(UserService1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
       public void adduser(User1 u) throws SQLException{
      
            String req1="INSERT INTO `user` (`Login`, `Mdp`, `E_mailU`, `NomU`, `PrenomU`,`NumTel`, `PhotoProfil`, `RoleU`) VALUES ('"+u.getLogin()+"', '"+u.getMdp()+"', '"+u.getE_mailU()+"', '"+u.getNomU()+"', '"+u.getPrenomU()+"', '"+u.getNumTel()+"', '"+u.getPhotoProfil()+"', '"+u.getRoleU()+"');";
            
            ste.executeUpdate(req1);
            System.out.println("elment insert");     
    }
       
       
       public void modifierStatus(int id,String status) {
        
         
        
   String sql="UPDATE user SET `status`=? WHERE `user`.`Id_User`='" + id + "' ";
   PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
           
            ps.setString(1, status);
            
            ps.executeUpdate();
            int rowsUpdated=ps.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification de "+id+" a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(UserService1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
       /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       
       
       public List<User1> readmembre() throws SQLException
    {List<User1> list=new ArrayList<>();
    ResultSet res=ste.executeQuery("select `Id_User`,`Login`, `E_mailU`,`NumTel`, `RoleU` from user");
    User1 per=null;
    while (res.next()) { 
        int a = this.getCount(res.getInt(1));
        inserte(res.getInt(1), a);
      per=new User1(res.getString(2), res.getString(3),res.getInt(4),res.getString(5),a);
      list.add(per);
        }
    return list;
    } 
       
      public int getCount(int id) throws SQLException {
        int result = -1;
        try (PreparedStatement pstmt = c.prepareStatement("SELECT COUNT(Id_User) FROM evenement where Id_User = '"+id+"'")) {
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1);
            }

            rs.close();
        }

        return result;
    } 
       
      
      public void inserte(int id,int nbr ){
             
   String sql="UPDATE user SET `nbr_c`=? WHERE Id_User='" + id + "' ";
   PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
           
            ps.setInt(1, nbr);
            
            ps.executeUpdate();
            int rowsUpdated=ps.executeUpdate();
            if (rowsUpdated>0){
           // System.out.println("La modification de "+login+" a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(UserService1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      
       
       
       
}
