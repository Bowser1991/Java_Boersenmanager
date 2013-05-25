package testing;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.RunWith;


@RunWith(Suite.class)
   @SuiteClasses({
       CashAccountTest.class,
       ShareDepositTest.class,
       ShareItemTest.class,
       AccountManagerImplTest.class,
       PlayerTest.class,
       ComperatorTest.class
   })
public class AllTests {

   
   
   

}
