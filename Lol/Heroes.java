package Lol;


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;


import static com.sun.javafx.util.Utils.split;

public class Heroes implements ItemListener {
    MainPanel mP;
    Heroes(MainPanel mp){
        mP= mp;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String hero = (String) e.getItem();
            try (BufferedReader bf = new BufferedReader(new FileReader("Heroes.txt"))) {
                String s;
                String[] mass;
                while ((s = bf.readLine()) != null) {
                    if (s.startsWith(hero)) {
                        mass = split(s, " ");
                        mP.attackDamageLvl1 = Double.parseDouble(mass[1]);
                        mP.basicAS = Double.parseDouble(mass[3]);
                        mP.setStats();
                        break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
