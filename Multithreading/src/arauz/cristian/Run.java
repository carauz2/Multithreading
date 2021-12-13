package arauz.cristian;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import animal.Animal;

public class Run implements Runnable{
	
		public String threadName;
		private String filePathWithName;
		private ArrayList<Animal> animalList;
		private Thread thread;
		
		private File outputFile;
		private BufferedWriter fileWriter;
	
		
		Run(String threadName, String filePathWithName, ArrayList<Animal> animalList){
			this.threadName = threadName;
			this.filePathWithName = filePathWithName;
			this.animalList = animalList;
			thread = new Thread(this, threadName);
		}
	
		@Override
		public void run() {
			writeToFile();
		}
		
		private void writeToFile() {
			try {
				outputFile = new File("" + filePathWithName);
				fileWriter = new BufferedWriter(new FileWriter(outputFile));
				for(Animal thisAnimal : animalList) {
					fileWriter.write("" + thisAnimal.getName() + " is a " + thisAnimal.getType() + "\n");
				}
					fileWriter.close();
					System.out.println("Successfully outputted to file!\n" + outputFile.getAbsolutePath());
				}catch(Exception e) {
					System.out.println("Error writing to file!");
			}
		}
	
		public static Run createAndStart(String threadName, String filePathWithName, ArrayList<Animal> animalList) {
			Run myRun = new Run(threadName, filePathWithName, animalList);
			myRun.thread.start();
			return myRun;
		}
	
		public Thread getThread() {
			return thread;
		}

}