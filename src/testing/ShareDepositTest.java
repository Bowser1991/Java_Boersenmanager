package testing;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Exception.ShareException;
import asset.Share;
import asset.ShareDeposit;
import asset.ShareItem;

public class ShareDepositTest{
    
    

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
    public void testBuyShare()
    {
    	int amount = 10;
        long actualshareprice = 300;
        ShareDeposit deposit1 = new ShareDeposit("Manu");
        Share share1 = new Share("BMW", actualshareprice);
        ShareItem[] item1 = new ShareItem[2];
        ShareItem newShareItem = new ShareItem("BMW");
        
        
        newShareItem.setPurchaseValue(amount*actualshareprice);
        newShareItem.setNumberOfShares(amount);
        item1[0] = newShareItem;
        deposit1.buyShare(share1, amount);
       
    }
    @Test
    public void testSellShare() throws ShareException{
    	int amount = 10;
        long actualshareprice = 300;
        ShareDeposit deposit1 = new ShareDeposit("Manu");
        Share share1 = new Share("BMW", actualshareprice);
        ShareItem[] item1 = new ShareItem[2];

        deposit1.buyShare(share1, amount);
        deposit1.sellShare(share1, amount-1);
        item1[0] = new ShareItem("BMW");
        item1[0].setNumberOfShares(amount-9);
        item1[0].setPurchaseValue(actualshareprice);
        assertTrue(deposit1.equals(item1));           
    }

}
