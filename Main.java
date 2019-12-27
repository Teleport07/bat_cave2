package Lol;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    new MainPanel();
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
