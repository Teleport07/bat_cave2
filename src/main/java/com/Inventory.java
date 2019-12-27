package com;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import static com.sun.javafx.util.Utils.split;

@Slf4j
public class Inventory extends JPanel implements ActionListener {

  private final JButton b1, b2, b3, b4, b5, b6;
  Label lab;
  ArrayList<JButton> invItems;
  private final MainPanel mP;

  Inventory(MainPanel mp) {
    mP = mp;
    setLayout(new GridLayout(2, 3, 5, 5));
    setBorder(BorderFactory.createLineBorder(Color.gray, 1));
    b1 = new JButton();
    b2 = new JButton();
    b3 = new JButton();
    b4 = new JButton();
    b5 = new JButton();
    b6 = new JButton();

    invItems = new ArrayList<>();
    invItems.add(b1);
    invItems.add(b2);
    invItems.add(b3);
    invItems.add(b4);
    invItems.add(b5);
    invItems.add(b6);

    //addButtons();
    //lab = new Label("Text here");
    //add(lab);
    for (JButton butt : invItems) {
      butt.setPreferredSize(new Dimension(80, 80));
      butt.setActionCommand("0");
      butt.addActionListener(this);
      add(butt);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (!"0".equals(e.getActionCommand())) {
      String[] mass;
      String[] mass1;
      mass = split(e.getActionCommand(), " ");
      try (BufferedReader reader = new BufferedReader(new FileReader(mass[1] + ".txt"))) {
        String s;
        while ((s = reader.readLine()) != null) {
          mass1 = split(s, " ");
          if (mass1[0].equals(mass[0])) {
            mP.ratioItem -= Double.parseDouble(mass1[1]);
            if (mP.critChanceExtra != 0) {
              mP.critChanceExtra -= Integer.parseInt(mass1[2]);
            } else if ((mP.critChance - Integer.parseInt(mass1[2])) < 0) {
              mP.critChance = 0;
            } else {
              mP.critChance -= Integer.parseInt(mass1[2]);
            }
            mP.attackDamageItem -= Double.parseDouble(mass1[3]);
            mP.price -= Integer.parseInt(mass1[4]);
            mP.setStats();
            break;
          }
        }
      } catch (IOException ex) {
        log.error(ex.getMessage(), ex);
      }
      ((JButton) e.getSource()).setIcon(new ImageIcon());
      ((JButton) e.getSource()).setActionCommand("0");
    }
  }
}