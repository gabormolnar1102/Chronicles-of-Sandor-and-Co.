package roleplayinggame.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import roleplayinggame.characters.Enemy;
import roleplayinggame.characters.Player;
import roleplayinggame.gui.Frame;

/**
 *
 * @author Halász 'Trimere' Gábor
 */
public class Model {

    //Változó deklaráció
    private Player player;
    private Enemy enemy;
    private Random rnd;
    private String loadFileName;
    public String[] createHero;
    private Fight fight;
    File file;
    File loadFile;

    //konstruktor, ezt már lehet h kell tudni
    public Model(Frame frame) {
        player = new Player();
        enemy = new Enemy();
        rnd = new Random();
        createHero = new String[2];
        fight = new Fight(player, enemy, this, frame);
    }

    public Fight getFight() {
        return fight;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public String getLoadFileName() {
        return loadFileName;
    }

    public void setLoadFileName(String loadFileName) {
        this.loadFileName = loadFileName;
    }

    // hp töltő pihenés függvény, 20-asával tölti a hp-t
    public void rest() {

        player.setHealth(player.getHealth() + 20);

        if (player.getHealth() >= player.getHealthCap()) {

            player.setHealth(player.getHealthCap());
        }

    }

    public void savePlayer() {

        file = new File("save/" + player.getName() + ".tuszione");

        PrintStream writer = null;

        try {
            writer = new PrintStream(file);
            writer.println(player.getName());
            writer.println(player.getRole());
            writer.println(String.valueOf(player.getHealthCap()));
            writer.println(String.valueOf(player.getActual_exp()));
            writer.println(String.valueOf(player.getLevel()));
            writer.println(String.valueOf(player.getAttackRating()));
            writer.println(String.valueOf(player.getDefenseRating()));
            writer.println(String.valueOf(player.getStrength()));
            writer.println(String.valueOf(player.getDexterity()));
            writer.println(String.valueOf(player.getConstitution()));

            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: unable to read file " + file);
            e.printStackTrace();
        }
        if (writer != null) {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }

    }

    public void loadPlayer() {

        System.out.println(loadFileName);

        loadFile = new File("save/" + loadFileName + ".tuszione");

        Scanner scanner;

        try {
            scanner = new Scanner(loadFile);

            player.setName(scanner.next());
            player.setRole(scanner.next());
            player.setHealth(scanner.nextInt());
            player.setActual_exp(scanner.nextInt());
            player.setLevel(scanner.nextInt());
            player.setAttackRating(scanner.nextInt());
            player.setDefenseRating(scanner.nextInt());
            player.setStrength(scanner.nextInt());
            player.setDexterity(scanner.nextInt());
            player.setConstitution(scanner.nextInt());

            scanner.close();

        } catch (FileNotFoundException e) {

            System.out.println("ERROR: unable to read file " + loadFile);
            e.printStackTrace();

        }

        player.setHealthCap(player.getHealth());
        player.setLevelCapExp(150 + (player.getLevel() - 1) * 120);
    }

    public void createHero() {

        player.setName(player.getName());
        player.setRole(player.getRole());
        player.setActual_exp(0);
        player.setLevel(1);
        player.setStrength(rnd.nextInt(3) + 1);
        player.setDexterity(rnd.nextInt(3) + 1);
        player.setConstitution(rnd.nextInt(3) + 1);

        player.setHealth(30 * player.getConstitution());
        player.setHealthCap(player.getHealth());

        player.setAttackRating(player.getStrength() * 5);
        player.setDefenseRating(player.getDexterity() * 5);
    }
}
