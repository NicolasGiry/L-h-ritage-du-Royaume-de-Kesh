package Scenario;

import Combattant.Player;
import Objet.Arme;
import Objet.Equipement;
import Combattant.Ennemy;

import java.util.Scanner;

import Aptitudes.Attaques;
import Bonus.Loot;
import Bonus.Marchand;
import Objet.Potions;

public class Histoire {

    public static Player intro(){
        Player player = new Player();
        player.ChoisirNom();
        return player;
    }

    public static void GameOver(){
        System.out.println("GAMEOVER ! Vous n'avez plus aucun PV !");
    }

    public static void Loot(Player joueur, Ennemy monstre){
        joueur.gagnerXP(monstre.getTresor().getXp());
        monstre.getTresor().ouvrirCoffre(joueur);
    }

    public static void combat(Player joueur, Ennemy monstre){
        Scanner input = new Scanner(System.in);
        if (joueur.getHealth()>0) {
            System.out.println("Vous croisez le chemin de "+monstre.getNom()+" !");
            input.nextLine();
            while (joueur.getHealth()>0 && monstre.getHealth()>0 && joueur.ChoixCombat()){
                if (!joueur.getFuiteRatee()){
                    Attaques attaque = joueur.choisirAttaque();
                    monstre.TakeDamage(joueur.AttaqueJoueur(attaque, monstre));
                }
                if (monstre.getHealth()>0){
                    joueur.TakeDamage(monstre.AttaqueMonstre(joueur));
                }
            } if (joueur.getHealth() <= 0){
                GameOver();
            }else if (monstre.getHealth() <=0){
                Loot(joueur, monstre);
            }
        }
    }

    public static void main(String[] args) {
        Player joueur = intro();
        Loot coffre1 = new Loot(500, Arme.SABRE, 99);
        Loot coffre2 = new Loot(5, Equipement.BOUCLIER_EN_METAL, 17);
        Loot coffre3 = new Loot(15, Equipement.BOUCLIER_DE_CHEVALIER, 36);
        Loot coffre4 = new Loot(25, Arme.BAGUETTE_MAGIQUE, 84);
        Ennemy monstre1 = new Ennemy(1, coffre1, "Armeus", 1);
        Ennemy monstre2 = new Ennemy(1, coffre2, "Tyron", 1);
        Ennemy monstre3 = new Ennemy(1, coffre3, "Anours", 2);
        Ennemy monstre4 = new Ennemy(5, coffre4, "Nanaconda", 2);
        Ennemy monstre5 = new Ennemy(1, coffre1, "Armeus", 2);
        Marchand marchand = new Marchand(Equipement.PLASTRON_DE_SOLDAT, Arme.EPEE_EMPOISONNEE, Potions.VIE);

        combat(joueur, monstre1);
        combat(joueur, monstre2);

        marchand.rencontrerMarchand(joueur);

        combat(joueur, monstre3);
        combat(joueur, monstre4);
        combat(joueur, monstre5);
    }
}