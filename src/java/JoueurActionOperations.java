package Corba;


/**
* Corba/JoueurActionOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Corba.idl
* lundi 20 juin 2016 19 h 59 CEST
*/

public interface JoueurActionOperations 
{
  boolean creerJoueur (String identifiant, String motDePasse);
  boolean connecterJoueur (String identifiant, String motDePasse);
  boolean deconnecterJoueur (String identifiant);
  int getScore (String identifiant);
  String listerJoueurs (String identifiant);
  String getClassement (String identifiant);
  int creerPartie (String identifiant, int nbJoueurs);
  boolean ajouterJoueurPartie (String identifiant, int idPartie);
  boolean disconnectToPartie (String identifiant, int idPartie);
  String listerParties (String identifiant);
  boolean nbJoueurComplet (int idPartie);
  String comencerJeu (int idPartie);
} // interface JoueurActionOperations