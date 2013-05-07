package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Exception.ShareException;
import asset.ShareItem;

public class ShareItemTest{
    
    private final ShareItem item1 = new ShareItem("BMW");    
    private final long price = 100;
    private final int amountofshares = 10;
    private final int sellamount = 12;
    private final int sellamount2 = 5;
    private final long purchasevalue = 200;
    private final ShareItem item2 = new ShareItem("Opel", amountofshares, purchasevalue);
    
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
    public void TestGetPurchaseValue(){
        assertTrue("Der Wert muss 0 seein", item1.getPurchasValue()==0);
        assertFalse(item1.getPurchasValue()==1);
    }
    @Test
    public void testSetPurchaseValue(){
        item1.setPurchaseValue(price);
        assertTrue("Der Wert muss price = 100 sein", item1.getPurchasValue()==price);
        assertFalse(item2.getPurchasValue()==price);
    }
    @Test
    public void testGetNumberOfShares(){
        assertTrue("Die Anzahl der Aktien muss 0 sein", item1.getNumberOfShares()==0);
        assertFalse(item2.getNumberOfShares()==1);
    }
    @Test
    public void testBuyShare(){
        item1.buyShare(amountofshares, purchasevalue);
        assertTrue("Der Wert muss purchasevalue = 200 sein", item1.getPurchasValue()==purchasevalue);
        assertTrue("Die Anzahl der Aktien muss amountofshares = 10 sein", item1.getNumberOfShares()==amountofshares);
        assertFalse(item1.getPurchasValue()==0);
        assertFalse(item1.getNumberOfShares()==0);
    }
    @Test
    public void testSellShare() throws ShareException{
        item2.sellShare(sellamount2, purchasevalue);
        assertTrue(item2.getNumberOfShares()==amountofshares-sellamount2);
        assertTrue(item2.getPurchasValue()==0);
        assertFalse(item2.getNumberOfShares()==amountofshares);
        assertFalse(item2.getPurchasValue()==purchasevalue);
    }
    @Test(expected=ShareException.class)
    public void testSellShareException() throws ShareException{
        item2.sellShare(sellamount, purchasevalue);
    }

}
