package Lol;

import javax.swing.*;


public class PanelOfItems extends JTabbedPane{
    MainPanel mainP;
    String item;
    String mass[];

    PanelOfItems (MainPanel maP){
        addTab("Boots",new TabItems("Boots", maP));
        addTab("Damage", new TabItems("Damage",maP));
        addTab("AttackSpeed", new TabItems("AttackSpeed",maP));
    }
}
