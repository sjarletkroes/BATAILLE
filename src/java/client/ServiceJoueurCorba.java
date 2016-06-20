/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import SynchronisationClient.*;
import Corba.JoueurActionPOA;
import Database.RemoteServer;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author Goldwing
 */
public class ServiceJoueurCorba extends JoueurActionPOA{

    private static final SynchronisationClient synchronisation;
    
    static {
        synchronisation = new SynchronisationClient();
    }

    @Override
    public boolean creerJoueur(String identifiant, String motDePasse) {
        boolean rep = this.synchronisation.creerJoueur(identifiant, motDePasse);
        return rep;
    }

    @Override
    public boolean connecterJoueur(String identifiant, String motDePasse) {
        boolean rep = this.synchronisation.connecterJoueur(identifiant, motDePasse);
        return rep;
    }

    @Override
    public boolean deconnecterJoueur(String identifiant) {
        boolean rep = this.synchronisation.deconnecterJoueur(identifiant);
        return rep;
    }

    @Override
    public int getScore(String identifiant) {
        int rep = this.synchronisation.getScore(identifiant);
        return rep;
    }

    @Override
    public String listerJoueurs(String identifiant) {
        String rep = this.synchronisation.listerJoueurs(identifiant);
        return rep;
    }

    @Override
    public String getClassement(String identifiant) {
        String rep = this.synchronisation.getClassement(identifiant);
        return rep;
    }

    @Override
    public int creerPartie(String identifiant, int nbJoueurs) {
        int rep = this.synchronisation.creerPartie(identifiant,nbJoueurs);
        return rep;
    }

    @Override
    public boolean ajouterJoueurPartie(String identifiant, int idPartie) {
        boolean rep = this.synchronisation.ajouterJoueurPartie(identifiant,idPartie);
        return rep;
    }

    @Override
    public boolean disconnectToPartie(String identifiant, int idPartie) {
        boolean rep = this.synchronisation.deconnecterJoueurPartie(identifiant,idPartie);
        return rep;
    }

    @Override
    public String listerParties(String identifiant) {
        String rep = this.synchronisation.listerParties(identifiant);
        return rep;
    }

    @Override
    public synchronized boolean nbJoueurComplet(int idPartie) {
        boolean rep = this.synchronisation.nbJoueurComplet(idPartie);
        return rep;
    }

    @Override
    public synchronized String comencerJeu(int idPartie) {
        String rep = this.synchronisation.comencerJeu(idPartie);
        return rep;
    }

    
}
