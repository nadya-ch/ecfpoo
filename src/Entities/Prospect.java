package Entities;

import MetierException.MetierException;

import java.time.LocalDate;

public class Prospect extends Societe{

    private LocalDate date_prospection ;
    private String prospect_interesse;



    public Prospect( int identifiant,String raison_sociale, String num_rue, String nom_rue, String code_postal,
                    String ville, String tel, String mail, String commentaire, LocalDate date_prospection,
                    String prospect_interesse) throws MetierException {
        super( identifiant,raison_sociale, num_rue, nom_rue, code_postal, ville, tel, mail, commentaire);
        setDate_prospection(date_prospection);
        setProspect_interesse(prospect_interesse);
    }

    public Prospect() {
    }
    //les setters
    public void setDate_prospection(LocalDate date_prospection) throws MetierException {

        if (date_prospection==null){
            throw new MetierException("entrez une date ");
        }
        else{this.date_prospection=date_prospection;}


    }

    public void setProspect_interesse(String prospect_interesse) throws MetierException {
        if (prospect_interesse==null||prospect_interesse.isEmpty()){
            throw new MetierException("entrer un reponse");
        }
        this.prospect_interesse = prospect_interesse;
    }


    public String getProspect_interesse() {
        return prospect_interesse;
    }

    public LocalDate getDate_prospection() {


        return date_prospection;
    }

    public String toString(){
        return "Prospect [ "  +getIdentifiant() +"\t"+  getRaison_sociale()+"\t" + getNum_rue()+"\t" +
                getNom_rue() +"\t" + getCode_postal()+"\t" + getVille()+"\t" + getEmail() +"\t"+ getTel()
                +"\t"+ date_prospection +"\t"+prospect_interesse+"\t"+ "]";
    }
}
