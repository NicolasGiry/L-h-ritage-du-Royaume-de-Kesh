package Objet;

import Aptitudes.Type;
import Combattant.Player;

public interface Objet{
    public int getPrix();
    public int getAtt();
    public Type getType();
    public String getEquip();
    public int getDefense();
    public void applyEffect(Player joueur);
}