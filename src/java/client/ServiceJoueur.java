/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import joueurs.Joueur;
import joueurs.Joueurs;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Database.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jeu.Partie;
/**
 * Le client doit pouvoir s’authentifier, créer un compte, récupérer son score, 
 * le classement des joueurs, demander la liste des joueurs connectés, demander 
 * la liste des parties en attente de joueurs.
 *
 * @author me
 */
@Path("/")
public class ServiceJoueur {
    
    private static final Joueurs joueurs;
    
    static {
        joueurs = new Joueurs();
        //joueurs.liste.add(new Joueur("Nom", "Prenom", "Identifiant", "Mot De Passe"));
    }
    
    /**
     * listerJoueurs
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Joueurs listerJoueurs() {
        return joueurs;
    }
    
    /**
     * authentifier
     */
    @GET
    @Path("authentifier/{identifiant}/{motDePasse}")
    @Produces("text/plain")
    public String authentifier(@PathParam("identifiant") String identifiant, 
            @PathParam("motDePasse") String motDePasse) {
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry(1099);
        } catch (RemoteException ex) {
            return "Server error #1";
        }
        try {
            RemoteServer stub = (RemoteServer) registry.lookup("RemoteServer");
            if(stub.Login(identifiant, motDePasse))
                return "OK";
            else
                return "Identifiants incorrects";
        } catch (RemoteException ex) {
            return ex.getMessage();
        } catch (NotBoundException ex) {
            return "Incorrect username or password";
        }
    }
    
    /**
     * creerCompte
     */
    @PUT
    @Path("creerCompte")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces("text/plain")
    public String creerCompte(JAXBElement<Joueur> c) {
        Joueur monJoueur = c.getValue();
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry(1099);
        } catch (RemoteException ex) {
            return "Server error #1";
        }
        try {
            RemoteServer stub = (RemoteServer) registry.lookup("RemoteServer");
            
            if(!stub.AddPlayer(monJoueur.getIdentifiant(),monJoueur.getMotDePasse()))
            {
                return "Error";
            }
            else
                return "OK";
        } catch (RemoteException ex) {
            return ex.getMessage();
        } catch (NotBoundException ex) {
            return "Game not found critical error";
        }
    }
    
    /**
     * Creates a new game bound to the player
     */
    @PUT
    @Path("creerPartie")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces("text/plain")
    public String creerPartie(JAXBElement<Joueur> c) {
        Joueur monJoueur = c.getValue();
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry(1099);
        } catch (RemoteException ex) {
            return "Server error #1";
        }
        try {
            RemoteServer stub = (RemoteServer) registry.lookup("RemoteServer");
            
            if(!stub.CreateGame(monJoueur.getIdentifiant(), monJoueur.getMotDePasse()))
            {
                return "Error";
            }
            else
                return "OK";
        } catch (RemoteException ex) {
            return ex.getMessage();
        } catch (NotBoundException ex) {
            return "Game not found critical error";
        }
    }
    
    /**
     * donnerScore
     */
    @GET
    @Path("donnerScore/{identifiant}")
    @Produces("text/plain")
    public String donnerScore(@PathParam("identifiant") String identifiant) {
        return "Donner score";
    }
    
    /**
     * donnerClassement
     */
    @GET
    @Path("donnerClassement/{identifiant}")
    @Produces("text/plain")
    public String donnerClassement(@PathParam("identifiant") String identifiant) {
        return "Donner classement";
    }
    
    /**
     * donnerListeConnectes
     */
    @GET
    @Path("donnerListeConnectes/{identifiant}")
    @Produces("text/plain")
    public String donnerListeConnectes(@PathParam("identifiant") String identifiant) {
        return "Donner liste joueurs connectés";
    }
    
    /**
     * donnerListePartiesAttente
     */
    @GET
    @Path("donnerListePartiesAttente/{identifiant}/{motDePasse}")
    @Produces("text/plain")
    public String donnerListePartiesAttente(@PathParam("identifiant") String identifiant, 
            @PathParam("motDePasse") String motDePasse) {
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry(1099);
        } catch (RemoteException ex) {
            return "Server error #1";
        }
        try {
            RemoteServer stub = (RemoteServer) registry.lookup("RemoteServer");
            List<Partie> games = stub.ListCurrentGames(identifiant, motDePasse);
            
            if(games != null)
                return Integer.toString(games.size());
            else
                return "Identifiants incorrects";
        } catch (RemoteException ex) {
            return ex.getMessage();
        } catch (NotBoundException ex) {
            return "Incorrect username or password";
        }
    }
    
}
