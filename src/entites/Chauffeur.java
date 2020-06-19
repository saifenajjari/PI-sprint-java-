/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author yosra
 */
public class Chauffeur {
    private int id ;
    private String nom ;
    private String prenom ;
    private int cin ;

    public Chauffeur(int id, String nom, String prenom, int cin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
    }
     public Chauffeur( String nom, String prenom, int cin) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
    }

    public Chauffeur(int id) {
        this.id = id;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "Chauffeur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + '}';
    }
  
   
    
   

  
    
}
