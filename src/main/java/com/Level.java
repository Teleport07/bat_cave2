package com;

import lombok.extern.slf4j.Slf4j;

import java.awt.event.*;
import java.io.*;

@Slf4j
public class Level implements ItemListener {

  private final MainPanel mainPanel;

  Level(MainPanel mp) {
    this.mainPanel = mp;
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    int lev = 0;
    if (e.getStateChange() == ItemEvent.SELECTED) {
      lev = Integer.parseInt((String) e.getItem());

      ClassLoader classloader = Thread.currentThread().getContextClassLoader();

      String heroe = mainPanel.getHeroes().getSelectedItem().toString();
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(classloader.getResourceAsStream("txt/" + heroe + ".txt")));
           BufferedReader reader1 = new BufferedReader(new InputStreamReader(classloader.getResourceAsStream("txt/" + heroe + "_D.txt")))) {
        String r;
        String[] mass;
        if ("Jhin".equals(heroe)) {
          log.debug("внутри");
          while ((r = reader.readLine()) != null) {
            mass = r.split(" ");
            if (Integer.parseInt(mass[0]) == lev) {
              mainPanel.setAttackSpeed(Double.parseDouble(mass[1]));
              //Slog.debug("AS2 {}", mP.attackSpeed);
              break;
            }
          }
          while ((r = reader1.readLine()) != null) {
            mass = r.split( " ");
            if (Integer.parseInt(mass[0]) == lev) {
              mainPanel.setJhin_BaseAd(Double.parseDouble(mass[1]));
              //log.debug("Base AD {}", mP.Jhin_BaseAd);
              break;
            }
          }
          mainPanel.setStats();
        } else if ("Caitlyn".equals(heroe)) {
          while ((r = reader1.readLine()) != null) {
            mass = r.split( " ");
            if (Integer.parseInt(mass[0]) == lev) {
              mainPanel.setAttackDamageLvl1(Double.parseDouble(mass[1]));
              break;
            }
          }
          while ((r = reader.readLine()) != null) {
            mass = r.split( " ");
            if (Integer.parseInt(mass[0]) == lev) {
              mainPanel.setRatio_base(Double.parseDouble(mass[1]));
              mainPanel.setBasicAS(mainPanel.getBaseAS_Caitlyn() + Double.parseDouble(mass[2]));
            }
          }
          mainPanel.setStats();
        } else {
          while ((r = reader.readLine()) != null) {
            mass = r.split( " ");
            if (Integer.parseInt(mass[0]) == lev) {
              mainPanel.setRatio_base( Double.parseDouble(mass[1]));
              //mP.lvl = lev;
              break;
            }
          }
          while ((r = reader1.readLine()) != null) {
            mass = r.split( " ");
            if (Integer.parseInt(mass[0]) == lev) {
              mainPanel.setAttackDamageLvl1(Double.parseDouble(mass[1]));
              break;
            }
          }
          mainPanel.setStats();
        }
      } catch (IOException ex) {
        log.error(ex.getMessage(), ex);
      }
    }
  }
}