//Rickard Björklund, Lucas Grönborg
public class Main {

    public static void main(String args[]){
        //Create Person object
        Person britta = new Person("Britta", 27);
        //Print name and age using getters
        System.out.println("Name: " + britta.getName() + ", age: " + britta.getAge());
        //Print name and age using toString method
        System.out.println(britta);
    }

}
/*
Fråga 1.2: Vilka typer bör variablerna vara?
name bör vara String,
age kan vara int.

Fråga 1.3: Vilka typer bör metoderna returnera?
getName() returnerar en String,
getAge() returnerar en int,
toString() returnerar en String.

Fråga 1.4: Vilka fält/metoder bör vara private, och vilka bör vara public?
metoderna som ska kunna nås utifrån måste vara public,
instansvariablerna kan vara private, proteced eller public, men i detta fall, då man inte
vill nå de direkt utifrån, är det logiskt att använda private.
*/