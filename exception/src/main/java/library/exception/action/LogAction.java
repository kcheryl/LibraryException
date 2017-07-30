package library.exception.action;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class LogAction extends Action {

	@Override
	public void executeAction(Map<String, String> attributes) {
		Set<Entry<String, String>> entry = attributes.entrySet();
		Iterator<Entry<String, String>> iterator = entry.iterator();
		while (iterator.hasNext()) {
			Object element = iterator.next();
			System.out.println("Logging " + element + "...");
		}
	}

}
