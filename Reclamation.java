
package Entite;


import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;


public class Reclamation {
    private int Id_Reclamation;
    private int Id_User;
    private String Titre_Reclamation;
    private String Contenu_Reclamation;
    private String Etat_Reclamation;
    private LocalDate date_R;
    private String rateR;

    
    public Reclamation() {
    }

    public Reclamation(int Id_Reclamation, int Id_User, String Titre_Reclamation, String Contenu_Reclamation, String Etat_Reclamation, LocalDate date_R) {
        this.Id_Reclamation = Id_Reclamation;
        this.Id_User = Id_User;
        this.Titre_Reclamation = Titre_Reclamation;
        this.Contenu_Reclamation = Contenu_Reclamation;
        this.Etat_Reclamation = Etat_Reclamation;
        this.date_R = date_R;
        
    }

    public Reclamation(int Id_User, String Titre_Reclamation, String Contenu_Reclamation, String Etat_Reclamation, LocalDate date_R) {
        this.Id_User = Id_User;
        this.Titre_Reclamation = Titre_Reclamation;
        this.Contenu_Reclamation = Contenu_Reclamation;
        this.Etat_Reclamation = Etat_Reclamation;
        this.date_R = date_R;
        
    }

    public Reclamation(String Titre_Reclamation, String Contenu_Reclamation, String Etat_Reclamation, LocalDate date_R) {
        this.Titre_Reclamation = Titre_Reclamation;
        this.Contenu_Reclamation = Contenu_Reclamation;
        this.Etat_Reclamation = Etat_Reclamation;
        this.date_R = date_R;
    }

    public Reclamation(String Titre_Reclamation, String Contenu_Reclamation, String Etat_Reclamation) {
        this.Titre_Reclamation = Titre_Reclamation;
        this.Contenu_Reclamation = Contenu_Reclamation;
        this.Etat_Reclamation = Etat_Reclamation;
    }

   
     public Reclamation(String Titre_Reclamation, String Contenu_Reclamation, LocalDate date_R, String rateR) {
        this.Titre_Reclamation = Titre_Reclamation;
        this.Contenu_Reclamation = Contenu_Reclamation;
        this.date_R = date_R;
        this.rateR = rateR;
    }

    public String getRateR() {
        return rateR;
    }

    public void setRateR(String rateR) {
        this.rateR = rateR;
    }

    
    
    public int getId_Reclamation() {
        return Id_Reclamation;
    }

    public void setId_Reclamation(int Id_Reclamation) {
        this.Id_Reclamation = Id_Reclamation;
    }

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int Id_User) {
        this.Id_User = Id_User;
    }

    public String getTitre_Reclamation() {
        return Titre_Reclamation;
    }

    public void setTitre_Reclamation(String Titre_Reclamation) {
        this.Titre_Reclamation = Titre_Reclamation;
    }

    public String getContenu_Reclamation() {
        return Contenu_Reclamation;
    }

    public void setContenu_Reclamation(String Contenu_Reclamation) {
        this.Contenu_Reclamation = Contenu_Reclamation;
    }

    public String getEtat_Reclamation() {
        return Etat_Reclamation;
    }

    public void setEtat_Reclamation(String Etat_Reclamation) {
        this.Etat_Reclamation = Etat_Reclamation;
    }

    public LocalDate getDate_R() {
        return date_R;
    }

    public void setDate_R(LocalDate date_R) {
        this.date_R = date_R;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "Id_Reclamation=" + Id_Reclamation + ", Id_User=" + Id_User + ", Titre_Reclamation=" + Titre_Reclamation + ", Contenu_Reclamation=" + Contenu_Reclamation + ", Etat_Reclamation=" + Etat_Reclamation + ", date_R=" + date_R + '}';
    }

   
    }

    

  

