/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joueurs;

import parties.Partie;
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
    
    /*
    * Default constructor
    * NB : track for its presence in the database for implementation errors
    */
    public Joueur() {
        super();
        this.identifiant = "John";
        this.motDePasse = "Doe";
        connecte = false;
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
        connecte = false;
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
}
