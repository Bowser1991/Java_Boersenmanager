package asset;
import Exception.ShareException;


public class Player {
    
    public final String name;
    private CashAccount account;
    private ShareDeposit deposit;
    
    public Player(String name){
        this.name = name;
        account = new CashAccount(name, 0l);
        deposit = new ShareDeposit(name);
    }
    
    public Player(String name, long cashaccountstatus){
        this.name = name;
        this.account = new CashAccount(name, cashaccountstatus);
        deposit = new ShareDeposit(name);
    }

    public CashAccount getCashAccount(){
        return account;
    }
    
    public void buyShare(Share share, int amount){
        deposit.buyShare(share, amount);
        account.setAccountStatus((account.getAccountStatus()-(share.getActualSharePrice()*amount)));
    }
    
    public void sellShare(Share share, int amount) throws ShareException{
        deposit.sellShare(share, amount);
        account.setAccountStatus((account.getAccountStatus()-(share.getActualSharePrice()*amount)));
    }
    
    public void setAccountWorth(long newworth){
    	this.account.setAccountStatus(newworth);
    }
    
    public ShareDeposit getShareDeposit(){
        return deposit;
    }
    
    
}
