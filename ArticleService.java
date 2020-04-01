/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Article;
import Entite.User;
import fxml.AuthentificationController;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author aymen
 */
public class ArticleService {

    Connection c = DataSource.getInstance().getConnection();

    Statement ste;

    public ArticleService() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void creerArticle(Article u) throws SQLException {
 java.util.Date date1= new java.util.Date();  
        String Date_Article = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        u.setDate_Article(Date_Article);
        String req1 = "INSERT INTO `article` (`Id_User`,`Nom_Article`, `Contenu_Article`,"
                + " `Image_Article`, `Titre_Event`, `Edition`,`Date_Article`) VALUES ( '" + u.getId_User() + "','" + u.getNom_Article() + "', "
                + "'" + u.getContenu_Article() + "', '" + u.getImage_Article() + "','" + u.getTitre_Event() + "', '" + u.getEdition() + "', '" + u.getDate_Article() + "');  ";

        ste.executeUpdate(req1);
        System.out.println("elment insert");
    }


    public List<Article> afficherArticle() {

        List<Article> Article = new ArrayList<>();
        Article e = null;
        String req2 = "select * from article";
        try {

            ResultSet res = ste.executeQuery(req2);
            while (res.next()) {
              e = new Article();
                
                e.setId_Article(res.getInt("Id_Article"));
                e.setId_User(res.getInt("Id_User"));
                e.setNom_Article(res.getString("Nom_Article"));
                e.setContenu_Article(res.getString("Contenu_Article"));
                e.setImage_Article(res.getString("Image_Article"));
                e.setTitre_Event(res.getString("Titre_Event"));
                e.setEdition(res.getInt("Edition"));
                e.setDate_Article (res.getString("Date_Article"));
            
        

             
                Article.add(e);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Article;
    }

   
    
    
   
    
    
    
    
    
       public void supprimerArticle(Article p) {
            
        
        try { 
            String req="DELETE FROM `article` WHERE `article`.`Nom_Article` = ?";
            PreparedStatement ps = c.prepareStatement(req);
            ps.setString(1, p.getNom_Article());
            ps.executeUpdate();
            System.out.println("element supprimer");
         
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
       
     
      public void modifierArticle(Article A) throws SQLException{
       /* String requete="UPDATE `article` SET `Nom_Article` = '"+A.getNom_Article()+"', "
        + "`Id_User` = '"+A.getId_User()+"', `Contenu_Article` = '"+A.getContenu_Article()+"', `Image_Article` = '"+A.getImage_Article()+"', `Titre_Event` = '"+A.getTitre_Event()+"', `Edition` = '"+A.getEdition()+"', `Date_Article` = '"+A.getDate_Article()+"' WHERE `article`.`Id_Article` ='"+Id_Article+"'; ";
        ste.executeUpdate(requete);
           System.out.println("elment modifier");*/
String requete="UPDATE `article` SET  `Nom_Article` = '"+A.getNom_Article()+"', "
        + " `Contenu_Article` = '"+A.getContenu_Article()+"', `Image_Article` = '"+A.getImage_Article()+"', `Titre_Event` = '"+A.getTitre_Event()+"' where  `Id_Article`=" + A.getId_Article();
        ste.executeUpdate(requete);
          System.out.println(requete);
           System.out.println("elment modifier");
    }  
    
       
      
       public ArrayList<Article> readTitre() throws SQLException
    {ArrayList<Article> list=new ArrayList<>();
    ResultSet res=ste.executeQuery("select Titre_Event from evenement ");
    Article per=null;
    while (res.next()) {            
      per=new Article(res.getString(1));
      list.add(per);
        }
    
    return list;
    }
      
       
       
        public ArrayList<Article> readTitrear() throws SQLException
    {ArrayList<Article> liste=new ArrayList<>();
    ResultSet res=ste.executeQuery("select Nom_Article from article ");
    Article per=null;
    while (res.next()) {            
      per=new Article(res.getString(1));
      liste.add(per);
        }
    
    return liste;
    }
       
           public ArrayList<Article> readAcceuil() throws SQLException
    {ArrayList<Article> list=new ArrayList<>();
    ResultSet res=ste.executeQuery("select Titre_Event from evenement ");
    Article per=null;
    while (res.next()) {            
      per=new Article(res.getString(1));
      list.add(per);
        }
    
    return list;
    }
           
           
          public List<Article> readArticle() {

        List<Article> Article = new ArrayList<>();
        Article e = null;
        String req2 = "select Id_Article,Id_User,Nom_Article,Contenu_Article,Image_Article,Titre_Event,Edition,Date_Article from article";
        try {

            ResultSet res = ste.executeQuery(req2);
            while (res.next()) {
              e = new Article();
                
               e.setId_Article(res.getInt("Id_Article"));
               e.setId_User(res.getInt("Id_User"));
                e.setNom_Article(res.getString("Nom_Article"));
                e.setContenu_Article(res.getString("Contenu_Article"));
                e.setImage_Article(res.getString("Image_Article"));
                e.setTitre_Event(res.getString("Titre_Event"));
                e.setEdition(res.getInt("Edition"));
                e.setDate_Article (res.getString("Date_Article"));
            
        

             
                Article.add(e);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Article;
    }  
           
   /*        
       public boolean rechercherNom(String nom) {
        boolean test=false;
        String req = "SELECT * from article where Nom_Article='"+nom+"'";
            
     
        try {
           
            ResultSet stp=ste.executeQuery(req);
            stp.last();
            if(stp.getRow()!=0)
            {
                test=true;
                System.out.println(test+"Article trouver");
            }
            else
            {
                test=false;
                System.out.println(test+"Article non trouver");
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
        
    } */   
           
        public ArrayList<Article> rechercherNom(String rech) throws SQLException {

        ArrayList<Article> off = new ArrayList<>();
           Article e = null;
        String req = "SELECT * FROM article where Nom_Article Like '%"+rech+"%'  ";     
        Statement stm = c.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
                              e = new Article();
                
              
                e.setNom_Article(rst.getString("Nom_Article"));
                e.setContenu_Article(rst.getString("Contenu_Article"));
                e.setImage_Article(rst.getString("Image_Article"));
                e.setTitre_Event(rst.getString("Titre_Event"));
                e.setEdition(rst.getInt("Edition"));
                e.setDate_Article (rst.getString("Date_Article"));
            
        

             
                
                      
   Article   per=new Article(rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getInt(7),rst.getString(8));
  

            off.add(e);
        }
        return off;
    } 
        
        
        
        
     public ArrayList<Article> rechercherTitre(String rech) throws SQLException {

        ArrayList<Article> off = new ArrayList<>();
           Article e = null;
        String req = "SELECT * FROM article where Titre_Event Like '%"+rech+"%'  ";     
        Statement stm = c.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
                              e = new Article();
                
              
                e.setTitre_Event(rst.getString("Titre_Event"));
              
            
                      
   Article   per=new Article(rst.getString(6));
  

            off.add(e);
        }
        return off;
    }     
        
        
        
        
        
}


