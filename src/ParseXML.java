import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeremy Chen
 *	"Runner" class containing DocumentBuilder for parsing XML and interpreting as Simulations
 */
public class ParseXML {
    
    protected static final String TYPE_NOT_SUPPORTED = "Simulation type in XML file not supported";
    private final String TYPE_ATTRIBUTE;
    private final DocumentBuilder DOCUMENT_BUILDER;

    /**
     * @param type
     * 
     * Will take in a type param to tell program what tag to look for in XML file root
     */
    public ParseXML (String type) {
        DOCUMENT_BUILDER = getDocumentBuilder();
        TYPE_ATTRIBUTE =  type;
    }

    /**
     * @param dataFile
     * @return returns a Simulation based off of the XML file chosen
     * @throws XMLException
     * 
     * Contains main functionality of this class: will load in XML File, and call requisite XMLHelper class to build a Simulation
     */
    public Simulation getSimulation (File dataFile) throws XMLException{
        Element root = getRootElement(dataFile);
        if (! isValidFile(root, Simulation.SUPPORTED_TYPES)) throw new XMLException(TYPE_NOT_SUPPORTED, Simulation.SUPPORTED_TYPES);
        String type = root.getAttribute(TYPE_ATTRIBUTE);
        
        NodeList paramNodes = root.getElementsByTagName("params");        
        NodeList cellNodes = root.getElementsByTagName("cells");
        NodeList dimNodes = root.getElementsByTagName("dimensions");
        NodeList headNodes = root.getElementsByTagName("head");
        XMLHelper helperInstance;
    
        try {
            Class<?> helperCls = Class.forName(type + "XMLHelper");
            helperInstance = (XMLHelper)(helperCls).getConstructor().newInstance();         
            Method getCells = helperCls.getMethod("getCells", NodeList.class);
            Method getGrid = helperCls.getMethod("getGrid", NodeList.class, ArrayList.class);
            Method initSimulation = helperCls.getMethod("initSimulation", NodeList.class, Grid.class);

            ArrayList<Cell> cells = (ArrayList<Cell>)(getCells.invoke(helperInstance, cellNodes));
            Grid g = (Grid)(getGrid.invoke(helperInstance, dimNodes, cells));
            Simulation sim = helperInstance.initSimulation(paramNodes, g);
            return sim;
        } 
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // TODO fix this later
        return null;
        // throw xml error
    }
    
    /**
     * @param xmlFile
     * @return returns root Element of a given xmlFile
     * 	
     * Taken from Duvall's sample XML parser
     */
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

    /**
     * @param root
     * @param types
     * @return
     * 
     * Will determine whether the file is valid or not
     */
    private boolean isValidFile (Element root, List<String> types) {
        for(String type: types) if(getAttribute(root, TYPE_ATTRIBUTE).equals(type)) return true;
        return false;
    }

    /**
     * @param e
     * @param attributeName
     * @return
     * 
     * Will return the String value of a specific Element's attribute
     */
    private String getAttribute (Element e, String attributeName) {
        return e.getAttribute(attributeName);
    }

    //NOT USED
//    /**
//     * @param e
//     * @param tagName
//     * @return
//     * 
//     * Will return 
//     */
//    private String getTextValue (Element e, String tagName) {
//        NodeList nodeList = e.getElementsByTagName(tagName);
//        if (nodeList != null && nodeList.getLength() > 0) {
//            return nodeList.item(0).getTextContent();
//        }
//        else {
//            // FIXME: empty string or null, is it an error to not find the text value?
//            return "";
//        }
//    }

    /**
     * @return
     * Will return a Document Builder
     */
    private DocumentBuilder getDocumentBuilder () {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            throw new XMLException(e);
        }
    }
}