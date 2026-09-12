import java.util.Scanner;

public class Week_1_PalindromeCheckerThreeApproaches {

    static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right))
                return false;

            left++;
            right--;
        }

        return true;
    }

    static boolean isPalindromeRecursive(String text) {
        return recursiveCheck(text, 0, text.length() - 1);
    }

    static boolean recursiveCheck(String text, int left, int right) {
        if (left >= right)
            return true;

        if (text.charAt(left) != text.charAt(right))
            return false;

        return recursiveCheck(text, left + 1, right - 1);
    }

    static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];

        for (int i = 0; i < original.length; i++)
            reversed[i] = original[original.length - 1 - i];

        for (int i = 0; i < original.length; i++) {
            if (original[i] != reversed[i])
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter text:");
        String text = sc.nextLine();

        boolean iterative = isPalindromeIterative(text);
        boolean recursive = isPalindromeRecursive(text);
        boolean array = isPalindromeArrayReversal(text);

        System.out.println("Iterative: " +
                (iterative ? "Palindrome" : "Not Palindrome"));
        System.out.println("Recursive: " +
                (recursive ? "Palindrome" : "Not Palindrome"));
        System.out.println("Array Reversal: " +
                (array ? "Palindrome" : "Not Palindrome"));

        sc.close();
    }
}
