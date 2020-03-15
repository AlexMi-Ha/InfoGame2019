import java.util.Properties;
import java.io.FileOutputStream;
import java.io.FileInputStream;

//CURRENTLY UNUSED
//Save / Load data of the current game

public class DataManager {
    
    private static Properties prop = new Properties();  //Propertie file Loader/Storer var
    private static String path = "game.data";           //default path to the save file
    
    //store data to an other fileLocation
    public static void store(String data, String key, String fileLocation) {
        try {
            prop.setProperty(key, data);
            prop.store(new FileOutputStream(fileLocation), null);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //store data to the "path" file
    public static void store(String data, String key) {
        try {
            prop.setProperty(key, data);
            prop.store(new FileOutputStream(path), null);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //load data from an other fileLocation
    public static String getData(String key, String fileLocation) {
        try{
            prop.load(new FileInputStream(fileLocation));
            return prop.getProperty(key);
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    //load data from the "path" file
    public static String getData(String key) {
        try{
            prop.load(new FileInputStream(path));
            return prop.getProperty(key);
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
}