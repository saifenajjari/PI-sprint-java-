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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Evenement;
import com.mycompany.entities.Reservation;

import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mahmoud ennouri
 */
public class EventService {

    public ArrayList<Evenement> Evenement;
    public ArrayList<Reservation> Reservation;
    ArrayList<Evenement> listPlat = new ArrayList<>();
    public static EventService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    Reservation reser = new Reservation();
    

    public EventService() {
        req = new ConnectionRequest();
    }

    public static EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }
        return instance;
    }

    public ArrayList<Evenement> parseEvents(String jsonText) {
        try {
            Evenement = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du rÃ©sultat json

            Map<String, Object> platListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) platListJson.get("root");

            //Parcourir la liste des tÃ¢ches Json
            for (Map<String, Object> obj : list) {
                //CrÃ©ation des tÃ¢ches et rÃ©cupÃ©ration de leurs donnÃ©es
                Evenement p = new Evenement();
                float idEvent = Float.parseFloat(obj.get("idEvent").toString());
                p.setId_Event((int) idEvent);

                float NbrPlaceE = Float.parseFloat(obj.get("nbrPlaceE").toString());
                p.setId_Event((int) NbrPlaceE);

                float NbrR = Float.parseFloat(obj.get("nbrR").toString());
                p.setId_Event((int) NbrR);

                //             SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                //              String date = formater.format(p.getDate());           
                p.setTitreEvent(obj.get("titreEvent").toString());
                p.setImageEvent(obj.get("imageEvent").toString());
                p.setDatedEvent(obj.get("datedEvent").toString());
                p.setDatefEvent(obj.get("datefEvent").toString());
                p.setCategorieEvent(obj.get("categorieEvent").toString());
                p.setDescEvent(obj.get("descrEvent").toString());

                //Ajouter la tÃ¢che extraite de la rÃ©ponse Json Ã  la liste
                Evenement.add(p);
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu rÃ©cupÃ©rer une liste des tÃ¢ches Ã  partir
        de la base de donnÃ©es Ã  travers un service web
        
         */
        return Evenement;
    }

    public ArrayList<Evenement> getAllEvents() {
        String url = "http://localhost/JardinEnfantsaif/web/app_dev.php/tech/listE";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Evenement = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Evenement;
    }

    public boolean addReservations(Reservation t, int Id_Event) {
        req.setUrl("http://localhost/JardinEnfantsaif/web/app_dev.php/tech/reservjson/" + Id_Event + "/?Titre_event=" + t.getTitre_event());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public Evenement getEvenement(Evenement evenement, int Id_Event) {
        return null;
    }
}
