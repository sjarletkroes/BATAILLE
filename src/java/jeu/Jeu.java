/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.util.ArrayList;
import joueurs.Joueur;
import parties.Partie;

/**
 * 
 */
public class Jeu {
    private static final int NOMBRE_CARTES = 32;
    
    private Partie partie;
    private ArrayList<Carte> jeu;

    public Jeu(Partie partie) {
        this.partie = partie;
        this.initJeu();
    }
    
    private void initJeu() {
        this.jeu = new ArrayList<>();
        for (int i=0; i<NOMBRE_CARTES/4; i++) {
            for (Carte.Couleur couleur : Carte.Couleur.values()) {
                this.jeu.add(new Carte(i+7, couleur));
            }
        }
    }
    
    private void initJoueurs() {
        
    }
    
    public void actionJeu() {
        
    }
    
    /*
     ** --- main ---
     */
    public static void main(String args[]) {
        Joueur joueur1 = new Joueur("j1", "j1");
        Joueur joueur2 = new Joueur("j2", "j2");
        joueur1.setConnecte(true);
        joueur2.setConnecte(true);
        Partie partie = new Partie(joueur1);
        partie.addPlayer(joueur2);
        Jeu jeu = new Jeu(partie);
    }
}
