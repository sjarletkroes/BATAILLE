/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joueurs;

import jeu.Partie;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import Database.*;

/**
 *
 * @author me
 */
@XmlRootElement(name = "Joueur")
public class Joueur implements IJoueur  {
    
    private String nom;
    private String identifiant;
    private String motDePasse;
    private ArrayList<Partie> parties;

    public Joueur() {
        super();
    }

    public Joueur(String nom, String identifiant, String motDePasse) {
        super();
        this.nom = nom;
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
        return "Joueur{" + "nom=" + nom + ", identifiant=" + identifiant + ", motDePasse=" + motDePasse + '}';
    }

    public ArrayList<Partie> getParties() {
        return parties;
    }

    public void setPartie(Partie partie) {
        this.parties.add(partie);
    }
}
