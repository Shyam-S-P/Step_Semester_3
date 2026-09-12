import java.util.Scanner;

class BookInventory {
    private int copiesTotal;
    private int copiesAvailable;

    BookInventory(int copiesTotal) {
        if (copiesTotal <= 0)
            throw new IllegalArgumentException("Invalid copiesTotal");

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    void checkOut() {
        if (copiesAvailable > 0)
            copiesAvailable--;
    }

    void checkIn() {
        if (copiesAvailable < copiesTotal)
            copiesAvailable++;
    }

    int getCopiesAvailable() {
        return copiesAvailable;
    }
}

public class Week_5_BookCopyCirculationGuard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter total copies:");
        int total = sc.nextInt();

        try {
            BookInventory inventory = new BookInventory(total);

            System.out.println("Enter number of checkouts:");
            int checkouts = sc.nextInt();

            for (int i = 0; i < checkouts; i++)
                inventory.checkOut();

            System.out.println("Available after checkouts: " +
                    inventory.getCopiesAvailable());

            System.out.println("Enter number of check-ins:");
            int checkins = sc.nextInt();

            for (int i = 0; i < checkins; i++)
                inventory.checkIn();

            System.out.println("Available after check-ins: " +
                    inventory.getCopiesAvailable());

        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        sc.close();
    }
}
