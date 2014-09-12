package roleplayinggame.characters;

import java.util.Random;

/**
 *
 * @author Halász 'Trimere' Gábor
 */

public class Player extends Character {

    //Változó deklaráció
    private String name;
    private int level;
    private int actual_exp;
    private String role;
    private int levelCapExp = 100;
    Random rnd = new Random();
    
    //konstruktor, ezt már lehet h kell tudni
    public Player() {

        name = "Sándor";
        role = "AlphaFighter";
        actual_exp = 0;
        level = 1;
        
        setStrength(rnd.nextInt(3)+1);
        setDexterity(rnd.nextInt(3)+1);
        setConstitution(rnd.nextInt(3)+1);
        
        setHealth(30*getConstitution());
        setHealthCap(getHealth());
        
        setAttackRating(getStrength()*5);
        setDefenseRating(getDexterity()*5);
    }
    
    public int getLevelCapExp() {
        return levelCapExp;
    }

    public void setLevelCapExp(int levelCapExp) {
        this.levelCapExp = levelCapExp;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getRole() {
        return role;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getActual_exp() {
        return actual_exp;
    }

    public void setActual_exp(int actual_exp) {
        this.actual_exp = actual_exp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    

}
