package main;

public class UserInterface {

	public void Show() {
		// TODO Auto-generated method stub
		String outLine = "@";
		String title = "�������б� �α���";
		for(int i = 0; i < 25; ++i)
		{
			outLine = outLine + "@";
		}
		System.out.println(outLine);
		System.out.println("@                        @");
		System.out.println("@                        @");
		System.out.println("@      " + title + "               @");
		System.out.println("@                        @");
		System.out.println("@                        @");
		System.out.println(outLine);
	}

	
}
