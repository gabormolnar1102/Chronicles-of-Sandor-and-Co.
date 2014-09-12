package roleplayinggame.characters;

/**
 *
 * @author Halász 'Trimere' Gábor & Motyka 'Tuszy' Róbert
 */
public abstract class Character {

    //Változó deklaráció
    private int health;
    private int healthCap;
    private int attackRating;
    private int defenseRating;
    private int strength;
    private int dexterity;
    private int constitution;

    //konstruktor, ezt már lehet h kell tudni
    public Character() {
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealthCap() {
        return healthCap;
    }

    public void setHealthCap(int healthCap) {
        this.healthCap = healthCap;
    }

    public int getAttackRating() {
        return attackRating;
    }

    public void setAttackRating(int attackRating) {
        this.attackRating = attackRating;
    }

    public int getDefenseRating() {
        return defenseRating;
    }

    public void setDefenseRating(int defenseRating) {
        this.defenseRating = defenseRating;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }
}
