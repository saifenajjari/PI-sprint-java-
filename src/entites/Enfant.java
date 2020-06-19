package entites;

import javafx.scene.image.Image;

public class Enfant {
    private int id;
    private String nom;
    private String prenom;
    private int age;
    private String categorie;
    private String sexe;
    private Jardin jardin;
    private Image imageload;
    private String image ;
    private String parent_id;

    public Enfant() {
       
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }



   


    public  Image getImageload() {
        imageload = new Image("file:///C:/wamp64/www/JardinEnfant/web/uploads/"+getImage());
        return imageload;
    }

    public void setImageload(Image imageload) {

        this.imageload = imageload;
    }
    public Enfant(int id, String nom, String prenom, int age, String categorie, String sexe,String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.categorie = categorie;
        this.sexe = sexe;
        this.image =image ;
    }

    public Enfant(int id, String nom, String prenom, int age, String categorie, String sexe, String image, String parent_id) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.categorie = categorie;
        this.sexe = sexe;
        this.image = image;
        this.parent_id = parent_id;
    }
    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Enfant(String nom, String prenom, int age, String categorie, String sexe,String image) {

        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.categorie = categorie;
        this.sexe = sexe;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }



    public Jardin getJardin() {
        return jardin;
    }

    public void setJardin(Jardin jardin) {
        this.jardin = jardin;
    }




    @Override
    public String toString() {
        return "Enfant{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", categorie=" + categorie + ", sexe=" + sexe +", image=" + image +",parent_id=" + parent_id +'}';
    }
}
