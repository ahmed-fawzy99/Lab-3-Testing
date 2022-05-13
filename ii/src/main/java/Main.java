import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    Random r = new Random();

    String accountName = "Mr. Tester";
    String accountNumber = Integer.toString(r.nextInt(10000000) + 1);
    static double current_balance = 0;
    static double totalWithdrawnToday = 0;
    static double withdrawLimit = 5000;

    /*
    public void initAccount(String accountName, String accountNumber, int current_value){
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.current_value = current_value;
    }
    */

    // Return code 0 means success, -1 means failure.
    public static int deposit(double toDeposit){
        current_balance += toDeposit;
        System.out.println(toDeposit + " EGP deposited successfully\n"
        //        + "Your current balance is:" + current_value + " EGP"
        );
        return 0;
    }

    public static int withdraw(double toWithdraw){
        if (current_balance >= toWithdraw){
            if (totalWithdrawnToday + toWithdraw <= withdrawLimit) {
                current_balance -= toWithdraw;
                totalWithdrawnToday += toWithdraw;
                System.out.println(toWithdraw + " EGP withdrawn successfully\n"
                //        + "Your current balance is: " + current_value + " EGP"
                );
                return 0;
            } else {
                System.out.println("Sorry, you have exceeded the withdraw limit today. Please try again tomorrow.\n");
                return -1;
            }

        } else {
            System.out.println("Insufficient Funds\n");
            return -1;
        }
    }

    public static int sendMoneyToAccount(String accNo, double toSend){
        if (current_balance >= toSend){
            current_balance -= toSend;
            System.out.println(toSend + " EGP were sent to account #" + accNo
            //        + ".\nYour current balance is:" + current_value + " EGP"
            );
            return 0;
        } else {
            System.out.println("Insufficient Funds\n");
            return -1;
        }
    }

    public static void main(String []args){

        System.out.println("Welcome to the ATM Machine!\n\n");
        Scanner scObj= new Scanner(System.in);
        String user_input = "";

        while(true){
            System.out.print("Your current balance is: " + current_balance + " EGP.\n\n"
                    + "Please choose an option (enter 1, 2, or 3):\n" +
                    "1. Deposit Funds.\n2. Withdraw Funds.\n3. Transfer Funds to another account\n\n> ");

            user_input = scObj.nextLine();

            if (Objects.equals(user_input, "1")){ // Deposit
                System.out.println("Please enter deposit value:\n> ");
                user_input = scObj.nextLine();
                deposit(Double.parseDouble(user_input));
            }
            else if (Objects.equals(user_input, "2")){ // Withdraw
                System.out.println("Please enter withdraw value:\n> ");
                user_input = scObj.nextLine();
                withdraw(Double.parseDouble(user_input));
            }
            else if (Objects.equals(user_input, "3")){ // Send
                String accNo;
                String sendAmt;
                System.out.println("Please enter account number:\n> ");
                accNo = scObj.nextLine();
                System.out.println("Please enter the amount to send to #" + accNo + ":\n> ");
                sendAmt = scObj.nextLine();
                sendMoneyToAccount(accNo, Double.parseDouble(sendAmt));
            } else {
                System.out.println("Incorrect choice. Please try again.\n");
            }
        }


    }
}
