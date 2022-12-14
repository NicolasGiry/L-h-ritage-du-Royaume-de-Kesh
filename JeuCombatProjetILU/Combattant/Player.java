package Combattant;

import java.util.Scanner;
import java.util.Random;

import Aptitudes.Attaques;
import Aptitudes.Type;
import Objet.Arme;
import Objet.Equipement;
import Objet.Objet;
import Objet.Potions;

public class Player extends Combattant{
    private static Attaques[] attaquesJoueur= {Attaques.POINTE, Attaques.LANCE_DARME, Attaques.SOIN};
    private Objet[] inventaire = new Objet[15];
    private Objet[] equipement = new Equipement[3];
    private Objet arme = Arme.EPEE_EN_BOIS;
    public int mana = 50;
    private int nbLoot = 0;
    private int pieces = 10;
    private int exp = 0;
    private boolean fuiteRatee;
    private Scanner input = new Scanner(System.in);
    private Random random = new Random();
    
    public Player() {
        super(0, 3, attaquesJoueur, 1, 100);
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

    public void setArme(Objet arme){
        for (int i=0; i<=nbLoot; i++){
            if (inventaire[i]==null){
                System.out.println("Vous équipez "+arme);
                inventaire[i]=this.arme;
                this.arme = arme;
                if (i==nbLoot){
                    nbLoot++;
                }
                break;
            }
        }
    }

    public void ChoisirNom(){
        System.out.println("*Une explosion vous reveille au loin*");
        input.nextLine();
        System.out.println("*Le receptionniste de l'aubrege dans laquelle vous avez passé la nuit arrive*");
        input.nextLine();
        System.out.println("Le Royaume de Kesh a ete pris d'assault par des monstres ! Le chateau est en ruine !");
        input.nextLine();
        System.out.println("Et les monstres s'approchent de nous, il faut vous battre !");
        input.nextLine();
        System.out.println("Vous êtes le dernier soldat encore en vie tous les autres ont ete pris par surprise ! ");
        input.nextLine();
        System.out.println("Quel est votre nom ? ");
        System.out.print("Entrez votre nom :"); String nom = input.nextLine();
        System.out.println(nom + " ? Partez au combat avant qu'il ne soit trop tard !");
        input.nextLine();
        super.SetNom(nom);
    }

    public boolean ChoixCombat(){
        Scanner input = new Scanner(System.in);
        int choix;

        System.out.println("Que voulez vous faire ?");
        System.out.println("1) COMBAT  2) INFOS 3) INVENTAIRE 4) FUIR");
        choix = input.nextInt();
        while (choix<1 || choix>4){
            System.out.println("Erreur, que voulez vous faire ?");
            System.out.println("1) COMBAT  2) INFOS  3) INVENTAIRE 4) FUIR");
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
                afficherInventaire();
                int i = 1;
                String[] choixString = new String[3];
                // afficher les options possibles par rapport a l'inventaire
                if (objetInInventaire("arme")){
                    System.out.print(i+") Changer d'arme ");
                    choixString[i-1] = "a";
                    i++;
                }if (objetInInventaire("equipement")){
                    System.out.print(i+") Changer d'equipement ");
                    choixString[i-1] = "e";
                    i++;
                }if (objetInInventaire("potion")){
                    System.out.println(i+") Boire potion");
                    choixString[i-1] = "p";
                }
                System.out.println();
                if (i>1) {
                	System.out.println("Que voulez vous faire ? [0] pour annuler");
	                int c = input.nextInt();
	                while (c<0 || c>i){
	                    System.out.println("Erreur, que voulez vous faire ? [0] pour annuler");
	                    c = input.nextInt();
	                }
	                if (c!=0 && choixString[c-1].equals("a")){
	                    changerArme();
	                }if (c!=0 && choixString[c-1].equals("e")){
	                    changerEquipement();
	                }if (c!=0 && choixString[c-1
                    ].equals("p")){
	                    boirePotion();
	                }
                }
                ChoixCombat();
                return true;
            case 4:
                fuiteRatee = !fuirCombat();
                return fuiteRatee;
            default:
                return true;
            }
    }

    private void boirePotion() {
    }

    private void changerEquipement() {
    }

    private void changerArme() {
        int j=1;
        int[] posArmes = new int[15];
        for (int i=0; i<nbLoot; i++){
            if (inventaire[i] instanceof Arme){
                System.out.println(j+") "+inventaire[i]);
                posArmes[j-1] = i;
                j++;
            }
        }
        System.out.println("Quelle arme voulez-vous équiper ?");
        int c = input.nextInt();
        while (c<1 || c>=j){
            System.out.println("Erreur, que voulez vous faire ?");
            c = input.nextInt();
        }
        input.nextLine();
        equiperArme(inventaire[posArmes[c-1]], posArmes[c-1]);

    }

    private boolean objetInInventaire(String objet) {
        boolean arme=false, equipement=false, potion=false;

        for (int i=0;i<nbLoot;i++){
            if (inventaire[i] instanceof Arme){
                arme = true;
            }if (inventaire[i] instanceof Equipement){
                equipement = true;
            }if (inventaire[i] instanceof Potions){
                potion = true;
            }
        }
        switch (objet){
            case "arme":
                return arme;
            case "equipement":
                return equipement;
            case "potion":
                return potion;
            default:
                return false;
        }
    }

    private void infosCombat(){
        for (int i=0; i<getNbAttaques(); i++){
            System.out.println(getAttaques()[i]+" : "+getAttaques()[i].getDescription());
        }
        input.nextLine();
    }

    private boolean fuirCombat(){
        int fuite = random.nextInt(10);
        if (fuite==0){
            System.out.println("Vous avez réussi à fuir");
            input.nextLine();
        }else 
        {
            System.out.println("Fuite ratée...");
            input.nextLine();
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
        Objet arme = this.arme;
        int att = super.calculerAtt(arme);
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
            default:
                break;
        }
        att -= ennemy.defense/5;
        if (att<0){
            att = 0;
        }
        System.out.println("Vous faites une attaque de "+att+" dégats !");
        input.nextLine();
        return att;
    }

    public void recevoirObjet(Objet objet){
        inventaire[nbLoot] = objet;
        nbLoot ++;
        if (objet instanceof Arme){
            equiperArme(objet);
        }else if (objet instanceof Equipement){
            equiperEquipement(objet);
        }
    }

    public void equiperArme(Objet arme){
        if (this.arme == null){
            setArme(arme);
            nbLoot--;
            inventaire[nbLoot] = null;
        }else {
            String reponse = confirmationEquipementObjet(this.arme);
            if (reponse.equals("o")){
                nbLoot--;
                inventaire[nbLoot] = null;
                setArme(arme);
                System.out.println("Votre attaque passe à "+ this.arme.getAtt());
            }
            if(reponse.equals("i")){
                obtenirInfos(arme);
                equiperArme(arme);
            }
        }
        
    }

    private void obtenirInfos(Objet objet) {
        int att = objet.getAtt();
        int def = objet.getDefense();
        Type type = objet.getType();
        if (att>0){
            System.out.println("attaque : "+att);
        }
        if (def>0){
            System.out.println("defense : "+ def);
        }
        System.out.println("type : "+type);
        input.nextLine();
    }

    public void equiperArme(Objet arme, int indice){
        if (this.arme == null){
            setArme(arme);
            inventaire[indice] = null;
        }else {
            String reponse = confirmationEquipementObjet(this.arme);
            if (reponse.equals("o")){
                inventaire[indice] = null;
                setArme(arme);
                System.out.println("Votre attaque passe à "+ this.arme.getAtt());
            }
            if(reponse.equals("i")){
                obtenirInfos(arme);
                equiperArme(arme);
            }
        }
        
    }

    private int indiceEquipement(Objet equipement){
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
        return indice;
    }

    public int calculerDefense(){
        defense = 0;
        for (int i=0; i<3; i++){
            if (this.equipement[i] != null){
               defense +=this.equipement[i].getDefense();
            }
        }
        return defense;
    }

    public String confirmationEquipementObjet(Objet objetDejaEquipe ){
        System.out.println("Vous portez déjà "+objetDejaEquipe+" ( def : "+objetDejaEquipe.getDefense()+"; att : "+objetDejaEquipe.getAtt()+" )");
        System.out.println("Voulez vous toujours l'équiper ou obtenir des informations ? [o/n/i]");
        String reponse = input.nextLine();
        while (!(reponse.equals("o")) && !(reponse.equals("n")) && !(reponse.equals("i"))){
            System.out.println("Erreur. Voulez vous l'équiper ? [o/n/i]");
            reponse = input.nextLine();
        }
        return reponse;
    }

    public void equiperEquipement(Objet equipement){
        int indice = indiceEquipement(equipement);
        if (this.equipement[indice] == null){
            this.equipement[indice] = equipement;
            nbLoot--;
            inventaire[nbLoot] = null;
        }else {
            String reponse = confirmationEquipementObjet(this.equipement[indice]);
            // on enleve le nouvel equipement de l'inventaire et on met l'ancien à la place, on équipe le nouvel equipement
            if (reponse.equals("o")){
                nbLoot --;
                Objet temp = inventaire[nbLoot];
                inventaire[nbLoot] = this.equipement[indice];
                nbLoot ++;
                this.equipement[indice] = temp;
                defense = calculerDefense();
                System.out.println("Vous équipez "+equipement.toString()+"\nVotre defense passe à "+defense);
                input.nextLine();
            }
            if (reponse.equals("i")){
                obtenirInfos(equipement);
                equiperEquipement(equipement);
            }
        }
        
    }

    public void afficherInventaire(){
        Scanner input = new Scanner(System.in);
        System.out.println("Arme : "+this.arme);
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
        input.nextLine();
    }

    public void gagnerPieces(int pieces){
        this.pieces += pieces;
        System.out.println("Vous gagnez "+pieces+" pieces");
        System.out.println("Vous avez "+this.pieces+" pieces");
        input.nextLine();
    }

    public void perdrePieces(int pieces){
        this.pieces -= pieces;
        System.out.println("-"+pieces+" ₽");
        input.nextLine();
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
        System.out.println("Votre attaque augmente de " + ((niveau*3+2+(niveau*5)/2)-((niveau-1)*3+2+((niveau-1)*5)/2)));
        SetHealth(super.maxHealth);
        System.out.println("Vous recuperer tous vos points de vie !");
        input.nextLine();
    }
}