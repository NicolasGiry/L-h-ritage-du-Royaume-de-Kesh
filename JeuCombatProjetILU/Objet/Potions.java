package Objet;

import java.util.Scanner;

import Aptitudes.Type;
import Combattant.Player;
import Scenario.Affichage;

public enum Potions implements Objet{
    VIE("potion de vie", 30), 
    FORCE("potion de force", 45), 
    DEFENSE("potion de defense", 45), 
    MAGIE("potion de magie", 50);

    private String nom;
    private int prix;
    private Scanner input = new Scanner(System.in);
    private Affichage output = new Affichage();

    private Potions(String nom, int prix){
        this.nom = nom;
        this.prix = prix;
    }

    public String getNom(){
        return nom;
    }

    public int getPrix(){
        return prix;
    }

    public int getAtt(){
        return 0;
    }

    public Type getType(){
        return Type.MAGIQUE;
    }

    public String getEquip(){
        return "potion";
    }

    public int getDefense(){
        if (this == DEFENSE){
            return 50;
        }
        return 0;
    }

    public void applyEffect(Player joueur){
        switch (this){
            case VIE:
                joueur.SetHealth(joueur.getHealth()+50);
                output.texteRetourALaLigne(new String[] {"Une energie fabuleuse vous rechauffe le corps",
                    "vous gagnez 50 PV ! Vous avez "+ joueur.getHealth()+" PV"});
                input.nextLine();
                break;
            case FORCE:
                break;
            case DEFENSE:
                break;
            case MAGIE:
                break;
        }
    }
}