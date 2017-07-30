package library.exception.action;

import java.util.Map;

public abstract class Action {
	public abstract void executeAction(Map<String, String> attributes);
}
