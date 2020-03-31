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
public class ReservationTransport {
    private int id;
    private Ligne ligne;
    private FosUser user;

    public ReservationTransport(int id, Ligne ligne, FosUser user) {
        this.id = id;
        this.ligne = ligne;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ligne getLigne() {
        return ligne;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }

    public FosUser getUser() {
        return user;
    }

    public void setUser(FosUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ReservationTransport{" + "id=" + id + ", ligne=" + ligne + ", user=" + user + '}';
    }
    
    
    
    
   
    
}
