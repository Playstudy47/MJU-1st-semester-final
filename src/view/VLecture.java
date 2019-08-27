package view;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

import controller.CLecture;
import entity.ELecture;

public class VLecture 
{
	private static VLecture m_instance;
	
	
	//--- Member Variables ---
	private CLecture cLecture;
	private static HashMap<String, String> sugang;

	//--- Member Functions ---
	private VLecture() 
	{
		this.cLecture = new CLecture();
		this.sugang = new HashMap<String, String>();
	}
	
	
	public static HashMap<String, String> GetSugangMap()
	{
		return sugang;
	}
	
	public static VLecture GetInstance()
	{
		if(m_instance == null)
		{
			m_instance = new VLecture();
		}
		
		return m_instance;
	}

	public ELecture Show(String fileName) 
	{
		Vector<ELecture> items;
		try 
		{
			items = this.cLecture.getItems(fileName);
			System.out.println("Select Number");
			System.out.println("");
			
			for(ELecture item: items) 
			{
				System.out.println(item.getNumber() + " " + item.getName());
				sugang.put(Integer.toString(item.getNumber()),item.getName());
			}
			
			System.out.println("");
			System.out.print("Select: ");

			Scanner scanner = new Scanner(System.in);
			int number = scanner.nextInt();
			
			ELecture.num = number;
			
			ELecture result = null;
			boolean found = false;
			
			for(int index = 0; index < items.size() && !found; index++)
			{
				if(items.get(index).getNumber() == number)
				{
					found = true;
					result =  items.get(index);
				}
			}
			
			return result;
			
		}
		catch (FileNotFoundException excep)
		{
			excep.printStackTrace();
		}
		return null;
		
	}
}
