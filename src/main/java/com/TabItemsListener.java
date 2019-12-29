package com;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TabItemsListener implements ActionListener {

    private final MainPanel mainPanel;
    private final String name;
    private final List<Params> params;

    TabItemsListener(MainPanel mainPanel, String it) {
        log.debug("TabItemsListener(mainPanel: {}, it: {})", mainPanel, it);

        this.mainPanel = mainPanel;
        this.name = it;

        params = new ArrayList<>();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String s;
        String[] mass;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(classloader.getResourceAsStream("txt/" + name + ".txt")))) {
            while ((s = reader.readLine()) != null) {
                mass = s.split(" ");
                Params param = new Params();
                param.param1 = mass[0];
                param.param2 = Double.parseDouble(mass[1]);
                param.param3 = Integer.parseInt(mass[2]);
                param.param4 = Double.parseDouble(mass[3]);
                param.param5 = Integer.parseInt(mass[4]);
                params.add(param);
            }
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if ("InfinityEdge".equals(e.getActionCommand().toString())) {
            mainPanel.setCritCof(2.2);
            mainPanel.setCritCof_Jhin(1.68);
        }
        for (JButton butt : mainPanel.getInv().invItems) {
            if ("0".equals(butt.getActionCommand())) {
                for (Params param : params) {
                    if (param.param1.equals(e.getActionCommand())) {
                        mainPanel.setRatioItem(mainPanel.getRatioItem() + param.param2);
                        if (mainPanel.getCritChance() + param.param3 > 100) {
                            mainPanel.setCritChance(100);
                            mainPanel.setCritChanceExtra(mainPanel.getCritChanceExtra() + param.param3);
                        } else {
                            mainPanel.setCritChance(mainPanel.getCritChance() + param.param3);
                        }
                        mainPanel.setAttackDamageItem(mainPanel.getAttackDamageItem() + param.param4);
                        mainPanel.setPrice(mainPanel.getPrice() + param.param5);
                        mainPanel.setStats();
                        break;
                    }
                }
                try {
                    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                    byte[] image = IOUtils.toByteArray(classloader.getResourceAsStream("images/" + e.getActionCommand() + ".png"));
                    butt.setIcon(new ImageIcon(image));
                } catch (IOException ex) {
                    log.error(ex.getMessage(), ex);
                }
                butt.setActionCommand(e.getActionCommand() + " " + name);
                break;
            }
        }
    }

    private static class Params {
        String param1;
        Double param2;
        Integer param3;
        Double param4;
        Integer param5;
    }
}
