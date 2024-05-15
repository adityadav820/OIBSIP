package task1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import model.User;
import service.ReservationServiceImpl;

public class Main {
	
public static void main(String[] args) {
	
	
	Scanner sc = new Scanner(System.in);
	User u1 = new User();
	String userName = u1.getUserName();
	String password = u1.getPassword();
	
	String url = "jdbc:mysql://localhost:3306/reservation";
	Connection conn = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 conn = DriverManager.getConnection(url,userName,password);
		System.out.println("User Connection Granted.\n");
		
		
	} catch (SQLException e) {
		System.err.println("SQLException : "+ e.getMessage());
	} 
	catch (Exception e) {
		System.err.println("SQLException : "+ e.getMessage());
	}
	while (true) {
        String InsertQuery = "insert into reservations values (?, ?, ?, ?, ?, ?, ?,?)";
        String DeleteQuery = "DELETE FROM reservations WHERE pnrNumber = ?";
        String ShowQuery = "Select * from reservations";

        System.out.println("Enter the choice : ");
        System.out.println("1. Reserve Seat.\n");
        System.out.println("2. Cancel Reservation\n");
        System.out.println("3. Show All Reservations.\n");
        System.out.println("4. Exit.\n");
        int choice = sc.nextInt();
        
        if(choice ==1) {
        	ReservationServiceImpl reserveService = new ReservationServiceImpl();
        	reserveService.ReserveSeat(conn,InsertQuery);
        }
        else if (choice == 2) {
        	ReservationServiceImpl reserveService = new ReservationServiceImpl();
        	reserveService.cancelReservation(conn,DeleteQuery);
        }
        else if (choice == 3) {
        	ReservationServiceImpl reserveService = new ReservationServiceImpl();
        	reserveService.showAllReservations(conn,ShowQuery);
        }

        else if (choice == 4) {
            System.out.println("Exiting the program.\n");
            break;
        }

        else {
            System.out.println("Invalid Choice Entered.\n");
        }
	}
	    
	    
	    
	    
		

	}

}
