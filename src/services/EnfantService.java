package services;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entites.Enfant;
import util.DataSource1;

    /**
     *
     * @author CASA INFO M
     */
    public class EnfantService {
        private Connection connexion;
        private Statement ste;
        private ResultSet rs;

        public EnfantService() {
            //nom de laclass.nom de la variable ou de la methode
            connexion = DataSource1.getInstance().getCnx();

        }

        public void insererEnfant(Enfant f) throws SQLException, ClassNotFoundException {
            String req = "insert into enfant (parent_id,nom,prenom,age,categorie,sexe,image) values(2,'" + f.getNom() + "','" + f.getPrenom() + "','" + f.getAge() + "','" + f.getCategorie() + "','" + f.getSexe() + "','" + f.getImage() + "' )";
            try {
                ste = connexion.createStatement();
                ste.executeUpdate(req);
            } catch (SQLException ex) {
                Logger.getLogger(JardinService.class.getName()).log(Level.SEVERE, null, ex);
                throw ex;
            }
        }

        public void modifierEnfant(Enfant f) {

            String requete = "UPDATE enfant SET nom='" + f.getNom() + "' ,prenom='" + f.getPrenom() + "',age='" + f.getAge() + "' ,categorie='" + f.getCategorie() + "',sexe='" + f.getSexe() + "'  WHERE id=" + f.getId();
            try {
                ste = connexion.createStatement();
                ste.executeUpdate(requete);
                System.out.println("Enfant modifiée !");

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }

        public void supprimerEnfant(Enfant f) {
            PreparedStatement pt;
            try {
                pt = connexion.prepareStatement("delete from enfant where nom=? ");
                pt.setString(1, f.getNom());
                pt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(JardinService.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        public List<Enfant> getAll() {
            String req = "select * from enfant where parent_id=2";
            List<Enfant> lp = new ArrayList<Enfant>();
            try {
                ste = connexion.createStatement();
                rs = ste.executeQuery(req);
                while (rs.next()) {
                    lp.add(new Enfant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("age"), rs.getString("categorie"), rs.getString("sexe"), rs.getString("image"),rs.getString("parent_id")));
                }
                System.out.println(lp+ "testtttttt");
            } catch (SQLException ex) {
                Logger.getLogger(JardinService.class.getName()).log(Level.SEVERE, null, ex);
            }

            return lp;

        }
    }
