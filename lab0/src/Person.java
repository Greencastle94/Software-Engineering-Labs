//Rickard Björklund, Lucas Grönborg
public class Person {
    //Instance variables
    private String name;
    private int age;

    //Constructor
    public Person(String nameIn, int ageIn){
        name = nameIn;
        age = ageIn;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String toString(){
        return "Name: " + name + ", age: " + age;
    }

}
