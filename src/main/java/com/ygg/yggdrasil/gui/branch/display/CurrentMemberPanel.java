package gui.branch.display;

import family.Member;
import family.Tree;
import gui.Scaleable;
import gui.branch.components.ProfileButton;
import gui.branch.components.labels.MemberLabel;

import javax.swing.*;
import java.awt.*;

public class CurrentMemberPanel extends JPanel implements Scaleable {
    Tree tree;
    Member current;

    int FRAME_WIDTH= (int) (uiScaler*500);
    int FRAME_HEIGHT= (int) (uiScaler*500);

    final int INFO_WIDTH = (int) (uiScaler*400);
    final int INFO_HEIGHT = (int) (uiScaler*500);

    public CurrentMemberPanel(Tree tree, Member current){
        super();
        this.tree=tree;
        this.current=current;

        setPreferredSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));

        BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
        setLayout(layout);
        setBackground(new Color (23,88,58));


        ProfileButton photoMember = new ProfileButton(current.getId(), current.getImagePath());
        MemberLabel nameMember = new MemberLabel(current.getName(),true);
        JScrollPane info = createInfoArea("");

        photoMember.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameMember.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension dim = new Dimension(INFO_WIDTH,INFO_HEIGHT);
        info.setMinimumSize(dim);
        info.setMaximumSize(dim);
        info.setPreferredSize(dim);

        add(photoMember); add(nameMember); add(info);
    }

    private JScrollPane createInfoArea(String str){

        JTextArea textArea = new JTextArea(str);
        textArea.setBorder(BorderFactory.createRaisedBevelBorder());
        textArea.setEditable(false);

        JScrollPane scroll = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scroll.setPreferredSize(new Dimension(INFO_WIDTH,INFO_HEIGHT));

        return scroll;
    }

}
