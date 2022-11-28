package Objet;

import Aptitudes.Type;

public enum Arme implements Objet{
    EPEE_EN_BOIS("épée en bois", 5, Type.NORMAL, 5), 
    EPEE_EN_FER("épée en fer", 10, Type.NORMAL, 10), 
    HACHE("hache", 10, Type.LOURD, 15), 
    COUTEAU("couteau", 7, Type.RAPIDE, 12), 
    DAGUE("dague", 8, Type.RAPIDE, 13), 
    SABRE("sabre", 12, Type.NORMAL, 20), 
    HACHE_DOUBLE_LAME("hache à double lame", 15, Type.LOURD, 25), 
    EPEE_EMPOISONNEE("épée empoisonnée", 10, Type.POISON, 20), 
    SCEPTRE_MAGIQUE("sceptre magique", 10, Type.MAGIQUE, 25), 
    BAGUETTE_MAGIQUE("baguette magique", 8, Type.MAGIQUE, 20);

    private String nom;
    private int att;
    private Type type;
    private int prix;

    private Arme(String nom, int att, Type type, int prix) {
        this.nom = nom;
        this.att = att;
        this.type = type;
        this.prix = prix;
    }

    @Override
	public String toString() {
		return nom;
	}

    public int getAtt(){
        return att;
    }

    public Type getType(){
        return type;
    }

    public int getPrix(){
        return prix;
    }

    public String getEquip(){
        return "arme";
    }

    public int getDefense(){
        return 0;
    }
}