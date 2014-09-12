package roleplayinggame;

import roleplayinggame.gui.Frame;
import java.awt.EventQueue;

/**
 *
 * @author Halász 'Trimere' Gábor
 */
public class RolePlayingGame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Frame().setVisible(true);
                } catch (Exception e) {
                }
            }
        });

    }
}
