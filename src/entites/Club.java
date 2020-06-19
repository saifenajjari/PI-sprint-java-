/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author USER
 */
public class Club {
    private int id;
    private String nom;
    private String jardin;
    private String descp;
    private int nbAct = 0;
    private String contact;
    private String image;
  
    public Club(){}
//recuperation
    public Club(int id, String nom, String jardin, String descp, String contact,int nbAct ,String image) {
        this.id = id;
        this.nom = nom;
        this.jardin = jardin;
        this.descp = descp;
        this.nbAct = nbAct;
        this.contact = contact;
        this.image = image;
       
        
    }
//ajout
    public Club(String nom, String jardin, String descp, String contact ,String image) {
        this.nom = nom;
        this.jardin = jardin;
        this.contact = contact;
        this.descp = descp;
        this.image = image;
      
        
    }


    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

   

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
      public String getJardin() {
        return jardin;
    }

    public void setJardin(String jardin) {
        this.jardin = jardin;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public int getNbAct() {
        return nbAct;
    }

    public void setNbAct(int nbAct) {
        this.nbAct = nbAct;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
   


    @Override
    public String toString() {
        return "Club{" + "id=" + id + ", nom=" + nom + ", jardin=" + jardin 
                + ", contact=" + contact + ", description=" + descp + ", nombre des activites=" + nbAct + ", image=" + image +'}';
    }
    
    
}
