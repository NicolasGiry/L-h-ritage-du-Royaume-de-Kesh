package Bonus;

import Equipements.Equipement;
import Equipements.Arme;

import java.util.Scanner;

import Combattant.Player;

public class Loot{
    private int pieces;
    private int exp;
    private Equipement equipement;
    private Arme arme;

    public Loot(int pieces, Equipement equipement, int exp){
        this.pieces = pieces;
        this.equipement = equipement;
        this.arme = null;
        this.exp = exp;
    }

    public Loot(int pieces, Arme arme, int exp){
        this.pieces = pieces;
        this.arme = arme;
        this.equipement = null;
        this.exp = exp;
    }

    public void ouvrirCoffre(Player joueur){
        Scanner input = new Scanner(System.in);
        String trash;
        System.out.println("Vous trouvez un coffre ! vous l'ouvrez.");
        trash = input.nextLine();
        joueur.gagnerPieces(pieces);
        if (equipement != null){
            System.out.println("Il y a un.e "+equipement);
            joueur.recevoirEquipement(equipement);
        }
        if (arme != null){
            System.out.println("Il y a un.e "+arme);
            joueur.recevoirArme(arme);
        }
    }
}