package main;
import java.io.IOException;

import ui.LoginFrame;
import ui.MainFrame;

public class Main
{
	
	public static void main(String[] args) throws IOException 
	{
		new LoginFrame();
	}
}
	
	
/*
public class Main 
{
	
	private VLogin m_login;
	private VSugangSincheong m_enrollment;
	private UserInterface m_userInterface;
	
	public Main() 
	{

		this.m_login = new VLogin();
	}

	private void Run() throws IOException 
	{
		
		this.m_userInterface = new UserInterface();
		this.m_userInterface.Show();
		String userId = this.m_login.authenticate();
		
		
		if(userId == null)
			return;
		
		this.m_enrollment = new VSugangSincheong();
		this.m_enrollment.Show();		
	}

	public static void main(String[] args) throws IOException
	{
		Main main = new Main();
		main.Run();
	}
}
*/

