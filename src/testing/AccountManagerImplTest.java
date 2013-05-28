/**
 * 
 */
package testing;

import static org.junit.Assert.*;
import innerimpl.AccountManagerImpl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asset.Player;
import asset.Share;

import Exception.AccountException;
import Exception.NotAddablePlayerException;
import Exception.ShareException;

import priceprovider.ConstStockPriceProvider;
import priceprovider.StockPriceProvider;

/**
 * @author Manu2
 *
 */
public class AccountManagerImplTest {
    
    private final Share[] sharearray = {new Share("BMW", 200), new Share("Opel", 250), new Share("Mercedes", 300)};
    private final String playername = "manu";
    private final long accountworth = 1000000;   
    private final String sharename = "BMW";
    private final int amount = 10;
    private final int amount2 = 12;
    private final Player player = new Player(playername, accountworth);

    private final StockPriceProvider provider = new ConstStockPriceProvider();
    private final AccountManagerImpl manager = new AccountManagerImpl(provider);
    

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        
        
    }
    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
    }
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        manager.addPlayer(player);
    }
    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {
    }
    /**
     * 
     * @throws AccountException
     */
    @Test(expected = NotAddablePlayerException.class)
    public void testAddPLayer() throws AccountException
    {
        manager.addPlayer(player);
        assertTrue("überprüft ob der Playername stimmt", manager.getAllPlayer()[0].name.equals(playername));
        assertTrue("überprüft ob der accountworth richtig gesetzt ist", manager.getAllPlayer()[0].getCashAccount().getvalue() ==accountworth);
    }
    /**
     * 
     * @throws ShareException
     * @throws AccountException
     */
    @Test(expected =  ShareException.class)
    public void testBuyShare() throws ShareException, AccountException{
        manager.buyShare(playername, sharename, 1000000); //löst Exception aus
        manager.buyShare(playername, sharename, amount);
        assertTrue("Üerprüft ob der Name des ShareItems richtig ist", manager.getAllPlayer()[0].getShareDeposit().getAllShareItems()[0].name.equals(sharename));
        assertTrue("Üerprüft ob die Anzahl der gekauften Aktien korrekt ist amount = 10", manager.getAllPlayer()[0].getShareDeposit().getAllShareItems()[0].getNumberOfShares()==amount);
        assertTrue("Üerprüft ob der Wert im ShareItem richtig berechnet wurde 10*200", manager.getAllPlayer()[0].getShareDeposit().getAllShareItems()[0].getPurchasValue()==amount*200);

    }
    /**
     * 
     * @throws ShareException
     * @throws AccountException
     */
    @Test(expected = ShareException.class)
    public void testSellShare() throws ShareException, AccountException{
        manager.buyShare(playername, sharename, amount);
        manager.sellShare(playername, sharename, amount2); //löst Exception aus
        manager.sellShare(playername, sharename, amount);
        assertTrue(" ob die Anzahl der gekauften Aktien korrekt ist 10 - 10 = 0", manager.getAllPlayer()[0].getShareDeposit().getAllShareItems()[0].getNumberOfShares()==0);
        assertTrue(" 10*200 - 10*200 = 0", manager.getAllPlayer()[0].getShareDeposit().getAllShareItems()[0].getPurchasValue()==0);

        assertTrue("überprüft ob der Wert im CashAccount richtig berechnet wurde 1000000", manager.getAllPlayer()[0].getCashAccount().getvalue()==accountworth);
    }
    @Test
    public void testGetAllAssetWorth(){
        assertTrue(manager.getAllAssetworth(playername)==accountworth);
    }
    @Test
    public void testDiverShareSell() throws ShareException, AccountException{
        manager.buyShare(playername, sharename, amount);
        assertTrue("überprüft ob der Durchscnittswert der gekauften Aktien >= dem aktuellen Aktienwert ist", manager.diverShareSell(sharename, playername));      
    }
}
