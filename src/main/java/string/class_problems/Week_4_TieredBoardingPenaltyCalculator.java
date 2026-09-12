import java.util.Scanner;

final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        if (minimumPenaltyPercent < 0)
            throw new IllegalArgumentException("Invalid minimum rate");

        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(
            double ticketFare, int minutesLate) {

        if (ticketFare < 0 || minutesLate < 0)
            throw new IllegalArgumentException("Invalid calculation input");

        if (minutesLate == 0)
            return 0.0;

        int firstTier = Math.min(minutesLate, 5);
        int secondTier = Math.min(Math.max(minutesLate - 5, 0), 10);
        int thirdTier = Math.max(minutesLate - 15, 0);

        double tiered =
            ticketFare * 0.005 * firstTier +
            ticketFare * 0.01 * secondTier +
            ticketFare * 0.02 * thirdTier;

        double floor =
            ticketFare * minimumPenaltyPercent / 100.0;

        return Math.max(tiered, floor);
    }
}

public class Week_4_TieredBoardingPenaltyCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter minimum penalty percent:");
        double minimumPercent = sc.nextDouble();

        try {
            BoardingPenaltyCalculator calculator =
                new BoardingPenaltyCalculator(minimumPercent);

            System.out.println("Enter ticket fare:");
            double fare = sc.nextDouble();

            System.out.println("Enter minutes late:");
            int minutes = sc.nextInt();

            System.out.println("Penalty: Rs " +
                    calculator.calculatePenalty(fare, minutes));

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input.");
        }

        sc.close();
    }
}
