package Combattant;

import java.util.Scanner;

import Aptitudes.Attaques;
import Aptitudes.Type;
import Bonus.Loot;
import Objet.Potions;
import Objet.Arme;
import Objet.Equipement;
import java.util.Random;
import Combattant.Ennemy;
import Objet.Objet;

public class Player extends Combattant{
    private static Attaques[] attaquesJoueur= {Attaques.POINTE, Attaques.LANCE_DARME, Attaques.SOIN};
    private Objet[] inventaire = new Objet[15];
    private Objet[] equipement = new Equipement[3];
    public int mana = 50;
    //private Equipement[] tresors = new Equipement[10];
    private int nbLoot = 0;
    private Potions[] stockPotions = new Potions[5];
    private int pieces = 10;
    private int exp = 0;
    private int nbPotions = 0;
    private boolean fuiteRatee;

    private Scanner input = new Scanner(System.in);
    private Random random = new Random();
    private String trash;
    
    public Player() {
        super(0,Arme.EPEE_EN_BOIS, 3, attaquesJoueur, 1, 100);
        for (int i=0; i<15; i++){
            inventaire[i] = null;
        }
    }

    public int getPieces(){
        return pieces;
    }

    public boolean getFuiteRatee(){
        if (fuiteRatee){
            fuiteRatee = false;
            return true;
        }
        return fuiteRatee;
    }

    public void ChoisirNom(){
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

    public boolean ChoixCombat(){
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
                infosCombat();
                ChoixCombat();
                return true;
            case 3:
                fuiteRatee = !fuirCombat();
                return fuiteRatee;
            default:
                return true;
            }
    }

    private void infosCombat(){
        for (int i=0; i<getNbAttaques(); i++){
            System.out.println(getAttaques()[i]+" : "+getAttaques()[i].getDescription());
        }
        trash = input.nextLine();
    }

    private boolean fuirCombat(){
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
        Objet arme = super.getArme();
        int att = (arme.getAtt() + random.nextInt(5)+2)*(niveau/2==0?1:niveau/2);
        attaque.effetArme(arme, ennemy, att);
        switch (attaque){
            case LANCE_DARME:
                att = attaque.lanceDarme(att);
            case POINTE:
                break;
            case SOIN:
                return attaque.soin(att, this);
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
        att -= ennemy.defense/5;
        if (att<0){
            att = 0;
        }
        System.out.println("Vous faites une attaque de "+att+" dégats !");
        trash = input.nextLine();
        return att;
    }

    public void recevoirObjet(Objet objet){
        String equip;
        
        System.out.println("Voulez vous équiper "+objet+" ? [o/n]");
        equip = input.nextLine();
        while (!(equip.equals("o")) && !(equip.equals("n"))){
                     System.out.println("Erreur. Voulez vous l'équiper ? [o/n]");
                     equip = input.nextLine();
                 }
        if (equip.equals("o")){
            equiperObjet(objet);
        }
        afficherInventaire();
    }

    private void equiperObjet(Objet objet){
        if (objet instanceof Arme){
            equiperArme(objet);
        } else {
            inventaire[nbLoot] = objet;
            nbLoot++;
            if (objet instanceof Equipement){
                equiperEquipement(objet);
            }
        }
    }

    public void equiperArme(Objet arme){
        String equip;
        if (getArme() == null){
            setArme(arme);
        }else {
            System.out.println("Vous portez deja une arme : "+getArme()+" ( att : "+getArme().getAtt()+" )");
            System.out.println("Voulez-vous abondonner cette arme et equiper la nouvelle ? [o/n]");
            equip = input.nextLine();
            while (!(equip.equals("o")) && !(equip.equals("n"))){
                System.out.println("Erreur. Voulez vous l'équiper ? [o/n]");
                equip = input.nextLine();
            }
            if (equip.equals("o")){
                setArme(arme);
            }
        }
        System.out.println("Votre attaque passe à "+ getArme().getAtt());
    }

    public void equiperEquipement(Objet equipement){
        int indice = 0;
        switch (equipement.getEquip()){
            case "casque":
                indice = 0;
                break;
            case "armure":
                indice = 1;
                break;
            case "bouclier":
                indice = 2;
                break;
            }

        if (this.equipement[indice] == null){
            this.equipement[indice] = equipement;
            nbLoot--;
            inventaire[nbLoot] = null;
        }else {
            System.out.println("Vous portez déjà "+this.equipement[indice]+" ( def : "+this.equipement[indice].getDefense()+" )");
            System.out.println("Voulez vous toujours l'équiper ? [o/n]");
            String reponse = input.nextLine();

            while (!(reponse.equals("o")) && !(reponse.equals("n"))){
                System.out.println("Erreur. Voulez vous l'équiper ? [o/n]");
                reponse = input.nextLine();
            }

            // on enleve le nouvel equipement de l'inventaire et on met l'ancien à la place, on équipe le nouvel equipement
            if (reponse.equals("o")){
                nbLoot --;
                Objet temp = inventaire[nbLoot];
                inventaire[nbLoot] = this.equipement[indice];
                nbLoot ++;
                this.equipement[indice] = temp;
            }
        }
        defense = 0;
        for (int i=0; i<3; i++){
            if (this.equipement[i] != null){
               defense +=this.equipement[i].getDefense();
            }
        }
        System.out.println("Vous équipez "+equipement.toString()+"\nVotre defense passe à "+defense);
        String trash = input.nextLine();
    }

    public void afficherInventaire(){
        System.out.println("Arme : "+getArme());
        System.out.print("\nEquipement : ");
        for (int i=0; i<3; i++){
            System.out.print("["+equipement[i]+"] ");
        }
        System.out.print("\n\nInventaire : ");
        for (int i=0; i<15; i++){
            if (i%5 == 0 && i!=0){
                System.out.print("\n             ");
            }
            System.out.print("["+inventaire[i]+"]");
            
        }System.out.println();
    }

    public void gagnerPieces(int pieces){
        this.pieces += pieces;
        System.out.println("Vous gagnez "+pieces+" pieces");
        System.out.println("Vous avez "+this.pieces+" pieces");
        trash = input.nextLine();
    }

    public void perdrePieces(int pieces){
        this.pieces -= pieces;
        System.out.println("-"+pieces+" ₽");
        trash = input.nextLine();
    }

    public void gagnerXP(int exp){
        int lvlUp = niveau*100 ;
        this.exp += exp;
        System.out.println("Vous gagner "+exp+" de points d'experience !");
        if (this.exp >= lvlUp){
            levelUp();
            this.exp -= lvlUp;
        }

    }

    public void levelUp(){
        niveau ++;
        System.out.println("Vous gagnez un niveau ! Vous etes au niveau "+niveau+" !");
    }
}