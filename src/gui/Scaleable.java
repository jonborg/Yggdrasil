package gui;

import java.awt.*;

public interface Scaleable {
     float uiScaler = (float) Math.min(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1920.0,
            Toolkit.getDefaultToolkit().getScreenSize().getHeight()/1080.0);

     String FONT_TYPE = "Arial";
     int TITLE_SIZE = (int) (uiScaler * 30);
     int LABEL_SIZE = (int) (uiScaler * 20);
     int MENU_SIZE = (int) (uiScaler * 15);
     int SUBMENU_SIZE = (int) (uiScaler * 15);

     int BUTTON_WIDTH =(int) (uiScaler * 150);
     int BUTTON_HEIGHT = (int) (uiScaler * 150);



}
