package roleplayinggame.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import roleplayinggame.characters.Enemy;
import roleplayinggame.logic.Model;
import roleplayinggame.characters.Player;

/**
 *
 * @author Halász 'Trimere' Gábor & Motyka 'Tuszy' Róbert
 */
public final class Frame extends JFrame {

    String version = "0.140501";
    Container contentPane;
    //Player variables
    private JTextField nameTextPlayer;
    private JTextField healthTextPlayer;
    private JTextField levelTextPlayer;
    private JTextField expTextPlayer;
    private JTextField damageTextPlayer;
    private JTextField attackTextPlayer;
    private JTextField defenseTextPlayer;
    private JTextField strenghtTextPlayer;
    private JTextField dexterityTextPlayer;
    private JTextField constitutionTextPlayer;
    private JLabel pictureLabelPlayer;
    private JPanel panelPlayer;
    //Enemy variables
    private JTextField raceTextEnemy;
    private JTextField healthTextEnemy;
    private JTextField damageTextEnemy;
    private JTextField statusTextEnemy;
    private JTextField worthTextEnemy;
    private JTextField attackTextEnemy;
    private JTextField defenseTextEnemy;
    private JTextField strenghtTextEnemy;
    private JTextField dexterityTextEnemy;
    private JTextField constitutionTextEnemy;
    private JLabel pictureLabelEnemy;
    private JPanel panelEnemy;
    //Other variables
    private JPanel effectPanel;
    private JTextArea effectEText;
    private JTextArea effectPText;
    DefaultComboBoxModel cbModel = new DefaultComboBoxModel();
    private Player player;
    private Enemy enemy;
    private Model model;
    private Clip clip;
    private boolean isMute;

    //konstruktor
    public Frame() throws Exception {
        
        isMute = false;
        File file = new File("music/music.wav");
        clip = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        contentPane = this.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        model = new Model(this);
        effectPText = new JTextArea();
        effectEText = new JTextArea();
        player = model.getPlayer();
        enemy = model.getEnemy();

        //attributes of frame
        setTitle("Chronicles of Sándor & Co. (v" + version + ")");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            setIconImage(ImageIO.read(new File("images/icon.gif")));
        } catch (IOException ioe) {
        }
        setResizable(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds(dim.width / 2 - 400, dim.height / 2 - 350, 800, 600);

        setJMenuBar(createJMenuBar());
        createPlayerPanel();
        createEnemyPanel();
        createEffectPanel();

        this.pack();
    }

    public JTextArea getEffectPText() {
        return effectPText;
    }

    public JTextArea getEffectEText() {
        return effectEText;
    }

    //Creates the menubar
    private JMenuBar createJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        jMenuBar.add(gameMenu);
        JMenuItem create = new JMenuItem("Create Hero");
        create.addActionListener(createActionListener);
        gameMenu.add(create);

        JMenuItem save = new JMenuItem("Save Hero");
        save.addActionListener(saveActionListener);
        gameMenu.add(save);

        JMenuItem load = new JMenuItem("Load Hero");
        load.addActionListener(loadActionListener);
        gameMenu.add(load);

        JMenuItem mute = new JMenuItem("Stop/start music");
        mute.addActionListener(muteActionListener);
        gameMenu.add(mute);

        JMenuItem exit = new JMenuItem("Exit game");
        exit.addActionListener(exitActionListener);
        gameMenu.add(exit);

        JButton rest = new JButton("Rest");
        rest.addActionListener(restActionListener);
        jMenuBar.add(rest);

        JButton fight = new JButton("Fight");
        jMenuBar.add(fight);
        fight.addActionListener(fightActionListener);
        
        JButton help = new JButton("Help");
        jMenuBar.add(help);
        fight.addActionListener(helpActionListener);

        return jMenuBar;
    }

    //Creates the panel for the player, displaying all it's attributes
    private void createPlayerPanel() {

        effectPText.setText(null);
        //Player
        panelPlayer = new JPanel();

        panelPlayer.setLayout(new BoxLayout(panelPlayer, BoxLayout.Y_AXIS));

        //textfields for the attributes
        nameTextPlayer = new JTextField(player.getName() + ", the " + player.getRole());
        healthTextPlayer = new JTextField("Health: " + Integer.toString(player.getHealth()));
        damageTextPlayer = new JTextField("Damage: Attack + 1d6");
        levelTextPlayer = new JTextField("Level: " + Integer.toString(player.getLevel()));
        expTextPlayer = new JTextField(player.getActual_exp() + "/" + player.getLevelCapExp());
        strenghtTextPlayer = new JTextField("Strenght: " + Integer.toString(player.getStrength()));
        dexterityTextPlayer = new JTextField("Dexterity: " + Integer.toString(player.getDexterity()));
        constitutionTextPlayer = new JTextField("Constitution: " + Integer.toString(player.getConstitution()));
        attackTextPlayer = new JTextField("Attack: " + Integer.toString(player.getAttackRating()));
        defenseTextPlayer = new JTextField("Defense: " + Integer.toString(player.getDefenseRating()));

        pictureLabelPlayer = new JLabel();

        nameTextPlayer.setEditable(false);
        healthTextPlayer.setEditable(false);
        damageTextPlayer.setEditable(false);
        levelTextPlayer.setEditable(false);
        expTextPlayer.setEditable(false);
        attackTextPlayer.setEditable(false);
        defenseTextPlayer.setEditable(false);
        strenghtTextPlayer.setEditable(false);
        dexterityTextPlayer.setEditable(false);
        constitutionTextPlayer.setEditable(false);

        //Player name
        panelPlayer.add(nameTextPlayer);
        contentPane.add(panelPlayer);
        nameTextPlayer.setPreferredSize(new Dimension(100, 20));
        nameTextPlayer.setBackground(Color.white);
        //Player hp
        panelPlayer.add(healthTextPlayer);
        contentPane.add(panelPlayer);
        healthTextPlayer.setPreferredSize(new Dimension(100, 20));
        healthTextPlayer.setBackground(Color.red);

        //Player Damage
        panelPlayer.add(damageTextPlayer);
        contentPane.add(panelPlayer);
        damageTextPlayer.setPreferredSize(new Dimension(100, 20));
        damageTextPlayer.setBackground(Color.cyan);

        //Player XP
        panelPlayer.add(expTextPlayer);
        contentPane.add(panelPlayer);
        expTextPlayer.setPreferredSize(new Dimension(100, 20));
        expTextPlayer.setBackground(Color.green);

        //Player Level
        panelPlayer.add(levelTextPlayer);
        contentPane.add(panelPlayer);
        levelTextPlayer.setPreferredSize(new Dimension(100, 20));
        levelTextPlayer.setBackground(Color.yellow);

        panelPlayer.add(strenghtTextPlayer);
        contentPane.add(panelPlayer);
        strenghtTextPlayer.setPreferredSize(new Dimension(100, 20));
        strenghtTextPlayer.setBackground(Color.black);
        strenghtTextPlayer.setForeground(Color.white);

        panelPlayer.add(dexterityTextPlayer);
        contentPane.add(panelPlayer);
        dexterityTextPlayer.setPreferredSize(new Dimension(100, 20));
        dexterityTextPlayer.setBackground(Color.black);
        dexterityTextPlayer.setForeground(Color.white);

        panelPlayer.add(constitutionTextPlayer);
        contentPane.add(panelPlayer);
        constitutionTextPlayer.setPreferredSize(new Dimension(100, 20));
        constitutionTextPlayer.setBackground(Color.black);
        constitutionTextPlayer.setForeground(Color.white);

        //Player Attack
        panelPlayer.add(attackTextPlayer);
        contentPane.add(panelPlayer);
        attackTextPlayer.setPreferredSize(new Dimension(100, 20));
        attackTextPlayer.setBackground(new Color(0.2f, 0.2f, 0.5f));
        attackTextPlayer.setForeground(new Color(0.6f, 0.6f, 0.3f));

        // Player Defense
        panelPlayer.add(defenseTextPlayer);
        contentPane.add(panelPlayer);
        defenseTextPlayer.setPreferredSize(new Dimension(100, 20));
        defenseTextPlayer.setBackground(Color.ORANGE);

        //Player pic
        ImageIcon iconPlayer = new ImageIcon("images/player1.jpg");
        pictureLabelPlayer.setIcon(iconPlayer);
        pictureLabelPlayer.setAlignmentX(CENTER_ALIGNMENT);
        panelPlayer.add(pictureLabelPlayer);
        panelPlayer.setBackground(Color.lightGray);
        contentPane.add(panelPlayer);

    }

    //Enemy panel, same as player
    private void createEnemyPanel() {

        effectPText.setText(null);

        //Enemy
        panelEnemy = new JPanel();

        panelEnemy.setLayout(new BoxLayout(panelEnemy, BoxLayout.Y_AXIS));

        raceTextEnemy = new JTextField("");
        healthTextEnemy = new JTextField("Health: " + Integer.toString(enemy.getHealth()));
        damageTextEnemy = new JTextField("Damage: Attack + 1d6");
        statusTextEnemy = new JTextField("Status: " + enemy.getStatus());
        worthTextEnemy = new JTextField("Worth: " + Integer.toString(enemy.getWorth()));
        strenghtTextEnemy = new JTextField("Strenght: " + Integer.toString(enemy.getStrength()));
        dexterityTextEnemy = new JTextField("Dexterity: " + Integer.toString(enemy.getDexterity()));
        constitutionTextEnemy = new JTextField("Constitution: " + Integer.toString(enemy.getConstitution()));
        attackTextEnemy = new JTextField("Attack: " + Integer.toString(enemy.getAttackRating()));
        defenseTextEnemy = new JTextField("Defense: " + Integer.toString(enemy.getDefenseRating()));

        pictureLabelEnemy = new JLabel();

        raceTextEnemy.setEditable(false);
        healthTextEnemy.setEditable(false);
        damageTextEnemy.setEditable(false);
        statusTextEnemy.setEditable(false);
        worthTextEnemy.setEditable(false);
        attackTextEnemy.setEditable(false);
        defenseTextEnemy.setEditable(false);
        strenghtTextEnemy.setEditable(false);
        dexterityTextEnemy.setEditable(false);
        constitutionTextEnemy.setEditable(false);

        //Enemy Race
        panelEnemy.add(raceTextEnemy);
        contentPane.add(panelEnemy);
        raceTextEnemy.setPreferredSize(new Dimension(100, 20));
        raceTextEnemy.setBackground(Color.white);

        //Enemy HP
        panelEnemy.add(healthTextEnemy);
        contentPane.add(panelEnemy);
        healthTextEnemy.setPreferredSize(new Dimension(100, 20));
        healthTextEnemy.setBackground(Color.red);

        //Enemy Damage
        panelEnemy.add(damageTextEnemy);
        contentPane.add(panelEnemy);
        damageTextEnemy.setPreferredSize(new Dimension(100, 20));
        damageTextEnemy.setBackground(Color.cyan);

        //Enemy Worth (how much XP player gets for defeating this enemy)
        panelEnemy.add(worthTextEnemy);
        contentPane.add(panelEnemy);
        worthTextEnemy.setPreferredSize(new Dimension(100, 20));
        worthTextEnemy.setBackground(Color.green);

        //Enemy Status
        panelEnemy.add(statusTextEnemy);
        contentPane.add(panelEnemy);
        statusTextEnemy.setPreferredSize(new Dimension(100, 20));
        statusTextEnemy.setBackground(Color.yellow);

        panelEnemy.add(strenghtTextEnemy);
        contentPane.add(panelEnemy);
        strenghtTextEnemy.setPreferredSize(new Dimension(100, 20));
        strenghtTextEnemy.setBackground(Color.black);
        strenghtTextEnemy.setForeground(Color.white);

        panelEnemy.add(dexterityTextEnemy);
        contentPane.add(panelEnemy);
        dexterityTextEnemy.setPreferredSize(new Dimension(100, 20));
        dexterityTextEnemy.setBackground(Color.black);
        dexterityTextEnemy.setForeground(Color.white);

        panelEnemy.add(constitutionTextEnemy);
        contentPane.add(panelEnemy);
        constitutionTextEnemy.setPreferredSize(new Dimension(100, 20));
        constitutionTextEnemy.setBackground(Color.black);
        constitutionTextEnemy.setForeground(Color.white);

        //Enemy Attack
        panelEnemy.add(attackTextEnemy);
        contentPane.add(panelEnemy);
        attackTextEnemy.setPreferredSize(new Dimension(100, 20));
        attackTextEnemy.setBackground(new Color(0.2f, 0.2f, 0.5f));
        attackTextEnemy.setForeground(new Color(0.6f, 0.6f, 0.3f));

        // Enemy Defense
        panelEnemy.add(defenseTextEnemy);
        contentPane.add(panelEnemy);
        defenseTextEnemy.setPreferredSize(new Dimension(100, 20));
        defenseTextEnemy.setBackground(Color.ORANGE);

        //Enemy pic
        ImageIcon icon;

        //Sets the race of the enemy
        switch (enemy.getPictureID()) {

            case 0:
                icon = new ImageIcon("images/snake0.jpg");
                pictureLabelEnemy.setIcon(icon);
                enemy.setRace("Snake (lvl: " + enemy.getLevel() + ")");
                break;
            case 1:
                icon = new ImageIcon("images/wolf1.jpg");
                pictureLabelEnemy.setIcon(icon);
                enemy.setRace("Black wolf (lvl: " + enemy.getLevel() + ")");
                break;
            case 2:
                icon = new ImageIcon("images/worm2.jpg");
                pictureLabelEnemy.setIcon(icon);
                enemy.setRace("White Worm (lvl: " + enemy.getLevel() + ")");
                break;
            case 3:
                icon = new ImageIcon("images/lynx3.jpg");
                pictureLabelEnemy.setIcon(icon);
                enemy.setRace("Brown Lynx (lvl: " + enemy.getLevel() + ")");
                break;
            case 4:
                icon = new ImageIcon("images/fellbeast4.jpg");
                pictureLabelEnemy.setIcon(icon);
                enemy.setRace("Fellbeast (lvl: " + enemy.getLevel() + ")");
                break;

        }
        pictureLabelEnemy.setAlignmentX(CENTER_ALIGNMENT);
        panelEnemy.add(pictureLabelEnemy);
        panelEnemy.setBackground(Color.lightGray);
        contentPane.add(panelEnemy);

        raceTextEnemy.setText(enemy.getRace());

    }

    //Updates every text on field according to the Model
    public void textUpdate() {
        nameTextPlayer.setText(player.getName() + ", a(z) " + player.getRole());
        healthTextPlayer.setText("Health: " + Integer.toString(player.getHealth()));
        damageTextPlayer.setText("Damage: Attack + 1d6");
        expTextPlayer.setText(player.getActual_exp() + "/" + player.getLevelCapExp());
        levelTextPlayer.setText("Level: " + Integer.toString(player.getLevel()));
        strenghtTextPlayer.setText("Strenght: " + Integer.toString(player.getStrength()));
        dexterityTextPlayer.setText("Dexterity: " + Integer.toString(player.getDexterity()));
        constitutionTextPlayer.setText("Constitution: " + Integer.toString(player.getConstitution()));
        attackTextPlayer.setText("Attack: " + Integer.toString(player.getAttackRating()));
        defenseTextPlayer.setText("Defense: " + Integer.toString(player.getDefenseRating()));
        if (player.getHealth() <= 0) {

            JOptionPane.showMessageDialog(this, enemy.getRace() + " killed you! Starting new game...");

            player = model.getPlayer();
            model.getFight().createEnemy();
            textUpdate();
        }

        ImageIcon icon;

        switch (player.getLevel()) {

            case 1:
                icon = new ImageIcon("images/player1.jpg");
                pictureLabelPlayer.setIcon(icon);
                break;
            case 2:
                icon = new ImageIcon("images/player2.jpg");
                pictureLabelPlayer.setIcon(icon);
                break;
            case 3:
                icon = new ImageIcon("images/player2.jpg");
                pictureLabelPlayer.setIcon(icon);
                break;
            case 4:
                icon = new ImageIcon("images/player3.jpg");
                pictureLabelPlayer.setIcon(icon);
                break;
            case 5:
                icon = new ImageIcon("images/player3.jpg");
                pictureLabelPlayer.setIcon(icon);
                break;
            default:
                icon = new ImageIcon("images/player4.jpg");
                pictureLabelPlayer.setIcon(icon);
                break;
        }

        healthTextEnemy.setText("Health: " + Integer.toString(enemy.getHealth()));
        damageTextEnemy.setText("Damage: Attack + 1d6");
        worthTextEnemy.setText("Worth: " + Integer.toString(enemy.getWorth()));
        statusTextEnemy.setText("Status: " + enemy.getStatus());
        strenghtTextEnemy.setText("Strenght: " + Integer.toString(enemy.getStrength()));
        dexterityTextEnemy.setText("Dexterity: " + Integer.toString(enemy.getDexterity()));
        constitutionTextEnemy.setText("Constitution: " + Integer.toString(enemy.getConstitution()));
        attackTextEnemy.setText("Attack: " + Integer.toString(enemy.getAttackRating()));
        defenseTextEnemy.setText("Defense: " + Integer.toString(enemy.getDefenseRating()));

        if (enemy.isRaging()) {
            panelEnemy.setBackground(Color.red);
            statusTextEnemy.setBackground(Color.red);
        } else {
            panelEnemy.setBackground(Color.lightGray);
            statusTextEnemy.setBackground(Color.yellow);
        }

        switch (enemy.getPictureID()) {

            case 0:
                icon = new ImageIcon("images/snake0.jpg");
                pictureLabelEnemy.setIcon(icon);
                enemy.setRace("Snake (lvl: " + enemy.getLevel() + ")");
                break;
            case 1:
                icon = new ImageIcon("images/wolf1.jpg");
                pictureLabelEnemy.setIcon(icon);
                enemy.setRace("Black wolf (lvl: " + enemy.getLevel() + ")");
                break;
            case 2:
                icon = new ImageIcon("images/worm2.jpg");
                pictureLabelEnemy.setIcon(icon);
                enemy.setRace("White Worm (lvl: " + enemy.getLevel() + ")");
                break;
            case 3:
                icon = new ImageIcon("images/lynx3.jpg");
                pictureLabelEnemy.setIcon(icon);
                enemy.setRace("Brown Lynx (lvl: " + enemy.getLevel() + ")");
                break;
            case 4:
                icon = new ImageIcon("images/fellbeast4.jpg");
                pictureLabelEnemy.setIcon(icon);
                enemy.setRace("Fellbeast (lvl: " + enemy.getLevel() + ")");
                break;
        }

        raceTextEnemy.setText(enemy.getRace());

    }

    public void createEffectPanel() {

        effectPanel = new JPanel();

        effectPText.setPreferredSize(new Dimension(260, 200));
        effectPText.setEditable(false);
        effectPText.setFont(new Font("Serif", Font.ITALIC, 16));
        effectPText.setBackground(new Color(255, 215, 0));

        effectPanel.add(effectPText);
       
        effectEText.setEditable(false);
        effectEText.setFont(new Font("Serif", Font.ITALIC, 16));
        effectEText.setBackground(new Color(156, 93, 82));
        effectEText.setPreferredSize(new Dimension(260, 200));

        effectPanel.add(effectEText);
        
        effectPanel.setLayout(new BoxLayout(effectPanel, BoxLayout.Y_AXIS));
        contentPane.add(effectPanel);
    }
    //simulates a round of fight on clicking the button 'Fight'
    public ActionListener fightActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int tempInt = player.getLevel();
            model.getFight().simulateFight();
            if (tempInt != player.getLevel() && player.getLevel() % 3 == 0) {
                String[] upgrd = {"Strength", "Dexterity", "Constitution"};
                String tempStr = (String) JOptionPane.showInputDialog(null, "What do you want to upgrade?", "Upgrade box", JOptionPane.QUESTION_MESSAGE, null, upgrd, null);
                switch (tempStr) {
                    case "Strength":
                        player.setStrength(player.getStrength() + 1);
                        break;
                    case "Dexterity":
                        player.setDexterity(player.getDexterity() + 1);
                        break;
                    case "Constitution":
                        player.setConstitution(player.getConstitution() + 1);
                        break;
                }
            }

            textUpdate();
            if (model.getFight().isEnemyDead()) {
                model.getFight().createEnemy();
                textUpdate();
            }
        }
    };
    public ActionListener restActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.rest();
            textUpdate();
        }
    };
    public ActionListener saveActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.savePlayer();
            if (cbModel.getIndexOf(player.getName()) == -1) {
                cbModel.addElement(player.getName());
            }
            System.out.println("Hero has been saved");
        }
    };
    public ActionListener loadActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            LoadFrame frame = new LoadFrame();

            frame.setModal(true);
            frame.setVisible(true);

            if (!frame.isCanceled()) {

                model.setLoadFileName((String) frame.getHero());
                model.loadPlayer();
                System.out.println("Hero has been loaded");
                model.getFight().createEnemy();
                textUpdate();
            }
        }
    };
    public ActionListener createActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            CreateFrame frame = new CreateFrame();

            frame.setModal(true);
            frame.setVisible(true);

            if (!frame.isCanceled()) {

                player.setName(frame.getName());
                player.setRole((String) frame.getRole());
                model.createHero();
                textUpdate();

            }
        }
    };
    public ActionListener muteActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isMute) {
                clip.start();
                isMute = false;
            } else {
                clip.stop();
                isMute = true;
            }
        }
    };
    public ActionListener exitActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            Frame.this.dispose();
        }
    };
    public ActionListener helpActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            HelpFrame frame = new HelpFrame();

            frame.setModal(true);
            frame.setVisible(true);            
        }
    };
}
