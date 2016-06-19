/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import SynchronisationClient.SynchronisationClient;
/**
 * Le client doit pouvoir s’authentifier, créer un compte, récupérer son score, 
 * le classement des joueurs, demander la liste des joueurs connectés, demander 
 * la liste des parties en attente de joueurs.
 */
@Path("/")
public class ServiceJoueur {
    
    private static final SynchronisationClient synchronisation;
    
    static {
        synchronisation = new SynchronisationClient();
    }
    
    /**
     * authentifier
     */
    @GET
    @Path("authentifier/{identifiant}/{motDePasse}")
    @Produces("text/plain")
    public boolean authentifier(@PathParam("identifiant") String identifiant, 
            @PathParam("motDePasse") String motDePasse) {
        
        return this.synchronisation.connecterJoueur(identifiant, motDePasse);
        
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
        
        return this.synchronisation.creerJoueur(identifiant, motDePasse);
        
    }
    
    /**
     * deconnecter
     */
    @GET
    @Path("deconnecter/{identifiant}")
    @Produces("text/plain")
    public boolean deconnecter(@PathParam("identifiant") String identifiant) {
        
        return this.synchronisation.deconnecterJoueur(identifiant);
        
    }
    
    /**
     * creerPartie
     */
    @GET
    @Path("creerPartie/{identifiant}/{nbJoueurs}")
    @Produces("text/plain")
    public int creerPartie(@PathParam("identifiant") String identifiant,  
            @PathParam("nbJoueurs") int nbJoueurs) {
        
        return this.synchronisation.creerPartie(identifiant, nbJoueurs);
        
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
        
        return this.synchronisation.deconnecterJoueurPartie(identifiant, idPartie);
   
    }
    
    /**
     * donnerScore
     */
    @GET
    @Path("donnerScore/{identifiant}")
    @Produces("text/plain")
    public int donnerScore(@PathParam("identifiant") String identifiant) {
        
        return this.synchronisation.getScore(identifiant);
        
    }
    
    /**
     * donnerClassement
     */
    @GET
    @Path("donnerClassement/{identifiant}")
    @Produces("text/plain")
    public String donnerClassement(@PathParam("identifiant") String identifiant) {
        
        return this.synchronisation.getClassement(identifiant);
        
    }
    
    /**
     * donnerListeConnectes
     */
    @GET
    @Path("donnerListeConnectes/{identifiant}")
    @Produces("text/plain")
    public String donnerListeConnectes(@PathParam("identifiant") String identifiant) {
        
        return this.synchronisation.listerJoueurs(identifiant);
        
    }
    
    /**
     * donnerListePartiesAttente
     */
    @GET
    @Path("donnerListePartiesAttente/{identifiant}")
    @Produces("text/plain")
    public String donnerListePartiesAttente(@PathParam("identifiant") String identifiant) {
        
        return this.synchronisation.listerParties(identifiant);
        
    }
    
    /**
     * donnerListePartiesAttente
     */
    @GET
    @Path("rejoindrePartie/{identifiant}/{idPartie}")
    @Produces("text/plain")
    public boolean rejoindrePartie(@PathParam("identifiant") String identifiant, 
            @PathParam("idPartie") int idPartie) {
        
        return this.synchronisation.ajouterJoueurPartie(identifiant, idPartie);
        
    }
    
    /**
     * donnerListePartiesAttente
     */
    @GET
    @Path("jouerCarte/{identifiant}/{idPartie}")
    @Produces("text/plain")
    public String jouerCarte(@PathParam("identifiant") String identifiant, 
            @PathParam("idPartie") int idPartie) {
        
        return this.synchronisation.jouerCarte(identifiant, idPartie);
        
    }
    
    @GET
    @Path("estComplete/{identifiant}/{idPartie}")
    @Produces("text/plain")
    public boolean estComplete(@PathParam("identifiant") String identifiant,
            @PathParam("idPartie") int idPartie) {
        
        return this.synchronisation.estComplete(identifiant, idPartie);
   
    }
    
}
