package Bonus;

import java.util.Random;
import java.util.Scanner;
import Combattant.Player;
import Equipements.Equipement;
import Equipements.Arme;

public class Marchand{
    private Equipement equipement;
    private Arme arme;
    private Potions potion;

    public Marchand(Equipement equipement, Arme arme, Potions potion){
        this.equipement = equipement;
        this.arme = arme;
        this.potion = potion;
    }

    public void rencontrerMarchand(Player joueur){
        Scanner input = new Scanner(System.in);
        int choix;
        int prix=0;
        System.out.println("Bonjour voyageur ! n'hésitez pas à regarder !");
        afficherMarchandise();
        System.out.println("Que voulez-vous acheter ? [1/2/3] ( 0 pour partir )");
        choix = input.nextInt();
        while (choix<0 || choix>3){
            System.out.println("Erreur ! Que voulez-vous acheter ? [1/2/3] ( 0 pour partir )");
            choix = input.nextInt();
        }
        if (choix==1){
            
            prix = equipement.getPrix();
        }if (choix==2){
            
           prix = arme.getPrix();
        }if (choix==3){
            
            prix = potion.getPrix();
        }
        if (prix>joueur.getPieces()){
            System.out.println("Vous n'avez pas assez de pieces");
            rencontrerMarchand(joueur);
        }else{
            joueur.perdrePieces(prix);
            if (choix==1){
                joueur.recevoirEquipement(equipement);
            }if (choix==2){
                joueur.recevoirArme(arme);
            }if (choix==3){
                joueur.recevoirPotion(potion);
            }
        }
        
    }

    private void afficherMarchandise(){
        System.out.println("1. Equipement : "+equipement+" "+equipement.getPrix()+"₽");
        System.out.println("2. Arme : " + arme + " " + arme.getPrix() + "₽");
        System.out.println("3. Potion : " + potion + " " + potion.getPrix() + "₽");
    }
}