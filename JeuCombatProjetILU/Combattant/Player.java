package Combattant;

import java.util.Scanner;
import java.util.Random;

import Aptitudes.Attaques;
import Aptitudes.Type;
import Objet.Arme;
import Objet.Equipement;
import Objet.Objet;
import Objet.Potions;
import Scenario.Affichage;

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
    private Affichage output = new Affichage();
    
    public Player() {
        super(0, 3, attaquesJoueur, 1, 100);
        for (int i=0; i<15; i++){
            inventaire[i] = null;
        }
    }

    public int getMaxHealth(){
        return super.maxHealth;
    }

    public int getPieces(){
        return pieces;
    }

    public int getXP(){
        return exp;
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
                output.raconterTexte("Vous équipez "+arme);
                input.nextLine();
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
        
        output.didascalie("*Une explosion vous reveille au loin*");
        input.nextLine();
        output.didascalie("*Le receptionniste de l'aubrege dans laquelle vous avez passé la nuit arrive*");
        input.nextLine();
        String[] histoire = {"Le Royaume de Kesh a ete pris d'assault par des monstres ! Le chateau est en ruine !",
                                "Et les monstres s'approchent de nous, il faut vous battre !",
                                "Vous êtes le dernier soldat encore en vie tous les autres ont ete pris par surprise ! "};
        output.raconterTexte(histoire);
        String nom = output.raconterInput(new String[] {"Quel est votre nom ? ", "Entrez votre nom : "});
        output.raconterTexte(new String[] {nom+ " ? Partez au combat avant qu'il ne soit trop tard !"});
        super.SetNom(nom);
    }

    public boolean ChoixCombat(){
        Scanner input = new Scanner(System.in);
        String choixS;
        
        choixS = output.raconterInput(new String[] {"Que voulez vous faire ?","1) COMBAT  2) INFOS 3) INVENTAIRE 4) FUIR :\n"}, this);
        int choix = Integer.parseInt(choixS);
        while (choix<1 || choix>4){
            choixS = output.raconterInput(new String[] {"Erreur ! Que voulez vous faire ?","1) COMBAT  2) INFOS 3) INVENTAIRE 4) FUIR :\n"}, this);
            choix = Integer.parseInt(choixS);
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
                String[] afficherString = new String[3];
                // afficher les options possibles par rapport a l'inventaire
                if (objetInInventaire("arme")){
                    afficherString[i-1] = (i+") Changer d'arme ");
                    choixString[i-1] = "a";
                    i++;
                }if (objetInInventaire("equipement")){
                    afficherString[i-1] = (i+") Changer d'equipement ");
                    choixString[i-1] = "e";
                    i++;
                }if (objetInInventaire("potion")){
                    afficherString[i-1] = (i+") Boire potion");
                    choixString[i-1] = "p";
                    i++;
                }
                if (i>1) {
                    output.texteRetourALaLigne(afficherString);
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

    private int[] indiceObjetInstanceOf(String instance){
        int j=1;
        int[] posObjet = new int[15];
        for (int i=0; i<nbLoot; i++){
            if (instance.equals("Arme") && inventaire[i] instanceof Arme || instance.equals("Equipement") && inventaire[i] instanceof Equipement || instance.equals("Potions") && inventaire[i] instanceof Potions){
                System.out.println(j+") "+inventaire[i]);
                posObjet[j] = i;
                j++;
            }
        }
        // le premier element du tableau stocke j : le nb d'element du type choisi
        posObjet[0] = j;
        return posObjet;
    }

    private void boirePotion() {
        int[] posPotion = indiceObjetInstanceOf("Potions");
        System.out.println("Quelle potion voulez-vous boire ?");
        int c = input.nextInt();
        while (c<1 || c>=posPotion[0]){
            System.out.println("Erreur, que voulez vous faire ?");
            c = input.nextInt();
        }
        input.nextLine();
        inventaire[posPotion[c]].applyEffect(this);
        inventaire[posPotion[c]] = null;
    }

    private void changerEquipement() {
        int[] posEquipement = indiceObjetInstanceOf("Equipement");
        System.out.println("Quel équipement voulez-vous équiper ?");
        int c = input.nextInt();
        while (c<1 || c>=posEquipement[0]){
            System.out.println("Erreur, que voulez vous faire ?");
            c = input.nextInt();
        }
        input.nextLine();
        equiperEquipement(inventaire[posEquipement[c]], posEquipement[c]);
    }

    private void changerArme() {
        int[] posArmes = indiceObjetInstanceOf("Arme");
        System.out.println("Quelle arme voulez-vous équiper ?");
        int c = input.nextInt();
        while (c<1 || c>=posArmes[0]){
            System.out.println("Erreur, que voulez vous faire ?");
            c = input.nextInt();
        }
        input.nextLine();
        equiperArme(inventaire[posArmes[c]], posArmes[c]);

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
        String[] infos = new String[getNbAttaques()];
        for (int i=0; i<getNbAttaques(); i++){
            infos[i] = (getAttaques()[i]+" : "+getAttaques()[i].getDescription());
        }
        output.texteRetourALaLigne(infos, this);
        input.nextLine();
    }

    private boolean fuirCombat(){
        int fuite = random.nextInt(10);
        if (fuite==0){
            output.raconterTexte("Vous avez réussi à fuir", this);
            input.nextLine();
        }else 
        {
            output.raconterTexte("Fuite ratée...", this);
            input.nextLine();
        }
        return fuite==0;
    }

    public Attaques choisirAttaque(){
        String choix = "1";
        Attaques[] attaques = super.getAttaques();
        int nbAttaques = super.getNbAttaques();
        String[] choixAttS = new String[nbAttaques+1];
        choixAttS[0] = "ATTAQUES :";
        for (int i=0; i<nbAttaques; i++){
            choixAttS[i+1] = i+1 + ") " + attaques[i];
        }
        output.texteRetourALaLigne(choixAttS, this);
        System.out.println("\n Choisissez votre attaque ! ( entre 1 et " + nbAttaques + ")");
        choix = input.nextLine();
        int choixNum = Integer.parseInt(choix);
        while (choixNum<1 || choixNum >nbAttaques){
            System.out.println("Choix incorrect ! \nrecommencez :");
            System.out.println("\n Choisissez votre attaque ! ( entre 1 et " + nbAttaques + ")");
            choix = input.nextLine();
            choixNum = Integer.parseInt(choix);
        }
        output.raconterTexte("Attaques chosie :" + attaques[choixNum-1], this);
        input.nextLine();
        return attaques[choixNum-1];
    }

    public int AttaqueJoueur(Attaques attaque, Ennemy ennemy){
        Objet arme = this.arme;
        int att = super.calculerAtt(arme);
        attaque.effetArme(arme, ennemy, att);
        switch (attaque){
            case LANCE_DARME:
                att = attaque.lanceDarme(att);
                break;
            case POINTE:
                att = 999;
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
        output.raconterTexte("Vous faites une attaque de "+att+" dégats !", this);
        input.nextLine();
        return att;
    }

    private void obtenirInfos(Objet objet) {
        int att = objet.getAtt();
        int def = objet.getDefense();
        Type type = objet.getType();
        String[] infos = new String[3];
        if (att>0){
            infos[0] = "attaque : "+att;
        }
        if (def>0){
            infos[1] = ("defense : "+ def);
        }
        infos[2] =("type : "+type);
        output.texteRetourALaLigne(infos, this);
        input.nextLine();
    }

    public void recevoirObjet(Objet objet){
        inventaire[nbLoot] = objet;
        nbLoot ++;
        if (objet instanceof Arme){
            nbLoot --;
            equiperArme(objet, nbLoot);
        }else if (objet instanceof Equipement){
            nbLoot --;
            equiperEquipement(objet, nbLoot);
        }
        
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
                output.raconterTexte("Votre attaque passe à "+ this.arme.getAtt());
                input.nextLine();
            }
            if(reponse.equals("i")){
                obtenirInfos(arme);
                equiperArme(arme, indice);
            }
        }
        if (indice==nbLoot){
            nbLoot++;
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
        output.texteRetourALaLigne(new String[] {"Vous portez déjà "+objetDejaEquipe+" ( def : "+objetDejaEquipe.getDefense()+"; att : "+objetDejaEquipe.getAtt()+" )",
                                    "Voulez vous toujours l'équiper ou obtenir des informations ?"});
        System.out.println("[o/n/i] : ");
        String reponse = input.nextLine();
        while (!(reponse.equals("o")) && !(reponse.equals("n")) && !(reponse.equals("i"))){
            System.out.println("Erreur. Voulez vous l'équiper ? [o/n/i]");
            reponse = input.nextLine();
        }
        return reponse;
    }

    public void equiperEquipement(Objet equipement, int indiceInventaire){
        int indiceEquipement = indiceEquipement(equipement);
        if (this.equipement[indiceEquipement] == null){
            this.equipement[indiceEquipement] = equipement;
            inventaire[indiceInventaire] = null;
        }else {
            String reponse = confirmationEquipementObjet(this.equipement[indiceEquipement]);
            // on enleve le nouvel equipement de l'inventaire et on met l'ancien à la place, on équipe le nouvel equipement
            if (reponse.equals("o")){
                Objet temp = inventaire[indiceInventaire];
                inventaire[indiceInventaire] = this.equipement[indiceEquipement];
                if (indiceInventaire==nbLoot){
                    nbLoot++;
                }
                this.equipement[indiceEquipement] = temp;
                defense = calculerDefense();
                output.texteRetourALaLigne(new String[] {"Vous équipez "+equipement.toString(),"Votre defense passe à "+defense});
                input.nextLine();
            }
            if (reponse.equals("i")){
                obtenirInfos(equipement);
                equiperEquipement(equipement, indiceInventaire);
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
        output.texteRetourALaLigne(new String[] {"Vous gagnez "+pieces+" pieces", "Vous avez "+this.pieces+" pieces"});
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
        output.raconterTexte("Vous gagner "+exp+" de points d'experience !");
        input.nextLine();
        if (this.exp >= lvlUp){
            levelUp();
            this.exp -= lvlUp;
        }
    }

    public void levelUp(){
        niveau ++;
        output.texteRetourALaLigne(new String[] {"Vous gagnez un niveau ! Vous etes au niveau "+niveau+" !",
                                                    "Votre attaque augmente de " + ((niveau*3+2+(niveau*5)/2)-((niveau-1)*3+2+((niveau-1)*5)/2)),
                                                    "Vous recuperer tous vos points de vie !"});
        SetHealth(super.maxHealth);
        input.nextLine();
    }

    public int getNiveau() {
        return niveau;
    }
}