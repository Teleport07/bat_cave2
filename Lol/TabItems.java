package Lol;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.sun.javafx.util.Utils.split;

public class TabItems extends JPanel {
    MainPanel mainP;
    String item;
    String mass[];

    TabItems(String it, MainPanel mp, Inventory inv) {
        setLayout(new GridLayout(2, 4));
        mainP = mp;
        int count = 0;
        try (BufferedReader bf = new BufferedReader(new FileReader(it + ".txt"))) {
            String s = "";
            while ((s = bf.readLine()) != null) {
                count++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (BufferedReader bf = new BufferedReader(new FileReader(it + ".txt"))) {
            mass = new String[4];
            System.out.println("Count " + count);
            for (int i = 0; i < count; i++) {
                item = bf.readLine();
                System.out.println(item);
                mass = split(item, " ");
                JButton butt = new JButton(new ImageIcon(mass[0] + ".png"));
                butt.addActionListener(new TabItemsListener(mp, it));
                butt.setActionCommand(mass[0]);
                add(butt);
            }
        } catch (IOException ioE) {
            ioE.printStackTrace();
        }
    }
}
