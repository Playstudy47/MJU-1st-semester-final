package entity;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ELogin {
	private String rUserId;
	private String rPassword;

	private void read(Scanner scanner) 
	{
		this.rUserId = scanner.next();
		this.rPassword = scanner.next();
	}


	public boolean authenticate(String userId, String password) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("data/login"));	
		while(true) {
			this.read(scanner);
			if(this.rUserId.equals(userId) && this.rPassword.equals(password)) {
				scanner.close();
				return true;
			}
			if(scanner.hasNext())
			{
				break;
			}
		}
		
		scanner.close();
		return false;
	}
	


}