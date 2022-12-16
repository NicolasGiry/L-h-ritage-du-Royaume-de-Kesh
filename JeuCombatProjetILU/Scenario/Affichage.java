package Scenario;

import java.util.Scanner;

import Combattant.Player;

public class Affichage{
    private static Scanner input = new Scanner(System.in);

    public void barreDeVie(Player joueur){
        int maxHealth = joueur.getMaxHealth();
        int health = joueur.getHealth();
        for (int i=0; i<(health/3); i++){
            System.out.print("▮");
        }
        for (int j=health/3; j<(maxHealth/3); j++){
            System.out.print("▯");
        }
        System.out.print(" "+health+"/"+maxHealth+" PV");
        System.out.printf("%23s", "lvl "+joueur.getNiveau()+" : "+joueur.getXP()+" xp");
        System.out.printf("%35s %n",joueur.getPieces()+" ₽");
    }

    public void raconterTexte(String[] textes){
        for (int i=0; i<textes.length; i++){
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                                                     ║");
            System.out.print("║ ");
            System.out.printf("%-99s", textes[i]);
            System.out.println(" ║");
            System.out.println("║                                                                                                   v ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝");
            input.nextLine();
        }
    }

    public void raconterTexte(String texte){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                                                     ║");
        System.out.print("║ ");
        System.out.printf("%-99s", texte);
        System.out.println(" ║");
        System.out.println("║                                                                                                   v ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝");
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
        System.out.print("╎ ");
        System.out.printf("%-99s", texte);
        System.out.println(" ╎");
        System.out.println("╎                                                                                                   v ╎");
        System.out.println("-------------------------------------------------------------------------------------------------------");
    }

    public void texteRetourALaLigne(String[] textes){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        for (int i=0; i<textes.length; i++){
            if (textes[i]!= null){
                System.out.println("║                                                                                                     ║");
                System.out.print("║ ");
                System.out.printf("%-99s", textes[i]);
                System.out.println(" ║");
            }
        }
        System.out.println("║                                                                                                   v ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }
    



    public void raconterTexte(String texte, Player joueur){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        barreDeVie(joueur);
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                                                     ║");
        System.out.print("║ ");
        System.out.printf("%-99s", texte);
        System.out.println(" ║");
        System.out.println("║                                                                                                   v ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝");
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
                System.out.print("║ ");
                System.out.printf("%-99s", textes[i]);
                System.out.println(" ║");
            }
        }
        System.out.println("║                                                                                                   v ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝");
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