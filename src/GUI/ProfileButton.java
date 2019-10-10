package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProfileButton extends JButton {
    int id;
    int DIMENSION_WIDTH=200;
    int DIMENSION_HEIGHT=200;

    public ProfileButton(int setId) {

        id = setId;
        Dimension dim;
        Color color;

        setFont(new Font("Arial", Font.PLAIN, 30));
        setText("<html><center>Add<br />profile<br />picture</center></html>");

        dim = new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setMinimumSize(dim);

        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);

        color = new Color(23, 88, 58);
        setBackground(color);
        setForeground(new Color(255, 255, 255));


        setBorder(BorderFactory.createRaisedBevelBorder());
    }

    public ProfileButton(int setId,String path) {

        id = setId;
        Dimension dim;
        Color color;


        try {
            BufferedImage buttonIcon = ImageIO.read(new File(path));
            setIcon(new ImageIcon(buttonIcon));
        } catch (IOException e) {
            setFont(new Font("Arial", Font.PLAIN, 30));
            setText("<html><center>Add<br />profile<br />picture</center></html>");
        }

        dim = new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setMinimumSize(dim);

        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);

        color = new Color(23, 88, 58);
        setBackground(color);
        setForeground(new Color(255, 255, 255));


        setBorder(BorderFactory.createRaisedBevelBorder());
    }

    @Override
    public void addActionListener(ActionListener l) {
        super.addActionListener(l);
        Branch branch = (Branch) SwingUtilities.getRoot(this);
        branch.reload(id);
    }
}