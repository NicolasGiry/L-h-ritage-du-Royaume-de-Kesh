package Bonus;

import Objet.Objet;
import Scenario.Affichage;

import java.util.Scanner;

import Combattant.Player;

public class Loot{
    private int pieces;
    private int exp;
    private Objet objet;
    private Affichage output = new Affichage();

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
        output.raconterTexte("Vous trouvez un coffre ! vous l'ouvrez.");
        input.nextLine();
        joueur.gagnerPieces(pieces);
        if (objet != null){
            output.raconterTexte("Il y a un.e "+objet+ " a l'interieur !");
            input.nextLine();
            joueur.recevoirObjet(objet);
        }
    }
}