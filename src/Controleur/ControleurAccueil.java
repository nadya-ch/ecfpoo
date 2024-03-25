package Controleur;

import DAO.DaoClient;
import DAO.Daoprospect;
import Entities.Client;
import Entities.Prospect;
import UI.Accueil;
import Utilitaire.ChoixAction;
import Utilitaire.Choix;

import java.util.ArrayList;

public class ControleurAccueil {


    public ControleurAccueil() {

    }

    /**
     * Initialise la fenêtre d'accueil.
     *
     * Cette méthode crée une instance de la classe Accueil et la rend visible.
     */
    public static void init() {
        Accueil accueil = new Accueil();
        accueil.setVisible(true);
    }

    /**
     * Remplit une liste de raisons sociales à afficher dans une ComboBox en fonction du choix spécifié.
     *
     * @param choi Un objet de type Choix représentant le type de données à utiliser (client ou prospect).
     *             Utilisez Choix.CLIENT pour récupérer les raisons sociales des clients,
     *             et Choix.PROSPECT pour récupérer les raisons sociales des prospects.
     * @return Une ArrayList de chaînes de caractères contenant les raisons sociales correspondantes.
     * @throws Exception Si une erreur se produit lors de la récupération des données depuis la source de données.
     */
    public static ArrayList<String> remplirCombox(Choix choi) throws Exception {

        ArrayList<String> raison = null;
        if (choi==Choix.CLIENT) {
            raison = new ArrayList<>();
            ArrayList<Client> clients = DaoClient.findAll();
            for (Client c : clients) {
                raison.add(c.getRaison_sociale());
            }
        } else {
            raison = new ArrayList<>();
            ArrayList<Prospect> prospects = Daoprospect.findAll();
            for (Prospect p : prospects) {
                raison.add(p.getRaison_sociale());
            }
        }

        return raison;
    }
    /**
     * Affiche les données des clients ou des prospects en fonction du choix spécifié.
     *
     * Cette méthode utilise le contrôleur d'affichage pour initialiser l'affichage en fonction du type de données spécifié.
     *
     * @param client_prospect Un objet de type Choix représentant le type de données à afficher (client ou prospect).
     *                        Utilisez Choix.CLIENT pour afficher les données des clients,
     *                        et Choix.PROSPECT pour afficher les données des prospects.
     * @throws Exception Si une erreur se produit lors de l'affichage des données.
     */
    public static void affichage(Choix client_prospect)throws Exception {

        Controleur_affichage.init(client_prospect);
    }


    /**
     * Initialise le formulaire de modification ou de suppression en fonction de l'action et du type spécifiés.
     *
     * Cette méthode utilise le contrôleur de formulaire pour initialiser le formulaire de modification
     * ou de suppression en fonction de l'action et du type de données spécifiés.
     *
     * @param action         Un objet de type ChoixAction représentant l'action à effectuer (modification ou suppression).
     *                       Utilisez ChoixAction.MODIFICATION pour initialiser le formulaire de modification
     *                       et ChoixAction.SUPPRESSION pour initialiser le formulaire de suppression.
     * @param client_prospect Un objet de type Choix représentant le type de données à traiter (client ou prospect).
     *                       Utilisez Choix.CLIENT pour traiter les données des clients
     *                       et Choix.PROSPECT pour traiter les données des prospects.
     * @param raison         La raison sociale du client ou prospect concerné.
     * @throws Exception     Si une erreur se produit lors de l'initialisation du formulaire.
     */
    public static void modif_supp(ChoixAction action, Choix client_prospect, String raison) throws Exception {
        ControleurFormulaire.init_form_modif_supp(action,client_prospect,raison);

    }
    /**
     * Initialise le formulaire de création en fonction du type de données et de l'action spécifiés.
     *
     * Cette méthode utilise le contrôleur de formulaire pour initialiser le formulaire de création en fonction
     * du type de données (client ou prospect) et de l'action à effectuer.
     *
     * @param c     Un objet de type Choix représentant le type de données pour lequel le formulaire doit être créé
     *              (client ou prospect).
     * @param actio Un objet de type ChoixAction représentant l'action à effectuer dans le formulaire
     *              (par exemple, création).
     * @throws Exception Si une erreur survient lors de l'initialisation du formulaire.
     */
    public static void formulaire_creation(Choix c, ChoixAction actio){
        ControleurFormulaire.init(c,actio);
    }

    }



