package pl.parser.nbp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ExchangeManager {

	private Scanner reader;
	private SimpleDateFormat dateFormat;
	private String currencyCode;
	private Date startDate;
	private Date endDate;

	public ExchangeManager(){
		reader = new Scanner(System.in);
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	}

	public void start(){
		while(true){
			System.out.println("Write currency code: (USD, EUR, CHF, GBP)");
			currencyCode = reader.next();
			System.out.println("Write start date: (YYYY-MM-DD)");
			startDate = getDateFromUser();
			System.out.println("Write end date: (YYYY-MM-DD)");
			endDate = getDateFromUser();
		}
	}

	public Date getDateFromUser(){
		Date date = new Date();
		String message = reader.next();
		try {
			date = dateFormat.parse(message);
		} catch (ParseException e) {
			System.out.println("Bad date Format! Write proper date format: (YYYY-MM-DD)");
			getDateFromUser();
		}
		return date;
	}
}
