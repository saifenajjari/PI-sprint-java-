package entites;

import javafx.scene.image.Image;

public class ReservationJardin {
    private  int id;
    private  int idjardin;
    private  int idenfant;
    private String image;
    private String nom_enfant;
    private String prenom_enfant;
    private String nom_jardin;
    private Image imageload;

    public  Image getImageload() {
        imageload = new Image("file:///C:/wamp64/www/JardinEnfant/web/uploads/"+getImage());
        return imageload;
    }

    public void setImageload(Image imageload) {

        this.imageload = imageload;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom_enfant() {
        return nom_enfant;
    }

    public void setNom_enfant(String nom_enfant) {
        this.nom_enfant = nom_enfant;
    }

    public String getPrenom_enfant() {
        return prenom_enfant;
    }

    public void setPrenom_enfant(String prenom_enfant) {
        this.prenom_enfant = prenom_enfant;
    }

    public String getNom_jardin() {
        return nom_jardin;
    }

    public void setNom_jardin(String nom_jardin) {
        this.nom_jardin = nom_jardin;
    }

    public ReservationJardin(int idjardin, int idenfant) {
        this.idjardin = idjardin;
        this.idenfant = idenfant;

    }

    public ReservationJardin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdjardin() {
        return idjardin;
    }

    public void setIdjardin(int idjardin) {
        this.idjardin = idjardin;
    }

    public int getIdenfant() {
        return idenfant;
    }

    public int setIdenfant(int idenfant) {
        this.idenfant = idenfant;
        return idenfant;
    }

    @Override
    public String toString() {
        return "ReservationJardin{" +
                "id=" + id +
                ", idjardin=" + idjardin +
                ", idenfant=" + idenfant +
                ", image=" + image +
                ", nom_enfant=" + nom_enfant +
                ", prenom_enfant=" + prenom_enfant +
                ", nom_jardin=" + nom_jardin +
                '}';
    }
}
