package GUI;

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
    private JButton editButton;
    private JScrollPane info;

    private JButton photoMember;
    private JLabel nameMember;


    private void createUIComponents(){
        createUIMember();

    }

    private void createUIMember(){
        panelSplitter = new JSplitPane();
        panelMember = new JPanel();


        photoMember = createProfileButton("");
        nameMember = createLabel("");
        info = createInfoArea("");

        panelMember.add(photoMember);
        panelMember.add(nameMember);
        panelMember.add(info);
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
        color = new Color (255,255,255);
        profile.setForeground(color);


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
        label.setForeground(new Color(255,255,255));

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
}
