package enums;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
    private static final String BUNDLE_NAME = "properties.Boundle_de"; //$NON-NLS-1$

    private static ResourceBundle resourceBoundle = ResourceBundle
            .getBundle(BUNDLE_NAME);

    private Messages() {
    }
    
    public static void setresourceBoundle(String boundleName){
        if(boundleName.equals("en")){
            resourceBoundle = ResourceBundle.getBundle("properties.Boundle", Locale.ENGLISH);
            Locale.setDefault(Locale.ENGLISH);
        } else {
            resourceBoundle = ResourceBundle.getBundle("properties.Boundle", Locale.GERMANY);
            Locale.setDefault(Locale.GERMANY);
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
