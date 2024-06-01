// Define a new class called "Ticket"
public class Ticket {

    // Define four instance variables for storing the ticket's row number, seat number, price, and associated person object
    int Row;
    int Seat;
    double Price;
    Person person;


    // Define a constructor method that takes in four parameters (row, seat, price, and person)
    // and assigns them to the corresponding instance variables
    public Ticket(int Row, int Seat, double Price, Person person) {
        this.Row = Row;
        this.Seat = Seat;
        this.Price = Price;
        this.person = person;

    }

    // Define a method that returns the ticket's row number
    public int getRow() {
        return Row;
    }

    // Define a method that returns the ticket's seat number
    public int getSeat() {
        return Seat;
    }

    // Define a method that returns the ticket's price
    public double getPrice() {
        return Price;
    }

    // Define a method that returns the associated person object for the ticket
    public Person getPerson() {
        return person;
    }

    // Define a method that prints out the ticket information and associated person information
    public void print() {
        System.out.println("Person's Name: " + person.getName());
        System.out.println("Person's Surname: " + person.getSurname());
        System.out.println("Person's Email: " + person.getEmail());
        System.out.println("Row: " + Row);
        System.out.println("Seat: " + Seat);
        System.out.println("Price: " + Price);

    }
}

//https://www.w3schools.com/java/java_classes.asp