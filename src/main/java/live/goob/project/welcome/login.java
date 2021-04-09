package live.goob.project.welcome;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import live.goob.project.dao.*;

import live.goob.project.model.User;

public class login {
	public static Logger log = LogManager.getLogger(login.class);

	public static void main(String[] args) {
		// Menu

		Input: while (true) {
			Scanner scan = new Scanner(System.in);

			System.out.println("Welcome to Goob's Banking application!\n" + "What would you like to do? \n"
					+ "\t(1): Login\n" + "\t(2): Create an account\n" + "\t(3): Get the heck out of here\n\n"
					+ "Please enter the affiliated number: ");
			if (scan.hasNextInt()) {
				int i = scan.nextInt();
				switch (i) {
				case 1:
					System.out.println("Username: ");
					Scanner scanUser = new Scanner(System.in);
					String loginusername = scanUser.nextLine();
					System.out.println("Password: ");
					Scanner scanPass = new Scanner(System.in);
					String loginuserpass = scanPass.nextLine();
					loginCheck(loginusername, loginuserpass);
					break;
				case 2:
					register();
					break;
				case 3:
					System.out.println("Have a nice day :)");
					System.exit(0);
					break;
				default:
					System.out.println("Please input a correct parameter:");
					continue Input;
				}
			} else {
				System.out.println("Please input a correct parameter:");
				continue Input;
			}
		}

	}

	public static void loginCheck(String username, String userpass) {
		UserDAOImpl eDaoP = new UserDAOImpl();
		// System.out.println();
		User test = eDaoP.selectUser(username);
		String user = test.getUser_name();
		String password = test.getUser_pass();
		boolean ch_u = username.equals(user);
		boolean ch_p = userpass.equals(password);

		if (ch_u == true && ch_p == true) {
			System.out.println("Succesfully Logged In");
			// System.out.println(eDaoP); This prints memory address
			if (test.getUser_level().equals("t")) {
				System.out.println("You are a temporary user, please wait until an admin approves your account");
				log.trace("Login | \t" + test);
			} else if (test.getUser_level().equals("u")) {
				System.out.println("You are a user, welcome to your account");
				log.trace("Login | \t" + test);
				UserMenu.main(test.getUser_name());
			} else if (test.getUser_level().equals("a")) {
				System.out.println("You are an admin, all the power is yours");
				log.trace("Login | \t" + test);
				AdminMenu.main(null);
			}
			// System.out.println(test); //this prints our Overwritten string method

		} else {
			System.out.println("Incorrect username or password.");
		}
	}

	// adds to database
	public static void register() {
		// Added because of menu
		// user input
		Boolean registerState = false;
		while (registerState == false) {
			System.out.print("Please input a username: ");
			Scanner username_ = new Scanner(System.in);
			String user_name = username_.nextLine();
			System.out.print("Please input a password: ");
			Scanner userpass_ = new Scanner(System.in);
			String user_pass = username_.nextLine();

			// confirm this is correct
			System.out.println("Is the following information correct?\n" + "username: " + user_name + "\npassword: "
					+ user_pass + "\n(Y / N / (E)xit");
			Boolean confirm = false;
			while (confirm == false) {
				Scanner confirmI = new Scanner(System.in);
				char confirmInput = confirmI.next().charAt(0);
				if (confirmInput == 'Y' || confirmInput == 'y') {
					registerState = true;
					confirm = true;

					UserDAOImpl eDaoP = new UserDAOImpl();
					User username = new User(null, user_name, user_pass, null);
					// e.getUser_id();
					if (checkExistingUser(user_name) == true) {
						System.out.println("That user exists please try again");
						boolean userexists = true;
					} else {
						username.getUser_pass();
						Boolean check = eDaoP.insertIntoUsers(username);
						System.out.println("Please wait for an employee or admin to approve your account");
						log.trace("New Registration | \t" + username + eDaoP.selectUser(user_name).getUser_id());
						break;
					}
				}

				else if (confirmInput == 'N' || confirmInput == 'n') {
					System.out.println("Go ahead and re-type the information");
					break;
				} else if (confirmInput == 'E' || confirmInput == 'e') {
					System.out.println("Have a good day!");
					System.exit(0);
					break;
				} else
					System.out.println("Please input a correct response: ");

			}
		}

	}

	public static Boolean checkExistingUser(String possibleUser) {
		UserDAOImpl eDaoP = new UserDAOImpl(); // connects to the database location
		User test = eDaoP.selectUser(possibleUser); // checking if input exists on table
		String user = test.getUser_name(); // getting the user ID from that field
		Boolean check = possibleUser.equals(user); // checking if field is equal to the input
		return check;

	}

}
