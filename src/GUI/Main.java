package GUI;

import Family.*;
import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Tree tree;
        FileIO fileIO=new FileIO();
        try {
            tree = fileIO.loadFile();
            JFrame frame = new Branch(tree,1);
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

