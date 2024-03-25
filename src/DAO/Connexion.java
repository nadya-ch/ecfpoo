package DAO;

import Log.LoggerExo;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

public class Connexion {


    //
    private static Connection connect;


    FileInputStream input = null;

    /**
     * Constructeur privé de la classe Connexion.
     *
     * Ce constructeur initialise une connexion à la base de données en utilisant les informations de connexion
     * fournies dans le fichier "database.properties".
     *
     * @throws DaoException Si une erreur survient lors de la connexion à la base de données, une exception de type DaoException est levée.
     */
    private Connexion() throws Exception {
        try {
            final Properties dataProperties = new Properties();
            File fichier = new File("database.properties");
            FileInputStream input=new FileInputStream(fichier);
            dataProperties.load(input);
            connect = DriverManager.getConnection(
                    dataProperties.getProperty("url"),
                    dataProperties.getProperty("login"),
                    dataProperties.getProperty("password") );

        } catch (FileNotFoundException | SQLException e) {
            LoggerExo.LOGGER.log(Level.SEVERE,"erreur de fichier");

            throw new DaoException("erreur lors de la connexion à la base de données : ",5);

        }

    }
    /**
     * Renvoie une instance de connexion à la base de données.
     *
     * Si aucune connexion n'est établie, cette méthode initialise une nouvelle connexion en utilisant le constructeur privé de la classe Connexion.
     * Une fois la connexion établie, elle la renvoie.
     *
     * @return Une instance de la classe Connection représentant la connexion à la base de données.
     * @throws Exception Si une erreur survient lors de l'établissement de la connexion à la base de données.
     */
    public static Connection getInstance() throws Exception {
        if (connect == null) {

                new Connexion();
                LoggerExo.LOGGER.log(Level.INFO,"connexion établie");

            }

        return connect;
    }

    /**
     * Bloc statique pour l'ajout d'un hook de fermeture lors de l'arrêt du programme.
     *
     * Ce bloc statique ajoute un hook de fermeture qui sera exécuté lors de l'arrêt du programme.
     * L'objectif de ce hook est de fermer la connexion à la base de données si elle est ouverte,
     * afin de libérer les ressources et de garantir une fermeture propre de la connexion.
     * En cas d'erreur lors de la fermeture de la connexion, le programme logguera le problème
     * et se terminera avec un code d'erreur.
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            public void run()
            {
                try {
                if (connect != null) {

                    connect.close();}

                    } catch (SQLException ex) {
                        LoggerExo.LOGGER.log(Level.SEVERE,"Problème au niveau de la connection");
                        System.exit(1);
                    }
                }

            }
        );
    }




}
