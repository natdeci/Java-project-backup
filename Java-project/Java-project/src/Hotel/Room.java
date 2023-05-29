package Hotel;

import java.util.List;

public class Room {
	private boolean availability;
	private boolean clean_status;
	private String type;
	private Fridge fridge;
	private Person guest;
	private final int roomNumber;
	private int costPerNight;
	
	public Room(int roomNumber, String type, int costPerNight) {
		availability = true;
		clean_status = true;
		this.roomNumber = roomNumber;
		this.type = type;
		this.fridge = new Fridge();
		this.costPerNight = costPerNight;
		guest = null;
	}
	
	public Room(int roomNumber, List<String> fridgeContents,String type, int costPerNight) {
		availability = true;
		clean_status = true;
		this.roomNumber = roomNumber;
		this.type = type;
		this.fridge = new Fridge(fridgeContents);
		this.costPerNight = costPerNight;
		guest = null;
	}
	
	public void checkIn(Person guest) {
		availability = false;
		guest.checkIn(roomNumber);
		this.guest = guest;
	}
	public void checkOut() {
		availability = true;
		guest.checkOut();
		this.guest = null;
	}
	public void chargeGuest() {
		this.guest.charge(costPerNight);
	}
	public String getDetails() {
		return this.roomNumber + " " + this.type + " " + this.costPerNight;
	}
	public String getType() {
		return type;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public Person getGuest() {
		return guest;
	}
	public boolean getAvailability() {
		return availability;
	}
	public int getCostPerNight() {
		return costPerNight;
	}
}
