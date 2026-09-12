import java.util.Scanner;

final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        if (minimumSurgePercent < 0)
            throw new IllegalArgumentException("Invalid surge rate");

        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(
            double orderValue, int delayMinutes) {

        if (orderValue < 0 || delayMinutes < 0)
            throw new IllegalArgumentException("Invalid input");

        if (delayMinutes == 0)
            return 0.0;

        int first = Math.min(delayMinutes, 5);
        int second =
            Math.min(Math.max(delayMinutes - 5, 0), 10);
        int third = Math.max(delayMinutes - 15, 0);

        double tiered =
            orderValue * 0.005 * first +
            orderValue * 0.01 * second +
            orderValue * 0.02 * third;

        double floor =
            orderValue * minimumSurgePercent / 100.0;

        return Math.max(tiered, floor);
    }
}

public class Week_4_ExamWeekSurgeFeeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter minimum surge percent:");
        double minimumPercent = sc.nextDouble();

        try {
            SurgeFeeCalculator calculator =
                new SurgeFeeCalculator(minimumPercent);

            System.out.println("Enter order value:");
            double value = sc.nextDouble();

            System.out.println("Enter delay minutes:");
            int delay = sc.nextInt();

            System.out.println("Surge fee: Rs " +
                    calculator.calculateSurgeFee(
                            value, delay));

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input.");
        }

        sc.close();
    }
}
