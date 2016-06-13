import java.io.StringReader;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import joueurs.JoueurImpl;

public class ClientBataille {

    private static WebTarget serviceJoueur = null;
    
    /**
     * authentifier
     */
    public String authentifier(String identifiant, String motDePasse) {
        return serviceJoueur.path("authentifier/" + identifiant + motDePasse)
                .request().get(String.class);
    }
    
    /**
     * creerCompte
     */
    public String creerCompte(JoueurImpl joueur) {
        return serviceJoueur.path("authentifier").request(MediaType.TEXT_PLAIN)
                .put(Entity.xml(joueur)).readEntity(String.class);
    }
    
    /**
     * donnerScore
     */
    public String donnerScore(String identifiant) {
        return serviceJoueur.path("donnerScore/" + identifiant)
                .request().get(String.class);
    }
    
    /**
     * donnerClassement
     */
    public String donnerClassement(String identifiant) {
        return serviceJoueur.path("donnerClassement/" + identifiant)
                .request().get(String.class);
    }
    
    /**
     * donnerListeConnectes
     */
    public String donnerListeConnectes(String identifiant) {
        return serviceJoueur.path("donnerListeConnectes/" + identifiant)
                .request().get(String.class);
    }
    
    /**
     * donnerListePartiesAttente
     */
    public String donnerListePartiesAttente() {
        return serviceJoueur.path("donnerListePartiesAttente/")
                .request().get(String.class);
    }
    
    /*
     ** --- main ---
     */
    public static void main(String args[]) throws Exception {
        
    }
}
