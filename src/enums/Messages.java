package enums;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.tools.JavaFileManager.Location;

public class Messages {
    private static final String BUNDLE_NAME = "enums.Boundle_de"; //$NON-NLS-1$

    private static ResourceBundle resourceBoundle = ResourceBundle
            .getBundle(BUNDLE_NAME);

    private Messages() {
    }
    
    public static void setresourceBoundle(String boundleName){
        if(boundleName.equals("en")){
            resourceBoundle = ResourceBundle.getBundle("enums.Boundle", Locale.ENGLISH);
        } else {
            resourceBoundle = ResourceBundle.getBundle("enums.Boundle", Locale.GERMAN);
        }
    }

    public static String getString(String key)
    {
        try {
            return resourceBoundle.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}