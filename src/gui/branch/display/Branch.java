package gui.branch.display;

import family.*;
import gui.Scaleable;
import gui.branch.components.ProfileButton;
import gui.branch.components.labels.MemberLabel;

import javax.swing.*;
import java.awt.*;

public class Branch extends JFrame implements Scaleable {


    public JSplitPane panelMain;
    private JPanel panelMember;
    private JPanel panelFamily;
    private JScrollPane scrollMember;
    private JScrollPane scrollFamily;

    private static Tree tree;
    private int id;

    final int FRAME_WIDTH = (int) (uiScaler*1500);
    final int FRAME_HEIGHT = (int) (uiScaler*1000);

    final int TITLE_SIZE = (int) (uiScaler*40);


    public int getCurrentId(){
        return this.id;
    }

    public Tree getTree(){
        return this.getTree();
    }

    public Branch(Tree tree, int currentId){
        this.tree=tree;
        reload(currentId);
    }

    public void reload(int currentId) {
        if(scrollMember!=null && scrollFamily!=null){
            removeContent(getContentPane());
        }

        id=currentId;

        createUIMember(tree.getMember(currentId));
        createUIFamily(tree.getMember(currentId));

        panelMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,scrollMember,scrollFamily);
        panelMain.setDividerLocation(panelMember.getPreferredSize().width+20);
        getContentPane().add(panelMain);

        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    }

    private void createUIMember(Member current){
        JScrollPane info;
        JButton photoMember;
        JLabel nameMember;
        BoxLayout layout;

        scrollMember = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelMember = new CurrentMemberPanel(tree,current);
        scrollMember.setViewportView(panelMember);
    }

    private void createUIFamily(Member current){

        scrollFamily = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelFamily = new FamilyPanel(tree,current);
        scrollFamily.setViewportView(panelFamily);
    }


    private void removeContent(Container container){
        container.removeAll();
        container.revalidate();
        container.repaint();
    }


}
