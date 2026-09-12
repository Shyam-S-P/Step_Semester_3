import java.util.Scanner;

class AccessRuleEngine {
    static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("public")) return "ALLOWED";
        if (fieldModifier.equals("private"))
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
        if (fieldModifier.equals("default"))
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE") ? "ALLOWED" : "DENIED";
        if (fieldModifier.equals("protected"))
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE") ? "ALLOWED" : "DENIED";
        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {
        int allowed = 0, denied = 0;
        for (String[] attempt : attempts) {
            if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED"))
                allowed++;
            else
                denied++;
        }
        return "Allowed: " + allowed + " | Denied: " + denied;
    }
}

class PatientRecord {
    private String patientId;
    String wardCode;
    protected double vitalsScore;
    public String facilityName;

    public PatientRecord(String patientId, String wardCode,
                         double vitalsScore, String facilityName) {
        String id = patientId == null ? "" : patientId.trim();
        if (id.length() < 4)
            throw new IllegalArgumentException("Invalid patientId");

        this.patientId = id;
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }
}

public class Week_5_FieldVisibilityIntakeValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter field modifier:");
        String modifier = sc.nextLine();

        System.out.println("Enter accessor context:");
        String context = sc.nextLine();

        System.out.println("Access: " +
                AccessRuleEngine.classifyAccess(modifier, context));

        System.out.println("Enter number of batch attempts:");
        int n = Integer.parseInt(sc.nextLine());

        String[][] attempts = new String[n][2];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter field modifier:");
            attempts[i][0] = sc.nextLine();

            System.out.println("Enter accessor context:");
            attempts[i][1] = sc.nextLine();
        }

        System.out.println(AccessRuleEngine.summarizeBatch(attempts));

        System.out.println("Enter patient ID:");
        String patientId = sc.nextLine();

        try {
            new PatientRecord(patientId, "W3", 98.2, "MediTrack Central");
            System.out.println("construction succeeded");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        sc.close();
    }
}
