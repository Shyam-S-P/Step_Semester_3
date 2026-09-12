import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class BusTicket {
    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (!isMeaningfulName(passengerName) ||
            !isMeaningfulDestination(destination)) {
            throw new IllegalArgumentException("Invalid booking");
        }

        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
    }

    private boolean isMeaningfulName(String name) {
        if (name == null || name.trim().isEmpty())
            return false;

        return name.matches("[A-Za-z ]+");
    }

    private boolean isMeaningfulDestination(String destination) {
        return destination != null && !destination.trim().isEmpty();
    }

    public void markCheckedIn() {
        if (!checkedIn) {
            checkedIn = true;
            System.out.println("Checked in successfully.");
        } else {
            System.out.println("Already checked in.");
        }
    }

    public static void processBatch(String[][] rawBookings) {
        Set<String> accepted = new HashSet<>();
        int valid = 0, rejected = 0, duplicates = 0;

        for (String[] booking : rawBookings) {
            if (booking == null || booking.length < 2) {
                rejected++;
                continue;
            }

            try {
                BusTicket ticket =
                    new BusTicket(booking[0], booking[1]);

                String key = ticket.passengerName.toLowerCase() +
                             "|" + ticket.destination.toLowerCase();

                if (accepted.contains(key)) {
                    duplicates++;
                } else {
                    accepted.add(key);
                    valid++;
                }
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid +
                " | Rejected: " + rejected +
                " | Duplicates skipped: " + duplicates);
    }
}

public class Week_4_BusTicketBookingValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of booking attempts:");
        int n = sc.nextInt();
        sc.nextLine();

        String[][] bookings = new String[n][2];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter passenger name:");
            bookings[i][0] = sc.nextLine();

            System.out.println("Enter destination:");
            bookings[i][1] = sc.nextLine();
        }

        BusTicket.processBatch(bookings);

        System.out.println("Test a ticket check-in.");
        System.out.println("Enter passenger name:");
        String name = sc.nextLine();

        System.out.println("Enter destination:");
        String destination = sc.nextLine();

        try {
            BusTicket ticket = new BusTicket(name, destination);
            ticket.markCheckedIn();
            ticket.markCheckedIn();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid booking.");
        }

        sc.close();
    }
}
