
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
import parties.Partie;
import parties.Parties;

public class ClientBataille {

    private static WebTarget serviceJoueur = null;

    
    /**
     * listerJoueurs.
     * @return Joueurs
     * @throws javax.xml.bind.JAXBException 
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
     * authentifier.
     * @param identifiant
     * @param motDePasse
     * @return Joueur
     */
    public static Joueur authentifier(String identifiant, String motDePasse) {
        return serviceJoueur.path("authentifier/" + identifiant + "/" + motDePasse)
                .request().get(Joueur.class);
    }

    /**
     * creerCompte
     * @param joueur
     * @return String
     */
    public static String creerCompte(Joueur joueur) {
        return serviceJoueur.path("creerCompte/").request(MediaType.TEXT_PLAIN)
                .put(Entity.xml(joueur)).readEntity(String.class);
    }

    /**
     * creerCompte
     */
    public static String creerPartie(Joueur joueur) {
        return serviceJoueur.path("creerPartie/").request(MediaType.TEXT_PLAIN)
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
    public static Joueurs donnerListeConnectes(String identifiant,String motDePasse) throws JAXBException {
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
        reponse = serviceJoueur.path("donnerListeConnectes/" + identifiant + "/" + motDePasse)
                .request().get(String.class);

        /*
         ** Traitement de la reponse XML : transformation en une instance de la classe Villes
         */
        xmlStr = new StringBuffer(reponse);
        if(reponse.length()>0)
            root = unmarshaller.unmarshal(new StreamSource(new StringReader(xmlStr.toString())), Joueurs.class);
        else
            return null;
        return root.getValue();
    }

    /**
     * donnerListePartiesAttente.
     * @param identifiant
     * @param motDePasse
     * @return Parties
     * @throws javax.xml.bind.JAXBException
     */
    public static Parties donnerListePartiesAttente(String identifiant, String motDePasse) throws JAXBException {
        String reponse;
        StringBuffer xmlStr;
        JAXBContext context;
        JAXBElement<Parties> root;
        Unmarshaller unmarshaller;

        /*
         ** Instanciation du convertiseur XML => Objet Java
         */
        context = JAXBContext.newInstance(Parties.class);
        unmarshaller = context.createUnmarshaller();

        /*
         ** Envoi de la requete
         */
        reponse = serviceJoueur.path("donnerListePartiesAttente/" + identifiant + "/" + motDePasse)
                .request().get(String.class);

        /*
         ** Traitement de la reponse XML : transformation en une instance de la classe Villes
         */
        xmlStr = new StringBuffer(reponse);
        if(reponse.length()>0)
            root = unmarshaller.unmarshal(new StreamSource(new StringReader(xmlStr.toString())), Parties.class);
        else
            return null;
        Parties p = root.getValue();
        return p;
    }
    
    /**
     * donnerListePartiesAttente
     * @param identifiant
     * @param motDePasse
     * @return Parties
     * @throws javax.xml.bind.JAXBException 
     */
    public static Parties donnerListeParties(String identifiant, String motDePasse) throws JAXBException {
        String reponse;
        StringBuffer xmlStr;
        JAXBContext context;
        JAXBElement<Parties> root;
        Unmarshaller unmarshaller;

        /*
         ** Instanciation du convertiseur XML => Objet Java
         */
        context = JAXBContext.newInstance(Parties.class);
        unmarshaller = context.createUnmarshaller();

        /*
         ** Envoi de la requete
         */
        reponse = serviceJoueur.path("donnerListePartiesAttente/" + identifiant + "/" + motDePasse)
                .request().get(String.class);

        /*
         ** Traitement de la reponse XML : transformation en une instance de la classe Villes
         */
        xmlStr = new StringBuffer(reponse);
        if(reponse.length()>0)
            root = unmarshaller.unmarshal(new StreamSource(new StringReader(xmlStr.toString())), Parties.class);
        else
            return null;
        Parties p = root.getValue();
        Parties attenteP = new Parties();
        for(Partie pa : p.liste)
        {
            if(!pa.isFini())
                attenteP.liste.add(pa);
        }
        return attenteP;
    }
    
    /**
     * donnerListePartiesAttente
     * @param partie
     * @param identifiant
     * @param motDePasse
     * @return Partie
     * @throws javax.xml.bind.JAXBException 
     */
    public static Partie rejoindrePartie(String partie, String identifiant, String motDePasse) throws JAXBException {
        String reponse;
        StringBuffer xmlStr;
        JAXBContext context;
        JAXBElement<Parties> root;
        Unmarshaller unmarshaller;

        /*
         ** Instanciation du convertiseur XML => Objet Java
         */
        context = JAXBContext.newInstance(Parties.class);
        unmarshaller = context.createUnmarshaller();

        /*
         ** Envoi de la requete
         */
        reponse = serviceJoueur.path("rejoindrePartie/" + partie + "/" + identifiant + "/" + motDePasse)
                .request().get(String.class);

        /*
         ** Traitement de la reponse XML : transformation en une instance de la classe Villes
         */
        xmlStr = new StringBuffer(reponse);
        if(reponse.length()>0)
            root = unmarshaller.unmarshal(new StreamSource(new StringReader(xmlStr.toString())), Parties.class);
        else
            return null;
        Parties p = root.getValue();
        return p.liste.get(0);
    }

    /*
     ** --- main ---
     */
    public static void main(String args[]) throws Exception {
        /*
         ** Initialisation du stub pour interagir avec le service web REST
         */
        serviceJoueur = ClientBuilder.newClient().target("http://localhost:8080/BATAILLE");
        
        Scanner sc;
        Joueur joueur = null;
        Partie partie = null;

        int input;
        boolean mauvaiseReponse = true;
        while (mauvaiseReponse) {
            sc = new Scanner(System.in);
            System.out.println("#####################################");
            System.out.println("# Bienvenue sur le jeu de bataille! #");
            System.out.println("#####################################");
            System.out.print("Pour vous identifier taper 1, \n"
                    + "Pour créer un compte taper 2 \n"
                    + "Pour quitter taper 3 : ");
            try {
                input = sc.nextInt();
            } catch (Exception e) {
                input = 3;
            }
            if (input == 1) {
                sc = new Scanner(System.in);
                System.out.print("Entrez votre identifiant: ");
                String identifiant = sc.nextLine();
                System.out.print("Entrez votre mot de passe: ");
                String motDePasse = sc.nextLine();
                joueur = authentifier(identifiant, motDePasse);
                if (joueur != null) {
                    System.out.print("Connexion OK");
                    mauvaiseReponse = false;
                }

            } else if (input == 2) {
                sc = new Scanner(System.in);
                System.out.print("Entrez votre identifiant: ");
                String identifiant = sc.nextLine();
                System.out.print("Entrez votre mot de passe: ");
                String motDePasse = sc.nextLine();
                joueur = new Joueur(identifiant, motDePasse);
                String res = creerCompte(joueur);
                if (res.equals("OK")) {
                    System.out.println("Compte créé & connexion OK");
                    mauvaiseReponse = false;
                }
                
            } else if (input == 2) {
                mauvaiseReponse = false;
                
            } else {
                System.out.println("Désolé, je n'ai pas compris, veuillez réessayer: ");
            }
        }

        while (joueur != null) {
            sc = new Scanner(System.in);
            System.out.println("Vous souhaitez: ");
            System.out.println("    récupérer votre score, taper 1");
            System.out.println("    récupérer votre classement, taper 2");
            System.out.println("    recupérer la liste des joueurs connectés, taper 3");
            System.out.println("    récupérer la liste des parties en attente, taper 4");
            System.out.println("    créer une nouvelle partie, taper 5");
            System.out.println("    rejoindre partie, taper 6");
            System.out.println("    vous déconnecter, taper n'importe quel autre caractère");
            System.out.print("C'est à vous: ");

            try {
                input = sc.nextInt();
            } catch (Exception e) {
                input = 7;
            }
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
                    Joueurs joueurs = donnerListeConnectes(joueur.getIdentifiant(),joueur.getMotDePasse());
                    for(Joueur j : joueurs.liste)
                    {
                        System.out.println(j.getIdentifiant());
                    }
                    System.out.println("#####################################");
                    break;
                case 4:
                    System.out.println("#####################################");
                    for (Partie p : donnerListeParties(joueur.getIdentifiant(), joueur.getMotDePasse()).liste) {
                        System.out.println(p);
                    }
                    System.out.println("#####################################");
                    break;
                case 5:
                    System.out.println("#####################################");
                    System.out.println(creerPartie(joueur));
                    System.out.println("#####################################");
                    break;
                case 6:
                    System.out.println("#####################################");
                    Parties parties = donnerListePartiesAttente(joueur.getIdentifiant(), joueur.getMotDePasse());
                    int i = 1;
                    for(Partie p : parties.liste)
                    {
                        System.out.println(p.getCreateur().getIdentifiant() + "nombre joueurs : " + p.getListeJoueurs().size());
                        i++;
                    }
                     try {
                        input = Integer.getInteger(sc.nextLine());
                    } catch (Exception e) {
                        input = i;
                    }
                    if(input != i)
                    {
                        partie = rejoindrePartie(parties.liste.get(i).getCreateur().getIdentifiant(),joueur.getIdentifiant(),joueur.getMotDePasse());
                    }
                    System.out.println("#####################################");
                    break;
                default:
                    joueur = null;
            }
        }
        System.out.println("Vous êtes déconnecté");
    }
}
