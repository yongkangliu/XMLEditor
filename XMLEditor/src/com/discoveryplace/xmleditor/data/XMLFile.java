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
    public static String NODE_NAME = "name";
    public static String NODE_DESCRIPTION = "description";
    public static String NODE_LEVEL = "level";
    public static String NODE_TYPE = "type";
    public static String NODE_URL = "url";
    public static String NODE_IS_ANSWER = "isAnswer";

    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

    // Load and parse XML file into DOM
    private Document parse(String filePath) {
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
        JTree tree = treePanel.getJTree();
        DefaultMutableTreeNode quizNode = (DefaultMutableTreeNode) tree.getModel().getRoot();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element quizNodeInfo = doc.createElement("quiz");
        TreeNodeQuiz quiz = (TreeNodeQuiz) quizNode.getUserObject();
        quizNodeInfo.setAttribute(NODE_NAME, quiz.getName());
        quizNodeInfo.setAttribute(NODE_DESCRIPTION, quiz.getDescription());
        doc.appendChild(quizNodeInfo);

        for (Enumeration<?> questions = quizNode.children(); questions.hasMoreElements();) {
            DefaultMutableTreeNode questionNode = (DefaultMutableTreeNode) questions.nextElement();

            Element questionNodeInfo = doc.createElement("question");
            TreeNodeQuestion question = (TreeNodeQuestion) questionNode.getUserObject();
            questionNodeInfo.setAttribute(NODE_NAME, question.getName());
            questionNodeInfo.setAttribute(NODE_DESCRIPTION, question.getDescription());
            questionNodeInfo.setAttribute(NODE_LEVEL, question.getQuestionLevel());
            questionNodeInfo.setAttribute(NODE_TYPE, question.getQuestionType());
            questionNodeInfo.setAttribute(NODE_URL, question.getUrl());
            quizNodeInfo.appendChild(questionNodeInfo);

            for (Enumeration<?> options = questionNode.children(); options.hasMoreElements();) {
                DefaultMutableTreeNode optionNode = (DefaultMutableTreeNode) options.nextElement();

                Element optionNodeInfo = doc.createElement("option");
                TreeNodeOption option = (TreeNodeOption) optionNode.getUserObject();
                optionNodeInfo.setAttribute(NODE_NAME, option.getName());
                optionNodeInfo.setAttribute(NODE_DESCRIPTION, option.getDescription());
                optionNodeInfo.setAttribute(NODE_IS_ANSWER, String.valueOf(option.isAnswer()));
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
        TreeNodeQuiz quiz = new TreeNodeQuiz(quizElement.getAttribute(NODE_NAME));
        quiz.setDescription(quizElement.getAttribute(NODE_DESCRIPTION));
        XMLTree treePanel = XMLTree.getInstance(quiz);

        // traverse child elements
        NodeList questionNodes = quizElement.getChildNodes();
        for (int i = 0; i < questionNodes.getLength(); i++) {
            Node node = questionNodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element questionElement = (Element) node;
                // process child element
                TreeNodeQuestion questionTreeNode = new TreeNodeQuestion(questionElement.getAttribute(NODE_NAME));
                questionTreeNode.setDescription(questionElement.getAttribute(NODE_DESCRIPTION));
                questionTreeNode.setQuestionLevel(questionElement.getAttribute(NODE_LEVEL));
                questionTreeNode.setQuestionType(questionElement.getAttribute(NODE_TYPE));
                questionTreeNode.setUrl(questionElement.getAttribute(NODE_URL));
                DefaultMutableTreeNode qNode = treePanel.addObject(null, questionTreeNode);

                NodeList optionNodes = questionElement.getChildNodes();
                for (int j = 0; j < optionNodes.getLength(); j++) {
                    Node optionNode = optionNodes.item(j);
                    if (optionNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element optionElement = (Element) optionNode;
                        TreeNodeOption optionTreeNode = new TreeNodeOption(optionElement.getAttribute(NODE_NAME));
                        optionTreeNode.setDescription(optionElement.getAttribute(NODE_DESCRIPTION));
                        optionTreeNode.setAnswer(optionElement.getAttribute(NODE_IS_ANSWER));
                        treePanel.addObject(qNode, optionTreeNode);
                    }
                }
            }
        }

        return treePanel;
    }
}