import asset.Share;

public class StockGameLauncher {
    public static void main(String[] args) throws Exception
    {
        
        process();

    }
    private static Share [] shareArray (){
        Share share1 = new Share("Merzedes", 14000l);   
        Share share2 = new Share("BMW", 19436l);
        Share share3 = new Share("Opel", 28900l);
        Share[] sharearray1 = {share1, share2, share3};
        return sharearray1;
    }
    private static String readInString () throws Exception{
        String s = "";
        char readInChar = (char)System.in.read();  
        if (readInChar == '\n' || readInChar == '\r'){
            readInChar = (char)System.in.read(); 
        }
        while (readInChar != '\n' && readInChar != '\r'){
            s += readInChar;
            readInChar = (char)System.in.read();
        }
        return s;       
    }
    private static int readInInt () throws Exception {
        int i = 0;
        int readInInt = (int)System.in.read();
        if (readInInt == '\n' || readInInt == '\r'){
            readInInt = (int)System.in.read(); 
        }
        while (readInInt != '\n' && readInInt != '\r'){
            i = i*10 + (readInInt-48);
            readInInt = (int)System.in.read();
        }
        return i;
    }
    private static long readInLong () throws Exception {
        long i = 0;
        long readInLong = (long)System.in.read();
        if (readInLong == '\n' || readInLong == '\r'){
            readInLong = (long)System.in.read(); 
        }
        while (readInLong != '\n' && readInLong != '\r'){
            i = i*10 + (readInLong-48);
            readInLong = (long)System.in.read();
        }
        return i;
    }
    public static void process() throws Exception{
        String eingabe = "";
        AccountManagerImpl manager = new AccountManagerImpl(shareArray());
        AccountManagerImpl newmanager = new AccountManagerImpl(shareArray());
//        StockPriceInfo info = new RandomStockPriceProvider(shareArray());
//        StockPriceViewer newviewer = new StockPriceViewer(info, newmanager);
//        newviewer.start();
        char eingabeChar;      
        while(true){    
            eingabeChar = (char)System.in.read();
            while (eingabeChar != '\n'&& eingabeChar != '\r'){
                eingabe = eingabe + eingabeChar;
                eingabeChar = (char)System.in.read();
                
            }
            String playername;
            String sharename;
            int amount;
            long accountworth;
            switch (eingabe) {
            case "buy":
                System.out.print("Player name: ");
                playername = readInString();
                System.out.print("Share name: ");
                sharename = readInString();
                System.out.print("Amount: ");
                amount = readInInt();
                manager.buyShare(playername, sharename, amount); 
                eingabe = "";
                break;
            case "sell":
                System.out.print("Player name: ");
                playername = readInString();
                System.out.print("Share name: ");
                sharename = readInString();
                System.out.print("Amount: ");
                amount = readInInt();
                manager.sellShare(playername, sharename, amount);
                eingabe = "";
                break;
            case "newplayer":
                System.out.print("Player name: ");
                playername = readInString();
                System.out.print("Account worth: ");
                accountworth = readInLong();
                manager.addPlayer(playername, accountworth);
                eingabe = "";
                break;
            case "out":
                System.out.println(manager.getPlayer());
                eingabe = "";
                break;
            case "q":
                System.out.println("Stop");
                System.exit(0);
            case "":
                break;
            default:
                System.out.println("False input!");
                eingabe = "";
            }            
        }
    }  
}
