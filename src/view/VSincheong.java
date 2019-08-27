package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import entity.ELecture;

public class VSincheong 
{
	
	public void Show() throws FileNotFoundException 
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        File file = new File("../LMS/data/sincheong");
   	 	BufferedReader br = new BufferedReader(new FileReader("../LMS/data/basket"));
   	 	String key;
   	 	VLecture m_lec = VLecture.GetInstance();
        try
        {
        	System.out.println("");
            while(true) 
            {
                String line = br.readLine();
                
                if (line == null)
                	break;
                
                System.out.println(line);
            }
        
            System.out.println("");
            br.close();
            key = sc.next();
            
            if(m_lec.GetSugangMap().containsKey(key))
            {
            	FileWriter fw = new FileWriter(file, true);
                
            	fw.write("\r\n"+key + "  "+ m_lec.GetSugangMap().get(key));
                fw.close();
            
                System.out.println("Your application for " + m_lec.GetSugangMap().get(key) + " is complete.");
                System.out.println("");
            	
            }
            
            else 
            {
            	System.out.println("Invalid Input");
            }
        }	
        catch (IOException e)
        {
            e.printStackTrace();
        }
	}
}
