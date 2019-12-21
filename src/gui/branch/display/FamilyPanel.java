package gui.branch.display;

import family.Member;
import family.Tree;
import gui.Scaleable;
import gui.branch.components.ProfileButton;
import gui.branch.components.labels.MemberLabel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FamilyPanel extends JPanel implements Scaleable {

    Tree tree;
    Member current;

    public FamilyPanel(Tree tree, Member current){
        super();
        this.tree=tree;
        this.current=current;

        setBackground(new Color(86 ,186,50));
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.FIRST_LINE_START;

        MemberLabel[] titles = generateTitles();
        for (int i=0; i<titles.length; i++){
            addGridBagComponent(this,titles[i],0,3*i,
                    Math.max(current.getParents().size(),Math.max(current.getSiblings(tree).size(),current.getChildren().size())), 1,
                    1,2,GridBagConstraints.LINE_START);
        }

        generateMemberButtons(current.getParents(),1);
        generateMemberButtons(current.getSiblings(tree),4);
        generateMemberButtons(current.getChildren(),7);
    }

    private void generateMemberButtons(List<Integer> list, int yi){
        int x=0;
        int y;

        for (int id:list){
            y=yi;

            Member member = tree.getMember(id);
            addGridBagComponent(this,new ProfileButton(id,member.getImagePath()),x,y++,1,1,0,1,GridBagConstraints.NORTHWEST);
            addGridBagComponent(this,new MemberLabel(member.getName(),false),x++,y,1,1,0,1,GridBagConstraints.NORTHWEST);
        }

    }


    private MemberLabel[] generateTitles(){
        MemberLabel[] titles= new MemberLabel[4];
        String[] titleText = {"Parents","Siblings","Children","Partner"};

        for (int i=0;i<titles.length;i++){
            titles[i]=new MemberLabel(titleText[i]);
            titles[i].setBold();
        }
        return titles;
    }

    private void addGridBagComponent(JPanel p, JComponent c, int x, int y,
                                     int width, int height,int weightx, int weighty, int align) {

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.insets = (c instanceof JLabel)? new Insets(5,5,60,5) : new Insets(-50+5,5,5,5);
        gbc.anchor = align;
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        p.add(c, gbc);

    }


}
