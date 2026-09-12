import java.util.Scanner;

class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = 0;
    }

    void allot(String studentName) {
        if (occupied < beds) {
            occupied++;
            System.out.println(studentName +
                    " allotted to room " + roomNo);
        } else {
            System.out.println("Room " + roomNo +
                    " is full. " + studentName +
                    " added to waiting list.");
        }
    }
}

public class Week_3_ObjectReferences {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter room number:");
        String roomNo = sc.nextLine();

        System.out.println("Enter number of beds:");
        int beds = sc.nextInt();
        sc.nextLine();

        HostelRoom room = new HostelRoom(roomNo, beds);

        HostelRoom sameRoom = room;

        System.out.println("Enter first student name:");
        String firstStudent = sc.nextLine();
        sameRoom.allot(firstStudent);

        System.out.println("Occupied through first reference: " +
                room.occupied);
        System.out.println("Occupied through second reference: " +
                sameRoom.occupied);

        HostelRoom separate = new HostelRoom(roomNo, beds);
        separate.occupied = room.occupied;

        System.out.println("sameRoom == room: " +
                (sameRoom == room));
        System.out.println("separate == room: " +
                (separate == room));

        System.out.println("Enter number of additional students to allot:");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter student name:");
            String student = sc.nextLine();
            room.allot(student);
        }

        sc.close();
    }
}
