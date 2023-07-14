import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

interface ATMInterface
{
    void signUp();
    void login();
    void transactionMenu();
    void withdraw();
    void deposit();
    void transfer();
    void checkBalance();
    void viewTransactionHistory();
}

class User
{
    String acno;
    String name;
    String pin;
    double balance;
    List<String> transactionHistory;

    public User(String acno, String name, String pin)
    {
        this.acno = acno;
        this.name = name;
        this.pin = pin;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId()
    {
        return acno;
    }
    public String getUserName()
    {
        return name;
    }
    public String getPassword()
    {
        return pin;
    }
    public double getBalance()
    {
        return balance;
    }
    public void deposit(double amount)
    {
        balance += amount;
        transactionHistory.add("Deposited: " + amount);
        System.out.println("Deposited successfully of amount: " +amount);
        System.out.println("Your current balance is: " + balance);
    }
    public void withdraw(double amount)
    {
        if (balance >= amount)
        {
            balance -= amount;
            transactionHistory.add("Withdrawn: " + amount);
            System.out.println("Withdrawal successful of amount: " +amount);
            System.out.println("Your current balance is: " + balance);
        }
        else
        {
            System.out.println("Insufficient balance!");
        }
    }

    public void transfer(User recipient, double amount)
    {
        if (balance >= amount)
        {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add("Transferred: " + amount + " to " + recipient.getUserName());
            System.out.println("Transferred successfully of amount: " +amount);
            System.out.println("Your current balance is: " + balance);
        }
        else
        {
            System.out.println("Insufficient balance!");
        }
    }

    public void printTransactionHistory()
    {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory)
        {
            System.out.println(transaction);
        }
    }
}
class ATM implements ATMInterface
{
    private User user;
    private Scanner scanner;
    public ATM()
    {
        this.scanner = new Scanner(System.in);
    }
    public void signUp()
    {
        System.out.println("***** INDIAN BANK ATM *****");
        System.out.println("-*-SIGNUP PAGE-*-");
        System.out.print("Enter Account number: ");
        String acno = scanner.nextLine();
        System.out.print("Enter User Name: ");
        String name = scanner.nextLine();
        System.out.print("Set MPIN for your account: ");
        String pin = scanner.nextLine();
        user = new User(acno, name, pin);
        System.out.println("Dear User, account created successfully!");
        System.out.println("** Kindly do not share you ID/Password **");
    }
    public void login()
    {
        System.out.println("***** INDIAN BANK ATM *****");
        System.out.println("-*-LOGIN PAGE-*-");
        System.out.print("Enter your Account number: ");
        String newacno = scanner.nextLine();
        System.out.print("Enter your MPIN: ");
        String enteredpin = scanner.nextLine();

        if (user != null && user.getUserId().equals(newacno) && user.getPassword().equals(enteredpin))
        {
            transactionMenu();
        }
        else
        {
            System.out.println("OOPS ! You have entered wrong details :( ");
        }
    }
    public void transactionMenu()
    {
        int choice;
        do {
            System.out.println("****** INDIAN BANK ATM ******");
            System.out.println("-*-TRANSACTION MENU-*-");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Transfer");
            System.out.println("4. Check Balance");
            System.out.println("5. Transaction History");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    transfer();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    System.out.println("Quiting INDIAN BANK ATM Transaction menu!");
                    System.out.println("Thank You for using our services !");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }
    public void withdraw()
    {
        System.out.print("How much amount you would like to withdraw: ");
        double amount = scanner.nextDouble();
        user.withdraw(amount);
    }
    public void deposit()
    {
        System.out.print("How much amount you would like to deposit: ");
        double amount = scanner.nextDouble();
        user.deposit(amount);
    }
    public void transfer()
    {
        System.out.print("Enter recipient's Account Number: ");
        String recipientacno = scanner.next();
        System.out.print("How much amount you would like to transfer: ");
        double amount = scanner.nextDouble();

        // Dummy check for recipient user id. You can implement a proper check.
        User recipient = new User(recipientacno, "", "");
        user.transfer(recipient, amount);
    }


    public void checkBalance()
    {
        System.out.println("Balance: " + user.getBalance());
    }


    public void viewTransactionHistory()
    {
        user.printTransactionHistory();
    }
}

public class Main
{
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("*/*/*/*/*  Welcome to the INDIAN BANK ATM! */*/*/*/*");
            System.out.println("1. SignUp");
            System.out.println("2. Login");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    atm.signUp();
                    break;
                case 2:
                    atm.login();
                    break;
                case 3:
                    System.out.println("Quiting INDIAN BANK ATM Services");
                    System.out.println("Thank You for choosing us !");
                    System.out.println("** STAY SAFE **");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }
}
