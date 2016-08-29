/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bankemployapplication;

/**
 *
 * @author yuantu
 */
import java.util.Scanner;
import java.util.Random;
import java.util.Date;
public class BankEmployApplication {
    //private static variables: an array of Accounts; int storing no. of accounts
    public static int numberOfAccounts=0;
    
    static Account[] accounts=new Account[10];
    
          
    
    //static methods:
    
    //create an account
    public static void createAccount(){
        accounts[numberOfAccounts]=new Account();
        //extend array:
        if (numberOfAccounts*2>=accounts.length){
            Account[] temp=accounts;
            accounts=new Account[accounts.length*2];//double the array size
            for (int i=0;i<temp.length;i++){
                //copy acounts to new array.
                accounts[i]=temp[i];
            }
            
            
        }
        
    }
    
    public static int binarySearchAccount(Account[] accounts,int key){
        int min=0;
        int max=numberOfAccounts-1;
        // System.out.println(key);
        
        while (min<=max){
            int mid=(min+max)/2;
            if (accounts[mid].getAccountNumber()==key){
               // System.out.println(mid);
                return mid;
            }
            else if (accounts[mid].getAccountNumber()<key){
                min=mid+1;
            }
            else{
                max=mid-1;
            }
        }
        return -1;
        
    }
    
    
    //text based User Interface:
    public static void createAndStartTextBasedUserInterface(){
        
        
        //System.out.println(numberOfAccounts);
        Scanner keyword=new Scanner(System.in);
        //deposit; withdraw; open account; quit; check balance; transaction history
        int user_botton=0;
        System.out.println();
        boolean check=true;
        do{
            //display menu to user
            System.out.println("1. Open a new bank account");
            System.out.println("2. Deposit to a bank account");
            System.out.println("3. Withdraw to bank account");
            System.out.println("4. Print transaction history");
            System.out.println("5. check balance");
            System.out.println("6. Quit");
            System.out.println();
            
            user_botton=keyword.nextInt();
            switch (user_botton){
                
                case 1:
                    // open a new account
                   
                    createAccount();
                    System.out.println("Enter an opening balance: ");
                    Money newbalance=new Money(keyword.nextInt());                  
                    accounts[numberOfAccounts-1].setBalance(newbalance);
                    System.out.println("Account has been created. Here is your new account number: "+
                            accounts[numberOfAccounts-1].getAccountNumber());
                    System.out.println("Current balance: "+accounts[numberOfAccounts-1].getBalance()+"\n");
                    break;
                case 2:
                    //deposit to bank account
                    System.out.println("Enter your account number: ");
                    int userAccountNumber=keyword.nextInt();
                    //System.out.println(userAccountNumber);
                    int numberInAccounts=binarySearchAccount(accounts,userAccountNumber);
                    if (numberInAccounts!=-1){
                    System.out.println("Enter a depost amount: ");
                    Money deposit=new Money(keyword.nextInt());
                    Transaction transaction=new Transaction(true,deposit,new Date());
                    accounts[numberInAccounts].addTransaction(transaction);
                    System.out.println(accounts[numberInAccounts].toString()+"\n");
                    }
                    else{
                        System.out.println("Invalid account number.");
                    }
                    break;
                case 3:
                   // Withdraw to bank account
                    System.out.println("Enter your account number: ");
                    userAccountNumber=keyword.nextInt();
                    numberInAccounts=binarySearchAccount(accounts,userAccountNumber);
                     if (numberInAccounts!=-1){
                    System.out.println("Enter a withdraw amount: ");
                    Money withdraw=new Money(keyword.nextInt());
                    Transaction transaction=new Transaction(false,withdraw,new Date());
                    accounts[numberInAccounts].addTransaction(transaction);
                    System.out.println(accounts[numberInAccounts].toString()+"\n");
                     }
                     else{
                        System.out.println("Invalid account number.");
                    }
                    break;
                case 4:
                    //Print transaction history
                    System.out.println("Enter your account number: ");
                    userAccountNumber=keyword.nextInt();
                    numberInAccounts=binarySearchAccount(accounts,userAccountNumber);
                    if (numberInAccounts!=-1){
                    System.out.println(accounts[numberInAccounts].getTransactionHistory());
                    System.out.println(accounts[numberInAccounts].toString()+"\n");
                    }
                    else{
                        System.out.println("Invalid account number.");
                    }
                    break;
                    
                    
                case 5:
                    //check balance
                    System.out.println("Enter your account number: ");
                    userAccountNumber=keyword.nextInt();
                    numberInAccounts=binarySearchAccount(accounts,userAccountNumber);
                    if (numberInAccounts!=-1){
                    System.out.println(accounts[numberInAccounts].toString()+"\n");
                    }
                    else{
                         System.out.println("Invalid account number.");
                    }
                    break;
                case 6:
                    //quit 
                    check=false;
                    System.out.println("System stop processing...");
                    System.out.println("Thanks you, see you later.");
                    
            }
            
            
        }while(check);
        System.exit(0);
    }
   
    //total amount of money in all accounts for the bank
    public static String getTotalInAllAccounts(){
        Money totallInAccounts=new Money();
        for (int i =0;i<numberOfAccounts;i++){
            totallInAccounts=totallInAccounts.add(accounts[i].getBalance());
        }
        return "Total "+totallInAccounts.toString();
}
    
    public static void main(String args[]){
        accounts=new Account[5];
        numberOfAccounts=0;
        test(10,10);
        createAndStartTextBasedUserInterface();
        
    }
    //test
    public static void test(int maxAccounts,int maxTransactionsPerAccount){
       Random rg=new Random();
       int numberToGenerate= rg.nextInt(maxAccounts)+1;
       //create a bunch of accounts
       for (int i=0;i<numberToGenerate;i++){
           createAccount();
       }
        System.out.println("numberOfAccounts==numberToGenerate: "+(numberOfAccounts==numberToGenerate));
        System.out.println();
        
        //create a bunch of transactions and add them to random accounts
        for (int i=0;i<numberOfAccounts*maxTransactionsPerAccount;i++){
            int randomAccountsArrayIndex=rg.nextInt(numberOfAccounts);
            Account account=accounts[randomAccountsArrayIndex];
            boolean isDeposit=rg.nextInt(2)==0;
            boolean isCash=rg.nextInt(2)==0;
            
            
            Money amount=new Money(rg.nextInt(10000),rg.nextInt(100));
            
            if (isCash){
                account.addTransaction(new Transaction(isDeposit,amount,new Date()));
                
            }
            else{
                int otherAccountNumber=rg.nextInt(100000000);
                account.addTransaction(new Transaction(isDeposit,otherAccountNumber,amount,new Date()));
                
                        
            }
        }
        System.out.println();
        //print the balance in each account
        for (int i=0;i<numberOfAccounts;i++){
            System.out.println("ACCOUNT#"+accounts[i].getAccountNumber()+":"
                    +accounts[i].getAvailableFunds());
        }
        //print total in bank
       System.out.println("TOTAL IN BANK: "+getTotalInAllAccounts());
       
       System.out.println();
       //show he transaction history of a random account
       int randomAccountsArrayIndex=rg.nextInt(numberOfAccounts);
       Account account=accounts[randomAccountsArrayIndex];
       System.out.println("ACCOUNT#"+account.getAccountNumber());
       System.out.println(account.getTransactionHistory());//this should print out all the transacton for the account
    }
    
    
    
    
    
}

   