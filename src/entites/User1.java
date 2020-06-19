
package entites;

import java.util.Objects;
import java.util.prefs.Preferences;


public class User1 {
    
    private int Id_User;
    private String Login;
    private String Mdp;
    private String E_mailU;
    private String NomU;
    private String PrenomU;
    private int NumTel;
    private String PhotoProfil;
    private String RoleU;
    private String status;
    private int nbr_c;
    
    
    

    public User1() {
    }
    
    public User1(int Id_User, String Login, String Mdp) {
        this.Id_User = Id_User;
        this.Login = Login;
        this.Mdp = Mdp;
    }

    public User1(int Id_User, String Login, String Mdp, String E_mailU, String NomU, String PrenomU, int NumTel, String PhotoProfil, String RoleU) {
        this.Id_User = Id_User;
        this.Login = Login;
        this.Mdp = Mdp;
        this.E_mailU = E_mailU;
        this.NomU = NomU;
        this.PrenomU = PrenomU;
        this.NumTel = NumTel;
        this.PhotoProfil = PhotoProfil;
        this.RoleU = RoleU;
        
    }

    public User1(String Login, String Mdp, String E_mailU, String NomU, String PrenomU, int NumTel, String PhotoProfil, String RoleU) {
        this.Login = Login;
        this.Mdp = Mdp;
        this.E_mailU = E_mailU;
        this.NomU = NomU;
        this.PrenomU = PrenomU;
        this.NumTel = NumTel;
        this.PhotoProfil = PhotoProfil;
        this.RoleU = RoleU;
    }

    public User1(String Login, String Mdp, String E_mailU, String NomU, String PrenomU, int NumTel, String PhotoProfil) {
        this.Login = Login;
        this.Mdp = Mdp;
        this.E_mailU = E_mailU;
        this.NomU = NomU;
        this.PrenomU = PrenomU;
        this.NumTel = NumTel;
        this.PhotoProfil = PhotoProfil;
    }

    
    
    
    public User1(String Login, String Mdp) {
        this.Login = Login;
        this.Mdp = Mdp;
    }

    public User1(String Login, String E_mailU, int NumTel, String RoleU) {
        this.Login = Login;
        this.E_mailU = E_mailU;
        
        this.NumTel = NumTel;
        this.RoleU = RoleU;
    }

    public User1(String Login, String E_mailU, int NumTel, String RoleU, int nbr_c) {
        this.Login = Login;
        this.E_mailU = E_mailU;
        this.NumTel = NumTel;
        this.RoleU = RoleU;
        this.nbr_c = nbr_c;
    }
    
    
    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int Id_User) {
        this.Id_User = Id_User;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getMdp() {
        return Mdp;
    }

    public void setMdp(String Mdp) {
        this.Mdp = Mdp;
    }

    public String getE_mailU() {
        return E_mailU;
    }

    public void setE_mailU(String E_mailU) {
        this.E_mailU = E_mailU;
    }

    public String getNomU() {
        return NomU;
    }

    public void setNomU(String NomU) {
        this.NomU = NomU;
    }

    public String getPrenomU() {
        return PrenomU;
    }

    public void setPrenomU(String PrenomU) {
        this.PrenomU = PrenomU;
    }

    public int getNumTel() {
        return NumTel;
    }

    public void setNumTel(int NumTel) {
        this.NumTel = NumTel;
    }

    public String getPhotoProfil() {
        return PhotoProfil;
    }

    public void setPhotoProfil(String PhotoProfil) {
        this.PhotoProfil = PhotoProfil;
    }

    public String getRoleU() {
        return RoleU;
    }

    public void setRoleU(String RoleU) {
        this.RoleU = RoleU;
    }
  public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNbr_c() {
        return nbr_c;
    }

    public void setNbr_c(int nbr_c) {
        this.nbr_c = nbr_c;
    }

    public User1(int Id_User, String Login, String Mdp, String E_mailU, String NomU, String PrenomU, int NumTel, String PhotoProfil, String RoleU, String status, int nbr_c) {
        this.Id_User = Id_User;
        this.Login = Login;
        this.Mdp = Mdp;
        this.E_mailU = E_mailU;
        this.NomU = NomU;
        this.PrenomU = PrenomU;
        this.NumTel = NumTel;
        this.PhotoProfil = PhotoProfil;
        this.RoleU = RoleU;
        this.status = status;
        this.nbr_c = nbr_c;
    }
    
}