package utils;

import constants.FrameworkConstants;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Objects;
import exceptions.PropertyFileException;
import enums.ConfigProperties;


public final class PropertyUtils {
    private PropertyUtils(){
    }
    private static Properties property= new Properties();
    private static Map<String, String> CONFIGMAP= new HashMap<>();
    private static String environment = null;
    private static String browserType = null;

    /**
     * This method is used to load the properties from config.properties file
     * @return it returns Properties prop object
     */


    public static void setBrowserType(String browser) {
        browserType = browser;
    }
    public static String getBrowserType() {
        if (browserType != null)
            return browserType;
        else
            throw new RuntimeException("browser not specified in the testng.xml");
    }

    public static void setEnv(String env) {
        environment = env;
    }
    public static String getEnv() {
        if (environment != null)
            return environment;
        else
            throw new RuntimeException("env not specified in the testng.xml");
    }

//    public static void initializeProp() {
//
//        prop = new Properties();
//        try {
//            FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
//            prop.load(ip);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    static {
        //try with resources
        try {
            FileInputStream file = new FileInputStream(FrameworkConstants.getConfigFilePath());

            property.load(file);

            for (Map.Entry<Object, Object> entry : property.entrySet()) {
                CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim()); //remove the trailing and leading spaces
            }
            //property.entrySet().forEach(entry ->CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        } catch (Exception e ) {
            //throw new InvalidPathForPropertyFileException("Please check the Path of the config.properties file");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static String get(ConfigProperties key) {
        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
            throw new PropertyFileException("Property name "+ key +" is not found. Please check config.properties");
        }
        return CONFIGMAP.get(key.name().toLowerCase());
    }

    //Hashtable -- little slow, thread safe
    public static String getValue(String key){

        if (Objects.isNull(property.getProperty(key)) || Objects.isNull(key)) {
            throw new PropertyFileException("Property name "+ key +" is not fournd. Please check config.properties");
        }
        return property.getProperty(key);
    }


}
