package arauz.cristian;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import animal.Animal;
import animal.carnivore.Lion;
import animal.herbivore.Giraffe;

public class Main {
	
	private static Scanner reader = new Scanner(System.in);
	private static int input;
	private static File namesFile = new File("names.txt");
	private static Scanner fileReader;
	private static ArrayList<Animal> animalList = new ArrayList<Animal>();
	private static ArrayList<Run> threadList = new ArrayList<Run>();

	public static void main(String[] args) {
		getInput();
		startThreads();
	}
	
		private static void getInput() {
			System.out.println("How many Giraffes?");
			input = reader.nextInt();
			createAnimal("Giraffe");
				
			System.out.println("How many Lions?");
			input = reader.nextInt();
			createAnimal("Lion");
			
			System.out.println("How many threads?");
			input = reader.nextInt();
		}
		
		private static void createAnimal(String animal) {
			try {
				fileReader = new Scanner(namesFile);
				if(animal.equalsIgnoreCase("Giraffe")) {
					for(int i = input; i > 0; i--) {
						animalList.add(new Giraffe(fileReader.nextLine()));
					}
				}
				else if(animal.equalsIgnoreCase("Lion")) {
					for(int i = input; i > 0; i--) {
						animalList.add(new Lion(fileReader.nextLine()));
					}
				}
				fileReader.close();
			}catch(Exception e) {
				System.out.println("Error creating an animal!");
			}
		}
		
		private static void startThreads() {
			for(int i = 0; i < input; i++) {
				Run run = Run.createAndStart("thread" + i, "output" + i + ".txt", animalList);
				threadList.add(run);
			}
			
			for(int i = 0; i < input; i++) {
				try {
					threadList.get(i).getThread().join();
				} catch (Exception e) {
					System.out.println("Error creating, running or finishing threads!");
				}
			}
			
		}
	
}