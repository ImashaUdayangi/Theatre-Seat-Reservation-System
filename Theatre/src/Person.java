//// Define a new class called "Person"
public class Person {
    // Define three instance variables for storing the person's name, surname, and email
    String Name;
    String Surname;
    String Email;

    // Define a constructor method that takes in three parameters (name, surname, and email)
    // and assigns them to the corresponding instance variables
    public Person(String Name, String Surname, String Email) {
        this.Name = Name;
        this.Surname = Surname;
        this.Email = Email;
    }

    // Define a method that returns the person's name
    public String getName() {
        return Name;
    }

    // Define a method that returns the person's surname
    public String getSurname() {
        return Surname;
    }

    // Define a method that returns the person's email address
    public String getEmail() {
        return Email;
    }
}

//https://www.w3schools.com/java/java_classes.asp