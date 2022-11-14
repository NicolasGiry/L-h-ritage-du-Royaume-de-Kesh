package Combattant;

import Equipements.Arme;
import Equipements.Equipement;
import Bonus.Loot;
import Aptitudes.Type;
import Aptitudes.Attaques;
import java.util.Random;
import java.util.Scanner;

public class Ennemy extends Combattant{
    private Equipement tresor;
    private Type type;

    public Ennemy(int defense, Arme arme, Equipement equipement, String nom, int niveau){
        super(defense, arme, Attaques.MORSURE, Attaques.GRIFFE, niveau);
        this.tresor = equipement;
        super.SetNom(nom);
    }

    public Equipement getTresor(){
        return tresor;
    }
    
    public int AttaqueMonstre(Player joueur){
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        String trash;
        int choixAttaque = random.nextInt(2);
        int att = ((getNiveau()/2)+1) * (random.nextInt(5)+1);
        if (joueur.getIsPoisonned()){
            joueur.BePoisonned(att/3);
        }
        Attaques attaque = super.getAttaques()[choixAttaque];
        System.out.println(super.getNom()+" lance l'attaque "+attaque+" !");
        trash = input.nextLine();

        switch (attaque){
            case MORSURE:
                int poison = random.nextInt(5);
                if (poison == 0){
                    joueur.setNbToursPoison(5);
                    joueur.BePoisonned(0);
                    System.out.println("Cette attaque vous a empoisonné !");
                    trash = input.nextLine();
                }
                break;
            case GRIFFE:
                int doubleAtt = random.nextInt(5);
                if (doubleAtt == 0){
                    att *= 2;
                    System.out.println("Pris par la vitesse il attaque deux fois !");
                }
                break;
        }
        att -= joueur.getDefense()/5;
        if (att<0){
            att = 0;
        }
        System.out.println(getNom()+" fait une attaque de "+att+" dégats !");
        trash = input.nextLine();
        return att;
    }
}