package Family;

public class Person {
    private int id;
    private String name;
    private String imagePath;

    public Person(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId (){ return this.id; }

    public void setImagePath(String path) { this.imagePath = path; }

    public String getImagePath (){ return this.imagePath; }

}
