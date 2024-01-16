package test;

import manager.TestManager;

public class BaseTest {

	public BaseTest getCurrentObject() {
		return this;
	}
	
	public void addLog(String LogMessage) {
		TestManager.addLogToTest(this, LogMessage);
	}
	
	public void addAuthor(String AuthorName) {
		TestManager.assignAuthorToTest(this, AuthorName);
	}
}
