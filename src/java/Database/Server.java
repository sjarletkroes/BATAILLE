/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import parties.Partie;
import joueurs.Joueur;
import joueurs.Joueurs;
import parties.Parties;

/**
 * 
 */
public class Server implements RemoteServer {
    
    /*
    *Properties
    */
    private List<Joueur> joueurs;
    private List<Partie> parties;
    /*
    *Instanciate a server
    */
    public Server() {
        joueurs = new ArrayList<Joueur>();
        parties = new ArrayList<Partie>();
    }
    /*
    *Main entry
    *Creates an rmi registry if doest exist and registers to it as a Remote
    */
    public static void main(String args[]) {
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(1099);
        } catch (RemoteException ex) {
            try {
                registry = LocateRegistry.getRegistry();
            } catch (RemoteException ex1) {
                ex1.printStackTrace();
            }
        }
        try {
            Server obj = new Server();
            RemoteServer stub = (RemoteServer) UnicastRemoteObject.exportObject(obj, 0);
            registry.bind("RemoteServer", stub);
            System.err.println("Server ready");
        } 
        catch (java.rmi.AlreadyBoundException e)
        {
            //its okay
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    private Registry GetSafeRegistry()
    {
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(1099);
        } catch (RemoteException ex) {
            try {
                registry = LocateRegistry.getRegistry();
            } catch (RemoteException ex1) {
                return null;
            }
        }
        return registry;
    }
    /*
    Private class to check if the user is in the players list
    *@param username Users username
    *@param password Users password
    *@return true if the user has been found
    */
    private Joueur Authentify(String username, String password) throws RemoteException
    {
         for(Joueur j : joueurs)
        {
            if(j.getIdentifiant().equals(username) && j.getMotDePasse().equals(password))
                return j;
        }
         return null;
    }
    /*
    Checks if the user is in the players list
    *@param username Users username
    *@param password Users password
    *@return true if the user has been found
    */
    @Override
    public boolean Login(String username, String password) throws RemoteException {
        Joueur j = Authentify(username,password);
        if(j == null)
            return false;
        else
            j.setConnecte(true);
        return true;
    }
    /*
    Add a player to the players list on the server
    *@param username Users username
    *@param password Users password
    *@return false if the username is taken, else true
    */
    @Override
    public boolean AddPlayer(String username, String password) throws RemoteException {

        for(Joueur j : joueurs)
        {
            if(j.getIdentifiant().equals(username) && j.getMotDePasse().equals(password))
                return false;
        }
        Joueur j = new Joueur(username,password);
        joueurs.add(j);
        return Login(username, password);
    }
    /*
    *Create a new game identified by the creators username and the game's name
    *@param username Users username
    *@param password Users password
    *@return false if a game with a similar name already exists or if the users doesnt exist
    */
    @Override
    public boolean CreateGame(String username, String password) throws RemoteException {
        
        for(Partie p : parties)
        {
            if(p.getCreateur().getIdentifiant().equals(username))
                return false;
        }
        Joueur j = new Joueur(username,password);
        parties.add(new Partie(j));
        return true;
    }
    /*
    * Lister toutes les parties
    *@param username User's username
    *@param password User's password
    *@return Liste des parties
    */
    @Override
    public Parties ListCurrentGames(String username, String password) throws RemoteException, AccessException {
        Parties p = new Parties();
        p.liste.addAll(parties);
        return p;
    }

    @Override
    public void Disconnect(String username, String password) throws RemoteException {
        for(Joueur j : joueurs)
        {
            if(j.getIdentifiant().equals(username) && j.getMotDePasse().equals(password))
                j.setConnecte(false);
        }
    }

    @Override
    public Parties RejoindrePartie(String partie, String username, String password) throws RemoteException {
        Joueur j = Authentify(username,password);
        if(j == null)
            return null;
        Parties part = new Parties();
        for(Partie p : parties)
        {
            if(p.getCreateur().getIdentifiant().equals(partie))
            {
                part.liste.add(p);
                return part;
            }
                
        }
        return null;
    }

    @Override
    public Joueurs ListJoueursConnectes(String username, String password) throws RemoteException {
        if(Authentify(username,password) == null)
            return null;
        Joueurs jou = new Joueurs();
        for(Joueur j : joueurs)
        {
            if(j.getConnecte())
                jou.liste.add(j);
        }
        return jou;
    }
    
}
