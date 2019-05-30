package GUI;

import Family.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Tree tree;
        FileIO fileIO=new FileIO();
        try {
            tree = fileIO.loadFile();
            JFrame frame = new Branch(tree,3);
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }catch(Exception e) {
            System.out.println("File not found");
        }
    }
}

