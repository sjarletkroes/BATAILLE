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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Describes a basic Game identified by creator's username and games unique name
 */
public class Partie extends Thread {

    public enum EtatPartie {EN_ATTENTE, EN_COURS, FINI};
    private static final int NOMBRE_CARTES = 32;
    
    private Joueur createur;            //creator's username
    private List<Joueur> listeJoueurs;   //list of participants
    private int nbJoueurs;
    private EtatPartie fini;
    private ArrayList<Carte> jeu;
    private Map<Joueur, Integer> score;
    private Map<Joueur, MainJoueur> mainsJoueurs;
    private Joueur joueurCourant;
    private Map<Joueur, Carte> tour;
    
    
    public Partie() {}
    
    /**
     * Crée un nouveau jeu
     *
     * @param createur
     */
    public Partie(Joueur createur)
    {
        this.createur = createur;
        this.fini = EtatPartie.EN_ATTENTE;
        this.listeJoueurs = new ArrayList<>();
        this.listeJoueurs.add(createur);
        this.nbJoueurs = 2;
        this.tour = new HashMap();
        this.mainsJoueurs = new HashMap();
        this.score = new HashMap();
    }
    
    public Partie(Joueur createur, int nbJoueurs)
    {
        this.createur = createur;
        this.fini = EtatPartie.EN_ATTENTE;
        this.listeJoueurs = new ArrayList<>();
        this.listeJoueurs.add(createur);
        this.nbJoueurs = nbJoueurs > 1 ? nbJoueurs : 2;
        this.tour = new HashMap();
        this.mainsJoueurs = new HashMap();
        this.score = new HashMap();
    }

    /**
     * Retourne une carte du jeu au hasard.
     *
     * @return Carte
     */
    public Carte getSuivant() {
        Random r = new Random();
        int x = r.nextInt(this.jeu.size());
        return this.jeu.remove(x);
    }
    
    /**
     * Initialise le jeu de cartes.
     */
    private void initJeu() {
        this.jeu = new ArrayList<>();
        for (int i=0; i<NOMBRE_CARTES/4; i++) {
            for (Carte.Couleur couleur : Carte.Couleur.values()) {
                this.jeu.add(new Carte(i+7, couleur));
            }
        }
        for (Joueur j : this.getListeJoueurs()) {
            score.put(j, 0);
        }
    }
    
    /**
     * Distribue les cartes aux joueurs.
     *
     * @return Map<Joueur, MainJoueur>
     */
    private void initJoueurs() {
        for (Joueur j : this.getListeJoueurs()) {
            mainsJoueurs.put(j, new MainJoueur());
        }
        for (int i=0; i<NOMBRE_CARTES; i++) {
            for (MainJoueur m : mainsJoueurs.values()) {
                if (!this.jeu.isEmpty()) {
                    m.addCarte(this.getSuivant());
                }
            }
        }
        int i = 0;
    }
    
    public void run() {
        this.initJeu();
        this.initJoueurs();
        while(this.fini == EtatPartie.EN_COURS) {
            // on récupère les cartes des joueurs
            for(Joueur j : listeJoueurs) {
                this.joueurCourant = j;
                synchronized(this.joueurCourant) {
                    this.joueurCourant.notifyAll();
                }
                try {
                    synchronized(this.tour) {
                        this.tour.wait();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // on joue le tour
            this.fini = EtatPartie.FINI;
        }
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
        return fini == EtatPartie.FINI;
    }
    
    public void setFini(EtatPartie fini)
    {
        synchronized(this.fini) {
            this.fini = fini;
            this.fini.notifyAll();   
        }
    }

    public List<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    public void setListeJoueurs(List<Joueur> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }
    
    public int getNombreJoueurs()
    {
        return nbJoueurs;
    }

    @Override
    public String toString() {
        String s = "Partie{" + "createur=" + createur + ", listeJoueurs=";
        for(Joueur j : listeJoueurs) {
            s += ", " + j;
        }
        s += ", fini=" + fini + '}';
        return s;
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
        if(!isFini() && !exists) {
            listeJoueurs.add(joueur);
            if(this.nbJoueurs == this.listeJoueurs.size()) {
                synchronized(this.fini) {
                    this.setFini(EtatPartie.EN_COURS);
                    this.fini.notifyAll();
                }
            }
        } else {
            return false;
        }
        return true;
    }

    boolean removePlayer(Joueur joueur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String jouerCarte(Joueur j) {
        while(this.joueurCourant != j) {
        }
        Carte c = this.mainsJoueurs.get(j).getCarte();
        if(c != null) {
            this.tour.put(j, c);
            synchronized(this.tour) {
                this.tour.notify();
            }
            return c.toString();
        } else {
            this.removePlayer(j);
            return "fini";
        }
    }
    
    public boolean estComplete() {
        while(!(fini == EtatPartie.EN_COURS)) {}
        return this.getListeJoueurs().size() == this.getNombreJoueurs();
    }
}
