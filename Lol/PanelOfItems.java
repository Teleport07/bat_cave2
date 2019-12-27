package Lol;

import javax.swing.*;

public class PanelOfItems extends JTabbedPane{


    PanelOfItems (MainPanel maP, Inventory inv){

        addTab("Boots",new TabItems("Boots", maP, inv));
        addTab("Damage", new TabItems("Damage",maP, inv));
        addTab("AttackSpeed", new TabItems("AttackSpeed",maP, inv));

    }

}
