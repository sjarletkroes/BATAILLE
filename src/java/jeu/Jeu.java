/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import joueurs.Joueur;
import parties.Partie;

/**
 * 
 */
public class Jeu {
    private static final int NOMBRE_CARTES = 32;
    
    private Partie partie;
    private ArrayList<Carte> jeu;
    Map<Joueur, Integer> score;

    public Jeu(Partie partie) {
        this.partie = partie;
        this.initJeu();
    }

    /**
     * Retourne une carte au hasard.
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
        for (Joueur j : this.partie.getListeJoueurs()) {
            score.put(j, 0);
        }
    }
    
    /**
     * Distribue les cartes aux joueurs.
     *
     * @return Map<Joueur, MainJoueur>
     */
    private ArrayList<MainJoueur> initJoueurs() {
        ArrayList<MainJoueur> liste = new ArrayList<>();
        for (Joueur j : this.partie.getListeJoueurs()) {
            MainJoueur main = new MainJoueur(j);
            liste.add(main);
        }
        for (int i=0; i<NOMBRE_CARTES; i++) {
            for (MainJoueur m : liste) {
                if (!this.jeu.isEmpty()) {
                    m.addCarte(this.getSuivant());
                }
            }
        }
        return liste;
    }
    
    /**
     * Fait jouer les joueurs. Renvoie le gagnant
     * 
     * @param liste
     * @return 
     */
    public Joueur actionJeu(ArrayList<MainJoueur> liste) {
        while (!liste.isEmpty()) {
            Joueur meilleur = null;
            Carte max = new Carte(0, null);
            for (MainJoueur m : liste) {
                
                // S'il ne reste plus de cartes dans la main de m il est éliminé
                if (m.isEmpty()) {
                    // ajouter un point à tous les autres
                    this.score.replace(m.getJoueur(), this.score.get(m.getJoueur())+1);
                    // et supprimer le joueur
                    liste.remove(m);
                    
                // sinon m joue
                } else {
                    Carte carte = m.getCarte();
                    Joueur joueur = m.getJoueur();
                    
                    // Si la carte est meilleure
                    if (carte.getValeur() > max.getValeur()) {
                        carte = max;
                        meilleur = joueur;
                        
                    // Si les cartes sont identiques
                    } else if (carte.getValeur() == max.getValeur()) {
                        // TODO
                    }
                }
            }
        }
        this.partie.isFini();
        return null;
    }
    
    /*
     ** --- main ---
     */
    public static void main(String args[]) {
        Joueur joueur1 = new Joueur("j1", "j1");
        Joueur joueur2 = new Joueur("j2", "j2");
        Joueur joueur3 = new Joueur("j3", "j3");
        Joueur joueur4 = new Joueur("j4", "j4");
        Joueur joueur5 = new Joueur("j5", "j5");
        joueur1.setConnecte(true);
        joueur2.setConnecte(true);
        joueur3.setConnecte(true);
        joueur4.setConnecte(true);
        joueur5.setConnecte(true);
        Partie partie = new Partie(joueur1, joueur2, joueur3, joueur4, joueur5);
        Jeu jeu = new Jeu(partie);
        
        // Distribution des cartes
        ArrayList<MainJoueur> liste = jeu.initJoueurs();
        
        // Jeu
        jeu.actionJeu(liste);
    }
}
