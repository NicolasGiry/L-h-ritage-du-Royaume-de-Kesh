package Equipements;

import Aptitudes.Type;

public enum Arme{
    EPEE_EN_BOIS("épée en bois", 5, Type.NORMAL), EPEE_EN_FER("épée en fer", 10, Type.NORMAL), HACHE("hache", 10, Type.LOURD), 
    COUTEAU("couteau", 7, Type.RAPIDE), DAGUE("dague", 8, Type.RAPIDE), SABRE("sabre", 12, Type.NORMAL), 
    HACHE_DOUBLE_LAME("hache à double lame", 15, Type.LOURD), EPEE_EMPOISONNEE("épée empoisonnée", 10, Type.POISON), 
    SCEPTRE_MAGIQUE("sceptre magique", 10, Type.MAGIQUE), BAGUETTE_MAGIQUE("baguette magique", 8, Type.MAGIQUE);

    private String nom;
    private int att;
    private Type type;

    private Arme(String nom, int att, Type type) {
        this.nom = nom;
        this.att = att;
        this.type = type;
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
}