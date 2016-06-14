/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import javax.xml.bind.annotation.XmlRootElement;
import joueurs.Joueur;

/**
 * Describes a basic Game identified by creator's username and games unique name
 */
@XmlRootElement(name = "Partie")
public class Partie {
    private Joueur createur;            //creator's username
    private List<Joueur> listeJoueurs;   //list of participants
    private boolean fini;
    private Joueur gagnant;
    
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
    
    public Partie(Joueur createur, Joueur... joueur)
    {
        this.createur = createur;
        this.fini = false;
        this.gagnant = null;
        this.listeJoueurs = new ArrayList<>();
        this.listeJoueurs.add(createur);
        this.listeJoueurs.addAll(Arrays.asList(joueur));
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
}
