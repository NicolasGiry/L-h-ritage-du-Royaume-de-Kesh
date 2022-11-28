package Bonus;

import java.util.Scanner;
import Combattant.Player;
import Objet.Equipement;
import Objet.Arme;
import Objet.Potions;
import Objet.Objet;

public class Marchand{
    private Objet equipement;
    private Objet arme;
    private Objet potion;

    public Marchand(Equipement equipement, Arme arme, Potions potion){
        this.equipement = equipement;
        this.arme = arme;
        this.potion = potion;
    }

    public void rencontrerMarchand(Player joueur){
        Scanner input = new Scanner(System.in);
        int choix;
        int argent = joueur.getPieces();
        System.out.println("Bonjour voyageur ! n'hésitez pas à regarder !");
        afficherMarchandise();
        System.out.println("Que voulez-vous acheter ? [1/2/3] ( 0 pour partir )");
        System.out.println("Votre argent : "+joueur.getPieces()+" ₽");
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

    private void afficherMarchandise(){
        System.out.println("1. Equipement : "+equipement+" "+equipement.getPrix()+"₽");
        System.out.println("2. Arme : " + arme + " " + arme.getPrix() + "₽");
        System.out.println("3. Potion : " + potion + " " + potion.getPrix() + "₽");
    }

    private boolean verifierArgent(int prix, int argent){
        boolean verif = prix <= argent;
        if (!verif){
            System.out.println("Vous n'avez pas assez de pieces");
        }
        return verif;
    }

    private void equiperAchat(Player joueur, int argent, Objet objet){
        int prix = equipement.getPrix();
        if (verifierArgent(prix, argent)){
            joueur.recevoirObjet(objet);
        }else{
            System.out.println("Vous n'avez pas assez de pieces");
            rencontrerMarchand(joueur);
        }
    }
}