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
public class Reservation {
    private int id_res;
    private int Id_Event;
    private String titre_event;
    private String dated_event;
    private String datef_event;
    private String image_event;

    public Reservation() {  
    } 

    public Reservation(int id_res, int Id_Event, String titre_event, String dated_event, String datef_event, String image_event) {
        this.id_res = id_res;
        this.Id_Event = Id_Event;
        this.titre_event = titre_event;
        this.dated_event = dated_event;
        this.datef_event = datef_event;
        this.image_event = image_event;
    }
    
    

    public Reservation(String titreEvent) {
    }
    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public int getId_Event() {
        return Id_Event;
    }

    public void setId_Event(int id_event) {
        this.Id_Event = id_event;
    }

    public String getTitre_event() {
        return titre_event;
    }

    public void setTitre_event(String titre_event) {
        this.titre_event = titre_event;
    }

    public String getDated_event() {
        return dated_event;
    }

    public void setDated_event(String dated_event) {
        this.dated_event = dated_event;
    }

    public String getDatef_event() {
        return datef_event;
    }

    public void setDatef_event(String datef_event) {
        this.datef_event = datef_event;
    }

    public String getImage_event() {
        return image_event;
    }

    public void setImage_event(String image_event) {
        this.image_event = image_event;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_res=" + id_res + ", id_event=" + Id_Event + ", titre_event=" + titre_event + ", dated_event=" + dated_event + ", datef_event=" + datef_event + ", image_event=" + image_event + '}';
    }
    
    
    
    
    
}
