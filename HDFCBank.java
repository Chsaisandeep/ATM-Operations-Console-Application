package gqt;
import java.util.HashMap;
import java.util.Scanner;
class ATM {
	private double depositAmount;
	private double withdrawAmount;
	private double balanceAmount;
	
	public ATM ( double depositAmount, double withdrawAmount, double balanceAmount) {
		super();
		this.depositAmount = depositAmount;
		this.withdrawAmount = withdrawAmount;
		this.balanceAmount = balanceAmount;
	}
	
	public ATM() {
		super();
	}
	
	public double getDepositAmount() {
		return depositAmount;
	}
	
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
	
	public double getWidrawAmount() {
		return withdrawAmount;
	}
	
	public void setWithdrawAmount( double withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	
	public double getBalanceAmount() {
		return balanceAmount;
	}
	
	public void setBalanceAmount( double balanceAmount ) {
		this.balanceAmount = balanceAmount;
	}
}

interface AtmOperationInterf {
	void deposit( double depositAmount ) throws Exception;
	void withdraw( double withdrawAmount) throws Exception;
	void viewBalance() throws Exception;
	void viewMiniStatement() throws Exception;
	void exit() throws Exception;
}

class AtmOperationInterfImpl implements AtmOperationInterf {
	ATM atm = new ATM();
	HashMap<Double,String> hm = new HashMap<Double,String>(); //hashmap
	
	public void deposit(double depositAmount) throws Exception {
		if(depositAmount%100==0) {
			Thread.sleep(3000);
			hm.put(depositAmount,": Deposited");
			System.out.println("RS." +depositAmount+ "/- has been deposited successfully");
			Thread.sleep(2000);
			atm.setBalanceAmount(atm.getBalanceAmount()+depositAmount);
			viewBalance();
		}
		else {
			System.out.println("Please enter amount in multiple of 100's ");
		}
	}
	
	public void withdraw( double withdrawAmount) throws Exception{
		if(withdrawAmount%100==0) {
			if(withdrawAmount<=atm.getBalanceAmount()) {
				Thread.sleep(3000);
				hm.put(withdrawAmount,": withdraw");
				System.out.println("Collect the cash RS."+withdrawAmount+"/-");
				atm.setBalanceAmount(atm.getBalanceAmount()-withdrawAmount);
				viewBalance();
			}
			else {
				System.out.println("Insufficient balance");
			}
		}
		else {
			System.out.println("Please enter amount in multiples of 100's");
		}
	}
	
	public void viewBalance() {
		System.out.println("Available balance = "+atm.getBalanceAmount());
	}
	
	public void viewMiniStatement() {
		hm.forEach((i,j) -> System.out.println(i+" "+j));
	}
	
	public void exit() throws Exception{
		Thread.sleep(3000);
		System.out.println("\nCollect your ATM");
		System.out.println("Thanks for using HDFC ATM");
		System.exit(0);
	}
}

public class HDFCBank {

	public static void main(String[] args) throws Exception {
		int accountNumber = 12345;
		int password = 123;
		AtmOperationInterf a = new AtmOperationInterfImpl();
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to HDFC ATM");
		
		//accepting account details
		
		System.out.print("\nEnter account number:");
		int an = scan.nextInt();
		System.out.print("Enter password:");
		int pwd = scan.nextInt();
		
		//validating account details
		
		if(an==accountNumber && pwd==password) {
			while(true) {
				System.out.println();
				Thread.sleep(3000);
				System.out.println("1.Deposit\n2.Withdraw\n3.Balance\n4.Mini Statement\n5.Exit");
				System.out.print("\nEnter your choice:");
				int choice = scan.nextInt();
				
				if(choice==1) {
					System.out.print("Enter amount to be deposited:");
					int depositAmount = scan.nextInt();
					if(depositAmount>0) {
						a.deposit(depositAmount);
					}
					else {
						System.out.println("Invalid amount");
					}
				}
				else if(choice==2) {
					System.out.print("Enter amount to be withdraw:");
					int withdrawAmount=scan.nextInt();
					if(withdrawAmount>0) {
						a.withdraw(withdrawAmount);
					}
					else {
						System.out.println("Invalid amount");
					}
				}
				else if(choice==3) {
					a.viewBalance();
				}
				else if(choice==4) {
					a.viewMiniStatement();
				}
				else if(choice==5) {
					a.exit();
				}
				else {
					System.out.println("invalid choice");
					System.exit(0);
				}
			}
		}
		else{
			System.out.println("Invalid account number or password");
			}
	}
}
