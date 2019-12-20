package GUI;

import Family.Member;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProfileButton extends JButton {
    int id;
    int DIMENSION_WIDTH = 200;
    int DIMENSION_HEIGHT = 200;

    public ProfileButton(Member member) {
        this(member.getId(), member.getImagePath());
    }

    public ProfileButton(int setId, String path) {

        id = setId;
        Dimension dim;
        Color color;

        try {
            BufferedImage buttonIcon = ImageIO.read(new File(path));
            setIcon(new ImageIcon(buttonIcon));
        } catch (IOException e) {
            setFont(new Font("Arial", Font.PLAIN, 30));
            setText("<html><center>Add<br />profile<br />picture</center></html>");
        } catch (NullPointerException e) {
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

        this.addActionListener(this::actionPerformed);
    }

    public void actionPerformed(ActionEvent e) {
        Branch branch = (Branch) SwingUtilities.getRoot(this);
        System.out.println("id="+id+" branchId="+branch.id);
        if (branch.id!=id) {
            branch.reload(id);
        }else {
            try {
                System.out.println("Adding picture picture");
                JFileChooser loadImage = new JFileChooser();
                loadImage.showOpenDialog(null);
                imageLoader(loadImage.getSelectedFile());
                setText("");
                branch.tree.getMember(id).setImagePath(loadImage.getSelectedFile().getAbsolutePath());
                System.out.println(loadImage.getSelectedFile().getAbsolutePath());
            }catch(IOException exception){
                System.out.println("File not found");
            }catch(NullPointerException exception){
                System.out.println("No image was selected.");
            }
        }
    }

    private void imageLoader(File image) throws IOException,NullPointerException{
        try {
            BufferedImage buttonIcon = ImageIO.read(image);
            setIcon(new ImageIcon(buttonIcon));
        } catch (IOException e) {
            throw new IOException();
        } catch (IllegalArgumentException|NullPointerException e) {
            throw new NullPointerException();
        }
    }
}


