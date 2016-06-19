/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SynchronisationClient;

import Database.RemoteServer;
import SynchronisationClient.Partie.EtatPartie;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Goldwing
 */
public class SynchronisationClient extends Thread {
    
    private LinkedList<Joueur> joueurs;
    private HashMap<Integer, Partie> parties;

    /**
     *
     */
    public SynchronisationClient() {
        this.joueurs = joueurs = new LinkedList();
        this.parties = parties = new HashMap();
    }
    
    /**
     * Crée un nouveau Joueur. Crée le joueur, l'enregistre dans le tier 3 puis
     * appelle connecterJoueur.
     *
     * @param identifiant
     * @param motDePasse
     * @return
     */
    public boolean creerJoueur(String identifiant, String motDePasse) {
        
        boolean rep = false;
        Joueur joueur  = new Joueur(identifiant,motDePasse);
        
        if(!joueurs.contains(joueur)) {
            
            Registry registry;
            try {
                registry = LocateRegistry.getRegistry(1099);
            } catch (RemoteException ex) {
                System.out.println("Server error #1");
                return false;
            }
            try {
                RemoteServer stub = (RemoteServer) registry.lookup("RemoteServer");
                rep = stub.addJoueur(joueur);
            } catch (RemoteException ex) {
                System.out.println(ex.getMessage());
                return false; 
            } catch (NotBoundException ex) {
                System.out.println("Not found, critical error: " + ex.getMessage());
                return false;
            }
        
            joueur.setConnecte(true);
            joueurs.add(joueur);
        }
        return rep;
    }

    /**
     * Connecte le joueur. Si le joueur est enregistré dans le tier 3, il est
     * ajouté à la liste des joueurs connectés.
     *
     * @param identifiant
     * @param motDePasse
     * @return
     */
    public boolean connecterJoueur(String identifiant, String motDePasse){
        
        boolean rep = false;
        Joueur joueur;
            
            Registry registry;
            try {
                registry = LocateRegistry.getRegistry(1099);
            } catch (RemoteException ex) {
                System.out.println("Server error #1");
                return false;
            }
            try {
                RemoteServer stub = (RemoteServer) registry.lookup("RemoteServer");
                joueur = stub.getJoueur(identifiant, motDePasse);
            } catch (RemoteException ex) {
                System.out.println(ex.getMessage());
                return false; 
            } catch (NotBoundException ex) {
                System.out.println("Not found, critical error: " + ex.getMessage());
                return false;
            }
            
        if(joueur != null) {
            joueur.setConnecte(true);
            joueurs.add(joueur);
            rep = true;
        }
        return rep;
    }
    
    /**
     * Déconnecte le joueur.
     *
     * @param identifiant
     * @return
     */
    public boolean deconnecterJoueur(String identifiant){
        
        boolean rep = false;
        for(Joueur joueur : joueurs) {
            if(joueur.getIdentifiant().equals(identifiant)) {
                joueur.setConnecte(false);
                
                Registry registry;
                try {
                    registry = LocateRegistry.getRegistry(1099);
                } catch (RemoteException ex) {
                    System.out.println("Server error #1");
                    return false;
                }
                try {
                    RemoteServer stub = (RemoteServer) registry.lookup("RemoteServer");
                    rep = stub.saveJoueur(joueur);
                } catch (RemoteException ex) {
                    System.out.println(ex.getMessage());
                    return false; 
                } catch (NotBoundException ex) {
                    System.out.println("Not found, critical error: " + ex.getMessage());
                    return false;
                }
                
                joueurs.remove(joueur);
                return rep;
            }
        }
        return rep;
    }

    /**
     * Crée la partie. Crée une nouvelle partie sur le tier 2 et retourne le
     * numéro de la partie.
     *
     * @param identifiant
     * @param nbJoueurs
     * @return
     */
    public int creerPartie(String identifiant, int nbJoueurs) {
        
        for(Joueur joueur : joueurs) {
            if(joueur.getIdentifiant().equals(identifiant)) {
                for(int i=0; i<100; i++) {
                    if(!parties.containsKey(i)) {
                        parties.put(i, new Partie(joueur, nbJoueurs));
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    /**
     * Ajoute le joueur à la partie. Ajoute le joueur à la partie idPartie.
     *
     * @param identifiant
     * @param idPartie
     * @return
     */
    public boolean ajouterJoueurPartie(String identifiant, int idPartie) {
        for(Joueur joueur : joueurs) {
            if(joueur.getIdentifiant().equals(identifiant)) {
                if(parties.containsKey(idPartie)) {
                    Partie p = parties.get(idPartie);
                    boolean ajout = p.addPlayer(joueur);
                    if(p.estComplete()) {
                        p.setFini(EtatPartie.EN_COURS);
                        p.start();
                    }
                    return ajout;
                }
            }
        }
        return false;
    }
    
    /**
     * déconnecte le joueur de la partie. Déconnecte le joueur de la partie et
     * notifie la partie de sa déconnexion.
     *
     * @param identifiant
     * @return
     */
    public boolean deconnecterJoueurPartie(String identifiant, int idPartie) {
        for(Joueur joueur : joueurs) {
            if(joueur.getIdentifiant().equals(identifiant)) {
                if(parties.containsKey(idPartie)) {
                    return parties.get(idPartie).removePlayer(joueur);
                }
            }
        }
        return false;
    }

    /**
     * Retourne le score du joueur.
     *
     * @param identifiant
     * @return
     */
    public int getScore(String identifiant) {
        for(Joueur j : this.joueurs) {
            if(j.getIdentifiant().equals(identifiant)) {
                return j.getScore();
            }
        }
        return -1;
    }

    /**
     * Retourne le classement des joueurs.
     *
     * @param identifiant
     * @return
     */
    public String getClassement(String identifiant) {
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry(1099);
        } catch (RemoteException ex) {
            return "Server error #1";
        }
        try {
            RemoteServer stub = (RemoteServer) registry.lookup("RemoteServer");
            return stub.getClassement();
        } catch (RemoteException ex) {
            return ex.getMessage();
        } catch (NotBoundException ex) {
            return "Not found, critical error: " + ex.getMessage();
        }
    }

    /**
     * Renvoie les joueurs connectés.
     *
     * @param identifiant
     * @return
     */
    public String listerJoueurs(String identifiant) {
        StringBuilder sb = new StringBuilder();
        for(Joueur j : joueurs) {
            sb.append(String.format("%s\n", j.getIdentifiant()));
        }
        return sb.toString();
    }

    /**
     * Renvoie les parties en attente de joueurs.
     *
     * @param identifiant
     * @return
     */
    public String listerParties(String identifiant) {
        StringBuilder sb = new StringBuilder();
        for(Entry<Integer, Partie> p : parties.entrySet()) {
            sb.append(String.format("%s : %s\n", p.getKey(), p.getValue().toString()));
        }
        return sb.toString();
    }

    public String jouerCarte(String identifiant, int idPartie) {
        Joueur j = this.getJoueur(identifiant);
        Partie p = this.getPartie(idPartie);
        if(j != null && p != null) {
            return p.jouerCarte(j);
        }
        return null;
    }
    
    private Joueur getJoueur(String identifiant) {
        for(Joueur j : this.joueurs) {
            if(j.getIdentifiant().equals(identifiant)) {
                return j;
            }
        }
        return null;
    }
    
    private Partie getPartie(int idPartie) {
        if (this.parties.containsKey(idPartie)) {
            return this.parties.get(idPartie);
        }
        return null;
    }

    public boolean estComplete(String identifiant, int idPartie) {
        return this.getPartie(idPartie).estComplete();
    }
    
}
