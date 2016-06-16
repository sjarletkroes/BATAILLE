/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SynchronisationClient;

import Database.IJoueur;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import javax.xml.bind.annotation.XmlRootElement;
import SynchronisationClient.Joueur;

/**
 * Describes a basic Game identified by creator's username and games unique name
 */
@XmlRootElement(name = "Partie")
public class Partie implements Serializable {
    private Joueur createur;            //creator's username
    private List<Joueur> listeJoueurs;   //list of participants
    private int nbJoueurs;
    private boolean fini;
    private Joueur gagnant;
    public Partie() {}
    /**
     * Crée un nouveau jeu
     *
     * @param createur
     */
    public Partie(Joueur createur)
    {
        this.createur = createur;
        this.fini = false;
        this.gagnant = null;
        this.listeJoueurs = new ArrayList<>();
        this.listeJoueurs.add(createur);
    }
    
    public Partie(Joueur createur, int nbJoueurs, Joueur... joueur)
    {
        this.createur = createur;
        this.fini = false;
        this.gagnant = null;
        this.listeJoueurs = new ArrayList<>();
        this.listeJoueurs.add(createur);
        this.listeJoueurs.addAll(Arrays.asList(joueur));
        this.nbJoueurs = nbJoueurs > 1 ? nbJoueurs : 2;
    }
    
    /*
    * GETTERS AND SETTERS
    */
    
    public Joueur getCreateur()
    {
        return createur;
    }
    
    public void setCreateur(Joueur createur)
    {
        this.createur = createur;
    }
    
    public boolean isFini() 
    {
        return fini;
    }
    
    private void setFini(boolean fini)    //only accessed by the class if conditions meet
    {
        this.fini = fini;
    }

    public Joueur getGagnant() {
        return gagnant;
    }

    public void setGagnant(Joueur gagnant) {
        this.gagnant = gagnant;
    }

    public List<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    public void setListeJoueurs(List<Joueur> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }
    
    public int getNombreJoueurs()
    {
        return listeJoueurs.size();
    }

    @Override
    public String toString() {
        return "Partie{" + "createur=" + createur + ", listeJoueurs=" + listeJoueurs + ", fini=" + fini + ", gagnant=" + gagnant + '}';
    }
    
    
    /**
     * Add a joueur to the players list if he doesnt exist there and the game isnt ended
     * @param joueur Joueur to add
     */
    public boolean addPlayer(Joueur joueur)
    {
        boolean exists = false;
        ListIterator<Joueur> it = listeJoueurs.listIterator();
        while(it.hasNext() && !exists)
        {
            if(it.next().getIdentifiant().equals(joueur.getIdentifiant()))
                exists = true;
        }
        if(!isFini() && !exists)
            listeJoueurs.add(joueur);
        else
            return false;
        return true;
    }

    boolean removePlayer(Joueur joueur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}