package Combattant;

import Objet.Objet;
import Scenario.Affichage;

import java.util.Random;
import java.util.Scanner;

import Aptitudes.Attaques;

public class Combattant {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    private Random random = new Random();
    private String nom;
    protected int maxHealth;
    private int currentHealth;
    protected int defense;
    private Attaques[] attaques;
    private int nbAttaques;
    protected boolean isPoisonned;
    private int nbToursPoison = 0;
    private boolean isStunt;
    private int nbToursStunt = 0;
    protected int niveau = 1;
    private Affichage output = new Affichage();
    private Scanner input = new Scanner(System.in);

    public Combattant(int defense, int nbAttaques, Attaques[] attaques, int niveau, int maxHealth) {
        this.defense = defense;
        this.nbAttaques = nbAttaques;
        this.attaques = attaques;
        this.niveau = niveau;
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
    }

    public String getNom(){
        return nom;
    }

    public Attaques[] getAttaques(){
        return attaques;
    }

    public int getNbAttaques(){
        return nbAttaques;
    }

    public int getHealth(){
        return currentHealth;
    }

    public boolean getIsPoisonned(){
        return isPoisonned;
    }

    public void setNbToursPoison(int nbTours){
        nbToursPoison = nbTours;
    }

    public void SetNom(String nom){
        this.nom = nom;
    }

    public void SetHealth(int health){
        this.currentHealth = health;
        if (currentHealth>maxHealth){
            currentHealth = maxHealth;
        }
    }

    public int calculerAtt(){
        return 2+niveau*3+ random.nextInt(100)%((niveau*5)/2);
    }
    public int calculerAtt(Objet arme){
        return 2+niveau*3+ random.nextInt(1000)%((niveau*5)/2)+arme.getAtt();
    }

    public void TakeDamage(int damage){
        this.currentHealth -= damage;
        if (getHealth()>0){
            output.raconterTexte("Il reste "+getHealth()+" pv à "+getNom());
        } else{
            output.raconterTexte(getNom()+" a été tué par le coup !");
        }
        input.nextLine();
    }

    public void TakeDamage(int damage, Player joueur){
        this.currentHealth -= damage;
        if (getHealth()>0){
            output.raconterTexte("Il reste "+getHealth()+" pv à "+getNom(), joueur);
        } else{
            output.raconterTexte(getNom()+" a été tué par le coup !", joueur);
        }
        input.nextLine();
    }

    public void BePoisonned(int degat){
        if (degat>0){
            output.texteRetourALaLigne( new String[] {"Le poison draine les pv de "+getNom(),
            nbToursPoison+" tours restants avant que l'effet du poison ne se dissipe"});
            input.nextLine();
            TakeDamage(degat+1);
        }
        nbToursPoison --;
        isPoisonned = nbToursPoison>0;
    }

    public int BeStunt(int nbTours){
        isStunt = nbTours>0;
        return nbTours - 1;
    }
}