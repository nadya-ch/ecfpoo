package Controleur;
import DAO.DaoClient;
import DAO.Daoprospect;
import UI.Affichage;
import Utilitaire.Choix;
import java.util.ArrayList;



public class Controleur_affichage {


    public Controleur_affichage() {

    }
    /**
     * Initialise l'affichage avec les choix du client ou prospect spécifiés.
     *
     * @param client_prospect Un objet de type Choix représentant les choix du client ou prospect.
     *                        Ce paramètre est utilisé pour configurer l'affichage en fonction des choix.
     */
    public static void init(Choix client_prospect)  {
        Affichage affichage = new Affichage();
        affichage.setVisible(true);
        affichage.initcomponent(client_prospect);
    }

    /**
     * Affiche une liste de clients ou de prospects en fonction des choix spécifiés.
     *
     * @param client_prospect Un objet de type Choix représentant le type de données à afficher (client ou prospect).
     *                        Utilisez l'énumération Choix.CLIENT pour afficher les clients et Choix.PROSPECT pour afficher les prospects.
     * @return Une ArrayList contenant la liste des clients ou des prospects, selon les choix spécifiés.
     * @throws Exception Si une erreur se produit lors de la récupération des données depuis la source de données.
     */


    public static ArrayList afficher(Choix client_prospect) throws Exception {
        ArrayList liste = null;
        if (client_prospect==Choix.CLIENT) {
            liste = DaoClient.findAll();

        } else {
            liste = Daoprospect.findAll();
        }
        return liste;
    }


}