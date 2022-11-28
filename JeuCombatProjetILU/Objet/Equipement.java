package Objet;

import Aptitudes.Type;

public enum Equipement implements Objet{
    CAPUCHE_DE_BRIGANT("capuche de brigant", "casque", Type.NORMAL, 2, 5), 
    CRANE_DOURS("crane d'ours", "casque", Type.NORMAL, 4, 7), 
    CASQUE_DE_SOLDAT("casque de soldat", "casque", Type.NORMAL, 8, 10), 
    CASQUE_PIQUANT("casque piquant", "casque", Type.POISON, 8, 15), 
    CASQUE_TENEBREUX("casque ténébreux", "casque", Type.MAGIQUE, 10, 50),
    TUNIQUE_EN_CUIR("tunique en cuir", "armure", Type.NORMAL, 1, 5), 
    PEAU_DOURS("peau d'ours", "armure", Type.NORMAL, 4, 8), 
    PLASTRON_DE_SOLDAT("plastron de soldat", "armure", Type.NORMAL, 10, 12), 
    ARMURE_PIQUANTE("armure piquante", "armure", Type.POISON, 10, 20), 
    ROBE_TENEBREUSE("robe ténébreuse", "armure", Type.MAGIQUE, 15, 75),
    BOUCLIER_EN_BOIS("bouclier en bois", "bouclier", Type.NORMAL, 3, 2), 
    BOUCLIER_EN_METAL("bouclier en métal", "bouclier", Type.NORMAL, 5, 7), 
    BOUCLIER_PIQUANT("bouclier piquant", "bouclier", Type.POISON, 10, 15), 
    BOUCLIER_DE_CHEVALIER("bouclier de chevalier", "bouclier", Type.NORMAL, 10, 10),
    BOUCLIER_TENEBREUX("bouclier ténébreux", "bouclier", Type.MAGIQUE, 15, 50);

    private String nom;
    private String equip;
    private Type type;
    private int defense;
    private int prix;

    private Equipement(String nom, String equip, Type type, int defense, int prix){
        this.nom = nom;
        this.equip = equip;
        this.type = type;
        this.defense = defense;
        this.prix = prix;
    }

    @Override
    public String toString(){
        return nom;
    }

    public String getEquip(){
        return equip;
    }

    public int getDefense(){
        return defense;
    }
    
    public int getPrix(){
        return prix;
    }

    public int getAtt(){
        return 0;
    }

    public Type getType(){
        return type;
    }
}