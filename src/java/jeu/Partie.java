/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.xml.bind.annotation.XmlRootElement;
import joueurs.Joueur;

/**
 * Describes a basic Game identified by creator's username and games unique name
 */
@XmlRootElement(name = "Partie")
public class Partie {
    private String Username;            //creator's username
    private List<Joueur> PlayersList;   //list of participants
    private boolean Ended;
    
    public Partie(String username)
    {
        Username = username;
        Ended = false;
        PlayersList = new ArrayList<Joueur>();
    }
    
    /*
    * GETTERS AND SETTERS
    */
    
    public String getUsername()
    {
        return Username;
    }
    
    public void setUsername(String username)
    {
        Username = username;
    }
    
    public boolean getEnded() 
    {
        return Ended;
    }
    
    private void setEnded(boolean ended)    //only accessed by the class if conditions meet
    {
        Ended = ended;
    }
    
    /**
     * Add a player to the players list if he doesnt exist there and the game isnt ended
     * @param player Player to add
     */
    public boolean addPlayer(Joueur player)
    {
        boolean exists = false;
        ListIterator<Joueur> it = PlayersList.listIterator();
        while(it.hasNext() && !exists)
        {
            if(it.next().getIdentifiant().equals(player.getIdentifiant()))
                exists = true;
        }
        if(!getEnded() && !exists)
            PlayersList.add(player);
        else
            return false;
        return true;
    }
}
