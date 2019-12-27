package com;

import com.configuration.GeneratorDataConfig;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.util.Formatter;

@Slf4j
public class MainPanel extends JFrame {

  JComboBox heroes;
  JComboBox level;
  PanelOfItems items;
  String[] levels;
  Inventory inv;
  double attackDamage, basicAS, attackDamageLvl1, attackSpeed, bonusDamage, damagePerSec, Jhin_BaseAd;
  double JhinBaseRatio = 10;
  double critCof = 2;
  double critCof_Jhin = 1.50;
  double attackDamageItem, damageInc, ratioItem, critChance, critChanceExtra = 0;
  double baseAS_Caitlyn = 0.568;
  Double[] JhinLvl;
  int lvl = 1;
  int price = 0;
  double ratio_base = 13.0;
  Label aD, aS, cC, Dps, pR;
  Formula formula;
  JScrollPane scr;

  MainPanel(GeneratorDataConfig config) {
    log.debug("MainPanel(config: {})", config);

    setLayout(new FlowLayout());

    JhinLvl = config.getJhinLvl().toArray(new Double[0]);
    levels = config.getLevels().toArray(new String[0]);
    heroes = new JComboBox(config.getHeros().toArray());
    add(heroes);
    heroes.addItemListener(new Heroes(this));
    ComboBoxRenderer renderer = new ComboBoxRenderer();
    renderer.setPreferredSize(new Dimension(250, 130));
    heroes.setRenderer(renderer);
    aD = new Label("Attack Damage: 0  ");
    aS = new Label("Attack Speed: 0  ");
    cC = new Label("Critical Chance: 0  ");
    Dps = new Label("Dps: 0  ");
    pR = new Label("Price: 0   ");

    formula = new Formula(aD, aS, cC, pR, Dps);

    level = new JComboBox(levels);
    level.addItemListener(new Level(this));
    add(level);

    inv = new Inventory(this);
    items = new PanelOfItems(this, inv);
    add(inv);
    add(items);

    add(formula);

    setSize(900, 500);
    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  public void setStats(String hero) {

    if (hero.equals("Jhin")) {
      log.debug("Level% {}", JhinLvl[level.getSelectedIndex()]);
      log.debug("Base AD {}", Jhin_BaseAd);
      log.debug("Crit cha {}", critChance);
      log.debug("ratio item {}", ratioItem);
      attackDamage = ((critChance * 0.4) / 100 + (ratioItem + 10) * 0.25 / 100 + (JhinLvl[level.getSelectedIndex()] / 100)) * (Jhin_BaseAd + 6 + attackDamageItem) + (Jhin_BaseAd + 6 + attackDamageItem);
           /* attackDamage = (((critChance*0.4)/100*Jhin_BaseAd) + ((ratioItem+10)*0.25/100*Jhin_BaseAd)+Jhin_BaseAd +6 + attackDamageItem*1.0625)*(JhinLvl[level.getSelectedIndex()]/100) + Jhin_BaseAd + 6+ attackDamageItem*1.0625+
                    ((critChance*0.4)/100*Jhin_BaseAd) + ((ratioItem+10)*0.25/100*Jhin_BaseAd);*/
      log.debug("Attack d {}", attackDamage);
      try (Formatter f = new Formatter()) {
        aD.setText("Attack Damage: " + f.format("%.2f", attackDamageLvl1));
      }
      try (Formatter f = new Formatter()) {
        aS.setText("Attack Speed: " + f.format("%.3f", attackSpeed));
      }
      try (Formatter d = new Formatter()) {
        cC.setText("Critical Chance: " + d.format(critChance + "%%"));
      }
      damagePerSec = (attackDamage * critCof_Jhin * (critChance / 100) + attackDamage * (1 - critChance / 100)) * attackSpeed;
      try (Formatter s = new Formatter()) {
        Dps.setText("Dps: " + s.format("%.2f", damagePerSec));
      }
      pR.setText("Price: " + price);
    } else {
      attackDamage = attackDamageLvl1 + attackDamageItem;
      attackSpeed = basicAS * ((ratio_base + ratioItem) / 100 + 1);
      aD.setText("Attack Damage: " + attackDamage);
      try (Formatter f = new Formatter()) {
        aS.setText("Attack Speed: " + f.format("%.2f", attackSpeed));
      }
      try (Formatter d = new Formatter()) {
        cC.setText("Critical Chance: " + d.format(critChance + "%%"));
      }
      log.debug("attackSpeed: {}", attackSpeed);
      damagePerSec = (attackDamage * critCof * (critChance / 100) + attackDamage * (1 - critChance / 100)) * attackSpeed;
      try (Formatter s = new Formatter()) {
        Dps.setText("Dps: " + s.format("%.2f", damagePerSec));
      }
      pR.setText("Price: " + price);
    }
  }

  public void setStats() {
    log.debug("heroes.getSelectedItem(): {}", heroes.getSelectedItem());

    if ("Jhin".equals(heroes.getSelectedItem())) {
      log.debug("Level% {}", JhinLvl[level.getSelectedIndex()]);
      log.debug("Base AD {}", Jhin_BaseAd);
      log.debug("Crit cha {}", critChance);
      log.debug("ratio item {}", ratioItem);
      attackDamage = ((critChance * 0.4) / 100 + (ratioItem + 10) * 0.25 / 100 + (JhinLvl[level.getSelectedIndex()] / 100)) * (Jhin_BaseAd + 6 + attackDamageItem) + (Jhin_BaseAd + 6 + attackDamageItem);
            /*attackDamage = (((critChance*0.4)/100*Jhin_BaseAd) + ((ratioItem+10)*0.25/100*Jhin_BaseAd)+Jhin_BaseAd + 6 + attackDamageItem*1.0625)*(JhinLvl[level.getSelectedIndex()]/100) + Jhin_BaseAd + 6 + attackDamageItem*1.0625
            +((critChance*0.4)/100*Jhin_BaseAd) + ((ratioItem+10)*0.25/100*Jhin_BaseAd);*/
      log.debug("Attack d {}", (((critChance * 0.4) / 100 * Jhin_BaseAd) + ((ratioItem + 10) * 0.25 / 100 * Jhin_BaseAd) + Jhin_BaseAd + 6) * (JhinLvl[level.getSelectedIndex()] / 100));
      try (Formatter f = new Formatter()) {
        aD.setText("Attack Damage: " + f.format("%.2f", attackDamage));
      }
      try (Formatter f = new Formatter()) {
        aS.setText("Attack Speed: " + f.format("%.3f", attackSpeed));
      }
      try (Formatter d = new Formatter()) {
        cC.setText("Critical Chance: " + d.format(critChance + "%%"));
      }
      log.debug("Coef {}", attackDamage * critCof_Jhin * (critChance / 100));
      damagePerSec = (attackDamage * critCof_Jhin * (critChance / 100) + attackDamage * (1 - critChance / 100)) * attackSpeed;
      try (Formatter s = new Formatter()) {
        Dps.setText("Dps: " + s.format("%.2f", damagePerSec));
      }
      pR.setText("Price: " + price);
    } else {
      attackDamage = attackDamageLvl1 + attackDamageItem;
      attackSpeed = basicAS * ((ratio_base + ratioItem) / 100 + 1);
      log.debug("Базовый дамаг {}", (attackDamageLvl1+(lvl-1)*damageInc));
      log.debug("Extra cC {}", critChanceExtra);
      aD.setText("Attack Damage: " + attackDamage);
      try (Formatter f = new Formatter()) {
        aS.setText("Attack Speed: " + f.format("%.2f", attackSpeed));
      }
      try (Formatter d = new Formatter()) {
        cC.setText("Critical Chance: " + d.format(critChance + "%%"));
      }
      damagePerSec = (attackDamage * critCof * (critChance / 100) + attackDamage * (1 - critChance / 100)) * attackSpeed;
      try (Formatter s = new Formatter()) {
        Dps.setText("Dps: " + s.format("%.2f", damagePerSec));
      }
      pR.setText("Price: " + price);
      log.debug("attackSpeed: {}", attackSpeed);
    }
  }
}
