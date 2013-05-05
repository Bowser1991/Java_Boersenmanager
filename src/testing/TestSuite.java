package testing;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.RunWith;

import org.junit.AfterClass;
import org.junit.Test;

@RunWith(Suite.class)
   @Suite.SuiteClasses({
       CashAccountTest.class,
       ShareDepositTest.class,
       ShareItemTest.class       
   })
public class TestSuite {

   
   
   

}
