package Lol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static com.sun.javafx.util.Utils.split;

public class Inventory extends JPanel{
    JButton b1,b2,b3,b4,b5,b6;
    Label lab;
    ArrayList<JButton> invItems;
    //MainPanel mP;
    Inventory(MainPanel mp){
        //mP = mp;
        setLayout(new GridLayout(2,3,5,5));
        setBorder(BorderFactory.createLineBorder(Color.gray,1));
        b1 = new JButton();
        b2 = new JButton();
        b3 = new JButton();
        b4 = new JButton();
        b5 = new JButton();
        b6 = new JButton();

        invItems = new ArrayList<JButton>();
        invItems.add(b1);
        invItems.add(b2);
        invItems.add(b3);
        invItems.add(b4);
        invItems.add(b5);
        invItems.add(b6);

        for(JButton butt : invItems){
            butt.setPreferredSize(new Dimension(80,80));
            butt.setActionCommand("0");
            butt.addActionListener(new InventoryListener(mp, true));
            add(butt);
        }
    }

    /*@Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()!="0"){
            String mass[];
            String mass1[];

            mass = split(e.getActionCommand()," ");
            if(mass[0].equals("BOTRK")){
                new DialogHp(mP);
            }
            try(BufferedReader reader = new BufferedReader(new FileReader(mass[1]+".txt"))){
                String s;
                while((s=reader.readLine())!=null){
                    mass1 = split(s," ");
                    if(mass1[0].equals(mass[0])){
                        mP.ratioItem-=Double.parseDouble(mass1[1]);
                        if(mP.critChanceExtra!=0){
                            mP.critChanceExtra-=Integer.parseInt(mass1[2]);
                        }else if((mP.critChance-Integer.parseInt(mass1[2]))<0){
                            mP.critChance = 0;
                        }else{
                            mP.critChance-=Integer.parseInt(mass1[2]);
                        }
                        mP.attackDamageItem-=Double.parseDouble(mass1[3]);
                        mP.price-=Integer.parseInt(mass1[4]);
                        mP.setStats();
                        break;
                    }
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
            ((JButton) e.getSource()).setIcon(new ImageIcon());
            ((JButton) e.getSource()).setActionCommand("0");
        }

    }*/
}
