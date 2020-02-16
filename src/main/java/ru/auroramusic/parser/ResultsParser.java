package ru.auroramusic.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultsParser {
    private final Map<String, String>  partipants = new HashMap<>();
    private  Document doc;
    private XPath xPath= XPathFactory.newInstance().newXPath();

    public ResultsParser(File file) {
        try {
        doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        }catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            doc = (doc != null) ? doc : null;
        }
    }

    public static String getTransformed() {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        // включаем поддержку пространства имен XML
        builderFactory.setNamespaceAware(true);
        DocumentBuilder builder;
        try {
            builder = builderFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void readParticipants() {
        try {
            // получаем список всех узлов, которые отвечают условию
            XPathExpression xPathExpression = xPath.compile( "/Ski123Data/Participants");
            NodeList nodeList = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
            Node node;
            for (int i = 0; i < nodeList.getLength(); i++) {
                node = nodeList.item(i);
                node.getChildNodes();
//                partipants.put(nodeList.item(i).getNodeValue(), );
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
