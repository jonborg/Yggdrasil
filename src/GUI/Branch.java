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


        JLabel[] titles = createLabelTitles();

        GridBagLayout layout = new GridBagLayout();
        panelFamily.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.FIRST_LINE_START;




        for (int i=0;i<current.getParents().size();i++){
            aux=current.getMemberId("parent",i,tree);
            System.out.println(aux+" "+i+current.getParents());
            addGridBagComponent(panelFamily,new ProfileButton(current.getMemberId("parent",aux,tree)),1,i,1,1,GridBagConstraints.CENTER);
            addGridBagComponent(panelFamily,createMemberLabel(tree.getMemberName(aux)),2,i,1,1,GridBagConstraints.CENTER);

            panelFamily.add(new ProfileButton(current.getMemberId("parent",i,tree)));
           // parentsLabelList[i] = ;
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

        scrollFamily.setViewportView(panelFamily);

    }

    private void addGridBagComponent(JPanel p, JComponent c, int x, int y,
                         int width, int height, int align) {

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = align;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        p.add(c, gbc);

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
