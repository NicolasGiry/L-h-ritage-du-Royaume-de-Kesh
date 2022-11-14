package Scenario;

import Combattant.Player;
import Equipements.Arme;
import Equipements.Equipement;
import Combattant.Ennemy;

import java.util.Scanner;

import Aptitudes.Attaques;
import Bonus.Loot;

public class Histoire {

    public static Player intro(){
        Player player = new Player();
        player.ChoisirNom();
        return player;
    }

    public static void GameOver(){
        System.out.println("GAMEOVER ! Vous n'avez plus aucun PV !");
    }

    public static void Loot(Player joueur, Equipement equipement){
        if (equipement != null){
            System.out.println("Le monstre laisse tomber un "+equipement.toString());
            joueur.recevoirLoot(equipement);
        }
    }

    public static void combat(Player joueur, Ennemy monstre){
        String trash;
        Scanner input = new Scanner(System.in);

        System.out.println("Vous croisez le chemin de "+monstre.getNom()+" !");
        trash = input.nextLine();
        while (joueur.getHealth()>0 && monstre.getHealth()>0 && joueur.ChoixAction()){
            Attaques attaque = joueur.choisirAttaque();
            monstre.TakeDamage(joueur.AttaqueJoueur(attaque, monstre));
            if (monstre.getHealth()>0){
                joueur.TakeDamage(monstre.AttaqueMonstre(joueur));
            }
        } if (joueur.getHealth() <= 0){
            GameOver();
        }else if (monstre.getHealth() <=0){
            Loot(joueur, monstre.getTresor());
        }
    }

    public static void main(String[] args) {
        Player joueur = intro();
        Ennemy monstre1 = new Ennemy(1, null, Equipement.BOUCLIER_EN_BOIS, "Armeus", 1);
        Ennemy monstre2 = new Ennemy(10, null, null, "Tyron", 50);
        combat(joueur, monstre1);
        combat(joueur, monstre2);
    }
}