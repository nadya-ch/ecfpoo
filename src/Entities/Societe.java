package Entities;

import MetierException.MetierException;
import Utilitaire.Utilitaire;

import java.util.regex.Pattern;

public abstract class Societe {
    private int identifiant;
    private String raison_sociale;
    private String num_rue;
    private String nom_rue;
    private String code_postal;
    private String ville;
    private String tel;
    private String email;
    private String commentaire;

    //Constructeur
    public Societe( int identifiant,String raison_sociale, String num_rue, String nom_rue,
                   String code_postal, String ville, String tel, String email, String commentaire) throws MetierException {
        setIdentifiant(identifiant);

        setRaison_sociale(raison_sociale);
        setNum_rue(num_rue);
        setNom_rue(nom_rue);
        setCode_postal(code_postal);
        setVille(ville);
        setTel(tel);
        setEmail(email);
        setCommentaire(commentaire);
    }

    public Societe() {

    }
    //les setters

    public void setIdentifiant(int identifiant) {

        this.identifiant = identifiant;
    }

    public void setRaison_sociale(String raison_sociale) throws MetierException {
        if(raison_sociale==null ||raison_sociale.isEmpty()){
            throw new MetierException("entrez une raison sociale");
        }
        this.raison_sociale = raison_sociale;

    }

    public void setNum_rue(String num_rue) throws MetierException {
        if(num_rue==null||num_rue.isEmpty()){
            throw new MetierException("entrez un num de rue");
        }
        this.num_rue = num_rue;

    }

    public void setNom_rue(String nom_rue) throws MetierException {
        if (nom_rue==null||nom_rue.isEmpty()){
            throw new MetierException("entrez un nom de rue");
        }
        this.nom_rue = nom_rue;
            }
    public void setCode_postal(String code_postal) throws MetierException {
        if (code_postal==null||code_postal.isEmpty()){
            throw new MetierException("entrez un code postal");
        }
        this.code_postal = code_postal;

    }

    public void setVille(String ville) throws MetierException {
        if (ville==null||ville.isEmpty()){
            throw new MetierException("entrez une ville");
        }
        this.ville = ville;

    }

    public void setTel(String tel) throws MetierException {
        if (tel==null||tel.isEmpty()){
            throw new MetierException("rentrez un tel");
        }
        if (tel.length()<=10){
            throw new MetierException("le num de tel est invalide");
        }
        this.tel = tel;

    }

    public void setEmail(String email) throws MetierException{
        if (email==null||email.isEmpty()){
            throw new MetierException("entrez un mail");
        }
        if (!Utilitaire.PATTERN_MAIL.matcher(email).matches()){
            throw new MetierException("format email incorrect");
        }
        this.email = email;

    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    //les getters
    public int getIdentifiant() {
        return identifiant;
    }

    public String getRaison_sociale() {
        return raison_sociale;
    }

    public String getNum_rue() {
        return num_rue;
    }

    public String getNom_rue() {
        return nom_rue;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public String getVille() {
        return ville;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getCommentaire() {
        return commentaire;
    }
    public String toString(){
        return "cilent " + raison_sociale +" "+ nom_rue +" "+ num_rue + code_postal +" "+ ville +" "+ tel +" "+ email
                + " " + commentaire;
    }
}
