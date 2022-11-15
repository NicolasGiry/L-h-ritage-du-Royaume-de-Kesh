package Combattant;

import java.util.Scanner;

import Aptitudes.Attaques;
import Aptitudes.Type;
import Bonus.Loot;
import Bonus.Potions;
import Equipements.Arme;
import Equipements.Equipement;
import java.util.Random;
import Combattant.Ennemy;

public class Player extends Combattant{
    private static Attaques[] attaquesJoueur= {Attaques.POINTE, Attaques.LANCE_DARME, Attaques.SOIN};
    private Equipement[] equipement = new Equipement[3];
    public int mana = 50;
    private Equipement[] tresors = new Equipement[10];
    private int nbLoot = 0;
    private Potions[] stockPotions = new Potions[5];
    private int pieces = 10;
    private int exp = 0;
    private int nbPotions = 0;
    
    public Player() {
        super(0,Arme.EPEE_EN_BOIS, 3, attaquesJoueur, 1);
        
        
        //equipement[0] = Equipement.CAPUCHE_DE_BRIGANT; equipement[1] = Equipement.TUNIQUE_EN_CUIR;
    }

    public int getPieces(){
        return pieces;
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

        int att = (arme.getAtt() + random.nextInt(5)+2)*(getNiveau());
        String trash;

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
                att=50000000;
                break;
            case SOIN:
                mana -=5;
                SetHealth(getHealth()+25);
                System.out.println("Vous gagnez 25 PV !");
                System.out.println("Vous avez "+getHealth()+" PV");
                System.out.println("Il vous reste "+mana+" mana");
                trash = input.nextLine();
                return 0;
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

    public void recevoirEquipement(Equipement equipement){
        Scanner input = new Scanner(System.in);
        String equip;

        tresors[nbLoot] = equipement;
        nbLoot++;
        System.out.println("Voulez vous équiper "+equipement+" ( def :"+equipement.getDefense()+" ) ? [o/n]");
        equip = input.nextLine();
        while (!(equip.equals("o")) && !(equip.equals("n"))){
            System.out.println("Erreur. Voulez vous l'équiper ? [o/n]");
            equip = input.nextLine();
        }
        if (equip.equals("o")){
            equiperEquipement(equipement);
        }
    }

    public void recevoirArme(Arme arme){
        Scanner input = new Scanner(System.in);
        String equip;

        System.out.println("Voulez vous équiper "+arme+" ( att : "+arme.getAtt()+" ) ? [o/n]");
        equip = input.nextLine();
        while (!(equip.equals("o")) && !(equip.equals("n"))){
            System.out.println("Erreur. Voulez vous l'équiper ? [o/n]");
            equip = input.nextLine();
        }
        if (equip.equals("o")){
            equiperArme(arme);
        }
    }

    public void recevoirPotion(Potions potion){
        Scanner input = new Scanner(System.in);

        if (nbPotions < 5){
            stockPotions[nbPotions] = potion;
            nbPotions++;
        } else{
            System.out.println("Vous n'avez plus de places ! Voulez vous abandonner cette potion ou en jeter une autre [1/2]");
            int choix = input.nextInt();
            if (choix == 2){
                afficherPotions();
                System.out.println("Quelle potion voulez vous jeter ? [1/2/3/4/5]");
                choix = input.nextInt();
                stockPotions[choix-1] = potion;
            }
        }
    }

    public void equiperArme(Arme arme){
        Scanner input = new Scanner(System.in);
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

    public void equiperEquipement(Equipement equipement){
        Scanner input = new Scanner(System.in);
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
            tresors[nbLoot] = null;
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
                Equipement temp = tresors[nbLoot];
                tresors[nbLoot] = this.equipement[indice];
                nbLoot ++;
                this.equipement[indice] = temp;
            }
        }
        setDefense(0);
        for (int i=0; i<3; i++){
            if (this.equipement[i] != null){
                setDefense(getDefense() + this.equipement[i].getDefense());
            }
        }
        System.out.println("Vous équipez "+equipement.toString()+"\nVotre defense passe à "+getDefense());
        String trash = input.nextLine();
    }

    public void afficherInventaire(){
        for (int i=0; i<nbLoot; i++){
            System.out.print("["+tresors[i]+"]");
            if (i%3 == 0){
                System.out.println();
            }
        }
    }

    public void afficherEquipements(){
        for (int i=0; i<3; i++){
            System.out.print("["+equipement[i]+"] ");
        }System.out.println("["+getArme()+"]");
    }

    public void afficherPotions(){
        for (int i=0; i<nbPotions; i++){
            System.out.print("["+stockPotions[i]+"] ");
        }System.out.println();
    }

    public void gagnerPieces(int pieces){
        Scanner input = new Scanner(System.in);
        String trash;
        this.pieces += pieces;
        System.out.println("Vous gagnez "+pieces+" pieces");
        System.out.println("Vous avez "+this.pieces+" pieces");
        trash = input.nextLine();
    }

    public void perdrePieces(int pieces){
        this.pieces -= pieces;
    }

    public void gagnerXP(int exp){
        this.exp += exp;
        System.out.println("Vous gagner "+exp+" de points d'experience !");
    }

}