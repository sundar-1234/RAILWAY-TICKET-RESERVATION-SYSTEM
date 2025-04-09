import java.util.*;
public class Ticket_System {
	private final List<String> availableBerths = new ArrayList<>(Arrays.asList("L","U","M"));
	private final Queue<Passenger> RAC = new LinkedList<>();
	private final Queue<Passenger>  waitingList = new LinkedList<>();
	private final List<Passenger> confirmedPassengers = new ArrayList<>();
	private int ticketCounter = 1;
	
	public void bookTicket(String name,int age,String gender,String berthPreference) {
		String ticketId = "T" + ticketCounter++;
		Passenger passenger;
		if(!availableBerths.isEmpty()) {
			String allocatedBerth = allocateBerth(age,gender,berthPreference);
			passenger = new Passenger(name,age,gender,berthPreference,allocatedBerth,ticketId);
			confirmedPassengers.add(passenger);
			availableBerths.remove(allocatedBerth);
			System.out.println("Ticket Confirmed: "+ passenger);
		}
		else if(RAC.size()<1) {
			passenger = new Passenger(name,age,gender,berthPreference,"RAC",ticketId);
			RAC.add(passenger);
			System.out.println("Ticket in RAC: "+ passenger);
		}
		else if(waitingList.size()<1) {
			passenger = new Passenger(name,age,gender,berthPreference,"Waiting List",ticketId);
			waitingList.add(passenger);
			System.out.println("Ticket in Waiting List: "+passenger);
		}
		else {
			System.out.println("No tickets available");
		}
	}
	public void cancelTicket(String ticketId) {
		Passenger passenger = findTicketId(ticketId);
		if(passenger!=null) {
			confirmedPassengers.remove(passenger);
			availableBerths.add(passenger.allotedBerth);
			if(!RAC.isEmpty()) {
				Passenger racPassenger = RAC.poll();
				String allocatedBerth = allocateBerth(racPassenger.age,racPassenger.gender,racPassenger.berthPreference);
				racPassenger.allotedBerth = allocatedBerth;
				confirmedPassengers.add(racPassenger);
				availableBerths.remove(allocatedBerth);
				System.out.println("RAC ticket moved to confirmed: "+racPassenger);
			}
			if(!waitingList.isEmpty()) {
				Passenger waitingPassenger = waitingList.poll();
				waitingPassenger.allotedBerth = "RAC";
				RAC.add(waitingPassenger);
				System.out.println("Waiting ticket moved to RAC: "+waitingPassenger);
			}
			System.out.println("Ticket cancelled successfully for ID: "+ticketId);
		}
		else {
			System.out.println("No ticket found with ID: "+ticketId);
		}
	}
	public void printBookedTickets() {
		if(confirmedPassengers.isEmpty()) {
			System.out.println("No confirmed tickets");
		}
		else {
			System.out.println("Confirmed tickets: ");
			for(Passenger passenger:confirmedPassengers) {
				System.out.println(passenger);
			}
		}
	}
	public void printRacTickets() {
		if(RAC.isEmpty()) {
			System.out.println("No RAC tickets");
		}
		else {
			System.out.println("RAC tickets.");
			for(Passenger RacPassenger:RAC) {
				System.out.println(RacPassenger);	
			}
		}
	}
	public void printWaitingTickets() {
		if(waitingList.isEmpty()) {
			System.out.println("No Waiting tickets");
		}
		else {
			System.out.println("Waiting tickets.");
			for(Passenger WaitingPassenger:waitingList) {
				System.out.println(WaitingPassenger);	
			}
		}
	}
	public void printAvailableTickets() {
		System.out.println("Available berths: "+availableBerths.size());
		System.out.println("Available RACs: "+ (1-RAC.size()));
		System.out.println("Available waiting: "+(1-waitingList.size()));
	}
	
	private String allocateBerth(int age,String gender, String berthPrefernce) {
		if((age>60 || gender.equalsIgnoreCase("female")) && availableBerths.contains("L")) {
			return "L";
		}
		if(availableBerths.contains(berthPrefernce)) {
			return berthPrefernce;
		}
		return availableBerths.get(0);
	}
	private Passenger findTicketId(String TicketId) {
		for(Passenger passenger:confirmedPassengers) {
			if(passenger.ticketId.equals(TicketId)) {
				return passenger;
			}
		}
		return null;
	}
}
