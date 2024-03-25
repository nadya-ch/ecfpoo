package DAO;


import Entities.Prospect;
import Log.LoggerExo;
import MetierException.MetierException;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;

import static java.sql.Date.valueOf;


public class Daoprospect {

    /**
     * Crée un nouveau prospect dans la base de données.
     *
     * Cette méthode prend en charge l'insertion d'un nouveau prospect dans la base de données.
     * Elle exécute une requête SQL préparée pour insérer les données du prospect dans la table "prospect".
     * Si l'insertion réussit, elle récupère l'identifiant généré pour le nouveau prospect et l'affiche.
     * En cas d'échec de l'insertion ou d'une erreur SQL, une exception DaoException est levée avec un message approprié.
     *
     * @param p Un objet de type Prospect représentant les données du prospect à insérer dans la base de données.
     * @throws DaoException Si une erreur survient lors de l'insertion du prospect dans la base de données.
     *                      Cette exception contient un code d'erreur pour identifier la cause de l'échec de l'opération.
     */

    public static void create(Prospect p) throws Exception {

        String sql = "INSERT INTO prospect ( Raison_sociale, Num_rue, Nom_rue, Code_postal, Ville, Email, Tel, Date_prospection, Prospect_interesse,Commentaire) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = Connexion.getInstance();

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, p.getRaison_sociale());
            preparedStatement.setString(2, p.getNum_rue());
            preparedStatement.setString(3, p.getNom_rue());
            preparedStatement.setString(4, p.getCode_postal());
            preparedStatement.setString(5, p.getVille());
            preparedStatement.setString(6, p.getEmail());
            preparedStatement.setString(7, p.getTel());
            preparedStatement.setDate(8, valueOf(p.getDate_prospection()));
            preparedStatement.setString(9, p.getProspect_interesse());
            preparedStatement.setString(10, p.getCommentaire());


            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int idGenere = rs.getInt(1);
                JOptionPane.showMessageDialog(null, "L'identifiant  généré est : " + idGenere);

            }


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
     * Récupère tous les prospects de la base de données.
     *
     * Cette méthode récupère tous les prospects enregistrés dans la base de données.
     * Elle exécute une requête SQL pour sélectionner toutes les données des prospects.
     * Chaque prospect récupéré est ensuite ajouté à une liste d'objets Prospect.
     * Si aucun prospect n'est trouvé, une liste vide est renvoyée.
     * En cas d'erreur SQL ou de problème lors de la récupération, une exception DaoException est levée avec un message approprié.
     *
     * @return Une liste d'objets Prospect contenant tous les prospects de la base de données, ou une liste vide si aucun prospect n'est trouvé.
     * @throws DaoException Si une erreur survient lors de la récupération des prospects dans la base de données.
     *                      Cette exception contient un code d'erreur pour identifier la cause de l'échec de l'opération.
     */
    public static ArrayList<Prospect> findAll() throws Exception {
        Connection connection = Connexion.getInstance();
        ArrayList<Prospect> prospects = null;
        try {
            prospects = new ArrayList<Prospect>();

            String query = "SELECT id_prospect, Raison_sociale, Num_rue, Nom_rue, Code_postal, Ville, EMail," +
                    " Date_prospection, Tel, prospect_interesse, Commentaire FROM prospect";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Prospect prospect = new Prospect();

                prospect.setIdentifiant(rs.getInt(1));
                prospect.setRaison_sociale(rs.getString(2));
                prospect.setNum_rue(rs.getString(3));
                prospect.setNom_rue(rs.getString(4));
                prospect.setCode_postal(rs.getString(5));
                prospect.setVille(rs.getString(6));
                prospect.setEmail(rs.getString(7));
                prospect.setDate_prospection(rs.getDate(8).toLocalDate());
                prospect.setTel(rs.getString(9));
                prospect.setProspect_interesse(rs.getString(10));
                prospect.setCommentaire(rs.getString(11));

                prospects.add(prospect);
            }
        } catch (SQLException e) {
            LoggerExo.LOGGER.log(Level.SEVERE, "pb  an niveau de  findalla" + e.getMessage());
            throw new DaoException("pb  an niveau de  findall", 1);


        }

        return prospects;
    }

    /**
     * Met à jour les informations d'un prospect dans la base de données.
     *
     * Cette méthode prend en charge la mise à jour des informations d'un prospect dans la base de données.
     * Elle exécute une requête SQL préparée pour mettre à jour les données du prospect dans la table "prospect".
     * Si la mise à jour réussit, les informations du prospect sont actualisées dans la base de données.
     * En cas d'échec de la mise à jour ou d'une erreur SQL, une exception DaoException est levée avec un message approprié.
     *
     * @param prospect Un objet de type Prospect représentant les nouvelles données du prospect à mettre à jour dans la base de données.
     * @throws DaoException Si une erreur survient lors de la mise à jour des informations du prospect dans la base de données.
     *                      Cette exception contient un code d'erreur pour identifier la cause de l'échec de l'opération.
     */
    public static void update(Prospect prospect) throws Exception {
        Connection connection = Connexion.getInstance();

        String updateString = "UPDATE prospect SET  Raison_sociale=?, " +
                "Num_rue=?, Nom_rue=?, Code_postal=?, Ville=?, Email=?, date_prospection=?, " +
                "Tel=?, prospect_interesse=?, Commentaire=? WHERE id_prospect=?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateString);

        try {
            preparedStatement.setString(1, prospect.getRaison_sociale());
            preparedStatement.setString(2, prospect.getNum_rue());
            preparedStatement.setString(3, prospect.getNom_rue());
            preparedStatement.setString(4, prospect.getCode_postal());
            preparedStatement.setString(5, prospect.getVille());
            preparedStatement.setString(6, prospect.getEmail());
            preparedStatement.setDate(7, valueOf(prospect.getDate_prospection()));
            preparedStatement.setString(8, prospect.getTel());
            preparedStatement.setString(9, prospect.getProspect_interesse());
            preparedStatement.setString(10, prospect.getCommentaire());
            preparedStatement.setInt(11, prospect.getIdentifiant());
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
     * Supprime un prospect de la base de données en fonction de sa raison sociale.
     *
     * Cette méthode prend en charge la suppression d'un prospect de la base de données en utilisant sa raison sociale.
     * Elle exécute une requête SQL préparée pour supprimer les données du prospect correspondant à sa raison sociale dans la table "prospect".
     * Si la suppression réussit, les données du prospect sont retirées de la base de données.
     * En cas d'échec de la suppression ou d'une erreur SQL, une exception DaoException est levée avec un message approprié.
     *
     * @param Raison_sociale La raison sociale du prospect à supprimer de la base de données.
     * @throws DaoException Si une erreur survient lors de la suppression du prospect dans la base de données.
     *                      Cette exception contient un code d'erreur pour identifier la cause de l'échec de l'opération.
     */
    public static void delete(String Raison_sociale) throws Exception {
        Connection connection = Connexion.getInstance();
        String query = "delete FROM prospect WHERE Raison_sociale = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {

            preparedStatement.setString(1, Raison_sociale);


        } catch (SQLException e) {

            throw new DaoException("pb au niveau delete", 1);
        }
        preparedStatement.executeUpdate();

    }


    /**
     * Recherche un prospect dans la base de données en fonction de sa raison sociale.
     *
     * Cette méthode récupère les informations d'un prospect enregistré dans la base de données en utilisant sa raison sociale.
     * Elle exécute une requête SQL préparée pour sélectionner les données du prospect correspondant à sa raison sociale dans la table "prospect".
     * Si le prospect est trouvé, un objet de type Prospect contenant ses informations est créé et retourné.
     * Si aucun prospect n'est trouvé avec la raison sociale donnée, null est retourné.
     * En cas d'erreur SQL ou de problème lors de la récupération, une exception DaoException est levée avec un message approprié.
     *
     * @param Raison_sociale La raison sociale du prospect à rechercher dans la base de données.
     * @return Un objet de type Prospect contenant les informations du prospect correspondant à la raison sociale spécifiée, ou null si aucun prospect n'est trouvé.
     * @throws DaoException Si une erreur survient lors de la recherche du prospect dans la base de données.
     *                      Cette exception contient un code d'erreur pour identifier la cause de l'échec de l'opération.
     */
    public static Prospect findByName(String Raison_sociale) throws Exception {
        Connection connection = Connexion.getInstance();
        String query = "SELECT id_prospect, Raison_sociale, Num_rue, Nom_rue, Code_postal, Ville, Email," +
                " date_prospection, " +
                "Tel, prospect_interesse, Commentaire FROM prospect WHERE Raison_sociale = ?";
        Prospect p = null;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            preparedStatement.setString(1, Raison_sociale);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id_prospect = rs.getInt("id_prospect");

                String Num_rue = rs.getString("Num_rue");
                String Nom_rue = rs.getString("Nom_rue");
                String Code_postal = rs.getString("code_postal");
                String Ville = rs.getString("Ville");
                String email = rs.getString("email");
                LocalDate date_prospection = rs.getDate("date_prospection").toLocalDate();
                String Tel = rs.getString("Tel");
                String prospect_interesse = rs.getString("prospect_interesse");
                String Commentaire = rs.getString("Commentaire");

                p = new Prospect(id_prospect, Raison_sociale, Num_rue, Nom_rue, Code_postal, Ville, Tel, email,
                        Commentaire, date_prospection, prospect_interesse);
            }
        } catch (SQLException e) {
            LoggerExo.LOGGER.log(Level.SEVERE, "pb  an niveau de  finbyname" + e.getMessage());
            throw new DaoException("pb  an niveau de  find by name", 1);

        }
        return p;
    }

}
