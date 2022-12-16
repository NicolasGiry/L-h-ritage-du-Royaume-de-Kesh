package Scenario;

import java.util.Scanner;

import Combattant.Player;

public class Affichage{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BROWN = "\u001B[38;2;205;133;60m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BACKGROUND = "\u001B[48;2;210;180;140m";
    private static Scanner input = new Scanner(System.in);

    public void barreDeVie(Player joueur){
        int maxHealth = joueur.getMaxHealth();
        int health = joueur.getHealth();
        for (int i=0; i<(health/3); i++){
            System.out.print(ANSI_GREEN+"▮");
        }
        for (int j=health/3; j<(maxHealth/3); j++){
            System.out.print("▯");
        }
        System.out.print(" "+health+"/"+maxHealth+" PV");
        System.out.printf("%23s", "lvl "+joueur.getNiveau()+" : "+joueur.getXP()+" xp");
        System.out.printf("%35s %n",joueur.getPieces()+" ₽"+ANSI_RESET);
    }

    public void raconterTexte(String[] textes){
        for (int i=0; i<textes.length; i++){
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                                                     ║");
            System.out.print("║ "+ ANSI_RESET);
            System.out.printf( "%-99s", textes[i]);
            System.out.println(" ║");
            System.out.println("║                                                                                                   v ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝"+ ANSI_RESET);
            input.nextLine();
        }
    }

    public void raconterTexte(String texte){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                                                     ║");
        System.out.print("║ "+ ANSI_RESET);
        System.out.printf("%-99s", texte);
        System.out.println(" ║");
        System.out.println("║                                                                                                   v ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝"+ ANSI_RESET);
    }

    public String raconterInput(String[] textes){
        raconterTexte(textes[0]);
        System.out.print(textes[1]);
        String reponse = input.nextLine();
        return reponse;
    }

    public void didascalie(String texte){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println("╎                                                                                                     ╎");
        System.out.print("╎ "+ ANSI_RESET);
        System.out.printf("%-99s", texte);
        System.out.println(" ╎");
        System.out.println("╎                                                                                                   v ╎");
        System.out.println("-------------------------------------------------------------------------------------------------------"+ ANSI_RESET);
    }

    public void texteRetourALaLigne(String[] textes){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        for (int i=0; i<textes.length; i++){
            if (textes[i]!= null){
                System.out.println("║                                                                                                     ║");
                System.out.print("║ "+ ANSI_RESET);
                System.out.printf("%-99s", textes[i]);
                System.out.println(" ║");
            }
        }
        System.out.println("║                                                                                                   v ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝"+ ANSI_RESET);
    }
    
    public void afficherInventaire(Player joueur){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(" Arme :                                            Casque :                 Armure :                 Bouclier : ");
        System.out.println("╔═══════════════════════╗                         ╔════════════════════════╦════════════════════════╦═══════════════════════╗");
        System.out.print("║ ");
        System.out.printf("%-21s", joueur.getArme());
        System.out.print(" ║");
        System.out.printf("%25s", " ");
        for (int i=0; i<2; i++){
            System.out.print("║ ");
            System.out.printf("%-23s", joueur.getEquipement()[i]);
        }
        System.out.print("║ ");
        System.out.printf("%-22s", joueur.getEquipement()[2]);
        System.out.println("║");
        System.out.println("╚═══════════════════════╝                         ╚════════════════════════╩════════════════════════╩═══════════════════════╝");
        System.out.println(" Inventaire :");
        System.out.println("╔════════════════════════╦════════════════════════╦════════════════════════╦════════════════════════╦═══════════════════════╗");
        for (int i=1; i<=15; i++){
            if (i%5==0 && i!=0){
                System.out.print("║ ");
                System.out.printf("%-22s", joueur.getInventaire()[i-1]);
                System.out.println("║");
                if (i!=15){
                    System.out.println("╠════════════════════════╬════════════════════════╬════════════════════════╬════════════════════════╬═══════════════════════╣");
                }
            }
            else {
                System.out.print("║ ");
                System.out.printf("%-23s", joueur.getInventaire()[i-1]);
            }
        }
        System.out.println("╚════════════════════════╩════════════════════════╩════════════════════════╩════════════════════════╩═══════════════════════╝");
        input.nextLine();
    }



    public void raconterTexte(String texte, Player joueur){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        barreDeVie(joueur);
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                                                     ║");
        System.out.print("║ "+ ANSI_RESET);
        System.out.printf("%-99s", texte);
        System.out.println(" ║");
        System.out.println("║                                                                                                   v ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝"+ ANSI_RESET);
    }

    public String raconterInput(String[] textes, Player joueur){
        raconterTexte(textes[0], joueur);
        System.out.print(textes[1]);
        String reponse = input.nextLine();
        return reponse;
    }

    public void texteRetourALaLigne(String[] textes, Player joueur){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        barreDeVie(joueur);
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        for (int i=0; i<textes.length; i++){
            if (textes[i]!= null){
                System.out.println("║                                                                                                     ║");
                System.out.print("║ "+ ANSI_RESET);
                System.out.printf("%-99s", textes[i]);
                System.out.println(" ║");
            }
        }
        System.out.println("║                                                                                                   v ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝"+ ANSI_RESET);
    }


    public static void main(String[] args){
        Player joueur = new Player();
        Affichage affichage = new Affichage();
        
        joueur.TakeDamage(25);

        affichage.barreDeVie(joueur);

        String[] textes = {"Bonjour jeune guerrier !", "Bienvenue dans le tutoriel. C'est ici que tu vas apprendre toutes les bases", "bien que ce jeu ne soit pas bien compliqué, il requiert quelques informations", "Alors voici le TUTO !"};
        
        
        affichage.texteRetourALaLigne(textes);
        input.nextLine();
        String[] q = {"Quel est votre nom ?", "Entrez votre nom : "};
        affichage.raconterInput(q);
        affichage.didascalie("* Une explosion retentie au loin *");
    }
}