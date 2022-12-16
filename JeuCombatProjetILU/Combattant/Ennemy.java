package Combattant;

import Bonus.Loot;
import Scenario.Affichage;
import Aptitudes.Attaques;
import java.util.Random;
import java.util.Scanner;

public class Ennemy extends Combattant{
    private static Attaques[] attaquesEnnemy = {Attaques.GRIFFE, Attaques.MORSURE};
    private Loot tresor;
    private Scanner input = new Scanner(System.in);
    private Random random = new Random();
    private Affichage output = new Affichage();

    public Ennemy(int defense, Loot loot, String nom, int niveau){
        super(defense, 2, attaquesEnnemy, niveau, 30 + (niveau*2)%70);
        this.tresor = loot;
        super.SetNom(nom);
    }

    public Loot getTresor(){
        return tresor;
    }
    
    public int AttaqueMonstre(Player joueur){
        
        int choixAttaque = random.nextInt(2);
        int att = super.calculerAtt();
        if (joueur.isPoisonned){
            joueur.BePoisonned(att/3);
        }
        Attaques attaque = super.getAttaques()[choixAttaque];
        output.raconterTexte(super.getNom()+" lance l'attaque "+attaque+" !", joueur);
        input.nextLine();

        switch (attaque){
            case MORSURE:
                att = attaque.morsure(att, joueur);
                break;
            case GRIFFE:
                att = attaque.griffe(att);
                break;
            default:
                break;
        }
        att -= joueur.defense/5;
        if (att<0){
            att = 0;
        }
        output.raconterTexte(getNom()+" fait une attaque de "+att+" dégats !", joueur);
        input.nextLine();
        return att;
    }
}