import java.util.Scanner;

public class Week_1_BMITeamCalculator {

    static String getBmiStatus(double bmi) {
        if (bmi < 18.5)
            return "Underweight";
        else if (bmi < 25)
            return "Normal";
        else if (bmi < 30)
            return "Overweight";
        else
            return "Obese";
    }

    static void printWellnessReport(double[] heights,
                                    double[] weights) {
        System.out.println("Person | Height (m) | Weight (kg) | BMI | Status");

        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] /
                    (heights[i] * heights[i]);

            System.out.printf("%d | %.2f | %.2f | %.2f | %s%n",
                    i + 1, heights[i], weights[i],
                    bmi, getBmiStatus(bmi));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of people:");
        int n = sc.nextInt();

        double[] heights = new double[n];
        double[] weights = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter height for person " +
                    (i + 1) + " in meters:");
            heights[i] = sc.nextDouble();

            System.out.println("Enter weight for person " +
                    (i + 1) + " in kg:");
            weights[i] = sc.nextDouble();
        }

        printWellnessReport(heights, weights);

        sc.close();
    }
}
