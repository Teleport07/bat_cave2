package com;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

@Slf4j
public class ComboBoxRenderer extends JLabel implements ListCellRenderer {

  ComboBoxRenderer() {
    setOpaque(true);
    setHorizontalAlignment(CENTER);
    setVerticalAlignment(CENTER);
  }

  @Override
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    String name = (String) value;
    if (isSelected) {
      setBackground(list.getSelectionBackground());
      setForeground(list.getSelectionForeground());
    } else {
      setBackground(list.getBackground());
      setForeground(list.getForeground());
    }

    try {
      ClassLoader classloader = Thread.currentThread().getContextClassLoader();
      byte[] image = IOUtils.toByteArray(classloader.getResourceAsStream("images/" + name + ".png"));
      ImageIcon icon = new ImageIcon(image);
      setIcon(icon);
      setText(name);
      setFont(list.getFont());
    } catch (IOException ex) {
      log.error(ex.getMessage(), ex);
    }
    return this;
  }
}