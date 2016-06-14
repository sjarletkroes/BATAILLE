/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 */
public interface IPartie extends Remote  {
    String getPlayers() throws RemoteException;
    int    getPlayerScore(IJoueur joueur) throws RemoteException;
}
