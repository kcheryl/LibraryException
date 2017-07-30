package library.exception.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import library.exception.beans.ActionAttribute;
import library.exception.beans.ExceptionInfo;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class ServiceImpl implements IService {
	Map<ExceptionInfo, List<ActionAttribute>> infoMap;

	public ServiceImpl() {
		infoMap = new HashMap<>();
	}

	public Map<ExceptionInfo, List<ActionAttribute>> getInfoMap() {
		return new TreeMap<>(infoMap);
	}

	public void init() {

		try {
			File inputFile = new File("input.xml");

			// get the factory
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			// parse using builder to get DOM representation of XML file
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			project(doc);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void project(Document doc) {
		// get a nodelist of elements
		NodeList projList = doc.getElementsByTagName("project");
		for (int temp = 0; temp < projList.getLength(); temp++) {
			// get current element
			Node projNode = projList.item(temp);
			if (projNode.getNodeType() == Node.ELEMENT_NODE) {
				Element projElement = (Element) projNode;
				String projAttribute = projElement.getAttribute("name");
				module(projElement, projAttribute);
			}
		}
	}

	private void module(Element projElement, String projAttribute) {
		NodeList modList = projElement.getElementsByTagName("module");
		for (int i = 0; i < modList.getLength(); i++) {
			Node modNode = modList.item(i);
			if (modNode.getNodeType() == Node.ELEMENT_NODE) {
				Element modElement = (Element) modNode;
				String modAttribute = modElement.getAttribute("name");
				exception(modElement, projAttribute, modAttribute);
			}
		}
	}

	private void exception(Element modElement, String projAttribute, String modAttribute) {
		NodeList excepList = modElement.getElementsByTagName("exception");
		for (int j = 0; j < excepList.getLength(); j++) {
			Node excepNode = excepList.item(j);
			if (excepNode.getNodeType() == Node.ELEMENT_NODE) {
				Element excepElement = (Element) excepNode;
				String excepAttribute = excepElement.getAttribute("type");
				action(excepElement, projAttribute, modAttribute, excepAttribute);
			}
		}
	}

	private void action(Element excepElement, String projAttribute, String modAttribute, String excepAttribute) {
		ExceptionInfo info = new ExceptionInfo(projAttribute, modAttribute, excepAttribute);

		NodeList actionList = excepElement.getElementsByTagName("action");
		NodeList children = actionList.item(0).getChildNodes();
		for (int k = 0; k < children.getLength(); k++) {
			if (children.item(k).getNodeType() == Node.ELEMENT_NODE) {
				Element childElement = (Element) children.item(k);
				String nodeName = childElement.getNodeName();
				NamedNodeMap attributes = childElement.getAttributes();

				for (int l = 0; l < attributes.getLength(); l++) {
					String attrNodeName = attributes.item(l).getNodeName();
					String attrNodeValue = attributes.item(l).getNodeValue();

					// if info is already included in the map
					if (infoMap.containsKey(info)) {
						Map<String, String> attrMap = new HashMap<String, String>();
						attrMap.put(attrNodeName, attrNodeValue);
						infoMap.get(info).add(new ActionAttribute(nodeName, attrMap));
					}
					// if info is new
					else {
						Map<String, String> attrMap = new HashMap<String, String>();
						attrMap.put(attrNodeName, attrNodeValue);
						List<ActionAttribute> list = new ArrayList<ActionAttribute>();
						list.add(new ActionAttribute(nodeName, attrMap));
						infoMap.put(info, list);
					}
				}
			}
		}
	}
}
