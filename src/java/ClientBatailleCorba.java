/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Corba.JoueurAction;
import Corba.JoueurActionHelper;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.naming.InitialContext;

/**
 *
 * @author Goldwing
 */
public class ClientBatailleCorba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {

            // *** Déclaration des variables ***
            boolean jeu = true;
            boolean enJeu = false;
            String name = "";
            boolean connected = false;
            boolean userError = true;
            boolean erreur = false;
            int nbr;
            
            // *** Initialisation du lien Corba ***
            InitialContext ctxt = new InitialContext();
            Object Stub = ctxt.lookup("CorbaServer");
            JoueurAction jActStub = JoueurActionHelper.narrow((org.omg.CORBA.Object) Stub);

            
            // *** Début du jeu ***
            System.out.println("Bonjour et binvennue jeune padawan !");
            System.out.println("Que souhaites-tu faire ?");
            System.out.println(" 1 - Pour créer un compte ");
            System.out.println(" 2 - Pour te connecter ");
            System.out.println("Entre le numéro de ton choix : ");
            System.out.println("======================================");

            int nbAction = 0;

            // *** Etape 1 : création de compte/connexion au jeu ***
            while (!connected) {

                nbr = 0;

                // *** Controle des entrées joueur ***
                do {
                    erreur = false;
                    if (nbAction > 0) {
                        System.out.println("======================================");
                        System.out.println("Et maintenant que souhaites-tu faire ?");
                        System.out.println(" 1 - Créer un compte ");
                        System.out.println(" 2 - Te connecter ");
                        System.out.println("Entre le numéro de ton choix : ");
                        System.out.println("======================================");
                    } else {
                        nbAction = 1;
                    }

                    try {
                        Scanner sc = new Scanner(System.in);
                        nbr = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("ERREUR : La valeur entrée est erronnée, il faut recommencer avec un numéro");
                        erreur = true;
                    }

                } while (erreur);

                System.out.println("======================================");
                System.out.println("Alors que la Force soit avec toi ! ");
                System.out.println("======================================");

                String mdp;
                Scanner sc = new Scanner(System.in);
                switch (nbr) {
                    case 1:// *** Création de compte
                        
                        do {
                            if(userError == true){
                            System.out.println("Entre ton nom d'utilisateur : ");
                            name = sc.next();
                            userError = false;
                            }
                            
                            System.out.println("Entre ton mot de passe : ");
                            mdp = sc.next();
                            System.out.println("Confirme ton mot de passe : ");
                            String mdpConfirm = sc.next();
                            
                            // *** Test des valeurs en entrées ***
                            try {
                                if (mdp.toString().equals(mdpConfirm.toString())) {
                                    boolean repS = jActStub.creerJoueur(name, mdp);
                                    System.out.println("\n");
                                    System.out.println("Loading ...");

                                    if (repS == true) {
                                        System.out.println("======================================");
                                        System.out.println("--------- Ton compte " + name + " a bien été créé ---------");
                                        System.out.println("Ils recrutent des nains, maintenant dans les commandos ?");
                                        System.out.println("======================================");
                                        erreur = false;
                                    } else {
                                        System.out.println("======================================");
                                        System.out.println("--------- ERROR : Ton compte " + name + " n'a pas été créé ---------");
                                        System.out.println("--------- Il faut essayer avec un autre nom ---------");
                                        System.out.println("======================================");
                                        userError = true;
                                        erreur = true;
                                    }
                                    mdpConfirm = null;
                                    mdp = null;
                                } else {
                                    erreur = true;
                                    System.out.println("======================================");
                                    System.out.println("Erreur : Ton mot de passe et sa confirmation ne correspondent pas, il faut recommencer !");
                                    System.out.println("======================================");
                                }
                            } catch (InputMismatchException e) {
                                erreur = true;
                                System.out.println("Erreur : Il faut recommencer !");
                            }

                        } while (erreur);
                        nbr = 0;
                        
                        break;

                    case 2: // *** Connexion ***

                        erreur = true;
                        do {
                            erreur = false;

                            System.out.println("Entre ton nom d'utilisateur : ");

                            // *** Test des valeurs en entrées ***
                            try {
                                name = sc.next();
                                System.out.println("Entre ton mot de passe : ");
                                mdp = sc.next();

                                System.out.println("\n");
                                System.out.println("Loading ...");

                                boolean repS = jActStub.connecterJoueur(name, mdp);
                                if (repS == true) {
                                    System.out.println("======================================");
                                    System.out.println("--------- Le compte est bien connecté ---------");
                                    System.out.println("======================================");
                                    connected = true;
                                    erreur = false;
                                } else {
                                    System.out.println("======================================");
                                    System.out.println("--------- L'identifiant ou le mot de passe est incorrecte ---------");
                                    System.out.println("======================================");
                                    erreur = true;
                                }
                            } catch (InputMismatchException e) {
                                erreur = true;
                            }
                        } while (erreur);

                        nbr = 0;
                        break;
                    default:
                        System.out.println("Erreur veuillez entrer un autre numéro ");
                }
            }
            
            // *** Etape 2 : Jeu ***
            while (jeu == true) {
                nbr = 0;
                                
                // *** Test des valeurs en entrées ***
                do {
                    erreur = false;
                    
                    System.out.println("======================================");
                    System.out.println("Il t'es maintenant possible de :");
                    System.out.println(" 1 - Récupérer ton score ");
                    System.out.println(" 2 - Récupérer le classement ");
                    System.out.println(" 3 - Consulter les joueurs connectés ");
                    System.out.println(" 4 - Consulter les parties en attente de joueurs ");
                    System.out.println(" 5 - Rejoindre une partie ");
                    System.out.println(" 6 - Créer une partie ");
                    System.out.println(" 7 - Te déconnecter ");
                    System.out.println("Entre le numéro de ton choix : ");
                    System.out.println("======================================");
                   
                    try {
                        Scanner sc = new Scanner(System.in);
                        nbr = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("ERREUR : La valeur entrée est erronnée, il faut recommencer avec un numéro");
                        erreur = true;
                    }

                } while (erreur);
                
                // *** Choix Joueur ***
                switch (nbr) {
                    case 1: // *** Récupérer ton score ***
                        int score = jActStub.getScore(name);
                        System.out.println("\n");
                        System.out.println("Loading ...");
                        if (score>-1) {
                            System.out.println("======================================");
                            System.out.println("--------- Ton score est de " + score + " ---------");
                            System.out.println("======================================");
                        } 
                        break;
                        
                    case 2: // *** Consulter le classement ***
                        String repClassement = jActStub.getClassement(name);
                        System.out.println("\n");
                        System.out.println("Loading ...");
                        if (!repClassement.isEmpty()) {
                            System.out.println("======================================");
                            System.out.println("--------- Le classement est : ---------\n " + repClassement + "\n");
                            System.out.println("======================================");
                        } else{
                            System.out.println("--------- Il n'y a pas encore de classement à afficher ---------");
                        }
                        break; 
                        
                    case 3: // *** Consulter les joueurs connectés ***
                        String repJoueurs = jActStub.listerJoueurs(name);
                        System.out.println("\n");
                        System.out.println("Loading ...");
                        if (!repJoueurs.isEmpty()) {
                            System.out.println("======================================");
                            System.out.println("--------- Les joueurs connectés sont : ---------\n " + repJoueurs + "\n");
                            System.out.println("======================================");
                        } 
                        break;
                        
                    case 4: // *** Consulter les partie en attente de joueurs ***
                        String repParties = jActStub.listerParties(name);
                        System.out.println("\n");
                        System.out.println("Loading ...");
                        if (!repParties.isEmpty()) {
                            System.out.println("======================================");
                            System.out.println("--------- Les parties en attentes sont : ---------\n " + repParties + "\n");
                            System.out.println("======================================");
                        }else{
                            System.out.println("--------- Il n'y a pas de partie en cours ---------");
                        }
                        break;
                        
                    case 5: // *** Rejoindre une partie ***
                            repParties = jActStub.listerParties(name);
                            System.out.println("\n");
                            System.out.println("Loading ...");
                            if (!repParties.isEmpty()) {
                                System.out.println("======================================");
                                System.out.println("--------- Les parties en attentes sont : ---------\n " + repParties.toString() + "\n");
                                System.out.println("======================================");
                                
                                // *** Test des valeurs en entrées ***
                                do {
                                    erreur = false;
                                    System.out.println("======================================");
                                    System.out.println("Entre le numéro de la partie que tu souhaite rejoindre : ");
                                    System.out.println("======================================");
                                    try {
                                        Scanner sc = new Scanner(System.in);
                                        nbr = sc.nextInt();
                                        
                                            if(jActStub.nbJoueurComplet(nbr)){
                                                System.out.println("======================================");
                                                System.out.println("Votre partie va commencer, veuillez patienter");
                                                System.out.println("Loading ...");
                                                System.out.println("\n");
                                                Thread.sleep(500);
                                                System.out.println("======================================");
                                                String gagnant = jActStub.comencerJeu(nbr);
                                                System.out.println("Le gagnant est : "+gagnant);
                                            }
                                        
                                        
                                    } catch (InputMismatchException e) {
                                        System.out.println("ERREUR : La valeur entrée est erronnée, il faut recommencer avec un numéro");
                                        erreur = true;
                                    }

                                } while (erreur);
                            
                            }else{
                                System.out.println("--------- Il n'y a pas de partie en cours ---------");
                            }
                        
                        break; 
                        
                    case 6: // *** Créer une partie ***
                        // *** Test des valeurs en entrées ***
                        do {
                            erreur = false;
                            nbAction = 0;
                            try {
                            System.out.println("======================================");
                            System.out.println("Entre le nombre de joueur minimum pour ta partie  : ");
                            System.out.println(" === Attention : Pas moins de 2 biensûr ! === ");
                            Scanner sc = new Scanner(System.in);
                            nbr = sc.nextInt();
                            if(nbr<2){
                                erreur = true;
                                System.out.println("ERREUR : La valeur entrée est erronnée, il faut recommencer avec un autre numéro");
                                System.out.println("Apparement il te faut aussi des lunettes");
                           }else{
                                
                                    int repS = jActStub.creerPartie(name,nbr);
                                    boolean repP = jActStub.nbJoueurComplet(repS);
                                    System.out.println("nbr : "+nbr);
                                    if(repP==true){
                                        System.out.println("======================================");
                                        System.out.println("Votre partie va commencer, veuillez patienter");
                                        System.out.println("Loading ...");
                                        System.out.println("\n");
                                        Thread.sleep(500);
                                        System.out.println("======================================");
                                        String gagnant = jActStub.comencerJeu(repS);
                                        System.out.println("Le gagnant est : "+gagnant);
                                    }
                                
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("ERREUR : La valeur entrée est erronnée, il faut recommencer avec un numéro");
                            erreur = true;
                        }

                    } while (erreur);
                        
                        break;
                        
                    case 7: // *** Déconnecter ***
                        boolean repS = jActStub.deconnecterJoueur(name);
                        System.out.println("\n");
                        System.out.println("Loading ...");
                        if (repS == true) {
                            System.out.println("======================================");
                            System.out.println("--------- Ton compte " + name + " a bien été déconnecté ---------");
                            System.out.println("--------- Bye Bye ---------");
                            System.out.println("======================================");
                            
                            jeu = false;
                        } else {
                            System.out.println("======================================");
                            System.out.println("--------- ERROR : Ton compte " + name + " n'a pas été déconnecté ---------");
                            System.out.println("======================================");
                            
                        }
                        break;
                        
                    default:
                        System.out.println("Erreur veuillez entrer un autre numéro ");
                }
                
                // Etape 3 : Partie
                while (enJeu) {
                
                }
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
