/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import entites.Bus;
import entites.Chauffeur;
import entites.FosUser;
import entites.Ligne;
import java.util.Date;


import services.BusService;
import services.ChauffeurService;
import services.LigneService;

/**
 *
 * @author yosra
 */
public class TestConsoleTransport {

    public static void main(String[] args) {

        //instance service
        BusService busService = new BusService();

        //instance bus
        
        
        //instance chauffeur
        
        Chauffeur chauffeur =new Chauffeur(10, "test", "test", 96374125);
        
        ChauffeurService chauffeurService = new ChauffeurService();
        
        chauffeurService.ajouter(chauffeur);
        
        //instance user
        FosUser u =new FosUser(1);
        
        
         
        Bus bus = new Bus(1,789,"test",chauffeur);
        busService.ajouter(bus);
        
        
        LigneService ligneService  =new LigneService();
        
        
        Ligne ligne = new Ligne(0, "", "",new Date(), "", 0, bus);
        
        ligneService.ajouter(ligne);
        
        
        
        //SELECT * FROM `bus` b join ligne l on (b.id=l.bus_id)
        
        
        for(Bus b :busService.afficher()){
            
            
            System.out.println(b);
        }
        
    }

}
