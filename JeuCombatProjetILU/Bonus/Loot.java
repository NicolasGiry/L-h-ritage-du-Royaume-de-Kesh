package Bonus;

public enum Loot{
    TRESOR("tresor"), CASQUE("equipement"), ARMURE("equipement"), BOUCLIER("equipement"), ARME("arme");

    private String type;

    private Loot(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

}