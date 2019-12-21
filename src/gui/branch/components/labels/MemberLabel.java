package gui.branch.components.labels;

import gui.Scaleable;

import javax.swing.*;
import java.awt.*;

public class MemberLabel extends JLabel implements Scaleable {

    int LABEL_SIZE = 30;

    String FONT_TYPE="Arial";

    public MemberLabel(String str){
        super(str);
        rescaleDimensions(uiScaler);
        setSettings();
    }

    public MemberLabel(String str, boolean center){
        super("", JLabel.CENTER);
        rescaleDimensions(uiScaler);
        if(!center) str = filterName(str);

        String html = "<html>";
        if(center) html += "<center>";
        html += generateNameLabel(str,15);
        if(center) html += "</center>";
        html += "</html>";

        setText(html);
        setSettings();
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

    public void setBold(int size){
        setFont(new Font(FONT_TYPE,Font.BOLD,size));
    }

    private String filterName(String name){
        String [] nameSplit = name.split(" ");
        int l = nameSplit.length;
        if (l > 2) return nameSplit[0]+" "+nameSplit[1]+" "+nameSplit[l-1];
        return name;
    }


    private void setSettings(){
        setFont(new Font(FONT_TYPE, Font.PLAIN, LABEL_SIZE));
        setForeground(new Color (255,255,255));
    }

    private void rescaleDimensions(float scaler){
        LABEL_SIZE = (int) (scaler*LABEL_SIZE);
    }
}
