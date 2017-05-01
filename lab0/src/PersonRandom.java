//Rickard Björklund, Lucas Grönborg
import java.util.Random;

public class PersonRandom{
    //Constant class variable
    private static String[] names = new String[]{"Albert", "Britta", "Caesar", "Daniella", "Erik", "Frida", "Gert", "Harriette", "Ingemar", "Julia"};

    //Instance variables
    protected String name;
    protected int age;

    //Constructor
    public PersonRandom(){
        this(names[new Random().nextInt(names.length)], new Random().nextInt(100-15)+15);
        /*
        Random random = new Random();
        String randomName = this.names[random.nextInt(this.names.length)];
        int randomAge = random.nextInt(100-15) + 15;
        personRandom(randomName, randomAge);
        */
    }

    //Constructor
    public PersonRandom(String nameIn, int ageIn){
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