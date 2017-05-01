//Rickard Björklund, Lucas Grönborg
public class MainRandom {

    public static void main(String args[]){
        //Create Person object
        PersonRandom britta = new PersonRandom("Britta", 27);
        //Print name and age using getters
        System.out.println("Name: " + britta.getName() + ", age: " + britta.getAge());
        //Print name and age using toString method
        System.out.println(britta);

        //Create an array to store references to randomly created persons
        PersonRandom[] randomPersons = new PersonRandom[15];
        //Create random persons and store them into array
        for(int i = 0; i < 15; i++){
            PersonRandom randomPerson = new PersonRandom();
            randomPersons[i] = randomPerson;
        }

        //Print information of all randomly created persons
        for(int i = 0; i < randomPersons.length; i++){
            System.out.println(randomPersons[i]);
        }
    }
}
/*
Fråga 2.3: Måste man anropa toString() explicit?
Nej, det går bra att printa ett objekt, då kallas toString() och resultatet av metodanropet skrivs ut.

Fråga 3.3: Arrayen med namn i klassen Person bör vara deklarerad static.
Programmet fungerar likadant även om man utelämnar static. Varför är det ändå bättre att arrayen är static?
Man vill att alla person objekt ska använda samma array med namn, denna ska inte ändras mellan olika instanser av person-
objekt, därför är det bra att deklarara arrayn med namn som en konstant klass variabel vilket static används för.
*/