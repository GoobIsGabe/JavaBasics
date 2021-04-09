package live.goob.project.welcome;

import java.util.List;
import java.util.Scanner;

import live.goob.project.dao.UserDAOImpl;
import live.goob.project.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AdminMenu {
	public static Logger log = LogManager.getLogger(AdminMenu.class);
	public static void main(String[] args) {
		//Admin Menu	
		Input: 
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Welcome to the admin panel\n"
					+ "What would you like to do? \n"
					+ "\t(1): Approve/Reject temporary accounts\n"
					+ "\t(2): Change User Levels\n"
					+ "\t(3): Quit application\n\n"
					+ "Please enter the affiliated number: ");
			//Gets user input 
			if (scan.hasNextInt()) {
			int i = scan.nextInt();
			switch(i){
			case 1:
				UserDAOImpl eDaoP = new UserDAOImpl();
				User test = eDaoP.selectTemp("t");
				System.out.println("Did you want to (A)pprove or (D)elete records? (C)ancel");
				Scanner AppChoice = new Scanner(System.in);
				String choice = AppChoice.nextLine();
				if(choice.equals("a") || choice.equals("A")) {
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
					log.trace("Deleted | \tUserID " + delUser  + "\tusername: " + eDaoP.selectUser(delUser).getUser_name() );
					AdminActions.deleteExistingUser(delUser);
					
				}
				break;	
			case 2:
				UserDAOImpl select = new UserDAOImpl();
				User TempAndUser = select.selectTempAndUser();
				System.out.println("Which account did you want to change? (Please enter User ID)");
				Scanner change = new Scanner(System.in);
				int changeUser = change.nextInt();
				UserDAOImpl selected = new UserDAOImpl();
				System.out.println("So what would you like to do with this user? ");
				User Selected = selected.selectUser(changeUser);
				System.out.println(Selected);
				boolean valid = true;
				while (valid == true) {
				System.out.println("(D)elete user / UserLevel: (U)(A)(T)");
				Scanner modUser = new Scanner(System.in);
				String mod = modUser.nextLine();
					switch (mod){
						case "D":
							log.info("Deleted | \tUserID " + changeUser  + "\tUser Name: " + selected.selectUser(changeUser).getUser_name() );
							AdminActions.deleteExistingUser(changeUser);
							valid = false;
							break;
						case "U":
							System.out.println("U was selected"); //more or less for testing/debugging purposes
							AdminActions.changeUserLevel(mod.toLowerCase(), selected.selectUser(changeUser).getUser_id());
							log.info("Updated User Level | \tUserID " + changeUser  + "\tUser Name: " + selected.selectUser(changeUser).getUser_name() + "\tUser Level: " + selected.selectUser(changeUser).getUser_level());
							valid = false;
							break;
						case "A":
							System.out.println("A was selected");
							AdminActions.changeUserLevel(mod.toLowerCase(), selected.selectUser(changeUser).getUser_id());
							log.info("Updated User Level | \tUserID " + changeUser  + "\tUser Name: " + selected.selectUser(changeUser).getUser_name() + "\tUser Level: " + selected.selectUser(changeUser).getUser_level());
							valid = false;
							break;
						case "T":
							System.out.println("T was selected"); 
							AdminActions.changeUserLevel(mod.toLowerCase(), selected.selectUser(changeUser).getUser_id());
							log.info("Updated User Level | \tUserID " + changeUser  + "\tUser Name: " + selected.selectUser(changeUser).getUser_name() + "\tUser Level: " + selected.selectUser(changeUser).getUser_level());
							valid = false;
							break;
						default:
							System.out.println("Please select a valid option.");
								break;
					}
				}
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
