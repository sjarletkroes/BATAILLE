/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.util.ArrayList;
import SynchronisationClient.Joueur;

/**
 * 
 */
public class MainJoueur {
    
    private Joueur joueur;
    private ArrayList<Carte> main;

    public MainJoueur(Joueur joueur) {
        this.joueur = joueur;
        this.main = new ArrayList<>();
    }

    public MainJoueur(Joueur joueur, ArrayList<Carte> main) {
        this.joueur = joueur;
        this.main = main;
    }
    
    /*
    * GETTERS AND SETTERS
    */

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public ArrayList<Carte> getMain() {
        return main;
    }

    public void setMain(ArrayList<Carte> main) {
        this.main = main;
    }
    
    public Carte getCarte() {
        if (!this.isEmpty()) {
            return main.remove(0);
        }
        return null;
    }

    public void addCarte (Carte carte) {
        this.main.add(carte);
    }
    
    public boolean isEmpty() {
        return main.isEmpty();
    }

    @Override
    public String toString() {
        return "MainJoueur{" + "joueur=" + joueur + ", main=" + main.get(0) + '}';
    }
}
