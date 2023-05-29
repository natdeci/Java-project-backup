package Hotel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu extends Application implements Initializable{
	private ArrayList<Room> hotelRooms;
	private ArrayList<Person> Guests;
	private ArrayList<Staff> staffList;
	private HashMap<String, Integer> validItems;
	private Set<String> ID_List;
	private Set<Integer> RoomNums;
	private Parent root;
	private Scene scene;
	private Stage stage;
	@FXML
	TextField Name;
	@FXML
	TextField Phone;
	@FXML
	TextField roomNumber;
	@FXML
	TextField roomCost;
	@FXML
	Text PhoneError;
	@FXML
	Text ID_error;
	@FXML
	Text RoomError;
	@FXML
	Text PriceError;
	@FXML
	FlowPane List;
	@FXML
	Spinner<Integer> ID_num;
	@FXML
	ChoiceBox<Character> ID_gender;
	@FXML
	ChoiceBox<String> roomType;
	SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999);
	public Menu() {
		hotelRooms = new ArrayList<Room>();
		Guests = new ArrayList<Person>();
		staffList = new ArrayList<Staff>();
		validItems = new HashMap<String,Integer>();
		ID_List = new HashSet<String>();
		RoomNums = new HashSet<Integer>();
		Fridge.addAllValidItems(validItems);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		System.out.println("Initialize is run");
		for(Person G: Guests) {
			System.out.println(G.getDetails());
		}
		if(arg0.equals(getClass().getResource("GuestMenu.fxml"))) {
			loadGuests();
			for(Person G: Guests) {
				VBox guestInfo = new VBox();
				guestInfo.getChildren().add(new Text(G.getName()));
				guestInfo.getChildren().add(new Text(G.getPhone()));
				guestInfo.getChildren().add(new Text(G.getRoomNumber()!=-1?Integer.toString(G.getRoomNumber()):"None"));
				guestInfo.setAlignment(Pos.TOP_CENTER);
				guestInfo.setPrefWidth(80);
				guestInfo.setBorder(new Border(new BorderStroke(null,BorderStrokeStyle.SOLID,null,null)));
				guestInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
				    @Override
				    public void handle(MouseEvent mouseEvent) {
				        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
				            if(mouseEvent.getClickCount() == 2){
				                System.out.println("Double clicked");
				            }
				        }
				    }
				});
				List.getChildren().add(guestInfo);
//				saveGuest();
			}
			return;
		}
		if(arg0.equals(getClass().getResource("StaffMenu.fxml"))) {
			loadStaffList();
			for(Staff S: staffList) {
				VBox staffInfo = new VBox();
				staffInfo.getChildren().add(new Text(S.getName()));
				staffInfo.getChildren().add(new Text(S.getPhone()));
				staffInfo.getChildren().add(new Text(S.getStaffID()));
				staffInfo.setAlignment(Pos.TOP_CENTER);
				staffInfo.setPrefWidth(80);
				staffInfo.setBorder(new Border(new BorderStroke(null,BorderStrokeStyle.SOLID,null,null)));
				staffInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
				    @Override
				    public void handle(MouseEvent mouseEvent) {
				        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
				            if(mouseEvent.getClickCount() == 2){
				                System.out.println("Double clicked");
				            }
				        }
				    }
				});
				List.getChildren().add(staffInfo);
//				saveStaff();
			}
			return;
		}
		if(arg0.equals(getClass().getResource("ManagementMenu.fxml"))) {
			loadhotelRooms();
			for(Room R: hotelRooms) {
				VBox roomInfo = new VBox();
				roomInfo.getChildren().add(new Text(Integer.toString(R.getRoomNumber())));
				roomInfo.getChildren().add(new Text(R.getType()));
				roomInfo.getChildren().add(new Text(Integer.toString(R.getCostPerNight())));
				roomInfo.getChildren().add(new Text(R.getAvailability()?"Available":"Occupied"));
				roomInfo.setPrefWidth(95);
				roomInfo.setAlignment(Pos.TOP_CENTER);
				roomInfo.setBorder(new Border(new BorderStroke(null,BorderStrokeStyle.SOLID,null,null)));
				roomInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
				    @Override
				    public void handle(MouseEvent mouseEvent) {
				        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
				            if(mouseEvent.getClickCount() == 2){
				                System.out.println("Double clicked");
				            }
				        }
				    }
				});
				List.getChildren().add(roomInfo);
//				saveRoom();
			}
			return;
		}
		if(arg0.equals(getClass().getResource("addStaff.fxml"))) {
			Character[] gender = {'F','M'};
			ID_gender.getItems().addAll(gender);
			valueFactory.setValue(1);
			ID_num.setValueFactory(valueFactory);
			return;
		}
		if(arg0.equals(getClass().getResource("addRoom.fxml"))) {
			String[] type = {"Standard", "Special Standard", "Deluxe", "Executive"};
			roomType.getItems().addAll(type);
			return;
		}
	}
	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
			root = FXMLLoader.load(getClass().getResource("StartMenu.fxml"));
			scene = new Scene(root);
			stage = primaryStage;
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void guestMenu(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("GuestMenu.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void staffMenu(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("StaffMenu.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void managementMenu(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ManagementMenu.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void addRoomMenu(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("addRoom.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void addGuestMenu(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("addGuest.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void addStaffMenu(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("addStaff.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void addRoom(ActionEvent e) throws IOException {
		try {
			loadhotelRooms();
			RoomError.setOpacity(0);
			PriceError.setOpacity(0);
			if(!roomNumber.getText().matches("[0-9]+")) {
				throw new IllegalArgumentException("Room number can only contain numbers");
			}
			Integer num = Integer.parseInt(roomNumber.getText());
			if(RoomNums.contains(num)) {
				throw new IllegalArgumentException("Room number already exists");
			}
			if(!roomCost.getText().matches("[0-9]+")) {
				throw new IllegalArgumentException("Room price can only contain numbers");
			}
			hotelRooms.add(new Room(num.intValue(), roomType.getValue(), Integer.parseInt(roomCost.getText())));
			RoomNums.add(num);
			saveRoom();
			managementMenu(e);
		}catch(IllegalArgumentException IAE) {
			if(IAE.getMessage().contains("Room number")) {
				RoomError.setText(IAE.getMessage());
				RoomError.setOpacity(1);
			}
			else {
				System.out.println("shitf");
				PriceError.setText(IAE.getMessage());
				PriceError.setOpacity(1);
			}
		}
	}
	private void removeRoom() {
		
	}
	public void addGuest(ActionEvent e) throws IOException{
		try {
			loadGuests();
			Guests.add(new Person(Name.getText(),Phone.getText()));
			System.out.println("addGuest is run");
			for(Person G: Guests) {
				System.out.println(G.getDetails());
			}
			saveGuest();
			guestMenu(e);
		}catch(IllegalArgumentException IAE) {
			PhoneError.setText(IAE.getMessage());
			PhoneError.setOpacity(1);
		}
	}
	private void removeGuest() {
		
	}
	public void addStaff(ActionEvent e) throws IOException{
		try {
			loadStaffList();
			PhoneError.setOpacity(0);
			ID_error.setOpacity(0);
			String numStringer;
			System.out.println(ID_num.getValue());
			if(ID_num.getValue() < 10) {
				numStringer = "00" + ID_num.getValue().toString();
			} else if (ID_num.getValue() < 100) {
				numStringer = "0" + ID_num.getValue().toString();
			} else{
				numStringer = ID_num.getValue().toString();
			}
			numStringer = ID_gender.getValue().toString() + numStringer;
			if(ID_List.contains(numStringer)) {
				throw new IllegalArgumentException("The ID already exists");
			}
			staffList.add(new Staff(numStringer, Name.getText(),Phone.getText()));
			saveStaff();
			staffMenu(e);
		}catch(IllegalArgumentException IAE) {
			if(IAE.getMessage().contains("phone")) {
				PhoneError.setText(IAE.getMessage());
				PhoneError.setOpacity(1);
			}
			else {
				ID_error.setText(IAE.getMessage());
				ID_error.setOpacity(1);
			}
		}
	}
	private void removeStaff() {
		
	}
	private void saveInfo() {
		saveGuest();
		saveRoom();
		saveStaff();
	}
	private void saveGuest() {
		try {
			FileWriter writer = new FileWriter("GuestList.txt", false);
	        writer.write("");
	        for (Person G : Guests) {
	        	writer.write(G.getName() + "," + G.getPhone()+"\n");
	        }
	        writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void saveRoom() {
		try {
			FileWriter writer = new FileWriter("RoomList.txt", false);
	        writer.write("");
	        for (Room R : hotelRooms) {
	        	writer.write(R.getRoomNumber() + "," + R.getType() + "," + R.getCostPerNight()+"\n");
	        }
	        writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void saveStaff() {
		try {
			FileWriter writer = new FileWriter("StaffList.txt", false);
	        writer.write("");
	        for (Staff S : staffList) {
	        	writer.write(S.getStaffID() + "," + S.getName() + "," + S.getPhone()+"\n");
	        }
	        writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void loadStaffList() {
		// TODO Auto-generated method stub
		try {
			File guestFile = new File("StaffList.txt");
			Scanner input = new Scanner(guestFile);
			
			while (input.hasNextLine()) {
				String aString = input.nextLine();
				String[] parts = aString.split(",");
				Staff s1 = new Staff(parts[0], parts[1], parts[2]);
				staffList.add(s1);
				ID_List.add(parts[0]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void loadGuests() {
		// TODO Auto-generated method stub
		try {
			File guestFile = new File("GuestList.txt");
			Scanner input = new Scanner(guestFile);
			
			while (input.hasNextLine()) {
				String aString = input.nextLine();
				String[] parts = aString.split(",");
				Person p1 = new Person(parts[0], parts[1]);
				Guests.add(p1);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void loadhotelRooms() {
		// TODO Auto-generated method stub
		try {
			File guestFile = new File("RoomList.txt");
			Scanner input = new Scanner(guestFile);
			
			while (input.hasNextLine()) {
				String aString = input.nextLine();
				String[] parts = aString.split(",");
				Room r1 = new Room(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]));
				hotelRooms.add(r1);
				RoomNums.add(Integer.parseInt(parts[0]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	private void loadValidItems() {
		validItems.put("Coca Cola", 15000);
		validItems.put("Water bottle", 10000);
		validItems.put("Pepsi", 14500);
		validItems.put("Heineken", 20000);
		validItems.put("Bud Light", 17000);
	}
	public static void main(String[] args) {
		Menu menu = new Menu();
		launch(args);
	}
}
