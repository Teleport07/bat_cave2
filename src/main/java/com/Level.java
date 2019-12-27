package com;

import lombok.extern.slf4j.Slf4j;

import java.awt.event.*;
import java.io.*;

import static com.sun.javafx.util.Utils.split;

@Slf4j
public class Level implements ItemListener {

  private MainPanel mP;

  Level(MainPanel mp) {
    mP = mp;
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    int lev = 0;
    if (e.getStateChange() == ItemEvent.SELECTED) {
      lev = Integer.parseInt((String) e.getItem());
      try (BufferedReader reader = new BufferedReader(new FileReader(mP.heroes.getSelectedItem() + ".txt"));
           BufferedReader reader1 = new BufferedReader(new FileReader(mP.heroes.getSelectedItem() + "_D.txt"))) {
        String r;
        String[] mass;
        if ("Jhin".equals(mP.heroes.getSelectedItem())) {
          log.debug("внутри");
          while ((r = reader.readLine()) != null) {
            mass = split(r, " ");
            if (Integer.parseInt(mass[0]) == lev) {
              mP.attackSpeed = Double.parseDouble(mass[1]);
              //Slog.debug("AS2 {}", mP.attackSpeed);
              break;
            }
          }
          while ((r = reader1.readLine()) != null) {
            mass = split(r, " ");
            if (Integer.parseInt(mass[0]) == lev) {
              mP.Jhin_BaseAd = Double.parseDouble(mass[1]);
              //log.debug("Base AD {}", mP.Jhin_BaseAd);
              break;
            }
          }
          mP.setStats();
        } else if ("Caitlyn".equals(mP.heroes.getSelectedItem())) {
          while ((r = reader1.readLine()) != null) {
            mass = split(r, " ");
            if (Integer.parseInt(mass[0]) == lev) {
              mP.attackDamageLvl1 = Double.parseDouble(mass[1]);
              break;
            }
          }
          while ((r = reader.readLine()) != null) {
            mass = split(r, " ");
            if (Integer.parseInt(mass[0]) == lev) {
              mP.ratio_base = Double.parseDouble(mass[1]);
              mP.basicAS = mP.baseAS_Caitlyn + Double.parseDouble(mass[2]);
            }
          }
          mP.setStats();
        } else {
          while ((r = reader.readLine()) != null) {
            mass = split(r, " ");
            if (Integer.parseInt(mass[0]) == lev) {
              mP.ratio_base = Double.parseDouble(mass[1]);
              //mP.lvl = lev;
              break;
            }
          }
          while ((r = reader1.readLine()) != null) {
            mass = split(r, " ");
            if (Integer.parseInt(mass[0]) == lev) {
              mP.attackDamageLvl1 = Double.parseDouble(mass[1]);
              break;
            }
          }
          mP.setStats();
        }
      } catch (IOException ex) {
        log.error(ex.getMessage(), ex);
      }
    }
  }
}
