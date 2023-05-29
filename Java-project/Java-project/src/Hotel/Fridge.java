package Hotel;

import java.util.HashMap;
import java.util.List;

public class Fridge {
	private HashMap<String,Integer> contents = new HashMap<String,Integer>();
	private static HashMap<String,Integer> validItems = new HashMap<String,Integer>();
	
	public Fridge() {};
	public Fridge(List<String> contents) {
		for(String item : contents) {
			addItem(item);
		}
	}
	
	public HashMap<String,Integer> getContents(){
		return contents;
	}
	
	public void printContents() {
		System.out.println(contents.toString());
	}
	
	public void removeItem(String item) {
		if(!contents.containsKey(item)) {
			throw new IllegalArgumentException(item + " doesn't exist in the fridge");
		}
		else {
			if(contents.get(item)==1) {
				contents.remove(item);
			}
			else {
				contents.put(item, contents.get(item)-1);
			}
		}
	}
	
	public void addItem(String item) {
		if(!validItems.containsKey(item)) {
			throw new IllegalArgumentException(item + " is not a valid item to put in the fridge");
		}
		if(this.contents.containsKey(item)) {
			this.contents.put(item, this.contents.get(item)+1);
		}
		else {
			this.contents.put(item, 1);
		}
	}
	
	
	public static void addAllValidItems(HashMap<String,Integer> validItems) {
		Fridge.validItems.putAll(validItems);
	}
	public static void addValidItem(String name, Integer price) {
		Fridge.validItems.put(name,price);
	}
	public static void addValidItem(String name, int price) {
		Fridge.validItems.put(name, Integer.valueOf(price));
	}
	
}
