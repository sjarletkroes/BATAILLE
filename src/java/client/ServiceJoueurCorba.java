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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Goldwing
 */
public class ServiceJoueurCorba extends JoueurActionPOA{

    static RemoteSynchronisationClient synchronisation;
    
    static {
        Registry reg;
        try {
            reg = LocateRegistry.getRegistry();
            
            synchronisation = (RemoteSynchronisationClient)reg.lookup("Synchro");
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean creerJoueur(String identifiant, String motDePasse) {
        try {
            boolean rep = this.synchronisation.creerJoueur(identifiant, motDePasse);
            return rep;
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueurCorba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean connecterJoueur(String identifiant, String motDePasse) {
        try {
            boolean rep = this.synchronisation.connecterJoueur(identifiant, motDePasse);
            return rep;
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueurCorba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deconnecterJoueur(String identifiant) {
        try {
            boolean rep = this.synchronisation.deconnecterJoueur(identifiant);
            return rep;
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueurCorba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public int getScore(String identifiant) {
        int rep = -1;
        try {
            rep = this.synchronisation.getScore(identifiant);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueurCorba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }

    @Override
    public String listerJoueurs(String identifiant) {
        String rep = "";
        try {
            rep = this.synchronisation.listerJoueurs(identifiant);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueurCorba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }

    @Override
    public String getClassement(String identifiant) {
        String rep = "";
        try {
            rep = this.synchronisation.getClassement(identifiant);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueurCorba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }

    @Override
    public int creerPartie(String identifiant, int nbJoueurs) {
        int rep = -1;
        try {
            rep = this.synchronisation.creerPartie(identifiant,nbJoueurs);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueurCorba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }

    @Override
    public boolean ajouterJoueurPartie(String identifiant, int idPartie) {
        boolean rep = false;
        try {
            rep = this.synchronisation.ajouterJoueurPartie(identifiant,idPartie);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueurCorba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }

    @Override
    public boolean disconnectToPartie(String identifiant, int idPartie) {
        boolean rep = false;
        try {
            rep = this.synchronisation.deconnecterJoueurPartie(identifiant,idPartie);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueurCorba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }

    @Override
    public String listerParties(String identifiant) {
        String rep = "";
        try {
            rep = this.synchronisation.listerParties(identifiant);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueurCorba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }

    @Override
    public synchronized boolean nbJoueurComplet(int idPartie) {
        boolean rep = false;
        try {
            rep = this.synchronisation.nbJoueurComplet(idPartie);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueurCorba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }

    @Override
    public synchronized String comencerJeu(int idPartie) {
        String rep = "";
        try {
            rep = this.synchronisation.comencerJeu(idPartie);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueurCorba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }

    
}
