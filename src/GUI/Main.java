package GUI;

import Family.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame("Yggdrasil");
        window.setContentPane(new Branch().panelMain);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }
}
