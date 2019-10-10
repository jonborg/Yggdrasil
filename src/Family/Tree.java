package Family;

import java.util.HashMap;

public class Tree {
    HashMap<Integer,Member> map;
    int key=1;

    public Tree(){
        map=new HashMap<>();
    }



    public int generateKey(){
        this.key++;
        return this.key;
    }


    public Member addMember(Member member){
        if (map.containsKey(this.key)){
                generateKey();
          }

          member.setId(this.key);
          map.put(this.key,member);
          return member;
    }

    public Member getMember(int key){
        return map.get(key);
    }

    public String getMemberName(int key){ return this.getMember(key).getName();}




    public String ToString(){
        String str="";
        for (int key : map.keySet()) {
           str=str+key + " : " + map.get(key).getName()+"\r\n";
        }
        return str;
    }
}
