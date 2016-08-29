/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bankemployapplication;

/**
 *
 * @author yuantu
 */
import java.util.Date;

public class Transaction {
    //check whether the transaction was a deposit or a withdrawal
    
    //what other account number was involved, or if cash waws involved
    
    //time and date of the transcation : import java.util.Date
    
    // the amount of money involved
  
 
    private boolean isDeposit;
    private Money amount;
    private Date newDate;
    private Money balance;
    public boolean insufficient=false;
    private int otherAccountNumber=0;
    
    public Transaction(boolean isDeposit,Money amount,Date newDate){
    this.isDeposit=isDeposit;
    this.amount=amount;
    this.newDate=newDate;
    
    }
    
    public Transaction(boolean isDeposit,int otherAccountNumber,Money amount,Date newDate){
    this.isDeposit=isDeposit;
    this.amount=amount;
    this.newDate=newDate;
    this.otherAccountNumber=otherAccountNumber;
    
    }
    
    public boolean getIsDeposit(){
        //System.out.println(isDeposit);
        //System.out.println("amount: "+ amount);
        return this.isDeposit;
    }
    public Money getAmount(){
        return this.amount;
    }
    public Date getDate(){
        return this.newDate;
    }
    public int getOtherAccountNumber(){
        return this.otherAccountNumber;
    }
    
    
    public String toString(){
        if (getIsDeposit()){
            return "Deposit: "+getAmount().toString()+"on"+getDate();
        }
    
            return "Withdrawal: "+getAmount().toString()+"on"+getDate();
        
    }
    
    
    
}
