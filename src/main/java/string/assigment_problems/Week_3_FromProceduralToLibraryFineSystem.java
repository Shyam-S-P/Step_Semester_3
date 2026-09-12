import java.util.Scanner;

class BookIssue {
    private String title;
    private String borrowerName;
    private int daysOverdue;

    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    double fineAmount() {
        return daysOverdue > 0 ? daysOverdue * 5 : 0;
    }

    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    // fineAmount() belongs to one BookIssue, so it is an instance method.
    // totalFineCollected() works on the whole array, so it belongs to the class.
    static double totalFineCollected(BookIssue[] issues) {
        double total = 0;

        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }

        return total;
    }

    String getTitle() {
        return title;
    }

    int getDaysOverdue() {
        return daysOverdue;
    }
}

public class Week_3_FromProceduralToLibraryFineSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BookIssue[] issues = new BookIssue[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("Enter book title:");
            String title = sc.nextLine();

            System.out.println("Enter borrower name:");
            String borrowerName = sc.nextLine();

            System.out.println("Enter days overdue:");
            int daysOverdue = sc.nextInt();
            sc.nextLine();

            issues[i] = new BookIssue(title, borrowerName, daysOverdue);
        }

        System.out.println("\nBook Details:");

        for (BookIssue issue : issues) {
            String status = issue.isSeverelyOverdue()
                    ? "Severely overdue"
                    : "OK";

            System.out.println(issue.getTitle() + " - " +
                    issue.getDaysOverdue() + " days - " + status);
        }

        System.out.println("Total fine collected: Rs " +
                BookIssue.totalFineCollected(issues));

        sc.close();
    }
}
