package Controleur;

import DAO.DaoClient;
import DAO.Daoprospect;
import Entities.Client;
import Entities.Prospect;
import Entities.Societe;
import UI.Formulaire;
import Utilitaire.ChoixAction;
import Utilitaire.Choix;

import static UI.Accueil.raison_select;


public class ControleurFormulaire {


    public ControleurFormulaire() {

    }
    /**
     * Initialise un nouveau formulaire en fonction du choix de gestion et de l'action spécifiés,
     * puis affiche la fenêtre du formulaire.
     *
     * @param client_prospect Le choix de gestion (CLIENT ou PROSPECT).
     * @param action L'action à effectuer (CREATION, MODIFICATION ou SUPPRESSION).
     */
    public static void init(Choix client_prospect, ChoixAction action) {
        Formulaire formulaire = new Formulaire();
        formulaire.setVisible(true);
        formulaire.initcomponent(client_prospect,action);
    }
    /**
     * Crée une nouvelle société dans la base de données.
     *
     * Cette méthode prend en charge la création d'un nouveau client ou prospect dans la base de données
     * en fonction du type de société spécifié.
     *
     * @param s Un objet de type Societe représentant la société à créer.
     * @throws Exception Si une erreur survient lors de la création de la société dans la base de données.
     */
    public static void creation(Societe s) throws Exception {
            if (s instanceof Client) {
                DaoClient.create((Client) s);
            } else {
                Daoprospect.create((Prospect) s);
            }
    }

    /**
     * Recherche et renvoie une société (client ou prospect) en fonction du type et du nom spécifiés.
     *
     * Cette méthode recherche une société (client ou prospect) dans la base de données en fonction du type spécifié
     * (client ou prospect) et du nom de la société. Elle retourne l'objet Societe correspondant trouvé dans la base de données.
     *
     * @param client_prospect Un objet de type Choix représentant le type de société à rechercher (client ou prospect).
     * @param raison          Le nom de la société à rechercher.
     * @return Un objet de type Societe représentant la société trouvée dans la base de données.
     * @throws Exception      Si une erreur survient lors de la recherche de la société dans la base de données.
     */
    public static Societe modif_client_prospect(Choix client_prospect,String raison) throws Exception {
        if (client_prospect == Choix.CLIENT){
            return DaoClient.findByName(raison);

        } else {
            return Daoprospect.findByName(raison);

        }

    }
    /**
     * Modifie les informations d'une société dans la base de données.
     *
     * Cette méthode prend en charge la modification des informations d'une société (client ou prospect) dans la base de données
     * en fonction du type de société spécifié. Elle met à jour les informations de la société avec les nouvelles données fournies.
     *
     * @param s Un objet de type Societe représentant la société à modifier.
     * @throws Exception Si une erreur survient lors de la modification des informations de la société dans la base de données.
     */
    public static void modification(Societe s) throws Exception {

        if (s instanceof Client) {
            DaoClient.update((Client) s);
        } else {
            Daoprospect.update((Prospect) s);
        }
    }
    public static void retourPageAccueil() {
        ControleurAccueil.init();
    }
    /**
     * Initialise le formulaire de modification ou de suppression en fonction de l'action et du type de société spécifiés.
     *
     * Cette méthode crée une instance du formulaire et initialise ses composants en fonction de l'action à effectuer
     * (modification ou suppression) et du type de société (client ou prospect) associé à la raison sociale spécifiée.
     *
     * @param action         Un objet de type ChoixAction représentant l'action à effectuer (modification ou suppression).
     * @param client_prospect Un objet de type Choix représentant le type de société à traiter (client ou prospect).
     * @param raison_sociale La raison sociale de la société pour laquelle le formulaire est initialisé.
     */
    public static void init_form_modif_supp(ChoixAction action, Choix client_prospect, String raison_sociale) {
        Formulaire f = new Formulaire();
        f.initcomponent(action,client_prospect,raison_sociale);
    }

    /**
     * Supprime une société (client ou prospect) de la base de données en fonction du type et de la raison sociale spécifiés.
     *
     * Cette méthode prend en charge la suppression d'une société (client ou prospect) de la base de données en fonction du type spécifié
     * (client ou prospect) et de la raison sociale de la société à supprimer.
     *
     * @param cl_pr  Un objet de type Choix représentant le type de société à supprimer (client ou prospect).
     * @param raiso  La raison sociale de la société à supprimer.
     * @throws Exception Si une erreur survient lors de la suppression de la société de la base de données.
     */
    public static void delete(Choix cl_pr,String raiso) throws Exception {
        if (cl_pr==Choix.CLIENT) {
            DaoClient.delete(raiso);
        } else {
            Daoprospect.delete(raiso);
        }
    }

}
