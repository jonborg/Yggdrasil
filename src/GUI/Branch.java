package GUI;

import static javax.swing.GroupLayout.Alignment.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Flow;

public class Branch {


    public JPanel panelMain;
    private JSplitPane panelSplitter;
    private JPanel panelMember;
    private JPanel panelFamily;
    private JScrollPane scrollFamily;


    private void createUIComponents(){
        createUIMember();
        createUIFamily();
    }

    private void createUIMember(){
        JScrollPane info;

        JButton photoMember;
        JLabel nameMember;

        panelSplitter = new JSplitPane();
        panelMember = new JPanel();
        panelMember.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        photoMember = createProfileButton("");
        nameMember = createLabel("");
        info = createInfoArea("");

        System.out.println(c.insets);
        c.gridx = 0; c.gridy=0; c.weighty=0; c.insets=new Insets(50,0,0,0); panelMember.add(photoMember, c);
        c.gridy=1; c.weighty=0.1; c.insets=new Insets(0,0,40,0); panelMember.add(nameMember,c);
        c.gridy=2;  c.weighty=0; panelMember.add(info,c);
        c.gridy=3;  c.insets=new Insets(10,0,50,0); panelMember.add(new JButton("Edit Profile"),c);
    }

    private JButton createProfileButton(String path)   {
        JButton profile = new JButton();
        Dimension dim;
        Color color;


        try {
            BufferedImage buttonIcon = ImageIO.read(new File(path));
            profile.setIcon(new ImageIcon(buttonIcon));
        }catch (IOException e){
            profile.setFont(new Font("Arial", Font.PLAIN, 30));
            profile.setText("<html><center>Add<br />profile<br />picture</center></html>");
        }

        dim= new Dimension(200,200);
        profile.setPreferredSize(dim);
        profile.setMaximumSize(dim);
        profile.setMinimumSize(dim);

        profile.setVerticalTextPosition(SwingConstants.CENTER);
        profile.setHorizontalTextPosition(SwingConstants.CENTER);

        color = new Color (23,88,58);
        profile.setBackground(color);
        profile.setForeground(new Color (255,255,255));


        profile.setBorder(BorderFactory.createRaisedBevelBorder());
        return profile;

    }

    private JLabel createLabel(String str){
        JLabel label = new JLabel();
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

        return label;
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

    private void createUIFamily(){

        scrollFamily = new JScrollPane();
        panelFamily = new JPanel();


        JButton[] aux1 = new JButton[10];
        for (int i=0;i<aux1.length;i++){
            aux1[i]=createProfileButton("");
        }
        JButton[] aux2 = new JButton[3];
        for (int i=0;i<aux2.length;i++){
            aux2[i]=createProfileButton("");
        }
        JButton[] aux3 = new JButton[1];
        for (int i=0;i<aux3.length;i++){
            aux3[i]=createProfileButton("");
        }
        int maxDimension=Math.max(aux1.length,Math.max(aux2.length,aux3.length));

       

        GroupLayout layout = new GroupLayout(panelFamily);
        panelFamily.setLayout(layout);

        GroupLayout.Group parentsH = (GroupLayout.SequentialGroup) returnGroup(layout.createSequentialGroup(),aux1);
        GroupLayout.Group parentsV = (GroupLayout.ParallelGroup) returnGroup(layout.createParallelGroup(),aux1);
        GroupLayout.Group siblingsH = (GroupLayout.SequentialGroup) returnGroup(layout.createSequentialGroup(),aux2);
        GroupLayout.Group siblingsV = (GroupLayout.ParallelGroup) returnGroup(layout.createParallelGroup(),aux2);
        GroupLayout.Group childrenH = (GroupLayout.SequentialGroup) returnGroup(layout.createSequentialGroup(),aux3);
        GroupLayout.Group childrenV = (GroupLayout.ParallelGroup) returnGroup(layout.createParallelGroup(),aux3);


        /*
        layout.setHorizontalGroup(layout.createParallelGroup().addComponent(aux).addComponent(aux2));
        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(aux).addComponent(aux2));
        */
        layout.setHorizontalGroup(layout.createParallelGroup().addGroup(parentsH).addGroup(siblingsH).addGroup(childrenH));
        layout.setVerticalGroup(layout.createSequentialGroup().addGroup(parentsV).addGroup(siblingsV).addGroup(childrenV));
    }

    private GroupLayout.Group returnGroup(GroupLayout.Group group, Component[] components ){


        for (int i=0;i<components.length;i++){
            group.addComponent(components[i]);
        }
        return group;
    }


    private void createLabelTitles(JPanel panel){
        JLabel[] titles= new JLabel[4];
        GridBagConstraints c = new GridBagConstraints();
        String[] titleText = {"Parents","Siblings","Children","Partner"};

        for (int i=0;i<titles.length;i++){
            titles[i]=new JLabel();
            titles[i].setText(titleText[i]);
            titles[i].setFont(new Font("Arial", Font.PLAIN, 30));
            titles[i].setForeground(new Color (255,255,255));
            c.anchor=GridBagConstraints.NORTHWEST;
            if (i<titles.length-1) {
                c.gridy = i * 3;
                panel.add(titles[i],c);

            }else{
                c.gridx=1; c.gridy=0;
            }

        }
    }



}
