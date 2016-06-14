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
public interface IJoueur extends Remote {
    String getIdentifiant() throws RemoteException;
    String getMotDePasse() throws RemoteException;
}