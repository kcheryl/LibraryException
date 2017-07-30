package library.exception.service;

public interface IService {
	public void init();

	public String handleAction(String projName, String modName, Exception exception);
}
