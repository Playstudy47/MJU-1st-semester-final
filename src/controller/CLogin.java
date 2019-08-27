package controller;
import java.io.FileNotFoundException;

import entity.ELogin;


public class CLogin {
	private ELogin eLogin;
	
	public CLogin() {
		this.eLogin =  new ELogin();
	}

	public boolean authenticate(String userId, String password) throws FileNotFoundException {
		boolean validUser = eLogin.authenticate(userId, password);
		return validUser;
		
	}

}
