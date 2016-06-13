import java.io.StringReader;
import java.util.Scanner;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import joueurs.*;

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
    public static String creerCompte(Joueur joueur) {
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
        
        /*String identifiant = "stalker474";
        String motDePasse = "stalker";
        JoueurImpl joueur = new JoueurImpl("Possylkine", "Anton", identifiant, motDePasse);
        
        System.out.println(authentifier(identifiant, motDePasse));
        System.out.println(creerCompte(joueur));
        System.out.println(donnerScore(identifiant));
        System.out.println(donnerClassement(identifiant));
        System.out.println(donnerListeConnectes(identifiant));
        System.out.println(donnerListePartiesAttente(identifiant));*/
        
        Scanner sc = new Scanner(System.in);
        Joueur joueur = null;
        
        System.out.println("#####################################");
        System.out.println("# Bienvenue sur le jeu de bataille! #");
        System.out.println("#####################################");
        System.out.print("Pour vous identifier taper 1, pour créer un compte taper 2: ");
        
        int input;
        boolean mauvaiseReponse = true;
        while (mauvaiseReponse) {
            input = sc.nextInt();
            if (input == 1) {
                sc = new Scanner(System.in);
                System.out.print("Entrez votre identifiant: ");
                String identifiant = sc.nextLine();
                System.out.print("Entrez votre mot de passe: ");
                String motDePasse = sc.nextLine();
                System.out.println(authentifier(identifiant, motDePasse));
                mauvaiseReponse = false;
                
            } else if (input == 2) {
                sc = new Scanner(System.in);
                System.out.print("Entrez votre nom: ");
                String nom = sc.nextLine();
                System.out.print("Entrez votre identifiant: ");
                String identifiant = sc.nextLine();
                System.out.print("Entrez votre mot de passe: ");
                String motDePasse = sc.nextLine();
                joueur = new Joueur(nom, identifiant, motDePasse);
                System.out.println(creerCompte(joueur));
                mauvaiseReponse = false;
                
            } else {
                System.out.println("Désolé, je n'ai pas compris, veuillez réessayer: ");
            }
        }
        
        while (joueur != null) {
            System.out.println("Vous souhaitez: ");
            System.out.println("    récupérer votre score, taper 1");
            System.out.println("    récupérer votre classement, taper 2");
            System.out.println("    recupérer la liste des joueurs connectés, taper 3");
            System.out.println("    récupérer la liste des parties en attente, taper 4");
            System.out.println("    vous déconnecter, taper n'importe quel autre caractère");
            System.out.print("C'est à vous: ");

            input = sc.nextInt();
            switch (input) {
                case 1: 
                    System.out.println("#####################################");
                    System.out.println(donnerScore(joueur.getIdentifiant()));
                    System.out.println("#####################################");
                    break;
                case 2: 
                    System.out.println("#####################################");
                    System.out.println(donnerClassement(joueur.getIdentifiant()));
                    System.out.println("#####################################");
                    break;
                case 3: 
                    System.out.println("#####################################");
                    System.out.println(donnerListeConnectes(joueur.getIdentifiant()));
                    System.out.println("#####################################");
                    break;
                case 4: 
                    System.out.println("#####################################");
                    System.out.println(donnerListePartiesAttente(joueur.getIdentifiant()));
                    System.out.println("#####################################");
                    break;
                default: joueur = null;
            }
        }
        System.out.println("Vous êtes déconnecté");
    }
}
