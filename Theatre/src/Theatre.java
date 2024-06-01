import java.util.Scanner;// Import the Scanner class to read text files
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileWriter; //Import this class to write in a file
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Collections;
import java.util.Comparator;


public class Theatre {

    // create an ArrayList to hold Ticket objects
    static ArrayList<Ticket> Tickets =new ArrayList<Ticket>();

    // create a 2D array for the seating
    private static int[][] seats = new int[3][];

    //main method
    public static void main(String[] args) {

        //Display Welcome message
        System.out.println("‘Welcome to the New Theatre!’");

        seats[0] = new int[12];
        seats[1] = new int[16];
        seats[2] = new int[20];

        // initialize all seats to 0 (free)
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = 0;
            }
        }

        //print the menu and ask the user for their choice
        while(true) {
            try { //use a try-catch block to handle invalid input
                System.out.println("""
                                -------------------------------------------------
                                \nPlease select an option:
                                1) Buy a ticket
                                2) Print seating area
                                3) Cancel ticket
                                4) List available seats
                                5) Save to file
                                6) Load from file
                                7) Print ticket information and total price
                                8) Sort tickets by price
                                     0) Quit
                                -------------------------------------------------""");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter option: ");

                int option = scanner.nextInt();

                //call the appropriate method based on user choice
                switch (option){
                    case 1:
                        buy_ticket();
                        break;
                    case 2:
                        print_seating_area();
                        break;
                    case 3:
                        cancel_ticket(seats);
                        break;
                    case 4:
                        show_available();
                        break;
                    case 5:
                        save();
                        break;
                    case 6:
                        load();
                        break;
                    case 7:
                        show_tickets_info();
                        break;
                    case 8:
                        sort_tickets();
                        break;
                    case 0:
                        System.out.println("Thank You.Have a nice day ! ");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e){
                System.out.println("Enter a valid Input!");
            }
        }
    }
    //Create a method called buy_ticket that asks the user to input a row number and a seat number.
    private static void buy_ticket() {

        Scanner input = new Scanner(System.in);
        String Name = "";
        String Surname = "";
        String Email = "";

        //Get user's name, surname and email
        while(Name.isEmpty()){
            System.out.println("Enter your name : ");
            Name = input.nextLine();
            if (Name.isEmpty()){
                System.out.print("Error!!!");
            }
        }

        while(Surname.isEmpty()){
            System.out.println("Enter your surname : ");
            Surname = input.nextLine();
            if (Surname.isEmpty()){
                System.out.print("Error!!!");
            }
        }

        while(Email.isEmpty()){
            System.out.println("Enter your email : ");
            Email = input.nextLine();
            if (Email.isEmpty()){
                System.out.print("Error!!!");
            }
        }

        //Get user's chosen row number
        int row = 0;
        while (true) {
            try {
                while (row < 1 || row > 3) {
                    System.out.print("Enter row number (1-3): ");
                    row = input.nextInt();
                    if (row < 1 || row > 3) {
                        System.out.println("Invalid row number.Please select 1-3");
                    }
                }
                break;
            } catch (Exception e) {
                System.out.println("Enter a valid row number");
                input.nextLine();}
        }

        //Get user's chosen seat number
        int seat = 0;
        while(true){
            try{
                int[] rowArray = seats[row - 1];

                while (seat < 1 || seat > rowArray.length) {
                    System.out.print("Enter seat number (1-" + rowArray.length + "):");
                    seat = input.nextInt();

                    if (seat < 1 || seat > rowArray.length) {
                        System.out.println("Invalid seat number. Please try again.");
                    }
                }
                break;

            } catch (Exception e){
                System.out.println("Enter a valid seat number");
                input.nextLine();}
        }

        //Get user's chosen ticket price
        double price = 0;
        while(true) {
            try{
                System.out.print("Enter the price : ");
                price = input.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.Please try again.");
                input.nextLine();
            }
        }

        //Check if the selected seat is available
        int[] rowArray = seats[row - 1];
        while (seat < 1 || seat > rowArray.length) {
            System.out.print("This seat number does not exist. Please select the seat (1-" + rowArray.length + "):");
            seat = input.nextInt();
        }
        if (rowArray[seat - 1] == 1) {
            System.out.println("This seat is already occupied. Please choose another seat.");
            return;
        }

        //Reserve the selected seat and create a new ticket
        rowArray[seat - 1] = 1 ;
        System.out.println("\nYou have successfully booked " + row + "-" + seat + "seat.");
        Person person = new Person(Name , Surname, Email);
        Ticket ticket = new Ticket(row, seat, price, person);
        Tickets.add(ticket);
    }

    /*create a method to show the seats that have been sold,
     and the seats that are still available*/
    public static void print_seating_area() {
        String[] align = new String[] {"     ","   "," "};
        System.out.println("      ***********");
        System.out.println("      *  STAGE  *");
        System.out.println("      ***********");

        for (int i = 0; i < seats.length; i++) {
            System.out.print("\n" + align[i]);
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
                if (j == seats[i].length/2-1) {
                    System.out.print(" ");
                }
            }
            // Add extra spaces to align the seats with the stage
            for (int k = 0; k < seats.length - seats[i].length; k++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    //create a method to make a seat available again.
    public static void cancel_ticket(int[][] seats) {
        Scanner get = new Scanner(System.in);

        // Ask user for row number
        int row =0;
        while(true){
            try{
                while (row < 1 || row > 3) {
                    System.out.print("Please enter the row number(1-3): ");
                    row = get.nextInt();
                    if (row < 1 || row > 3) {
                        System.out.print("This is not a valid row number. ");
                    }
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input.");
                get.next();
            }
        }

        // Ask user for seat number
        int seat = 0;
        int[] rowArray = seats[row - 1];
        while (true){
            try{
                seat = 0;
                while (seat < 1 || seat > rowArray.length){
                    System.out.print("please select the seat number(1-" + rowArray.length + "): ");
                    seat = get.nextInt();
                    if (seat < 1 || seat > rowArray.length) {
                        System.out.println("This is not a valid seat number.");
                    }
                }
                break;
            } catch (InputMismatchException e){
                System.out.println("Invalid Input.");
                get.next();
            }
        }



        // Check if the seat is available
        if (rowArray[seat-1] == 0) {
            System.out.println("This seat is already available.");
            return;
        }

        // Make the seat available and remove corresponding ticket
        rowArray[seat-1] = 0;
        for (Ticket ticket : Tickets){
            if (ticket.Row == row && ticket.Seat == seat) {
                Tickets.remove(ticket);
                System.out.println("Seat " + row + "-" + seat + " cancelled successfully.");
                return;
            }
        }
    }
    public static void show_available() {
        //This function print the available seats  for each row in the theatre
        for (int row = 0; row < seats.length; row++) {
            //print the row number
            System.out.print("Seats available in row " + (row+1) + ": ");
            //Iterates over each seat in the row and checks if it's available
            for (int seat = 0; seat < seats[row].length; seat++) {
                if (seats[row][seat] == 0) {
                    //print the seat number if it's available
                    System.out.print((seat+1) + " ");
                }
            }
            //start a new line for the next row
            System.out.println();
        }
    }
    public static void save() {
        //Save the state of the theatre to a file named "Theatre.txt"
        try {
            //Create new FileWriter object to write to the file
            FileWriter writer = new FileWriter("Theatre.txt");

            //Iterate over each seat in the theatre and writes its value to the file
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    writer.write(seats[i][j] + " ");
                }
                //Start a new line for the next row
                writer.write("\n");
            }

            //Close the FileWriter object and print a message indicating success
            writer.close();
            System.out.println("Rows saved to file 'Theatre.txt'");
        } catch (IOException e) {
            //Print an error message if  an exception is caught
            System.out.println("An error occurred while saving the rows: " + e.getMessage());
        }
    }

    /*https://www.w3schools.com/java/java_files_read.asp
     */
    public static void load() {
        //this load  the state of the Theatre from the file named "Theatre.txt"
        try {
            //create a new File object and a scanner object to read from the file
            File filesave = new File("Theatre.txt");
            Scanner reader = new Scanner(filesave);
            //Iterates over each line in the file and print its contents
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }
            //Close the scanner object
            reader.close();
        } catch (FileNotFoundException e) {
            //printing error message if file not found
            System.out.println("An error occurred.");
        }
    }

    public static void show_tickets_info() {
        //This prints the details of all purchased tickets
        double allTickets = 0;
        //Iterate over each Ticket object in the Tickets ArrayList and calls its print() function
        for (Ticket ticket : Tickets){
            ticket.print();
            //Add the ticket price to the allTickets variable
            allTickets = allTickets + ticket.getPrice();
            //Starts a new line for next ticket
            System.out.println(" ");
        }
        //print the total ticket price
        System.out.println("Total price: £" + allTickets + "");
    }


    //https://www.w3schools.com/java/java_arraylist.asp
    public static void sort_tickets() {
        //sort the list of tickets using a comparator that compare the ticket price
        Collections.sort(Tickets, new Comparator<Ticket>() {
            @Override
            public int compare(Ticket tik1, Ticket tik2){
                //if tik1 price is less than tik2 price, return - 1
                if (tik1.getPrice() < tik2.getPrice()){
                    return -1;
                    //if tik1 price is greater than tik2 price,return 1
                } else if (tik1.getPrice() > tik2.getPrice()){
                    return 1;
                    //If tik1 price is equal to tik2 price, return 0
                } else{
                    return 0;
                }
            }
        });

        //printing a message indicating that the tickets have been sorted by price
        System.out.println("\nTickets Sorted by price (Cheapest first): \n");

        //Iterating through the sorted list of tickets and printing each ticket's information
        for (Ticket ticket: Tickets){
            ticket.print();
            System.out.println("");
        }

        System.out.println("");
    }
}