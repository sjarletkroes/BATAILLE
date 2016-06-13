import java.io.StringReader;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import joueurs.JoueurImpl;
import joueurs.Joueurs;

public class ClientBataille {

    private static WebTarget serviceJoueur = null;
    
    /**
     * listerJoueurs
     */
    public static Joueurs listerJoueurs() throws JAXBException {
        String reponse;
        StringBuffer xmlStr;
        JAXBContext context;
        JAXBElement<Joueurs> root;
        Unmarshaller unmarshaller;

        /*
         ** Instanciation du convertiseur XML => Objet Java
         */
        context = JAXBContext.newInstance(Joueurs.class);
        unmarshaller = context.createUnmarshaller();

        /*
         ** Envoi de la requete
         */
        reponse = serviceJoueur.request().get(String.class);

        /*
         ** Traitement de la reponse XML : transformation en une instance de la classe Villes
         */
        xmlStr = new StringBuffer(reponse);
        root = unmarshaller.unmarshal(new StreamSource(new StringReader(xmlStr.toString())), Joueurs.class);

        return root.getValue();
    }
    
    /**
     * authentifier
     */
    public static String authentifier(String identifiant, String motDePasse) {
        return serviceJoueur.path("authentifier/" + identifiant + "/" + motDePasse)
                .request().get(String.class);
    }
    
    /**
     * creerCompte
     */
    public static String creerCompte(JoueurImpl joueur) {
        return serviceJoueur.path("creerCompte/").request(MediaType.TEXT_PLAIN)
                .put(Entity.xml(joueur)).readEntity(String.class);
    }
    
    /**
     * donnerScore
     */
    public static String donnerScore(String identifiant) {
        return serviceJoueur.path("donnerScore/" + identifiant)
                .request().get(String.class);
    }
    
    /**
     * donnerClassement
     */
    public static String donnerClassement(String identifiant) {
        return serviceJoueur.path("donnerClassement/" + identifiant)
                .request().get(String.class);
    }
    
    /**
     * donnerListeConnectes
     */
    public static String donnerListeConnectes(String identifiant) {
        return serviceJoueur.path("donnerListeConnectes/" + identifiant)
                .request().get(String.class);
    }
    
    /**
     * donnerListePartiesAttente
     */
    public static String donnerListePartiesAttente(String identifiant) {
        return serviceJoueur.path("donnerListePartiesAttente/" + identifiant)
                .request().get(String.class);
    }
    
    /*
     ** --- main ---
     */
    public static void main(String args[]) throws Exception {
        /*
         ** Initialisation du stub pour interagir avec le service web REST
         */
        serviceJoueur = ClientBuilder.newClient().target("http://localhost:8080/BATAILLE");
        
        String identifiant = "stalker474";
        String motDePasse = "stalker";
        JoueurImpl joueur = new JoueurImpl("Possylkine", "Anton", identifiant, motDePasse);
        
        System.out.println(authentifier(identifiant, motDePasse));
        System.out.println(creerCompte(joueur));
        System.out.println(donnerScore(identifiant));
        System.out.println(donnerClassement(identifiant));
        System.out.println(donnerListeConnectes(identifiant));
        System.out.println(donnerListeConnectes(identifiant));
        System.out.println(donnerListePartiesAttente(identifiant));
    }
}
