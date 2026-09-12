import java.util.Scanner;

class BusTicketAccount {
    protected String bookingId;
    protected double ticketFare;

    static {
        System.setProperty("fleet.reconciliation.ready", "true");
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        if (bookingId == null || bookingId.trim().isEmpty() ||
            ticketFare < 0)
            throw new IllegalArgumentException("Invalid account");

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public final double calculatePenalty(int minutesLate) {
        if (minutesLate < 0)
            throw new IllegalArgumentException("Invalid delay");

        return minutesLate * ticketFare * 0.01;
    }

    void processAccount(BusTicketAccount account,
                        double amount,
                        int minutesLate) {
        double penalty = account.calculatePenalty(minutesLate);
        System.out.println(account.bookingId +
                " | amount: Rs " + amount +
                " | penalty: Rs " + penalty);
    }

    static void processBatch(BusTicketAccount[] accounts,
                              double[] amounts,
                              int[] minutesLateArray) {

        if (accounts == null || amounts == null ||
            minutesLateArray == null) {
            System.out.println("Invalid batch.");
            return;
        }

        int limit = Math.min(accounts.length,
                    Math.min(amounts.length, minutesLateArray.length));

        int processed = 0, nullSkipped = 0;
        int sleeper = 0, regular = 0;
        double total = 0;

        for (int i = 0; i < limit; i++) {
            BusTicketAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            double penalty = account.calculatePenalty(
                    minutesLateArray[i]);

            if (account instanceof SleeperBusTicketAccount) {
                sleeper++;
                penalty *= 0.5;
            } else {
                regular++;
            }

            processed++;
            total += penalty;
        }

        System.out.println(processed + " processed | " +
                nullSkipped + " null skipped | " +
                sleeper + " sleeper | " +
                regular + " regular | grand total penalties = Rs " +
                total);
    }
}

class SleeperBusTicketAccount extends BusTicketAccount {
    public SleeperBusTicketAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }
}

public class Week_4_NightlyFleetReconciliationEngine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter batch size:");
        int n = sc.nextInt();
        sc.nextLine();

        BusTicketAccount[] accounts = new BusTicketAccount[n];
        double[] amounts = new double[n];
        int[] delays = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Account " + (i + 1) +
                    " type (1-Regular, 2-Sleeper, 3-Null):");
            int type = sc.nextInt();
            sc.nextLine();

            if (type != 3) {
                System.out.println("Enter booking ID:");
                String id = sc.nextLine();

                System.out.println("Enter ticket fare:");
                double fare = sc.nextDouble();

                if (type == 2)
                    accounts[i] =
                        new SleeperBusTicketAccount(id, fare);
                else
                    accounts[i] =
                        new BusTicketAccount(id, fare);

                sc.nextLine();
            }

            System.out.println("Enter amount:");
            amounts[i] = sc.nextDouble();

            System.out.println("Enter minutes late:");
            delays[i] = sc.nextInt();
            sc.nextLine();
        }

        BusTicketAccount.processBatch(
                accounts, amounts, delays);

        sc.close();
    }
}
