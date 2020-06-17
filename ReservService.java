/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Reservation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mahmoud ennouri
 */
public class ReservService {
  
 
    
    public ArrayList<Reservation> Ticket;
    ArrayList<Reservation> listPlat = new ArrayList<>();
    
    public static ReservService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    Reservation tick =new Reservation();
    
    public ReservService() {
         req = new ConnectionRequest();
    }
    
     public static ReservService getInstance() {
        if (instance == null) {
            instance = new ReservService();
        }
        return instance;
    }
     
            public ArrayList<Reservation> parseTicket(String jsonText){
        try {
            Ticket=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du rÃ©sultat json

            
           Map<String,Object> ticketListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            
          List<Map<String,Object>> list = (List<Map<String,Object>>)ticketListJson.get("root");
            
            //Parcourir la liste des tÃ¢ches Json
            for(Map<String,Object> obj : list){
                //CrÃ©ation des tÃ¢ches et rÃ©cupÃ©ration de leurs donnÃ©es
                Reservation p = new Reservation();
                float id = Float.parseFloat(obj.get("Id_Event").toString());
                p.setId_Event((int) id);
                
   //             SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
  //              String date = formater.format(p.getDate());           
 
                
                p. setTitre_event(obj.get("Titre_Event").toString());
                p.setDated_event(obj.get("DATED_EVENT").toString());
         
                p.setDatef_event(obj.get("DATEF_EVENT").toString());


              
             
             
           

                //Ajouter la tÃ¢che extraite de la rÃ©ponse Json Ã  la liste
                Ticket.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu rÃ©cupÃ©rer une liste des tÃ¢ches Ã  partir
        de la base de donnÃ©es Ã  travers un service web
        
        */
       return Ticket;
    }
            
    
     
}