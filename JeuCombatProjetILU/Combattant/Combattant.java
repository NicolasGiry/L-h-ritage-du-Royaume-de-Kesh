package Combattant;

import Equipements.Arme;

import java.util.Scanner;

import Aptitudes.Attaques;

public class Combattant {
    private String nom;
    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private int defense;
    private Arme arme;
    private Attaques[] attaques = new Attaques[6];
    private int nbAttaques = 2;
    private boolean isPoisonned;
    private int nbToursPoison = 0;
    private boolean isStunt;
    private int nbToursStunt = 0;

    public Combattant(int defense, Arme arme, Attaques attaque1, Attaques attaque2) {
        this.defense = defense;
        this.arme = arme;
        this.attaques[0] = attaque1;
        this.attaques[1] = attaque2;
    }

    public String getNom(){
        return nom;
    }

    public Arme getArme(){
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

    public int getDefense(){
        return defense;
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

    public void setDefense(int defense){
        this.defense = defense;
    }

    public void SetHealth(int health){
        this.currentHealth = health;
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
            TakeDamage(degat);
        }
        nbToursPoison --;
        isPoisonned = nbToursPoison>0;
    }

    public int BeStunt(int nbTours){
        isStunt = nbTours>0;
        return nbTours - 1;
    }
}