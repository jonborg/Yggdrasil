package Family;

import java.util.*;

public class Member extends Person {

    private List<Integer> parents = new ArrayList<>();
    private List<Integer> children = new ArrayList<>();

    private Integer partner;

    public Member(String name) {
        super(name);
    }



    private void setParent(Member parent) {
        if (!this.parents.contains(parent.getId()))
            this.parents.add(parent.getId());
        if (!parent.children.contains(this.getId()))
            parent.children.add(this.getId());
    }

    void loadParents(List<Integer> parents){
        this.parents=parents;
    }

    private void setChild(Member child) {
        this.children.add(child.getId());
        child.parents.add(this.getId());
    }

    void loadChildren(List<Integer> children){
        this.children=children;
    }

    private void setPartner(Member partner){
        this.partner=partner.getId();
        partner.partner=this.getId();
    }

    public int getMemberId(String mode, int index, Tree tree){
        if (mode.toLowerCase().contains("parent")){
            return this.getParents().get(index);
        }
        if (mode.toLowerCase().contains("sibling")){
            return this.getSiblings(tree)[index].getId();
        }
        if (mode.toLowerCase().contains("child")){
            return this.getChildren().get(index);
        }
        return -1;
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
                if (siblingId != this.getId()) {
                    siblings.add(tree.map.get(siblingId));
                }
            }
        }

        return siblings.toArray(Member[]::new);
    }


    public List<Integer> getChildren(){
        return children;
    }


    public int getPartner(){
        return partner;
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
        String str = this.getId()+separator+this.getName()+separator;

        str += relationToString(parents)+separator;
        str += relationToString(children);


        return str+"\r\n";
    }

}
