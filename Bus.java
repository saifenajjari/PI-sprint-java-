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
    private int matricule;
    private String marque;
    private Chauffeur chauffeur;

    public Bus(int id, int matricule, String marque, Chauffeur chauffeur) {
        this.id = id;
        this.matricule = matricule;
        this.marque = marque;
        this.chauffeur = chauffeur;
    }

    public Bus(int id) {
        this.id = id;
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

    @Override
    public String toString() {
        return "Bus{" + "id=" + id + ", matricule=" + matricule + ", marque=" + marque + ", chauffeur=" + chauffeur + '}';
    }
    
    
    
    

}
