package listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetrier implements IRetryAnalyzer{
	int initialTry = 0;
	int maxTry = 2;
	
	@Override
	public boolean retry(ITestResult result) {
		if(initialTry < maxTry) {
			initialTry++;
			return true;
		}
		return false;
	}
}
