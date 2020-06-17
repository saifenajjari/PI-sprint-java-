/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author saife
 */
public class Evenement {
    private int Id_Event;
    private String descEvent;
    private String imageEvent;
    private String titreEvent;
    private String datedEvent;
    private String datefEvent;
    private String emplacement;
    private String categorieEvent;
    private int nbrPlaceE;
    private int nbrR;

    public Evenement() {
    }

    public Evenement(int Id_Event, String descEvent, String imageEvent, String titreEvent, String datedEvent, String datefEvent, String emplacement, String categorieEvent, int nbrPlaceE, int nbrR) {
        this.Id_Event = Id_Event;
        this.descEvent = descEvent;
        this.imageEvent = imageEvent;
        this.titreEvent = titreEvent;
        this.datedEvent = datedEvent;
        this.datefEvent = datefEvent;
        this.emplacement = emplacement;
        this.categorieEvent = categorieEvent;
        this.nbrPlaceE = nbrPlaceE;
        this.nbrR = nbrR;
    }

    public Evenement(String titreEvent) {
    }

    public Evenement(int id) {
    }

    public int getId_Event() {
        return Id_Event;
    }

    public void setId_Event(int idEvent) {
        this.Id_Event = idEvent;
    }

    public String getDescEvent() {
        return descEvent;
    }

    public void setDescEvent(String descEvent) {
        this.descEvent = descEvent;
    }

    public String getImageEvent() {
        return imageEvent;
    }

    public void setImageEvent(String imageEvent) {
        this.imageEvent = imageEvent;
    }

    public String getTitreEvent() {
        return titreEvent;
    }

    public void setTitreEvent(String titreEvent) {
        this.titreEvent = titreEvent;
    }

    public String getDatedEvent() {
        return datedEvent;
    }

    public void setDatedEvent(String datedEvent) {
        this.datedEvent = datedEvent;
    }

    public String getDatefEvent() {
        return datefEvent;
    }

    public void setDatefEvent(String datefEvent) {
        this.datefEvent = datefEvent;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getCategorieEvent() {
        return categorieEvent;
    }

    public void setCategorieEvent(String categorieEvent) {
        this.categorieEvent = categorieEvent;
    }

    public int getNbrPlaceE() {
        return nbrPlaceE;
    }

    public void setNbrPlaceE(int nbrPlaceE) {
        this.nbrPlaceE = nbrPlaceE;
    }

    public int getNbrR() {
        return nbrR;
    }

    public void setNbrR(int nbrR) {
        this.nbrR = nbrR;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvent=" + Id_Event + ", descEvent=" + descEvent + ", imageEvent=" + imageEvent + ", titreEvent=" + titreEvent + ", datedEvent=" + datedEvent + ", datefEvent=" + datefEvent + ", emplacement=" + emplacement + ", categorieEvent=" + categorieEvent + ", nbrPlaceE=" + nbrPlaceE + ", nbrR=" + nbrR + '}';
    }

    

    
    
    
    
            }

