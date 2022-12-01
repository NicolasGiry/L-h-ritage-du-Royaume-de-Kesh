package Bonus;

import Objet.Objet;

import java.util.Scanner;

import Combattant.Player;

public class Loot{
    private int pieces;
    private int exp;
    private Objet objet;

    public Loot(int pieces, Objet objet, int exp){
        this.pieces = pieces;
        this.objet = objet;
        this.exp = exp;
    }

    public int getXp(){
        return exp;
    }

    public void ouvrirCoffre(Player joueur){
        Scanner input = new Scanner(System.in);
        System.out.println("Vous trouvez un coffre ! vous l'ouvrez.");
        input.nextLine();
        joueur.gagnerPieces(pieces);
        if (objet != null){
            System.out.println("Il y a un.e "+objet+ " a l'interieur !");
            joueur.recevoirObjet(objet);
        }
    }
}