//Rickard Björklund, Lucas Grönborg
import java.util.Random;

public class Student extends PersonRandom {

    private int startYear;

    public Student(){
        super();

        Random random = new Random();
        int randomYear = random.nextInt(2017-1934)+1934;
        while (2017 - randomYear + 15 > age){
            randomYear = random.nextInt(2017-1934)+1934;
        }
        startYear = randomYear;
    }

    public Student(String nameIn, int ageIn, int startYearIn){
        name = nameIn;
        age = ageIn;
        startYear = startYearIn;
    }

    public int getStartYear(){
        return startYear;
    }

    public String toString(){
        return "Name: " + name + ", age: " + age + " started at CMETE " + startYear;
    }

}
