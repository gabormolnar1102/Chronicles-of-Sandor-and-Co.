package roleplayinggame.characters;

import java.util.Random;

/**
 *
 * @author Halász 'Trimere' Gábor
 */
public class Enemy extends Character {

    //Változó deklaráció
    private String race;
    private int worth;
	private int level;
    private String status;
    private int pictureID;
    private boolean raging;
    Random rnd = new Random();

    public Enemy() {
		this.level = 1;
        setStrength(rnd.nextInt(3)+1);
        setDexterity(rnd.nextInt(3)+1);
        setConstitution(rnd.nextInt(3)+1);
        
        setHealth(30*getConstitution());
        setHealthCap(getHealth());
        
        setAttackRating(getStrength()*5);
        setDefenseRating(getDexterity()*5);
        race = "";
        worth = (getHealth()/2) + getAttackRating() + getDefenseRating();
        status = "Calm";
        raging = false;
        pictureID = rnd.nextInt(5);

    }

    public int getWorth() {
        return worth;
    }

    public void setWorth(int worth) {
        this.worth = worth;
    }
	
	public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getPictureID() {
        return pictureID;
    }

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }

    public boolean isRaging() {
        return raging;
    }

    public void setRaging(boolean raging) {
        this.raging = raging;
    }
}
