import java.util.Scanner;

class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo +
                    " allotted to slot " + slotNo);
        }
    }
}

public class Week_3_ObjectReferencesNullSafetyMutatingMethod {

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot slot : slots) {
            if (slot != null && slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }

        return null;
    }

    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot slot = findAvailableSlot(slots);

        if (slot != null) {
            slot.allot(vehicleNo);
        } else {
            System.out.println(
                    "No slots available for " + vehicleNo);
        }
    }

    /*
     * A Java object array stores references to ParkingSlot objects.
     * Passing the array copies the array reference, not every ParkingSlot.
     * Therefore changes made through a slot reference affect the same object.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of parking slots:");
        int n = sc.nextInt();
        sc.nextLine();

        ParkingSlot[] slots = new ParkingSlot[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter slot number:");
            String slotNo = sc.nextLine();

            System.out.println("Enter capacity:");
            int capacity = sc.nextInt();

            System.out.println("Enter occupied count:");
            int occupied = sc.nextInt();
            sc.nextLine();

            slots[i] = new ParkingSlot(
                    slotNo, capacity, occupied);
        }

        System.out.println("Enter vehicle number for first allocation:");
        String vehicle1 = sc.nextLine();
        safeAllot(slots, vehicle1);

        System.out.println("Enter vehicle number for second allocation:");
        String vehicle2 = sc.nextLine();
        safeAllot(slots, vehicle2);

        sc.close();
    }
}
