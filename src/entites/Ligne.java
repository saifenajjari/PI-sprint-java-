/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.time.LocalTime;
import java.util.Date;



/**
 *
 * @author yosra
 */
public class Ligne {

    private int id;
    private String pointDepart;
    private String pointArrive;
    private LocalTime dateDepart;
    private String nom;
    private int placesDisponibles;
    private int Bus_id ;

    public Ligne(int id,int Bus_id, String pointDepart, String pointArrive, LocalTime dateDepart, String nom, int placesDisponibles) {
        this.id = id;
        this.pointDepart = pointDepart;
        this.pointArrive = pointArrive;
        this.dateDepart = dateDepart;
        this.nom = nom;
        this.placesDisponibles = placesDisponibles;
        this.Bus_id = Bus_id;
    }
       public Ligne(int Bus_id, String pointDepart, String pointArrive, LocalTime dateDepart, String nom, int placesDisponibles) {
        this.pointDepart = pointDepart;
        this.pointArrive = pointArrive;
        this.dateDepart = dateDepart;
        this.nom = nom;
        this.placesDisponibles = placesDisponibles;
        this.Bus_id = Bus_id;
    }
  
public Ligne(int id) {
        this.id = id;
       
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

    public LocalTime getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalTime dateDepart) {
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

    public int getBus_id() {
        return Bus_id;
    }

    public void setBus_id(int Bus_id) {
        this.Bus_id = Bus_id;
    }

    

    
    @Override
    public String toString() {
        return "Ligne{" + "id=" + id + ", pointDepart=" + pointDepart + ", pointArrive=" + pointArrive + ", dateDepart=" + dateDepart + ", nom=" + nom + ", placesDisponibles=" + placesDisponibles + ", bus=" + Bus_id + '}';
    }
    
    

}
