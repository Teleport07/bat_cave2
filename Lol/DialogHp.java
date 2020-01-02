package Lol;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogHp extends JDialog {

    JTextField text;
    JPanel panel;
    JLabel lab;
    boolean sw;
    DialogHp (MainPanel mp, boolean t){
        super(mp,"HP or remove");
        panel = new JPanel();
        add(panel);
        setLocation(mp.getWidth()/2-this.getWidth()/2,mp.getHeight()/2-this.getHeight()/2);
        JButton b1 = new JButton("Change HP");
        JButton b2 = new JButton("Remove item");
        panel.add(b1);
        panel.add(b2);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DialogHp(mp);
                dispose();
            }
        });
        setSize(300,100);
        setVisible(true);
        b2.setActionCommand("BOTRK Damage");
        b2.addActionListener(new InventoryListener(mp, false, this));
    }
    DialogHp(MainPanel mp){
        super(mp,"Max XP");
        setLocation(mp.getWidth()/2-this.getWidth()/2,mp.getHeight()/2-this.getHeight()/2);
        panel = new JPanel();
        add(panel);

        lab = new JLabel("Enter Max HP");
        setSize(300,100);

        text = new JTextField(10);
        panel.add(lab);
        panel.add(text);
        setVisible(true);

        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mp.Botrk_HP = Integer.parseInt(text.getText());
                mp.setStats();
                dispose();
            }
        });
    }
}
