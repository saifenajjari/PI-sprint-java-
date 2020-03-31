/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author LENOVO
 */
public class Reservation {
  
    private int Id_Res;
    private int Id_Event;
    private int Id_User;
    public  String etat="en attente";
    private String E_mailU;
    private int NumTel;
    private String Descr_Event;
    private String Titre_Event;
    private String Login;
 

    public LocalDate DATED_EVENT ;
    public LocalDate DATEF_EVENT ;

    public Reservation() {
    }

    public Reservation(int Id_Res, int Id_Event, int Id_User, String E_mailU, int NumTel, String Descr_Event, LocalDate DATED_EVENT, LocalDate DATEF_EVENT) {
        this.Id_Res = Id_Res;
        this.Id_Event = Id_Event;
        this.Id_User = Id_User;
        this.E_mailU = E_mailU;
        this.NumTel = NumTel;
        this.Descr_Event = Descr_Event;
        this.DATED_EVENT = DATED_EVENT;
        this.DATEF_EVENT = DATEF_EVENT;
    }

    public Reservation(int Id_Event, int Id_User, String E_mailU, int NumTel, String Descr_Event, LocalDate DATED_EVENT, LocalDate DATEF_EVENT) {
        this.Id_Event = Id_Event;
        this.Id_User = Id_User;
        this.E_mailU = E_mailU;
        this.NumTel = NumTel;
        this.Descr_Event = Descr_Event;
        this.DATED_EVENT = DATED_EVENT;
        this.DATEF_EVENT = DATEF_EVENT;
    }

 
    

    public Reservation(int Id_Event, int Id_User) {
        this.Id_Event = Id_Event;
        this.Id_User = Id_User;
    }

    public Reservation(int Id_Res) {
        this.Id_Res = Id_Res;
    }

    public Reservation(int Id_Res, int Id_Event, int Id_User,String etat,String Descr_Event) {
        this.Id_Res = Id_Res;
        this.Id_Event = Id_Event;
        this.Id_User = Id_User;
        this.etat= etat ;
        this.Descr_Event=Descr_Event;
    }

    public Reservation(String Titre_Event) {
        this.Titre_Event = Titre_Event;
    }

 
  

    public Reservation(int Id_Res, int Id_Event, int Id_User) {
        this.Id_Res = Id_Res;
        this.Id_Event = Id_Event;
        this.Id_User = Id_User;
    }

    public Reservation(int Id_Res, int Id_Event, int Id_User, String E_mailU, int NumTel, String Descr_Event) {
        this.Id_Res = Id_Res;
        this.Id_Event = Id_Event;
        this.Id_User = Id_User;
        this.E_mailU = E_mailU;
        this.NumTel = NumTel;
        this.Descr_Event = Descr_Event;
    }

    public Reservation(int Id_Res, int Id_Event, int Id_User, String Descr_Event) {
        this.Id_Res = Id_Res;
        this.Id_Event = Id_Event;
        this.Id_User = Id_User;
        this.Descr_Event = Descr_Event;
    }

    public Reservation(int Id_Res,String E_mailU, int NumTel) {
        this.Id_Res=Id_Res;
       
        this.E_mailU = E_mailU;
        this.NumTel = NumTel;
    }

    public Reservation(String Titre_Event, String Login) {
        this.Titre_Event = Titre_Event;
        this.Login = Login;
    }
    
        public Reservation(String Titre_Event, String Descr_Event, String E_mailU, int NumTel) {
        this.E_mailU = E_mailU;
        this.NumTel = NumTel;
        this.Descr_Event = Descr_Event;
        this.Titre_Event = Titre_Event;
    }

    public Reservation(String Descr_Event, String E_mailU, int NumTel) {
        
    this.E_mailU = E_mailU;
        this.NumTel = NumTel;
        this.Descr_Event = Descr_Event;
    }
 
    

    public int getId_Res() {
        return Id_Res;
    }

    public void setId_Res(int Id_Res) {
        this.Id_Res = Id_Res;
    }

    public int getId_Event() {
        return Id_Event;
    }

    public void setId_Event(int Id_Event) {
        this.Id_Event = Id_Event;
    }

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int Id_User) {
        this.Id_User = Id_User;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getE_mailU() {
        return E_mailU;
    }

    public void setE_mailU(String E_mailU) {
        this.E_mailU = E_mailU;
    }

    public int getNumTel() {
        return NumTel;
    }

    public void setNumTel(int NumTel) {
        this.NumTel = NumTel;
    }

    public String getDescr_Event() {
        return Descr_Event;
    }

    public void setDescr_Event(String Descr_Event) {
        this.Descr_Event = Descr_Event;
    }

    public LocalDate getDATED_EVENT() {
        return DATED_EVENT;
    }

    public void setDATED_EVENT(LocalDate DATED_EVENT) {
        this.DATED_EVENT = DATED_EVENT;
    }

    public LocalDate getDATEF_EVENT() {
        return DATEF_EVENT;
    }

    public void setDATEF_EVENT(LocalDate DATEF_EVENT) {
        this.DATEF_EVENT = DATEF_EVENT;
    }

    public String getTitre_Event() {
        return Titre_Event;
    }

    public void setTitre_Event(String Titre_Event) {
        this.Titre_Event = Titre_Event;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }



    @Override
    public String toString() {
        return "Reservation{" + "Id_Res=" + Id_Res + ", Id_Event=" + Id_Event + ", Id_User=" + Id_User + ", etat=" + etat + ", E_mailU=" + E_mailU + ", NumTel=" + NumTel + ", Descr_Event=" + Descr_Event + ", DATED_EVENT=" + DATED_EVENT + ", DATEF_EVENT=" + DATEF_EVENT + '}';
    }
   
    

    
    

   


}
    

