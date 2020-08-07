package listeners;

import drivemanager.DriverManager;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Utility;

import java.io.IOException;

public class ScreenshotListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            Utility.captureScreenshot(result.getName(), DriverManager.getDriver(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            Utility.captureScreenshot(result.getName(), DriverManager.getDriver(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
