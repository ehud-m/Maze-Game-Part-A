package Server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Configurations {

    private OutputStream output;
    private InputStream input;
    private static Configurations config = null;


    private Configurations(){}


    public static Configurations getConfigInstance() {
        if (config == null)
            config = new Configurations();
        return config;


    }

    public void writeProp(Properties p){
        try{
            output = new FileOutputStream("C:\\Users\\Owner\\IdeaProjects\\ATP-Project-PartA\\resources\\config.properties");
            p.store(output,null);
        }
        catch (Exception e){
            e.getCause();
        }

    }

    public Properties loadProp(){
        try{
            input = new FileInputStream("C:\\Users\\Owner\\IdeaProjects\\ATP-Project-PartA\\resources\\config.properties");
            Properties p = new Properties();
            p.load(input);
            return p;
        }
        catch (Exception e){
            e.getCause();
        }
        return null;
    }

    public void print(){
        System.out.println(loadProp());
    }


}