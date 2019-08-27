package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import entity.EBasket;


public class DAOBasket {
	public Vector<EBasket> getItems(String fileName) throws FileNotFoundException {
		Vector<EBasket> items = new Vector<EBasket>();
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		
		while(scanner.hasNext()) {
			EBasket eBasket = new EBasket();
			eBasket.read(scanner);
			items.add(eBasket);
		}
		scanner.close();
		return items;
	}
}
