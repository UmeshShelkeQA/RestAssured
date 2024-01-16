package manager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportManager {
	private ExtentReports extentReporter ;
	private String PATH;
	private static ReportManager reportManager;
	private ReportManager() {}

	public synchronized static ReportManager getReportManager() {
		if(reportManager == null)
			reportManager = new ReportManager();
		return reportManager;
	}
	
	public ExtentReports getExtentReporter() {
		if(extentReporter ==null) {
			String workingDir = System.getProperty("user.dir");
			String reportName = "Extent.html";

			if(System.getProperty("os.name").toLowerCase().contains("mac")
					|| System.getProperty("os.name").toLowerCase().contains("linux")) {
				PATH = "//test-output//" + reportName;
			}else {
				PATH = "\\test-output\\" + reportName;
			}

			ExtentSparkReporter htmlReporter = new ExtentSparkReporter(workingDir + PATH); 
			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config().setDocumentTitle("Report");
			htmlReporter.config().setEncoding("utf-8");
			htmlReporter.config().setReportName("Automated Tests - Report");
			
		
			extentReporter = new ExtentReports();
			extentReporter.attachReporter(htmlReporter);
		}
		return extentReporter;
	}

}
