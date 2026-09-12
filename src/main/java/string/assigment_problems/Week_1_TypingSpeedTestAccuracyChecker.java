import java.util.Scanner;

public class Week_1_TypingSpeedTestAccuracyChecker {

    static void checkTypingAccuracy(String original, String typed) {
        if (original.length() != typed.length()) {
            System.out.println("Invalid input: strings must have equal length.");
            return;
        }

        int matched = 0;
        int firstMismatch = -1;

        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        double accuracy = original.length() == 0
                ? 100.0
                : (matched * 100.0) / original.length();

        if (firstMismatch == -1) {
            System.out.printf(
                "Matched: %d/%d | Accuracy: %.2f%% | No Mismatches%n",
                matched, original.length(), accuracy);
        } else {
            System.out.printf(
                "Matched: %d/%d | Accuracy: %.2f%% | First Mismatch at position %d ('%c' vs '%c')%n",
                matched, original.length(), accuracy,
                firstMismatch + 1,
                original.charAt(firstMismatch),
                typed.charAt(firstMismatch));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter original passage:");
        String original = sc.nextLine();

        System.out.println("Enter typed passage:");
        String typed = sc.nextLine();

        checkTypingAccuracy(original, typed);

        sc.close();
    }
}
