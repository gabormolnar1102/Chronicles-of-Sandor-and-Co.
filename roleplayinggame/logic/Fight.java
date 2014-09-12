package roleplayinggame.logic;

import java.util.Random;
import roleplayinggame.characters.Enemy;
import roleplayinggame.characters.Player;
import roleplayinggame.gui.Frame;

public class Fight {

    private Model model;
    private Player player;
    private Enemy enemy;
    private boolean isEnemyDead;
    private boolean playerTurn;
    private Frame frame;
    private Random rnd;
    private boolean isCritical;
    private int dice;

    public Fight(Player player, Enemy enemy, Model model, Frame frame) {
        this.frame = frame;
        this.player = player;
        this.enemy = enemy;
        this.model = model;

        rnd = new Random();
        playerTurn = true;
        isEnemyDead = false;
        isCritical = false;
    }

    public boolean isEnemyDead() {
        return isEnemyDead;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void simulateFight() {
        frame.getEffectPText().setText(null);
        frame.getEffectEText().setText(null);
        
        //ez fejezi ki a kockadobást
        dice = rnd.nextInt(20) + 1;
        if (dice == 20) {
            isCritical = true;
        } else if (dice >= 18 && enemy.isRaging()) {
            isCritical = true;
        }
        //Itt kezdődik a játékos köre
        if (playerTurn) {

            //Kiírja a dobás eredményét
            //TODO: a grafikai felületen megjeleníteni a konzol helyett
            frame.getEffectPText().append("PLAYER Attack Throw (1d20): " + dice + "\n");

            //itt számolja h sikerült-e a támadás
            // player támadóérték+dobás>= enemy védőérték akkor hit
            if ((player.getAttackRating() + dice) >= enemy.getDefenseRating()) {

                dice = rnd.nextInt(6) + 1;
                if (isCritical) {
                    frame.getEffectPText().append("PLAYER Damage Throw (1d6): " + dice + "\n");
                    //kiírja a sebzést
                    frame.getEffectPText().append("PLAYER Damage(CRITICAL): " + (player.getAttackRating() + dice) * 2 + "\n");

                    //csökkenti az enemy hp-ját
                    enemy.setHealth(enemy.getHealth() - (player.getAttackRating() + dice) * 2);
                    isCritical = false;
                } else {
                    frame.getEffectPText().append("PLAYER Damage Throw (1d6): " + dice + "\n");
                    //kiírja a sebzést
                    frame.getEffectPText().append("PLAYER Damage: " + (player.getAttackRating() + dice) + "\n");

                    //csökkenti az enemy hp-ját
                    enemy.setHealth(enemy.getHealth() - (player.getAttackRating() + dice));
                }
            }
            //itt zárul a player köre
            playerTurn = false;
            //ha enemy hp-ja 20% alá csökken, akkor raging státuszba lép
            if (enemy.getHealth() <= enemy.getHealthCap() * 0.2f) {
                enemy.setRaging(true);
                enemy.setStatus("Raging");
                enemy.setDefenseRating(enemy.getDefenseRating() * (2 / 3));
            }
            //ha meghal az enemy akkor módósítja az exp-t az enemy értékével
            if (enemy.getHealth() <= 0) {
                frame.getEffectEText().append("DEAD\n");
                player.setActual_exp(player.getActual_exp() + enemy.getWorth());
                isEnemyDead = true;
                playerTurn = true;
            }
        }

        //Enemy kör kezdete, szinte minden ugyanúgy mint a player körében
        if (!playerTurn && !isEnemyDead) {

            dice = rnd.nextInt(20) + 1;
            if (dice == 20) {
                isCritical = true;
            }

            frame.getEffectEText().append("ENEMY Attack Throw (1d20): " + dice + "\n");

            if ((enemy.getAttackRating() + dice) >= player.getDefenseRating()) {

                dice = rnd.nextInt(6) + 1;
                if (enemy.isRaging()) {
                    dice += rnd.nextInt(6) + 1;
                }
                if (isCritical) {
                    if (enemy.isRaging()) {
                        frame.getEffectEText().append("ENEMY Damage Throw (2d6 - raging): " + dice + "\n");
                    } else {
                        frame.getEffectEText().append("ENEMY Damage Throw (1d6): " + dice + "\n");
                    }
                    frame.getEffectEText().append("ENEMY Damage(CRITICAL): " + (enemy.getAttackRating() + dice) * 2 + "\n");
                    player.setHealth(player.getHealth() - (enemy.getAttackRating() + dice) * 2);
                    isCritical = false;
                } else {
                    if (enemy.isRaging()) {
                        frame.getEffectEText().append("ENEMY Damage Throw (2d6 - raging): " + dice + "\n");
                    } else {
                        frame.getEffectEText().append("ENEMY Damage Throw (1d6): " + dice + "\n");
                    }
                    frame.getEffectEText().append("ENEMY Damage: " + (enemy.getAttackRating() + dice) + "\n");
                    player.setHealth(player.getHealth() - (enemy.getAttackRating() + dice));
                }
                if (player.getHealth() <= 0) {

                    player.setHealth(0);
                    model.setPlayer(new Player());
                    this.player = model.getPlayer();
                }
            }

            //Enemy kör vége
            playerTurn = true;
        }

        //Player szintlépés
        if (player.getActual_exp() >= player.getLevelCapExp()) {

            player.setLevel(player.getLevel() + 1);
            player.setActual_exp(player.getActual_exp() % player.getLevelCapExp());
            player.setLevelCapExp(150 + (player.getLevel() - 1) * 120);
            player.setHealthCap(player.getHealthCap() + player.getConstitution() * 8);
            player.setHealth(player.getHealthCap());
            player.setAttackRating(player.getAttackRating() + player.getStrength());
            player.setDefenseRating(player.getDefenseRating() + player.getDexterity());
        }

    }

    public void createEnemy() {
        isEnemyDead = false;
        if (player.getLevel() == 1) {
            enemy.setLevel(rnd.nextInt(2) + 1);
        } else {
            enemy.setLevel(rnd.nextInt(3) + player.getLevel() - 1);
        }


        enemy.setRaging(false);
        enemy.setStatus("Calm");
        enemy.setStrength(rnd.nextInt(3) + 1);
        enemy.setDexterity(rnd.nextInt(3) + 1);
        enemy.setConstitution(rnd.nextInt(3) + 1);

        enemy.setHealthCap(30 * enemy.getConstitution());

        enemy.setAttackRating(enemy.getStrength() * 5);
        enemy.setDefenseRating(enemy.getDexterity() * 5);

        if (enemy.getLevel() >= 2) {
            for (int i = 2; i <= enemy.getLevel(); i++) {
                if (i % 3 == 0) {
                    int temp = rnd.nextInt(3);
                    switch (temp) {
                        case 0:
                            enemy.setStrength(enemy.getStrength() + 1);
                            break;
                        case 1:
                            enemy.setDexterity(enemy.getDexterity() + 1);
                            break;
                        case 2:
                            enemy.setConstitution(enemy.getConstitution() + 1);
                            break;
                    }
                }

                enemy.setHealthCap(enemy.getHealthCap() + enemy.getConstitution() * 8);

                enemy.setAttackRating(enemy.getAttackRating() + enemy.getStrength());
                enemy.setDefenseRating(enemy.getDefenseRating() + enemy.getDexterity());
            }
        }
        enemy.setWorth(enemy.getAttackRating() + enemy.getDefenseRating() + (enemy.getHealthCap() / 2));
        enemy.setHealth(enemy.getHealthCap());
        enemy.setPictureID(rnd.nextInt(6));
    }
}
