package gui.branch.edit;

import gui.Scaleable;

import javax.swing.*;
import java.awt.*;

public class CreateMemberMenu extends JDialog implements Scaleable {

    final int FRAME_WIDTH = (int) (uiScaler*500);
    final int FRAME_HEIGHT = (int) (uiScaler*700);


    public CreateMemberMenu(){

        setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setPanel();
        setModal(true);
        setVisible(true);

    }

    private void setPanel(){
        JPanel panel = new JPanel();
        panel.setVisible(true);

        getContentPane().add(panel);
    }
}
