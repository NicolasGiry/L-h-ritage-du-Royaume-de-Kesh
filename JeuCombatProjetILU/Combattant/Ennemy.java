package Combattant;

import Bonus.Loot;
import Aptitudes.Attaques;
import java.util.Random;
import java.util.Scanner;

public class Ennemy extends Combattant{
    private static Attaques[] attaquesEnnemy = {Attaques.GRIFFE, Attaques.MORSURE};
    private Loot tresor;
    private Scanner input = new Scanner(System.in);
    private Random random = new Random();

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
        System.out.println(super.getNom()+" lance l'attaque "+attaque+" !");
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
        System.out.println(getNom()+" fait une attaque de "+att+" dégats !");
        input.nextLine();
        return att;
    }
}