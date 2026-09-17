import java.util.Scanner;

class EventTicket {
    protected double basePrice;
    protected double balanceDue;

    public EventTicket(double basePrice) {
        if (basePrice <= 0)
            throw new IllegalArgumentException("Invalid base price");

        this.basePrice = basePrice;
        this.balanceDue = basePrice;
    }

    public EventTicket(String attendeeId, double basePrice) {
        this(basePrice);
    }

    public double getBalanceDue() {
        return balanceDue;
    }

    public void printTicket() {
        System.out.println("Standard | Balance: " +
                getBalanceDue());
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(double basePrice, String track) {
        super(basePrice);

        if (track == null || track.trim().isEmpty())
            throw new IllegalArgumentException("Invalid track");

        this.track = track.trim();
    }

    public WorkshopTicket(String attendeeId, double basePrice,
                           String track) {
        this(basePrice, track);
    }

    @Override
    public void printTicket() {
        System.out.println("Workshop | Track: " + track +
                " | Balance: " + getBalanceDue());
    }

    public String getTrack() {
        return track;
    }
}

public class Week_6_NightlyTicketAnnouncer {

    static String batchPrint(EventTicket[] tickets) {
        StringBuilder report = new StringBuilder();

        for (EventTicket ticket : tickets) {
            ticket.printTicket();

            if (ticket instanceof WorkshopTicket) {
                WorkshopTicket workshop =
                        (WorkshopTicket) ticket;

                report.append("Workshop | Track: ")
                      .append(workshop.getTrack())
                      .append(" | Balance: ")
                      .append(workshop.getBalanceDue())
                      .append(" [Track via downcast: ")
                      .append(workshop.getTrack())
                      .append("] | ");
            } else {
                report.append("Standard | Balance: ")
                      .append(ticket.getBalanceDue())
                      .append(" | ");
            }
        }

        return report.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter standard ticket price:");
        double standardPrice = sc.nextDouble();
        sc.nextLine();

        System.out.println("Enter workshop ticket price:");
        double workshopPrice = sc.nextDouble();
        sc.nextLine();

        System.out.println("Enter workshop track:");
        String track = sc.nextLine();

        EventTicket[] tickets = {
                new EventTicket(standardPrice),
                new WorkshopTicket(workshopPrice, track)
        };

        System.out.println(batchPrint(tickets));

        sc.close();
    }
}
