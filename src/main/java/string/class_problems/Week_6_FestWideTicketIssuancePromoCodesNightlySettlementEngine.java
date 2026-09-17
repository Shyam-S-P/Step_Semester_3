import java.util.Scanner;

class EventTicket {
    private static int ticketsIssued = 0;

    public final String ticketId;
    protected double basePrice;
    protected double balanceDue;

    public EventTicket(double basePrice) {
        if (basePrice <= 0)
            throw new IllegalArgumentException("Invalid base price");

        this.basePrice = basePrice;
        this.balanceDue = basePrice;

        ticketsIssued++;
        ticketId = "TCK-" + (1000 + ticketsIssued);
    }

    public void pay(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Payment must be positive");

        balanceDue = Math.max(0, balanceDue - amount);
    }

    public void pay(double amount, String mode) {
        System.out.println("Payment mode: " + mode);
        pay(amount);
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5)
            return false;

        if (code.charAt(0) != 'F')
            return false;

        if (!Character.isDigit(code.charAt(1)) ||
            !Character.isDigit(code.charAt(2)) ||
            !Character.isDigit(code.charAt(3)))
            return false;

        return Character.isUpperCase(code.charAt(4));
    }

    public static int getTicketsIssued() {
        return ticketsIssued;
    }

    public void printTicket() {
        System.out.println(ticketId +
                " | Balance Due: " + balanceDue);
    }
}

class GroupTicket extends EventTicket {
    private int groupSize;

    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);

        if (groupSize <= 0)
            throw new IllegalArgumentException("Invalid group size");

        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}

public class Week_6_FestWideTicketIssuancePromoCodesNightlySettlementEngine {

    static String processNightlySettlement(EventTicket[] tickets) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (EventTicket ticket : tickets) {
            if (ticket == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (ticket instanceof GroupTicket)
                group++;
            else
                individual++;
        }

        return processed + " processed | " +
                nullSkipped + " null skipped | " +
                group + " group | " +
                individual + " individual";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter individual ticket price:");
        double individualPrice = sc.nextDouble();

        System.out.println("Enter group ticket price:");
        double groupPrice = sc.nextDouble();

        System.out.println("Enter group size:");
        int groupSize = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter promo code:");
        String promo = sc.nextLine();

        EventTicket t1 =
                new EventTicket(individualPrice);
        GroupTicket group =
                new GroupTicket(groupPrice, groupSize);

        System.out.println("Ticket 1 ID: " + t1.ticketId);
        System.out.println("Tickets Issued: " +
                EventTicket.getTicketsIssued());

        System.out.println("Promo valid: " +
                EventTicket.isValidPromoCode(promo));

        System.out.println("Enter first payment:");
        double p1 = sc.nextDouble();

        System.out.println("Enter second payment:");
        double p2 = sc.nextDouble();
        sc.nextLine();

        System.out.println("Enter payment mode:");
        String mode = sc.nextLine();

        t1.pay(p1);
        t1.pay(p2, mode);

        System.out.println("Balance Due: " +
                t1.getBalanceDue());

        EventTicket[] tickets = {
                group, null, t1
        };

        System.out.println(
                processNightlySettlement(tickets));

        sc.close();
    }
}
