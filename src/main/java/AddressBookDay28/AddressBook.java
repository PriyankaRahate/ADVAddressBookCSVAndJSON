package AddressBookDay28;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class AddressBook {

	/**
	 * declaring variables
	 */
	static String name;
	static boolean is_Running = false;

	public HashMap<String, ContactInfo> addressBook = new HashMap<>();

	/**
	 * creating ArrayList of ContactInfo to store all the contact details
	 */
	public ArrayList<ContactInfo> listOfContacts = new ArrayList<>();

	
	public static void main(String[] args) throws IOException, CsvValidationException, ParseException {

		/**
		 * creating HashMap for multipleAddressBook
		 */
		HashMap<String, AddressBook> multiAddressBook = new HashMap<>();
		System.out.println("Welcome to the ADDRESS BOOK");
		AddressBook obj = new AddressBook();

		/**
		 * creating instance for the addressBook class for the csv file, json file and
		 * text file
		 */
		AddressBook addressBookObj1 = new AddressBook();
		AddressBook addressBookObj2 = new AddressBook();
		AddressBook addressBookObj3 = new AddressBook();
		multiAddressBook.put("AB1", addressBookObj1);
		multiAddressBook.put("AB2", addressBookObj2);
		multiAddressBook.put("AB3", addressBookObj3);

		obj.createContact(multiAddressBook);
		/**
		 * read simple text file
		 */
		obj.readFromFile();
		/**
		 * read CSV file
		 */
		obj.readFromCSVFile();
		/**
		 * read JSON file
		 */
		obj.readJSONFile();
	}

	
	public void createContact(HashMap<String, AddressBook> multiAddressBook) throws IOException, ParseException {

		/**
		 * Java BufferedWriter class is used to provide buffering for Writer instances. 
		 * It makes the performance fast. It inherits Writer class. The buffering characters are used 
		 * for providing the efficient writing of single arrays, characters, and strings.
		 */
		BufferedWriter bw1 = new BufferedWriter(
				new FileWriter("D:\\AddressBookDay-28\\src\\main\\java.txt\\"));
		BufferedWriter bw2 = new BufferedWriter(
				new FileWriter("D:\\AddressBookDay-28\\src\\main\\java.txt"));
		BufferedWriter bw3 = new BufferedWriter(
				new FileWriter("D:\\AddressBookDay-28\\src\\main\\jav.txt"));

		 /**
		  * create instance of CSVWriter class taking filewriter object as parameter 
		  * in parameter passing the csv path
		  */
		CSVWriter csv1 = new CSVWriter(
				new FileWriter("D:\\AddressBookDay-28\\src\\main\\java.csv"));
		CSVWriter csv2 = new CSVWriter(
				new FileWriter("D:\\AddressBookDay-28\\src\\main\\java.csv"));
		CSVWriter csv3 = new CSVWriter(
				new FileWriter("D:\\AddressBookDay-28\\src\\main\\java.csv"));

		/**
		 * create String of array of type header contains the contact details 
		 */
		String[] header = { "FirstName", "LastName", "Address", "City", "State", "Zipcode", "PhoneNo", "Email" };
		csv1.writeNext(header);
		csv2.writeNext(header);
		csv3.writeNext(header);

		Gson gson = new Gson();

		FileWriter jsonFW1 = new FileWriter("D:\\AddressBookDay-28\\src\\main\\java.json");
		FileWriter jsonFW2 = new FileWriter("D:\\AddressBookDay-28\\src\\main\\java.json");
		FileWriter jsonFW3 = new FileWriter("D:\\AddressBookDay-28\\src\\main\\java.json");

		JSONArray outerObj1 = new JSONArray();
		JSONArray outerObj2 = new JSONArray();
		JSONArray outerObj3 = new JSONArray();

		JSONObject jsonObject = new JSONObject();

		while (!is_Running) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter 1 ,2 ,3 for diff addressBook and 4 to exit");
			int option = scanner.nextInt();
			String key = switch (option) {
			case 1 -> "AB1";
			case 2 -> "AB2";
			case 3 -> "AB3";
			default -> null;
			};
			if (option == 4)
				break;
			System.out.println("""
					 Enter:
					1.Create a new contact
					2.Exit
					3.Edit existing contact
					4.Delete an existing contact
					5.SearchContactBasedOnCity
					6.SortContactsByPersonName
					7.SortContactsByCity
					8.SortContactsByState
					9.SortContactsByZip""");
			int choice = scanner.nextInt();
			if (choice == 1) {
				ContactInfo contact = new ContactInfo();
				contact.setContactInfo();
				name = contact.firstName.toUpperCase(Locale.ROOT) + " " + contact.lastName.toUpperCase(Locale.ROOT);
				
				/**
				 *  Java Streams is used to check any Duplicate entries
				 */
				if (multiAddressBook.get(key).addressBook.keySet().stream().noneMatch(k -> k.equals(name))) { 
					
					multiAddressBook.get(key).addressBook.put(name, contact);
					multiAddressBook.get(key).listOfContacts.add(contact); 
					/**
					 *  contact already exist in the addressBook
					 */
					multiAddressBook.get(key).addressBook.get(name).displayContactInfo();
					String outputData = multiAddressBook.get(key).addressBook.get(name).showContact();
					String csvOutputString = multiAddressBook.get(key).addressBook.get(name).showContactCSV();
					
					/**
					 * CSV Data can be splitted into a comma separated values 
					 */
					String[] csvData = csvOutputString.split(",");
					String json = gson.toJson(contact);
					JSONParser parser = new JSONParser();
					JSONObject strToJsonObj = (JSONObject) parser.parse(json);

					switch (option) {
					case 1 -> {
						bw1.write(outputData);
						csv1.writeNext(csvData);
						outerObj1.add(strToJsonObj);
					}
					case 2 -> {
						bw2.write(outputData);
						csv2.writeNext(csvData);
						outerObj2.add(strToJsonObj);
					}
					case 3 -> {
						bw3.write(outputData);
						csv3.writeNext(csvData);
						outerObj3.add(strToJsonObj);
					}
					}

				} else
					System.out.println("Contact already exist duplicate not allowed");
			} else if (choice == 2) {
				is_Running = true;
			} else if (choice == 3) {
				multiAddressBook.get(key).editContact();
			} else if (choice == 4) {
				multiAddressBook.get(key).deleteContact();
			} else if (choice == 5) {
				searchContactBasedOnCity(multiAddressBook);
			} else if (choice == 6) {
				sortContactsByPersonName(multiAddressBook);
			} else if (choice == 7) {
				sortContactsByCity(multiAddressBook);
			} else if (choice == 8) {
				sortContactsByState(multiAddressBook);
			} else if (choice == 9) {
				sortContactsByZip(multiAddressBook);
			}
		}

		jsonFW1.write(outerObj1.toString());
		jsonFW2.write(outerObj2.toString());
		jsonFW3.write(outerObj3.toString());

		bw1.close();
		bw2.close();
		bw3.close();

		csv1.close();
		csv2.close();
		csv3.close();

		jsonFW1.close();
		jsonFW2.close();
		jsonFW3.close();
	}

	/**
	 * Method for reading JSON File
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public void readJSONFile() throws IOException, ParseException {
		System.out.println("--------------------------------------------------------------\n Read from JSON File: ");
		JSONParser parser = new JSONParser();
		JSONArray jsonArray1 = (JSONArray) parser
				.parse(new FileReader("D:\\AddressBookDay-28\\src\\main\\java.json"));
		System.out.println(jsonArray1.toJSONString());

		JSONArray jsonArray2 = (JSONArray) parser
				.parse(new FileReader("D:\\AddressBookDay-28\\src\\main\\java.json"));
		System.out.println(jsonArray2.toJSONString());

		JSONArray jsonArray3 = (JSONArray) parser
				.parse(new FileReader("D:\\AddressBookDay-28\\src\\main\\java.json"));
		System.out.println(jsonArray3.toJSONString());
	}

	/**
	 * Method for reading data from csv file
	 *
	 * @throws IOException
	 * @throws CsvValidationException
	 */
	public void readFromCSVFile() throws IOException, CompletionException {
		System.out
				.println("--------------------------------------------------------------\nReading from CSV files:  \n");
		String[] contactInfo;
		CSVReader csvR1 = new CSVReader(
				new FileReader("D:\\AddressBookDay-28\\src\\main\\java.csv"));
		while ((contactInfo = csvR1.readNext()) != null) {
			for (String cell : contactInfo) {
				System.out.print(cell + "\t");
			}
			System.out.println();
		}
		System.out.println("\n");
		CSVReader csvR2 = new CSVReader(
				new FileReader("D:\\AddressBookDay-28\\src\\main\\java.csv"));
		while ((contactInfo = csvR2.readNext()) != null) {
			for (String cell : contactInfo) {
				System.out.print(cell + "\t");
			}
			System.out.println();
		}
		System.out.println("\n");
		CSVReader csvR3 = new CSVReader(
				new FileReader("D:\\AddressBookDay-28\\src\\main\\java.csv"));
		while ((contactInfo = csvR3.readNext()) != null) {
			for (String cell : contactInfo) {
				System.out.print(cell + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * Method for reading contacts stored in addressBook .txt File
	 *
	 * @throws IOException
	 */
	public void readFromFile() throws IOException {
		String contact;
		System.out.println(
				"--------------------------------------------------------------\nReading from File IO method: \n");
		System.out.println("List of Contacts in AddressBook 1 : ");
		System.out.println("firstName, lastName, address, city, state, zipcode, phoneNo, email");
		BufferedReader br1 = new BufferedReader(
				new FileReader("D:\\AddressBookDay-28\\src\\main\\java.txt"));
		while ((contact = br1.readLine()) != null) {
			System.out.println(contact);
		}
		System.out.println("\nList of Contacts in AddressBook 2 : ");
		System.out.println("firstName, lastName, address, city, state, zipcode, phoneNo, email");
		BufferedReader br2 = new BufferedReader(
				new FileReader("D:\\AddressBookDay-28\\src\\main\\java.txt"));
		while ((contact = br2.readLine()) != null) {
			System.out.println(contact);
		}
		System.out.println("\nList of Contacts in AddressBook 3 : ");
		System.out.println("firstName, lastName, address, city, state, zipcode, phoneNo, email");
		BufferedReader br3 = new BufferedReader(
				new FileReader("D:\\AddressBookDay-28\\src\\main\\java.txt"));
		while ((contact = br3.readLine()) != null) {
			System.out.println(contact);
		}
	}

	/**
	 * Method to delete an existing contact
	 */
	public void deleteContact() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the first and last name of the contact you want to delete from AddressBook: ");
		String name = scanner.nextLine().toUpperCase(Locale.ROOT);
		if (addressBook.containsKey(name)) {
			addressBook.remove(name);
			System.out.println("Contact removed");
		} else
			System.out.println("Contact not found");
	}

	/**
	 * Method to edit an existing contact
	 */
	public void editContact() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your first name and Last name  : ");
		String name = sc.nextLine().toUpperCase(Locale.ROOT);
		if (addressBook.containsKey(name)) {
			System.out.println(
					"Enter the number you want to edit\n1.Address\n2.City\n3.State\n4.Zipcode\n5.Phone Number\n6.Email");
			int number = sc.nextInt();
			sc.nextLine();
			switch (number) {
			case 1 -> {
				System.out.println("Enter new Address");
				addressBook.get(name).setAddress(sc.nextLine());
			}
			case 2 -> {
				System.out.println("Enter new City");
				addressBook.get(name).setCity(sc.nextLine());
			}
			case 3 -> {
				System.out.println("Enter new State");
				addressBook.get(name).setState(sc.nextLine());
			}
			case 4 -> {
				System.out.println("Enter new ZipCode");
				addressBook.get(name).setZipcode(sc.nextLine());
			}
			case 5 -> {
				System.out.println("Enter new Phone number");
				addressBook.get(name).setPhoneNo(sc.nextLine());
			}
			case 6 -> {
				System.out.println("Enter new Email");
				addressBook.get(name).setEmail(sc.nextLine());
			}
			default -> System.out.println("Please input a valid number (1-6)");
			}
			addressBook.get(name).displayContactInfo();
		} else
			System.out.println("Contact not found");
	}

	/**
	 * Method for searching contacts based on city using HashMap
	 */
	public void searchContactBasedOnCity(HashMap<String, AddressBook> multiAddressBook) {

		HashMap<String, String> personCityDictionary = new HashMap<>();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the city to search the contacts based on city");
		String searchCity = scanner.nextLine();
		int counter = 0;

		for (Map.Entry<String, AddressBook> addressBookEntry : multiAddressBook.entrySet()) {
			addressBookEntry.getKey();
			AddressBook currentAddressBook = addressBookEntry.getValue();
			for (ContactInfo item : currentAddressBook.listOfContacts) {
				System.out.println("Contact details: ");
				System.out.println(item.showContact());
			}

		}

		for (Map.Entry<String, AddressBook> addressBookEntry : multiAddressBook.entrySet()) {
			System.out.println(addressBookEntry.getKey());
			for (Map.Entry<String, ContactInfo> contactEntry : addressBookEntry.getValue().addressBook.entrySet()) {
				String result = addressBookEntry.getValue().addressBook.get(contactEntry.getKey()).showContact();
				System.out.println(contactEntry.getKey());
				if (result.contains(searchCity)) {
					personCityDictionary.put(contactEntry.getKey(), searchCity);
					counter++;
				}
			}
		}
		for (Map.Entry<String, String> pEntry : personCityDictionary.entrySet()) {
			System.out.println("Key= " + pEntry.getKey() + ", Value= " + pEntry.getValue());
		}
		System.out.println("No of contacts from the searched city were: " + counter);
	}

	/**
	 * Method for sorting contacts based on Person Name
	 *
	 * @param multiAddressBook HashMap containing all addressBooks is passed as
	 *                         parameter
	 */
	public void sortContactsByPersonName(HashMap<String, AddressBook> multiAddressBook) {

		for (Map.Entry<String, AddressBook> addressBookMapEntry : multiAddressBook.entrySet()) {
			List<ContactInfo> sortedContacts = addressBookMapEntry.getValue().addressBook.values().stream()
					.sorted(Comparator.comparing(contactInfo -> contactInfo.firstName + contactInfo.lastName))
					.collect(Collectors.toList());
			System.out.println("Sorted Contacts By name : ");
			for (ContactInfo currentContact : sortedContacts) {
				System.out.println(currentContact.getFirstName() + " " + currentContact.getLastName());
			}
			System.out.println("\n");
		}
	}

	/**
	 * Method for sorting contacts based on City
	 *
	 * @param multiAddressBook HashMap containing all addressBooks is passed as a
	 *                         parameter
	 */
	public void sortContactsByCity(HashMap<String, AddressBook> multiAddressBook) {

		for (Map.Entry<String, AddressBook> addressBookMapEntry : multiAddressBook.entrySet()) {
			List<ContactInfo> sortedContacts = addressBookMapEntry.getValue().addressBook.values().stream()
					.sorted(Comparator.comparing(contactInfo -> contactInfo.city)).collect(Collectors.toList());
			System.out.println("Sorted Contacts By City : ");
			for (ContactInfo currentContact : sortedContacts) {
				System.out.println("Name: " + currentContact.getFirstName() + " " + currentContact.getLastName()
						+ " City " + currentContact.getCity());
			}
			System.out.println("\n");

		}
	}

	/**
	 * Method for sorting contacts based on State
	 *
	 * @param multiAddressBook HashMap containing all addressBooks is passed as a
	 *                         parameter
	 */
	public void sortContactsByState(HashMap<String, AddressBook> multiAddressBook) {

		for (Map.Entry<String, AddressBook> addressBookMapEntry : multiAddressBook.entrySet()) {
			List<ContactInfo> sortedContacts = addressBookMapEntry.getValue().addressBook.values().stream()
					.sorted(Comparator.comparing(contactInfo -> contactInfo.state)).collect(Collectors.toList());
			System.out.println("Sorted Contacts By State : ");
			for (ContactInfo currentContact : sortedContacts) {
				System.out.println("Name: " + currentContact.getFirstName() + " " + currentContact.getLastName()
						+ " State " + currentContact.getState());
			}
			System.out.println("\n");

		}
	}

	/**
	 * Method for sorting contacts based on Zipcode
	 *
	 * @param multiAddressBook HashMap containing all addressBooks is passed as a
	 *                         parameter
	 */
	public void sortContactsByZip(HashMap<String, AddressBook> multiAddressBook) {

		for (Map.Entry<String, AddressBook> addressBookMapEntry : multiAddressBook.entrySet()) {
			List<ContactInfo> sortedContacts = addressBookMapEntry.getValue().addressBook.values().stream()
					.sorted(Comparator.comparing(contactInfo -> contactInfo.zipcode)).collect(Collectors.toList());
			System.out.println("Sorted Contacts By Zip : ");
			for (ContactInfo currentContact : sortedContacts) {
				System.out.println("Name: " + currentContact.getFirstName() + " " + currentContact.getLastName()
						+ " Zip " + currentContact.getZipcode());
			}
			System.out.println("\n");
		}
	}
}