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
import SynchronisationClient.Partie;
import SynchronisationClient.Joueur;
import java.util.Collections;
import java.util.LinkedList;

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
    
    /**
     * Sauvegarde le joueur. Persiste joueur s'il n'existe pas de joueur du
     * même identifiant.
     *
     * @param joueur
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean saveJoueur(Joueur joueur) throws RemoteException {
        for(Joueur j : joueurs) {
            if(j.getIdentifiant().equals(joueur.getIdentifiant())) {
                joueurs.remove(j);
                return joueurs.add(joueur);
            }
        }
        return false;
    }

    @Override
    public boolean addJoueur(Joueur joueur) throws RemoteException {
        for(Joueur j : joueurs) {
            if(j.getIdentifiant().equals(joueur.getIdentifiant())) {
                return false;
            }
        }
        return joueurs.add(joueur);
    }

    @Override
    public Joueur getJoueur(String username, String password) throws RemoteException {
        for(Joueur j : joueurs) {
            if(j.getIdentifiant().equals(username) && j.getMotDePasse().equals(password)) {
                return j;
            }
        }
        return null;
    }

    @Override
    public String getClassement() throws RemoteException {
        StringBuilder sb = new StringBuilder();

        LinkedList<Joueur> liste = new LinkedList();
        liste.addAll(joueurs);
        Collections.sort(liste, (Joueur tc1, Joueur tc2) -> 
                ((Integer) tc1.getScore()).compareTo((Integer) tc2.getScore()));
        
        for(Joueur j : liste) {
            sb.append(String.format("%s : %s\n", j.getIdentifiant(), j.getScore()));
        }
        return sb.toString();
    }

}
