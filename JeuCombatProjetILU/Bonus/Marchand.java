package Bonus;

import java.util.Scanner;
import Combattant.Player;
import Objet.Equipement;
import Objet.Arme;
import Objet.Potions;
import Scenario.Affichage;
import Objet.Objet;

public class Marchand{
    private Objet equipement;
    private Objet arme;
    private Objet potion;
    private Affichage output = new Affichage();

    public Marchand(Equipement equipement, Arme arme, Potions potion){
        this.equipement = equipement;
        this.arme = arme;
        this.potion = potion;
    }

    public void rencontrerMarchand(Player joueur){
        Scanner input = new Scanner(System.in);
        int choix;
        int argent = joueur.getPieces();
        output.texteRetourALaLigne(new String[] {"Vous rencontrer le chemin d'un marchand !","Bonjour voyageur ! n'hésitez pas à regarder !"});
        input.nextLine();
        afficherMarchandise(joueur);
        System.out.println("Que voulez-vous acheter ? [1/2/3] ( 0 pour partir )");
        System.out.println();
        choix = input.nextInt();
        while (choix<0 || choix>3){
            System.out.println("Erreur ! Que voulez-vous acheter ? [1/2/3] ( 0 pour partir )");
            choix = input.nextInt();
        }
        if (choix==1){
            equiperAchat(joueur, argent, equipement);
        }if (choix==2){
            equiperAchat(joueur, argent, arme);
        }if (choix==3){
            equiperAchat(joueur, argent, potion);
        }
    }

    private void afficherMarchandise(Player joueur){
        String[] marchandises = {"                                                                              Votre argent : "+joueur.getPieces()+" ₽",
                                    "1. Equipement : "+equipement+" "+equipement.getPrix()+"₽",
                                    "2. Arme : " + arme + " " + arme.getPrix() + "₽",
                                    "3. Potion : " + potion + " " + potion.getPrix() + "₽"};
        output.texteRetourALaLigne(marchandises);
    }

    private boolean verifierArgent(int prix, int argent){
        boolean verif = prix <= argent;
        if (!verif){
            output.raconterTexte("Vous n'avez pas assez de pieces");
        }
        return verif;
    }

    private void equiperAchat(Player joueur, int argent, Objet objet){
        int prix = objet.getPrix();
        if (verifierArgent(prix, argent)){
            joueur.perdrePieces(prix);
            joueur.recevoirObjet(objet);
        }else{
            output.raconterTexte("Vous n'avez pas assez de pieces");
            rencontrerMarchand(joueur);
        }
    }
}