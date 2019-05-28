package Family;

import java.util.*;

public class Member extends Person {

    List<Integer> parents = new ArrayList<>();
    List<Integer> children = new ArrayList<>();

    Integer partner;

    public Member(String name) {
        super(name);
    }





    private void setParent(Member parent) {
        if (!this.parents.contains(parent.id))
            this.parents.add(parent.id);
        if (!parent.children.contains(this.id))
            parent.children.add(this.id);
    }

    private void setChild(Member child) {
        this.children.add(child.id);
        child.parents.add(this.id);
    }

    private void setPartner(Member partner){
        this.partner=partner.id;
        partner.partner=this.id;
    }





    public void associateParent(Tree tree, Member parent){
        setParent(parent);
        if (tree.map.containsKey(parent.partner)){
            setParent(tree.map.get(parent.partner));
        }
    }

    public void associateChild (Tree tree, Member child){
        setChild(child);
        if (tree.map.containsKey(this.partner)){
            tree.map.get(this.partner).setChild(child);
        }
    }

    public void associatePartner(Tree tree, Member partner){
        Set<Member> children = new HashSet<>();

        setPartner(partner);

        for (int childrenId : this.children)
            children.add(tree.map.get(childrenId));
        for (int childrenId : partner.children)
            children.add(tree.map.get(childrenId));

        for (Member child : children){
            child.setParent(this);
            child.setParent(partner);
        }
    }

    public List<Integer> getParents(){
        return parents;
    }

    public Member[] getSiblings(Tree tree){
        Set<Member> siblings = new HashSet();
        for(int parentId : this.parents){
            Member parent = tree.map.get(parentId);
            for (int siblingId : parent.children){
                if (siblingId != this.id) {
                    siblings.add(tree.map.get(siblingId));
                }
            }
        }

        return siblings.toArray(Member[]::new);
    }
    public List<Integer> getChildren(){
        return children;
    }



    private String relationToString(List<Integer> relation) {
        String str = "";

        if (relation.isEmpty()) {
            str += " ";
        } else {
            for (Object elem : relation) {
                str += elem.toString() + ":";
            }
        }
        return str;
    }

    public String toString(){
        String separator = ",,";
        String str = id+separator+name+separator;

        str += relationToString(parents)+separator;
        str += relationToString(children);


        return str+"\r\n";
    }

}
