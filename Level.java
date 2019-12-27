package Lol;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.sun.javafx.util.Utils.split;

public class Level implements ItemListener {
    MainPanel mP;
    Level(MainPanel mp){
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
                if (mP.heroes.getSelectedItem().equals("Jhin")) {
                    while ((r = reader.readLine()) != null) {
                        mass = split(r, " ");
                        if (Integer.parseInt(mass[0]) == lev) {
                            mP.attackSpeed = Double.parseDouble(mass[1]);
                            break;
                        }
                    }
                    while((r=reader1.readLine())!=null){
                        mass = split(r, " ");
                        if(Integer.parseInt(mass[0]) == lev){
                            mP.Jhin_BaseAd = Double.parseDouble(mass[1]);
                            break;
                        }
                    }
                    mP.setStats();
                } else if(mP.heroes.getSelectedItem().equals("Caitlyn")){
                    while ((r = reader1.readLine()) != null) {
                        mass = split(r, " ");
                        if (Integer.parseInt(mass[0]) == lev) {
                            mP.attackDamageLvl1 = Double.parseDouble(mass[1]);
                            break;
                        }
                    }
                    while((r=reader.readLine())!=null){
                        mass = split(r, " ");
                        if (Integer.parseInt(mass[0]) == lev){
                            mP.ratio_base = Double.parseDouble(mass[1]);
                            mP.basicAS=mP.baseAS_Caitlyn+Double.parseDouble(mass[2]);
                        }
                    }
                    mP.setStats();
                }else{
                    while ((r = reader.readLine()) != null) {
                        mass = split(r, " ");
                        if (Integer.parseInt(mass[0]) == lev) {
                            mP.ratio_base = Double.parseDouble(mass[1]);
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
                } catch(IOException ex){
                    ex.printStackTrace();
                }

        }
    }
}
