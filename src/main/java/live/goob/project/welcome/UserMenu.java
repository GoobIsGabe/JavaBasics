package live.goob.project.welcome;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import live.goob.project.dao.AccountDAOImpl;
import live.goob.project.dao.UserDAOImpl;
import live.goob.project.model.Account;
import live.goob.project.model.User;

public class UserMenu {
	public static Logger log = LogManager.getLogger(UserMenu.class);
	public static void main(String User) {
		//User Menu	
				Input: 
				while (true) {
					Scanner scan = new Scanner(System.in);
					System.out.println("Welcome to the user panel, " + User + "!\n" 
							+ "What would you like to do? \n"
							+ "\t(1): Deposit funds\n"
							+ "\t(2): Withdrawl funds\n"
							+ "\t(3): Quit application\n\n"
							+ "Please enter the affiliated number: ");
					//Gets user input 
					if (scan.hasNextInt()) {
					int i = scan.nextInt();
					switch(i){
					case 1:
						System.out.println("How much would you like to deposit?");
						Scanner AppChoice = new Scanner(System.in);
						Double dAmount = AppChoice.nextDouble();
						AccountDAOImpl Deposit = new AccountDAOImpl();
						Account test = Deposit.deposit(dAmount, User);
						System.out.println("Sorry, the application is currently under maintenance...");
						
					/*	if(choice.equals("a") || choice.equals("A")) {
							System.out.println("Which account did you want to approve? (Please enter User ID)");
							Scanner approve = new Scanner(System.in);
							int appUser = approve.nextInt();
							AdminActions.approveExistingUser(appUser);
							log.trace("Approved");
						}
						else if(choice.equals("d") || choice.equals("D")) {
							System.out.println("Which account did you want to delete? (Please enter User ID)");
							Scanner delete = new Scanner(System.in);
							int delUser = delete.nextInt();
							AdminActions.deleteExistingUser(delUser);
							log.trace("Deleted user: " + delUser);
						}*/
						break;	
					case 2:
						UserDAOImpl select = new UserDAOImpl();
						User TempAndUser = select.selectTempAndUser();
						System.out.println("Which account did you want to change? (Please enter User ID)");
						Scanner change = new Scanner(System.in);
						int changeUser = change.nextInt();
						//UserDAOImpl eDaoP = new UserDAOImpl();
						//User test = eDaoP.selectTemp("t");
						break;
					case 3:
						System.out.println("Have a nice day :)");
						System.exit(0);
						break;
					default:	
							System.out.println("Please input a correct parameter:");
							continue Input;
					}
					}
					else {
						System.out.println("Please input a correct parameter:");
						continue Input;
					}
					}
				
				

	}

}
