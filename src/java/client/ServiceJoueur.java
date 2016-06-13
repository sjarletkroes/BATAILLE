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
import joueurs.JoueurImpl;
import joueurs.Joueurs;

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
        joueurs.liste.add(new JoueurImpl("Nom", "Prenom", "Identifiant", "Mot De Passe"));
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
    @Path("authentifier/{identifiant}{motDePasse}")
    @Produces("text/plain")
    public String authentifier(@PathParam("identifiant") String identifiant, 
            @PathParam("motDePasse") String motDePasse) {
        return "Authentification";
    }
    
    /**
     * creerCompte
     */
    @PUT
    @Path("creerCompte")
    @Produces("text/plain")
    public String creerCompte(JAXBElement<JoueurImpl> c) {
        return "Création compte";
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
    @Path("donnerListePartiesAttente/{identifiant}")
    @Produces("text/plain")
    public String donnerListePartiesAttente(@PathParam("identifiant") String identifiant) {
        return "Donner liste parties attente";
    }
    
}