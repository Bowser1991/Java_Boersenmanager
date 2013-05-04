package testing;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Exception.ShareException;
import asset.Share;
import asset.ShareDeposit;
import asset.ShareItem;

public class ShareDepositTest {
    private final int amount = 10;
    private final long actualshareprice = 300;
    private final ShareDeposit deposit1 = new ShareDeposit("Manu");
    private final Share share1 = new Share("BMW", actualshareprice);
    private final ShareItem[] item1 = new ShareItem[2];
    private final ShareItem newShareItem = new ShareItem("BMW");
    

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
//        ShareItem[] item = createMock(ShareItem[].class);
//        item = 
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testBuyShare()
    {
        newShareItem.setPurchaseValue(amount*actualshareprice);
        newShareItem.setNumberOfShares(amount);
        item1[0] = newShareItem;
        deposit1.buyShare(share1, amount);
        assertTrue(deposit1.equals(item1));       
    }
    @Test
    public void testSellShare() throws ShareException{
        deposit1.buyShare(share1, amount);
        deposit1.sellShare(share1, amount-1);
        item1[0] = new ShareItem("BMW");
        item1[0].setNumberOfShares(amount-9);
        item1[0].setPurchaseValue(actualshareprice);
        assertTrue(deposit1.equals(item1));           
    }

}
