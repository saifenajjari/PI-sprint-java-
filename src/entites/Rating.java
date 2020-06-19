/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author CASA INFO M
 */
public class Rating {
    private int id;
    private int id_jardin;
    private float rating;
    private String nom_jardin;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_jardin() {
        return id_jardin;
    }

    public void setId_jardin(int id_jardin) {
        this.id_jardin = id_jardin;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Rating(int id, int id_jardin, float rating) {
        this.id = id;
        this.id_jardin = id_jardin;
        this.rating = rating;
    }

    public Rating() {
    }

    public Rating(int id_jardin, float rating) {
        this.id_jardin = id_jardin;
        this.rating = rating;
    }

    public String getNom_jardin() {
        return nom_jardin;
    }

    public void setNom_jardin(String nom_jardin) {
        this.nom_jardin = nom_jardin;
    }
    
    
}
