package main.java.com.rp.automation.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RerunFailedTCS implements IRetryAnalyzer{

	@Override
	public boolean retry(ITestResult arg0) {
		
		return false;
	}

}
