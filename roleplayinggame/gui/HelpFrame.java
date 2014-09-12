/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roleplayinggame.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JTextField;

/**
 *
 * @author Róbert
 */
public class HelpFrame extends JDialog{
    
    private JTextField text;
    private boolean closed = false;
    
    public HelpFrame(){
        setSize(300, 200);

        setLayout(new FlowLayout());

        text = new JTextField("Name of hero");
        text.setPreferredSize(new Dimension(100, 200));
        
        add(text);
    }
}
