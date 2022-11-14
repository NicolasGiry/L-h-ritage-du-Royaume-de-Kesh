package Combattant;

import java.util.Scanner;

import Aptitudes.Attaques;
import Aptitudes.Type;
import Bonus.Loot;
import Equipements.Arme;
import Equipements.Equipement;
import java.util.Random;
import Combattant.Ennemy;

public class Player extends Combattant{
    private Equipement[] equipement = new Equipement[3];
    public int mana = 50;
    private Equipement[] tresors = new Equipement[10];
    private int nbLoot = 0;
    
    public Player() {
        super(10,Arme.EPEE_EN_BOIS, Attaques.POINTE, Attaques.LANCE_DARME);
        equipement[0] = Equipement.CAPUCHE_DE_BRIGANT; equipement[1] = Equipement.TUNIQUE_EN_CUIR;
    }

    public void ChoisirNom(){
        Scanner input = new Scanner(System.in); 
        String trash;
        System.out.println("Bonjour ! Je suis desolee, vous venez de vous reveiller mais le temps presse !");
        trash = input.nextLine();
        System.out.println("Le Royaume de Kesh a ete pris d'assault par des monstres ! Le chateau est en ruine !");
        trash = input.nextLine();
        System.out.println("Et les monstres s'approchent de nous, il faut vous battre !");
        trash = input.nextLine();
        System.out.println("Vous êtes le dernier soldat encore en vie tous les autres ont ete pris par surprise ! ");
        trash = input.nextLine();
        System.out.println("Quel est votre nom ? ");
        System.out.print("Entrez votre nom :"); String nom = input.nextLine();
        System.out.println(nom + " ? Partez au combat avant qu'il ne soit trop tard !");
        trash = input.nextLine();
        super.SetNom(nom);
    }

    public boolean ChoixAction(){
        Scanner input = new Scanner(System.in);
        int choix;

        System.out.println("Que voulez vous faire ?");
        System.out.println("1) COMBAT  2) INFOS  3) FUIR");
        choix = input.nextInt();
        while (choix<1 || choix>3){
            System.out.println("Erreur, que voulez vous faire ?");
            System.out.println("1) COMBAT  2) INFOS  3) FUIR");
            choix = input.nextInt();
        }

        switch (choix){
            case 1:
                return true;
            case 2:
                infos();
                ChoixAction();
                return true;
            case 3:
                return !fuir();
            default:
                return true;
            }
    }

    public void infos(){
        Scanner input = new Scanner(System.in);
        String trash;
        for (int i=0; i<getNbAttaques(); i++){
            System.out.println(getAttaques()[i]+" : "+getAttaques()[i].getDescription());
        }
        trash = input.nextLine();
    }

    public boolean fuir(){
        Scanner input = new Scanner(System.in);
        String trash;
        Random random = new Random();
        int fuite = random.nextInt(10);
        if (fuite==0){
            System.out.println("Vous avez réussi à fuir");
            trash = input.nextLine();
        }else 
        {
            System.out.println("Fuite ratée...");
            trash = input.nextLine();
        }
        return fuite==0;
    }

    public Attaques choisirAttaque(){
        Scanner input = new Scanner(System.in); 
        String choix = "1";
        Attaques[] attaques = super.getAttaques();
        int nbAttaques = super.getNbAttaques();
        System.out.println("ATTAQUES :");
        for (int i=0; i<nbAttaques; i++){
            System.out.println(i+1 + ") " + attaques[i]);
        }
        System.out.println("\n Choisissez votre attaque ! ( entre 1 et " + nbAttaques + ")");
        choix = input.nextLine();
        int choixNum = Integer.parseInt(choix);
        while (choixNum<1 || choixNum >nbAttaques){
            System.out.println("Choix incorrect ! \nrecommencez :");
            System.out.println("\n Choisissez votre attaque ! ( entre 1 et " + nbAttaques + ")");
            choix = input.nextLine();
            choixNum = Integer.parseInt(choix);
        }
        System.out.println("Attaques chosie :" + attaques[choixNum-1]);
        return attaques[choixNum-1];
    }

    public int AttaqueJoueur(Attaques attaque, Ennemy ennemy){
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        Arme arme = super.getArme();
        int att = arme.getAtt() + random.nextInt(5)+2;;
        int nbToursPoison = 5;
        String trash;

        if (ennemy.getIsPoisonned()){
            ennemy.BePoisonned(att/3);
        }

        switch (attaque){
            case LANCE_DARME:
                int coeff = random.nextInt(5);
                if (coeff == 0){
                    System.out.println("Cible ratée...");
                    att = 0;
                }else  if (coeff==1){
                    System.out.println("En plein dans le mille !");
                    att *= 2;
                }
                break;
            case POINTE:
                att = 100;
                break;
            case BOULE_DE_FEU:
                mana -= 5;
                break;
            case BOULE_DE_GLACE:
                mana -= 5;
                break;
            case ECLAIR:
                mana -= 5;
                break;
            case RACINE:
                int piege = random.nextInt(5);
                if (piege == 0){
                    ennemy.BeStunt(1);
                }
                mana -= 5;
                break;
            case ATTAQUE_TENEBREUSE:
                mana -= 10;
                att *= random.nextInt(4)+1;
                break;
        }
        att -= ennemy.getDefense()/5;
        if (att<0){
            att = 0;
        }
        System.out.println("Vous faites une attaque de "+att+" dégats !");
        trash = input.nextLine();
        return att;
    }

    public void recevoirLoot(Equipement equipement){
        Scanner input = new Scanner(System.in);
        tresors[nbLoot] = equipement;
        String equip;
        System.out.println("Voulez vous l'équiper ? o/n");
        equip = input.nextLine();
        while (!(equip.equals("o")) && !(equip.equals("n"))){
            System.out.println("Erreur. Voulez vous l'équiper ? o/n");
            equip = input.nextLine();
        }
        if (equip.equals("o")){
            equiper(equipement);
        }
    }

    public void equiper(Equipement equipement){
        switch (equipement.getEquip()){
            case "casque":
                if (this.equipement[0] == null){
                    this.equipement[0] = equipement;
                }
                break;
            case "armure":
                if (this.equipement[1] == null){
                    this.equipement[1] = equipement;
                }
                break;
            case "bouclier":
                if (this.equipement[2] == null){
                    this.equipement[2] = equipement;
                }
                break;
            }
        setDefense(0);
        for (int i=0; i<3; i++){
            if (this.equipement[i] != null){
                setDefense(getDefense() + this.equipement[i].getDefense());
            }
        }
        System.out.println("Vous équipez "+equipement.toString()+"\nVotre defense passe à "+getDefense());
    }

}