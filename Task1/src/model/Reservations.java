package model;

public class Reservations {
	private static final int min = 10000;
    private static final int max = 99999;

	private int pnrNumber;
    private String passengerName;
    private String trainNumber;
    private String trainName;
    private String classType;
    private String journeyDate;
    private String from;
    private String to;
    
    public Reservations(int pnrNumber,String passengerName, String trainNumber,String classType,
    		String journeyDate, String from, String to,String trainName) {
    	this.pnrNumber = pnrNumber;
    	this.passengerName= passengerName;
    	this.trainNumber= trainNumber;
    	this.classType = classType;
    	this.journeyDate = journeyDate;
    	this.from = from;
    	this.to = to;
    	this.trainName = trainName;
    	
    }

}
