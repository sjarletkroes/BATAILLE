/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

/**
 * 
 */
public class Carte {
    public enum Couleur {CARREAU, COEUR, PIQUE, TREFLE};
    
    private int valeur;
    private Couleur couleur;

    public Carte() {
    }

    public Carte(int valeur, Couleur couleur) {
        this.valeur = valeur;
        this.couleur = couleur;
    }
    
    /*
    * GETTERS AND SETTERS
    */

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return "Carte{" + "valeur=" + valeur + ", couleur=" + couleur + '}';
    }
}
