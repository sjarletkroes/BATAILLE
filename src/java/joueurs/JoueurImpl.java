/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joueurs;

import jeu.Partie;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author me
 */
@XmlRootElement(name = "JoueurImpl")
public class JoueurImpl extends Thread implements Joueur  {
    
    private String nom;
    private String prenom;
    private String identifiant;
    private String motDePasse;
    private ArrayList<Partie> parties;

    public JoueurImpl() {
    }

    public JoueurImpl(String nom, String prenom, String identifiant, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.parties = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return "Joueur{" + "nom=" + nom + ", prenom=" + prenom + ", identifiant=" + identifiant + ", motDePasse=" + motDePasse + '}';
    }

    public ArrayList<Partie> getParties() {
        return parties;
    }

    public void setPartie(Partie partie) {
        this.parties.add(partie);
    }
}
