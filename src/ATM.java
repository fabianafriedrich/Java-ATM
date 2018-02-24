
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.File;


public class ATM {
    String userID_global = "";
    String userPin_global = "";
    String accBalance_global = "";

    public ATM() {

        welcome();
        login();
        menu();
        menuSelect();


    }

    public void login() {

        //--------------------
        //Get the ID
        //--------------------
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        try {

            boolean validID = false;

            do {
                System.out.println("Please enter your ID: ");
                input = br.readLine();

                if (input.matches("[0-9]+")) {
                    validID = true;
                } else {
                    validID = false;
                }


            } while (validID == false);

        } catch (Exception e) {
            System.out.println("Error");
        }

        //---------------------
        //Get Pin
        //---------------------
        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
        String input2 = "";

        try {

            boolean validPIN = false;

            do {
                System.out.println("Please enter your PIN: ");
                input2 = br2.readLine();

                if (input2.matches("[0-9]+")) {
                    validPIN = true;
                } else {
                    validPIN = false;
                }


            } while (validPIN == false);

        } catch (Exception e) {
            System.out.println("Error");
        }

        //-----------------------
        //Reading in the file
        //-----------------------
        try {

            BufferedReader br3 = new BufferedReader(new FileReader("src/Users/"+input + ".txt"));
            String userID = br3.readLine();
            String userPIN = br3.readLine();
            String accBalance = br3.readLine();

            userID_global = userID;
            userPin_global = userPIN;
            accBalance_global = accBalance;

            if (userID.equals(input) && userPIN.equals(input2)) {

                menu();
                menuSelect();

            } else {
                System.out.println("Login failed, try again");

                login();
            }

        } catch (Exception e) {
            System.out.println("Login failed, try again.");
            welcome();
            login();
        }

    }

    // User select number in the (menu
    public void menuSelect() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        try {

            boolean validMenu = false;

            do {
                System.out.println("Please select a number: ");
                input = br.readLine();

                if (input.matches("[0-9]+")) {
                    validMenu = true;
                } else {
                    validMenu = false;
                }


            } while (validMenu == false);

        } catch (Exception e) {
            System.out.println("Error");
        }
        //System.out.println(input);

        //Check the user number and send to the menu select
        if (input.equals("1")) {
            checkAccountBalance();
        } else if (input.equals("2")) {
            withdrawMoney();
        } else if (input.equals("3")) {
            changePassword();
        } else if (input.equals("4")) {
            stockPrices();
        } else if (input.equals("5")) {
            logOut();
         } else if (input.equals("6")){
            BankSumm();
         }

    }

    //Reading the Balance
    public void checkAccountBalance() {

        //Reading in the File the balance
        System.out.println("Your account balance is: " + accBalance_global);

        //Sending to menu or Logout
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Would you like to do another operation? Yes or No");
            String option = "";
            option = br.readLine();
            if (option.equals("Yes") || option.equals("yes")) {
                menu();
                menuSelect();

            } else {
                logOut();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Withdraw Money
    public void withdrawMoney() {
        System.out.println(" How much would you like to withdraw?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        try {

            boolean valid = false;

            do {
                System.out.println("Please enter a value: 20 , 50, 100 or other");
                input = br.readLine();

                if (input.matches("[0-9]+")) {
                    valid = true;
                } else {
                    valid = false;
                }


            } while (valid == false);

        } catch (Exception e) {
            System.out.println("Error");
        }

        //New balance
        if (Double.parseDouble(accBalance_global) >= Double.parseDouble(input)) {
            Double answer = Double.parseDouble(accBalance_global) - Double.parseDouble(input);
            accBalance_global = String.valueOf(answer);

            //Update balance new Balance in the file
            replaceDataFile("src/Users/"+userID_global + ".txt", 3, accBalance_global);

            System.out.println("New balance: " + accBalance_global);

        } else {
            System.out.println("No money");
        }

        //Sending to menu or Logout
        try {
            System.out.println("Would you like to do another operation? Yes or No");
            String option = "";
            option = br.readLine();
            if (option.equals("Yes") || option.equals("yes")) {
                menu();
                menuSelect();

            } else {
                logOut();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Get new Pin and confirm
    public void changePassword() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            String input = "";
            String input2 = "";
            System.out.println("Please insert your new PIN");
            input = br.readLine();
            System.out.println("Please confirm your new PIN");
            input2 = br.readLine();

            if (input.equals(input2)) {
                replaceDataFile("src/Users/"+userID_global + ".txt", 2, input);
                System.out.println("Successfully changed password");
            } else {
                System.out.println("ERROR: Wrong PIN, Try again");
                changePassword();

            }

            //Sending to menu or Logout
            System.out.println("Would you like to do another operation? Yes or No");
            String option = "";
            option = br.readLine();
            if (option.equals("Yes") || option.equals("yes")) {
                menu();
                menuSelect();

            } else {
                logOut();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Going into StockPrice
    public void stockPrices() {
        System.out.println("Stock Price");
        String input = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //Reading File
        try {
            BufferedReader br3 = new BufferedReader(new FileReader(input + "Stock.txt"));
            input = br3.readLine();
            while (input != null) {
                System.out.println(input);
                input = br3.readLine();

            }
            //Sending to menu or Logout
            System.out.println("Would you like to do another operation? Yes or No");
            String option = "";
            option = br.readLine();
            if (option.equals("Yes") || option.equals("yes")) {
                menu();
                menuSelect();

            } else {
                logOut();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //Logout and get new User
    public void logOut() {
        System.out.println("Logout of the system!");

        welcome();
        login();
    }

    // Get into menu
    public void menu() {
        System.out.println("\n");
        System.out.println("1.Check the current account balance");
        System.out.println("2.Withdraw money from your account");
        System.out.println("3.Change current password");
        System.out.println("4.Check the latest stock prices for the bank");
        System.out.println("5.Logout of the system");
        System.out.println("6. Bank Summary");
        System.out.println("\n");
    }

    //Welcome mensage
    public void welcome() {
        System.out.println("Welcome to the ATM");
    }

    //Replace Data File
    //from https://stackoverflow.com/questions/20039980/java-replace-line-in-text-file
    public static void replaceDataFile(String filePath, int line, String newValue) {
        try {
            Path path = Paths.get(filePath);
            List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
            fileContent.set(line - 1, newValue);
            Files.write(path, fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //LISTING THE USERS
    public void BankSumm() {
        String address = "src/Users/";
        File directory = new File(address);
        File[] users_list = directory.listFiles();
        String line = "";
        String[] balances = new String[users_list.length];

        int small = 0;
        int medium = 0;
        int large = 0;
        int ex_large = 0;

        System.out.println("ID Balance");
        for (int i = 0; i < users_list.length; i++) {
            balances[i] = balance_reader(users_list[i].getName());

            if (Double.parseDouble(balances[i]) <= 100) {
                small++;
            } else if (Double.parseDouble(balances[i]) > 100 && Double.parseDouble(balances[i]) <= 200) {
                medium++;
            } else if (Double.parseDouble(balances[i]) > 200 && Double.parseDouble(balances[i]) <= 400) {
                large++;
            } else if (Double.parseDouble(balances[i]) > 400) {
                ex_large++;
            }

            System.out.println(users_list[i].getName().split("[.]")[0] + "     " + balances[i]);

        }
        System.out.println(" ");
        System.out.println("Number of Small accounts: " + small);
        System.out.println("Number of Medium accounts: " + medium);
        System.out.println("Number of Large accounts: " + large);
        System.out.println("Number of Extra Large accounts: " + ex_large);

        try {
            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Would you like to do another operation? Yes or No");
            String option = "";
            option = br2.readLine();
            if (option.equals("Yes") || option.equals("yes")) {
                menu();
                menuSelect();

            } else {
                logOut();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    String balance_reader(String id) {
        String line = "";
        String[] balances = new String[3];


        try {
            BufferedReader br = new BufferedReader(new FileReader("src/users/" + id));
            line = br.readLine();
            int counter = 0;

            while (line != null) {

                balances[counter] = line;

                counter++;
                line = br.readLine();

            }


        } catch (Exception e) {
        }

        return balances[2];//RETURNING JUST THE BALANC
    }



    public static void main(String[] args) {

        new ATM();

    }
}

