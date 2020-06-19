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
public class Bus {

    private int id;
    private String matricule;
    private String marque;
    private int idChauffeur;
    public Bus(int id, String matricule, String marque, int idChauffeur) {
        this.id = id;
        this.matricule = matricule;
        this.marque = marque;
        this.idChauffeur = idChauffeur;
    }
     public Bus( String matricule, String marque, int idChauffeur) {
        this.matricule = matricule;
        this.marque = marque;
        this.idChauffeur = idChauffeur;
    }

    public Bus(int id) {
        this.id = id;
    }
      public Bus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getIdChauffeur() {
        return idChauffeur;
    }

    public void setIdChauffeur(int idChauffeur) {
        this.idChauffeur = idChauffeur;
    }
      
    

    @Override
    public String toString() {
        return "Bus{" + "id=" + id + ", matricule=" + matricule + ", marque=" + marque + ", chauffeur=" + idChauffeur + '}';
    }
    
    
    
    

}
