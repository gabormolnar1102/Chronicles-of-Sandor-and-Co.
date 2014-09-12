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
import java.io.File;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

/**
 *
 * @author Gáb
 */
public class LoadFrame extends JDialog {

    DefaultComboBoxModel cbModel = new DefaultComboBoxModel();
    private JComboBox loadHero;
    private boolean closed = false;
    private boolean canceled = false;
    File file = new File("save/");
    String[] fileList = file.list();
    String[] fileNames;

    public LoadFrame() {

        setSize(300, 200);

        setLayout(new FlowLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        for (String s : fileList) {
            fileNames = s.split("\\.");
            cbModel.addElement(fileNames[0]);
        }
        loadHero = new JComboBox(cbModel);

        add(loadHero);

        JButton button = new JButton("Load");
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

                LoadFrame.this.dispose();
            }
        });
        //add(cancel);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent winEvt) {
                canceled = true;
                setVisible(false);
            }
        });
    }

    public Object getHero() {

        return loadHero.getSelectedItem();
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
