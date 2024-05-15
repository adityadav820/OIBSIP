package model;

import java.util.Scanner;

public class User {

	private String userName;
	private String password;
	
	Scanner sc = new Scanner(System.in); 
	
	public String getUserName() {
		System.out.println("Enter Username : ");
		userName = sc.nextLine();
		return userName;
	}
	
	public String getPassword() {
		System.out.println("Enter password : ");
		password = sc.nextLine();
		return password;
	}
}
