package com;

import javax.swing.*;
import java.awt.*;

public class Formula extends JPanel {

  Formula(Label l1, Label l2, Label l3, Label l4, Label l5) {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(l1);
    add(l2);
    add(l3);
    add(l4);
    add(l5);
  }
}
