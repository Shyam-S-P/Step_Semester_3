import java.util.Scanner;

class Employee {
    private String empId;
    private String empName;
    private double salary;

    Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    ManagerEmployee(String empId, String empName,
                    double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

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
        if (occupiedCount < capacity)
            occupiedCount++;
    }
}

class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId,
                          Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    String fullProfile() {
        double pay;

        if (employee instanceof ManagerEmployee)
            pay = ((ManagerEmployee) employee).effectiveSalary();
        else
            pay = employee.getSalary();

        String slotInfo = slot == null
                ? "no parking assigned"
                : slot.slotNo;

        return name + " | Pay: Rs " + pay +
                " | Slot: " + slotInfo;
    }
}

public class Week_3_HRParkingAllocationMiniSystem {
    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot slot : slots) {
            if (slot != null &&
                slot.occupiedCount < slot.capacity)
                return slot;
        }

        return null;
    }

    static ParkingSlot safeAllot(ParkingSlot[] slots,
                                 String vehicleNo) {
        ParkingSlot slot = findAvailableSlot(slots);

        if (slot != null) {
            slot.allot(vehicleNo);
            return slot;
        }

        System.out.println("No slots available for " + vehicleNo);
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of parking slots:");
        int slotCount = sc.nextInt();
        sc.nextLine();

        ParkingSlot[] slots = new ParkingSlot[slotCount];

        for (int i = 0; i < slotCount; i++) {
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

        CompanyEmployeeRecord[] records =
                new CompanyEmployeeRecord[3];

        for (int i = 0; i < 3; i++) {
            System.out.println("\nEnter employee " + (i + 1) + " name:");
            String name = sc.nextLine();

            System.out.println("Enter employee ID:");
            String empId = sc.nextLine();

            System.out.println("Enter employee type (1-Manager, 2-Plain, 3-Intern):");
            int type = sc.nextInt();

            System.out.println("Enter salary:");
            double salary = sc.nextDouble();
            sc.nextLine();

            Employee employee;

            if (type == 1) {
                System.out.println("Enter team bonus:");
                double bonus = sc.nextDouble();
                sc.nextLine();

                employee = new ManagerEmployee(
                        empId, name, salary, bonus);
            } else if (type == 3) {
                System.out.println("Enter stipend cap:");
                double cap = sc.nextDouble();
                sc.nextLine();

                employee = new InternEmployee(
                        empId, name, salary, cap);
            } else {
                employee = new Employee(
                        empId, name, salary);
            }

            ParkingSlot assignedSlot = null;

            // Only the first two records are intentionally given parking.
            if (i < 2) {
                System.out.println(
                        "Enter vehicle number for parking allocation:");
                String vehicleNo = sc.nextLine();

                assignedSlot = safeAllot(
                        slots, vehicleNo);
            }

            records[i] = new CompanyEmployeeRecord(
                    name, empId, employee, assignedSlot);
        }

        System.out.println("\nEmployee Profiles:");

        for (CompanyEmployeeRecord record : records)
            System.out.println(record.fullProfile());

        System.out.println("Total records: " +
                CompanyEmployeeRecord.totalRecords);

        sc.close();
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(String empId, String empName,
                   double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}
