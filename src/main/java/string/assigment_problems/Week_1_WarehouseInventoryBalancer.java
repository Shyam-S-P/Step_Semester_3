import java.util.Scanner;

public class Week_1_WarehouseInventoryBalancer {

    static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0;
        int totalB = 0;

        for (int value : sectionA)
            totalA += value;

        for (int value : sectionB)
            totalB += value;

        String status =
                totalA == totalB ? "Balanced" : "Not Balanced";

        int highest = Integer.MIN_VALUE;
        char highestSection = 'A';
        int highestIndex = -1;

        for (int i = 0; i < sectionA.length; i++) {
            if (sectionA[i] > highest) {
                highest = sectionA[i];
                highestSection = 'A';
                highestIndex = i;
            }
        }

        for (int i = 0; i < sectionB.length; i++) {
            if (sectionB[i] > highest) {
                highest = sectionB[i];
                highestSection = 'B';
                highestIndex = i;
            }
        }

        System.out.println("Section A Total: " + totalA +
                " | Section B Total: " + totalB +
                " | Status: " + status);

        System.out.println("Highest Quantity: " + highest +
                " (Section " + highestSection +
                ", Item " + (highestIndex + 1) + ")");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of items:");
        int n = sc.nextInt();

        int[] sectionA = new int[n];
        int[] sectionB = new int[n];

        System.out.println("Enter Section A quantities:");
        for (int i = 0; i < n; i++)
            sectionA[i] = sc.nextInt();

        System.out.println("Enter Section B quantities:");
        for (int i = 0; i < n; i++)
            sectionB[i] = sc.nextInt();

        analyzeInventory(sectionA, sectionB);

        sc.close();
    }
}
