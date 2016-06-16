/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SynchronisationClient;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import Database.*;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Describes a player defined by a username, password, and connection status
 */
@XmlRootElement(name = "Joueur")
public class Joueur implements Serializable {
    /*
    * Properties
    */
    private String identifiant;         //username
    private String motDePasse;          //password
    private ArrayList<Partie> parties;  //unknown
    private boolean connecte;           //connection status
    private int score;
    
    /*
    * Default constructor
    * NB : track for its presence in the database for implementation errors
    */
    public Joueur() {
        super();
        this.identifiant = "John";
        this.motDePasse = "Doe";
        this.connecte = false;
        this.score = 0;
    }
    
    /*
    * Creates a new Player
    * @param identifiant username
    * @param modDePasse password
    */
    public Joueur(String identifiant, String motDePasse) {
        super();
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.parties = new ArrayList<>();
        this.connecte = false;
        this.score = 0;
    }
    
    /*
    * GETTERS AND SETTERS
    */
    
    public String getIdentifiant()
    {
        return this.identifiant;
    }
    public boolean getConnecte()
    {
        return connecte;
    }
    
    public void setConnecte(boolean connectionStatus)
    {
        connecte = connectionStatus;
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
    
    public ArrayList<Partie> getParties() {
        return parties;
    }

    public void setPartie(Partie partie) {
        this.parties.add(partie);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
}
