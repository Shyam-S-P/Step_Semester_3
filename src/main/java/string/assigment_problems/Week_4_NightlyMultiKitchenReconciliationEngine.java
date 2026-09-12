import java.util.Scanner;

class DeliveryAccount {
    protected String studentId;
    protected double orderValue;

    static {
        System.setProperty("delivery.reconciliation.ready", "true");
    }

    public DeliveryAccount(String studentId, double orderValue) {
        if (studentId == null ||
            studentId.trim().isEmpty() ||
            orderValue < 0)
            throw new IllegalArgumentException("Invalid account");

        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes < 0)
            throw new IllegalArgumentException("Invalid delay");

        if (delayMinutes == 0)
            return 0.0;

        return orderValue * 0.01 * delayMinutes;
    }

    void processAccount(DeliveryAccount account,
                        double amount,
                        int delayMinutes) {
        double fee = account.calculateSurgeFee(delayMinutes);

        System.out.println(account.studentId +
                " | amount: Rs " + amount +
                " | surge fee: Rs " + fee);
    }

    static void processBatch(DeliveryAccount[] accounts,
                             double[] amounts,
                             int[] delayMinutesArray) {

        if (accounts == null ||
            amounts == null ||
            delayMinutesArray == null) {
            System.out.println("Invalid batch.");
            return;
        }

        int limit = Math.min(accounts.length,
                    Math.min(amounts.length,
                             delayMinutesArray.length));

        int processed = 0, nullSkipped = 0;
        int premium = 0, regular = 0;
        double total = 0;

        for (int i = 0; i < limit; i++) {
            DeliveryAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            double fee =
                account.calculateSurgeFee(
                        delayMinutesArray[i]);

            if (account instanceof PremiumDeliveryAccount) {
                premium++;
                fee *= 0.5;
            } else {
                regular++;
            }

            processed++;
            total += fee;
        }

        System.out.println(processed + " processed | " +
                nullSkipped + " null skipped | " +
                premium + " premium | " +
                regular + " regular | grand total surge fees = Rs " +
                total);
    }
}

class PremiumDeliveryAccount extends DeliveryAccount {
    public PremiumDeliveryAccount(
            String studentId, double orderValue) {
        super(studentId, orderValue);
    }
}

public class Week_4_NightlyMultiKitchenReconciliationEngine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter batch size:");
        int n = sc.nextInt();
        sc.nextLine();

        DeliveryAccount[] accounts =
                new DeliveryAccount[n];

        double[] amounts = new double[n];
        int[] delays = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println(
                    "Account " + (i + 1) +
                    " type (1-Regular, 2-Premium, 3-Null):");
            int type = sc.nextInt();
            sc.nextLine();

            if (type != 3) {
                System.out.println("Enter student ID:");
                String id = sc.nextLine();

                System.out.println("Enter order value:");
                double value = sc.nextDouble();
                sc.nextLine();

                if (type == 2)
                    accounts[i] =
                        new PremiumDeliveryAccount(id, value);
                else
                    accounts[i] =
                        new DeliveryAccount(id, value);
            }

            System.out.println("Enter amount:");
            amounts[i] = sc.nextDouble();

            System.out.println("Enter delay minutes:");
            delays[i] = sc.nextInt();
            sc.nextLine();
        }

        DeliveryAccount.processBatch(
                accounts, amounts, delays);

        sc.close();
    }
}
