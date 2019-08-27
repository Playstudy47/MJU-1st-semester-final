package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import entity.ERegister;

public class DAORegister {

	public Vector<ERegister> getItems(String fileName) throws FileNotFoundException {
		Vector<ERegister> items = new Vector<ERegister>();
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		
		while(scanner.hasNext()) {
			ERegister eRegister = new ERegister();
			eRegister.read(scanner);
			items.add(eRegister);
		}
		scanner.close();
		return items;
	}

}
