package gui.branch.components;

import family.Member;
import gui.Scaleable;
import gui.branch.display.Branch;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProfileButton extends JButton implements Scaleable {
    int id;
    Branch branch;

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
            setFont(new Font("Arial", Font.PLAIN, LABEL_SIZE));
            setText("<html><center>Add<br />profile<br />picture</center></html>");
        } catch (NullPointerException e) {
            setFont(new Font("Arial", Font.PLAIN, LABEL_SIZE));
            setText("<html><center>Add<br />profile<br />picture</center></html>");
        }


        dim = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
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
        branch = (Branch) SwingUtilities.getRoot(this);
        System.out.println("id="+id+" branchId="+branch.getCurrentId());
        if (branch.getCurrentId()!=id) {
            branch.reload(id);
        }else {
            try {
                System.out.println("Adding picture picture");
                JFileChooser loadImage = new JFileChooser();
                loadImage.showOpenDialog(null);
                imageLoader(loadImage.getSelectedFile());
                setText("");
                branch.getTree().getMember(id).setImagePath(loadImage.getSelectedFile().getAbsolutePath());
                System.out.println("Selected file: " + loadImage.getSelectedFile().getAbsolutePath());
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


