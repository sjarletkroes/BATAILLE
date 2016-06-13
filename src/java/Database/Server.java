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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jeu.Partie;
import joueurs.Joueur;

/**
 *
 * @author Anton
 */
public class Server implements RemoteServer {
    public Server() {}
    
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public boolean Login(String username, String password) throws RemoteException {
        for(int i = 0; i < Players.size(); ++i)
        {
            if(Players.get(i).getNom().equals(username) && Players.get(i).getMotDePasse().equals(password))
                return true;
        }
        return false;
    }

    @Override
    public boolean AddPlayer(String username, String password) throws RemoteException {
        Joueur joueur = new Joueur();
        joueur.setMotDePasse(password);
        Players.add(joueur);
        return true;
    }
    
    List<Joueur> Players;
    List<Partie> Gamesets;

    @Override
    public List<Partie> GetGamesets() throws RemoteException {
        return Gamesets;
    }
    
}
