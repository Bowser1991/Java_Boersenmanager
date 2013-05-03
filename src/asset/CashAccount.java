package asset;

import Exception.AccountException;

public class CashAccount extends Asset {
    
    private long accountStatus;
    
    public CashAccount(String name,long firstaccountstatus) {
        super(name);
        accountStatus = firstaccountstatus;
    }

    public void deposit(Share newshare) {
        accountStatus = accountStatus - newshare.getActualSharePrice();
    }   
    
    public String toString(){
        return " Accountstatus: "+Long.toString(accountStatus);
    }
    
    public void withdraw(Share newshare) {
        accountStatus = accountStatus + newshare.getActualSharePrice();
    }
    
    public long getAccountStatus(){
        return accountStatus;
    }
    
    public void setAccountStatus(long newaccount) throws AccountException{
        if(newaccount >= 0){
    	this.accountStatus = newaccount;
        }else{
        	throw new AccountException("invalid account action");
        }
    }

    public long getvalue() {
        return accountStatus;
    }
}
