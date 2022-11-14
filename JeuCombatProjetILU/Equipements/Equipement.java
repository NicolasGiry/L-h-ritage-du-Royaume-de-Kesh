package Equipements;

import Aptitudes.Type;

public enum Equipement{
    CAPUCHE_DE_BRIGANT("capuche de brigant", "casque", Type.NORMAL, 2), CRANE_DOURS("crane d'ours", "casque", Type.NORMAL, 4), 
    CASQUE_DE_SOLDAT("casque de soldat", "casque", Type.NORMAL, 8), CASQUE_PIQUANT("casque piquant", "casque", Type.POISON, 8), 
    CASQUE_TENEBREUX("casque ténébreux", "casque", Type.MAGIQUE, 10),
    TUNIQUE_EN_CUIR("tunique en cuir", "armure", Type.NORMAL,1), PEAU_DOURS("peau d'ours", "armure", Type.NORMAL, 4), 
    PLASTRON_DE_SOLDAT("plastron de soldat", "armure", Type.NORMAL, 10), ARMURE_PIQUANTE("armure piquante", "armure", Type.POISON, 10), 
    ROBE_TENEBREUSE("robe ténébreuse", "armure", Type.MAGIQUE, 12),
    BOUCLIER_EN_BOIS("bouclier en bois", "bouclier", Type.NORMAL, 3), BOUCLIER_EN_METAL("bouclier en métal", "bouclier", Type.NORMAL, 5), 
    BOUCLIER_PIQUANT("bouclier piquant", "bouclier", Type.POISON, 10), BOUCLIER_DE_CHEVALIER("bouclier de chevalier", "bouclier", Type.NORMAL, 10),
    BOUCLIER_TENEBREUX("bouclier ténébreux", "bouclier", Type.MAGIQUE, 15);

    private String nom;
    private String equip;
    private Type type;
    private int defense;

    private Equipement(String nom, String equip, Type type, int defense){
        this.nom = nom;
        this.equip = equip;
        this.type = type;
        this.defense = defense;
    }

    public String getEquip(){
        return equip;
    }

    public int getDefense(){
        return defense;
    }
}