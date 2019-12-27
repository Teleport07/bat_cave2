package com;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static com.sun.javafx.util.Utils.split;

@Slf4j
public class TabItemsListener implements ActionListener {

  private final MainPanel mP;
  private final String name;
  private String[] mass;

  TabItemsListener(MainPanel mp, String it) {
    mP = mp;
    name = it;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    mass = new String[5];
    String s;
    if (e.getActionCommand().equals("InfinityEdge")) {
      mP.critCof = 2.2;
      mP.critCof_Jhin = 1.68;
    }
    for (JButton butt : mP.inv.invItems) {
      if ("0".equals(butt.getActionCommand())) {
        try (BufferedReader reader = new BufferedReader(new FileReader(name + ".txt"))) {
          while ((s = reader.readLine()) != null) {
            mass = split(s, " ");
            if (mass[0].equals(e.getActionCommand())) {
              mP.ratioItem += Double.parseDouble(mass[1]);
              if (mP.critChance + Integer.parseInt(mass[2]) > 100) {
                mP.critChance = 100;
                mP.critChanceExtra += Integer.parseInt(mass[2]);
              } else {
                mP.critChance += Integer.parseInt(mass[2]);
              }
              mP.attackDamageItem += Double.parseDouble(mass[3]);
              mP.price += Integer.parseInt(mass[4]);
              mP.setStats();
              break;
            }
          }
        } catch (IOException ex) {
          log.error(ex.getMessage(), ex);
        }
        butt.setIcon(new ImageIcon(e.getActionCommand() + ".png"));
        butt.setActionCommand(e.getActionCommand() + " " + name);
        break;
      }
    }
  }
}
