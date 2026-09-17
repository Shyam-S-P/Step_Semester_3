import java.util.Scanner;

class EventTicket {
    protected double basePrice;
    protected double balanceDue;
    private double[] lateFeeHistory = new double[10];
    private int historyCount = 0;

    public EventTicket(double basePrice) {
        if (basePrice <= 0)
            throw new IllegalArgumentException("Invalid base price");

        this.basePrice = basePrice;
        this.balanceDue = basePrice;
    }

    public EventTicket(String attendeeId, double basePrice) {
        this(basePrice);
    }

    public void pay(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Payment must be positive");

        balanceDue = Math.max(0, balanceDue - amount);
    }

    protected void applyLateFee(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Fee must be positive");

        balanceDue += amount;

        if (historyCount < lateFeeHistory.length)
            lateFeeHistory[historyCount++] = amount;
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public double[] getLateFeeHistory() {
        double[] copy = new double[historyCount];

        for (int i = 0; i < historyCount; i++)
            copy[i] = lateFeeHistory[i];

        return copy;
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(double basePrice) {
        super(basePrice);
        this.track = "General";
    }

    public WorkshopTicket(double basePrice, String track) {
        super(basePrice);

        if (track == null || track.trim().isEmpty())
            throw new IllegalArgumentException("Invalid track");

        this.track = track.trim();
    }

    public WorkshopTicket(String attendeeId, double basePrice,
                           String track) {
        this(basePrice, track);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class Week_6_LateRegistrationPenaltyOverrideAuditTrail {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter workshop base price:");
        double price = sc.nextDouble();

        System.out.println("Enter payment amount:");
        double payment = sc.nextDouble();

        System.out.println("Enter late fee amount:");
        double lateFee = sc.nextDouble();

        try {
            WorkshopTicket ticket =
                    new WorkshopTicket(price);

            ticket.pay(payment);

            // applyLateFee is protected, so this main method cannot
            // call it directly. A helper subclass exposes it safely.
            FeeTestTicket testTicket =
                    new FeeTestTicket(price);

            testTicket.pay(payment);
            testTicket.applyFee(lateFee);

            System.out.println("Balance Due: " +
                    testTicket.getBalanceDue());

            double[] history =
                    testTicket.getLateFeeHistory();

            System.out.println("Late Fee History:");
            for (double value : history)
                System.out.println(value);

            if (history.length > 0)
                history[0] = 999999;

            System.out.println("Defensive copy check:");
            double[] actual = testTicket.getLateFeeHistory();
            for (double value : actual)
                System.out.println(value);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input.");
        }

        sc.close();
    }

    static class FeeTestTicket extends WorkshopTicket {
        FeeTestTicket(double price) {
            super(price);
        }

        void applyFee(double amount) {
            applyLateFee(amount);
        }
    }
}
