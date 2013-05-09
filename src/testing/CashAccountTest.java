package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Exception.AccountException;
import asset.CashAccount;
import asset.Share;

public class CashAccountTest{
    private final long accountstatus1 = 1000000;
    private final long accountstatus2 = -10;
    private final long actualshareprice = 300; 
    private final CashAccount account1= new CashAccount("Manu", accountstatus1);
    private final CashAccount account2 = new CashAccount("Daniel", 0);
    private final Share share1 = new Share("BMW", actualshareprice);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }

    @Before
    public void setUp() throws Exception
    {
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testGetAccountstatus()
    {
        assertTrue(account1.getvalue()==accountstatus1);
        assertFalse(account1.getvalue()==0);
    }
    @Test(expected=AccountException.class)
    public void testSetAccountStatus() throws AccountException {
        account2.setAccountStatus(accountstatus2);
        
    }
    @Test
    public void testSetAccountStatus2() throws AccountException{
        account2.setAccountStatus(accountstatus1);
        assertTrue(account2.getvalue()==accountstatus1);
        assertFalse(account2.getvalue()==0);
    }
    @Test
    public void testDeposit(){
        account1.deposit(share1);
        assertTrue(account1.getvalue()==accountstatus1-actualshareprice);
        assertFalse(account1.getvalue()==accountstatus1);
    }
    @Test
    public void testWithdraw(){
        account1.withdraw(share1);
        assertTrue(account1.getvalue()==accountstatus1+actualshareprice);
        assertFalse(account1.getvalue()==accountstatus1);
    }

}
