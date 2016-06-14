/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

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

/**
 * 
 */
public class Server implements RemoteServer {
    
    /*
    *Properties
    */
    private List<Joueur> joueurs;   //List of all players
    private List<Partie> parties;  //List of current and old games
    
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
    
    /*
    Private class to check if the user is in the players list
    *@param username Users username
    *@param password Users password
    *@return true if the user has been found
    */
    private Joueur Authentify(String username, String password)
    {
        ListIterator <Joueur> it = joueurs.listIterator();
        while(it.hasNext())
        {
            Joueur j = it.next();
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
    public Joueur Login(String username, String password) throws RemoteException {
        return Authentify(username,password);
    }
    /*
    Add a player to the players list on the server
    *@param username Users username
    *@param password Users password
    *@return false if the username is taken, else true
    */
    @Override
    public boolean AddPlayer(String username, String password) throws RemoteException {
        Joueur joueur = new Joueur(username,password);
        boolean exist = false;

        ListIterator <Joueur> it = joueurs.listIterator();
        while(it.hasNext() && !exist)
            exist = it.next().getIdentifiant().equals(username);
        if(exist)
            return false;
        joueurs.add(joueur);
        
        return true;
    }
    /*
    *Create a new game identified by the creators username and the game's name
    *@param username Users username
    *@param password Users password
    *@return false if a game with a similar name already exists or if the users doesnt exist
    */
    @Override
    public boolean CreateGame(String username, String password) throws RemoteException {
        Joueur j = Authentify(username,password);
        if(j == null)
            return false;
        parties.add(new Partie(j));
        return true;
    }
    /*
    * List non ended games
    *@param username User's username
    *@param password User's password
    */
    @Override
    public List<Partie> ListCurrentGames(String username, String password) throws RemoteException {
        if(Authentify(username,password) == null)
            return null;
        List<Partie> currentGames = new ArrayList<Partie>();
        ListIterator <Partie> it = parties.listIterator();
        while(it.hasNext())
        {
            Partie p = it.next();
            if(!p.getFini())
                currentGames.add(p);
        }
        return currentGames;
    }
    
}
