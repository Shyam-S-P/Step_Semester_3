import java.util.Scanner;

class FoodOrder {
    private String studentName;
    private String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty())
            throw new IllegalArgumentException("Invalid student name");

        if (dishName == null || dishName.trim().isEmpty())
            throw new IllegalArgumentException("Invalid dish name");

        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
    }

    public void markDelivered() {
        if (!delivered) {
            delivered = true;
            System.out.println("Order marked as delivered.");
        } else {
            System.out.println("Order was already delivered.");
        }
    }

    public static void processBatch(String[][] rawOrders) {
        int valid = 0, rejected = 0;

        for (String[] order : rawOrders) {
            if (order == null || order.length < 2) {
                rejected++;
                continue;
            }

            try {
                new FoodOrder(order[0], order[1]);
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid +
                " | Rejected: " + rejected);
    }
}

public class Week_4_GhostOrderValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of orders:");
        int n = sc.nextInt();
        sc.nextLine();

        String[][] orders = new String[n][2];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter student name:");
            orders[i][0] = sc.nextLine();

            System.out.println("Enter dish name:");
            orders[i][1] = sc.nextLine();
        }

        FoodOrder.processBatch(orders);

        System.out.println("Enter a valid student name:");
        String student = sc.nextLine();

        System.out.println("Enter dish name:");
        String dish = sc.nextLine();

        try {
            FoodOrder order = new FoodOrder(student, dish);
            order.markDelivered();
            order.markDelivered();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid order.");
        }

        sc.close();
    }
}
