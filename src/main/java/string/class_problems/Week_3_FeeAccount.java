import java.util.Scanner;

class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    void pay(double amount) {
        if (amount <= 0) {
            System.out.println("Payment must be positive.");
            return;
        }

        if (amount > getDue()) {
            System.out.println("Payment exceeds the due amount.");
            return;
        }

        amountPaid += amount;
    }

    double getDue() {
        return totalFee - amountPaid;
    }

    void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }

    double effectiveDue(double scholarshipPercent) {
        return getDue() * (1 - scholarshipPercent / 100);
    }
}

public class Week_3_FeeAccount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Account A registration number:");
        String regA = sc.nextLine();

        System.out.println("Enter Account A total fee:");
        double totalA = sc.nextDouble();

        System.out.println("Enter Account A amount to pay in two installments:");
        double installmentAmount = sc.nextDouble();

        FeeAccount accountA = new FeeAccount(regA, totalA, 0);
        accountA.payInTwoInstallments(installmentAmount);

        System.out.println("Enter Account B registration number:");
        sc.nextLine();
        String regB = sc.nextLine();

        System.out.println("Enter Account B total fee:");
        double totalB = sc.nextDouble();

        System.out.println("Enter Account B amount already paid:");
        double paidB = sc.nextDouble();

        System.out.println("Enter scholarship percentage:");
        double scholarship = sc.nextDouble();

        FeeAccount accountB = new FeeAccount(regB, totalB, paidB);

        System.out.println("Account A due: Rs " + accountA.getDue());
        System.out.println("Account B effective due (" +
                scholarship + "% scholarship): Rs " +
                accountB.effectiveDue(scholarship));

        sc.close();
    }
}
