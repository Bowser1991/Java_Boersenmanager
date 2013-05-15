/**
 * 
 */
package proxy;

import innerimpl.AccountManager;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * @author daniel und manuel
 *
 */
public class AccountManagerHandler implements InvocationHandler{
    private final static Logger logger = Logger.getLogger(AccountManagerHandler.class.getName());
    private AccountManager target;
    
    public AccountManagerHandler(AccountManager target){
        try {
            logger.addHandler(new java.util.logging.FileHandler());
//            logger.addHandler(new java.util.logging.ConsoleHandler());
        } catch (SecurityException e) {
            logger.warning(e.toString());
        } catch (IOException e) {
            logger.warning(e.toString());
        }
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable
    {
        
        Object result = null;
		try {
			result = method.invoke(target, args);
			logger.info(method.getName());
		} catch (IllegalAccessException ex) {
			String output = "";
			for (int i = 0; i < args.length; i++) {
				output = args[i].getClass().getName();
			}
			logger.warning(ex.toString() + " In Method: " + method.getName()
					+ " With Parameter: " + output);
		} catch (InvocationTargetException ex) {
			String output = "";
			for (int i = 0; i < args.length; i++) {
				output = args[i].getClass().getName();
			}
			logger.warning(ex.toString() + " In Method: " + method.getName()
					+ " With Parameter: " + output + "target " + target.toString());
		}
		return result;
	}

}
