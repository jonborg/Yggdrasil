package gui.branch.components;

import gui.Scaleable;
import gui.branch.edit.CreateMemberMenu;

import javax.swing.*;
import java.awt.*;

public class MenuBranch extends JMenuBar implements Scaleable {

    private final String[] mainMenu = {"File", "Edit"};
    private final String[][] subMenu = {{"Save Tree","Save As", "Load Tree"},
                                        {"Create Member", "Delete Member"}};

    public MenuBranch(){
        int i=0;
        for (String menuName:mainMenu){
            JMenu menu = new JMenu(menuName);
            menu.setFont(new Font(FONT_TYPE,Font.PLAIN,MENU_SIZE));
            for (String subName:subMenu[i]){
                JMenuItem subM = new JMenuItem(subName);
                subM.setFont(new Font(FONT_TYPE, Font.PLAIN,SUBMENU_SIZE));
                addListener(subM);
                menu.add(subM);
            }
            add(menu);
            i++;
        }
    }


    private void addListener(JMenuItem subMenu){
        final String mode = subMenu.getText();
        if(mode.equals("Create Member")) subMenu.addActionListener(arg0->{new CreateMemberMenu();});
    }

}
