package Lol;

import javax.swing.*;
import java.awt.*;
import java.util.Formatter;

public class MainPanel extends JFrame {
    JComboBox heroes;
    JComboBox level;
    JPanel stat;
    static String [] hero = {"Ashe","Caitlyn","Ezreal","Jhin","Jinx","Kaisa","Kalista","KogMaw","Lucian","MissFortune",
    "Sivir","Tristana","Twitch","Varus","Vayne","Xayah","Draven"};
    PanelOfItems items;
    String [] levels = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18"};
    Inventory inv;
    double attackDamage,basicAS,attackDamageLvl1, attackSpeed, damagePerSec, Jhin_BaseAd;
    double critCof = 2;
    double critCof_Jhin = 1.50;
    double attackDamageItem,ratioItem, critChance, critChanceExtra = 0;
    double baseAS_Caitlyn = 0.568;
    int Botrk_HP = 0;
    Double [] JhinLvl = {4.0,5.0,6.0,7.0,8.0,9.0,10.0,11.0,12.0,14.0,16.0,20.0,24.0,28.0,32.0,36.0,40.0,44.0};
    int price = 0;
    double ratio_base = 13.0;
    Label aD, aS, cC, Dps, pR, HP;

    MainPanel (){
        setLayout(new FlowLayout());

        heroes = new JComboBox(hero);
        heroes.addItemListener(new Heroes(this));
        ComboBoxRenderer renderer = new ComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(250,130));
        heroes.setRenderer(renderer);

        stat = new JPanel();

        aD = new Label("Attack Damage: 0  ");
        aS = new Label("Attack Speed: 0  ");
        cC = new Label("Critical Chance: 0  ");
        Dps = new Label("Dps: 0  ");
        pR = new Label("Price: 0   ");
        HP = new Label("HP: 0   ");

        stat.setLayout(new BoxLayout(stat,BoxLayout.Y_AXIS));
        stat.add(aD);
        stat.add(aS);
        stat.add(cC);
        stat.add(Dps);
        stat.add(pR);
        stat.add(HP);

        level = new JComboBox(levels);

        level.addItemListener(new Level(this));
        level.setSelectedIndex(0);
        inv = new Inventory(this);
        items = new PanelOfItems(this);

        add(heroes);
        add(level);
        add(inv);
        add(items);
        add(stat);

        setSize(950,500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setStats(){
        if(heroes.getSelectedItem().equals("Jhin")){
            attackDamage = ((critChance*0.4)/100 + (ratioItem+10)*0.25/100 + (JhinLvl[level.getSelectedIndex()]/100))*(Jhin_BaseAd + 6 + attackDamageItem) + (Jhin_BaseAd + 6 + attackDamageItem);
            try(Formatter f = new Formatter()) {
                if (Botrk_HP != 0) {
                    aD.setText("Attack Damage: " + f.format("%.2f+%.2f", attackDamage,Botrk_HP * 0.08));
                } else {
                    aD.setText("Attack Damage: " + f.format("%.2f", attackDamage));
                }
            }
            try(Formatter f = new Formatter()){
                aS.setText("Attack Speed: "+f.format("%.3f",attackSpeed));
            }
            try(Formatter d = new Formatter()){
                cC.setText("Critical Chance: "+d.format(critChance+"%%"));
            }
            damagePerSec = (attackDamage*critCof_Jhin*(critChance/100)+attackDamage*(1-critChance/100))*attackSpeed;
            try(Formatter s = new Formatter()){
                Dps.setText("Dps: "+s.format("%.2f",damagePerSec));
            }
            HP.setText("HP: "+Botrk_HP);
            pR.setText("Price: "+price);
        }else {
            attackDamage = attackDamageLvl1 + attackDamageItem;
            attackSpeed = basicAS * ((ratio_base + ratioItem) / 100 + 1);
            try(Formatter f = new Formatter()) {
                if (Botrk_HP != 0) {
                    aD.setText("Attack Damage: " + f.format("%.2f+%.2f", attackDamage,Botrk_HP * 0.08));
                } else {
                    aD.setText("Attack Damage: " + f.format("%.2f", attackDamage));
                }
            }
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
            HP.setText("HP: "+Botrk_HP);
        }
    }
}
