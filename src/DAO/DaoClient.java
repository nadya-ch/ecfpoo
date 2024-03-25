package DAO;

import Entities.Client;
import Log.LoggerExo;
import MetierException.MetierException;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

public class DaoClient {




    /**
     * Crée un nouveau client dans la base de données.
     *
     * Cette méthode prend en charge l'insertion d'un nouveau client dans la base de données.
     * Elle exécute une requête SQL préparée pour insérer les données du client dans la table "client".
     * Si l'insertion réussit, elle récupère l'identifiant généré pour le nouveau client et l'affiche.
     * En cas d'échec de l'insertion ou d'une erreur SQL, une exception DaoException est levée avec un message approprié.
     *
     * @param client1 Un objet de type Client représentant les données du client à insérer dans la base de données.
     * @throws DaoException Si une erreur survient lors de l'insertion du client dans la base de données.
     *                      Cette exception contient un code d'erreur pour identifier la cause de l'échec de l'opération.
     */
    public static void create(Client client1) throws Exception {
        String sql = "INSERT INTO client (Raison_sociale, Num_rue, Nom_rue, Code_postal, Ville, Email, Tel, Chiffre_affaires, nb_employes, Commentaire) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try  {
            Connection connection = Connexion.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, client1.getRaison_sociale());
            preparedStatement.setString(2, client1.getNum_rue());
            preparedStatement.setString(3, client1.getNom_rue());
            preparedStatement.setString(4, client1.getCode_postal());
            preparedStatement.setString(5, client1.getVille());
            preparedStatement.setString(6, client1.getEmail());
            preparedStatement.setString(7, client1.getTel());
            preparedStatement.setDouble(8, client1.getChiffre_affaires());
            preparedStatement.setInt(9, client1.getNb_employes());
            preparedStatement.setString(10, client1.getCommentaire());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DaoException("L'insertion du client a échoué, aucune ligne affectée.", 1);
            }

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int idGenere = rs.getInt(1);
                JOptionPane.showMessageDialog(null, "L'identifiant  généré est : " + idGenere);
            }
        } catch (SQLException se) {
            if (se.getErrorCode() == 1062) {
                LoggerExo.LOGGER.info("Code erreur : " + se.getErrorCode() + ", à cause de : " + se.getCause());
                throw new DaoException("La raison sociale existe déjà.", 1);
            } else {
                LoggerExo.LOGGER.severe("Problème MySQL : " + se.getMessage() + ", code : " + se.getSQLState());
                throw new DaoException("Message : " + se.getMessage(), 5);
            }
        }
    }



    /**
     * Met à jour les informations d'un client dans la base de données.
     *
     * Cette méthode prend en charge la mise à jour des informations d'un client dans la base de données.
     * Elle exécute une requête SQL préparée pour mettre à jour les données du client dans la table "client".
     * Si la mise à jour réussit, les informations du client sont actualisées dans la base de données.
     * En cas d'échec de la mise à jour ou d'une erreur SQL, une exception DaoException est levée avec un message approprié.
     *
     * @param client1 Un objet de type Client représentant les nouvelles données du client à mettre à jour dans la base de données.
     * @throws DaoException Si une erreur survient lors de la mise à jour des informations du client dans la base de données.
     *                      Cette exception contient un code d'erreur pour identifier la cause de l'échec de l'opération.
     */
    public static void update(Client client1) throws Exception {

        Connection connection = Connexion.getInstance();
        String updateString = "UPDATE client SET  Raison_sociale=?, " +
                "Num_rue=?, Nom_rue=?, Code_postal=?, Ville=?, email=?, Chiffre_affaires=?, " +
                "tel=?, nb_employes=?, Commentaire=? WHERE id_client=?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateString);
        try {
            preparedStatement.setString(1, client1.getRaison_sociale());
            preparedStatement.setString(2, client1.getNum_rue());
            preparedStatement.setString(3, client1.getNom_rue());
            preparedStatement.setString(4, client1.getCode_postal());
            preparedStatement.setString(5, client1.getVille());
            preparedStatement.setString(6, client1.getEmail());
            preparedStatement.setDouble(7, client1.getChiffre_affaires());
            preparedStatement.setString(8, client1.getTel());
            preparedStatement.setInt(9, client1.getNb_employes());
            preparedStatement.setString(10, client1.getCommentaire());
            preparedStatement.setInt(11, client1.getIdentifiant());
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            if (se.getErrorCode() == 1062) {
                LoggerExo.LOGGER.info("code erreur" + se.getErrorCode() + "à cause de " + se.getCause());
                throw new DaoException("la raison sociale existe déjà", 1);
            } else {

                LoggerExo.LOGGER.severe("problème mysql" + se.getMessage() + "code" + se.getSQLState());
                throw new DaoException("message :" + se.getMessage(), 5);
            }
        }
    }
    /**
     * Recherche un client par son nom dans la base de données.
     *
     * Cette méthode recherche un client dans la base de données en utilisant son nom (raison sociale).
     * Elle exécute une requête SQL préparée pour sélectionner les données du client correspondant à son nom.
     * Si un client est trouvé avec le nom spécifié, un objet Client correspondant est créé avec les données récupérées.
     * Si aucun client n'est trouvé, la méthode renvoie null.
     * En cas d'erreur SQL ou de problème lors de la recherche, une exception DaoException est levée avec un message approprié.
     *
     * @param Raison_sociale Le nom (raison sociale) du client à rechercher dans la base de données.
     * @return Un objet de type Client représentant le client trouvé dans la base de données, ou null s'il n'existe pas.
     * @throws DaoException Si une erreur survient lors de la recherche du client dans la base de données.
     *                      Cette exception contient un code d'erreur pour identifier la cause de l'échec de l'opération.
     */
    public static Client findByName(String Raison_sociale) throws Exception {
        Connection connection = Connexion.getInstance();
        String query = "SELECT id_client, Raison_sociale, Num_rue, Nom_rue, Code_postal, Ville," +
                " Email, Chiffre_affaires, Tel," +
                " nb_employes, Commentaire FROM client WHERE Raison_sociale = ?";
        Client c = null;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setString(1, Raison_sociale);
            ResultSet rs = preparedStatement.executeQuery();
            int id_client;
            String Num_rue, Tel, Nom_rue, Code_postal, Ville, Email;
            int nb_employes;
            double Chiffre_affaires;
            String Commentaire;
            while (rs.next()) {
                id_client = rs.getInt("id_client");
                Num_rue = rs.getString("Num_rue");
                Nom_rue = rs.getString("Nom_rue");
                Code_postal = rs.getString("Code_postal"); // Correction ici
                Ville = rs.getString("Ville");
                Email = rs.getString("Email");
                Chiffre_affaires = rs.getDouble("Chiffre_affaires");
                Tel = rs.getString("Tel");
                nb_employes = rs.getInt("nb_employes");
                Commentaire = rs.getString("Commentaire");

                c = new Client(id_client, Raison_sociale, Num_rue, Nom_rue, Code_postal, Ville, Tel, Email, Commentaire, Chiffre_affaires,
                        nb_employes);

            }
        } catch (SQLException e) {
            LoggerExo.LOGGER.log(Level.SEVERE, "pb  an niveau de  finbyname" + e.getMessage());
            throw new DaoException("pb  an niveau de  find by name", 1);
        }
        return c;
    }
    /**
     * Supprime un client de la base de données en fonction de son nom (raison sociale).
     *
     * Cette méthode supprime un client de la base de données en utilisant son nom (raison sociale).
     * Elle exécute une requête SQL préparée pour supprimer les données du client correspondant à son nom.
     * Si un client est trouvé avec le nom spécifié, ses données sont supprimées de la base de données.
     * En cas d'erreur SQL ou de problème lors de la suppression, une exception DaoException est levée avec un message approprié.
     *
     * @param Raison_sociale Le nom (raison sociale) du client à supprimer de la base de données.
     * @throws DaoException Si une erreur survient lors de la suppression du client dans la base de données.
     *                      Cette exception contient un code d'erreur pour identifier la cause de l'échec de l'opération.
     */
    public static void delete(String Raison_sociale) throws Exception {
        Connection connection = Connexion.getInstance();
        String query = "delete FROM client WHERE Raison_sociale = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setString(1, Raison_sociale);
        } catch (SQLException e) {
            throw new DaoException("pb  an niveau delete", 1);
        }
        preparedStatement.executeUpdate();
    }

    /**
     * Récupère tous les clients de la base de données.
     *
     * Cette méthode récupère tous les clients enregistrés dans la base de données.
     * Elle exécute une requête SQL pour sélectionner toutes les données des clients.
     * Chaque client récupéré est ensuite ajouté à une liste d'objets Client.
     * Si aucun client n'est trouvé, une liste vide est renvoyée.
     * En cas d'erreur SQL ou de problème lors de la récupération, une exception DaoException est levée avec un message approprié.
     *
     * @return Une liste d'objets Client contenant tous les clients de la base de données, ou une liste vide si aucun client n'est trouvé.
     * @throws DaoException Si une erreur survient lors de la récupération des clients dans la base de données.
     *                      Cette exception contient un code d'erreur pour identifier la cause de l'échec de l'opération.
     */

    public static ArrayList<Client> findAll() throws Exception {

        Connection connection = Connexion.getInstance();

        ArrayList<Client> ClientList = null;
        try {
            ClientList = new ArrayList<Client>();
            String query = "SELECT id_client, Raison_sociale, Num_rue, Nom_rue,Ville, Code_postal,  Email," +
                    " Tel,Chiffre_affaires,nb_employes, Commentaire FROM client";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.setIdentifiant(rs.getInt(1));
                client.setRaison_sociale(rs.getString(2));
                client.setNum_rue(rs.getString(3));
                client.setNom_rue(rs.getString(4));
                client.setCode_postal(rs.getString(5));
                client.setVille(rs.getString(6));
                client.setEmail(rs.getString(7));
                client.setTel(rs.getString(8));
                client.setChiffre_affaires(rs.getDouble(9));
                client.setNb_employes(rs.getInt(10));
                client.setCommentaire(rs.getString(11));
                ClientList.add(client);

            }
        } catch (SQLException e) {
            LoggerExo.LOGGER.log(Level.SEVERE, "pb  an niveau de  findall" + e.getMessage());

            throw new DaoException("pb  au niveau de  findall", 5);        }
        return ClientList;
    }
}






