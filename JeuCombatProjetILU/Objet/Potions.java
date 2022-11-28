package Objet;

import Aptitudes.Type;

public enum Potions implements Objet{
    VIE("potion de vie", 30), 
    FORCE("potion de force", 45), 
    DEFENSE("potion de defense", 45), 
    MAGIE("potion de magie", 50);

    private String nom;
    private int prix;

    private Potions(String nom, int prix){
        this.nom = nom;
        this.prix = prix;
    }

    public String getNom(){
        return nom;
    }

    public int getPrix(){
        return prix;
    }

    public int getAtt(){
        return 0;
    }

    public Type getType(){
        return Type.MAGIQUE;
    }

    public String getEquip(){
        return "potion";
    }

    public int getDefense(){
        if (this == DEFENSE){
            return 50;
        }
        return 0;
    }
}