package library.exception.beans;

import java.util.Map;

public class ActionAttribute {
	String actionName;
	Map<String, String> attributeMap;

	public ActionAttribute(String actionName, Map<String, String> attributeMap) {
		this.actionName = actionName;
		this.attributeMap = attributeMap;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Map<String, String> getAttributeMap() {
		return attributeMap;
	}

	public void setMap(Map<String, String> map) {
		this.attributeMap = map;
	}

	@Override
	public String toString() {
		return "ActionAttribute [" + actionName + ", " + attributeMap + "]";
	}
}
