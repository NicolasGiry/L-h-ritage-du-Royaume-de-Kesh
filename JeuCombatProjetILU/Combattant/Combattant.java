package Combattant;

import Objet.Arme;
import Objet.Objet;

import java.util.Scanner;

import Aptitudes.Attaques;

public class Combattant {
    private String nom;
    protected int maxHealth;
    private int currentHealth;
    protected int defense;
    private Objet arme;
    private Attaques[] attaques;
    private int nbAttaques;
    protected boolean isPoisonned;
    private int nbToursPoison = 0;
    private boolean isStunt;
    private int nbToursStunt = 0;
    protected int niveau = 1;

    public Combattant(int defense, Objet arme, int nbAttaques, Attaques[] attaques, int niveau, int maxHealth) {
        this.defense = defense;
        this.arme = arme;
        this.nbAttaques = nbAttaques;
        this.attaques = attaques;
        this.niveau = niveau;
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
    }

    public String getNom(){
        return nom;
    }

    public Objet getArme(){
        return arme;
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

    // public void setDefense(int defense){
    //     this.defense = defense;
    // }

    public void SetHealth(int health){
        this.currentHealth = health;
        if (currentHealth>maxHealth){
            currentHealth = maxHealth;
        }
    }

    public void setArme(Objet arme){
        this.arme = arme;
        System.out.println("Vous équipez "+arme);
    }

    public void TakeDamage(int damage){
        Scanner input = new Scanner(System.in);
        String trash;
        this.currentHealth -= damage;
        if (getHealth()>0){
            System.out.println("Il reste "+getHealth()+" pv à "+getNom());
        } else{
            System.out.println(getNom()+" a été tué par le coup !");
        }
        trash = input.nextLine();
    }

    public void BePoisonned(int degat){
        if (degat>0){
            System.out.println("Le poison draine les pv de "+getNom()+"\n"+nbToursPoison+" tours restants avant que l'effet du poison ne se dissipe");
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