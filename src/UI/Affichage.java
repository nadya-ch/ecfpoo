package UI;

import Controleur.ControleurAccueil;
import Controleur.Controleur_affichage;
import DAO.DaoException;
import Entities.Client;
import Entities.Prospect;
import Log.LoggerExo;
import Utilitaire.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;


public class Affichage extends JDialog {
    private JPanel contentPane;
    private JTable tableaffichage;
    private JButton btnretour;


    public Affichage() {
        setContentPane(contentPane);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        btnretour.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ControleurAccueil.init();
                onCancel();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }



    /**
     * Initialise les composants de l'interface utilisateur en fonction du choix spécifié.
     * Définit la taille de la fenêtre à 1000 pixels de largeur et 300 pixels de hauteur.
     * Remplit le tableau d'affichage avec les données des clients ou des prospects en fonction du choix.
     * Si le choix est CLIENT, les données des clients sont affichées dans le tableau.
     * Si le choix est PROSPECT, les données des prospects sont affichées dans le tableau.
     * Les données sont récupérées à partir du contrôleur d'affichage.
     * En cas d'erreur lors de la récupération des données, affiche un message d'erreur et arrête l'application.
     *
     * @param s Le choix spécifié (CLIENT ou PROSPECT) pour afficher les données correspondantes.
     */
    public void initcomponent(Choix s) {
        this.setSize(1000, 300);

        try {
            ArrayList clients = Controleur_affichage.afficher(s);
            if (s == Choix.CLIENT) {

                Object[][] tab = new Object[clients.size()][10];
                for (int i = 0; i < clients.size(); i++) {
                    Client client = (Client) clients.get(i);
                    tab[i][0] = client.getIdentifiant();
                    tab[i][1] = client.getRaison_sociale();
                    tab[i][2] = client.getNum_rue();
                    tab[i][3] = client.getNom_rue();

                    tab[i][4] = client.getVille();
                    tab[i][5] = client.getCode_postal();
                    tab[i][6] = client.getTel();
                    tab[i][7] = client.getEmail();
                    tab[i][8] = client.getChiffre_affaires();
                    tab[i][9] = client.getNb_employes();
                }
                String[] colonne = {"id", "Raison sociale", "Num_rue", "Nom_rue", "Ville", "Code postale", "Tel",
                        "Email", "Chiffre affaire", "Nombre employe"};
                DefaultTableModel model = new DefaultTableModel(tab, colonne);
                tableaffichage.setModel(model);

            } else {

                Object[][] tab = new Object[clients.size()][10];
                for (int i = 0; i < clients.size(); i++) {
                    Prospect prospect = (Prospect) clients.get(i);
                    tab[i][0] = prospect.getIdentifiant();
                    tab[i][1] = prospect.getRaison_sociale();
                    tab[i][2] = prospect.getNum_rue();
                    tab[i][3] = prospect.getNom_rue();
                    tab[i][4] = prospect.getVille();
                    tab[i][5] = prospect.getCode_postal();
                    tab[i][6] = prospect.getTel();
                    tab[i][7] = prospect.getEmail();
                    tab[i][8] = prospect.getDate_prospection();
                    tab[i][9] = prospect.getProspect_interesse();

                }
                String[] colonne = {"id", "Raison sociale", "Num_rue", "Nom_rue", "Ville", "Code postale", "Tel",
                        "Email", "Date de prospection", "Prospect interesse"};
                DefaultTableModel model = new DefaultTableModel(tab, colonne);
                tableaffichage.setModel(model);

            }


        } catch (DaoException de) {
            LoggerExo.LOGGER.severe("pb base de données" + de.getMessage());
            JOptionPane.showMessageDialog(null, de.getMessage());
            JOptionPane.showMessageDialog(null, "l'application va s'arrêter");

            System.exit(1);
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.exit(1);

        }
    }


}


