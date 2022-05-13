import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    static String[] coffeeMenu = {"Espresso ", "Americano ", "Latte ", "Mocha ", "Cappuccino "};
    static double[] coffeePrices = {14, 11, 19, 17, 13};
    static double balance = 0;
    static double dueAmount = 0;

    static ArrayList<String> customerOrder = new ArrayList<String>();

    public static void showMenu(){
        for (int i = 0; i < 5; i++) {
            System.out.println((i+1) + ": " + coffeeMenu[i] + coffeePrices[i] + " EGP");
        }
    }
    public static void showOrder(){
        System.out.println("\nYour Ordered: \n");
        for (int i = 0; i < customerOrder.size(); i++) {
            System.out.println((i+1) + ": " + customerOrder.get(i));
        }
        System.out.print("\n");
    }

    public static void requestCoffee(String coffeeName, double cost){
        customerOrder.add(coffeeName);
        dueAmount += cost;
    }

    public static void requestMoney(){
        System.out.println("To simulate the deposit functionality here, just enter the value you have to insert. " +
                "For example: enter '5' to insert 5 EGP into your balance.");
        Scanner scObj= new Scanner(System.in);
        double user_input = 0;

        while (true){
            System.out.print("\nYour balance is now: " + balance + "EGP. Your order total is " + dueAmount + " EGP \n" +
                    "Please insert your money into the machine (Enter '-1' to finish depositing)\n> ");
            user_input = Double.parseDouble(scObj.nextLine());
            if (user_input == -1){
                break;
            }
            else {
                balance += user_input;
            }
        }
    }

    public static void requestMoney__NO_INPUT(double deposit_cash){ // No interaction with user. Same function but for testing.
        balance += deposit_cash;
    }

    public static boolean pay(double due){
        if (balance >= due){
            balance -= due;
            dueAmount = 0;
            customerOrder.clear();
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String []args) throws InterruptedException {

        System.out.println("Welcome to the Coffee Machine!\n");
        Scanner scObj= new Scanner(System.in);
        String user_input = "";

        while (true){
            System.out.println("Here's our menu for today:\n");
            showMenu();
            boolean stay = true;

            while (stay){
                System.out.print("\nPlease Enter your menu item number (a value between 1 and 5) " +
                        "- Enter '-1' to checkout:\n> ");
                user_input = scObj.nextLine();

                if (Objects.equals(user_input, "1")){
                    requestCoffee(coffeeMenu[Integer.parseInt(user_input) - 1], coffeePrices[Integer.parseInt(user_input) - 1]);
                    System.out.println("Item added. Total = " + dueAmount + " EGP.");
                }
                else if (Objects.equals(user_input, "2")){
                    requestCoffee(coffeeMenu[Integer.parseInt(user_input) - 1], coffeePrices[Integer.parseInt(user_input) - 1]);
                    System.out.println("Item added. Total = " + dueAmount + " EGP.");
                }
                else if (Objects.equals(user_input, "3")){
                    requestCoffee(coffeeMenu[Integer.parseInt(user_input) - 1], coffeePrices[Integer.parseInt(user_input) - 1]);
                    System.out.println("Item added. Total = " + dueAmount + " EGP.");
                }
                else if (Objects.equals(user_input, "4")){
                    requestCoffee(coffeeMenu[Integer.parseInt(user_input) - 1], coffeePrices[Integer.parseInt(user_input) - 1]);
                    System.out.println("Item added. Total = " + dueAmount + " EGP.");
                }
                else if (Objects.equals(user_input, "5")){
                    requestCoffee(coffeeMenu[Integer.parseInt(user_input) - 1], coffeePrices[Integer.parseInt(user_input) - 1]);
                    System.out.println("Item added. Total = " + dueAmount + " EGP.");
                }
                else if (Objects.equals(user_input, "-1")){
                    stay = false; // AKA quit the loop
                }
                else {
                    System.out.println("Incorrect choice. Please try again.\n");
                }
            }

            showOrder();
            while (true){
                requestMoney();
                if (balance >= dueAmount){
                    pay(dueAmount);
                    System.out.println("Your order is now processing. Please wait..");
                    TimeUnit.SECONDS.sleep(3);
                    break;
                }
            }
            System.out.println("\nPlease receive your order from the counter.\nThanks for using our service!!");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("\nPress Enter to start a new order\n");
            user_input = scObj.nextLine();
        }

    }
}
