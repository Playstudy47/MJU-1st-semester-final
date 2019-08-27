package view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

import controller.CDirectory;
import entity.EDirectory;
import entity.ELecture;

public class VDirectory {
	

	private CDirectory cDirectory;
	public VDirectory() {
		this.cDirectory = new CDirectory();
	}
	
	public String show(String fileName) throws IOException {
		
		Vector<EDirectory> items;
		
		
		try {
			items = this.cDirectory.getItems(fileName);
			
			System.out.println("");
			System.out.println("Select Number");
			for(EDirectory item: items) {
				System.out.println(item.getNumber() + " " + item.getName());
			}
			
			
			System.out.println("");
			System.out.print("Select : ");
			Scanner scanner = new Scanner(System.in);
			
			int number = scanner.nextInt();
			int index = 0;
			String result = null;
			boolean found = false;
			for(index = 0; index < items.size() && !found; index++) {
				if(items.get(index).getNumber() == number) {
					found = true;
					result =  items.get(index).getHyperLink();
				}
			}
			return result;
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	
}
