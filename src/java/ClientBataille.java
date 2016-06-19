
import SynchronisationClient.Joueur;
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
import SynchronisationClient.Partie;

public class ClientBataille {

    private static WebTarget serviceJoueur = null;

    /**
     * authentifier.
     * @param identifiant
     * @param motDePasse
     * @return Joueur
     */
    public static boolean authentifier(String identifiant, String motDePasse) {
        return serviceJoueur.path("authentifier/" + identifiant + "/" + motDePasse)
                .request().get(Boolean.class);
    }

    /**
     * creerCompte
     * 
     * @param identifiant
     * @param motDePasse
     * @return String
     */
    public static boolean creerCompte(String identifiant, String motDePasse) {
        return serviceJoueur.path("creerCompte/" + identifiant + "/" + motDePasse)
                .request().get(Boolean.class);
    }
    
    /**
     * deconnecter
     *
     * @param identifiant
     * @return
     */
    public static boolean deconnecter(String identifiant, int idPartie) {
        return serviceJoueur.path("deconnecter/" + identifiant + "/" + idPartie)
                .request().get(Boolean.class);
    }

    /**
     * creerCompte
     * 
     * @param identifiant
     * @return 
     */
    public static int creerPartie(String identifiant, int nbJoueurs) {
        return serviceJoueur.path("creerPartie/" + identifiant + "/" + nbJoueurs)
                .request().get(Integer.class);
    }
    
    /**
     * deconnecterPartie
     *
     * @param identifiant
     * @return
     */
    public static boolean deconnecterPartie(String identifiant) {
        return serviceJoueur.path("deconnecterPartie/" + identifiant)
                .request().get(Boolean.class);
    }

    /**
     * donnerScore
     * @param identifiant
     * @return 
     */
    public static int donnerScore(String identifiant) {
        return serviceJoueur.path("donnerScore/" + identifiant)
                .request().get(Integer.class);
    }

    /**
     * donnerClassement
     * @param identifiant
     * @return 
     */
    public static String donnerClassement(String identifiant) {
        return serviceJoueur.path("donnerClassement/" + identifiant)
                .request().get(String.class);
    }

    /**
     * donnerListeConnectes
     * @param identifiant
     * @return 
     */
    public static String donnerListeConnectes(String identifiant) {
        return serviceJoueur.path("donnerListeConnectes/" + identifiant)
                .request().get(String.class);
    }

    /**
     * donnerListePartiesAttente.
     * @param identifiant
     * @return Parties
     */
    public static String donnerListePartiesAttente(String identifiant) {
        return serviceJoueur.path("donnerListePartiesAttente/" + identifiant)
                .request().get(String.class);
    }
    
    /**
     * donnerListePartiesAttente
     * @param identifiant
     * @param idPartie
     * @return Partie
     */
    public static boolean rejoindrePartie(String identifiant, int idPartie) {
        return serviceJoueur.path("rejoindrePartie/" + identifiant)
                .request().get(Boolean.class);
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
        
        boolean connectOk = false;
        String identifiant = "";
        int numeroPartie = -1;

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
                identifiant = sc.nextLine();
                System.out.print("Entrez votre mot de passe: ");
                String motDePasse = sc.nextLine();
                connectOk = authentifier(identifiant, motDePasse);
                if (connectOk) {
                    System.out.print("Connexion OK");
                    mauvaiseReponse = false;
                }

            } else if (input == 2) {
                sc = new Scanner(System.in);
                System.out.print("Entrez votre identifiant: ");
                identifiant = sc.nextLine();
                System.out.print("Entrez votre mot de passe: ");
                String motDePasse = sc.nextLine();
                connectOk = creerCompte(identifiant, motDePasse);
                if (connectOk) {
                    System.out.println("Compte créé & connexion OK");
                    mauvaiseReponse = false;
                }
                
            } else if (input == 2) {
                mauvaiseReponse = false;
                
            } else {
                System.out.println("Désolé, je n'ai pas compris, veuillez réessayer: ");
            }
        }

        while (connectOk) {
            sc = new Scanner(System.in);
            System.out.println("Vous souhaitez: ");
            System.out.println("    récupérer votre score, taper 1");
            System.out.println("    récupérer votre classement, taper 2");
            System.out.println("    recupérer la liste des joueurs connectés, taper 3");
            System.out.println("    récupérer la liste des parties en attente, taper 4");
            System.out.println("    créer une nouvelle partie, taper 5");
            System.out.println("    rejoindre une partie, taper 6");
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
                    System.out.println(donnerScore(identifiant));
                    System.out.println("#####################################");
                    break;
                case 2:
                    System.out.println("#####################################");
                    System.out.println(donnerClassement(identifiant));
                    System.out.println("#####################################");
                    break;
                case 3:
                    System.out.println("#####################################");
                    System.out.println(donnerListeConnectes(identifiant));
                    System.out.println("#####################################");
                    break;
                case 4:
                    System.out.println("#####################################");
                    System.out.println(donnerListePartiesAttente(identifiant));
                    System.out.println("#####################################");
                    break;
                case 5:
                    System.out.print("Donnez le nombre de joueurs: ");
                    int nbJoueurs;
                    try {
                        nbJoueurs = sc.nextInt();
                    } catch(Exception e) {
                        nbJoueurs = 2;
                    }
                    System.out.println("#####################################");
                    System.out.println("Partie créée avec " 
                            + creerPartie(identifiant, nbJoueurs) + " joueurs");
                    System.out.println("#####################################");
                    break;
                case 6:
                    System.out.println("#####################################");
                    System.out.println(donnerListePartiesAttente(identifiant));
                    System.out.println("#####################################");
                    System.out.print("Tapez le numéro de la partie à rejoindre: ");
                    try {
                        numeroPartie = sc.nextInt();
                    } catch (Exception e) {
                        numeroPartie = -1;
                    }
                    if(numeroPartie >= 0) {
                        if(rejoindrePartie(identifiant, numeroPartie)) {
                            System.out.println("Partie rejointe avec succès.");
                        } else {
                            System.out.println("Impossible de rejoindre la partie.");
                        }
                    } else {
                        System.out.println("Nombre incorrect.");
                    }
                    System.out.println("#####################################");
                    break;
                default:
                    deconnecter(identifiant, numeroPartie);
                    connectOk = false;
            }
        }
        System.out.println("Vous êtes déconnecté");
    }
}
