/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Objects;

/**
 *
 * @author aymen
 */
public class Article {
    
    private int Id_Article;
    private int Id_User;
    private String Nom_Article;
    private String Contenu_Article;
    private String Image_Article;
    private String Titre_Event;
    private int Edition;
    private String Date_Article;

    public Article(String Nom_Article, String Contenu_Article, String Image_Article, String Titre_Event) {
        this.Nom_Article = Nom_Article;
        this.Contenu_Article = Contenu_Article;
        this.Image_Article = Image_Article;
        this.Titre_Event = Titre_Event;
    }
public Article (){

}

    public Article(int Id_User, String Nom_Article, String Contenu_Article, String Image_Article, String Titre_Event, int Edition, String Date_Article) {
        this.Id_User = Id_User;
        this.Nom_Article = Nom_Article;
        this.Contenu_Article = Contenu_Article;
        this.Image_Article = Image_Article;
        this.Titre_Event = Titre_Event;
        this.Edition = Edition;
        this.Date_Article = Date_Article;
    }
    public Article(int Id_Article, int Id_User, String Nom_Article, String Contenu_Article, String Image_Article, String Titre_Event, int Edition, String Date_Article) {
        this.Id_Article = Id_Article;
        this.Id_User = Id_User;
        this.Nom_Article = Nom_Article;
        this.Contenu_Article = Contenu_Article;
        this.Image_Article = Image_Article;
        this.Titre_Event = Titre_Event;
        this.Edition = Edition;
        this.Date_Article = Date_Article;
    }

    public Article(String Nom_Article, String Contenu_Article, String Image_Article, String Titre_Event, int Edition, String Date_Article) {
        this.Nom_Article = Nom_Article;
        this.Contenu_Article = Contenu_Article;
        this.Image_Article = Image_Article;
        this.Titre_Event = Titre_Event;
        this.Edition = Edition;
        this.Date_Article = Date_Article;
    }

    
    
    
    
    public Article(String Titre_Event) {
        this.Titre_Event = Titre_Event;
    }

  

  
    

    public int getId_Article() {
        return Id_Article;
    }

    public int getId_User() {
        return Id_User;
    }

    public String getNom_Article() {
        return Nom_Article;
    }

    public String getContenu_Article() {
        return Contenu_Article;
    }

    public String getImage_Article() {
        return Image_Article;
    }

    public String getTitre_Event() {
        return Titre_Event;
    }

    public int getEdition() {
        return Edition;
    }

    public String getDate_Article() {
        return Date_Article;
    }

    public void setId_Article(int Id_Article) {
        this.Id_Article = Id_Article;
    }

    public void setId_User(int Id_User) {
        this.Id_User = Id_User;
    }

    public void setNom_Article(String Nom_Article) {
        this.Nom_Article = Nom_Article;
    }

    public void setContenu_Article(String Contenu_Article) {
        this.Contenu_Article = Contenu_Article;
    }

    public void setImage_Article(String Image_Article) {
        this.Image_Article = Image_Article;
    }

    public void setTitre_Event(String Titre_Event) {
        this.Titre_Event = Titre_Event;
    }

    public void setEdition(int Edition) {
        this.Edition = Edition;
    }

    public void setDate_Article(String Date_Article) {
        this.Date_Article = Date_Article;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.Id_Article;
        hash = 97 * hash + this.Id_User;
        hash = 97 * hash + Objects.hashCode(this.Nom_Article);
        hash = 97 * hash + Objects.hashCode(this.Contenu_Article);
        hash = 97 * hash + Objects.hashCode(this.Image_Article);
        hash = 97 * hash + Objects.hashCode(this.Titre_Event);
        hash = 97 * hash + this.Edition;
        hash = 97 * hash + Objects.hashCode(this.Date_Article);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Article other = (Article) obj;
        if (this.Id_Article != other.Id_Article) {
            return false;
        }
        if (this.Id_User != other.Id_User) {
            return false;
        }
        if (this.Edition != other.Edition) {
            return false;
        }
        if (!Objects.equals(this.Nom_Article, other.Nom_Article)) {
            return false;
        }
        if (!Objects.equals(this.Contenu_Article, other.Contenu_Article)) {
            return false;
        }
        if (!Objects.equals(this.Image_Article, other.Image_Article)) {
            return false;
        }
        if (!Objects.equals(this.Titre_Event, other.Titre_Event)) {
            return false;
        }
        if (!Objects.equals(this.Date_Article, other.Date_Article)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Article{" + "Id_Article=" + Id_Article + ", Id_User=" + Id_User + ", Nom_Article=" + Nom_Article + ", Contenu_Article=" + Contenu_Article + ", Image_Article=" + Image_Article + ", Titre_Event=" + Titre_Event + ", Edition=" + Edition + ", Date_Article=" + Date_Article + '}';
    }

   
    

   
    
    
    
    
    
    
    
    
    
}
