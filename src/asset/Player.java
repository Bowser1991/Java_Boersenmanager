package asset;
import Exception.AccountException;
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
    
    public void buyShare(Share share, int amount) throws AccountException{
        deposit.buyShare(share, amount);
        account.setAccountStatus((account.getAccountStatus()-(share.getActualSharePrice()*amount)));
    }
    
    public void sellShare(Share share, int amount) throws ShareException, AccountException{
        deposit.sellShare(share, amount);
        account.setAccountStatus((account.getAccountStatus()+(share.getActualSharePrice()*amount)));
    }
    
    public void setAccountWorth(long newworth) throws AccountException{
    	this.account.setAccountStatus(newworth);
    }
    
    public ShareDeposit getShareDeposit(){
        return deposit;
    }
    public boolean equals(Player player){
        boolean b = true;
        if (!this.name.equals(player.name)){
            b = false;
            return b;
        }
        if (!this.deposit.equals(player.getShareDeposit())){
            b = false;
            return b;
        }
        if (!this.account.equals(player.getCashAccount()))
            b = false;
        return b;
    }
    
    
}
