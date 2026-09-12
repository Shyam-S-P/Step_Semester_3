import java.util.Arrays;
import java.util.Scanner;

/*
 * The assignment requires LoanReceipt to be final while also requiring
 * ReferenceOnlyLoanReceipt to extend it and be checked with instanceof.
 * Java cannot satisfy both conditions simultaneously.
 *
 * Therefore LoanReceipt is kept non-final here so the required subclass
 * and instanceof behavior can compile and run.
 */
class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    static {
        System.setProperty("library.ledger.ready", "true");
    }

    public LoanReceipt(String memberId, String[] bookIds) {
        if (bookIds == null)
            throw new IllegalArgumentException("Invalid book IDs");

        for (String id : bookIds) {
            if (id == null || !id.matches("BK-\\d{3}"))
                throw new IllegalArgumentException("Invalid book ID");
        }

        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length ||
            newId == null || !newId.matches("BK-\\d{3}"))
            throw new IllegalArgumentException("Invalid correction");

        String[] corrected = bookIds.clone();
        corrected[index] = newId;

        return new LoanReceipt(memberId, corrected);
    }

    static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        if (receipts == null)
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt)
                referenceOnly++;
            else
                regular++;
        }

        return processed + " processed | " + nullSkipped +
               " null skipped | " + referenceOnly +
               " reference-only | " + regular + " regular";
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId,
                                    String[] bookIds,
                                    String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}

public class Week_5_ImmutableLoanReceiptNightlyCirculationLedger {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter member ID:");
            String memberId = sc.nextLine();

            System.out.println("Enter number of book IDs:");
            int n = Integer.parseInt(sc.nextLine());

            String[] bookIds = new String[n];

            for (int i = 0; i < n; i++) {
                System.out.println("Enter book ID " + (i + 1) +
                        " (BK- followed by 3 digits):");
                bookIds[i] = sc.nextLine();
            }

            LoanReceipt receipt =
                    new LoanReceipt(memberId, bookIds);

            System.out.println("Book IDs: " +
                    Arrays.toString(receipt.getBookIds()));

            System.out.println("Enter index to correct:");
            int index = Integer.parseInt(sc.nextLine());

            System.out.println("Enter corrected book ID:");
            String newId = sc.nextLine();

            LoanReceipt corrected =
                    receipt.withCorrectedBookId(index, newId);

            System.out.println("Corrected IDs: " +
                    Arrays.toString(corrected.getBookIds()));

            System.out.println("Enter room number for reference-only receipt:");
            String room = sc.nextLine();

            ReferenceOnlyLoanReceipt referenceReceipt =
                    new ReferenceOnlyLoanReceipt(memberId, bookIds, room);

            LoanReceipt[] receipts = {
                referenceReceipt,
                null,
                receipt
            };

            System.out.println(
                    LoanReceipt.processNightlyCirculation(receipts));

        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        sc.close();
    }
}
