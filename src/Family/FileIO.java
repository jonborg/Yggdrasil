package Family;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class FileIO {
    String fileName="output.csv";


    public void saveFile(Tree tree) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(Tree2CSV(tree));
        writer.close();
    }

    public Tree loadFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        Tree tree=CSV2Tree(reader);
        reader.close();
        return tree;
    }

    private String Tree2CSV(Tree tree) {
        String csv="";
        for (int key : tree.map.keySet()){
            Member member=tree.map.get(key);
            csv += member.toString();
        }
        return csv;
    }

    private Tree CSV2Tree(BufferedReader str) throws IOException{
        Tree tree = new Tree();
        String line;

        while ((line = str.readLine()) != null) {

            String[] info = line.split(",,");
            Member member = new Member(info[1]);
            loadInfo(member,info);
            tree.addMember(member);

        }
        return tree;
    }


    private void loadInfo(Member member, String[] info) {
        String aux;
        member.setId(Integer.parseInt(info[0]));

        member.loadParents(relationTrim(member.getParents(),info[2]));
        member.loadChildren(relationTrim(member.getChildren(),info[3]));


    }




    private List<Integer> relationTrim(List<Integer> relation, String info){
        if (!info.equals(" "))
            relation = Arrays.stream(info.substring(0, info.length() - 1).split(":")).map(Integer::parseInt).collect(Collectors.toList());
        return relation;
    }

}
