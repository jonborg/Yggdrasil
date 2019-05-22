package GUI;

import javax.swing.*;

public class Branch {

    public JPanel panelMain;
    private JSplitPane panelSplitter;
    private JPanel panelMember;
    private JPanel panelFamily;

    private void createUIComponents(){
        panelMain = new JPanel();
        panelSplitter = new JSplitPane();
        System.out.println(panelSplitter.getMaximumDividerLocation());
    }

}
