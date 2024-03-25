package UI;

import Controleur.ControleurFormulaire;
import DAO.DaoException;
import Entities.Client;
import Entities.Prospect;
import Entities.Societe;
import Log.LoggerExo;
import MetierException.MetierException;
import Utilitaire.*;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.logging.Level;

import static Utilitaire.ChoixAction.*;


public class Formulaire extends JDialog {
    private JPanel contentPane;
    protected JButton buttonOK;

    protected JTextField txtraison;
    protected JLabel lblraison;
    protected JTextField txtnumrue;
    protected JLabel lblnumrue;
    protected JLabel lblNomrue;
    protected JTextField txtNomru;
    protected JLabel lblcodepost;
    protected JTextField txtcodeposta;
    protected JLabel btnville;
    protected JTextField txtville;
    protected JTextField txttel;
    protected JLabel lbltel;
    protected JTextField txtemail;
    protected JLabel lblemail;
    protected JLabel lblcomme;
    protected JTextField txtcomm;
    protected JButton btnvalider;
    protected JButton pageDAccueilButton;
    protected JLabel lblidentif;
    protected JTextField txtident;
    protected JTextField txtchiffr_datepros;
    protected JTextField txtnbemploy_prospectinteress;
    protected JLabel Lbltitre;
    protected JLabel lblchiffre_dateprospec;
    protected JLabel lblnbemplo_prospinterr;


    private ChoixAction actionchoisi;
    private Choix client_prospect;
    String raison_sociale;

    /**
     * Initialise les composants du formulaire.
     * Définit le bouton par défaut sur le bouton de validation.
     * Définit le comportement de la fenêtre lors de la fermeture.
     * Gère l'action lorsque la touche ESCAPE est enfoncée.
     * Associe les actions aux boutons du formulaire.
     * Effectue les opérations de création, modification ou suppression en fonction de l'action choisie.
     * Affiche des messages d'erreur appropriés en cas de problème lors de la saisie des données, d'erreurs métier, d'erreurs de base de données ou d'exceptions générales.
     * Affiche un message de confirmation avant de supprimer des données.
     *
     */

    public Formulaire() {
        setContentPane(contentPane);
        //setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pageDAccueilButton.addActionListener(e -> {
            ControleurFormulaire.retourPageAccueil();
            onCancel();

        });


        btnvalider.addActionListener(e -> {
            try {


                Societe societe = null;

                String raison_socilae = null;
                String numrue;
                String nomrue;
                String ville;
                String email;
                String code_postale;
                String tel;
                String comment;

                switch (actionchoisi) {
                    case CREATION:


                        raison_socilae = txtraison.getText();
                        numrue = txtnumrue.getText();
                        nomrue = txtNomru.getText();
                        ville = txtville.getText();
                        email = txtemail.getText();
                        code_postale = txtcodeposta.getText();
                        tel = txttel.getText();
                        comment = txtcomm.getText();


                        if (client_prospect == Choix.CLIENT) {

                            Client client = new Client(0, raison_socilae, numrue, nomrue, code_postale, ville,
                                    tel, email, comment, Double.parseDouble(txtchiffr_datepros.getText()), Integer.parseInt(txtnbemploy_prospectinteress.getText()));
                            ControleurFormulaire.creation(client);
                            dispose();
                        } else {
                            LocalDate date_prosp = LocalDate.parse(txtchiffr_datepros.getText(),
                                    Utilitaire.DATE_TIME_FORMATTER);
                            String prospe_interr = txtnbemploy_prospectinteress.getText();


                            Prospect prospect = new Prospect(0, raison_socilae, numrue, nomrue, code_postale, ville, tel,
                                    email, comment, date_prosp, prospe_interr);
                            ControleurFormulaire.creation(prospect);
                        }
                        JOptionPane.showMessageDialog(null, "Création réussie");
                        dispose();


                    break;

                    case MODIFICATION:

                        raison_socilae = txtraison.getText();
                        numrue = txtnumrue.getText();
                        nomrue = txtNomru.getText();
                        ville = txtville.getText();
                        email = txtemail.getText();
                        code_postale = txtcodeposta.getText();
                        tel = txttel.getText();
                        comment = txtcomm.getText();


                        if (client_prospect == Choix.CLIENT) {

                            Client client = new Client(Integer.parseInt(txtident.getText()), raison_socilae, numrue, nomrue, code_postale, ville,
                                    tel, email, comment, Double.parseDouble(txtchiffr_datepros.getText()),
                                    Integer.parseInt(txtnbemploy_prospectinteress.getText()));
                            ControleurFormulaire.modification(client);

                        } else {

                            LocalDate date_prosp = LocalDate.parse(txtchiffr_datepros.getText(),
                                    Utilitaire.DATE_TIME_FORMATTER);
                            String prospe_interr = txtnbemploy_prospectinteress.getText();


                            Prospect prospect = new Prospect(0, raison_socilae, numrue, nomrue, code_postale, ville, tel,
                                    email, comment, date_prosp, prospe_interr);
                            ControleurFormulaire.modification(prospect);
                        }JOptionPane.showMessageDialog(null, "vous venez de modifer un "+client_prospect);
                        dispose();
                    break;


                    case SUPPRESSION:


                       int dialogButton = JOptionPane.showConfirmDialog(null, "êtes vous sûr de couloir supprimer ce "+client_prospect +"?",
                                "WARNING", JOptionPane.YES_NO_OPTION);

                       if (dialogButton == JOptionPane.YES_OPTION) {
                           ControleurFormulaire.delete(client_prospect, raison_sociale);

                           JOptionPane.showMessageDialog(null,"suppression réussite");
                           dispose();
                           ControleurFormulaire.retourPageAccueil();
                      }
                   break;
                }

            } catch (NumberFormatException ne) {
                JOptionPane.showMessageDialog(null, "entrez un nombre valide");
            } catch (DateTimeException de) {
                JOptionPane.showMessageDialog(null, "entrez la date sous fourmat jj/mm/aaaa");

            } catch (MetierException x) {
                JOptionPane.showMessageDialog(null, x.getMessage());

            } catch (DaoException de) {
                if (de.getGravity() != 5) {

                    JOptionPane.showMessageDialog(null, "erreur de saisie" + de.getMessage());
                } else {
                    LoggerExo.LOGGER.severe("pb de base de données" + de.getMessage());
                    JOptionPane.showMessageDialog(null, "l'application va s'arrêter");

                    System.exit(1);
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "erreur au niveau de base de données : " + ex.getMessage());
                System.exit(1);
            } catch (Exception ee) {
                LoggerExo.LOGGER.log(Level.SEVERE, ee.getMessage());
                JOptionPane.showMessageDialog(null, ee.getMessage());
                System.exit(1);

            }
        });

    }
    /**
     * Initialise les composants du formulaire en fonction du choix de l'utilisateur et de l'action sélectionnée.
     * Affiche la fenêtre du formulaire.
     * Ajuste la taille de la fenêtre à 500x800 pixels.
     * Modifie le titre du formulaire en fonction du choix de gestion (client ou prospect).
     * Cache les champs d'identification s'ils ne sont pas nécessaires pour l'action en cours.
     * Ajuste les libellés en fonction du choix de gestion et de l'action sélectionnée.
     *
     * @param client_prospect Le choix de gestion (CLIENT ou PROSPECT).
     * @param action L'action sélectionnée (CREATION, MODIFICATION ou SUPPRESSION).
     */

    public void initcomponent(Choix client_prospect, ChoixAction action) {
        this.client_prospect = client_prospect;
        this.actionchoisi = action;

        setVisible(true);
        setSize(500, 800);
        Lbltitre.setText("gestion de " + client_prospect);
        lblidentif.setVisible(false);

        txtident.setVisible(false);
        try {

            if (client_prospect == Choix.CLIENT) {
                lblchiffre_dateprospec.setText("chiffre d'affaires");
                lblnbemplo_prospinterr.setText("nombre d'employés");


            } else {
                lblchiffre_dateprospec.setText("date de prospection");
                lblnbemplo_prospinterr.setText("prospect interessé");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


    private void onCancel() {
        // add your code here if necessary

        dispose();
    }

    /**
     * Initialise les composants du formulaire en fonction de l'action et du choix de gestion spécifiés,
     * puis affiche la fenêtre du formulaire.
     *
     * @param action           L'action à effectuer (CREATION, MODIFICATION ou SUPPRESSION).
     * @param client_prospect  Le choix de gestion (CLIENT ou PROSPECT).
     * @param raisonsociale    La raison sociale du client ou prospect à modifier ou supprimer.
     */
    public void initcomponent(ChoixAction action, Choix client_prospect, String raisonsociale) {
        this.raison_sociale =raisonsociale;

            this.actionchoisi = action;
            this.client_prospect = client_prospect;
            setVisible(true);
            setSize(500, 800);
            Lbltitre.setText("gestion de " + client_prospect);
            lblidentif.setVisible(false);

            txtident.setVisible(false);

            Societe s = null;
            try {
                s = ControleurFormulaire.modif_client_prospect(client_prospect, raisonsociale);


                txtident.setText(String.valueOf(s.getIdentifiant()));
                txtraison.setText(s.getRaison_sociale());
                txtnumrue.setText(s.getNum_rue());
                txtNomru.setText(s.getNom_rue());
                txtville.setText(s.getVille());
                txtcodeposta.setText(s.getCode_postal());
                txttel.setText(s.getTel());
                txtemail.setText(s.getEmail());

                if (s instanceof Client) {
                    lblchiffre_dateprospec.setText("chiffre d'affaires");
                    lblnbemplo_prospinterr.setText("nombre d'employés");

                    txtchiffr_datepros.setText("" + ((Client) s).getChiffre_affaires());
                    txtnbemploy_prospectinteress.setText("" + ((Client) s).getNb_employes());
                } else {
                    lblchiffre_dateprospec.setText("date de prospection");
                    lblnbemplo_prospinterr.setText("prospect interessé");
                    //s = ControleurFormulaire.modif_client_prospect(client_prospect,raisonsociale);

                    txtchiffr_datepros.setText("" + ((Prospect) s).getDate_prospection());
                    txtnbemploy_prospectinteress.setText(((Prospect) s).getProspect_interesse());

                }if (action==SUPPRESSION){desactiver();}

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "");
            }

    }
    /**
     * Désactive les composants du formulaire pour empêcher toute modification des données.
     * Les composants concernés sont la zone de texte pour l'identifiant, ainsi que tous les champs de saisie.
     */

    public void desactiver(){
        lblidentif.setVisible(true);
        txtident.setVisible(true);
        txtident.setEnabled(false);
        txtraison.setEnabled(false);
        txtnumrue.setEnabled(false);
        txtNomru.setEnabled(false);
        txtville.setEnabled(false);
        txtcodeposta.setEnabled(false);
        txttel.setEnabled(false);
        txtemail.setEnabled(false);
        txtchiffr_datepros.setEnabled(false);
        txtnbemploy_prospectinteress.setEnabled(false);
    }



}
