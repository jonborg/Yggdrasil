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
    int id;

    public Branch(Tree loadTree,int currentId){
        tree=loadTree;
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

        photoMember = new ProfileButton(current.getId(), current.getImagePath());
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
        String html;


        html="<html><center>";

        html+=generateNameLabel(str,15);

        html+="</center></html>";


        label.setText(html);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setForeground(new Color (255,255,255));
        //label.setBorder(BorderFactory.createEmptyBorder(20,0,50,0));
        //label.setPreferredSize(new Dimension (200,50));
        return label;
    }

    private JLabel createMemberLabel(String str){
        JLabel label = new JLabel();

        label.setText("<html>" + (generateNameLabel(str,15)) + "</html>");
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setForeground(new Color (255,255,255));
        //label.setBorder(BorderFactory.createEmptyBorder(20,0,50,0));
        //label.setPreferredSize(new Dimension (200,50));
        return label;
    }

    private String generateNameLabel(String bigName, int maxLetters){
        String[] sepName;
        String result="";
        int counter=0;


        sepName = bigName.split(" ");
        for (String name:sepName){
            if (result.equals("")){
                result=name;
                counter=name.length();
                continue;
            }
            if (counter+name.length()+1<=maxLetters){
                result+=" "+name;
                counter+=name.length()+1;
            }else{
                result+="<br />"+name;
                counter=name.length();
            }
        }
        return result;
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


        JLabel[] titles = createLabelTitles();

        GridBagLayout layout = new GridBagLayout();
        panelFamily.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.FIRST_LINE_START;


        for (int i=0; i<titles.length; i++){
            addGridBagComponent(panelFamily,titles[i],0,3*i,
                    Math.max(current.getParents().size(),Math.max(current.getSiblings(tree).length,current.getChildren().size())), 1,
                    1,2,GridBagConstraints.LINE_START);
        }


        for (int i=0;i<current.getParents().size();i++){
            aux=current.getMemberId("parent",i,tree);
            System.out.println(current.getImagePath());
            addGridBagComponent(panelFamily,new ProfileButton(aux,tree.getMember(aux).getImagePath()),i,1,1,1,0,1,GridBagConstraints.NORTHWEST);
            addGridBagComponent(panelFamily,createMemberLabel(tree.getMemberName(aux)),i,2,1,1,0,1,GridBagConstraints.NORTHWEST);
        }

        for (int i=0;i<current.getSiblings(tree).length;i++){
            aux=current.getMemberId("sibling",i,tree);
            addGridBagComponent(panelFamily,new ProfileButton(aux,tree.getMember(aux).getImagePath()),i,4,1,1,0,0,GridBagConstraints.NORTHWEST);
            addGridBagComponent(panelFamily,createMemberLabel(tree.getMemberName(aux)),i,5,1,1,0,0,GridBagConstraints.NORTHWEST);

        }

        for (int i=0;i<current.getChildren().size();i++){
            aux=current.getMemberId("child",i,tree);
            addGridBagComponent(panelFamily,new ProfileButton(aux,tree.getMember(aux).getImagePath()),i,7,1,1,0,0,GridBagConstraints.NORTHWEST);
            addGridBagComponent(panelFamily,createMemberLabel(tree.getMemberName(aux)),i,8,1,1,0,0,GridBagConstraints.NORTHWEST);
        }

        scrollFamily.setViewportView(panelFamily);

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

    private JLabel[] createLabelTitles(){
        JLabel[] titles= new JLabel[4];
        String[] titleText = {"Parents","Siblings","Children","Partner"};

        for (int i=0;i<titles.length;i++){
            titles[i]=new JLabel();
            titles[i].setText(titleText[i]);
            titles[i].setFont(new Font("Arial", Font.BOLD, 40));
            titles[i].setForeground(new Color (255,255,255));
        }
        return titles;
    }

    private void removeContent(Container container){
        container.removeAll();
        container.revalidate();
        container.repaint();
    }


}
