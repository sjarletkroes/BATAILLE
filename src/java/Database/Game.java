/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.rmi.RemoteException;
import java.util.List;
import jeu.Partie;
import joueurs.Joueur;

/**
 *
 * @author Anton
 */
public class Game implements IGame {

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
