package Aptitudes;

import java.util.Random;
import java.util.Scanner;

import Combattant.Ennemy;
import Combattant.Player;
import Objet.Objet;
import Scenario.Affichage;

public enum Attaques {
    LANCE_DARME("lancé d'arme", "lance l'arme du joueur en direction de l'ennemie. Cette action a une possibilité d'échouer mais vous pouvez aussi viser dans le mille et doubler votre attaque", Type.NORMAL), 
    POINTE("pointe", "attaque simple réussissant à coup sur", Type.NORMAL), 
    MORSURE("morsure", "le monstre plante ses crocs dans votre chair, vous avez une chance sur cinq d'être empoisonné", Type.POISON), 
    GRIFFE("griffe", "le monstre vous griffe avec ses ongles acérés, il peut enchainer deux griffes à la suite", Type.RAPIDE), 
    LANCE_FLAMME("lance flamme", "le monstre crache un jet de flamme qui vous brûle intensémment, au fil des jets de flammes, les dégats augmentent", Type.MAGIQUE), 
    BOULE_DE_FEU("boule de feu", "vous lancez une boule de feu par magie, très efficace contre les monstres plantes", Type.MAGIQUE), 
    BOULE_DE_GLACE("boule de glace", "vous lancez une boule de glace par magie, très efficace contre les monstres feu", Type.MAGIQUE), 
    ECLAIR("éclair", "vous lancez un éclair par magie, très efficace contre les monstres glace", Type.MAGIQUE), 
    RACINE("souffle", "des racines aparraissent du sol et enlassent l'adversaire, l'ennemi peut être bloqué pendant un tour", Type.MAGIQUE), 
    ATTAQUE_TENEBREUSE("attaque ténébreuse", "une aura maléfique née de votre colère se déverse sur l'ennemi, qui sait ce qu'il lui arrivera...", Type.MAGIQUE),
    SOIN("soin", "vous utilisez 5 mana pour guérir vous blessures ( guéri 25 PV )", Type.MAGIQUE);

    private String nom;
    private String description;
    private Type type;
    private Random random = new Random();
    private Scanner input = new Scanner(System.in);
    private Affichage output = new Affichage();

    private Attaques(String nom, String description, Type type){
        this.nom = nom;
        this.description = description;
        this.type = type;
    }

    @Override
	public String toString() {
		return nom;
	}

    public Type getType(){
        return type;
    }

    public String getDescription(){
        return description;
    }

    public void effetArme(Objet arme, Ennemy ennemy, int att){
        switch (arme.getType()){
            case POISON:
                if (ennemy.getIsPoisonned()){
                    ennemy.BePoisonned(att/3);
                }else {
                    int poison = random.nextInt(3);
                    if (poison == 0){
                        ennemy.setNbToursPoison(5);
                        ennemy.BePoisonned(0);
                        output.raconterTexte("l'épée a empoisonné "+ennemy.getNom()+" !");
                        input.nextLine();
                    }
                }
                break;
            case RAPIDE:
                break;
            case LOURD:
                break;
            case MAGIQUE:
                break;
        }
    }

    public int lanceDarme(int att){
        int coeff = random.nextInt(5);
        if (coeff == 0){
            output.raconterTexte("Cible ratée...");
            input.nextLine();
            att = 0;
        }else  if (coeff==1){
            output.raconterTexte("En plein dans le mille !");
            input.nextLine();
            att *= 2;
        }
        return att;
    }

    public int soin(int att, Player joueur){
        joueur.mana -=5;
        joueur.SetHealth(joueur.getHealth()+25);
        output.texteRetourALaLigne(new String[] {"Vous gagnez 25 PV !",
            "Vous avez "+joueur.getHealth()+" PV",
            "Il vous reste "+joueur.mana+" mana"});
        input.nextLine();
        return 0;
    }

    public int morsure(int att, Player joueur){
        int poison = random.nextInt(5);
        if (poison == 0){
            joueur.setNbToursPoison(5);
            joueur.BePoisonned(0);
            output.raconterTexte("Cette attaque vous a empoisonné !");
            input.nextLine();
        }
        return att;
    }
    
    public int griffe(int att){
        int doubleAtt = random.nextInt(5);
        if (doubleAtt == 0){
            att *= 2;
            output.raconterTexte("Pris par la vitesse il attaque deux fois !");
            input.nextLine();
        }
        return att;
    }
}