package view;
import java.io.FileNotFoundException;
import java.util.Scanner;

import controller.CLogin;

public class VLogin {
	
	private CLogin cLogin;
	
	public VLogin() {
		this.cLogin = new CLogin();
	}

	public String authenticate() {
		Scanner sc = new Scanner(System.in);		

		System.out.print("ID : ");
		String userId = sc.next();
		System.out.print("PassWord : ");
		String password = sc.next();
		System.out.println("");
				
		try {
			this.cLogin.authenticate(userId, password);
			return userId;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
