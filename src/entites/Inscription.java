/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;




/**
 *
 * @author USER
 */
public class Inscription {
    private int id;
    private String nomParent;
    private String email;
    private String nomEnfant ;
    private int age;
    private int numTel;
    private float montant;
    private int nbmois;
    private String adresse;
    private Date dated;
    private String commentaire;
    private boolean confirmed = false;
    private String photo;
    private int activite_id;
  
//recuperation
    public Inscription(int id, String nomParent, String email, String nomEnfant,
           int age,int numTel, float montant,
            int nbmois, String adresse, Date dated,
            String commentaire, String photo ,int activite_id ) {
        this.id = id;
        this.nomParent = nomParent;
        this. email =  email;
        this.adresse = adresse;
        this.numTel = numTel;
        this.nomEnfant = nomEnfant;
        this.age = age;
        this.nbmois = nbmois;
        this.montant = montant;
        this.dated = dated;
        this.commentaire = commentaire;
        this.photo = photo;
        this.activite_id = activite_id;
        
    }
//ajout
    public Inscription( String nomParent, String email, String nomEnfant,
           int age,int numTel, 
            int nbmois, String adresse, Date dated,
            String commentaire,  String photo , float montant,int activite_id )  {
        this.nomParent = nomParent;
        this. email =  email;
        this.adresse = adresse;
        this.numTel = numTel;
        this.nomEnfant = nomEnfant;
        this.age = age;
        this.nbmois = nbmois;
        this.dated = dated;
        this.commentaire = commentaire; 
        this.photo = photo;
        this.montant = montant;

        this.activite_id = activite_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomParent() {
        return nomParent;
    }

    public void setNomParent(String nomParent) {
        this.nomParent = nomParent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomEnfant() {
        return nomEnfant;
    }

    public void setNomEnfant(String nomEnfant) {
        this.nomEnfant = nomEnfant;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public int getNbmois() {
        return nbmois;
    }

    public void setNbmois(int nbmois) {
        this.nbmois = nbmois;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDated() {
        return dated;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getActivite_id() {
        return activite_id;
    }

    public void setActivite_id(int activite_id) {
        this.activite_id = activite_id;
    }
    
    
   
    @Override
     public String toString() {
      return "Activites {" + "Id=" + id + ", Nom du parent =" + nomParent + 
              ", Adresse=" + adresse +
              ", Email =" + email  + 
              ", Numero de telephone=" + numTel + 
              ", Nom de l'enfant=" +  nomEnfant +
              ", Age=" + age +
              ", Nombre de mois de participation=" + nbmois +
              ", Date de debut=" + dated +
              ", Commentaire=" + commentaire +
              ", activite_id=" + activite_id +
             '}';
    }
}
