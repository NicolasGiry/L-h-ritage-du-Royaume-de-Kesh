package Aptitudes;

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
}