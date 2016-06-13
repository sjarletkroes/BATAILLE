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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import joueurs.JoueurImpl;

/**
 * Le client doit pouvoir s’authentifier, créer un compte, récupérer son score, 
 * le classement des joueurs, demander la liste des joueurs connectés, demander 
 * la liste des parties en attente de joueurs.
 *
 * @author me
 */
public class ServiceJoueur {
    
    /**
     * authentifier
     */
    @GET
    @Path("authentifier")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String authentifier() {
        return "Authentification";
    }
    
    /**
     * creerCompte
     */
    @PUT
    @Path("creerCompte")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces("text/plain")
    public String creerCompte(JAXBElement<JoueurImpl> c) {
        return "Création compte";
    }
    
    /**
     * donnerScore
     */
    @GET
    @Path("donnerScore")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String donnerScore() {
        return "Donner score";
    }
    
    /**
     * donnerClassement
     */
    @GET
    @Path("donnerClassement")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String donnerClassement() {
        return "Donner classement";
    }
    
    /**
     * donnerListeConnectes
     */
    @GET
    @Path("donnerListeConnectes")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String donnerListeConnectes() {
        return "Donner liste joueurs connectés";
    }
    
    /**
     * donnerListePartiesAttente
     */
    @GET
    @Path("donnerListePartiesAttente")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String donnerListePartiesAttente() {
        return "Donner liste parties attente";
    }
    
}
