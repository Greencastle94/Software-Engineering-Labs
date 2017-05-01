//Rickard Björklund, Lucas Grönborg
public class MainStudent {

    public static void main(String args[]){
        //Create Student object
        Student britta = new Student("Britta", 27, 2012);
        //Print name, age and start year using getters
        System.out.println("Name: " + britta.getName() + ", age: " + britta.getAge() + " started at CMETE " + britta.getStartYear());
        //Print name, age and start year using toString method
        System.out.println(britta);

        //Create an array to store references to randomly created students
        PersonRandom[] randomPersons = new PersonRandom[10];
        //Create random students and store them into array
        for(int i = 0; i < 5; i++){
            Student randomStudent = new Student();
            randomPersons[i] = randomStudent;

            PersonRandom randomPerson = new PersonRandom();
            randomPersons[(i+5)] = randomPerson;
        }

        //Print information of all randomly created students
        for(int i = 0; i < randomPersons.length; i++){
            System.out.println(randomPersons[i]);
        }
    }
}
