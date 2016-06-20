/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import SynchronisationClient.RemoteSynchronisationClient;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import SynchronisationClient.SynchronisationClient;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
/**
 * Le client doit pouvoir s’authentifier, créer un compte, récupérer son score, 
 * le classement des joueurs, demander la liste des joueurs connectés, demander 
 * la liste des parties en attente de joueurs.
 */
@Path("/")
public class ServiceJoueur {
    
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
    
    /**
     * authentifier
     */
    @GET
    @Path("authentifier/{identifiant}/{motDePasse}")
    @Produces("text/plain")
    public boolean authentifier(@PathParam("identifiant") String identifiant, 
            @PathParam("motDePasse") String motDePasse) {
        
        try {
            return this.synchronisation.connecterJoueur(identifiant, motDePasse);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * creerCompte
     */
    @GET
    @Path("creerCompte/{identifiant}/{motDePasse}")
//    @Consumes(MediaType.APPLICATION_XML)
    @Produces("text/plain")
    public boolean creerCompte(@PathParam("identifiant") String identifiant, 
            @PathParam("motDePasse") String motDePasse) {
        
        try {
            return this.synchronisation.creerJoueur(identifiant, motDePasse);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * deconnecter
     */
    @GET
    @Path("deconnecter/{identifiant}")
    @Produces("text/plain")
    public boolean deconnecter(@PathParam("identifiant") String identifiant) {
        
        try {
            return this.synchronisation.deconnecterJoueur(identifiant);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * creerPartie
     */
    @GET
    @Path("creerPartie/{identifiant}/{nbJoueurs}")
    @Produces("text/plain")
    public int creerPartie(@PathParam("identifiant") String identifiant,  
            @PathParam("nbJoueurs") int nbJoueurs) {
        
        try {
            return this.synchronisation.creerPartie(identifiant, nbJoueurs);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    /**
     * startPartie
     * @param identifiant
     * @param idPartie
     * @return 
     */
    @GET
    @Path("deconnecterPartie/{identifiant}/{idPartie}")
    @Produces("text/plain")
    public boolean deconnecterPartie(@PathParam("identifiant") String identifiant,
            @PathParam("idPartie") int idPartie) {
        
        try {
            return this.synchronisation.deconnecterJoueurPartie(identifiant, idPartie);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * donnerScore
     */
    @GET
    @Path("donnerScore/{identifiant}")
    @Produces("text/plain")
    public int donnerScore(@PathParam("identifiant") String identifiant) {
        
        try {
            return this.synchronisation.getScore(identifiant);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    /**
     * donnerClassement
     */
    @GET
    @Path("donnerClassement/{identifiant}")
    @Produces("text/plain")
    public String donnerClassement(@PathParam("identifiant") String identifiant) {
        
        try {
            return this.synchronisation.getClassement(identifiant);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    /**
     * donnerListeConnectes
     */
    @GET
    @Path("donnerListeConnectes/{identifiant}")
    @Produces("text/plain")
    public String donnerListeConnectes(@PathParam("identifiant") String identifiant) {
        
        try {
            return this.synchronisation.listerJoueurs(identifiant);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    /**
     * donnerListePartiesAttente
     */
    @GET
    @Path("donnerListePartiesAttente/{identifiant}")
    @Produces("text/plain")
    public String donnerListePartiesAttente(@PathParam("identifiant") String identifiant) {
        
        try {
            return this.synchronisation.listerParties(identifiant);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    /**
     * donnerListePartiesAttente
     */
    @GET
    @Path("rejoindrePartie/{identifiant}/{idPartie}")
    @Produces("text/plain")
    public boolean rejoindrePartie(@PathParam("identifiant") String identifiant, 
            @PathParam("idPartie") int idPartie) {
        
        try {
            return this.synchronisation.ajouterJoueurPartie(identifiant, idPartie);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * donnerListePartiesAttente
     */
    @GET
    @Path("jouerCarte/{identifiant}/{idPartie}")
    @Produces("text/plain")
    public String jouerCarte(@PathParam("identifiant") String identifiant, 
            @PathParam("idPartie") int idPartie) {
        
        try {
            return this.synchronisation.jouerCarte(identifiant, idPartie);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    @GET
    @Path("estComplete/{identifiant}/{idPartie}")
    @Produces("text/plain")
    public boolean estComplete(@PathParam("identifiant") String identifiant,
            @PathParam("idPartie") int idPartie) {
        
        try {
            return this.synchronisation.estComplete(identifiant, idPartie);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
   
    }
    
}
