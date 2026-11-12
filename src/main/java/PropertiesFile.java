import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesFile {
        //Create a object for class properties
        static Properties prop = new Properties();
        //defining the project path
        static String projectPath = System.getProperty("user.dir");
        public static void main(String[] args) {
            getProperties();
            setProperties();
            getProperties();
        }
        public static void getProperties() {
            try {
//create a object for class InputStream
                InputStream input = new FileInputStream(projectPath + "/src/test/abc");
                //Load properties file
                prop.load(input);
                //get values from properties file
                String browser = prop.getProperty("browser");
                System.out.println(browser);
            } catch (Exception exp) {
                System.out.println(exp.getMessage());
                System.out.println(exp.getCause());
                exp.printStackTrace();
            }
        }
        public static void setProperties() {
            try {
//create a object for class OuputStream
                OutputStream output = new FileOutputStream(projectPath + "/src/test/abc");
                //Load properties file and set firefox
                prop.setProperty("browser", "chrome");
                //store values i properties file
                prop.store(output, "setting chrome");
            } catch (Exception exp) {
                System.out.println(exp.getMessage());
                System.out.println(exp.getCause());
                exp.printStackTrace();
            }
        }
    }
