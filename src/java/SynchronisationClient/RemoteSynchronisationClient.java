/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SynchronisationClient;

import Database.RemoteServer;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Anton
 */
public interface RemoteSynchronisationClient extends Remote {
    
    public boolean creerJoueur(String identifiant, String motDePasse) throws RemoteException;

    /**
     * Connecte le joueur. Si le joueur est enregistré dans le tier 3, il est
     * ajouté à la liste des joueurs connectés.
     *
     * @param identifiant
     * @param motDePasse
     * @return
     */
    public boolean connecterJoueur(String identifiant, String motDePasse) throws RemoteException;
    
    /**
     * Déconnecte le joueur.
     *
     * @param identifiant
     * @return
     */
    public boolean deconnecterJoueur(String identifiant) throws RemoteException;
    /**
     * Crée la partie. Crée une nouvelle partie sur le tier 2 et retourne le
     * numéro de la partie.
     *
     * @param identifiant
     * @param nbJoueurs
     * @return
     */
    public int creerPartie(String identifiant, int nbJoueurs) throws RemoteException;
    
    /**
     * Ajoute le joueur à la partie. Ajoute le joueur à la partie idPartie.
     *
     * @param identifiant
     * @param idPartie
     * @return
     */
    public boolean ajouterJoueurPartie(String identifiant, int idPartie) throws RemoteException;
    
    /**
     * déconnecte le joueur de la partie. Déconnecte le joueur de la partie et
     * notifie la partie de sa déconnexion.
     *
     * @param identifiant
     * @return
     */
    public boolean deconnecterJoueurPartie(String identifiant, int idPartie) throws RemoteException;

    /**
     * Retourne le score du joueur.
     *
     * @param identifiant
     * @return
     */
    public int getScore(String identifiant)  throws RemoteException;

    /**
     * Retourne le classement des joueurs.
     *
     * @param identifiant
     * @return
     */
    public String getClassement(String identifiant) throws RemoteException;

    /**
     * Renvoie les joueurs connectés.
     *
     * @param identifiant
     * @return
     */
    public String listerJoueurs(String identifiant)  throws RemoteException;

    /**
     * Renvoie les parties en attente de joueurs.
     *
     * @param identifiant
     * @return
     */
    public String listerParties(String identifiant)  throws RemoteException;

    public String jouerCarte(String identifiant, int idPartie) throws RemoteException;
    
    public boolean estComplete(String identifiant, int idPartie) throws RemoteException;
    
    public boolean nbJoueurComplet(int idPartie) throws RemoteException;
    
    public String comencerJeu(int idPartie)  throws RemoteException;
    
}
