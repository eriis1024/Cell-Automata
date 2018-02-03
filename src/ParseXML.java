import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.scene.paint.Color;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseXML {
	
	private static final Grid fooGrid = new BasicGrid(1,1,new ArrayList<Cell>(), Color.BLACK);
	public static final String ERROR_MESSAGE = "XML file does not represent %s";
    // name of root attribute that notes the type of file expecting to parse
    private final String TYPE_ATTRIBUTE;
    // keep only one documentBuilder because it is expensive to make and can reset it before parsing
    private final DocumentBuilder DOCUMENT_BUILDER;

	public ParseXML (String type) {
		DOCUMENT_BUILDER = getDocumentBuilder();
		TYPE_ATTRIBUTE =  type;
	}
	
	public Simulation getSimulation (File dataFile) {
		Element root = getRootElement(dataFile);
        if (! isValidFile(root, Simulation.SUPPORTED_TYPES)) throw new XMLException(ERROR_MESSAGE, Simulation.SUPPORTED_TYPES);
        // read data associated with the fields given by the object
        Map<String, String> results = new HashMap<>();
        String type = root.getAttribute("simulation");

        for (String field : Simulation.DATA_FIELDS) {
        	System.out.println(field);
        	System.out.println(getTextValue(root, field));
            results.put(field, getTextValue(root, field));
        }
        
        
        
        
        return new ConwaySimulation(getCells(cells));
	}
	
	public ArrayList<Cell> getCells (NodeList cellNodes){
		for(int i = 0; i<cellNodes.getLength(); i++) { 
			System.out.println();
		}
		return null;
	}
	
	public Grid getGrid (Element root) {
		
		
		NodeList cells = root.getElementsByTagName("cells");
		
		return new Grid(50,50, getCells(cells));
	}
	
    // Get root element of an XML file
    private Element getRootElement (File xmlFile) {
        try {
            DOCUMENT_BUILDER.reset();
            Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
            return xmlDocument.getDocumentElement();
        }
        catch (SAXException | IOException e) {
            throw new XMLException(e, "Could not get root of document");
        }
    }

    // Returns if this is a valid XML file for the specified object type
    private boolean isValidFile (Element root, List<String> types) {
    	for(String type: types) if(getAttribute(root, TYPE_ATTRIBUTE).equals(type)) return true;
        return false;
    }

    // Get value of Element's attribute
    private String getAttribute (Element e, String attributeName) {
        return e.getAttribute(attributeName);
    }

    // Get value of Element's text
    private String getTextValue (Element e, String tagName) {
        NodeList nodeList = e.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
            // FIXME: empty string or null, is it an error to not find the text value?
            return "";
        }
    }

    // Helper method to do the boilerplate code needed to make a documentBuilder.
    private DocumentBuilder getDocumentBuilder () {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            throw new XMLException(e);
        }
    }

}
