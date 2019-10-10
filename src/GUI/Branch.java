package GUI;

import Family.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Branch extends JFrame {


    public JSplitPane panelMain;
    private JPanel panelMember;
    private JPanel panelFamily;
    private JScrollPane scrollMember;
    private JScrollPane scrollFamily;

    static Tree tree;

    public Branch(Tree loadTree,int currentId){

        tree=loadTree;
        int id=currentId;

        createUIMember(tree.getMember(id));
        createUIFamily(tree.getMember(id));

        panelMain = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,scrollMember,scrollFamily);
        panelMain.setDividerLocation(panelMember.getPreferredSize().width+20);
        getContentPane().add(panelMain);

        setPreferredSize(new Dimension(1500, 1000));
    }

    private void createUIMember(Member current){
        JScrollPane info;
        JButton photoMember;
        JLabel nameMember;
        BoxLayout layout;

        scrollMember = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelMember = new JPanel();
        panelMember.setPreferredSize(new Dimension(500,1000));

        layout = new BoxLayout(panelMember,BoxLayout.Y_AXIS);
        panelMember.setLayout(layout);

        photoMember = new ProfileButton(current.getId());
        nameMember = createLabel(current.getName());
        info = createInfoArea("");

        photoMember.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameMember.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.setAlignmentX(Component.CENTER_ALIGNMENT);



        Dimension dim = new Dimension(400,500);
        info.setMinimumSize(dim);
        info.setMaximumSize(dim);
        info.setPreferredSize(dim);


        panelMember.setBackground(new Color (23,88,58));
        panelMember.add(photoMember); panelMember.add(nameMember); panelMember.add(info);
        scrollMember.setViewportView(panelMember);
    }

    private JLabel createLabel(String str){
        JLabel label = new JLabel("", JLabel.CENTER);
        String[] aux;
        String html;


        aux = str.split(" ");
        html="<html><center>";

        for (int i=0;i<aux.length;i=i+3){
            html += aux[i];
            if (i+1<aux.length){
                html+=" "+aux[i+1];
            }
            if (i+2<aux.length){
                html+=" "+aux[i+2];
            }
            if (i+1<aux.length){
                html+="<br />";
            }
        }
        html+="</center></html>";


        label.setText(html);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setForeground(new Color (255,255,255));
        label.setBorder(BorderFactory.createEmptyBorder(20,0,50,0));
        label.setPreferredSize(new Dimension (200,50));
        return label;
    }

    private JLabel createMemberLabel(String str){
        String[] splitter=str.split(" ");
        return(splitter.length==1 ?  createLabel(str) :  createLabel(splitter[0]+" "+splitter[splitter.length-1]));
    }

    private JScrollPane createInfoArea(String str){

        JTextArea textArea = new JTextArea(str);
        textArea.setBorder(BorderFactory.createRaisedBevelBorder());
        textArea.setEditable(false);

        JScrollPane scroll = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scroll.setPreferredSize(new Dimension(400,400));

        return scroll;
    }

    private void createUIFamily(Member current){

        scrollFamily = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelFamily = new JPanel();
        panelFamily.setBackground(new Color(86 ,186,50));

        int aux;

        JButton[] parentsButtonList = new JButton[current.getParents().size()];
        JLabel[] parentsLabelList = new JLabel[current.getParents().size()];
        for (int i=0;i<parentsButtonList.length;i++){
            aux=current.getMemberId("parent",i,tree);
            parentsButtonList[i] = new ProfileButton(aux);
            parentsLabelList[i] = createMemberLabel(tree.getMemberName(aux));
        }

        JButton[] siblingsButtonList = new JButton[current.getSiblings(tree).length];
        JLabel[] siblingsLabelList = new JLabel[current.getSiblings(tree).length];
        for (int i=0;i<siblingsButtonList.length;i++){
            aux=current.getMemberId("sibling",i,tree);
            siblingsButtonList[i] = new ProfileButton(aux);
            siblingsLabelList[i] = createMemberLabel(tree.getMemberName(aux));
        }

        JButton[] childrenButtonList = new JButton[current.getChildren().size()];
        JLabel[] childrenLabelList = new JLabel[current.getChildren().size()];
        for (int i=0;i<childrenButtonList.length;i++){
            aux=current.getMemberId("child",i,tree);
            childrenButtonList[i] = new ProfileButton(aux);
            childrenLabelList[i] = createMemberLabel(tree.getMemberName(aux));
        }

        int maxDimension=Math.max(parentsButtonList.length,Math.max(siblingsButtonList.length,childrenButtonList.length));

        JLabel[] titles = createLabelTitles();

        GroupLayout layout = new GroupLayout(panelFamily);
        panelFamily.setLayout(layout);

        GroupLayout.Group parentsButtonH = (GroupLayout.SequentialGroup) returnGroup(layout.createSequentialGroup(),parentsButtonList);
        GroupLayout.Group parentsButtonV = (GroupLayout.ParallelGroup) returnGroup(layout.createParallelGroup(),parentsButtonList);
        GroupLayout.Group siblingsButtonH = (GroupLayout.SequentialGroup) returnGroup(layout.createSequentialGroup(),siblingsButtonList);
        GroupLayout.Group siblingsButtonV = (GroupLayout.ParallelGroup) returnGroup(layout.createParallelGroup(),siblingsButtonList);
        GroupLayout.Group childrenButtonH = (GroupLayout.SequentialGroup) returnGroup(layout.createSequentialGroup(),childrenButtonList);
        GroupLayout.Group childrenButtonV = (GroupLayout.ParallelGroup) returnGroup(layout.createParallelGroup(),childrenButtonList);

        GroupLayout.Group parentsLabelH = (GroupLayout.SequentialGroup) returnGroup(layout.createSequentialGroup(),parentsLabelList);
        GroupLayout.Group parentsLabelV = (GroupLayout.ParallelGroup) returnGroup(layout.createParallelGroup(),parentsLabelList);
        /*    GroupLayout.Group siblingsLabelH = (GroupLayout.SequentialGroup) returnGroup(layout.createSequentialGroup(),siblingsLabelList);
        GroupLayout.Group siblingsLabelV = (GroupLayout.ParallelGroup) returnGroup(layout.createParallelGroup(),siblingsLabelList);
        GroupLayout.Group childrenLabelH = (GroupLayout.SequentialGroup) returnGroup(layout.createSequentialGroup(),childrenLabelList);
        GroupLayout.Group childrenLabelV = (GroupLayout.ParallelGroup) returnGroup(layout.createParallelGroup(),childrenLabelList);
*/

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(titles[0]).addGroup(parentsButtonH).addGroup(parentsLabelH)
                .addComponent(titles[1]).addGroup(siblingsButtonH)
                .addComponent(titles[2]).addGroup(childrenButtonH));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(titles[0]).addGroup(parentsButtonV).addGroup(parentsLabelV)
                .addComponent(titles[1]).addGroup(siblingsButtonV)
                .addComponent(titles[2]).addGroup(childrenButtonV));

        scrollFamily.setViewportView(panelFamily);
    }

    private GroupLayout.Group returnGroup(GroupLayout.Group group, Component[] components ){


        for (int i=0;i<components.length;i++){
            group.addComponent(components[i],GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE);
        }
        return group;
    }


    private JLabel[] createLabelTitles(){
        JLabel[] titles= new JLabel[4];
        String[] titleText = {"Parents","Siblings","Children","Partner"};

        for (int i=0;i<titles.length;i++){
            titles[i]=new JLabel();
            titles[i].setText(titleText[i]);
            titles[i].setFont(new Font("Arial", Font.PLAIN, 30));
            titles[i].setForeground(new Color (255,255,255));
        }
        return titles;
    }

    public void reload(int currentId){
        createUIMember(tree.getMember(currentId));
        createUIFamily(tree.getMember(currentId));

    }


}
