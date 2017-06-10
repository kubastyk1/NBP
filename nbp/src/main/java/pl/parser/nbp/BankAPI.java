package pl.parser.nbp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class BankAPI {

	private Map<Integer, ArrayList<Double>> rents;

	public void makeRequest(Currency currency, String startDate, String endDate){
		try{
			String uri = "http://api.nbp.pl/api/exchangerates/rates/c/"
					+ currency.name() + "/" + startDate.toString() + "/" + endDate.toString();
			URL url = new URL(uri);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");

			InputStream stream = connection.getInputStream();
			XMLParser xmlParser = new XMLParser(stream);
			rents = xmlParser.getRents();

			printRents();

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public double getMeanBuyRent(){

		double sum = 0;

		for(double d : rents.get(1)){
			sum += d;
		}
		return sum/rents.get(1).size();
	}

	public double getStandardDeviationSellRent(){

		double sum = 0;
		double mean = 0;
		double variance = 0;

		for(double d : rents.get(2)){
			sum += d;
		}
		mean = sum/rents.get(2).size();
		sum = 0;

		for(double d : rents.get(2)){
			sum += Math.pow(d-mean, 2);
		}
		variance = sum / rents.get(2).size();

		return Math.sqrt(variance);
	}

	private void printRents(){

		for(Object d : rents.get(1)){
			System.out.println(d);
		}

		for(Object d : rents.get(2)){
			System.out.println("l" + d);
		}
	}
}
