import java.util.Scanner;

class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    void pay(double amount) {
        if (amount <= 0) {
            System.out.println("Payment must be positive.");
            return;
        }

        if (amount > getDue()) {
            System.out.println("Payment exceeds the due amount.");
            return;
        }

        amountPaid += amount;
    }

    double getDue() {
        return totalFee - amountPaid;
    }

    void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }

    double effectiveDue(double scholarshipPercent) {
        return getDue() * (1 - scholarshipPercent / 100);
    }
}

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
        } else {
            System.out.println("Room " + roomNo + " is full.");
        }
    }
}

class SrmStudent {
    String name;
    String regNo;
    FeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    SrmStudent(String name, String regNo,
               FeeAccount feeAccount, HostelRoom room) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = room;
        totalStudents++;
    }

    String fullStatus() {
        return name + " | Due: Rs " +
                feeAccount.getDue() +
                " | Room: " + room.roomNo;
    }
}

public class Week_3_StudentFeeRoomSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of students:");
        int n = sc.nextInt();
        sc.nextLine();

        SrmStudent[] students = new SrmStudent[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter student " + (i + 1) + " name:");
            String name = sc.nextLine();

            System.out.println("Enter registration number:");
            String regNo = sc.nextLine();

            System.out.println("Enter total fee:");
            double totalFee = sc.nextDouble();

            System.out.println("Enter amount already paid:");
            double amountPaid = sc.nextDouble();
            sc.nextLine();

            System.out.println("Enter room number:");
            String roomNo = sc.nextLine();

            System.out.println("Enter number of beds:");
            int beds = sc.nextInt();
            sc.nextLine();

            FeeAccount fee = new FeeAccount(
                    regNo, totalFee, amountPaid);

            HostelRoom room = new HostelRoom(roomNo, beds);

            students[i] = new SrmStudent(
                    name, regNo, fee, room);
        }

        System.out.println("\nEnter student number for payment:");
        int studentNumber = sc.nextInt();

        System.out.println("Enter payment amount:");
        double payment = sc.nextDouble();

        students[studentNumber - 1].feeAccount.pay(payment);

        System.out.println("\nStudent Status:");

        for (SrmStudent student : students) {
            System.out.println(student.fullStatus());
        }

        System.out.println("Total students: " +
                SrmStudent.totalStudents);

        sc.close();
    }
}
