package testing;

import static org.junit.Assert.*;
import innerimpl.AccountManager;
import innerimpl.AccountManagerImpl;
import history.BuySellHistory;
import history.CommandEntity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import commperator.ComparatorAllShareName;
import commperator.ComparatorMethodName;
import commperator.ComparatorTime;

import priceprovider.RandomStockPriceProvider;
import priceprovider.StockPriceProvider;

import asset.Player;
import asset.Share;

public class ComperatorTest {
	static final StockPriceProvider provider = new RandomStockPriceProvider();
	static final AccountManager account = new AccountManagerImpl(provider);
  
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		account.addPlayer(new Player("daniel",1000000000000000l));	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTimeTrue() {  
	    try {
	     	for (int i = 0; i < 1000; i++) {
	    		account.buyShare("daniel", "BMW", 2);
	    		account.sellShare("daniel", "BMW", 2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    BuySellHistory history =  account.getAllPlayer()[0].getBuySellHistory();
	    try {
			history.sort(new ComparatorTime());
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    CommandEntity[] entity = history.getFirstLast();
	    assertTrue("ist richtig nach Zeit sortiert", entity[0].getCalendar().getTime().getTime()<entity[1].getCalendar().getTime().getTime());
	}
	@Test
	public void testShareName() {  
	    try {
	     	for (int i = 0; i < 2; i++) {
	    		account.buyShare("daniel", "BMW", 2);
	    		account.buyShare("daniel", "Opel", 2);
	    		account.buyShare("daniel", "Merzedes", 2);
	    		account.sellShare("daniel", "BMW", 2);
	    		account.sellShare("daniel", "Opel", 2);
	    		account.sellShare("daniel", "Merzedes", 2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    BuySellHistory history =  account.getAllPlayer()[0].getBuySellHistory();
	    try {
			history.sort(new ComparatorAllShareName());
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    CommandEntity[] entity = history.getFirstLast();
	    System.out.println(history.toString());
	    assertFalse("ist falsch nach Aktienname sortiert", entity[0].getShare().name.equals(entity[1].getShare().name));
	}
	
	@Test
	public void testShareMethode() {  
	    try {
	     	for (int i = 0; i < 2; i++) {
	    		account.buyShare("daniel", "BMW", 2);
	    		account.buyShare("daniel", "Opel", 2);
	    		account.buyShare("daniel", "Merzedes", 2);
	    		account.sellShare("daniel", "BMW", 2);
	    		account.sellShare("daniel", "Opel", 2);
	    		account.sellShare("daniel", "Merzedes", 2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    BuySellHistory history =  account.getAllPlayer()[0].getBuySellHistory();
	    try {
			history.sort(new ComparatorMethodName());
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    CommandEntity[] entity = history.getFirstLast();
	    System.out.print(history.toString());
	    assertFalse("ist falsch nach Aktienname sortiert", entity[0].getMethodName().equals(entity[1].getMethodName()));
	}
}
