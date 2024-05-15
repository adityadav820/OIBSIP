package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import model.Reservations;

public class ReservationServiceImpl {
	
	private static final int min = 10000;
    private static final int max = 99999;
//    private  String InsertQuery = "insert into reservations values (?, ?, ?, ?, ?, ?, ?)";
//    private static String DeleteQuery = "DELETE FROM reservations WHERE pnr_number = ?";
//    private static String ShowQuery = "Select * from reservations";

	public void ReserveSeat(Connection conn , String InsertQuery) {
//		
		Scanner sc = new Scanner(System.in);
		
		
		Random random = new Random();
        int pnrNumber = random.nextInt(max) + min;
        
        System.out.println("Enter the passenger name : ");
        String passengerName = sc.nextLine();
        
        System.out.println("Available Trains are : 234,165,255");
        System.out.println("Enter the train number : ");
        String trainNumber = sc.nextLine();
        String trainName ;
        if(trainNumber == "234") {
        	 trainName = "Indian Express";
        }
        else if(trainNumber == "165") {
        	 trainName = "Yamuna Express";
        }
        else if(trainNumber == "255") {
        	 trainName = "North East Express";
        }
        else {
        	 trainName = "General Train";
        }
        System.out.println(trainName);
        System.out.println("Enter the class type : ");
        String classType = sc.nextLine();
        
        System.out.println("Enter the Journey date as 'YYYY-MM-DD' format");
        String journeyDate = sc.nextLine();
        
        System.out.println("Enter the starting place : ");
        String getFrom = sc.nextLine();
        
        System.out.println("Enter the destination place :  ");
        String getTo = sc.nextLine();
        
        Reservations p1 = new Reservations(pnrNumber,passengerName,trainNumber,classType,journeyDate, getFrom,getTo,trainName);
        
        try (PreparedStatement preparedStatement = conn.prepareStatement(InsertQuery)) {
            preparedStatement.setInt(1, pnrNumber);
            preparedStatement.setString(2, passengerName);
            preparedStatement.setString(3, trainNumber);
            preparedStatement.setString(4, trainName);
            preparedStatement.setString(5, classType);
            preparedStatement.setString(6, journeyDate);
            preparedStatement.setString(7, getFrom);
            preparedStatement.setString(8, getTo);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Record added successfully.");
            }

            else {
                System.out.println("No records were added.");
            }
        }

        catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
	}
	
	public void cancelReservation(Connection conn , String DeleteQuery) {
		System.out.println("Enter the PNR number to Cancel the Reservation : ");
		Scanner sc = new Scanner(System.in);
		
      int pnrNumber = sc.nextInt();
//      sc.close();
      try (PreparedStatement preparedStatement = conn.prepareStatement(DeleteQuery)) {
          preparedStatement.setInt(1, pnrNumber);
          int rowsAffected = preparedStatement.executeUpdate();

          if (rowsAffected > 0) {
              System.out.println("Record deleted successfully.");
          }

          else {
              System.out.println("No records were deleted.");
          }
      }

      catch (SQLException e) {
          System.err.println("SQLException: " + e.getMessage());
      }
	}
	
	public void showAllReservations(Connection conn,String ShowQuery) {
		try (PreparedStatement preparedStatement = conn.prepareStatement(ShowQuery);
              ResultSet resultSet = preparedStatement.executeQuery()) {
          System.out.println("\nAll records printing.\n");
          while (resultSet.next()) {

              System.out.println("PNR Number: " + resultSet.getString("pnrNumber"));
              System.out.println("Passenger Name: " + resultSet.getString("passengerName"));
              System.out.println("Train Number: " + resultSet.getString("trainNumber"));
              System.out.println("Train Name: " + resultSet.getString("trainName"));
              System.out.println("Class Type: " + resultSet.getString("classType"));
              System.out.println("Journey Date: " + resultSet.getString("journeyDate"));
              System.out.println("From Location: " + resultSet.getString("fromLocation"));
              System.out.println("To Location: " + resultSet.getString("toLocation"));
              System.out.println("--------------");
          }
      } catch (SQLException e) {
          System.err.println("SQLException: " + e.getMessage());
      }
	}

}
