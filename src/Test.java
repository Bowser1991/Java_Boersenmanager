
import Exception.ShareException;


public class Test {
    public static void main(String[] args) throws Exception {
//        ShareItem item1 = new ShareItem("Merzedes");
//        ShareItem item2 = new ShareItem("BMW");
//        ShareItem item3 = new ShareItem("Opel");

        
        
//        CashAccount account1 = new CashAccount("ersterAccount",1000000000l);
        
        Share share1 = new Share("Merzedes", 14000l);   
        Share share2 = new Share("BMW", 19436l);
        Share share3 = new Share("Opel", 28900l);
        Share[] sharearray1 = {share1, share2, share3};
        
        AccountManagerImpl newmanager = new AccountManagerImpl(sharearray1);
        
        
        ShareDeposit deposit1 = new ShareDeposit("erstesDeposit");
        deposit1.buyShare(share3, 3);
        deposit1.buyShare(share1, 3);
        deposit1.buyShare(share2, 3);
        
//        System.out.println(deposit1.toString());
        newmanager.addPlayer("Daniel",1000000l);
        newmanager.addPlayer("Manuel",1000000l);            

       newmanager.buyShare("Daniel", "Opel", 5);
       
       StockPriceInfo info = new RandomStockPriceProvider(sharearray1);
       StockPriceViewer newviewer = new StockPriceViewer(info, newmanager);
       newviewer.start();
       newmanager.sellShare("Daniel", "Opel", 4);
       System.out.println(newmanager.getPlayer());
        
    }

}

