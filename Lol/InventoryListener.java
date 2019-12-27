package Lol;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.sun.javafx.util.Utils.split;

public class InventoryListener implements ActionListener {
    MainPanel mP;
    boolean f;
    DialogHp dial;
    InventoryListener(MainPanel mp, boolean t){
        mP = mp;
        f = t;
    }
    InventoryListener(MainPanel mp, boolean t, DialogHp dial){
        mP = mp;
        f = t;
        this.dial = dial;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()!="0"){
            String mass[];
            String mass1[];
            System.out.println("Внутри");
            mass = split(e.getActionCommand()," ");
            if((mass[0].equals("BOTRK"))&& f){
                new DialogHp(mP, true);
            }else {
                try (BufferedReader reader = new BufferedReader(new FileReader(mass[1] + ".txt"))) {
                    String s;
                    while ((s = reader.readLine()) != null) {
                        mass1 = split(s, " ");
                        if (mass1[0].equals(mass[0])) {
                            mP.ratioItem-=Double.parseDouble(mass1[1]);
                            if (mP.critChanceExtra != 0) {
                                mP.critChanceExtra -= Integer.parseInt(mass1[2]);
                            } else if ((mP.critChance - Integer.parseInt(mass1[2])) < 0) {
                                mP.critChance = 0;
                            } else {
                                mP.critChance -= Integer.parseInt(mass1[2]);
                            }
                            mP.attackDamageItem -= Double.parseDouble(mass1[3]);
                            mP.price -= Integer.parseInt(mass1[4]);
                            mP.setStats();
                            break;
                        }
                    }
                    if(dial!=null){
                        mP.Botrk_HP=0;
                        mP.setStats();
                        dial.dispose();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if(!f){
                    for(JButton butt : mP.inv.invItems){
                        if(butt.getActionCommand().equals("BOTRK Damage")){
                            butt.setIcon(new ImageIcon());
                            butt.setActionCommand("0");
                            break;
                        }
                    }
                }else{
                    ((JButton) e.getSource()).setIcon(new ImageIcon());
                    ((JButton) e.getSource()).setActionCommand("0");
                }
            }
        }
    }
}
