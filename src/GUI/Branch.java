package GUI;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Branch {

    public JPanel panelMain;
    private JSplitPane panelSplitter;
    private JPanel panelMember;
    private JPanel panelFamily;






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

    private JLabel createLabel(@NotNull String str){
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

        panelFamily = new JPanel();

        GridBagConstraints c = new GridBagConstraints();

        panelFamily.setLayout(new GridBagLayout());


        int n_siblings=1;
        int n_children=1;

        createLabelTitles(panelFamily);
        c.weightx=0.1;c.weighty=1; c.insets = new Insets(0,50,0,0); c.anchor=GridBagConstraints.LINE_START;

        int maxi=1;
        for (int j=0;j<3;j++) {

            for (int i = 0; i < maxi; i++) {
                c.gridx = i;
                c.gridy = 1 + j * 3;
                panelFamily.add(createProfileButton(""), c);
            }
        }

    }

    private void createLabelTitles(JPanel panel){
        JLabel[] titles= new JLabel[3];
        GridBagConstraints c = new GridBagConstraints();
        String[] titleText = {"Parents","Siblings","Children"};

        for (int i=0;i<titles.length;i++){
            titles[i]=new JLabel();
            titles[i].setText(titleText[i]);
            titles[i].setFont(new Font("Arial", Font.PLAIN, 30));
            titles[i].setForeground(new Color (255,255,255));

            c.anchor=GridBagConstraints.LINE_START;
            c.gridy=i*3;
            panel.add(titles[i],c);
        }
    }
}
