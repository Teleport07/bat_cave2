package Lol;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Formatter;

public class MainPanel extends JFrame {
    JComboBox heroes;
    JComboBox level;
    static String [] hero = {"Ashe","Caitlyn","Ezreal","Jhin","Jinx","Kaisa","Kalista","KogMaw","Lucian","MissFortune",
    "Sivir","Tristana","Twitch","Varus","Vayne","Xayah","Draven"};
    PanelOfItems items;
    String [] levels = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18"};
    Inventory inv;
    double attackDamage,basicAS,attackDamageLvl1, attackSpeed,bonusDamage, damagePerSec, Jhin_BaseAd;
    double JhinBaseRatio = 10;
    double critCof = 2;
    double critCof_Jhin = 1.50;
    double attackDamageItem, damageInc, ratioItem, critChance, critChanceExtra = 0;
    double baseAS_Caitlyn = 0.568;
    Double [] JhinLvl = {4.0,5.0,6.0,7.0,8.0,9.0,10.0,11.0,12.0,14.0,16.0,20.0,24.0,28.0,32.0,36.0,40.0,44.0};
    int lvl = 1;
    int price = 0;
    double ratio_base = 13.0;
    Label aD, aS, cC, Dps, pR;
    Formula formula;
    JScrollPane scr;


    MainPanel (){
        setLayout(new FlowLayout());

        heroes = new JComboBox(hero);
        add(heroes);
        heroes.addItemListener(new Heroes(this));
        ComboBoxRenderer renderer = new ComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(250,130));
        heroes.setRenderer(renderer);
        aD = new Label("Attack Damage: 0  ");
        aS = new Label("Attack Speed: 0  ");
        cC = new Label("Critical Chance: 0  ");
        Dps = new Label("Dps: 0  ");
        pR = new Label("Price: 0   ");

        formula = new Formula(aD,aS,cC,pR,Dps);


        level = new JComboBox(levels);
        level.addItemListener(new Level(this));
        add(level);

        inv = new Inventory(this);
        items = new PanelOfItems(this, inv);
        add(inv);
        add(items);

        add(formula);

        setSize(900,500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void setStats(String hero){
        if(hero.equals("Jhin")){
            System.out.println("Level% "+JhinLvl[level.getSelectedIndex()]);
            System.out.println("Base AD "+Jhin_BaseAd);
            System.out.println("Crit cha "+critChance);
            System.out.println("ratio item "+ratioItem);
            attackDamage = ((critChance*0.4)/100 + (ratioItem+10)*0.25/100 + (JhinLvl[level.getSelectedIndex()]/100))*(Jhin_BaseAd + 6 + attackDamageItem) + (Jhin_BaseAd + 6 + attackDamageItem);
           /* attackDamage = (((critChance*0.4)/100*Jhin_BaseAd) + ((ratioItem+10)*0.25/100*Jhin_BaseAd)+Jhin_BaseAd +6 + attackDamageItem*1.0625)*(JhinLvl[level.getSelectedIndex()]/100) + Jhin_BaseAd + 6+ attackDamageItem*1.0625+
                    ((critChance*0.4)/100*Jhin_BaseAd) + ((ratioItem+10)*0.25/100*Jhin_BaseAd);*/
            System.out.println("Attack d "+attackDamage);
            try(Formatter f = new Formatter()){
                aD.setText("Attack Damage: "+f.format("%.2f",attackDamageLvl1));
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
            pR.setText("Price: "+price);
        }else {
            attackDamage = attackDamageLvl1 + attackDamageItem;
            attackSpeed = basicAS * ((ratio_base + ratioItem) / 100 + 1);
            aD.setText("Attack Damage: " + attackDamage);
            try (Formatter f = new Formatter()) {
                aS.setText("Attack Speed: " + f.format("%.2f", attackSpeed));
            }
            try (Formatter d = new Formatter()) {
                cC.setText("Critical Chance: " + d.format(critChance + "%%"));
            }
            System.out.println(attackSpeed);
            damagePerSec = (attackDamage * critCof * (critChance / 100) + attackDamage * (1 - critChance / 100)) * attackSpeed;
            try (Formatter s = new Formatter()) {
                Dps.setText("Dps: " + s.format("%.2f", damagePerSec));
            }
            pR.setText("Price: " + price);
        }
    }
    public void setStats(){
        //System.out.println(heroes.getSelectedItem());
        if(heroes.getSelectedItem().equals("Jhin")){
            System.out.println("Level% "+JhinLvl[level.getSelectedIndex()]);
            System.out.println("Base AD "+Jhin_BaseAd);
            System.out.println("Crit cha "+critChance);
            System.out.println("ratio item "+ratioItem);
            attackDamage = ((critChance*0.4)/100 + (ratioItem+10)*0.25/100 + (JhinLvl[level.getSelectedIndex()]/100))*(Jhin_BaseAd + 6 + attackDamageItem) + (Jhin_BaseAd + 6 + attackDamageItem);
            /*attackDamage = (((critChance*0.4)/100*Jhin_BaseAd) + ((ratioItem+10)*0.25/100*Jhin_BaseAd)+Jhin_BaseAd + 6 + attackDamageItem*1.0625)*(JhinLvl[level.getSelectedIndex()]/100) + Jhin_BaseAd + 6 + attackDamageItem*1.0625
            +((critChance*0.4)/100*Jhin_BaseAd) + ((ratioItem+10)*0.25/100*Jhin_BaseAd);*/
            System.out.println("Attack d "+(((critChance*0.4)/100*Jhin_BaseAd) + ((ratioItem+10)*0.25/100*Jhin_BaseAd)+Jhin_BaseAd +6)*(JhinLvl[level.getSelectedIndex()]/100));
            try(Formatter f = new Formatter()){
                aD.setText("Attack Damage: "+f.format("%.2f",attackDamage));
            }
            try(Formatter f = new Formatter()){
                aS.setText("Attack Speed: "+f.format("%.3f",attackSpeed));
            }
            try(Formatter d = new Formatter()){
                cC.setText("Critical Chance: "+d.format(critChance+"%%"));
            }
            System.out.println("Coef "+ attackDamage*critCof_Jhin*(critChance/100));
            damagePerSec = (attackDamage*critCof_Jhin*(critChance/100)+attackDamage*(1-critChance/100))*attackSpeed;
            try(Formatter s = new Formatter()){
                Dps.setText("Dps: "+s.format("%.2f",damagePerSec));
            }
            pR.setText("Price: "+price);
        }else {
            attackDamage = attackDamageLvl1 + attackDamageItem;
            attackSpeed = basicAS * ((ratio_base + ratioItem) / 100 + 1);
            //System.out.println("Базовый дамаг "+(attackDamageLvl1+(lvl-1)*damageInc));
            System.out.println("Extra cC "+critChanceExtra);
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
            System.out.println(attackSpeed);
        }
    }
}
