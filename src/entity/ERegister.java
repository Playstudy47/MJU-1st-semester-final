package entity;

import java.util.Scanner;

public class ERegister {
	private String name;
	private String id;
	private String passwd;
	private String email;

	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getId() 
	{
		return id;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	public String getPasswd() 
	{
		return passwd;
	}
	public void setPasswd(String passwd) 
	{
		this.passwd = passwd;
	}
	public void read(Scanner scanner) {
		this.id = scanner.next();
		this.passwd = scanner.next();
		this.name = scanner.next();
	}

}
