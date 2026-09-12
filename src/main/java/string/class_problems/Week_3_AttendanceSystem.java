import java.util.Scanner;

class SrmStudent {
    private String name;
    private String regNo;
    private int attendance;

    SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }

    boolean isEligible() {
        return attendance >= 75;
    }

    static double classAverage(SrmStudent[] students) {
        int total = 0;
        for (SrmStudent student : students) {
            total += student.attendance;
        }
        return (double) total / students.length;
    }

    String getName() {
        return name;
    }

    int getAttendance() {
        return attendance;
    }
}

public class Week_3_AttendanceSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        SrmStudent[] students = new SrmStudent[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("Enter student " + (i + 1) + " name:");
            String name = sc.nextLine();

            System.out.println("Enter registration number:");
            String regNo = sc.nextLine();

            System.out.println("Enter attendance percentage:");
            int attendance = sc.nextInt();
            sc.nextLine();

            students[i] = new SrmStudent(name, regNo, attendance);
        }

        System.out.println("\nAttendance Details:");

        for (SrmStudent student : students) {
            System.out.println(student.getName() + " - " +
                    student.getAttendance() + "% - " +
                    (student.isEligible() ? "Eligible" : "Detained"));
        }

        System.out.println("Class average: " +
                SrmStudent.classAverage(students) + "%");

        sc.close();
    }
}
