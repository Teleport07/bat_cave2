package Lol;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.sun.javafx.util.Utils.split;

public class TabItemsListener implements ActionListener {
    MainPanel mP;
    String name;
    String [] mass;
    TabItemsListener(MainPanel mp, String it){
        mP = mp;
        name = it;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mass = new String[5];
        String s;
        if(e.getActionCommand().equals("InfinityEdge")) {
            mP.critCof = 2.2;
            mP.critCof_Jhin = 1.68;
        }
        for (JButton butt : mP.inv.invItems) {
            if (butt.getActionCommand() == "0") {
                try (BufferedReader reader = new BufferedReader(new FileReader(name + ".txt"))) {
                    while((s=reader.readLine())!=null){
                        mass = split(s," ");
                        if(mass[0].equals(e.getActionCommand())){
                            mP.ratioItem+=Double.parseDouble(mass[1]);
                            if(mP.critChance+Integer.parseInt(mass[2])>100){
                                mP.critChance = 100;
                                mP.critChanceExtra +=Integer.parseInt(mass[2]);
                            }else{
                                mP.critChance+=Integer.parseInt(mass[2]);
                            }
                            mP.attackDamageItem+=Double.parseDouble(mass[3]);
                            mP.price+=Integer.parseInt(mass[4]);
                            mP.setStats();
                            break;
                        }
                    }
                }catch (IOException ex){
                    ex.printStackTrace();
                }
                butt.setIcon(new ImageIcon(e.getActionCommand() + ".png"));
                butt.setActionCommand(e.getActionCommand()+" "+name);
                break;
            }
        }
    }
}
