/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import jeu.Partie;

/**
 * Default remote server interface used by RMI for method access
 * NB : see Server for implementation and comments
 */
public interface RemoteServer extends Remote {
    boolean         Login(String username, String password) throws RemoteException;
    boolean         AddPlayer(String username, String password) throws RemoteException;
    boolean         CreateGame(String username, String password) throws RemoteException;
    List<Partie>    ListCurrentGames(String username, String password) throws RemoteException;
}
