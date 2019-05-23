package Family;

import GUI.*;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args){
        debugFamily();
    }



    public static void debugFamily(){
        Tree tree=new Tree();
        FileIO fileIO=new FileIO();
        Member[] person = new Member[5];

        person[0] = new Member("Jorge");
        person[1] = new Member("Ana");

        person[2] = new Member("João");
        person[3] = new Member("Inês");
        person[4] = new Member("Raul");

        for (int i=0;i<5;i++) {
            tree.addMember(person[i]);
        }
        person[0].associateChild(tree,person[2]);
        person[0].associateChild(tree,person[3]);
        person[1].associateChild(tree,person[4]);

        person[0].associatePartner(tree,person[1]);


        try{
            fileIO.saveFile(tree);
            fileIO.loadFile();
        }catch(IOException e) {
            System.out.print(e);
        }


        for (int i=0;i<5;i++){
            System.out.println(person[i].id);
            System.out.println("Parents:\r\n"+person[i].parents);
            System.out.println("Siblings:");
            for (Member s: person[i].getSiblings(tree))
                System.out.println(s.id+" ");
            System.out.println("Children:\r\n"+person[i].children);
            System.out.println("Partner:\r\n"+person[i].partner);
            System.out.println("\r\n");
        }

    }

}
