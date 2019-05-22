package Family;

public class Person {
    int id;
    String name;

    public Person(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setId(int id){
        this.id = id;
    }

}
