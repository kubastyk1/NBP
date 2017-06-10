package pl.parser.nbp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ExchangeManager {

	private Scanner reader;
	private BankAPI bankAPI;
	private Currency currencyCode;
	private String startDate;
	private String endDate;
	private String message;

	public ExchangeManager(){
		reader = new Scanner(System.in);
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
			System.out.println("Mean buy rent: " + bankAPI.getMeanBuyRent());
			System.out.println("Standard deviation sell rent: " + bankAPI.getStandardDeviationSellRent());
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
		String newDate = null;
		message = reader.next();
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(message);
			newDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		} catch (ParseException e) {
			System.out.println("Bad date Format! Write proper date format: (YYYY-MM-DD)");
			getDateFromUser();
		}
		return newDate;
	}
}
