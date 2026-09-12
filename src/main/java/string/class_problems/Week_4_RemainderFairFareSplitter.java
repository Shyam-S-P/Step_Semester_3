import java.util.Scanner;

class FareSplitter {
    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId,
                        double totalFare,
                        int passengerCount) {
        if (totalFare < 0 || passengerCount <= 0)
            throw new IllegalArgumentException("Invalid fare split");

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 1);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {
        double[] result = new double[passengerCount];

        if (passengerCount == 0)
            return result;

        long totalPaise = Math.round(totalFare * 100);
        long base = totalPaise / passengerCount;
        long remainder = totalPaise % passengerCount;

        for (int i = 0; i < passengerCount; i++) {
            long share = base;

            // The remainder is assigned from the last passenger backward,
            // giving the extra paisa to the last shares consistently.
            if (i >= passengerCount - remainder)
                share++;

            result[i] = share / 100.0;
        }

        return result;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }
}

public class Week_4_RemainderFairFareSplitter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter trip ID:");
        String tripId = sc.nextLine();

        System.out.println("Enter total fare:");
        double fare = sc.nextDouble();

        System.out.println("Enter passenger count:");
        int count = sc.nextInt();

        try {
            FareSplitter splitter =
                new FareSplitter(tripId, fare, count);

            double[] breakdown = splitter.fareBreakdown();

            System.out.println("Fare breakdown:");
            double total = 0;

            for (double share : breakdown) {
                System.out.printf("%.2f ", share);
                total += share;
            }

            System.out.println("\nTotal: " +
                    String.format("%.2f", total));

            System.out.println("Enter confirmed shares:");
            int confirmed = sc.nextInt();

            System.out.println("Enter expected shares:");
            int expected = sc.nextInt();

            System.out.println("Confirmation overdue: " +
                    splitter.isConfirmationOverdue(
                            confirmed, expected));

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid fare split.");
        }

        sc.close();
    }
}
