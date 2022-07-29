package dao.parser;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DOMParser {

    public static String DBUrl;
    public static String DBDriver;
    public static String user;
    public static String password;

    public static void setConnectionProperties() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();
        Path path = Paths.get("src/main/resources/dataAccess.xml");
        Document document = builder.parse(new File(path.toString()));

        Node root = document.getDocumentElement();
        NodeList dataSource = root.getChildNodes();
        for (int i = 0; i < dataSource.getLength(); i++) {
            Node source = dataSource.item(i);
            if (source.getNodeType() != Node.TEXT_NODE) {
                NodeList sourceProps = source.getChildNodes();
                for (int j = 0; j < sourceProps.getLength(); j++) {
                    Node sourceProp = sourceProps.item(j);
                    if (sourceProp.getNodeType() != Node.TEXT_NODE) {
                        if (sourceProp.getNodeName().equals("connection-url"))
                            DBUrl = sourceProp.getChildNodes().item(0).getTextContent();
                        else if (sourceProp.getNodeName().equals("driver-class"))
                            DBDriver = sourceProp.getChildNodes().item(0).getTextContent();
                        else if (sourceProp.getNodeName().equals("user-name"))
                            user = sourceProp.getChildNodes().item(0).getTextContent();
                        else if (sourceProp.getNodeName().equals("password"))
                            password = sourceProp.getChildNodes().item(0).getTextContent();
                    }
                }
            }
        }
    }
}
