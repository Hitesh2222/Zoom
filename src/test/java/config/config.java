package config;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class config {
    private static Logger logger = LogManager.getLogger(config.class);
    private static final String CONFIG_FILE = "framework.properties";
    private static config obj;
    private Properties properties;
    private config(){
        Path path = Paths.get("src","test","resources",CONFIG_FILE);
        if (Files.exists(path)) {
            logger.info("Reading file {}", path.getFileName());
        }
        else {
            logger.fatal("file {} does not exist",path.getFileName());
        }

        try {
            InputStream stream = new FileInputStream(path.toFile());
            properties = new Properties();
            properties.load(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static synchronized String getProperty(String key){
        if(key == null){
            throw new RuntimeException("Invalid Key NULL passed !");
        }
        if(obj==null){
            obj = new config();
        }
        return obj.properties.getProperty(key);
    }
}
