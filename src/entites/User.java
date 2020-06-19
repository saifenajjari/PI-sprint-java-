/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;

/**
 *
 * @author mazen
 */
public class User {
    private int id;
    private String username;
    private String username_canonical =null;
    private String email;
    private String email_canonical=null;
    private int    enabled =1;
    private String salt=null;
    private String password;
    private Date   last_login=null;
    private String confirmation_token=null;
    private Date   password_requested_at;
    private String roles;
    private String nom;
    private String prenom;
    private String demande;



    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String username, String email, String password, String roles, String nom, String prenom , String demande) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.demande = demande;
     
    }

    public User(String username, String password , String roles ,String email ,String nom ,String prenom ) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
    }

    public User(String email, String password, String cin, String sexe) {
        this.email = email;
        this.password = password;
    }
  
    
    

    public User(int id, String email, String nom, String prenom ) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
    
    }

   
    

    public User(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
      
    }

//    public User(int id, String nom, String prenom, String email, String cin, int num_tel, String sexe) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public User(int aInt, String string, String string0, String string1, int aInt0, int aInt1, String string2) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public User(int aInt, String string, String string0, String string1, String string2, int aInt0) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public User(int id, String username, String password, String email, String nom, String prenom, String cin, String sexe, String role) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public User(String string, String string0, String string1, String string2, String string3) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//   
//
//    public User(String string, String string0, String string1, String string2, String string3, String string4) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public User(int aInt, int aInt0, String string, String string0, String string1, String string2) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    

     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDemande() {
        return demande;
    }

    public void setDemande(String demande) {
        this.demande = demande;
    }

    
}
