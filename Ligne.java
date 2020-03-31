/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;



/**
 *
 * @author yosra
 */
public class Ligne {

    private int id;
    private String pointDepart;
    private String pointArrive;
    private Date dateDepart;
    private String nom;
    private int placesDisponibles;
    private Bus bus;

    public Ligne(int id, String pointDepart, String pointArrive, Date dateDepart, String nom, int placesDisponibles, Bus bus) {
        this.id = id;
        this.pointDepart = pointDepart;
        this.pointArrive = pointArrive;
        this.dateDepart = dateDepart;
        this.nom = nom;
        this.placesDisponibles = placesDisponibles;
        this.bus = bus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPointDepart() {
        return pointDepart;
    }

    public void setPointDepart(String pointDepart) {
        this.pointDepart = pointDepart;
    }

    public String getPointArrive() {
        return pointArrive;
    }

    public void setPointArrive(String pointArrive) {
        this.pointArrive = pointArrive;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPlacesDisponibles() {
        return placesDisponibles;
    }

    public void setPlacesDisponibles(int placesDisponibles) {
        this.placesDisponibles = placesDisponibles;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    @Override
    public String toString() {
        return "Ligne{" + "id=" + id + ", pointDepart=" + pointDepart + ", pointArrive=" + pointArrive + ", dateDepart=" + dateDepart + ", nom=" + nom + ", placesDisponibles=" + placesDisponibles + ", bus=" + bus + '}';
    }
    
    

}
