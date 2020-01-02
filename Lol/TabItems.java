package Lol;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.sun.javafx.util.Utils.split;

public class TabItems extends JPanel {
    MainPanel mainP;
    String item;
    String mass[];

    TabItems(String it, MainPanel mp) {
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
            for (int i = 0; i < count; i++) {
                item = bf.readLine();
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
