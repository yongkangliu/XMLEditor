import java.io.File;
import java.io.IOException;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLFile {
    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

    // Load and parse XML file into DOM
    public Document parse(String filePath) {
        Document document = null;
        try {
            // DOM parser instance
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            // parse an XML file into a DOM tree
            document = builder.parse(new File(filePath));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static void writeXML(XMLTree treePanel, String fileName) {
        //XMLFile parser = new XMLFile();
        //Document document = parser.parse(fileName);

        //Element root = document.createElement("products");

    }

    public static XMLTree readXML(String fileName) {
        XMLFile parser = new XMLFile();
        Document document = parser.parse(fileName);

        // get root element
        Element quizElement = document.getDocumentElement();
        XMLTree treePanel = XMLTree.getInstance(new TreeNodeQuiz(quizElement.getAttribute("name")));

        // traverse child elements
        NodeList questionNodes = quizElement.getChildNodes();
        for (int i = 0; i < questionNodes.getLength(); i++) {
            Node node = questionNodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element questionElement = (Element) node;
                // process child element
                TreeNode questionTreeNode = new TreeNodeQuestion(questionElement.getAttribute("name"));
                DefaultMutableTreeNode qNode = treePanel.addObject(null, questionTreeNode);

                NodeList optionNodes = questionElement.getChildNodes();
                for (int j = 0; j < optionNodes.getLength(); j++) {
                    Node optionNode = optionNodes.item(j);
                    if (optionNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element optionElement = (Element) optionNode;
                        TreeNode optionTreeNode = new TreeNodeOption(optionElement.getAttribute("name"));
                        treePanel.addObject(qNode, optionTreeNode);
                    }
                }
            }
        }

        return treePanel;
    }
}