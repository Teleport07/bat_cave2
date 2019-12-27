package com;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import static com.sun.javafx.util.Utils.split;

@Slf4j
public class TabItems extends JPanel {

  MainPanel mainP;
  Inventory inven;
  String item;
  String[] mass;

  //ScrollPane scr;
  TabItems(String it, MainPanel mp, Inventory inv) {
    setLayout(new GridLayout(2, 4));
    mainP = mp;
    int count = 0;
    //File f = new File(it+".txt");
    try (BufferedReader bf = new BufferedReader(new FileReader(it + ".txt"))) {
      String s = "";
      while ((s = bf.readLine()) != null) {
        count++;
      }
    } catch (IOException ex) {
      log.error(ex.getMessage(), ex);
    }
    try (BufferedReader bf = new BufferedReader(new FileReader(it + ".txt"))) {
      mass = new String[4];
      log.debug("Count {}", count);
      for (int i = 0; i < count; i++) {
        item = bf.readLine();
        log.debug(item);
        mass = split(item, " ");
        JButton butt = new JButton(new ImageIcon(mass[0] + ".png"));
        butt.addActionListener(new TabItemsListener(mp, it));
        butt.setActionCommand(mass[0]);
        add(butt);
      }
    } catch (IOException ex) {
      log.error(ex.getMessage(), ex);
    }
  }
}