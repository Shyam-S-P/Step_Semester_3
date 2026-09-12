import java.util.Scanner;

class PatientProfile {
    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPinHash;

    public PatientProfile() {
        this(null, null);
    }

    public PatientProfile(String name) {
        this(null, name);
    }

    public PatientProfile(String patientId, String name) {
        this.name = name;
        if (patientId != null)
            this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String id) {
        if (patientId == null)
            patientId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public void setLockerPin(String pin) {
        if (pin != null && pin.matches("\\d{4,6}"))
            lockerPinHash = Integer.toHexString(pin.hashCode());
    }
}

public class Week_5_PatientProfileJavaBean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter patient name:");
        String name = sc.nextLine();

        System.out.println("Enter patient ID (or press Enter for none):");
        String id = sc.nextLine();

        PatientProfile patient;

        if (id.isEmpty())
            patient = new PatientProfile(name);
        else
            patient = new PatientProfile(id, name);

        System.out.println("Patient ID: " + patient.getPatientId());

        System.out.println("Enter new patient ID:");
        String newId = sc.nextLine();

        patient.setPatientId(newId);

        System.out.println("Enter another patient ID:");
        String secondId = sc.nextLine();

        patient.setPatientId(secondId);

        System.out.println("Final patient ID: " +
                patient.getPatientId());

        System.out.println("Enter locker PIN (4-6 digits):");
        String pin = sc.nextLine();
        patient.setLockerPin(pin);

        System.out.println("Enter discharged status (true/false):");
        boolean discharged = Boolean.parseBoolean(sc.nextLine());

        patient.setDischarged(discharged);

        System.out.println("Discharged: " + patient.isDischarged());

        sc.close();
    }
}
