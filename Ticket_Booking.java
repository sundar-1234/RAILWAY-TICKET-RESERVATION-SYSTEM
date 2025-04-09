import java.util.*;
public class Ticket_Booking {
	public static void main(String[] args) {
		Ticket_System ticketSystem = new Ticket_System();
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("\nRailway Ticket Booking System");
			System.out.println("1. Book Ticket ");
			System.out.println("2. Cancel Ticket");
			System.out.println("3. View Confirmed Tickets");
			System.out.println("4. View Available Tickets");
			System.out.println("5. View RAC Tickets");
			System.out.println("6. View Waiting List Tickets");
			System.out.println("7. Exit");
			System.out.println("Enter your choice: ");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1:
				System.out.println("Enter your Name: ");
				String name = sc.nextLine();
				System.out.println("Enter your Age: ");
				int age = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter your Gender (Male/Female): ");
				String gender = sc.nextLine();
				System.out.println("Enter your birth preference (L/U/M): ");
				String birthPreference = sc.nextLine();
				ticketSystem.bookTicket(name, age, gender, birthPreference);
				break;
			case 2:
				System.out.println("Enter ticket ID to cancel: ");
				String ticketId = sc.nextLine();
				ticketSystem.cancelTicket(ticketId);
				break;
			case 3:
				ticketSystem.printBookedTickets();
				break;
			case 4:
				ticketSystem.printAvailableTickets();
				break;
			case 5:
				ticketSystem.printRacTickets();
				break;
			case 6:
				ticketSystem.printWaitingTickets();
				break;
			case 7:
				System.out.println("Exiting...");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Choice. Try again.");
			}
			
		}
	}
	
}
