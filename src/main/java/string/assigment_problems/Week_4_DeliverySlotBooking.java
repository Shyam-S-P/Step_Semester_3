import java.util.Scanner;

class DeliverySlot {
    private String orderId;
    private String timeSlot;

    public DeliverySlot(String orderId, String timeSlot) {
        this.orderId = orderId;
        this.timeSlot = timeSlot;
    }

    public DeliverySlot(String orderId) {
        this(orderId, "ASAP");
    }

    public boolean isPeakHour() {
        return timeSlot.equals("12:00-13:00") ||
               timeSlot.equals("13:00-14:00") ||
               timeSlot.equals("19:00-20:00") ||
               timeSlot.equals("20:00-21:00");
    }

    public String getTimeSlot() {
        return timeSlot;
    }
}

public class Week_4_DeliverySlotBooking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter order ID:");
        String orderId = sc.nextLine();

        System.out.println("Use ASAP? (true/false):");
        boolean asap = Boolean.parseBoolean(sc.nextLine());

        DeliverySlot slot;

        if (asap) {
            slot = new DeliverySlot(orderId);
        } else {
            System.out.println("Enter time slot:");
            String time = sc.nextLine();
            slot = new DeliverySlot(orderId, time);
        }

        System.out.println("Time slot: " + slot.getTimeSlot());
        System.out.println("Peak hour: " + slot.isPeakHour());

        sc.close();
    }
}
