/**
 * 
 */
package proxy;

import innerimpl.AccountManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author Manu2
 *
 */
public class AccountManagerHandler implements InvocationHandler{
    private Logger logger = Logger.getLogger("AccountManagerLogger");
    private AccountManager target;
    
    public AccountManagerHandler(AccountManager target){
        try {
            FileInputStream configFile = new FileInputStream("logging.properties");
            LogManager.getLogManager().readConfiguration(configFile);
            logger.addHandler(new java.util.logging.FileHandler());
//            logger.addHandler(new java.util.logging.ConsoleHandler());
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable
    {
        
        Object result = null;
        try  {
            result = method.invoke(target, args);
            logger.info(method.getName());
        } catch(IllegalAccessException ex)  { 
            logger.warning(ex.toString());
        } catch(InvocationTargetException ex)  {
            logger.warning(ex.toString());
        }
        return result;
    }

}
