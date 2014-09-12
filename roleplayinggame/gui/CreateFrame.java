/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roleplayinggame.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;

/**
 *
 * @author Gáb
 */
public class CreateFrame extends JDialog {

    private JTextField name;
    private JComboBox<String> role;
    private boolean closed = false;
    private boolean canceled = false;

    public CreateFrame() {
        setSize(300, 200);

        setLayout(new FlowLayout());

        name = new JTextField("Name of hero");
        name.setPreferredSize(new Dimension(100, 20));
        role = new JComboBox<>();

        role.addItem("Barbarian");
        role.addItem("Warrior");
        role.addItem("Rogue");
        role.addItem("Special-one");

        add(name);
        add(role);



        JButton button = new JButton("Create");
        button.setPreferredSize(new Dimension(250, 20));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                closed = false;
                //elég csak bezárni mert a gui automatikusan megvizsgálja az értékeit
                setVisible(false);
            }
        });
        add(button);

        JButton cancel = new JButton("Cancel");
        button.setPreferredSize(new Dimension(250, 20));

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                canceled = true;
                //elég csak bezárni mert a gui automatikusan megvizsgálja az értékeit
                setVisible(false);

                CreateFrame.this.dispose();
            }
        });
        add(cancel);


        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent winEvt) {
                canceled = true;
                setVisible(false);
            }
        });
    }

    @Override
    public String getName() {
        return name.getText();
    }

    public Object getRole() {

        return role.getSelectedItem();
    }

    public boolean isCanceled() {
        return canceled;
    }
}
