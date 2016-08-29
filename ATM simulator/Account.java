/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bankemployapplication;

/**
 *
 * @author yuantu
 */
public class Account {
    // static variables:
    
    // static int variable: a unique account number for each account
    private final static int baseAccountNumber=100000000;
    private final int accountNumber;
    private int transactionNumber=0;
    
    // static array of Transactions, either be withdrawals or deposits
    Transaction[] transcation=new Transaction[5];
    
    private Money balance;
    
    //constructor
    public Account(){
        
        accountNumber=BankEmployApplication.numberOfAccounts+baseAccountNumber;
        BankEmployApplication.numberOfAccounts+=1;
        this.balance= new Money(0,0);
        System.out.println("AccountNumber:" + accountNumber);
    }
    
    
    
    //instance methods:
    //getAvailableFunds();
    //addTransaction
    //getTransactionHistory()
    //getAccountNumber()
   
    //set a new balance
    public void setBalance(Money balance){
        this.balance=balance;
    }
    
    //withdraw money from an account
    public void withdrawals(Money amount){
        if(balance.compareTo(amount)!=-1){
            balance=balance.subtract(amount);
        }
        else{
            System.out.println("Transaction cancelled due to insufficient Funds.");
            transcation[transactionNumber].insufficient=true;
        }
        
    }
    
    //deposit money into an account
    public void deposit(Money amount){
        balance=balance.add(amount);
    }
    
    //get Account No.
    public int getAccountNumber(){
        return accountNumber;
}
    
    
    //check the balance (Money type) of an account
    public Money getBalance(){  
        return balance;
    }
    
    //check the balance(String type) of an account
    public String getAvailableFunds(){
        return "Current balance: "+balance.toString();
    }
    
    //get hte number of accounts have been created in this bank
    public int numberOfAccounts(){
        return BankEmployApplication.numberOfAccounts;
    }
    

   //when transcation occurs, add a new transaction to Array transcation 
    public void addTransaction(Transaction trans){
        //System.out.println("transactionNumber: "+transactionNumber+"\nAccount Number: "+getAccountNumber());
        transcation[transactionNumber]=trans;
        if (transcation[transactionNumber].getIsDeposit()){
            this.deposit(this.transcation[transactionNumber].getAmount());
        }
        else{
            this.withdrawals(this.transcation[transactionNumber].getAmount());
        }
        transactionNumber+=1;
        //extend array:
        if (transactionNumber*2>=transcation.length){
            Transaction[] temp=transcation;
            transcation=new Transaction[transactionNumber*2];
            for (int i=0;i<temp.length;i++){
                transcation[i]=temp[i];
            }
        }
    }
   
    //list all deposits and withdrawals for an account
    public String getTransactionHistory(){
        String getTransactionHistory="";
        for (int i=0;i<transactionNumber;i++){
            Transaction temp=transcation[i];
            if (temp.getOtherAccountNumber()==0){
                getTransactionHistory=getTransactionHistory+
                        "Transaction "+(i+1)+":"+(temp.getIsDeposit()?" Deposit: ":" Withdraw: ")+
                        temp.getAmount().toString()+(temp.getIsDeposit()?" to ":" from ") +"ACCOUNT#"+
                        getAccountNumber() +" on "+temp.getDate().toString()+(!temp.insufficient?"":" (Transaction cancelled due to insufficient Funds)")+"\n";
            }
            else{
                        getTransactionHistory=getTransactionHistory+
                        "Transaction "+(i+1)+":"+(temp.getIsDeposit()?" Deposit: "+temp.getAmount().toString()+" to":" Withdraw: "+
                        temp.getAmount().toString()+" from")+" ACCOUNT#" +
                        getAccountNumber()+(temp.getIsDeposit()?" from ":" to ") +"ACCOUNT#"+
                        temp.getOtherAccountNumber() +" on "+temp.getDate().toString()+
                        (!temp.insufficient?"":" (Transaction cancelled due to insufficient Funds.)")+"\n";
                        
            }
            
        }
        
        return getTransactionHistory;
        }
       
    
   @Override public String toString(){
        return "current balance: "+balance;
        
    }
    
}
