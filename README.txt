Pour lancer la partie web service vous devez:

- Déployer le service web
- Lancer le serveur RMI (Server.java)
- Lancer ClientBataille.java

Pour lancer la partie Corba vous devez :

- Lancer le serveur RMI (Server.java) (si ce n'est pas encore fait)
- Trouver les chemin java où se trouve votre fichier "idlj.exe", par exemple : C:\Program Files\Java\jdk1.7.0_79\bin
- Entrer la commande SET + cheminJava comme suit :
	set PATH=%PATH%;"C:\Program Files\Java\jdk1.7.0_79\bin"
- Lancer l'annuaire Corba avec la commande :
	start tnameserv
- Lancer le serveur Corba (Tier2_Corba.java)
- Lancer le client Corba (ClientBatailleCorba.java)