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
    
    private ArrayList<Carte> main;

    public MainJoueur() {
        this.main = new ArrayList<>();
    }

    public MainJoueur(ArrayList<Carte> main) {
        this.main.addAll(main);
    }
    
    /*
    * GETTERS AND SETTERS
    */

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
        String s = "MainJoueur{" + "main=";
        for(Carte c : this.main) {
            s += ", " + c;
        }
        s += '}';
        return s;
    }
}
