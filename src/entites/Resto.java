/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author Haifa
 */
public class Resto {
     private int id;
    private String nom;
    private String description;
    private String adresse;
    private String img;
    private int nbrdeplace;
    

    public Resto(int id, String nom, String description, String adresse, int nbrdeplace) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.nbrdeplace = nbrdeplace;
    }
 
    public Resto( String nom, String description, String adresse, int nbrdeplace) {
       
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.nbrdeplace = nbrdeplace;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNbrdeplace() {
        return nbrdeplace;
    }

    public void setNbrdeplace(int nbrdeplace) {
        this.nbrdeplace = nbrdeplace;
    }

 
    @Override
    public String toString() {
        return "Resto{" + ", nom=" + nom + ", description =" + description + ", adresse=" + adresse + ", nombre de place=" + nbrdeplace + '}';
    }
    

    
    
    
}
