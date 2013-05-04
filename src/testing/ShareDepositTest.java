package testing;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asset.Share;
import asset.ShareDeposit;

public class ShareDepositTest {
    private final int amount = 10;
    private final long actualshareprice = 300;
    private final ShareDeposit deposit1 = new ShareDeposit("Manu");
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
        Share share = EasyMock.
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testBuyShare()
    {
        deposit1.buyShare(share1, amount);
    }

}
