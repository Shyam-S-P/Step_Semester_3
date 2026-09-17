import java.util.Scanner;

class EventTicket {
    protected String attendeeId;
    protected double basePrice;
    protected double balanceDue;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4)
            throw new IllegalArgumentException("Invalid attendee ID");

        if (basePrice <= 0)
            throw new IllegalArgumentException("Invalid base price");

        this.attendeeId = attendeeId.trim();
        this.basePrice = basePrice;
        this.balanceDue = basePrice;
    }

    public EventTicket(double basePrice) {
        this("ANON", basePrice);
    }

    public void pay(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Payment must be positive");

        balanceDue = Math.max(0, balanceDue - amount);
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public void printTicket() {
        System.out.println("Standard Event Ticket | Balance Due: " +
                getBalanceDue());
    }

    public static String registerBatch(String[] attendeeIds,
                                       double basePrice) {
        int registered = 0;
        int rejected = 0;

        for (String id : attendeeIds) {
            try {
                new EventTicket(id, basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered +
                " | Rejected: " + rejected;
    }
}

class WorkshopTicket extends EventTicket {
    protected String track;

    public WorkshopTicket(String attendeeId, double basePrice,
                           String track) {
        super(attendeeId, basePrice);

        if (track == null || track.trim().isEmpty())
            throw new IllegalArgumentException("Invalid track");

        this.track = track.trim();
    }

    public WorkshopTicket(double basePrice, String track) {
        this("USER", basePrice, track);
    }

    @Override
    public void printTicket() {
        System.out.println("Workshop Ticket | Track: " + track +
                " | Balance Due: " + getBalanceDue());
    }

    public String getTrack() {
        return track;
    }
}

public class Week_6_TicketHierarchyFoundationBatchRegistrationValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter base price:");
        double price = sc.nextDouble();
        sc.nextLine();

        System.out.println("Enter number of attendee IDs:");
        int n = sc.nextInt();
        sc.nextLine();

        String[] ids = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter attendee ID " + (i + 1) + ":");
            ids[i] = sc.nextLine();
        }

        System.out.println(
                EventTicket.registerBatch(ids, price));

        System.out.println("Enter workshop attendee ID:");
        String id = sc.nextLine();

        System.out.println("Enter workshop track:");
        String track = sc.nextLine();

        System.out.println("Enter workshop payment:");
        double payment = sc.nextDouble();

        try {
            WorkshopTicket workshop =
                    new WorkshopTicket(id, price, track);
            workshop.pay(payment);
            workshop.printTicket();
        } catch (IllegalArgumentException e) {
            System.out.println("Workshop construction/payment rejected.");
        }

        sc.close();
    }
}
