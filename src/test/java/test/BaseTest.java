package test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import manager.TestManager;

public class BaseTest {

	protected static Logger logger;

	static {
		logger = Logger.getLogger("APITestLogger");
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/log4j.properties");
	}
	
	public BaseTest getCurrentObject() {
		return this;
	}
	
	public void addLog(String LogMessage) {
		TestManager.addLogToTest(this, LogMessage);
	}
	
	public void addAuthor(String AuthorName) {
		TestManager.assignAuthorToTest(this, AuthorName);
	}
	
	public Logger getLogger() {
		return logger;
	}
}
