import java.util.Scanner;

public class Week_1_MovieReviewWordLengthProfiler {

    static void classifyWordLengths(String review) {
        String[] words = review.trim().split("\\s+");

        int shortWords = 0;
        int mediumWords = 0;
        int longWords = 0;

        for (String word : words) {
            int length = word.length();

            if (length >= 1 && length <= 4)
                shortWords++;
            else if (length <= 8)
                mediumWords++;
            else
                longWords++;
        }

        System.out.println("Short: " + shortWords +
                " | Medium: " + mediumWords +
                " | Long: " + longWords);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter movie review:");
        String review = sc.nextLine();

        classifyWordLengths(review);

        sc.close();
    }
}
