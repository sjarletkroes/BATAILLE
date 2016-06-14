/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.rmi.Remote;
import java.rmi.RemoteException;
import joueurs.Joueurs;
import parties.Parties;

/**
 * Default remote server interface used by RMI for method access
 * NB : see Server for implementation and comments
 */
public interface RemoteServer extends Remote {
    boolean         Login(String username, String password) throws RemoteException;
    boolean         AddPlayer(String username, String password) throws RemoteException;
    boolean         CreateGame(String username, String password) throws RemoteException;
    Parties    ListCurrentGames(String username, String password) throws RemoteException;
    Parties    RejoindrePartie(String partie,String username,String password) throws RemoteException;
    void            Disconnect(String username, String password) throws RemoteException;
    Joueurs    ListJoueursConnectes(String username, String password) throws RemoteException;
}
