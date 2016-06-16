/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import SynchronisationClient.Joueur;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Default remote server interface used by RMI for method access
 * NB : see Server for implementation and comments
 */
public interface RemoteServer extends Remote {
    
    boolean saveJoueur(Joueur joueur) throws RemoteException;
    public boolean addJoueur(Joueur joueur) throws RemoteException;
    Joueur getJoueur(String username, String password) throws RemoteException;
    String getClassement() throws RemoteException;
}
