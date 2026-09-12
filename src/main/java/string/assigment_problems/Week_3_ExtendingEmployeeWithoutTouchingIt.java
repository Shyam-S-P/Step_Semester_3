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

    String getEmpName() {
        return empName;
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

public class Week_3_ExtendingEmployeeWithoutTouchingIt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter plain employee ID:");
        String id1 = sc.nextLine();

        System.out.println("Enter plain employee name:");
        String name1 = sc.nextLine();

        System.out.println("Enter plain employee salary:");
        double salary1 = sc.nextDouble();
        sc.nextLine();

        Employee plain = new Employee(id1, name1, salary1);

        System.out.println("\nEnter manager employee ID:");
        String id2 = sc.nextLine();

        System.out.println("Enter manager employee name:");
        String name2 = sc.nextLine();

        System.out.println("Enter manager salary:");
        double salary2 = sc.nextDouble();

        System.out.println("Enter team bonus:");
        double bonus = sc.nextDouble();
        sc.nextLine();

        Employee manager = new ManagerEmployee(
                id2, name2, salary2, bonus);

        System.out.println("\nEnter intern employee ID:");
        String id3 = sc.nextLine();

        System.out.println("Enter intern employee name:");
        String name3 = sc.nextLine();

        System.out.println("Enter intern salary:");
        double salary3 = sc.nextDouble();

        System.out.println("Enter stipend cap:");
        double cap = sc.nextDouble();

        Employee intern = new InternEmployee(
                id3, name3, salary3, cap);

        printPay(plain);
        printPay(manager);
        printPay(intern);

        sc.close();
    }

    static void printPay(Employee employee) {
        if (employee instanceof ManagerEmployee) {
            ManagerEmployee manager = (ManagerEmployee) employee;
            System.out.println("Manager effective pay: Rs " +
                    manager.effectiveSalary());
        } else if (employee instanceof InternEmployee) {
            InternEmployee intern = (InternEmployee) employee;
            System.out.println("Intern effective pay: Rs " +
                    intern.effectiveSalary());
        } else {
            System.out.println("Plain employee pay: Rs " +
                    employee.getSalary());
        }
    }
}
