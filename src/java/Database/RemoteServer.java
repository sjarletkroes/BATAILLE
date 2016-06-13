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
 *
 * @author Anton
 */
public interface RemoteServer extends Remote {
    boolean         Login(String username, String password) throws RemoteException;
    boolean         AddPlayer(String username, String password) throws RemoteException;
    List<Partie>    GetGamesets() throws RemoteException;
}
