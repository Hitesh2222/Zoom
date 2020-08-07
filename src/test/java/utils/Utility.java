package utils;

import config.Config;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
    private static void createDirs(Path path) throws IOException {
        if (Files.exists(path)){
            System.out.println("File already exist !");
            return;
        }
        Files.createDirectories(path);
    }
    public static String getTimeStampWithFormat(){
        String configuredFormat = Config.getProperty("timestamp.pattern");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(configuredFormat);
        return simpleDateFormat.format(new Date());
    }
    public static String getScreenShotFileName(String testName){
        return testName+"_"+getTimeStampWithFormat()+"_"+Config.getProperty("file.extension");
    }
    public static String captureScreenshot(String testName, WebDriver driver,boolean pass) throws IOException {
        if (Config.getProperty("screenshot.type").equalsIgnoreCase("all")){
            createDirs(Paths.get(Config.getProperty("pass.dir")));
            createDirs(Paths.get(Config.getProperty("fail.dir")));

        }if (Config.getProperty("screenshot.type").equalsIgnoreCase("pass")){
            createDirs(Paths.get(Config.getProperty("pass.dir")));
        }if (Config.getProperty("screenshot.type").equalsIgnoreCase("fail")){
            createDirs(Paths.get(Config.getProperty("fail.dir")));
        }
        File screenShot = getScreenShot(driver);
        String fileName = getScreenShotFileName(testName);
        Path path;
        if (pass) {
            path = Paths.get(Config.getProperty("pass.dir"),fileName);

        }else {
            path = Paths.get(Config.getProperty("fail.dir"),fileName);
        }
        Files.copy(screenShot.toPath(),path);
        return fileName;
    }

    private static File getScreenShot(WebDriver driver){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

}
