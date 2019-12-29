package com;

import lombok.extern.slf4j.Slf4j;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;

@Slf4j
public class Heroes implements ItemListener {

  private final MainPanel mainPanel;

  Heroes(MainPanel mp) {
    this.mainPanel = mp;
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.SELECTED) {
      String hero = (String) e.getItem();
      ClassLoader classloader = Thread.currentThread().getContextClassLoader();
      try (BufferedReader bf = new BufferedReader(new InputStreamReader(classloader.getResourceAsStream("txt/Heroes.txt")))) {
        String s;
        String[] mass;
        while ((s = bf.readLine()) != null) {
          if (s.startsWith(hero)) {
            mass = s.split( " ");
            mainPanel.setAttackDamageLvl1(Double.parseDouble(mass[1]));
            mainPanel.setBasicAS(Double.parseDouble(mass[3]));
            mainPanel.setStats(hero);
            log.debug("Damage {}", mainPanel.getAttackDamageLvl1());
            log.debug("AS {}", mainPanel.getBasicAS());
            break;
          }
        }
      } catch (IOException ex) {
        log.error(ex.getMessage(), ex);
      }
    }
  }
}
