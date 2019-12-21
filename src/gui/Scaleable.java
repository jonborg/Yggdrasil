package gui;

import java.awt.*;

public interface Scaleable {
     float uiScaler = (float) Math.min(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/1920.0,
            Toolkit.getDefaultToolkit().getScreenSize().getHeight()/1080.0);

}
