import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class User {
    private List<String> username = new ArrayList<>();
    private List<Integer> pin = new ArrayList<>();
    private String name;
    void register(Scanner sc) {
        System.out.print("Register a username: ");
        username.add(sc.next());
        System.out.print("Set a 4-digit PIN: ");
        pin.add(sc.nextInt());
        System.out.println("Registration successful!");
    }

    boolean login(Scanner sc) {
        System.out.print("Enter username: ");
        String inputUser = sc.next();
        System.out.print("Enter 4-digit PIN: ");
        int inputPin = sc.nextInt();

        for (int i = 0; i < username.size(); i++) {
            if (username.get(i).equals(inputUser) && pin.get(i) == inputPin) {
                name = username.get(i);
                System.out.println("Login successful!");
                return true;
            }
        }
        
        System.out.println("Invalid credentials!");
        return false;
    }

    public String getUsername() {
        return name;
    }
}

class TransactionHistory {
    List<String> action = new ArrayList<>();
    List<Integer> amount = new ArrayList<>();
    List<Integer> balance = new ArrayList<>();
    List<String> time = new ArrayList<>();

    void addHistory(String action, int amount, int balance) {
        this.action.add(action);
        this.amount.add(amount);
        this.balance.add(balance);

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.time.add(date.format(dtf));
    }

    void history() {
        for (int i = 0; i < action.size(); i++) {
            System.out.print(action.get(i) + " of ");
            System.out.print("Amount: " + amount.get(i) + ", ");
            System.out.print("Balance: " + balance.get(i) + ", ");
            System.out.println("Time: " + time.get(i));
        }
    }
}

class Withdraw {
    int withdraw(int balance, int amount, TransactionHistory th) {
        if (amount % 500 != 0) {
            System.out.println("Amount should be in multiples of 500");
        } else if (amount > balance) {
            System.out.println("Insufficient balance");
        } else if (amount > 25000) {
            System.out.println("Amount exceeds daily withdrawal limit");
        } else {
            balance -= amount;
            System.out.println("Amount withdrawn: " + amount);
            System.out.println("Current balance: " + balance);
            th.addHistory("Withdrawal", amount, balance);
        }
        return balance;
    }
}

class Deposit {
    int deposit(int balance, int amount, TransactionHistory th) {
        balance += amount;
        System.out.println("Amount deposited: " + amount);
        System.out.println("Current balance: " + balance);
        th.addHistory("Deposit", amount, balance);
        return balance;
    }
}

class Transfer {
    int transfer(String account, int balance, int amount, TransactionHistory th) {
        if (amount > balance) {
            System.out.println("Insufficient balance");
        } else {
            balance -= amount;
            System.out.println("Amount " + amount + " transferred to account " + account);
            System.out.println("Current balance: " + balance);
            th.addHistory("Transfer", amount, balance);
        }
        return balance;
    }
}

public class Oibsip_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        User user = new User();
        user.register(sc);

        if (!user.login(sc)) {
            System.out.println("Exiting program...");
            sc.close();
            return;
        }

        int flag = 0;
        int balance = 50000;
        TransactionHistory th = new TransactionHistory();
        Withdraw withdraw = new Withdraw();
        Deposit deposit = new Deposit();
        Transfer transfer = new Transfer();

        while (flag == 0) {
            System.out.println("\nWelcome, " + user.getUsername() + "!");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int c = sc.nextInt();

            switch (c) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    int depositAmount = sc.nextInt();
                    balance = deposit.deposit(balance, depositAmount, th);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    int withdrawAmount = sc.nextInt();
                    balance = withdraw.withdraw(balance, withdrawAmount, th);
                    break;

                case 3:
                    System.out.print("Enter account number to transfer to: ");
                    String account = sc.next();
                    System.out.print("Enter amount to transfer: ");
                    int transferAmount = sc.nextInt();
                    balance = transfer.transfer(account, balance, transferAmount, th);
                    break;

                case 4:
                    System.out.println("Transaction History:");
                    th.history();
                    break;

                case 5:
                    System.out.println("Thank you for using our service!");
                    flag = 1;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }
}
