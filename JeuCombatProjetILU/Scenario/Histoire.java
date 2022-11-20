package Scenario;

import Combattant.Player;
import Equipements.Arme;
import Equipements.Equipement;
import Combattant.Ennemy;

import java.util.Scanner;

import Aptitudes.Attaques;
import Bonus.Loot;
import Bonus.Marchand;
import Bonus.Potions;

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
        monstre.getTresor().ouvrirCoffre(joueur);
    }

    public static void combat(Player joueur, Ennemy monstre){
        String trash;
        Scanner input = new Scanner(System.in);

        System.out.println("Vous croisez le chemin de "+monstre.getNom()+" !");
        trash = input.nextLine();
        while (joueur.getHealth()>0 && monstre.getHealth()>0 && joueur.ChoixCombat()){
            Attaques attaque = joueur.choisirAttaque();
            monstre.TakeDamage(joueur.AttaqueJoueur(attaque, monstre));
            if (monstre.getHealth()>0){
                joueur.TakeDamage(monstre.AttaqueMonstre(joueur));
            }
        } if (joueur.getHealth() <= 0){
            GameOver();
        }else if (monstre.getHealth() <=0){
            Loot(joueur, monstre);
        }
    }

    public static void main(String[] args) {
        Player joueur = intro();
        Loot coffre1 = new Loot(5, Equipement.BOUCLIER_EN_BOIS, 15);
        Loot coffre2 = new Loot(5, Equipement.BOUCLIER_EN_METAL, 17);
        Loot coffre3 = new Loot(15, Equipement.CRANE_DOURS, 36);
        Loot coffre = new Loot(25, Arme.HACHE, 0);
        Ennemy monstre1 = new Ennemy(1, null, coffre1, "Armeus", 1);
        Ennemy monstre2 = new Ennemy(1, null, coffre2, "Tyron", 1);
        Ennemy monstre3 = new Ennemy(1, null, coffre3, "Anours", 5);
        Marchand marchand = new Marchand(Equipement.PLASTRON_DE_SOLDAT, Arme.EPEE_EMPOISONNEE, Potions.VIE);

        combat(joueur, monstre1);
        combat(joueur, monstre2);

        marchand.rencontrerMarchand(joueur);

        combat(joueur, monstre3);
        
        
    }
}