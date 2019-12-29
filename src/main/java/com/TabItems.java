package com;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.io.*;

@Slf4j
public class TabItems extends JPanel {

    private final MainPanel mainPanel;

    //ScrollPane scr;
    TabItems(String it, MainPanel mp, Inventory inv) {
        log.debug("TabItems(it: {})", it);

        setLayout(new GridLayout(2, 4));
        this.mainPanel = mp;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String item;
        String[] mass;
        TabItemsListener tabItemsListener = new TabItemsListener(mp, it);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(classloader.getResourceAsStream("txt/" + it + ".txt")))) {
            while ((item = bf.readLine()) != null) {
                log.debug(item);
                mass = item.split(" ");
                String name = mass[0];
                byte[] image = IOUtils.toByteArray(classloader.getResourceAsStream("images/" + name + ".png"));
                ImageIcon icon = new ImageIcon(image);
                JButton butt = new JButton(icon);
                butt.addActionListener(tabItemsListener);
                butt.setActionCommand(name);
                add(butt);
            }
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}