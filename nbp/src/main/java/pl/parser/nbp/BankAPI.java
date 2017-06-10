package pl.parser.nbp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BankAPI {

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
			Map<Integer, ArrayList> rents = xmlParser.getRents();

			for(Object d : rents.get(1)){
				System.out.println(d);
			}

			for(Object d : rents.get(2)){
				System.out.println("l" + d);
			}

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public double getMeanBuyRent(){

		return 0;
	}

	public double getStandardDeviationSellRent(){


		return 0;
	}
}
