package entites;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Jardin {
    private int id;
    private String nom;
    private String address;
    private int nbenfant;
    private String description ;
    private int numTel;
    private String image ;
    private int placesDisponibles;
    private String organisationDesClasses;
    private Image imageload;
    private Boolean active;

    public Jardin(int id, String nom, String address, int nbenfant,  String description,int numTel, String organisationDesClasses,String image1) {
         this.id = id;
        this.nom = nom;
        this.address = address;
        this.nbenfant = nbenfant;
        this.numTel = numTel;
        this.description = description;
        this.organisationDesClasses = organisationDesClasses;
        this.image=image1;
    }

    public  Image getImageload() {
        imageload = new Image("file:///C:/wamp64/www/JardinEnfant/web/uploads/"+getImage());
        return imageload;
    }

    public void setImageload(Image imageload) {

        this.imageload = imageload;
    }

    public Jardin(int id) {
        this.id = id;
    }

    public Jardin() {

    }

    public Jardin (int id, String nom, String address, int nbenfant, String description, int numTel, String image, int placesDisponibles, String organisationDesClasses){
        this.id = id;
        this.nom = nom;
        this.address= address;
        this.nbenfant= nbenfant;
        this.description=description;
        this.image=image;
        this.placesDisponibles= placesDisponibles ;
        this.numTel=numTel;
        this.organisationDesClasses=organisationDesClasses;
        

    }
    public Jardin (String nom,String address,int nbenfant,String description,int numTel,String organisationDesClasses,String image ){

        this.nom = nom;
        this.address= address;
        this.placesDisponibles= nbenfant;
        this.description=description;
        this.numTel=numTel;
        this.organisationDesClasses=organisationDesClasses;
        this.image= image;
     
    }
    public Jardin (String nom,String address,int nbenfant,String description,int numTel,String organisationDesClasses ){

        this.nom = nom;
        this.address= address;
        this.nbenfant= nbenfant;
        this.description=description;
        this.numTel=numTel;
        this.organisationDesClasses=organisationDesClasses;
        
    }

    public Jardin(String nom) {
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

    public String setNom(String nom) {
        this.nom = nom;
        return nom;
    }

    public Boolean getActive() {
        active = true;
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNbenfant() {
        return nbenfant;
    }

    public void setNbenfant(int nbenfant) {
        this.nbenfant = nbenfant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPlacesDisponibles() {
        return placesDisponibles;
    }

    public void setPlacesDisponibles(int placesDisponibles) {
        this.placesDisponibles = placesDisponibles;
    }

    public String getOrganisationDesClasses() {
        return organisationDesClasses;
    }


    public void setOrganisationDesClasses(String organisationDesClasses) {
        this.organisationDesClasses = organisationDesClasses;
    }
    @Override
    public String toString() {
        return "Jardin{" + "id=" + id + ", nom=" + nom + ", address=" + address + ", nbenfant=" + nbenfant + ", description=" + description + ", numTel=" + numTel + ", image=" + image + ", placesDisponibles=" + placesDisponibles + ", organisationDesClasses=" + organisationDesClasses +'}';
    }
}
