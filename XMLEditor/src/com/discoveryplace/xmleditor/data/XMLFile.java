package com.discoveryplace.xmleditor.data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.discoveryplace.xmleditor.ui.XMLTree;

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

    public static void writeXML(XMLTree treePanel, String fileName) throws ParserConfigurationException,
            FileNotFoundException, TransformerException {
        System.out.println("writing");
        JTree tree = treePanel.getJTree();
        DefaultMutableTreeNode quizNode = (DefaultMutableTreeNode) tree.getModel().getRoot();
        System.out.println("quiz:" + quizNode.getLevel());

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element quizNodeInfo = doc.createElement("quiz");
        quizNodeInfo.setAttribute("test", "ttt");
        doc.appendChild(quizNodeInfo);

        for (Enumeration<?> questions = quizNode.children(); questions.hasMoreElements();) {
            DefaultMutableTreeNode questionNode = (DefaultMutableTreeNode) questions.nextElement();
            System.out.println("question:" + questionNode.getLevel());

            Element questionNodeInfo = doc.createElement("question");
            questionNodeInfo.setAttribute("test2", "222");
            quizNodeInfo.appendChild(questionNodeInfo);

            for (Enumeration<?> options = questionNode.children(); options.hasMoreElements();) {
                DefaultMutableTreeNode optionNode = (DefaultMutableTreeNode) options.nextElement();
                System.out.println("option:" + optionNode.getLevel());

                Element optionNodeInfo = doc.createElement("option");
                optionNodeInfo.setAttribute("test3", "333");
                questionNodeInfo.appendChild(optionNodeInfo);
            }
        }

        wirteFile(doc, fileName);
    }

    private static void wirteFile(Document doc, String fileName) throws TransformerException, FileNotFoundException {
        TransformerFactory tf = TransformerFactory.newInstance();
        tf.setAttribute("indent-number", new Integer(4));
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(doc);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
        StreamResult result = new StreamResult(pw);
        transformer.transform(source, result);
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