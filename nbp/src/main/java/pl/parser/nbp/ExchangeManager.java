package pl.parser.nbp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ExchangeManager {

	private Scanner reader;
	private SimpleDateFormat dateFormat;
	private BankAPI bankAPI;
	private Currency currencyCode;
	private String startDate;
	private String endDate;
	private String message;

	public ExchangeManager(){
		reader = new Scanner(System.in);
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		bankAPI = new BankAPI();
	}

	public void start(){
		while(true){
			System.out.println("Write currency code: (USD, EUR, CHF, GBP)");
			currencyCode = getCurrencyFromUser();
			System.out.println("Write start date: (YYYY-MM-DD)");
			startDate = getDateFromUser();
			System.out.println("Write end date: (YYYY-MM-DD)");
			endDate = getDateFromUser();
			bankAPI.makeRequest(Currency.EUR, startDate, endDate);
		}
	}

	public Currency getCurrencyFromUser(){
		message = reader.next();
		message = message.toUpperCase();

		if(!isCurrency(message)){
			System.out.println("Bad currency code! Write proper currency code: (USD, EUR, CHF, GBP)");
			getCurrencyFromUser();
		}

		return Currency.valueOf(message);
	}

	public boolean isCurrency(String message){
		for(Currency currency : Currency.values()){
			if(message.equals(currency.name())){
				return true;
			}
		}
		return false;
	}

	public String getDateFromUser(){
		Date date = new Date();
		message = reader.next();
		try {
			date = dateFormat.parse(message);
		} catch (ParseException e) {
			System.out.println("Bad date Format! Write proper date format: (YYYY-MM-DD)");
			getDateFromUser();
		}
		return message;
	}
}
