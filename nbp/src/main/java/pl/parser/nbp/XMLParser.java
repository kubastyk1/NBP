package pl.parser.nbp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

	private InputStream stream;
	private Map<Integer, ArrayList<Double>> map = new HashMap<Integer, ArrayList<Double>>();
	private ArrayList<Double> buyRents = new ArrayList<Double>();
	private ArrayList<Double> sellRents = new ArrayList<Double>();

	public XMLParser(InputStream stream){
		this.stream = stream;
	}

	public Map<Integer, ArrayList<Double>> getRents(){

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(stream);

			getBuyRent(doc);
			getSellRent(doc);

			map.put(1, buyRents);
			map.put(2, sellRents);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return map;
	}

	private void getSellRent(Document doc){
		NodeList nList = doc.getElementsByTagName("Ask");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			Element eElement = (Element) nNode;
			buyRents.add(Double.parseDouble(eElement.getTextContent()));
		}
	}

	private void getBuyRent(Document doc){
		NodeList nList = doc.getElementsByTagName("Bid");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			Element eElement = (Element) nNode;
			sellRents.add(Double.parseDouble(eElement.getTextContent()));
		}
	}

}
