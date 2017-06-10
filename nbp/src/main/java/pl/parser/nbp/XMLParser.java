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
	private Map<Integer, ArrayList<Double>> map;
	private ArrayList<Double> buyRents;
	private ArrayList<Double> sellRents;

	public XMLParser(InputStream stream){
		this.stream = stream;
		map = new HashMap<Integer, ArrayList<Double>>();
	}

	public Map<Integer, ArrayList<Double>> getRents(){

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(stream);

			sellRents = getRentFromNodes(doc, "Bid");
			buyRents = getRentFromNodes(doc, "Ask");

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

	private ArrayList<Double> getRentFromNodes(Document doc, String nodeName){
		ArrayList<Double> rents = new ArrayList<Double>();
		NodeList nList = doc.getElementsByTagName(nodeName);

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			Element eElement = (Element) nNode;
			rents.add(Double.parseDouble(eElement.getTextContent()));
		}

		return rents;
	}

}
