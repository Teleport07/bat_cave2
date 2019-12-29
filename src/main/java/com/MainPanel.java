package com;

import com.configuration.GeneratorDataConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

@Slf4j
@Setter
@Getter
public class MainPanel extends JFrame {

    private JComboBox heroes;
    private JComboBox level;
    private PanelOfItems items;
    private String[] levels;
    private Inventory inv;
    private double attackDamage, basicAS, attackDamageLvl1, attackSpeed, bonusDamage, damagePerSec, Jhin_BaseAd;
    private double JhinBaseRatio = 10;
    private double critCof = 2;
    private double critCof_Jhin = 1.50;
    private double attackDamageItem, damageInc, ratioItem, critChance, critChanceExtra = 0;
    private double baseAS_Caitlyn = 0.568;
    private Double[] JhinLvl;
    private int lvl = 1;
    private int price = 0;
    private double ratio_base = 13.0;
    private Label aD, aS, cC, dps, pR;
    private Formula formula;
    private JScrollPane scr;

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
        dps = new Label("Dps: 0  ");
        pR = new Label("Price: 0   ");

        formula = new Formula(aD, aS, cC, pR, dps);

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
        log.debug("setStats(hero: {})", hero);

        if (hero.equals("Jhin")) {
            log.debug("Level% {}", JhinLvl[level.getSelectedIndex()]);
            log.debug("Base AD {}", Jhin_BaseAd);
            log.debug("Crit cha {}", critChance);
            log.debug("ratio item {}", ratioItem);
            attackDamage = ((critChance * 0.4) / 100 + (ratioItem + 10) * 0.25 / 100 + (JhinLvl[level.getSelectedIndex()] / 100)) * (Jhin_BaseAd + 6 + attackDamageItem) + (Jhin_BaseAd + 6 + attackDamageItem);
           /* attackDamage = (((critChance*0.4)/100*Jhin_BaseAd) + ((ratioItem+10)*0.25/100*Jhin_BaseAd)+Jhin_BaseAd +6 + attackDamageItem*1.0625)*(JhinLvl[level.getSelectedIndex()]/100) + Jhin_BaseAd + 6+ attackDamageItem*1.0625+
                    ((critChance*0.4)/100*Jhin_BaseAd) + ((ratioItem+10)*0.25/100*Jhin_BaseAd);*/
            log.debug("Attack d {}", attackDamage);
            damagePerSec = (attackDamage * critCof_Jhin * (critChance / 100) + attackDamage * (1 - critChance / 100)) * attackSpeed;

            aD.setText(String.format("Attack Damage: %.2f", attackDamageLvl1));

        } else {
            attackDamage = attackDamageLvl1 + attackDamageItem;
            attackSpeed = basicAS * ((ratio_base + ratioItem) / 100 + 1);
            log.debug("attackSpeed: {}", attackSpeed);
            damagePerSec = (attackDamage * critCof * (critChance / 100) + attackDamage * (1 - critChance / 100)) * attackSpeed;
            aD.setText("Attack Damage: " + attackDamage);
        }
        aS.setText(String.format("Attack Speed: %.3f", attackSpeed));
        cC.setText(String.format("Critical Chance: " + critChance + "%%"));
        dps.setText(String.format("Dps: %.2f", damagePerSec));
        pR.setText("Price: " + price);
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
            log.debug("Coef {}", attackDamage * critCof_Jhin * (critChance / 100));
            damagePerSec = (attackDamage * critCof_Jhin * (critChance / 100) + attackDamage * (1 - critChance / 100)) * attackSpeed;

        } else {
            attackDamage = attackDamageLvl1 + attackDamageItem;
            attackSpeed = basicAS * ((ratio_base + ratioItem) / 100 + 1);
            damagePerSec = (attackDamage * critCof * (critChance / 100) + attackDamage * (1 - critChance / 100)) * attackSpeed;

            log.debug("Базовый дамаг {}", (attackDamageLvl1 + (lvl - 1) * damageInc));
            log.debug("Extra cC {}", critChanceExtra);
            log.debug("attackSpeed: {}", attackSpeed);
        }
        aD.setText(String.format("Attack Damage: %.2f", attackDamage));
        aS.setText(String.format("Attack Speed: %.3f", attackSpeed));
        cC.setText(String.format("Critical Chance: " + critChance + "%%"));
        dps.setText(String.format("Dps: %.2f", damagePerSec));
        pR.setText("Price: " + price);
    }
}
