import java.util.Scanner;

class SrmStudent {
    private String name;
    private String regNo;
    private int attendance;

    static String university =
            "SRM Institute of Science and Technology";
    static int admissionCount = 0;

    SrmStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;

        admissionCount++;
        this.regNo = "RA231100301" +
                String.format("%03d", 10 + admissionCount);
    }

    void printIdCard() {
        System.out.println(name + " | " + regNo + " | " + university);
    }

    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " +
                admissionCount);
    }
}

public class Week_3_InstanceStaticBoundary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of students:");
        int n = sc.nextInt();
        sc.nextLine();

        SrmStudent[] students = new SrmStudent[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter student " + (i + 1) + " name:");
            String name = sc.nextLine();

            System.out.println("Enter attendance:");
            int attendance = sc.nextInt();
            sc.nextLine();

            students[i] = new SrmStudent(name, attendance);
        }

        System.out.println("\nID Cards:");

        for (SrmStudent student : students) {
            student.printIdCard();
        }

        SrmStudent.printTotalAdmissions();

        sc.close();
    }
}
