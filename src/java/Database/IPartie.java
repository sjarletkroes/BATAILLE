/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import joueurs.Joueur;

/**
 * 
 */
public interface IPartie extends Remote  {
    List<String> getJoueurs() throws RemoteException;
    int    getJoueurScore(IJoueur joueur) throws RemoteException;
    String getNomCreateur() throws RemoteException;;
    int    getNombreJoueurs() throws RemoteException;;
}
