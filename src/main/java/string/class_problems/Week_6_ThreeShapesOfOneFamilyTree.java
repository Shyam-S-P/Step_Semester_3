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

class PremiumWorkshopTicket extends WorkshopTicket {
    private double kitFee;

    public PremiumWorkshopTicket(String attendeeId, double basePrice,
                                  String track, double kitFee) {
        super(attendeeId, basePrice, track);

        if (kitFee < 0)
            throw new IllegalArgumentException("Invalid kit fee");

        this.kitFee = kitFee;
    }

    @Override
    public void printTicket() {
        System.out.println("Premium Workshop Ticket | Track: " +
                track + " | Kit Fee: " + kitFee +
                " | Balance Due: " + getBalanceDue());
    }
}

class HackathonTicket extends EventTicket {
    private String teamName;

    public HackathonTicket(String attendeeId, double basePrice,
                           String teamName) {
        super(attendeeId, basePrice);

        if (teamName == null || teamName.trim().isEmpty())
            throw new IllegalArgumentException("Invalid team name");

        this.teamName = teamName.trim();
    }

    @Override
    public void printTicket() {
        System.out.println("Hackathon Ticket | Team: " + teamName +
                " | Balance Due: " + getBalanceDue());
    }
}

public class Week_6_ThreeShapesOfOneFamilyTree {

    static String classifyGeneration(EventTicket ticket) {
        if (ticket instanceof PremiumWorkshopTicket)
            return "Multilevel descendant (3 generations deep)";

        if (ticket instanceof HackathonTicket)
            return "Hierarchical sibling (independent branch)";

        if (ticket instanceof WorkshopTicket)
            return "Direct child of EventTicket";

        return "Base EventTicket";
    }

    static double getTotalBalanceDue(EventTicket[] tickets) {
        double total = 0;

        for (EventTicket ticket : tickets) {
            if (ticket != null)
                total += ticket.getBalanceDue();
        }

        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter base ticket price:");
        double standardPrice = sc.nextDouble();
        sc.nextLine();

        System.out.println("Enter standard attendee ID:");
        String id1 = sc.nextLine();

        System.out.println("Enter workshop attendee ID:");
        String id2 = sc.nextLine();

        System.out.println("Enter workshop track:");
        String track = sc.nextLine();

        System.out.println("Enter premium attendee ID:");
        String id3 = sc.nextLine();

        System.out.println("Enter premium track:");
        String premiumTrack = sc.nextLine();

        System.out.println("Enter kit fee:");
        double kitFee = sc.nextDouble();
        sc.nextLine();

        System.out.println("Enter hackathon attendee ID:");
        String id4 = sc.nextLine();

        System.out.println("Enter team name:");
        String team = sc.nextLine();

        EventTicket standard =
                new EventTicket(id1, standardPrice);
        WorkshopTicket workshop =
                new WorkshopTicket(id2, standardPrice, track);
        PremiumWorkshopTicket premium =
                new PremiumWorkshopTicket(id3, standardPrice,
                        premiumTrack, kitFee);
        HackathonTicket hackathon =
                new HackathonTicket(id4, standardPrice, team);

        standard.printTicket();
        workshop.printTicket();
        premium.printTicket();
        hackathon.printTicket();

        System.out.println(classifyGeneration(premium));
        System.out.println(classifyGeneration(hackathon));

        EventTicket[] tickets =
                {standard, workshop, premium, hackathon};

        System.out.println("Total Balance Due: " +
                getTotalBalanceDue(tickets));

        sc.close();
    }
}
