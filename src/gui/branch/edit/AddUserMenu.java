package gui.branch.edit;

import gui.Scaleable;

import javax.swing.*;
import java.awt.*;

public class AddUserMenu extends JFrame implements Scaleable {

    final int FRAME_WIDTH = (int) (uiScaler*700);
    final int FRAME_HEIGHT = (int) (uiScaler*500);


    public AddUserMenu(){
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    }
}
