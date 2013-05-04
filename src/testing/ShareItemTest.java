package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Exception.ShareException;
import asset.ShareItem;

public class ShareItemTest {
    
    private final ShareItem item1 = new ShareItem("BMW");
    private final ShareItem item2 = new ShareItem("Opel");
    private final ShareItem item3 = new ShareItem("Mercedes");
    private final long price = 100;
    private final int amountofshares = 10;
    private final int sellamount = 12;
    private final int sellamount2 = 5;
    private final long purchasevalue = 200;

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
        assertTrue(item1.getPurchasValue()==0);
        assertFalse(item1.getPurchasValue()==1);
    }
    @Test
    public void testSetPurchaseValue(){
        item1.setPurchaseValue(price);
        assertTrue(item1.getPurchasValue()==price);
        assertFalse(item2.getPurchasValue()==price);
    }
    @Test
    public void testGetNumberOfShares(){
        assertTrue(item2.getNumberOfShares()==0);
        assertFalse(item2.getNumberOfShares()==1);
    }
    @Test
    public void testBuyShare(){
        item2.buyShare(amountofshares, purchasevalue);
        assertTrue(item2.getPurchasValue()==purchasevalue);
        assertTrue(item2.getNumberOfShares()==amountofshares);
        assertFalse(item2.getPurchasValue()==0);
        assertFalse(item2.getNumberOfShares()==0);
    }
    @Test(expected=ShareException.class)
    public void testSellShare() throws ShareException{
        item2.sellShare(sellamount, purchasevalue);
        item2.sellShare(sellamount2, purchasevalue);
        assertTrue(item2.getNumberOfShares()==amountofshares-sellamount2);
        assertTrue(item2.getPurchasValue()==0);
        assertFalse(item2.getNumberOfShares()==amountofshares);
        assertFalse(item2.getPurchasValue()==purchasevalue);
    }

}
