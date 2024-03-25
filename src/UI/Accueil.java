package UI;

import Controleur.ControleurAccueil;
import DAO.DaoException;
import Log.LoggerExo;
import Utilitaire.ChoixAction;
import Utilitaire.Choix;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Accueil extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;

    public JButton btnclient;
    public JButton btprospect;
    public JComboBox CBchoix;
    public static String raison_select;

    protected JButton btnaccueil;
    private JButton btncreat;
    private JButton btnmodif;
    private JButton btnsuppression;
    private JButton btnaffichage;
    private JButton btnCB;
    private Choix client_prospect;
    ChoixAction action;


    public Accueil() {
        setContentPane(contentPane);
        //setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        initcomponent();
        Listner();
    }


    public void Listner() {

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

        /**
         * Ajoute un écouteur d'événements à un bouton de création.
         * Lorsque le bouton est cliqué, il déclenche une action de création.
         * Cette action consiste à définir l'action en cours comme une création.
         * Ensuite, le contrôleur de l'accueil est utilisé pour initialiser un formulaire de création avec les paramètres spécifiés.
         * Le formulaire de création est associé au type de client ou de prospect en fonction du choix (client_prospect).
         */

        btncreat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                action = ChoixAction.CREATION;

                ControleurAccueil.formulaire_creation(client_prospect, action);

            }
        });
        /**
         * Ajoute un écouteur d'événements à un bouton de modification.
         * Lorsque le bouton est cliqué, il déclenche une action de modification.
         * Cette action consiste à rendre visible une combobox (CBchoix) et un bouton associé (btnCB).
         * La combobox est ensuite remplie avec les éléments correspondants aux clients ou prospects en fonction du choix (client_prospect).
         * Si une exception DaoException est levée lors de l'accès à la base de données, un message d'erreur est affiché et l'application s'arrête.
         * Si une autre exception est levée, un message d'erreur est affiché et l'application s'arrête.
         */


        btnmodif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action = ChoixAction.MODIFICATION;

                CBchoix.setVisible(true);
                btnCB.setVisible(true);


                try {
                    ArrayList<String> items = ControleurAccueil.remplirCombox(client_prospect);
                    for (String item : items) {
                        CBchoix.addItem(item);

                    }
                    CBchoix.setSelectedIndex(-1);
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
        });
        /**
         * Ajoute un écouteur d'événements au bouton de suppression.
         * Lorsque le bouton est cliqué, plusieurs actions sont effectuées :
         * 1. L'action en cours est définie comme une suppression.
         * 2. La combobox CBchoix et le bouton btnCB deviennent visibles.
         * 3. La combobox est remplie avec des éléments récupérés à partir du contrôleur de l'accueil en fonction du choix client_prospect.
         * 4. L'indice de sélection de la combobox est défini sur -1 pour qu'aucun élément ne soit initialement sélectionné.
         * Si une exception de type DaoException est levée lors de l'accès à la base de données, un message d'erreur est enregistré dans les logs, une boîte de dialogue affiche le message d'erreur, une autre boîte de dialogue informe l'utilisateur que l'application va s'arrêter, puis l'application se termine avec le code d'erreur 1.
         * Si une autre exception est levée, un message d'erreur est affiché dans une boîte de dialogue, puis l'application se termine avec le code d'erreur 1.
         */


        btnsuppression.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action = ChoixAction.SUPPRESSION;
                CBchoix.setVisible(true);
                btnCB.setVisible(true);


                try {
                    ArrayList<String> items = ControleurAccueil.remplirCombox(client_prospect);
                    for (String item : items) {
                        CBchoix.addItem(item);

                    }
                    CBchoix.setSelectedIndex(-1);
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
        });
        /**
         * Ajoute un écouteur d'événements au bouton de confirmation (btnCB).
         * Lorsque le bouton est cliqué, la méthode modif_supp du contrôleur de l'accueil est appelée avec l'action en cours, le choix entre client et prospect, et la raison sociale sélectionnée comme paramètres.
         * Si une exception de type DaoException est levée lors de l'accès à la base de données, un message d'erreur est enregistré dans les logs, une boîte de dialogue affiche le message d'erreur, une autre boîte de dialogue informe l'utilisateur que l'application va s'arrêter, puis l'application se termine avec le code d'erreur 1.
         * Si une autre exception est levée, un message d'erreur est affiché dans une boîte de dialogue, puis l'application se termine avec le code d'erreur 1.
         */

        btnCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ControleurAccueil.modif_supp(action, client_prospect, raison_select);

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
        });
        /**
         * Ajoute un écouteur d'événements à la JComboBox (CBchoix).
         * Lorsqu'un élément est sélectionné dans la liste déroulante, cet écouteur d'événements est déclenché et exécute les actions suivantes :
         * 1. Vérifie si l'élément sélectionné n'est pas null (CBchoix.getSelectedItem() != null).
         * 2. Si l'élément sélectionné n'est pas null, affecte cet élément à la variable raison_select.
         */
        CBchoix.addActionListener(new ActionListener() {


            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CBchoix.getSelectedItem() != CBchoix.getItemAt(-1)) {
                    raison_select = (String) CBchoix.getSelectedItem();
                }

            }
        });
        /**
         * Ajoute un écouteur d'événements au bouton btnclient.
         * Lorsque le bouton est cliqué, cet écouteur d'événements est déclenché et exécute les actions suivantes :
         * 1. Affecte la valeur Choix.CLIENT à la variable client_prospect.
         * 2. Désactive le bouton btnclient en le rendant non cliquable.
         * 3. Cache le bouton btprospect.
         * 4. Rend visible les boutons btncreat, btnmodif, btnsuppression, et btnaffichage.
         */

        btnclient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client_prospect = Choix.CLIENT;

                btnclient.setEnabled(false);
                btprospect.setVisible(false);


                btncreat.setVisible(true);
                btnmodif.setVisible(true);
                btnsuppression.setVisible(true);
                btnaffichage.setVisible(true);


            }
        });
        /**
         * Ajoute un écouteur d'événements au bouton btprospect.
         * Lorsque le bouton est cliqué, cet écouteur d'événements est déclenché et exécute les actions suivantes :
         * 1. Affecte la valeur Choix.PROSPECT à la variable client_prospect.
         * 2. Cache le bouton btnclient.
         * 3. Désactive le bouton btprospect en le rendant non cliquable.
         * 4. Rend visible les boutons btncreat, btnmodif, btnsuppression, et btnaffichage.
         */

        btprospect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client_prospect = Choix.PROSPECT;

                btnclient.setVisible(false);
                btprospect.setEnabled(false);

                btncreat.setVisible(true);
                btnmodif.setVisible(true);
                btnsuppression.setVisible(true);
                btnaffichage.setVisible(true);


            }
        });
        /**
         * Ajoute un écouteur d'événements au bouton btnaccueil.
         * Lorsque le bouton est cliqué, cet écouteur d'événements est déclenché et exécute les actions suivantes :
         * - Initialise l'accueil en appelant la méthode init() de la classe ControleurAccueil.
         */

        btnaccueil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControleurAccueil.init();

            }
        });
        /**
         * Ajoute un écouteur d'événements au bouton btnaffichage.
         * Lorsque le bouton est cliqué, cet écouteur d'événements est déclenché et exécute les actions suivantes :
         * 1. Appelle la méthode onCancel().
         * 2. Tente d'afficher les données des clients ou des prospects en fonction de la valeur de client_prospect en appelant la méthode affichage() de la classe ControleurAccueil.
         * 3. Gère les exceptions DaoException et autres exceptions en affichant un message d'erreur et en arrêtant l'application si une exception se produit.
         */


        btnaffichage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
                try {
                    ControleurAccueil.affichage(client_prospect);
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
        });


    }
    /**
     * Initialise les composants de l'interface utilisateur.
     * Affiche la fenêtre.
     * Active les boutons btnclient et btprospect.
     * Définit la taille de la fenêtre à 500 pixels de largeur et 800 pixels de hauteur.
     * Affiche les boutons btnclient et btprospect.
     * Cache la liste déroulante CBchoix ainsi que les boutons btncreat, btnmodif, btnsuppression, btnaffichage et btnCB.
     */

    public void initcomponent() {
        this.setVisible(true);
        btnclient.setEnabled(true);
        btprospect.setEnabled(true);


        setSize(500, 800);
        btnclient.setVisible(true);
        btprospect.setVisible(true);


        CBchoix.setVisible(false);
        btncreat.setVisible(false);
        btnmodif.setVisible(false);
        btnsuppression.setVisible(false);
        btnaffichage.setVisible(false);
        btnCB.setVisible(false);

    }


    private void onOK() {
        // add your code here
        dispose();
    }


    protected void onCancel() {
        // add your code here if necessary
        dispose();
    }


}
